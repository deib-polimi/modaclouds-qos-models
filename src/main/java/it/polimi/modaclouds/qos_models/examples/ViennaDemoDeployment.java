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
package it.polimi.modaclouds.qos_models.examples;

import it.polimi.modaclouds.monitoring.kb.api.KBEntity;
import it.polimi.modaclouds.qos_models.monitoring_ontology.VM;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViennaDemoDeployment extends DeploymentModelFactory {

	private final Logger logger = LoggerFactory.getLogger(ViennaDemoDeployment.class);
	
	@Override
	public Set<KBEntity> getModel() {
		Set<KBEntity> entities = new HashSet<KBEntity>();
		try {
			VM amazonVM = new VM("FrontendVM1");
			amazonVM.setType("FrontendVM");
			amazonVM.setCloudProvider("Amazon");
			entities.add(amazonVM);
		} catch (URISyntaxException e) {
			logger.error("Error while creating model", e);
		}

		return entities;
	}

}
