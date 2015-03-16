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

import static org.junit.Assert.*;
import it.polimi.modaclouds.qos_models.monitoring_rules.MonitoringRuleFactory;
import it.polimi.modaclouds.qos_models.monitoring_rules.Problem;
import it.polimi.modaclouds.qos_models.monitoring_rules.Validator;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.io.InputStream;
import java.util.Set;

import org.junit.Test;

public class Constraint2RuleTest {

	@Test
	public void rulesFromQoSConstraintsShouldValidate() {
		try {
			InputStream testRulesStream = getClass().getResourceAsStream(
					"/qosConstraints.xml");
			Constraints constraints = XMLHelper.deserialize(testRulesStream,
					Constraints.class);
			MonitoringRuleFactory factory = new MonitoringRuleFactory();
			MonitoringRules rules = factory
					.makeRulesFromQoSConstraints(constraints);
			Validator validator = new Validator();
			Set<Problem> problems = validator.validateAllRules(rules);
			if (!problems.isEmpty()) {
				for (Problem problem : problems) {
					System.out.println(problem.toString());
				}
				fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void rulesFromArchitecturalConstraintsShouldNotValidate() {
		try {
			InputStream testRulesStream = getClass().getResourceAsStream(
					"/architecturalConstraints.xml");
			Constraints constraints = XMLHelper.deserialize(testRulesStream,
					Constraints.class);
			MonitoringRuleFactory factory = new MonitoringRuleFactory();
			MonitoringRules rules = factory
					.makeRulesFromQoSConstraints(constraints);
			Validator validator = new Validator();
			Set<Problem> problems = validator.validateAllRules(rules);

			for (Problem problem : problems) {
				System.out.println(problem.toString());
			}
			
			assertFalse(problems.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void randomConstraintsShouldBeTranslatedButNotValidated() {
		try {
			InputStream testRulesStream = getClass().getResourceAsStream(
					"/RandomConstraints.xml");
			Constraints constraints = XMLHelper.deserialize(testRulesStream,
					Constraints.class);
			MonitoringRuleFactory factory = new MonitoringRuleFactory();
			MonitoringRules rules = factory
					.makeRulesFromQoSConstraints(constraints);
			Validator validator = new Validator();
			Set<Problem> problems = validator.validateAllRules(rules);

			for (Problem problem : problems) {
				System.out.println(problem.toString());
			}
			assertFalse(problems.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
