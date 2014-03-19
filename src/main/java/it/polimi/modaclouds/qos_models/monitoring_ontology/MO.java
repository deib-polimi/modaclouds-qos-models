/**
 * Copyright 2014 deib-polimi
 * Contact: deib-polimi <marco.miglierina@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package it.polimi.modaclouds.qos_models.monitoring_ontology;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * This class contains the monitoring ontology java representation
 *
 * @author miglie
 *
 */
public class MO {

    protected static final String URI = "http://www.modaclouds.eu/rdfs/1.0/monitoring/";
    protected static final String streamsURI = "http://www.modaclouds.eu/monitoring/streams/";
    protected static final String knowledgeBaseDataURL = "http://localhost:3030/modaclouds/kb/data";

    public static String getStreamsURI() {
        return streamsURI;
    }

    public static String getURI() {
        return URI;
    }

    public static String getKnowledgeBaseDataURL() {
        return knowledgeBaseDataURL;
    }

    public static Resource getResource(String local) {
        return ResourceFactory.createResource(URI + local);
    }

    public static Property getProperty(String local) {
        return ResourceFactory.createProperty(URI + local);
    }
    
    public static final String Component = "Component";
    public static final String ExternalComponent = "ExternalComponent";
    public static final String InternalComponent = "InternalComponent";
    public static final String CloudProvider = "CloudProvider";
    public static final String PaaSService = "PaaSService";
    public static final String VM = "VM";
    public static final String Method = "Method";
    public static final String Location = "Location";
    public static final String Region = "Region";
    public static final String Zone = "Zone";

    public static final String requires = "requires";
    public static final String provides = "provides";
    public static final String calls = "calls";
    public static final String offers = "offers";
    public static final String isIn = "isIn";

    /* --- Monitoring Specific --- */
    public static final String MonitoringDatum = "MonitoringDatum";

    public static final String hasMetric = "hasMetric";
    public static final String isAbout = "isAbout";
    public static final String hasValue = "hasValue";


}
