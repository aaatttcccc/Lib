//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.09.04 at 10:01:05 AM PDT
//


package com.zhongjh.sardine.model;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{DAV:}supported-report" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class SupportedReportSet {

    //@XmlElement(name = "supported-report", required = true)
    protected List<SupportedReport> supportedReport;

    /**
     * Gets the value of the supportedReport property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedReport property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedReport().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupportedReport }
     *
     *
     */
    public List<SupportedReport> getSupportedReport() {
        if (supportedReport == null) {
            supportedReport = new ArrayList<SupportedReport>();
        }
        return this.supportedReport;
    }

}
