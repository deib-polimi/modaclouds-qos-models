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

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.RDFS;

public class MO {
	
	public static final String URI = "http://www.modaclouds.eu/rdfs/1.0/monitoring/";
    public static String prefix = "mo";

	public static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
	// *** System *** //
	public static OntClass Component = makeClass(Vocabulary.Component);
	public static OntClass VM = makeClass(Vocabulary.VM);
	public static OntClass PaaSService = makeClass(Vocabulary.PaaSService);
	public static OntClass Location = makeClass(Vocabulary.Location);
	public static OntClass InternalComponent = makeClass(Vocabulary.InternalComponent);
	public static OntClass ExternalComponent = makeClass(Vocabulary.ExternalComponent);
	public static OntClass Method = makeClass(Vocabulary.Method);

	public static Property cloudProvider = makeProperty(Vocabulary.cloudProvider);
	public static Property requiredComponent = makeProperty(Vocabulary.requiredComponent);
	public static Property location = makeProperty(Vocabulary.location);
	public static Property providedMethod = makeProperty(Vocabulary.providedMethod);
	public static Property name = makeProperty(Vocabulary.name);
	public static Property type = makeProperty(Vocabulary.type);
	public static Property id = makeProperty(Vocabulary.id);

	// *** Monitoring *** /
	public static OntClass MonitoringComponent = makeClass(Vocabulary.MonitoringComponent);
	public static OntClass MonitorableResource = makeClass(Vocabulary.MonitorableResource);
	public static OntClass StatisticalDataAnalyzer = makeClass(Vocabulary.StatisticalDataAnalyzer);
	public static OntClass DataCollector = makeClass(Vocabulary.DataCollector);
	public static OntClass Parameter = makeClass(Vocabulary.Parameter);
	public static OntClass MonitoringDatum = makeClass(Vocabulary.MonitoringDatum);

	public static Property aboutResource = makeProperty(Vocabulary.aboutResource);
	public static Property parameter = makeProperty(Vocabulary.parameter);
	public static Property targetResource = makeProperty(Vocabulary.targetResource);
	public static Property metric = makeProperty(Vocabulary.metric);
	public static Property value = makeProperty(Vocabulary.value);
	public static Property timestamp = makeProperty(Vocabulary.timestamp);
	public static Property period = makeProperty(Vocabulary.period);
	public static Property method = makeProperty(Vocabulary.method);
	public static Property returnedMetric = makeProperty(Vocabulary.returnedMetric);
	public static Property targetMetric = makeProperty(Vocabulary.targetMetric);
	public static Property enabled = makeProperty(Vocabulary.enabled);
	public static Property collectedMetric = makeProperty(Vocabulary.collectedMetric);
	public static Property dataCollector = makeProperty(Vocabulary.dataCollector);
	

	static {
		model.setNsPrefix(prefix, URI);
		ExternalComponent.addProperty(RDFS.subClassOf, Component);
		VM.addProperty(RDFS.subClassOf, ExternalComponent);
		PaaSService.addProperty(RDFS.subClassOf, ExternalComponent);
		VM.addProperty(location, Location);
		Location.addProperty(location, Location);
		Component.addProperty(RDFS.subClassOf, MonitorableResource);
		MonitoringDatum.addProperty(aboutResource, MonitorableResource);
		MonitoringDatum.addProperty(dataCollector, DataCollector);
		InternalComponent.addProperty(requiredComponent, Component);
		InternalComponent.addProperty(RDFS.subClassOf, Component);
		InternalComponent.addProperty(providedMethod, Method);
		Method.addProperty(RDFS.subClassOf, MonitorableResource);
		MonitoringComponent.addProperty(RDFS.subClassOf, InternalComponent);
		StatisticalDataAnalyzer.addProperty(targetResource, MonitorableResource);
		StatisticalDataAnalyzer.addProperty(parameter, Parameter);
		DataCollector.addProperty(targetResource, MonitorableResource);
		DataCollector.addProperty(parameter, Parameter);
	}

	private static Property makeProperty(String string) {
		return model.createProperty(URI + string);
	}

	private static OntClass makeClass(String string) {
		return model.createClass(URI + string);
	}

    
	public static String shortForm(Property property) {
		return prefix+":"+property.getLocalName();
	}
	
	public static String shortForm(OntClass ontClass) {
		return prefix+":"+ontClass.getLocalName();
	}
   

}
