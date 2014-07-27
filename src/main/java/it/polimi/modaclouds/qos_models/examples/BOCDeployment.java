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

public class BOCDeployment extends DeploymentModelFactory {

	private final int nBLTiers = 3;
//	private final Logger logger = LoggerFactory.getLogger(BOCDeployment.class);

	@Override
	public Set<KBEntity> getModel() {

		Set<KBEntity> entities = new HashSet<KBEntity>();

		CloudProvider flexiantCloud = new CloudProvider();
		entities.add(flexiantCloud);
		flexiantCloud.setId("Flexiant");

		VM winVM = new VM();
		entities.add(winVM);
		winVM.setId("WinVM1");
		winVM.setType("WinVM");
		winVM.setCloudProvider(flexiantCloud.getUri());

		InternalComponent tomcat = new InternalComponent();
		entities.add(tomcat);
		tomcat.setId("Tomcat1");
		tomcat.setType("Tomcat");
		tomcat.addRequiredComponent(winVM.getUri());

		InternalComponent war = new InternalComponent();
		entities.add(war);
		war.setId("War1");
		war.setType("War");
		war.addRequiredComponent(tomcat.getUri());

		InternalComponent sqlDB = new InternalComponent();
		entities.add(sqlDB);
		sqlDB.setId("SQLDB1");
		sqlDB.setType("SQLDB");
		sqlDB.addRequiredComponent(winVM.getUri());

		for (int i = 0; i < nBLTiers; i++) {
			InternalComponent bLTier = new InternalComponent();
			entities.add(bLTier);
			bLTier.setId("BLTier" + (i + 1));
			bLTier.setType("BLTier");
			bLTier.addRequiredComponent(sqlDB.getUri());
			bLTier.addRequiredComponent(winVM.getUri());

			war.addRequiredComponent(bLTier.getUri());
		}

		return entities;
	}

}
