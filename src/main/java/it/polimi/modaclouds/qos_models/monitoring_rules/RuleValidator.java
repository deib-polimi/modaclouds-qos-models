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
import it.polimi.modaclouds.qos_models.schema.AggregateFunction;
import it.polimi.modaclouds.qos_models.schema.AvailableAction;
import it.polimi.modaclouds.qos_models.schema.GroupingCategory;
import it.polimi.modaclouds.qos_models.schema.Metric;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.util.Config;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang.NullArgumentException;

public class RuleValidator {

	private Config config;

	public RuleValidator() throws ConfigurationException, JAXBException {
		config = Config.getInstance();
	}

	/**
	 * Validate the entire {@code targetRule} against the
	 * {@code existingMonitoringRules}. The {@code targetRule} must not be part
	 * of the {@code existingMonitoringRules}. If the rule is
	 * already part of {@code existingMonitoringRules} use
	 * {@link #postvalidateRule(MonitoringRule, MonitoringRules)}
	 * instead.
	 * 
	 * @param targetRule
	 *            The rule to be validated
	 * @param existingMonitoringRules
	 *            The already defined rules
	 * @throws RuleValidationException
	 *             If the rule is not valid. Information about the error can be
	 *             retrieved from the exception message
	 */
	public void prevalidateRule(MonitoringRule targetRule,
			Collection<MonitoringRule> existingMonitoringRules)
			throws RuleValidationException {
		if (targetRule == null)
			throw new NullArgumentException("targetRule");
		if (existingMonitoringRules == null)
			throw new NullArgumentException("existingMonitoringRules");
		if (XMLHelper.containsId(existingMonitoringRules,
				targetRule.getId()))
			throw new RuleValidationException("a monitoring rule with id "
					+ targetRule.getId()
					+ " already exists among existingMonitoringRules");

		if (targetRule.getId() == null)
			throw new RuleValidationException("rule id is missing");

		if (targetRule.getMetricName() != null)
			validateMetricName(targetRule);

		if (targetRule.getMetricAggregation() != null) {
			if (targetRule.getMetricAggregation().getGroupingCategoryName() != null) {
				validateGroupingCategory(targetRule);
			}
			if (targetRule.getMetricAggregation().getAggregateFunction() != null) {
				validateAggregateFunction(targetRule);
			}
		}

		if (targetRule.getActions() != null)
			validateActions(targetRule);

		if (targetRule.getCondition() != null) {
			prevalidateCondition(targetRule, existingMonitoringRules);
		}
	}
	
	public void prevalidateRule(MonitoringRule targetRule,
			MonitoringRules existingMonitoringRules)
			throws RuleValidationException {
		if (targetRule == null)
			throw new NullArgumentException("targetRule");
		if (existingMonitoringRules == null)
			throw new NullArgumentException("existingMonitoringRules");
		prevalidateRule(targetRule, existingMonitoringRules.getMonitoringRules());
	}

	/**
	 * Check if the aggregate function in the {@code rule} is among the ones
	 * listed in the monitoring_aggregate_functions.xml file located at
	 * {@link Config#getMonitoringAggregateFunctionsUrl()}
	 * 
	 * @param rule
	 * @throws RuleValidationException
	 */
	public void validateAggregateFunction(MonitoringRule rule)
			throws RuleValidationException {
		if (rule == null)
			throw new NullArgumentException("rule");
		String aggregateFunction = rule.getMetricAggregation()
				.getAggregateFunction();
		for (AggregateFunction af : config.getMonitoringAggregateFunctions()
				.getAggregateFunctions()) {
			if (aggregateFunction.equals(af.getName()))
				return;
		}
		throw new RuleValidationException("Aggregate function "
				+ aggregateFunction + " is not allowed according to list in "
				+ config.getMonitoringAggregateFunctionsUrl());
	}

	/**
	 * Check if the metric name in the {@code rule} is among the ones listed in
	 * the monitoring_metrics.xml file located at
	 * {@link Config#getMonitoringMetricsUrl()}
	 * 
	 * @param rule
	 * @throws RuleValidationException
	 */
	public void validateMetricName(MonitoringRule rule)
			throws RuleValidationException {
		if (rule == null)
			throw new NullArgumentException("rule");
		String metric = rule.getMetricName();
		for (Metric m : config.getMonitoringMetrics().getMetrics()) {
			if (metric.equals(m.getValue()))
				return;
		}
		throw new RuleValidationException("Metric " + metric
				+ " is not allowed according to list in "
				+ config.getMonitoringMetricsUrl());
	}

	/**
	 * Check if the actions in the {@code rule} are among the ones listed in the
	 * monitoring_actions.xml file located at
	 * {@link Config#getMonitoringActionsUrl()}
	 * 
	 * @param rule
	 * @throws RuleValidationException
	 */
	public void validateActions(MonitoringRule rule)
			throws RuleValidationException {
		if (rule == null)
			throw new NullArgumentException("rule");
		List<Action> actions = rule.getActions().getActions();
		for (Action a : actions) {
			boolean found = false;
			for (AvailableAction availableAction : config
					.getMonitoringActions().getAvailableActions()) {
				if (a.getName().equals(availableAction.getName()))
					found = true;
			}
			if (!found)
				throw new RuleValidationException("Action " + a.getName()
						+ " is not allowed according to list in "
						+ config.getMonitoringActionsUrl());
		}

	}

	/**
	 * Check if the grouping category in the {@code rule} is among the ones
	 * listed in the monitoring_grouping_categories.xml file located at
	 * {@link Config#getMonitoringActionsUrl()}
	 * 
	 * @param rule
	 * @throws RuleValidationException
	 */
	public void validateGroupingCategory(MonitoringRule rule)
			throws RuleValidationException {
		if (rule == null)
			throw new NullArgumentException("rule");
		String groupingCategory = rule.getMetricAggregation()
				.getGroupingCategoryName();
		for (GroupingCategory gc : config.getGroupingCategories()
				.getGroupingCategories()) {
			if (gc.getName().equals(groupingCategory))
				return;
		}
		throw new RuleValidationException("Grouping category "
				+ groupingCategory + " is not allowed according to list in "
				+ config.getGroupingCategoriesUrl());
	}

	/**
	 * Validate the condition in the {@code targetRule}. {@code targetRule} must
	 * not be part of the {@code existingMonitoringRules}. If the rule is
	 * already part of {@code existingMonitoringRules} use
	 * {@link #postvalidateCondition(MonitoringRule, MonitoringRules)}
	 * instead.
	 * 
	 * @param targetRule
	 * @param existingMonitoringRules
	 * @throws RuleValidationException
	 */
	public void prevalidateCondition(MonitoringRule targetRule,
			Collection<MonitoringRule> existingMonitoringRules)
			throws RuleValidationException {
		if (targetRule == null)
			throw new NullArgumentException("targetRule");
		if (existingMonitoringRules == null)
			throw new NullArgumentException("existingMonitoringRules");
		ParseTree tree = syntacticValidation(targetRule.getCondition());
		assert tree != null;
		semanticValidation(tree, targetRule, existingMonitoringRules);
	}
	
	public void prevalidateCondition(MonitoringRule targetRule,
			MonitoringRules existingMonitoringRules)
			throws RuleValidationException {
		if (targetRule == null)
			throw new NullArgumentException("targetRule");
		if (existingMonitoringRules == null)
			throw new NullArgumentException("existingMonitoringRules");
		prevalidateCondition(targetRule, existingMonitoringRules.getMonitoringRules());
	}

	/**
	 * Validate the condition in the monitoring rule identified by {@code targetRuleId}. The monitoring rule must
	 * be already part of the {@code monitoringRules}.
	 * 
	 * @param targetRuleId
	 * @param monitoringRules
	 * @throws RuleValidationException
	 */
	public void postvalidateCondition(String targetRuleId,
			MonitoringRules monitoringRules) throws RuleValidationException {
		if (targetRuleId == null)
			throw new NullArgumentException("targetRuleId");
		if (monitoringRules == null)
			throw new NullArgumentException("monitoringRules");
		MonitoringRule targetRule = XMLHelper.getElementByID(
				monitoringRules.getMonitoringRules(), targetRuleId);
		if (targetRule == null)
			throw new RuleValidationException("a monitoring rule with id "
					+ targetRuleId
					+ " does not exists among existingMonitoringRules");
		postvalidateCondition(targetRule, monitoringRules);
	}

	/**
	 * Validate the condition in the {@code targetRule}. {@code targetRule} must
	 * be already part of the {@code monitoringRules}.
	 * 
	 * @param targetRule
	 * @param monitoringRules
	 * @throws RuleValidationException
	 */
	public void postvalidateCondition(MonitoringRule targetRule,
			MonitoringRules monitoringRules) throws RuleValidationException {
		if (targetRule == null)
			throw new NullArgumentException("targetRuleId");
		if (monitoringRules == null)
			throw new NullArgumentException("monitoringRules");
		targetRule = getActualRuleInstance(targetRule, monitoringRules);
		MonitoringRules others = new MonitoringRules();
		others.getMonitoringRules()
				.addAll(monitoringRules.getMonitoringRules());
		others.getMonitoringRules().remove(targetRule);
		prevalidateRule(targetRule, others);
	}

	/**
	 * Validate the entire {@code targetRule} against the
	 * {@code existingMonitoringRules}. The {@code targetRule} must be already part
	 * of the {@code monitoringRules}.
	 * 
	 * @param targetRule
	 * @param monitoringRules
	 * @throws RuleValidationException
	 */
	public void postvalidateRule(MonitoringRule targetRule,
			MonitoringRules monitoringRules) throws RuleValidationException {
		if (targetRule == null || monitoringRules == null)
			throw new NullPointerException();
		targetRule = getActualRuleInstance(targetRule, monitoringRules);
		MonitoringRules others = new MonitoringRules();
		others.getMonitoringRules()
				.addAll(monitoringRules.getMonitoringRules());
		others.getMonitoringRules().remove(targetRule);
		prevalidateRule(targetRule, others);
	}

	/**
	 * Validate the entire monitoring rule identified by {@code targetRuleId} against the
	 * {@code monitoringRules}. The monitoring rule must be already part
	 * of the {@code monitoringRules}.
	 * 
	 * @param targetRuleId
	 * @param monitoringRules
	 * @throws RuleValidationException
	 */
	public void postvalidateRule(String targetRuleId,
			MonitoringRules monitoringRules) throws RuleValidationException {
		if (targetRuleId == null || monitoringRules == null)
			throw new NullPointerException();
		MonitoringRule targetRule = XMLHelper.getElementByID(
				monitoringRules.getMonitoringRules(), targetRuleId);
		if (targetRule == null)
			throw new RuleValidationException("a monitoring rule with id "
					+ targetRuleId
					+ " does not exists among existingMonitoringRules");
		postvalidateRule(targetRule, monitoringRules);
	}

	/**
	 * Validate entirely all rules through subsequent calls of {@link #postvalidateRule(MonitoringRule, MonitoringRules)}.
	 * 
	 * @param monitoringRules
	 * @throws RuleValidationException
	 */
	public void validateAllRules(MonitoringRules monitoringRules)
			throws RuleValidationException {
		if (monitoringRules == null)
			throw new NullPointerException();
		for (MonitoringRule rule : monitoringRules.getMonitoringRules()) {
			postvalidateRule(rule, monitoringRules);
		}
	}

	private MonitoringRule getActualRuleInstance(MonitoringRule targetRule,
			MonitoringRules monitoringRules) throws RuleValidationException {
		MonitoringRule actualRuleInstance = targetRule;
		if (!monitoringRules.getMonitoringRules().contains(targetRule)) {
			actualRuleInstance = XMLHelper.getElementByID(
					monitoringRules.getMonitoringRules(), targetRule.getId());
			if (actualRuleInstance == null)
				throw new RuleValidationException(
						"target rule is not contained in monitoring rules");
			if (!targetRule.equals(actualRuleInstance))
				throw new RuleValidationException(
						"monitoringRule with same id exists among monitoringRules, but is different in some part");
		}
		return actualRuleInstance;
	}

	private ParseTree syntacticValidation(String condition)
			throws RuleValidationException {

		ANTLRInputStream input = new ANTLRInputStream(condition);
		ConditionLexer lexer = new ConditionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ConditionParser parser = new ConditionParser(tokens);
		parser.setErrorHandler(new ErrorStrategy());
		parser.removeErrorListeners();
		lexer.removeErrorListeners();

		String message;

		try {
			return parser.expression();
		} catch (Exception e) {

			if (e.getCause().toString().contains("NoViableAltException")) {
				Token token = ((NoViableAltException) (e.getCause()))
						.getOffendingToken();
				message = "Recognition error: '"
						+ condition.substring(0, token.getCharPositionInLine())
						+ " »"
						+ condition.substring(token.getCharPositionInLine())
						+ "'";
			} else if (e instanceof InputMismatchException) {
				message = "Input Mismatch Exception";
			} else if (e instanceof FailedPredicateException) {
				message = "Failed Predicate Exception";
			} else {
				message = "Unknown recognition error! Exception: "
						+ e.getClass().getName();
			}
			throw new RuleValidationException(message);
		}
	}

	private void semanticValidation(ParseTree tree, MonitoringRule targetRule,
			Collection<MonitoringRule> existingMonitoringRules)
			throws RuleValidationException {
		int childrenCount = tree.getChildCount();

		if (childrenCount != 0) {
			for (int i = 0; i < childrenCount; i++) {
				semanticValidation(tree.getChild(i), targetRule,
						existingMonitoringRules);
			}
		} else {
			String occurenceMR_ID;
			switch (tree.getText()) {
			case "parentCondition":
				if (targetRule.getParentMonitoringRule() == null)
					throw new RuleValidationException("Rule "
							+ targetRule.getId() + " has no parent rule");
				break;
			case "maxOccurrence":
			case "minOccurrence":
				occurenceMR_ID = tree.getParent().getChild(2).getText();
				if (targetRule.getId().equals(occurenceMR_ID))
					throw new RuleValidationException(
							"A rule cannot check maxOccurrence on itself");
				if (!XMLHelper.containsId(
						existingMonitoringRules,
						occurenceMR_ID))
					throw new RuleValidationException("Rule " + occurenceMR_ID
							+ " does not exist");
				break;
			}
		}
	}

	

}
