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

public abstract class StatisticalDataAnalyzer extends MonitoringComponent{
	
	private String period;
	private String method;
	private String returnedMetric;
	private String targetMetric;
	private List<MonitorableResource> targetResources;
	private List<Parameter> parameters;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
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
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	public List<MonitorableResource> getTargetResources() {
		return targetResources;
	}
	public void setTargetResources(List<MonitorableResource> targetResources) {
		this.targetResources = targetResources;
	}
	
	

}
