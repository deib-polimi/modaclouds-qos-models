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
package it.polimi.modaclouds.qos_models.monitoring_ontology.util;

import it.polimi.modaclouds.qos_models.monitoring_ontology.MO;

import java.util.UUID;

public class MonitoringOntologyPrinter {

	public static void main(String[] args) {
		// System.out.println("<?xml version=\"1.0\"?>");
		// m.write(System.out, "RDF/XML-ABBREV");
		MO.model.write(System.out, "TURTLE");
	}
}