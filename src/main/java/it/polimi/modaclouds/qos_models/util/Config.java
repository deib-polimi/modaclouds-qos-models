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

import it.polimi.modaclouds.qos_models.monitoring_rules.ConfigurationException;
import it.polimi.modaclouds.qos_models.schema.AggregateFunctions;
import it.polimi.modaclouds.qos_models.schema.GroupingCategories;
import it.polimi.modaclouds.qos_models.schema.Metrics;

public class Config {

	private static GroupingCategories defaultGroupingCategories;
	private static AggregateFunctions defaultMonitoringAggregateFunctions;
	private static AggregateFunctions defaultQosAggregateFunctions;
	private static Metrics defaultMonitoringMetrics;
	private static Metrics defaultQosMetrics;

	private static String groupingCategoriesFileName = "/monitoring_grouping_categories.xml";
	private static String monitoringAggregateFunctionsFileName = "/monitoring_aggregate_functions.xml";
	private static String qosAggregateFunctionsFileName = "/qos_aggregate_functions.xml";
	private static String monitoringMetricsFileName = "/monitoring_metrics.xml";
	private static String qosMetricsFileName = "/qos_metrics.xml";

	private GroupingCategories groupingCategories;
	private AggregateFunctions monitoringAggregateFunctions;
	private AggregateFunctions qosAggregateFunctions;
	private Metrics monitoringMetrics;
	private Metrics qosMetrics;

	private static Config _instance = null;

	private Config() throws ConfigurationException {
		try {
			this.groupingCategories = defaultGroupingCategories == null ? XMLHelper
					.deserialize(
							getClass().getResourceAsStream(
									groupingCategoriesFileName),
							GroupingCategories.class)
					: defaultGroupingCategories;
			this.monitoringAggregateFunctions = defaultMonitoringAggregateFunctions == null ? XMLHelper
					.deserialize(getClass().getResourceAsStream(
							monitoringAggregateFunctionsFileName),
							AggregateFunctions.class)
					: defaultMonitoringAggregateFunctions;
			this.qosAggregateFunctions = defaultQosAggregateFunctions == null ? XMLHelper
					.deserialize(getClass().getResourceAsStream(
							qosAggregateFunctionsFileName),
							AggregateFunctions.class)
					: defaultMonitoringAggregateFunctions;
			this.monitoringMetrics = defaultMonitoringMetrics == null ? XMLHelper
					.deserialize(getClass().getResourceAsStream(
							monitoringMetricsFileName),
							Metrics.class) : defaultMonitoringMetrics;
			this.qosMetrics = defaultQosMetrics == null ? XMLHelper
					.deserialize(getClass().getResourceAsStream(
							qosMetricsFileName),
							Metrics.class) : defaultQosMetrics;
		} catch (Exception e) {
			throw new ConfigurationException(
					"Error while loading configuration files", e);
		}
	}

	public static void setDefaultConfiguration(Metrics qosMetrics,
			AggregateFunctions qosAggregateFunctions,
			GroupingCategories groupingCategories,
			AggregateFunctions monitoringAggregateFunctions,
			Metrics monitoringMetrics) {
		defaultGroupingCategories = groupingCategories;
		defaultMonitoringAggregateFunctions = monitoringAggregateFunctions;
		defaultMonitoringMetrics = monitoringMetrics;
		defaultQosMetrics = qosMetrics;
		defaultQosAggregateFunctions = qosAggregateFunctions;
	}

	public static Config getInstance() throws ConfigurationException {
		if (_instance == null) {
			_instance = new Config();
		}
		return _instance;
	}


	public GroupingCategories getGroupingCategories() {
		return groupingCategories;
	}

	public AggregateFunctions getMonitoringAggregateFunctions() {
		return monitoringAggregateFunctions;
	}

	public AggregateFunctions getQosAggregateFunctions() {
		return qosAggregateFunctions;
	}

	public Metrics getMonitoringMetrics() {
		return monitoringMetrics;
	}

	public Metrics getQosMetrics() {
		return qosMetrics;
	}


	
}
