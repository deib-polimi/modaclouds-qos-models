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
package it.polimi.modaclouds.qos_models.monitoring_rules;

import it.polimi.modaclouds.qos_models.schema.Action;
import it.polimi.modaclouds.qos_models.schema.Actions;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Metric;
import it.polimi.modaclouds.qos_models.schema.MonitoredTarget;
import it.polimi.modaclouds.qos_models.schema.MonitoredTargets;
import it.polimi.modaclouds.qos_models.schema.MonitoringMetricAggregation;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.util.Config;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringRuleFactory {

	private Logger logger = LoggerFactory.getLogger(MonitoringRuleFactory.class
			.getName());
	private Config config;

	public MonitoringRuleFactory() throws ConfigurationException, JAXBException {
		config = Config.getInstance();
	}

	public MonitoringRule makeRuleFromConstraint(Constraint relatedConstraint,
			String ruleID) throws RuleValidationException {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(ruleID);
		monitoringRule.setRelatedQosConstraintId(relatedConstraint.getId());
		
		List<Metric> availableMetrics = config.getMonitoringMetrics().getMetrics();
		String metric = relatedConstraint.getMetric();
		// to be refactored... code should be moved to rulevalidator
		boolean found = false;
		for (Metric m: availableMetrics) {
			if (metric.equals(m.getValue())) {
				monitoringRule.setSamplingProbability(m.getDefaultSamplingProbability());
				monitoringRule.setSamplingTime(m.getDefaultSamplingTime());
				monitoringRule.setTimeStep(m.getDefaultTimeStep());
				monitoringRule.setTimeWindow(m.getDefaultTimeWindow());
				found = true;
				break;
			}
		}
		if (!found) throw new RuleValidationException("Metric " + metric + " is not listed in file " + config.getMonitoringMetricsUrl());
		monitoringRule.setMetricName(metric);

		MonitoringMetricAggregation monitoringMetricAggregation = new MonitoringMetricAggregation();
		monitoringMetricAggregation.setGroupingCategoryName(config
				.getGroupingCategories().getDefault().getName());
		monitoringMetricAggregation.setAggregateFunction(relatedConstraint
				.getMetricAggregation().getAggregateFunction());
		
		monitoringMetricAggregation.getParameters().addAll(relatedConstraint.getMetricAggregation().getParameters());
		monitoringRule.setMetricAggregation(monitoringMetricAggregation);

		monitoringRule.setLabel(relatedConstraint.getName());

		MonitoredTargets targets = new MonitoredTargets();
		MonitoredTarget target = new MonitoredTarget();
		target.setId(relatedConstraint.getTargetResourceIDRef());
		targets.getMonitoredTargets().add(target);
		monitoringRule.setMonitoredTargets(targets);

		Float maxValue = relatedConstraint.getRange().getHasMaxValue();
		Float minValue = relatedConstraint.getRange().getHasMinValue();
		String condition = "";
		if (maxValue != null) {
			condition += "METRIC > " + maxValue;
		}

		if (minValue != null) {
			condition += (maxValue != null ? " && " : "") + "METRIC < "
					+ minValue;
		}
		monitoringRule.setCondition(condition);
		
		monitoringRule.setStartEnabled(true);

		return monitoringRule;
	}

	public MonitoringRule makeRuleFromConstraint(Constraint relatedConstraint) throws RuleValidationException {
		return makeRuleFromConstraint(relatedConstraint, UUID.randomUUID()
				.toString());
	}

}
