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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class InternalComponent extends Component{

	public InternalComponent(String id) throws URISyntaxException {
		super(id);
	}
	
	public InternalComponent() throws URISyntaxException {
		super();
	}

	private Set<URI> requiredComponents;
	private Set<URI> providedMethods;

	public Set<URI> getRequiredComponents() {
		return requiredComponents;
	}

	public void setRequiredComponents(Set<URI> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}

	public Set<URI> getProvidedMethods() {
		return providedMethods;
	}

	public void setProvidedMethods(Set<URI> providedMethods) {
		this.providedMethods = providedMethods;
	}

	public void addRequiredComponent(URI component) {
		if (requiredComponents == null)
			requiredComponents = new HashSet<URI>();
		requiredComponents.add(component);
	}

	public void addProvidedMethod(URI method) {
		if (providedMethods == null)
			providedMethods = new HashSet<URI>();
		providedMethods.add(method);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((providedMethods == null) ? 0 : providedMethods.hashCode());
		result = prime
				* result
				+ ((requiredComponents == null) ? 0 : requiredComponents
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
		InternalComponent other = (InternalComponent) obj;
		if (providedMethods == null) {
			if (other.providedMethods != null)
				return false;
		} else if (!providedMethods.equals(other.providedMethods))
			return false;
		if (requiredComponents == null) {
			if (other.requiredComponents != null)
				return false;
		} else if (!requiredComponents.equals(other.requiredComponents))
			return false;
		return true;
	}

	
	

	
}
