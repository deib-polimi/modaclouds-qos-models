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

public class RuleValidatorTest {

	// private String monitoringRulesXMLString;
	private RuleValidator validator;
	private MonitoringRules monitoring_rules_SpecWeb;
	private static final String monitoring_rules_SpecWeb_url = "/monitoring_rules_SpecWeb.xml";
	private MonitoringRules monitoring_rules_example;
	public static final String monitoring_rules_example_url = "/monitoring_rules_example.xml";

//	@Before
//	public void init() {
//		try {
//			validator = new RuleValidator();
//			monitoring_rules_example = XMLHelper.deserialize(getClass().getResource(monitoring_rules_example_url), MonitoringRules.class);
//			monitoring_rules_SpecWeb = XMLHelper.deserialize(getClass().getResource(monitoring_rules_SpecWeb_url), MonitoringRules.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
//	}
//	
//	@Test
//	public void testAllRules() {
//		try {
//			validator.validateAllRules(monitoring_rules_example);
//			validator.validateAllRules(monitoring_rules_SpecWeb);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			e.printStackTrace();
//			fail();
//		}
//	}
//
//	@Test
//	public void testNoID() {
//		boolean success = false;
//		MonitoringRules monitoringRules = new MonitoringRules();
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRules.getMonitoringRules().add(monitoringRule);
//		try {
//			validator.validateAllRules(monitoringRules);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			success = true;
//		}
//		if (!success) fail();
//	}
//	
//	@Test
//	public void testWrongMetric() {
//		boolean success = false;
//		MonitoringRules monitoringRules = new MonitoringRules();
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setMetricName("wrong_metric");
//		monitoringRules.getMonitoringRules().add(monitoringRule);
//		try {
//			validator.validateAllRules(monitoringRules);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			success = true;
//		}
//		if (!success) fail();
//	}
//	
//	@Test
//	public void testCorrectMetric() {
//		MonitoringRules monitoringRules = new MonitoringRules();
//		MonitoringRule monitoringRule = new MonitoringRule();
//		monitoringRule.setId(UUID.randomUUID().toString());
//		monitoringRule.setMetricName("ResponseTime");
//		monitoringRules.getMonitoringRules().add(monitoringRule);
//		try {
//			validator.validateAllRules(monitoringRules);
//		} catch (RuleValidationException e) {
//			printMessage(e.getMessage());
//			fail();
//		}
//	}
//	
//
//	public void printMessage(String message) {
//		System.out.println(Thread.currentThread().getStackTrace()[2]
//				.getMethodName() + ": " + message);
//	}
}
