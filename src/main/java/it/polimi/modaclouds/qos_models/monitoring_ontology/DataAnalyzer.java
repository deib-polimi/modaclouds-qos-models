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

public class DataAnalyzer extends MonitoringComponent {

	private Set<AvailableAggregateFunction> availableAggregateFunctions;

	public Set<AvailableAggregateFunction> getAvailableAggregateFunctions() {
		return availableAggregateFunctions;
	}

	public void setAvailableAggregateFunctions(
			Set<AvailableAggregateFunction> availableAggregateFunctions) {
		this.availableAggregateFunctions = availableAggregateFunctions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((availableAggregateFunctions == null) ? 0
						: availableAggregateFunctions.hashCode());
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
		DataAnalyzer other = (DataAnalyzer) obj;
		if (availableAggregateFunctions == null) {
			if (other.availableAggregateFunctions != null)
				return false;
		} else if (!availableAggregateFunctions
				.equals(other.availableAggregateFunctions))
			return false;
		return true;
	}

}
