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

import java.util.HashSet;
import java.util.Set;

public class StatisticalDataAnalyzer extends KBEntity{
	
	private int period;
	private String method;
	private String returnedMetric;
	private String targetMetric;
	private Set<MonitorableResource> targetResources;
	private Set<Parameter> parameters;
	private String type;
	private boolean started;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public Set<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(Set<Parameter> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(Parameter parameter) {
		if (parameters == null)
			parameters = new HashSet<Parameter>();
		parameters.add(parameter);
	}
	public Set<MonitorableResource> getTargetResources() {
		return targetResources;
	}
	public void setTargetResources(Set<MonitorableResource> targetResources) {
		this.targetResources = targetResources;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + period;
		result = prime * result
				+ ((returnedMetric == null) ? 0 : returnedMetric.hashCode());
		result = prime * result + (started ? 1231 : 1237);
		result = prime * result
				+ ((targetMetric == null) ? 0 : targetMetric.hashCode());
		result = prime * result
				+ ((targetResources == null) ? 0 : targetResources.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (period != other.period)
			return false;
		if (returnedMetric == null) {
			if (other.returnedMetric != null)
				return false;
		} else if (!returnedMetric.equals(other.returnedMetric))
			return false;
		if (started != other.started)
			return false;
		if (targetMetric == null) {
			if (other.targetMetric != null)
				return false;
		} else if (!targetMetric.equals(other.targetMetric))
			return false;
		if (targetResources == null) {
			if (other.targetResources != null)
				return false;
		} else if (!targetResources.equals(other.targetResources))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
