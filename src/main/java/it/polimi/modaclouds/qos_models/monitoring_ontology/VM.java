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


public class VM extends ExternalComponent {
	
	private int numberOfCPUs;
	private String location;
	
	public int getNumberOfCPUs() {
		return numberOfCPUs;
	}
	public void setNumberOfCPUs(int numberOfCPUs) {
		this.numberOfCPUs = numberOfCPUs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + numberOfCPUs;
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
		VM other = (VM) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (numberOfCPUs != other.numberOfCPUs)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "VM [numberOfCPUs=" + numberOfCPUs + ", location=" + location
				+ ", getCloudProvider()=" + getCloudProvider() + ", getType()="
				+ getType() + ", getId()=" + getId() + "]";
	}

	

}
