//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:35 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.sast.api.data.detailedreport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * For each category, there are the following attributes:
 * * categoryid:  A numeric identifier for the category.
 * * categoryname:  The name of the category.
 * * pcirelated:  Whether the flaw is PCI related.  (This will be specified in a
 * future release of the platform.)
 * <p>
 * Each category also has the following child elements:
 * * desc:  A list of paragraphs describing the category.
 * * recommendations:  A list of paragraphs describing how to manage flaws within
 * that category.
 * * cwe:  For each distinct CWE ID for which there is at least one flaw within this
 * category, there is a cwe element.
 *
 *
 * <p>Java class for CategoryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CategoryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="desc" type="{https://www.veracode.com/schema/reports/export/1.0}ParaListType"/&gt;
 *         &lt;element name="recommendations" type="{https://www.veracode.com/schema/reports/export/1.0}ParaListType"/&gt;
 *         &lt;element name="cwe" type="{https://www.veracode.com/schema/reports/export/1.0}CweType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="categoryid" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="categoryname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="pcirelated" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", namespace = "https://www.veracode.com/schema/reports/export/1.0", propOrder = {"desc", "recommendations", "cwe"})
public class CategoryType {

	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0", required = true)
	private ParaListType desc;
	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0", required = true)
	private ParaListType recommendations;
	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0", required = true)
	private List<CweType> cwe;
	@XmlAttribute(name = "categoryid", required = true)
	private BigInteger categoryid;
	@XmlAttribute(name = "categoryname", required = true)
	private String categoryname;
	@XmlAttribute(name = "pcirelated")
	private Boolean pcirelated;

	/**
	 * Gets the value of the desc property.
	 *
	 * @return possible object is
	 * {@link ParaListType }
	 */
	public ParaListType getDesc() {
		return desc;
	}

	/**
	 * Sets the value of the desc property.
	 *
	 * @param value allowed object is
	 *              {@link ParaListType }
	 */
	public void setDesc(ParaListType value) {
		this.desc = value;
	}

	/**
	 * Gets the value of the recommendations property.
	 *
	 * @return possible object is
	 * {@link ParaListType }
	 */
	public ParaListType getRecommendations() {
		return recommendations;
	}

	/**
	 * Sets the value of the recommendations property.
	 *
	 * @param value allowed object is
	 *              {@link ParaListType }
	 */
	public void setRecommendations(ParaListType value) {
		this.recommendations = value;
	}

	/**
	 * Gets the value of the cwe property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the cwe property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getCwe().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CweType }
	 *
	 * @return value of the cwe property, or an empty list if the cwe property is null
	 */
	public List<CweType> getCwe() {
		if (cwe == null) {
			cwe = new ArrayList<>();
		}
		return this.cwe;
	}

	/**
	 * Gets the value of the categoryid property.
	 *
	 * @return possible object is
	 * {@link BigInteger }
	 */
	public BigInteger getCategoryid() {
		return categoryid;
	}

	/**
	 * Sets the value of the categoryid property.
	 *
	 * @param value allowed object is
	 *              {@link BigInteger }
	 */
	public void setCategoryid(BigInteger value) {
		this.categoryid = value;
	}

	/**
	 * Gets the value of the categoryname property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCategoryname() {
		return categoryname;
	}

	/**
	 * Sets the value of the categoryname property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCategoryname(String value) {
		this.categoryname = value;
	}

	/**
	 * Gets the value of the pcirelated property.
	 *
	 * @return possible object is
	 * {@link Boolean }
	 */
	public Boolean isPcirelated() {
		return pcirelated;
	}

	/**
	 * Sets the value of the pcirelated property.
	 *
	 * @param value allowed object is
	 *              {@link Boolean }
	 */
	public void setPcirelated(Boolean value) {
		this.pcirelated = value;
	}

}
