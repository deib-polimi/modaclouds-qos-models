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

public class SDAFactory extends DataAnalyzer {
	
	private Set<StatisticalDataAnalyzer> instantiatedSDAs;
	private String type;

	public Set<StatisticalDataAnalyzer> getInstantiatedSDAs() {
		return instantiatedSDAs;
	}

	public void setInstantiatedSDAs(Set<StatisticalDataAnalyzer> instantiatedSDAs) {
		this.instantiatedSDAs = instantiatedSDAs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((instantiatedSDAs == null) ? 0 : instantiatedSDAs.hashCode());
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
		SDAFactory other = (SDAFactory) obj;
		if (instantiatedSDAs == null) {
			if (other.instantiatedSDAs != null)
				return false;
		} else if (!instantiatedSDAs.equals(other.instantiatedSDAs))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
