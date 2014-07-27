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
import it.polimi.modaclouds.qos_models.monitoring_ontology.CloudProvider;
import it.polimi.modaclouds.qos_models.monitoring_ontology.InternalComponent;
import it.polimi.modaclouds.qos_models.monitoring_ontology.VM;

import java.util.HashSet;
import java.util.Set;

public class SofteamDeployment extends DeploymentModelFactory {

	public static int numberOfAgents = 2;
//	private final Logger logger = LoggerFactory
//			.getLogger(SofteamDeployment.class);

	@Override
	public Set<KBEntity> getModel() {

		Set<KBEntity> entities = new HashSet<KBEntity>();

		CloudProvider amazonCloud = new CloudProvider();
		entities.add(amazonCloud);
		amazonCloud.setId("Amazon");

		VM adminServer = new VM();
		entities.add(adminServer);
		adminServer.setId("AdministrationServer1");
		adminServer.setType("AdministrationServer");
		adminServer.setCloudProvider(amazonCloud.getUri());

		InternalComponent serverApp = new InternalComponent();
		serverApp.setType("ServerApp");
		serverApp.setId("ServerApp1");
		serverApp.addRequiredComponent(adminServer.getUri());
		entities.add(serverApp);

		for (int i = 0; i < numberOfAgents; i++) {
			VM mainAgent = new VM();
			mainAgent.setId("MainAgent" + (i + 1));
			;
			mainAgent.setCloudProvider(amazonCloud.getUri());
			mainAgent.setType("MainAgent");
			entities.add(mainAgent);

			InternalComponent agentApp = new InternalComponent();
			agentApp.setId("agentApp" + (i + 1));
			agentApp.setType("AgentApp");
			agentApp.addRequiredComponent(mainAgent.getUri());
			entities.add(agentApp);

			serverApp.addRequiredComponent(agentApp.getUri());
		}

		return entities;
	}

}
