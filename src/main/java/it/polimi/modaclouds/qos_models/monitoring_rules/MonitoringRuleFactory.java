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
import it.polimi.modaclouds.qos_models.schema.AggregateFunction;
import it.polimi.modaclouds.qos_models.schema.CollectedMetric;
import it.polimi.modaclouds.qos_models.schema.Condition;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.schema.Metric;
import it.polimi.modaclouds.qos_models.schema.MonitoredTarget;
import it.polimi.modaclouds.qos_models.schema.MonitoredTargets;
import it.polimi.modaclouds.qos_models.schema.MonitoringMetricAggregation;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.schema.Parameter;
import it.polimi.modaclouds.qos_models.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringRuleFactory {

	private Logger logger = LoggerFactory.getLogger(MonitoringRuleFactory.class
			.getName());
	private Config config;

	public MonitoringRuleFactory() throws ConfigurationException {
		config = Config.getInstance();
	}

	/**
	 * 
	 * @param qosConstraints
	 * @return all monitoring rules that can be built from the constraints
	 */
	public MonitoringRules makeRulesFromQoSConstraints(
			Constraints qosConstraints) {
		MonitoringRules rules = new MonitoringRules();
		for (Constraint c : qosConstraints.getConstraints()) {
			MonitoringRule rule = makeRuleFromConstraint(c);
			if (rule != null)
				rules.getMonitoringRules().add(rule);
		}
		return rules;
	}

	/**
	 * 
	 * @param qosConstraint
	 * @param ruleID
	 * @return the monitoring rule built from the constraint, {@code null} if no
	 *         monitoring rule can be constructed from the constraint
	 */
	public MonitoringRule makeRuleFromConstraint(Constraint qosConstraint,
			String ruleID) {
		MonitoringRule monitoringRule = new MonitoringRule();
		monitoringRule.setId(ruleID);
		monitoringRule.setRelatedQosConstraintId(qosConstraint.getId());
		monitoringRule.setTimeStep("60");
		monitoringRule.setTimeWindow("60");
		CollectedMetric collectedMetric = makeCollectedMetric(qosConstraint);
		if (collectedMetric == null) {
			logger.warn(
					"Constraint {} could not be translated to a monitoring rule: "
							+ "{} is not a valid monitoring metric",
					qosConstraint.getId(), qosConstraint.getMetric());
			return null;
		}
		monitoringRule.setCollectedMetric(collectedMetric);

		MonitoringMetricAggregation monitoringMetricAggregation = makeMetricAggregation(qosConstraint);
		if (monitoringMetricAggregation == null) {
			logger.warn(
					"Constraint {} could not be translated to a monitoring rule: "
							+ "{} is not a valid aggregate function",
					qosConstraint.getId(), qosConstraint.getMetricAggregation()
							.getAggregateFunction());
			return null;
		}
		monitoringRule.setMetricAggregation(monitoringMetricAggregation);

		MonitoredTargets targets = new MonitoredTargets();
		MonitoredTarget target = new MonitoredTarget();
		target.setId(qosConstraint.getTargetResourceIDRef());
		target.setClazz(qosConstraint.getTargetClass());
		targets.getMonitoredTargets().add(target);
		monitoringRule.setMonitoredTargets(targets);

		Float maxValue = qosConstraint.getRange().getHasMaxValue();
		Float minValue = qosConstraint.getRange().getHasMinValue();
		String conditionValue = "";
		if (maxValue != null) {
			conditionValue += "METRIC > " + maxValue;
		}

		if (minValue != null) {
			conditionValue += (maxValue != null ? " && " : "") + "METRIC < "
					+ minValue;
		}
		Condition condition = new Condition();
		condition.setValue(conditionValue);
		condition.setInherited(false);
		monitoringRule.setCondition(condition);

		monitoringRule.setStartEnabled(true);

		Action action = new Action();
		action.setName(MonitoringActions.OUTPUT_METRIC);
		Parameter p = new Parameter();
		p.setName("name");
		p.setValue("qosConstraint_" + qosConstraint.getId() + "_Violated");
		action.getParameters().add(p);
		Actions actions = new Actions();
		actions.getActions().add(action);

		monitoringRule.setActions(actions);

		return monitoringRule;
	}

	private MonitoringMetricAggregation makeMetricAggregation(
			Constraint qosConstraint) {
		List<AggregateFunction> availableAggregateFunctions = config
				.getMonitoringAggregateFunctions().getAggregateFunctions();
		MonitoringMetricAggregation monitoringMetricAggregation = null;
		for (AggregateFunction af : availableAggregateFunctions) {
			if (qosConstraint.getMetricAggregation().getAggregateFunction()
					.equals(af.getName())) {
				monitoringMetricAggregation = new MonitoringMetricAggregation();
				monitoringMetricAggregation.setAggregateFunction(af.getName());
				List<Parameter> defaultParameters = getDefaultParameters(af);
				defaultParameters = mergeParameters(qosConstraint
						.getMetricAggregation().getParameters(),
						defaultParameters);
				monitoringMetricAggregation.getParameters().addAll(
						defaultParameters);
				break;
			}
		}
		return monitoringMetricAggregation;
	}

	private CollectedMetric makeCollectedMetric(Constraint qosConstraint) {
		List<Metric> availableMetrics = config.getMonitoringMetrics()
				.getMetrics();
		CollectedMetric collectedMetric = null;
		for (Metric m : availableMetrics) {
			if (qosConstraint.getMetric().equals(m.getName())) {
				collectedMetric = new CollectedMetric();
				collectedMetric.setMetricName(m.getName());
				collectedMetric.setInherited(false);
				collectedMetric.getParameters().addAll(getDefaultParameters(m));
				break;
			}
		}
		return collectedMetric;
	}

	private List<Parameter> mergeParameters(List<Parameter> parameters1,
			List<Parameter> parameters2) {
		List<Parameter> mergedParameters = new ArrayList<Parameter>(parameters1);
		for (Parameter p2 : parameters2) {
			boolean found = false;
			for (Parameter p1 : parameters1) {
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
		for (AggregateFunction.RequiredParameter rPar : af
				.getRequiredParameters()) {
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

	/**
	 * 
	 * @param relatedConstraint
	 * @return the monitoring rule built from the constraint, {@code null} if no
	 *         monitoring rule can be constructed from the constraint
	 */
	public MonitoringRule makeRuleFromConstraint(Constraint relatedConstraint) {
		return makeRuleFromConstraint(relatedConstraint, UUID.randomUUID()
				.toString());
	}

}
