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
	
	// TODO URI and Prefix are both here and in kb-api
	public static final String URI = "http://www.modaclouds.eu/rdfs/1.0/entities#";
    public static String prefix = "modaent";

	public static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
	
	public static OntClass Component = makeClass(MOVocabulary.Component);
	public static OntClass CloudProvider = makeClass(MOVocabulary.CloudProvider);
	public static OntClass VM = makeClass(MOVocabulary.VM);
	public static OntClass PaaSService = makeClass(MOVocabulary.PaaSService);
	public static OntClass Location = makeClass(MOVocabulary.Location);
	public static OntClass InternalComponent = makeClass(MOVocabulary.InternalComponent);
	public static OntClass ExternalComponent = makeClass(MOVocabulary.ExternalComponent);
	public static OntClass Method = makeClass(MOVocabulary.Method);

	public static Property cloudProvider = makeProperty(MOVocabulary.cloudProvider);
	public static Property requiredComponents = makeProperty(MOVocabulary.requiredComponents);
	public static Property location = makeProperty(MOVocabulary.location);
	public static Property providedMethods = makeProperty(MOVocabulary.providedMethods);
	public static Property name = makeProperty(MOVocabulary.name);
	public static Property type = makeProperty(MOVocabulary.type);
	public static Property id = makeProperty(MOVocabulary.id);
	public static Property numberOfCPUs = makeProperty(MOVocabulary.numberOfCPUs);

	public static OntClass Resource = makeClass(MOVocabulary.Resource);
	

	static {
		model.setNsPrefix(prefix, URI);
		ExternalComponent.addProperty(RDFS.subClassOf, Component);
		VM.addProperty(RDFS.subClassOf, ExternalComponent);
		PaaSService.addProperty(RDFS.subClassOf, ExternalComponent);
		Component.addProperty(RDFS.subClassOf, Resource);
		InternalComponent.addProperty(RDFS.subClassOf, Component);
		Method.addProperty(RDFS.subClassOf, Resource);
		CloudProvider.addProperty(RDFS.subClassOf, Resource);
		Location.addProperty(RDFS.subClassOf, Resource);
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
