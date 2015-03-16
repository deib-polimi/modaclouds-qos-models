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
package it.polimi.modaclouds.qos_models.monitoring_rules.actions;

import it.polimi.modaclouds.qos_models.monitoring_rules.EnumErrorType;
import it.polimi.modaclouds.qos_models.monitoring_rules.Problem;
import it.polimi.modaclouds.qos_models.schema.Action;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.Parameter;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAction {
	
	Logger getLogger(){
		return LoggerFactory.getLogger(this.getClass().getName());
	}

	public final Set<Problem> validateRule(MonitoringRule rule,
			List<MonitoringRule> otherRules) {
		Set<Problem> problems = new HashSet<Problem>();
		for (Action action : getMyActions(rule)) {
			Map<String, String> pars = getParameters(action);
			Set<String> requiredPars = getMyRequiredPars();
			Set<String> missingPars = new HashSet<String>();
			missingPars.addAll(requiredPars);
			missingPars.removeAll(pars.keySet());
			if (!missingPars.isEmpty()) {
				problems.add(new Problem(rule.getId(),
						EnumErrorType.MISSING_REQUIRED_PARAMETER, action.getName(),
						"Missing required parameters: "
								+ missingPars.toString()));
			}
		}
		problems.addAll(validate(rule, otherRules));
		return problems;
	}

	abstract Set<String> getMyRequiredPars();

	abstract Collection<? extends Problem> validate(MonitoringRule rule,
			List<MonitoringRule> otherRules);

	public static Map<String, String> getParameters(Action action) {
		Map<String, String> pars = new HashMap<String, String>();
		for (Parameter par : action.getParameters()) {
			pars.put(par.getName(), par.getValue());
		}
		return pars;
	}

	Set<Action> getMyActions(MonitoringRule rule) {
		Set<Action> actions = new HashSet<Action>();
		for (Action action : rule.getActions().getActions()) {
			if (action.getName().equalsIgnoreCase(getId())) {
				actions.add(action);
			}
		}
		return actions;
	}

	public abstract String getId();

}
