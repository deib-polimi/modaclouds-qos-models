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
package it.polimi.modaclouds.monitoring.monitoring_rules.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.modaclouds.qos_models.monitoring_rules.MonitoringRuleFactory;
import it.polimi.modaclouds.qos_models.monitoring_rules.RuleValidator;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import org.junit.Before;
import org.junit.Test;

public class Constraint2RuleTest {

//	private static final String qos_constraints_SpecWeb_url = "/qos_constraints_SpecWeb.xml";
//	private static final String monitoring_rules_SpecWeb_url = "/monitoring_rules_SpecWeb.xml";
//
//	private MonitoringRuleFactory mrfactory;
//	private MonitoringRules monitoring_rules_SpecWeb;
//	private Constraints qos_constraints_SpecWeb;
//	private RuleValidator validator;


//	@Before
//	public void loadXMLString() {
//		try {
//
//			validator = new RuleValidator();
//			mrfactory = new MonitoringRuleFactory();
//			qos_constraints_SpecWeb = XMLHelper.deserialize(getClass().getResource(qos_constraints_SpecWeb_url), Constraints.class);
//			monitoring_rules_SpecWeb = XMLHelper.deserialize(getClass().getResource(monitoring_rules_SpecWeb_url), MonitoringRules.class);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
//	}

//	@Test
//	public void test() {
//		for (MonitoringRule monitoringRule : monitoring_rules_SpecWeb
//				.getMonitoringRules()) {
//			try {
//				String relatedConstraintId = monitoringRule
//						.getRelatedQosConstraintId();
//				Constraint relatedConstraint = XMLHelper.getElementByID(
//						qos_constraints_SpecWeb.getConstraints(),
//						relatedConstraintId);
//				MonitoringRule generatedRule = mrfactory
//						.makeRuleFromConstraint(relatedConstraint, monitoringRule.getId());
//
//				validator.prevalidateRule(generatedRule, new MonitoringRules());
////				System.out.println(monitoringRule.toString());
////				System.out.println(generatedRule.toString());
//				assertTrue(monitoringRule.equals(generatedRule));
//			} catch (Exception e) {
//				e.printStackTrace();
//				printMessage(e.getMessage());
//				fail();
//			}
//		}
//
//	}

//	public void printMessage(String message) {
//		System.out.println(Thread.currentThread().getStackTrace()[2]
//				.getMethodName() + ": " + message);
//	}

}
