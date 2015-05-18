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
package it.polimi.modaclouds.qos_models;

import it.polimi.modaclouds.qos_models.schema.AggregateFunction;
import it.polimi.modaclouds.qos_models.schema.Constraint;
import it.polimi.modaclouds.qos_models.schema.Constraints;
import it.polimi.modaclouds.qos_models.util.Config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NullArgumentException;

public class QoSValidator {

	private Config config;

	public QoSValidator() throws ConfigurationException {
		config = Config.getInstance();
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
			if (nullableEquals(af.getName(), c.getMetricAggregation()
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
		// TODO anything to check???
		return problems;
	}

	private Set<Problem> validateTargetClass(Constraint c) {
		Set<Problem> problems = new HashSet<Problem>();
		// TODO
		return problems;
	}


	private boolean nullableEquals(String name1, String name2) {
		if (name1 == null && name2 == null)
			return true;
		if (name1 == null || name2 == null)
			return false;
		return name1.equals(name2);
	}

}
