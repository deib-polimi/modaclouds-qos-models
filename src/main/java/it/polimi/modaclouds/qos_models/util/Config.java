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
package it.polimi.modaclouds.qos_models.util;

import it.polimi.modaclouds.qos_models.ConfigurationException;
import it.polimi.modaclouds.qos_models.schema.AggregateFunctions;

public class Config {

	private static String qosAggregateFunctionsFileName = "/qos_aggregate_functions.xml";

	private AggregateFunctions qosAggregateFunctions;

	private static Config _instance = null;

	private Config() throws ConfigurationException {
		try {

			this.qosAggregateFunctions = XMLHelper.deserialize(getClass()
					.getResourceAsStream(qosAggregateFunctionsFileName),
					AggregateFunctions.class);
		} catch (Exception e) {
			throw new ConfigurationException(
					"Error while loading configuration files", e);
		}
	}

	public static Config getInstance() throws ConfigurationException {
		if (_instance == null) {
			_instance = new Config();
		}
		return _instance;
	}

	public AggregateFunctions getQosAggregateFunctions() {
		return qosAggregateFunctions;
	}

}
