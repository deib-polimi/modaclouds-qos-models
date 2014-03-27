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

import java.net.URL;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.RDFS;

public class MO {
	
	public static final String URI = "http://www.modaclouds.eu/rdfs/1.0/monitoring/";
    public static final String streamsURI = "http://www.modaclouds.eu/monitoring/streams/";
    public static final String kbURLSuffix = "/modaclouds/kb";
    private static String knowledgeBaseURL = "http://localhost:3030" + kbURLSuffix;

	public static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
	// *** System *** //
	public static OntClass component = makeClass(Vocabulary.Component);
	public static OntClass vm = makeClass(Vocabulary.VM);
	public static OntClass paasService = makeClass(Vocabulary.PaaSService);
	public static OntClass location = makeClass(Vocabulary.Location);
	public static OntClass internalComponent = makeClass(Vocabulary.InternalComponent);
	public static OntClass externalComponent = makeClass(Vocabulary.ExternalComponent);
	public static OntClass method = makeClass(Vocabulary.Method);

	public static Property hasProvider = makeProperty(Vocabulary.hasProvider);
	public static Property requires = makeProperty(Vocabulary.requires);
	public static Property isIn = makeProperty(Vocabulary.isIn);
	public static Property provides = makeProperty(Vocabulary.provides);
	public static Property calls = makeProperty(Vocabulary.calls);
	public static Property hasNCPU = makeProperty(Vocabulary.hasNCPU);
	public static Property hasName = makeProperty(Vocabulary.hasName);
	public static Property hasPath = makeProperty(Vocabulary.hasPath);
	public static Property hasType = makeProperty(Vocabulary.hasType);
	public static Property hasURL = makeProperty(Vocabulary.hasURL);

	// *** Monitoring *** /
	public static OntClass monitoringComponent = makeClass(Vocabulary.MonitoringComponent);
	public static OntClass monitorableResource = makeClass(Vocabulary.MonitorableResource);
	public static OntClass sda = makeClass(Vocabulary.StatisticalDataAnalyzer);
	public static OntClass dc = makeClass(Vocabulary.DataCollector);
	public static OntClass parameter = makeClass(Vocabulary.Parameter);
	public static OntClass monitoringDatum = makeClass(Vocabulary.MonitoringDatum);
	public static OntClass sdaFactory = makeClass(Vocabulary.SDAFactory);
	public static OntClass dcFactory = makeClass(Vocabulary.DCFactory);

	public static Property isAbout = makeProperty(Vocabulary.isAbout);
	public static Property hasParameter = makeProperty(Vocabulary.hasParameter);
	public static Property hasTargetResource = makeProperty(Vocabulary.hasTargetResource);
	public static Property instantiates = makeProperty(Vocabulary.instantiates);
	public static Property hasMetric = makeProperty(Vocabulary.hasMetric);
	public static Property hasValue = makeProperty(Vocabulary.hasValue);
	public static Property hasTimestamp = makeProperty(Vocabulary.hasTargetResource);
	public static Property hasPeriod = makeProperty(Vocabulary.hasPeriod);
	public static Property hasMethod = makeProperty(Vocabulary.hasMethod);
	public static Property hasReturnedMetric = makeProperty(Vocabulary.hasReturnedMetric);
	public static Property hasTargetMetric = makeProperty(Vocabulary.hasTargetMetric);
	public static Property isStarted = makeProperty(Vocabulary.isStarted);
	public static Property isEnabled = makeProperty(Vocabulary.isEnabled);
	public static Property hasCollectedMetric = makeProperty(Vocabulary.hasCollectedMetric);

	static {
		model.setNsPrefix("mo", URI);
		externalComponent.addProperty(RDFS.subClassOf, component);
		vm.addProperty(RDFS.subClassOf, externalComponent);
		paasService.addProperty(RDFS.subClassOf, externalComponent);
		vm.addProperty(isIn, location);
		location.addProperty(isIn, location);
		component.addProperty(RDFS.subClassOf, monitorableResource);
		monitoringDatum.addProperty(isAbout, monitorableResource);
		internalComponent.addProperty(requires, component);
		internalComponent.addProperty(RDFS.subClassOf, component);
		internalComponent.addProperty(provides, method);
		method.addProperty(calls, method);
		method.addProperty(RDFS.subClassOf, monitorableResource);
		monitoringComponent.addProperty(RDFS.subClassOf, internalComponent);
		sdaFactory.addProperty(RDFS.subClassOf, monitoringComponent);
		dcFactory.addProperty(RDFS.subClassOf, monitoringComponent);
		sdaFactory.addProperty(instantiates, sda);
		sda.addProperty(hasTargetResource, monitorableResource);
		sda.addProperty(hasParameter, parameter);
		dcFactory.addProperty(instantiates, dc);
		dc.addProperty(hasTargetResource, monitorableResource);
		dc.addProperty(hasParameter, parameter);
	}

	private static Property makeProperty(String string) {
		return model.createProperty(URI + string);
	}

	private static OntClass makeClass(String string) {
		return model.createClass(URI + string);
	}

    public static String getKnowledgeBaseURL() {
        return knowledgeBaseURL;
    }
    
    public static void setKnowledgeBaseURL(URL url) {
    	knowledgeBaseURL = url.toString() + kbURLSuffix;
    }
    
    public static String getKnowledgeBaseDataURL() {
        return knowledgeBaseURL + "/data";
    }

   

}
