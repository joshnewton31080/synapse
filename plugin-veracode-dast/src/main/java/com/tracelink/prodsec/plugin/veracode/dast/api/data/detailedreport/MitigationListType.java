//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:35 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.dast.api.data.detailedreport;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This represents a list of mitigation annotations for this flaw.
 * This can contain any number of mitigation child elements.
 *
 *
 * <p>Java class for MitigationListType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MitigationListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mitigation" type="{https://www.veracode.com/schema/reports/export/1.0}MitigationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MitigationListType", namespace = "https://www.veracode.com/schema/reports/export/1.0", propOrder = {
		"mitigation"
})
public class MitigationListType {

	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0")
	private List<MitigationType> mitigation;

	/**
	 * Gets the value of the mitigation property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the mitigation property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getMitigation().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MitigationType }
	 *
	 * @return value of the mitigation property, or an empty list if the mitigation property is null
	 */
	public List<MitigationType> getMitigation() {
		if (mitigation == null) {
			mitigation = new ArrayList<>();
		}
		return this.mitigation;
	}

}
