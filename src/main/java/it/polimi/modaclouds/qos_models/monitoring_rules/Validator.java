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
import it.polimi.modaclouds.qos_models.schema.CollectedMetric;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.schema.GroupingCategory;
import it.polimi.modaclouds.qos_models.schema.Metric;
import it.polimi.modaclouds.qos_models.schema.Metric.RequiredParameter;
import it.polimi.modaclouds.qos_models.schema.MonitoredTarget;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.schema.Parameter;
import it.polimi.modaclouds.qos_models.util.Config;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang.NullArgumentException;

public class Validator {

	private Config config;

	public Validator() throws ConfigurationException {
		config = Config.getInstance();
	}

	/**
	 * Validate all rules
	 * 
	 * @param monitoringRules
	 * @return the set of problems found during validation, or an empty set if
	 *         the validation was successful
	 */
	public Set<Problem> validateAllRules(MonitoringRules monitoringRules) {
		if (monitoringRules == null)
			throw new NullArgumentException("monitoringRules");
		Set<Problem> problems = new HashSet<Problem>();
		List<MonitoringRule> rules = monitoringRules.getMonitoringRules();
		List<MonitoringRule> otherRules = new ArrayList<MonitoringRule>(rules);
		MonitoringRule previousRule = null;
		for (MonitoringRule rule : rules) {
			if (previousRule != null)
				otherRules.add(previousRule);
			otherRules.remove(rule);

//			problems.addAll(validateParentRequired(rule));
			problems.addAll(validateMissingFields(rule));
			problems.addAll(validateMonitoredTargets(rule));
			problems.addAll(validateCollectedMetric(rule, otherRules));
			problems.addAll(validateMetricAggregation(rule));
			problems.addAll(validateActions(rule));
			problems.addAll(validateCondition(rule, otherRules));

			previousRule = rule;
		}
		return problems;
	}

	public Set<Problem> validateAllConstraints(Constraints qosConstraints) {
		if (qosConstraints == null)
			throw new NullArgumentException("qosConstraints");
		Set<Problem> problems = new HashSet<Problem>();
		List<Constraint> constraints = qosConstraints.getConstraints();
		for (Constraint c : constraints) {
			problems.addAll(validateTargetClass(c));
			problems.addAll(validateMetric(c));
			problems.addAll(validateMetricAggregation(c));
		}
		return problems;
	}

	private Set<Problem> validateMetricAggregation(Constraint c) {
		Set<Problem> problems = new HashSet<Problem>();
		boolean found = false;
		for (AggregateFunction af : config.getQosAggregateFunctions()
				.getAggregateFunctions()) {
			if (softEquals(af.getName(), c.getMetricAggregation()
					.getAggregateFunction())) {
				found = true;
				break;
			}
		}
		if (!found) {
			problems.add(new Problem(c.getId(),
					EnumErrorType.INVALID_AGGREGATE_FUNCTION,
					"metricAggregation"));
		}
		return problems;
	}

	private Set<Problem> validateMetric(Constraint c) {
		Set<Problem> problems = new HashSet<Problem>();
		boolean found = false;
		for (Metric metric : config.getQosMetrics().getMetrics()) {
			if (softEquals(metric.getName(), c.getMetric())) {
				found = true;
				break;
			}
		}
		if (!found) {
			problems.add(new Problem(c.getId(), EnumErrorType.INVALID_METRIC,
					"metric"));
		}
		return problems;
	}

	private Set<Problem> validateTargetClass(Constraint c) {
		Set<Problem> problems = new HashSet<Problem>();
		boolean found = false;
		for (GroupingCategory gc : config.getGroupingCategories()
				.getGroupingCategories()) {
			if (softEquals(gc.getName(), c.getTargetClass())) {
				found = true;
				break;
			}
		}
		if (!found) {
			problems.add(new Problem(c.getId(), EnumErrorType.INVALID_CLASS,
					"targetClass"));
		}
		return problems;
	}

	private Set<Problem> validateActions(MonitoringRule rule) {
		Set<Problem> problems = new HashSet<Problem>();
		if (rule.getActions() == null)
			return problems;
		boolean found;
		List<String> requiredParameters = null;
		for (Action ruleAction : rule.getActions().getActions()) {
			found = false;
			for (AvailableAction availableAction : config
					.getMonitoringActions().getAvailableActions()) {
				if (softEquals(ruleAction.getName(), availableAction.getName())) {
					requiredParameters = availableAction
							.getRequiredParameters();
					found = true;
					break;
				}
			}
			if (!found) {
				problems.add(new Problem(rule.getId(),
						EnumErrorType.INVALID_ACTION, "actions"));
			} else if (requiredParameters != null) {
				for (String reqP : requiredParameters) {
					found = false;
					for (Parameter ruleP : ruleAction.getParameters()) {
						if (softEquals(reqP, ruleP.getName())) {
							found = true;
							break;
						}
					}
					if (!found) {
						problems.add(new Problem(rule.getId(),
								EnumErrorType.MISSING_REQUIRED_PARAMETER,
								"actions"));
					}
				}
			}
		}
		return problems;
	}

	private Set<Problem> validateMissingFields(MonitoringRule rule) {
		Set<Problem> problems = new HashSet<Problem>();
//		if (rule.getParentMonitoringRuleId() != null)
//			return problems;
		if (rule.getTimeStep() == null)
			problems.add(new Problem(rule.getId(), EnumErrorType.MISSING_FIELD,
					"timeStep"));
		if (rule.getTimeWindow() == null)
			problems.add(new Problem(rule.getId(), EnumErrorType.MISSING_FIELD,
					"timeWindow"));
		return problems;
	}

//	private Set<Problem> validateParentRequired(MonitoringRule rule) {
//		Set<Problem> problems = new HashSet<Problem>();
//		if (rule.getParentMonitoringRuleId() != null)
//			return problems;
//		if (rule.getCollectedMetric().isInherited())
//			problems.add(new Problem(rule.getId(),
//					EnumErrorType.MISSING_REQUIRED_PARENT, "collectedMetric"));
//		if (rule.getCondition() != null && rule.getCondition().isInherited())
//			problems.add(new Problem(rule.getId(),
//					EnumErrorType.MISSING_REQUIRED_PARENT, "condition"));
//		if (rule.getMetricAggregation() != null
//				&& rule.getMetricAggregation().isInherited())
//			problems.add(new Problem(rule.getId(),
//					EnumErrorType.MISSING_REQUIRED_PARENT, "metricAggregation"));
//		if (rule.getActions().isInherited())
//			problems.add(new Problem(rule.getId(),
//					EnumErrorType.MISSING_REQUIRED_PARENT, "actions"));
//		if (rule.getMonitoredTargets().isInherited())
//			problems.add(new Problem(rule.getId(),
//					EnumErrorType.MISSING_REQUIRED_PARENT, "monitoredTargets"));
//		return problems;
//	}

	private Set<Problem> validateMetricAggregation(MonitoringRule rule) {
		Set<Problem> problems = new HashSet<Problem>();
		if (rule.getMetricAggregation() == null)
			return problems;
		boolean found = false;
		if (rule.getMetricAggregation().getGroupingClass() != null) {
			for (GroupingCategory clazz : config.getGroupingCategories()
					.getGroupingCategories()) {
				if (softEquals(rule.getMetricAggregation().getGroupingClass(),
						clazz.getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				Problem problem = new Problem();
				problem.setElementId(rule.getId());
				problem.setError(EnumErrorType.INVALID_CLASS);
				problem.setTagName("metricAggregation");
				problems.add(problem);
			}
		}
		List<it.polimi.modaclouds.qos_models.schema.AggregateFunction.RequiredParameter> requiredParameters = null;
		if (rule.getMetricAggregation().getAggregateFunction() != null) {
			for (AggregateFunction function : config
					.getMonitoringAggregateFunctions().getAggregateFunctions()) {
				if (softEquals(rule.getMetricAggregation()
						.getAggregateFunction(), function.getName())) {
					requiredParameters = function.getRequiredParameters();
					found = true;
					break;
				}
			}
			if (!found) {
				Problem problem = new Problem();
				problem.setElementId(rule.getId());
				problem.setError(EnumErrorType.INVALID_AGGREGATE_FUNCTION);
				problem.setTagName("metricAggregation");
				problems.add(problem);
			} else {
				if (requiredParameters != null) {
					for (it.polimi.modaclouds.qos_models.schema.AggregateFunction.RequiredParameter reqP : requiredParameters) {
						found = false;
						for (Parameter ruleP : rule.getMetricAggregation()
								.getParameters()) {
							if (softEquals(reqP.getValue(),ruleP.getName())) {
								found = true;
								break;
							}
						}
						if (!found) {
							Problem problem = new Problem();
							problem.setElementId(rule.getId());
							problem.setError(EnumErrorType.MISSING_REQUIRED_PARAMETER);
							problem.setTagName("metricAggregation");
							problems.add(problem);
						}
					}
				}
			}
		}
		return problems;
	}

	private Set<Problem> validateMonitoredTargets(MonitoringRule rule) {
		Set<Problem> problems = new HashSet<Problem>();
		if (rule.getMonitoredTargets() == null)
			return problems;
		boolean found;
		for (MonitoredTarget target : rule.getMonitoredTargets()
				.getMonitoredTargets()) {
			if (target.getClazz() == null) {
				problems.add(new Problem(rule.getId(),
						EnumErrorType.MISSING_FIELD, "monitoredTargets",
						"target class is required"));
				break;
			}
//			if (target.getId() == null) {
//				problems.add(new Problem(rule.getId(),
//						EnumErrorType.MISSING_FIELD, "monitoredTargets",
//						"target id is required"));
//				break;
//			}
			found = false;
			for (GroupingCategory clazz : config.getGroupingCategories()
					.getGroupingCategories()) {
				if (softEquals(target.getClazz(),clazz.getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				Problem problem = new Problem();
				problem.setElementId(rule.getId());
				problem.setError(EnumErrorType.INVALID_CLASS);
				problem.setTagName("monitoredTargets");
				problems.add(problem);
			}
		}
		return problems;
	}

	private Set<Problem> validateCollectedMetric(MonitoringRule rule,
			List<MonitoringRule> otherRules) {
		Set<Problem> problems = new HashSet<Problem>();
		if (rule.getCollectedMetric() == null)
			return problems;
		boolean found = false;
		List<RequiredParameter> requiredParameters = null;
		for (Metric metric : config.getMonitoringMetrics().getMetrics()) {
			if (softEquals(metric.getName(), rule.getCollectedMetric().getMetricName())) {
				requiredParameters = metric.getRequiredParameters();
				found = true;
				break;
			}
		}
		if (!found) {
			for (MonitoringRule otherRule : otherRules) {
				for (Action action : otherRule.getActions().getActions()) {
					if (softEquals(action.getName()
							,MonitoringActions.OUTPUT_METRIC)) {
						if (softEquals(rule.getCollectedMetric()
								.getMetricName(),action.getParameters().get(0)
										.getValue())) {
							found = true;
							break;
						}
					}
				}
				if (found)
					break;
			}
			if (!found) {
				problems.add(new Problem(rule.getId(),
						EnumErrorType.INVALID_METRIC, "collectedMetric"));
			}
		} else {
			if (requiredParameters != null) {
				for (RequiredParameter reqP : requiredParameters) {
					found = false;
					for (Parameter ruleP : rule.getCollectedMetric()
							.getParameters()) {
						if (softEquals(reqP.getValue(),ruleP.getName())) {
							found = true;
							break;
						}
					}
					if (!found) {
						Problem problem = new Problem();
						problem.setElementId(rule.getId());
						problem.setError(EnumErrorType.MISSING_REQUIRED_PARAMETER);
						problem.setTagName("collectedMetric");
						problems.add(problem);
					}
				}
			}
		}
		return problems;
	}

	private Set<Problem> validateCondition(MonitoringRule rule,
			List<MonitoringRule> otherRules) {
		Set<Problem> problems = new HashSet<Problem>();
		if (rule.getCondition() == null)
			return problems;
		String condition = rule.getCondition().getValue();
		if (condition != null) {
			ANTLRInputStream input = new ANTLRInputStream(condition);
			ConditionLexer lexer = new ConditionLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ConditionParser parser = new ConditionParser(tokens);
			parser.setErrorHandler(new ErrorStrategy());
			parser.removeErrorListeners();
			lexer.removeErrorListeners();

			ParseTree tree = null;
			try {
				tree = parser.expression();
				problems.addAll(semanticValidation(tree, rule, otherRules));
			} catch (Exception e) {
				Problem problem = new Problem();
				problem.setElementId(rule.getId());
				problem.setTagName("condition");
				problem.setError(EnumErrorType.CONDITION_LEXICAL_ERROR);
				if (e.getCause().toString().contains("NoViableAltException")) {
					Token token = ((NoViableAltException) (e.getCause()))
							.getOffendingToken();
					problem.setDescription("Lexical error: '"
							+ condition.substring(0,
									token.getCharPositionInLine())
							+ " »"
							+ condition.substring(token.getCharPositionInLine())
							+ "'");
				} else if (e instanceof InputMismatchException) {
					problem.setDescription("Input Mismatch Exception");
				} else if (e instanceof FailedPredicateException) {
					problem.setDescription("Failed Predicate Exception");
				} else {
					problem.setDescription("Unknown recognition error! Exception: "
							+ e.getClass().getName());
				}
				problems.add(problem);
			}

		}
		return problems;
	}

	// /**
	// * Validate the condition in the {@code targetRule}. {@code targetRule}
	// must
	// * not be part of the {@code existingMonitoringRules}. If the rule is
	// * already part of {@code existingMonitoringRules} use
	// * {@link #postvalidateCondition(MonitoringRule, MonitoringRules)}
	// instead.
	// *
	// * @param targetRule
	// * @param existingMonitoringRules
	// * @throws RuleValidationException
	// */
	// public void prevalidateCondition(MonitoringRule targetRule,
	// Collection<MonitoringRule> existingMonitoringRules)
	// throws RuleValidationException {
	// if (targetRule == null)
	// throw new NullArgumentException("targetRule");
	// if (existingMonitoringRules == null)
	// throw new NullArgumentException("existingMonitoringRules");
	// ParseTree tree = syntacticValidation(targetRule.getCondition()
	// .getValue());
	// assert tree != null;
	// semanticValidation(tree, targetRule, existingMonitoringRules);
	// }
	//
	// private MonitoringRule getActualRuleInstance(MonitoringRule targetRule,
	// MonitoringRules monitoringRules) throws RuleValidationException {
	// MonitoringRule actualRuleInstance = targetRule;
	// if (!monitoringRules.getMonitoringRules().contains(targetRule)) {
	// actualRuleInstance = XMLHelper.getElementByID(
	// monitoringRules.getMonitoringRules(), targetRule.getId());
	// if (actualRuleInstance == null)
	// throw new RuleValidationException(
	// "target rule is not contained in monitoring rules");
	// if (!targetRule.equals(actualRuleInstance))
	// throw new RuleValidationException(
	// "monitoringRule with same id exists among monitoringRules, but is different in some part");
	// }
	// return actualRuleInstance;
	// }

	// private ParseTree syntacticValidation(String condition)
	// throws RuleValidationException {
	//
	// ANTLRInputStream input = new ANTLRInputStream(condition);
	// ConditionLexer lexer = new ConditionLexer(input);
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// ConditionParser parser = new ConditionParser(tokens);
	// parser.setErrorHandler(new ErrorStrategy());
	// parser.removeErrorListeners();
	// lexer.removeErrorListeners();
	//
	// String message;
	//
	// try {
	// return parser.expression();
	// } catch (Exception e) {
	//
	// if (e.getCause().toString().contains("NoViableAltException")) {
	// Token token = ((NoViableAltException) (e.getCause()))
	// .getOffendingToken();
	// message = "Recognition error: '"
	// + condition.substring(0, token.getCharPositionInLine())
	// + " »"
	// + condition.substring(token.getCharPositionInLine())
	// + "'";
	// } else if (e instanceof InputMismatchException) {
	// message = "Input Mismatch Exception";
	// } else if (e instanceof FailedPredicateException) {
	// message = "Failed Predicate Exception";
	// } else {
	// message = "Unknown recognition error! Exception: "
	// + e.getClass().getName();
	// }
	// throw new RuleValidationException(message);
	// }
	// }
	//
	private Set<Problem> semanticValidation(ParseTree tree,
			MonitoringRule rule, Collection<MonitoringRule> otherRules) {
		Set<Problem> problems = new HashSet<Problem>();
		int childrenCount = tree.getChildCount();

		if (childrenCount != 0) {
			for (int i = 0; i < childrenCount; i++) {
				semanticValidation(tree.getChild(i), rule, otherRules);
			}
		} else {
			String occurenceMR_ID;
			switch (tree.getText()) {
//			case "parentCondition":
//				if (rule.getParentMonitoringRuleId() == null)
//					problems.add(new Problem(rule.getId(),
//							EnumErrorType.MISSING_REQUIRED_PARENT, "condition"));
//				break;
			case "maxOccurrence":
			case "minOccurrence":
				occurenceMR_ID = tree.getParent().getChild(2).getText();
				if (rule.getId().equals(occurenceMR_ID)) {
					Problem problem = new Problem(rule.getId(),
							EnumErrorType.CONDITION_SEMANTIC_ERROR, "condition");
					problem.setDescription("A rule cannot check maxOccurrence on itself");
					problems.add(problem);
				}
				if (!XMLHelper.containsId(otherRules, occurenceMR_ID)) {
					Problem problem = new Problem(rule.getId(),
							EnumErrorType.CONDITION_SEMANTIC_ERROR, "condition");
					problem.setDescription("Rule " + occurenceMR_ID
							+ " does not exist");
					problems.add(problem);
				}
				break;
			}
		}
		return problems;
	}


	private boolean softEquals(String name1, String name2) {
		if (name1 == null && name2 == null)
			return true;
		if (name1 == null || name2 == null)
			return false;
		return name1.toLowerCase().equals(name2.toLowerCase());
	}

//	public String getRequiredDataAnalyzer(String aggregateFunction) {
//		for (AggregateFunction af: config.getMonitoringAggregateFunctions().getAggregateFunctions()) {
//			if (softEquals(aggregateFunction, af.getName())) {
//				return af.getComputedBy().value();
//			}
//		}
//		return null;
//	}


}
