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

import java.util.ArrayList;
import java.util.List;

public class InternalComponent extends Component{

	private List<Component> requiredComponents;
	private List<Method> providedMethods;

	public List<Component> getRequiredComponents() {
		return requiredComponents;
	}

	public void setRequiredComponents(List<Component> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}

	public List<Method> getProvidedMethods() {
		return providedMethods;
	}

	public void setProvidedMethods(List<Method> providedMethods) {
		this.providedMethods = providedMethods;
	}

	public void addRequiredComponent(Component component) {
		if (requiredComponents == null)
			requiredComponents = new ArrayList<Component>();
		requiredComponents.add(component);
	}

	public void addProvidedMethod(Method method) {
		if (providedMethods == null)
			providedMethods = new ArrayList<Method>();
		providedMethods.add(method);
	}
	

	
}
