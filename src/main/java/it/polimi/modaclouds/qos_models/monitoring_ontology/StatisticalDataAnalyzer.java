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
package it.polimi.modaclouds.qos_models.monitoring_ontology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticalDataAnalyzer {

	private String id;
	private String timeStep;
	private String aggregateFunction;
	private String returnedMetric;
	private List<String> inputMetrics = new ArrayList<String>();
	private List<String> inputResourcesIds = new ArrayList<String>();
	private Map<String, String> parameters = new HashMap<String, String>();
	
	public String getTimeStep() {
		return timeStep;
	}
	public void setTimeStep(String timeStep) {
		this.timeStep = timeStep;
	}
	public String getAggregateFunction() {
		return aggregateFunction;
	}
	public void setAggregateFunction(String aggregateFunction) {
		this.aggregateFunction = aggregateFunction;
	}
	public String getReturnedMetric() {
		return returnedMetric;
	}
	public void setReturnedMetric(String returnedMetric) {
		this.returnedMetric = returnedMetric;
	}
	public List<String> getInputMetrics() {
		return inputMetrics;
	}
	public void setInputMetrics(List<String> inputMetrics) {
		this.inputMetrics = inputMetrics;
	}
	public List<String> getInputResourcesIds() {
		return inputResourcesIds;
	}
	public void setInputResourcesIds(List<String> inputResourcesIds) {
		this.inputResourcesIds = inputResourcesIds;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((aggregateFunction == null) ? 0 : aggregateFunction
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inputMetrics == null) ? 0 : inputMetrics.hashCode());
		result = prime
				* result
				+ ((inputResourcesIds == null) ? 0 : inputResourcesIds
						.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((returnedMetric == null) ? 0 : returnedMetric.hashCode());
		result = prime * result
				+ ((timeStep == null) ? 0 : timeStep.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticalDataAnalyzer other = (StatisticalDataAnalyzer) obj;
		if (aggregateFunction == null) {
			if (other.aggregateFunction != null)
				return false;
		} else if (!aggregateFunction.equals(other.aggregateFunction))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputMetrics == null) {
			if (other.inputMetrics != null)
				return false;
		} else if (!inputMetrics.equals(other.inputMetrics))
			return false;
		if (inputResourcesIds == null) {
			if (other.inputResourcesIds != null)
				return false;
		} else if (!inputResourcesIds.equals(other.inputResourcesIds))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (returnedMetric == null) {
			if (other.returnedMetric != null)
				return false;
		} else if (!returnedMetric.equals(other.returnedMetric))
			return false;
		if (timeStep == null) {
			if (other.timeStep != null)
				return false;
		} else if (!timeStep.equals(other.timeStep))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "StatisticalDataAnalyzer [id=" + id + ", timeStep=" + timeStep
				+ ", aggregateFunction=" + aggregateFunction
				+ ", returnedMetric=" + returnedMetric + ", inputMetrics="
				+ inputMetrics + ", inputResourcesIds=" + inputResourcesIds
				+ ", parameters=" + parameters + "]";
	}
	public void addInputMetric(String metricName) {
		inputMetrics.add(metricName);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	

}
