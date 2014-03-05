//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.05 at 11:26:42 AM CET 
//


package it.polimi.modaclouds.qos_models;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for constraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="constraint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="targetResourceIDRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="metric" type="{http://www.modaclouds.eu/xsd/1.0/metrics_schema}metricString"/>
 *         &lt;element name="metricAggregation" type="{http://www.modaclouds.eu/xsd/1.0/qos_constraints_schema}qosMetricAggregation" minOccurs="0"/>
 *         &lt;element name="range" type="{http://www.modaclouds.eu/xsd/1.0/qos_constraints_schema}range"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "constraint", namespace = "http://www.modaclouds.eu/xsd/1.0/qos_constraints_schema", propOrder = {
    "targetResourceIDRef",
    "metric",
    "metricAggregation",
    "range",
    "priority"
})
public class Constraint {

    @XmlElement(required = true)
    protected String targetResourceIDRef;
    @XmlElement(required = true)
    protected String metric;
    protected QosMetricAggregation metricAggregation;
    @XmlElement(required = true)
    protected Range range;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger priority;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the targetResourceIDRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetResourceIDRef() {
        return targetResourceIDRef;
    }

    /**
     * Sets the value of the targetResourceIDRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetResourceIDRef(String value) {
        this.targetResourceIDRef = value;
    }

    /**
     * Gets the value of the metric property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetric() {
        return metric;
    }

    /**
     * Sets the value of the metric property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetric(String value) {
        this.metric = value;
    }

    /**
     * Gets the value of the metricAggregation property.
     * 
     * @return
     *     possible object is
     *     {@link QosMetricAggregation }
     *     
     */
    public QosMetricAggregation getMetricAggregation() {
        return metricAggregation;
    }

    /**
     * Sets the value of the metricAggregation property.
     * 
     * @param value
     *     allowed object is
     *     {@link QosMetricAggregation }
     *     
     */
    public void setMetricAggregation(QosMetricAggregation value) {
        this.metricAggregation = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link Range }
     *     
     */
    public Range getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link Range }
     *     
     */
    public void setRange(Range value) {
        this.range = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority(BigInteger value) {
        this.priority = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
