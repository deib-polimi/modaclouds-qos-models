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
import it.polimi.modaclouds.qos_models.schema.AvailableActions;
import it.polimi.modaclouds.qos_models.schema.GroupingCategories;
import it.polimi.modaclouds.qos_models.schema.Metrics;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static GroupingCategories defaultGroupingCategories;
	private static AggregateFunctions defaultMonitoringAggregateFunctions;
	private static AggregateFunctions defaultQosAggregateFunctions;
	private static Metrics defaultMonitoringMetrics;
	private static Metrics defaultQosMetrics;
	private static AvailableActions defaultMonitoringActions;

	private static String groupingCategoriesDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/monitoring_grouping_categories.xml";
	private static String monitoringAggregateFunctionsDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/monitoring_aggregate_functions.xml";
	private static String qosAggregateFunctionsDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/qos_aggregate_functions.xml";
	private static String monitoringMetricsDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/monitoring_metrics.xml";
	private static String qosMetricsDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/qos_metrics.xml";
	private static String monitoringActionsDefaultLocation = "https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/v2.0/metamodels/examples/monitoring_actions.xml";

	private GroupingCategories groupingCategories;
	private AggregateFunctions monitoringAggregateFunctions;
	private AggregateFunctions qosAggregateFunctions;
	private Metrics monitoringMetrics;
	private Metrics qosMetrics;
	private AvailableActions monitoringActions;

	private static Config _instance = null;
	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	private Config() throws ConfigurationException {
		try {
		this.groupingCategories = defaultGroupingCategories == null ? XMLHelper
				.deserialize(new URL(
						groupingCategoriesDefaultLocation), GroupingCategories.class)
				: defaultGroupingCategories;
		this.monitoringAggregateFunctions = defaultMonitoringAggregateFunctions == null ? XMLHelper
				.deserialize(new URL(
						monitoringAggregateFunctionsDefaultLocation),
						AggregateFunctions.class)
				: defaultMonitoringAggregateFunctions;
		this.qosAggregateFunctions = defaultQosAggregateFunctions == null ? XMLHelper
				.deserialize(new URL(
						qosAggregateFunctionsDefaultLocation),
						AggregateFunctions.class)
				: defaultMonitoringAggregateFunctions;
		this.monitoringMetrics = defaultMonitoringMetrics == null ? XMLHelper
				.deserialize(new URL(
						monitoringMetricsDefaultLocation), Metrics.class)
				: defaultMonitoringMetrics;
		this.qosMetrics = defaultQosMetrics == null ? XMLHelper.deserialize(
				new URL(
						qosMetricsDefaultLocation), Metrics.class) : defaultQosMetrics;
		this.monitoringActions = defaultMonitoringActions == null ? XMLHelper
				.deserialize(new URL(
						monitoringActionsDefaultLocation), AvailableActions.class)
				: defaultMonitoringActions;
		}catch (Exception e) {
			throw new ConfigurationException("Error while loading configuration files", e);
		}
	}

	public static void setDefaultConfiguration(Metrics qosMetrics,
			AggregateFunctions qosAggregateFunctions,
			GroupingCategories groupingCategories,
			AggregateFunctions monitoringAggregateFunctions,
			Metrics monitoringMetrics, AvailableActions monitoringActions) {
		defaultGroupingCategories = groupingCategories;
		defaultMonitoringActions = monitoringActions;
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

	/**
	 * 
	 * @return
	 */
	public GroupingCategories getGroupingCategories() {
		return groupingCategories;
	}

	/**
	 * 
	 * @return
	 */
	public AggregateFunctions getMonitoringAggregateFunctions() {
		return monitoringAggregateFunctions;
	}

	public AggregateFunctions getQosAggregateFunctions() {
		return monitoringAggregateFunctions;
	}

	/**
	 * 
	 * @return
	 */
	public Metrics getMonitoringMetrics() {
		return monitoringMetrics;
	}

	public Metrics getQosMetrics() {
		return qosMetrics;
	}

	/**
	 * 
	 * @return
	 */
	public AvailableActions getMonitoringActions() {
		return monitoringActions;
	}

	public static String getGroupingCategoriesDefaultLocation() {
		return groupingCategoriesDefaultLocation;
	}

	public static void setGroupingCategoriesDefaultLocation(
			String groupingCategoriesDefaultLocation) {
		Config.groupingCategoriesDefaultLocation = groupingCategoriesDefaultLocation;
	}

	public static String getMonitoringAggregateFunctionsDefaultLocation() {
		return monitoringAggregateFunctionsDefaultLocation;
	}

	public static String getQosAggregateFunctionsDefaultLocation() {
		return qosAggregateFunctionsDefaultLocation;
	}

	public static void setMonitoringAggregateFunctionsDefaultLocation(
			String monitoringAggregateFunctionsDefaultLocation) {
		Config.monitoringAggregateFunctionsDefaultLocation = monitoringAggregateFunctionsDefaultLocation;
	}

	public static void setQosAggregateFunctionsDefaultLocation(
			String qosAggregateFunctionsDefaultLocation) {
		Config.qosAggregateFunctionsDefaultLocation = qosAggregateFunctionsDefaultLocation;
	}

	public static String getMonitoringMetricsDefaultLocation() {
		return monitoringMetricsDefaultLocation;
	}

	public static String getQosMetricsDefaultLocation() {
		return qosMetricsDefaultLocation;
	}

	public static void setMonitoringMetricsDefaultLocation(
			String monitoringMetricsDefaultLocation) {
		Config.monitoringMetricsDefaultLocation = monitoringMetricsDefaultLocation;
	}

	public static void setQosMetricsDefaultLocation(
			String qosMetricsDefaultLocation) {
		Config.qosMetricsDefaultLocation = qosMetricsDefaultLocation;
	}

	public static String getMonitoringActionsDefaultLocation() {
		return monitoringActionsDefaultLocation;
	}

	public static void setMonitoringActionsDefaultLocation(
			String monitoringActionsDefaultLocation) {
		Config.monitoringActionsDefaultLocation = monitoringActionsDefaultLocation;
	}

	// /**
	// *
	// * @return
	// */
	// public URL getGroupingCategoriesUrl() {
	// return groupingCategoriesFile;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// public URL getMonitoringAggregateFunctionsUrl() {
	// return monitoringAggregateFunctionsFile;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// public URL getMonitoringMetricsUrl() {
	// return monitoringMetricsFile;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// public URL getMonitoringActionsUrl() {
	// return monitoringActionsFile;
	// }

	// public static URL getURL(String URLName) {
	// boolean exists = false;
	// URL url = null;
	// try {
	// url = Config.class.getResource(URLName);
	// exists = new File(url.toURI()).exists();
	// } catch (Exception e) {
	// logger.error("Error checking if file exists from URLName", e);
	// exists = false;
	// }
	// if (exists)
	// return url;
	// else {
	// try {
	// String alternativeURLName = new File(Config.class
	// .getProtectionDomain().getCodeSource().getLocation()
	// .getPath()).getParent()
	// + URLName;
	//
	// url = new File(alternativeURLName).toURI().toURL();
	// exists = new File(url.toURI()).exists();
	// } catch (Exception e) {
	// logger.error(
	// "Error checking if file exists from alternativeURLName",
	// e);
	// exists = false;
	// }
	// }
	// return null;
	// }

	// public static String getGroupingCategoriesDefaultUrl() {
	// return groupingCategoriesDefaultUrl;
	// }

	// public static void setGroupingCategoriesDefaultUrl(
	// String groupingCategoriesDefaultUrl) {
	// Config.groupingCategoriesDefaultUrl = groupingCategoriesDefaultUrl;
	// }

	// public static String getMonitoringAggregateFunctionsDefaultUrl() {
	// return monitoringAggregateFunctionsDefaultUrl;
	// }
	//
	// public static void setMonitoringAggregateFunctionsDefaultUrl(
	// String monitoringAggregateFunctionsDefaultUrl) {
	// Config.monitoringAggregateFunctionsDefaultUrl =
	// monitoringAggregateFunctionsDefaultUrl;
	// }
	//
	// public static String getMonitoringMetricsDefaultUrl() {
	// return monitoringMetricsDefaultUrl;
	// }
	//
	// public static void setMonitoringMetricsDefaultUrl(
	// String monitoringMetricsDefaultUrl) {
	// Config.monitoringMetricsDefaultUrl = monitoringMetricsDefaultUrl;
	// }
	//
	// public static String getMonitoringActionsDefaultUrl() {
	// return monitoringActionsDefaultUrl;
	// }
	//
	// public static void setMonitoringActionsDefaultUrl(
	// String monitoringActionsDefaultUrl) {
	// Config.monitoringActionsDefaultUrl = monitoringActionsDefaultUrl;
	// }

}
