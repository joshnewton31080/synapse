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
 * <p>Java class for FilePathList complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FilePathList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="file_path" type="{https://www.veracode.com/schema/reports/export/1.0}FilePath" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilePathList", namespace = "https://www.veracode.com/schema/reports/export/1.0", propOrder = {
		"filePath"
})
public class FilePathList {

	@XmlElement(name = "file_path", namespace = "https://www.veracode.com/schema/reports/export/1.0")
	private List<FilePath> filePath;

	/**
	 * Gets the value of the filePath property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the filePath property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getFilePath().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link FilePath }
	 *
	 * @return value of the filePath property, or an empty list if the filePath property is null
	 */
	public List<FilePath> getFilePath() {
		if (filePath == null) {
			filePath = new ArrayList<>();
		}
		return this.filePath;
	}

}
