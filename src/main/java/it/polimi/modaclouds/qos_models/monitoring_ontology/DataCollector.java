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

public class DataCollector extends KBEntity {
	
	

	public DataCollector() throws URISyntaxException {
		super();
	}

	private String monitoredMetric;
	private Set<URI> parameters;
	private Set<URI> monitoredResources;

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


	public String getMonitoredMetric() {
		return monitoredMetric;
	}

	public void setMonitoredMetric(String monitoredMetric) {
		this.monitoredMetric = monitoredMetric;
	}

	public Set<URI> getMonitoredResources() {
		return monitoredResources;
	}

	public void setMonitoredResources(Set<URI> monitoredResources) {
		this.monitoredResources = monitoredResources;
	}

	public void addMonitoredResource(URI monitoredResource) {
		if (monitoredResources == null)
			monitoredResources = new HashSet<URI>();
		monitoredResources.add(monitoredResource);
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
		result = prime * result
				+ ((monitoredMetric == null) ? 0 : monitoredMetric.hashCode());
		result = prime
				* result
				+ ((monitoredResources == null) ? 0 : monitoredResources
						.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
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
		if (monitoredMetric == null) {
			if (other.monitoredMetric != null)
				return false;
		} else if (!monitoredMetric.equals(other.monitoredMetric))
			return false;
		if (monitoredResources == null) {
			if (other.monitoredResources != null)
				return false;
		} else if (!monitoredResources.equals(other.monitoredResources))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
	
	
}
