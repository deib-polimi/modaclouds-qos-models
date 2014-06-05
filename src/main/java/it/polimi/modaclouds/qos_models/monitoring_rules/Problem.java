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
package it.polimi.modaclouds.qos_models.monitoring_rules;


public class Problem {
	private String id;
	private String tagName;
	private EnumErrorType error;
	private String description;

	public Problem(String id, EnumErrorType error, String tagName) {
		this.id = id;
		this.error = error;
		this.tagName = tagName;
	}

	public Problem() {
	}

	public Problem(String id, EnumErrorType error, String tagName,
			String description) {
		this(id, error, tagName);
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setElementId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public EnumErrorType getError() {
		return error;
	}

	public void setError(EnumErrorType error) {
		this.error = error;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
