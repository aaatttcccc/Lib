//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.09.04 at 07:26:18 PM PDT
//


package com.zhongjh.sardine.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
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
 *         &lt;element ref="{DAV:}response" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{DAV:}responsedescription" minOccurs="0"/&gt;
 *         &lt;element ref="{DAV:}sync-token" minOccurs="0"/&gt;
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
public class Multistatus {

    //@XmlElement(required = true)
    @ElementList(inline = true)
    protected List<Response> response;
    @Element(required = false)
    protected String responsedescription;
    //@XmlElement(name = "sync-token")
    //@XmlSchemaType(name = "anyURI")
    protected String syncToken;

    /**
     * Gets the value of the response property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the response property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponse().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Response }
     *
     *
     */
    public List<Response> getResponse() {
        if (response == null) {
            response = new ArrayList<Response>();
        }
        return this.response;
    }

    /**
     * Gets the value of the responsedescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getResponsedescription() {
        return responsedescription;
    }

    /**
     * Sets the value of the responsedescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResponsedescription(String value) {
        this.responsedescription = value;
    }

    /**
     * Gets the value of the syncToken property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSyncToken() {
        return syncToken;
    }

    /**
     * Sets the value of the syncToken property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSyncToken(String value) {
        this.syncToken = value;
    }

}
