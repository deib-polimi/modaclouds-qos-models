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

import java.util.Set;

public class DataCollector extends KBEntity {

	private boolean enabled;
	private String collectedMetric;
	private Set<Parameter> parameters;
	private Set<MonitorableResource> targetResources;
	private String type;

	public Set<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Set<Parameter> parameters) {
		this.parameters = parameters;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCollectedMetric() {
		return collectedMetric;
	}

	public void setCollectedMetric(String collectedMetric) {
		this.collectedMetric = collectedMetric;
	}

	public Set<MonitorableResource> getTargetResources() {
		return targetResources;
	}

	public void setTargetResources(Set<MonitorableResource> targetResources) {
		this.targetResources = targetResources;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((collectedMetric == null) ? 0 : collectedMetric.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
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
		DataCollector other = (DataCollector) obj;
		if (collectedMetric == null) {
			if (other.collectedMetric != null)
				return false;
		} else if (!collectedMetric.equals(other.collectedMetric))
			return false;
		if (enabled != other.enabled)
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
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
