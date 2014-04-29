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

import it.polimi.modaclouds.qos_models.monitoring_ontology.Vocabulary;
import it.polimi.modaclouds.qos_models.schema.AggregateFunction;
import it.polimi.modaclouds.qos_models.schema.CollectedMetric;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Metric;
import it.polimi.modaclouds.qos_models.schema.MonitoredTarget;
import it.polimi.modaclouds.qos_models.schema.MonitoredTargets;
import it.polimi.modaclouds.qos_models.schema.MonitoringMetricAggregation;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.Parameter;
import it.polimi.modaclouds.qos_models.util.Config;

import java.util.ArrayList;
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
		CollectedMetric collectedMetric = new CollectedMetric();
		for (Metric m: availableMetrics) {
			if (metric.equals(m.getName())) {
				collectedMetric.setMetricName(m.getName());
				collectedMetric.setInherited(false);
				collectedMetric.getParameters().addAll(getDefaultParameters(m));
				found = true;
				break;
			}
		}
		if (!found) throw new RuleValidationException("Metric " + metric + " is not listed in file " + config.getMonitoringMetricsUrl());
		monitoringRule.setCollectedMetric(collectedMetric);

		List<AggregateFunction> availableAggregateFunctions = config.getMonitoringAggregateFunctions().getAggregateFunctions();
		String metricAggregation = relatedConstraint.getMetricAggregation().getAggregateFunction();
		// to be refactored... code should be moved to rulevalidator
		found = false;
		MonitoringMetricAggregation monitoringMetricAggregation = new MonitoringMetricAggregation();
		for (AggregateFunction af: availableAggregateFunctions) {
			if (metricAggregation.equals(af.getName())) {
				monitoringMetricAggregation.setAggregateFunction(af.getName());;
				monitoringMetricAggregation.setGroupingClass(Vocabulary.All);
				List<Parameter> defaultParameters = getDefaultParameters(af);
				defaultParameters = mergeParameters(relatedConstraint.getMetricAggregation().getParameters(),defaultParameters);
				monitoringMetricAggregation.getParameters().addAll(defaultParameters);
				found = true;
				break;
			}
		}
		if (!found) throw new RuleValidationException("Aggregate function " + metricAggregation + " is not listed in file " + config.getMonitoringAggregateFunctionsUrl());
		monitoringRule.setMetricAggregation(monitoringMetricAggregation);


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

	private List<Parameter> mergeParameters(List<Parameter> parameters1,
			List<Parameter> parameters2) {
		List<Parameter> mergedParameters = new ArrayList<Parameter>(parameters1);
		for (Parameter p2: parameters2) {
			boolean found = false;
			for (Parameter p1: parameters1) {
				if (p2.getName().equals(p1.getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				mergedParameters.add(p2);
			}
		}
		return mergedParameters;
	}

	private List<Parameter> getDefaultParameters(AggregateFunction af) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (AggregateFunction.RequiredParameter rPar : af.getRequiredParameters()) {
			if (rPar.getDefaultValue() != null) {
				Parameter par = new Parameter();
				par.setName(rPar.getValue());
				par.setValue(rPar.getDefaultValue());
				parameters.add(par);
			}
		}
		return parameters;
	}

	private List<Parameter> getDefaultParameters(Metric m) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (Metric.RequiredParameter rPar : m.getRequiredParameters()) {
			if (rPar.getDefaultValue() != null) {
				Parameter par = new Parameter();
				par.setName(rPar.getValue());
				par.setValue(rPar.getDefaultValue());
				parameters.add(par);
			}
		}
		return parameters;
	}

	public MonitoringRule makeRuleFromConstraint(Constraint relatedConstraint)
			throws RuleValidationException {
		return makeRuleFromConstraint(relatedConstraint, UUID.randomUUID()
				.toString());
	}

}
