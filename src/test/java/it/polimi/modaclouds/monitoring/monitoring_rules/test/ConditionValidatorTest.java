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

import static org.junit.Assert.fail;
import it.polimi.modaclouds.qos_models.monitoring_rules.RuleValidationException;
import it.polimi.modaclouds.qos_models.monitoring_rules.RuleValidator;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class ConditionValidatorTest {

	// private String monitoringRulesXMLString;
	private RuleValidator validator;
	private MonitoringRules monitoring_rules_example;
	public static final String monitoring_rules_example_url = "/monitoring_rules_example.xml";
	

	@Before
	public void init() {
		try {
			validator = new RuleValidator();
			monitoring_rules_example = XMLHelper.deserialize(getClass().getResource(monitoring_rules_example_url), MonitoringRules.class);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	

//	@Test
//	public void testCondition1() {
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setCondition("maxOccurrence(mr_1,3)");
//
//		try {
//			validator.prevalidateCondition(monitoringRule,
//					monitoring_rules_example);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testCondition2() {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(UUID.randomUUID().toString());
		monitoringRule.setCondition("( METRIC >= 0.6");

		Boolean success = false;
		try {
			validator.prevalidateCondition(monitoringRule,
					monitoring_rules_example);
		} catch (RuleValidationException e) {
			printMessage(e.getMessage());
			success = true;
		}
		if (!success)
			fail();
	}

//	@Test
//	public void testCondition3() {
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setCondition("(METRIC >= 0.6) || minOccurrence(mr_3,3)");
//
//		try {
//			validator.prevalidateCondition(monitoringRule,
//					monitoring_rules_example);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			fail();
//		}
//	}

	@Test
	public void testNoParentCondition() {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(UUID.randomUUID().toString());
		monitoringRule.setCondition("(METRIC >= 0.6) || parentCondition");

		Boolean success = false;
		try {
			validator.prevalidateCondition(monitoringRule,
					monitoring_rules_example);
		} catch (RuleValidationException e) {
			printMessage(e.getMessage());
			success = true;
		}
		if (!success)
			fail();
	}

	@Test
	public void testHasParentCondition() {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(UUID.randomUUID().toString());
		monitoringRule.setParentMonitoringRule(new MonitoringRule());
		monitoringRule.setCondition("(METRIC >= 0.6) || parentCondition");

		try {
			validator.prevalidateCondition(monitoringRule,
					monitoring_rules_example);
		} catch (RuleValidationException e) {
			printMessage(e.getMessage());
			fail();
		}
	}

	@Test
	public void testMetricCondition() {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(UUID.randomUUID().toString());
		monitoringRule.setCondition("nMETRIC >= 0.6");

		Boolean success = false;
		try {
			validator.prevalidateCondition(monitoringRule,
					monitoring_rules_example);
		} catch (RuleValidationException e) {
			printMessage(e.getMessage());
			success = true;
		}
		if (!success)
			fail();
	}

//	@Test
//	public void testWrongMinOccurrenceCondition() {
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setCondition("minOccurrence(ResponseTimeViolation,3)");
//
//		Boolean success = false;
//		try {
//			validator.prevalidateCondition(monitoringRule,
//					monitoring_rules_example);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			success = true;
//		}
//		if (!success)
//			fail();
//	}

//	@Test
//	public void testMinOccurrenceOkCondition() {
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setParentMonitoringRule(new MonitoringRule());
//		monitoringRule.setCondition("(METRIC >= 0.6) || minOccurrence(mr_1,3)");
//
//		try {
//			validator.prevalidateCondition(monitoringRule,
//					monitoring_rules_example);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			fail();
//		}
//	}

	public void testWrongNumberCondition() {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(UUID.randomUUID().toString());
		monitoringRule.setCondition("METRIC >=0 0.6");

		Boolean success = false;
		try {
			validator.prevalidateCondition(monitoringRule,
					monitoring_rules_example);
		} catch (RuleValidationException e) {
			printMessage(e.getMessage());
			success = true;
		}
		if (!success)
			fail();
	}

	public void printMessage(String message) {
		System.out.println(Thread.currentThread().getStackTrace()[2]
				.getMethodName() + ": " + message);
	}
}
