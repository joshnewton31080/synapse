//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:35 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.dast.api.data.detailedreport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * For each analysis (static or dynamic), there is a list of modules.  If this
 * element is for static analysis, there is one module element per module
 * analyzed.  If this element is for dynamic analysis, there is exactly one
 * module element.
 * <p>
 * Each static or dynamic analysis has these attribute values:
 * * rating: A letter grade.
 * * score: A numeric score.
 * * mitigated_rating: A letter grade, taking into account flaws that are mitigated.
 * * mitigated_score: A numeric score, taking into account flaws that are mitigated.
 * * submitted_date: The date that this analysis was submitted to Veracode.
 * * published_date: The date that this analysis was published by Veracode.
 * * next_scan_due: The date that the active policy for this application requests the next scan by.
 * * analysis_size_bytes: Optional (Static Analysis Only) size of modules scanned in bytes.
 * * engine_version: The version of the engine that this scan was run against. Static only.
 * * dynamic_scan_type: Optional (Dynamic Analysis Only) indicates whether the build is MP or DS.
 * * scan_exit_status_id: Optional (Dynamic Analysis Only) A numeric code for scan exit status.
 * * scan_exit_status_desc: Optional (Dynamic Analysis Only) The description corresponds to the
 * status id.
 * * version: Optional scan name.
 *
 *
 * <p>Java class for AnalysisType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AnalysisType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="modules"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="module" type="{https://www.veracode.com/schema/reports/export/1.0}ModuleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="rating" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="mitigated_rating" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="mitigated_score" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="submitted_date" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="published_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="next_scan_due" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="analysis_size_bytes" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="engine_version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dynamic_scan_type" type="{https://www.veracode.com/schema/reports/export/1.0}DynamicScanType" /&gt;
 *       &lt;attribute name="scan_exit_status_id" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="scan_exit_status_desc" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalysisType", namespace = "https://www.veracode.com/schema/reports/export/1.0", propOrder = {"modules"})
public class AnalysisType {

	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0", required = true)
	private AnalysisType.Modules modules;
	@XmlAttribute(name = "rating", required = true)
	private String rating;
	@XmlAttribute(name = "score", required = true)
	private BigInteger score;
	@XmlAttribute(name = "mitigated_rating")
	private String mitigatedRating;
	@XmlAttribute(name = "mitigated_score")
	private BigInteger mitigatedScore;
	@XmlAttribute(name = "submitted_date", required = true)
	private String submittedDate;
	@XmlAttribute(name = "published_date")
	private String publishedDate;
	@XmlAttribute(name = "next_scan_due")
	private String nextScanDue;
	@XmlAttribute(name = "analysis_size_bytes")
	private Long analysisSizeBytes;
	@XmlAttribute(name = "engine_version")
	private String engineVersion;
	@XmlAttribute(name = "dynamic_scan_type")
	private DynamicScanType dynamicScanType;
	@XmlAttribute(name = "scan_exit_status_id")
	private BigInteger scanExitStatusId;
	@XmlAttribute(name = "scan_exit_status_desc")
	private String scanExitStatusDesc;
	@XmlAttribute(name = "version")
	private String version;

	/**
	 * Gets the value of the modules property.
	 *
	 * @return possible object is
	 * {@link AnalysisType.Modules }
	 */
	public AnalysisType.Modules getModules() {
		return modules;
	}

	/**
	 * Sets the value of the modules property.
	 *
	 * @param value allowed object is
	 *              {@link AnalysisType.Modules }
	 */
	public void setModules(AnalysisType.Modules value) {
		this.modules = value;
	}

	/**
	 * Gets the value of the rating property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Sets the value of the rating property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setRating(String value) {
		this.rating = value;
	}

	/**
	 * Gets the value of the score property.
	 *
	 * @return possible object is
	 * {@link BigInteger }
	 */
	public BigInteger getScore() {
		return score;
	}

	/**
	 * Sets the value of the score property.
	 *
	 * @param value allowed object is
	 *              {@link BigInteger }
	 */
	public void setScore(BigInteger value) {
		this.score = value;
	}

	/**
	 * Gets the value of the mitigatedRating property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMitigatedRating() {
		return mitigatedRating;
	}

	/**
	 * Sets the value of the mitigatedRating property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMitigatedRating(String value) {
		this.mitigatedRating = value;
	}

	/**
	 * Gets the value of the mitigatedScore property.
	 *
	 * @return possible object is
	 * {@link BigInteger }
	 */
	public BigInteger getMitigatedScore() {
		return mitigatedScore;
	}

	/**
	 * Sets the value of the mitigatedScore property.
	 *
	 * @param value allowed object is
	 *              {@link BigInteger }
	 */
	public void setMitigatedScore(BigInteger value) {
		this.mitigatedScore = value;
	}

	/**
	 * Gets the value of the submittedDate property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * Sets the value of the submittedDate property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSubmittedDate(String value) {
		this.submittedDate = value;
	}

	/**
	 * Gets the value of the publishedDate property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * Sets the value of the publishedDate property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setPublishedDate(String value) {
		this.publishedDate = value;
	}

	/**
	 * Gets the value of the nextScanDue property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNextScanDue() {
		return nextScanDue;
	}

	/**
	 * Sets the value of the nextScanDue property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNextScanDue(String value) {
		this.nextScanDue = value;
	}

	/**
	 * Gets the value of the analysisSizeBytes property.
	 *
	 * @return possible object is
	 * {@link Long }
	 */
	public Long getAnalysisSizeBytes() {
		return analysisSizeBytes;
	}

	/**
	 * Sets the value of the analysisSizeBytes property.
	 *
	 * @param value allowed object is
	 *              {@link Long }
	 */
	public void setAnalysisSizeBytes(Long value) {
		this.analysisSizeBytes = value;
	}

	/**
	 * Gets the value of the engineVersion property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getEngineVersion() {
		return engineVersion;
	}

	/**
	 * Sets the value of the engineVersion property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setEngineVersion(String value) {
		this.engineVersion = value;
	}

	/**
	 * Gets the value of the dynamicScanType property.
	 *
	 * @return possible object is
	 * {@link DynamicScanType }
	 */
	public DynamicScanType getDynamicScanType() {
		return dynamicScanType;
	}

	/**
	 * Sets the value of the dynamicScanType property.
	 *
	 * @param value allowed object is
	 *              {@link DynamicScanType }
	 */
	public void setDynamicScanType(DynamicScanType value) {
		this.dynamicScanType = value;
	}

	/**
	 * Gets the value of the scanExitStatusId property.
	 *
	 * @return possible object is
	 * {@link BigInteger }
	 */
	public BigInteger getScanExitStatusId() {
		return scanExitStatusId;
	}

	/**
	 * Sets the value of the scanExitStatusId property.
	 *
	 * @param value allowed object is
	 *              {@link BigInteger }
	 */
	public void setScanExitStatusId(BigInteger value) {
		this.scanExitStatusId = value;
	}

	/**
	 * Gets the value of the scanExitStatusDesc property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getScanExitStatusDesc() {
		return scanExitStatusDesc;
	}

	/**
	 * Sets the value of the scanExitStatusDesc property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setScanExitStatusDesc(String value) {
		this.scanExitStatusDesc = value;
	}

	/**
	 * Gets the value of the version property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setVersion(String value) {
		this.version = value;
	}


	/**
	 * <p>Java class for anonymous complex type.
	 *
	 * <p>The following schema fragment specifies the expected content contained within this class.
	 *
	 * <pre>
	 * &lt;complexType&gt;
	 *   &lt;complexContent&gt;
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
	 *       &lt;sequence&gt;
	 *         &lt;element name="module" type="{https://www.veracode.com/schema/reports/export/1.0}ModuleType" maxOccurs="unbounded" minOccurs="0"/&gt;
	 *       &lt;/sequence&gt;
	 *     &lt;/restriction&gt;
	 *   &lt;/complexContent&gt;
	 * &lt;/complexType&gt;
	 * </pre>
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {"module"})
	public static class Modules {

		@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0")
		private List<ModuleType> module;

		/**
		 * Gets the value of the module property.
		 *
		 * <p>
		 * This accessor method returns a reference to the live list,
		 * not a snapshot. Therefore any modification you make to the
		 * returned list will be present inside the JAXB object.
		 * This is why there is not a <CODE>set</CODE> method for the module property.
		 *
		 * <p>
		 * For example, to add a new item, do as follows:
		 * <pre>
		 *    getModule().add(newItem);
		 * </pre>
		 *
		 *
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link ModuleType }
		 *
		 * @return value of the module property, or an empty list if the module property is null
		 */
		public List<ModuleType> getModule() {
			if (module == null) {
				module = new ArrayList<>();
			}
			return this.module;
		}

	}

}
