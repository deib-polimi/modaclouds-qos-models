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
 * @author miglie
 *
 */
public class MO {

	protected static final String URI = "http://www.modaclouds.eu/rdfs/1.0/monitoring/";
	protected static final String streamsURI = "http://www.modaclouds.eu/monitoring/streams/";
	
	public static String getStreamsURI() {
		return streamsURI;
	}

	public static String getURI() {
		return URI;
	}
	
	private static Resource resource (String local) {
		return ResourceFactory.createResource(URI + local);
	}
	
	private static Property property (String local) {
		return ResourceFactory.createProperty(URI + local);
	}
	

	/* --- System Specific --- */
	public static final Resource Component = resource("component");
	public static final Resource ExternalComponent = resource("external_component");
	public static final Resource InternalComponent = resource("internal_component");
	public static final Resource CloudProvider = resource("cloud_provider");
	public static final Resource PaasService = resource("paas_service");
	public static final Resource VM = resource("vm");
	public static final Resource Method = resource("method");
	public static final Resource Location = resource("location");
	public static final Resource Region = resource("region");
	public static final Resource Zone = resource("zone");
	
	public static final Property requires = property("requires");
	public static final Property provides = property("provides");
	public static final Property calls = property("calls");
	public static final Property offers = property("offers");
	public static final Property isIn = property("is_in");
	
	/* --- Monitoring Specific --- */
	public static final Resource MonitoringDatum = resource("monitoring_datum");
	public static final Resource Violation = resource("violation");
	
	public static final Property hasMetric = property("has_metric");
	public static final Property isAbout = property("is_about");
	public static final Property hasValue = property("has_value");
		
	
}