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

import it.polimi.modaclouds.monitoring.kb.api.KBEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class StatisticalDataAnalyzer extends KBEntity{
	
	
	private String aggregateFunction;
	private String returnedMetric;
	private String targetMetric;
	private Set<String> targetResourcesIds;
	private Set<URI> parameters;
	
	public StatisticalDataAnalyzer() throws URISyntaxException {
		super();
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
	public String getTargetMetric() {
		return targetMetric;
	}
	public void setTargetMetric(String targetMetric) {
		this.targetMetric = targetMetric;
	}
	public Set<URI> getParameters() {
		return parameters;
	}
	public void setParameters(Set<URI> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(URI parameter) {
		if (parameters == null)
			parameters = new HashSet<URI>();
		parameters.add(parameter);
	}
	public Set<String> getTargetResourcesIds() {
		return targetResourcesIds;
	}
	public void setTargetResourcesIds(Set<String> targetResourcesIds) {
		this.targetResourcesIds = targetResourcesIds;
	}
	
	@Override
	public String getURIBase() {
		return MO.URI;
	}
	@Override
	public String getURIPrefix() {
		return MO.prefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((aggregateFunction == null) ? 0 : aggregateFunction
						.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((returnedMetric == null) ? 0 : returnedMetric.hashCode());
		result = prime * result
				+ ((targetMetric == null) ? 0 : targetMetric.hashCode());
		result = prime
				* result
				+ ((targetResourcesIds == null) ? 0 : targetResourcesIds
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticalDataAnalyzer other = (StatisticalDataAnalyzer) obj;
		if (aggregateFunction == null) {
			if (other.aggregateFunction != null)
				return false;
		} else if (!aggregateFunction.equals(other.aggregateFunction))
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
		if (targetMetric == null) {
			if (other.targetMetric != null)
				return false;
		} else if (!targetMetric.equals(other.targetMetric))
			return false;
		if (targetResourcesIds == null) {
			if (other.targetResourcesIds != null)
				return false;
		} else if (!targetResourcesIds.equals(other.targetResourcesIds))
			return false;
		return true;
	}
	
	

}
