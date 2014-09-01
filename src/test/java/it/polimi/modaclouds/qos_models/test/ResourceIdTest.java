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
package it.polimi.modaclouds.qos_models.test;

import static org.junit.Assert.fail;
import it.polimi.modaclouds.qos_models.monitoring_ontology.Resource;
import it.polimi.modaclouds.qos_models.monitoring_ontology.Vocabulary;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class ResourceIdTest {

	@Test
	public void test() {
		
		try {
			BeanUtils.getProperty(new Resource(), Vocabulary.resourceIdParameterName);
		} catch (Exception e) {
			System.out.println("Vacabulary is not aligned with Resource");
			fail();
		}
		
	}

}
