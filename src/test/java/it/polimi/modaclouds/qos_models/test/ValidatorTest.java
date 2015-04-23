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

import it.polimi.modaclouds.qos_models.monitoring_rules.ConfigurationException;
import it.polimi.modaclouds.qos_models.monitoring_rules.Problem;
import it.polimi.modaclouds.qos_models.monitoring_rules.Validator;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.schema.MultiCloudExtensions;
import it.polimi.modaclouds.qos_models.schema.ResourceModelExtension;
import it.polimi.modaclouds.qos_models.schema.UsageModelExtensions;
import it.polimi.modaclouds.qos_models.util.ValidationResult;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.io.InputStream;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;

public class ValidatorTest {

	@Test
	public void rulesTest() throws JAXBException, ConfigurationException,
			SAXException {
		InputStream testRulesStream = getClass().getResourceAsStream(
				"/MonitoringRules.xml");
		MonitoringRules rules = XMLHelper.deserialize(testRulesStream,
				MonitoringRules.class);
		Validator validator = new Validator();
		Set<Problem> problems = validator.validateAllRules(rules);
		if (!problems.isEmpty()) {
			for (Problem problem : problems) {
				System.out.println(problem.toString());
			}
			fail();
		}
	}

	@Test
	public void qosConstraintsShouldValidate() throws JAXBException,
			ConfigurationException, SAXException {
		InputStream testRulesStream = getClass().getResourceAsStream(
				"/qosConstraints.xml");
		Constraints constraints = XMLHelper.deserialize(testRulesStream,
				Constraints.class);
		Validator validator = new Validator();
		Set<Problem> problems = validator.validateAllConstraints(constraints);
		if (!problems.isEmpty()) {
			for (Problem problem : problems) {
				System.out.println(problem.toString());
			}
			fail();
		}
	}


	@Test
	public void architecturalConstraintsShouldValidate() throws JAXBException,
			ConfigurationException, SAXException {
		InputStream testRulesStream = getClass().getResourceAsStream(
				"/architecturalConstraints.xml");
		Constraints constraints = XMLHelper.deserialize(testRulesStream,
				Constraints.class);
		Validator validator = new Validator();
		Set<Problem> problems = validator.validateAllConstraints(constraints);
		if (!problems.isEmpty()) {
			for (Problem problem : problems) {
				System.out.println(problem.toString());
			}
			fail();
		}
	}

}
