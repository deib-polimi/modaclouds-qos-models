//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.05 at 11:26:42 AM CET 
//


package it.polimi.modaclouds.qos_models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggregateFunction" type="{http://www.modaclouds.eu/xsd/2.0/aggregate_functions_schema}aggregateFunction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "aggregateFunctions"
})
@XmlRootElement(name = "aggregateFunctions", namespace = "http://www.modaclouds.eu/xsd/2.0/aggregate_functions_schema")
public class AggregateFunctions {

    @XmlElement(name = "aggregateFunction", namespace = "http://www.modaclouds.eu/xsd/2.0/aggregate_functions_schema")
    protected List<AggregateFunction> aggregateFunctions;

    /**
     * Gets the value of the aggregateFunctions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aggregateFunctions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAggregateFunctions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AggregateFunction }
     * 
     * 
     */
    public List<AggregateFunction> getAggregateFunctions() {
        if (aggregateFunctions == null) {
            aggregateFunctions = new ArrayList<AggregateFunction>();
        }
        return this.aggregateFunctions;
    }

}
