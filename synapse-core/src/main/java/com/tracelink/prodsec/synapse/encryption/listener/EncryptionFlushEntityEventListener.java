package com.tracelink.prodsec.synapse.encryption.listener;

import com.tracelink.prodsec.synapse.encryption.converter.AbstractEncryptedAttributeConverter;
import com.tracelink.prodsec.synapse.encryption.converter.DataEncryptionKeyConverter;
import com.tracelink.prodsec.synapse.encryption.model.DataEncryptionKey;
import com.tracelink.prodsec.synapse.encryption.model.EncryptionType;
import com.tracelink.prodsec.synapse.encryption.repository.DataEncryptionKeyRepository;
import com.tracelink.prodsec.synapse.encryption.service.KeyEncryptionService;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.Convert;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.event.internal.DefaultFlushEntityEventListener;
import org.hibernate.event.spi.FlushEntityEvent;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A flush entity event listener to perform dirty checks on entities that may contain encrypted
 * attributes. Extends the {@link DefaultFlushEntityEventListener}. This class is only configured
 * as an event listener if encryption has been configured for Synapse via environment variables.
 * Note that this may be enabled even if Synapse is configured with {@link EncryptionType#NONE}.
 * This occurs when Synapse is decrypting existing database values on startup.
 * <p>
 * The purpose of this class is to ensure that attributes are marked as dirty during key rotation
 * (both for key encryption keys and for data encryption keys). Without this logic, Hibernate
 * cannot identify that changes have been made to the entity and therefore does not perform the
 * database update. This means that the data would not be re-encrypted with the new key.
 *
 * @author mcool
 */
@Component
public class EncryptionFlushEntityEventListener extends DefaultFlushEntityEventListener {

	private static final long serialVersionUID = 909748040401122381L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EncryptionFlushEntityEventListener.class);

	private final DataEncryptionKeyRepository dataEncryptionKeyRepository;
	private final KeyEncryptionService keyEncryptionService;

	public EncryptionFlushEntityEventListener(
			@Autowired DataEncryptionKeyRepository dataEncryptionKeyRepository,
			@Autowired KeyEncryptionService keyEncryptionService) {
		this.dataEncryptionKeyRepository = dataEncryptionKeyRepository;
		this.keyEncryptionService = keyEncryptionService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dirtyCheck(final FlushEntityEvent event) throws HibernateException {
		super.dirtyCheck(event);
		checkEncryptedProperties(event);
	}

	/**
	 * Checks the entity in the given flush event for properties that have an encryption converter.
	 * If there are such properties, and the encryption keys associated with them are in the middle
	 * of a rotation, it will mark the properties as dirty. The resulting dirty properties are a
	 * superset of the dirty properties identified by the {@link DefaultFlushEntityEventListener}.
	 *
	 * @param event the flush entity event triggered on entity save
	 */
	private void checkEncryptedProperties(final FlushEntityEvent event) {
		int[] dirtyProperties = event.getDirtyProperties();
		if (dirtyProperties == null) {
			dirtyProperties = new int[0];
		}
		// Get information from event
		Object entity = event.getEntity();
		EntityEntry entry = event.getEntityEntry();
		EntityPersister entityPersister = entry.getPersister();
		String[] propertyNames = entityPersister.getPropertyNames();
		// Initialize array to hold dirty properties results
		int[] results = new int[propertyNames.length];
		int count = 0;

		// Loop through all properties of the entity
		for (int i = 0; i < propertyNames.length; i++) {
			final int index = i;
			// If this was already marked as dirty by the default listener, add it to results
			if (IntStream.of(dirtyProperties).anyMatch(p -> p == index)) {
				results[count++] = i;
				continue;
			}

			try {
				// Get all the @Convert annotations
				Field field = entity.getClass().getDeclaredField(propertyNames[i]);
				Convert[] convertAnnotations = field.getAnnotationsByType(Convert.class);
				count += markConvertAnnotationPropertyAsDirty(convertAnnotations, results,
						propertyNames, i,
						entry.getEntityName());
			} catch (NoSuchFieldException e) {
				LOGGER.debug("Unable to find field " + StringHelper
						.qualify(entry.getEntityName(), propertyNames[i]));
			}
		}
		// If there are dirty properties, set the results on the event
		if (count > 0) {
			event.setDirtyProperties(ArrayHelper.trim(results, count));
		}
	}

	/**
	 * Helper function to mark a property with a convert annotation as dirty.
	 *
	 * @param convertAnnotations list of convert annotations for an entity
	 * @param results            the array of dirty property indices
	 * @param propertyNames      array of all entity property names
	 * @param propertyIndex      index of the property name
	 * @param entityName         the name of the entity that the property belongs to
	 * @return the number of dirty encryption convert annotations
	 */
	private int markConvertAnnotationPropertyAsDirty(Convert[] convertAnnotations, int[] results,
			String[] propertyNames, int propertyIndex, String entityName) {
		int count = 0;
		for (Convert convertAnnotation : convertAnnotations) {
			// If it is an encrypted attribute converter...
			if (AbstractEncryptedAttributeConverter.class
					.isAssignableFrom(convertAnnotation.converter())) {
				// And if the associated DEK is being rotated, mark as dirty
				Optional<DataEncryptionKey> dataEncryptionKey = dataEncryptionKeyRepository
						.findByConverterClassName(convertAnnotation.converter().getName());
				if (dataEncryptionKey.isPresent() && dataEncryptionKey.get()
						.isRotationInProgress()) {
					LOGGER.trace(StringHelper.qualify(entityName, propertyNames[propertyIndex])
							+ " is dirty");
					results[count++] = propertyIndex;
				}
				// If it is a DEK converter...
			} else if (DataEncryptionKeyConverter.class
					.isAssignableFrom(convertAnnotation.converter())) {
				// And if the KEK is being rotated, mark as dirty
				if (keyEncryptionService.keyRotationInProgress()) {
					LOGGER.trace(StringHelper.qualify(entityName, propertyNames[propertyIndex])
							+ " is dirty");
					results[count++] = propertyIndex;
				}
			}
		}
		return count;
	}
}
