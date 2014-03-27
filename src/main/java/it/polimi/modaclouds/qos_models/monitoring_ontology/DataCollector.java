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

import java.util.List;

public class DataCollector extends KBEntity {

	private boolean enabled;
	private String collectedMetric;
	private List<Parameter> parameters;
	private List<MonitorableResource> targetResources;
	private String type;

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
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

	public List<MonitorableResource> getTargetResources() {
		return targetResources;
	}

	public void setTargetResources(List<MonitorableResource> targetResources) {
		this.targetResources = targetResources;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
