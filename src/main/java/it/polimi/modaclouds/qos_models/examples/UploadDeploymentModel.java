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

import it.polimi.modaclouds.monitoring.kb.api.FusekiKBAPI;
import it.polimi.modaclouds.monitoring.kb.api.KBEntity;
import it.polimi.modaclouds.qos_models.monitoring_ontology.MO;

import java.lang.reflect.Method;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadDeploymentModel {

	private static Logger logger = LoggerFactory
			.getLogger(UploadDeploymentModel.class);

	private static final Class<? extends DeploymentModelFactory> currentModelFactory = SofteamDeployment.class;
	private static final String knowledgeBaseURL = "http://localhost:3030/modaclouds/kb";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			FusekiKBAPI kbAPI = new FusekiKBAPI(knowledgeBaseURL,"it.polimi.modaclouds.qos_models.monitoring_ontology");

			Method m = currentModelFactory.getMethod("getModel");
			Set<KBEntity> entities = (Set<KBEntity>) m
					.invoke(currentModelFactory.newInstance());

			kbAPI.add(entities);

		} catch (Exception e) {
			logger.error("Error", e);
		}
	}

}
