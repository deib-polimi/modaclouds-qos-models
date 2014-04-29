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

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	private static final String groupingCategoriesDefaultUrl = "/monitoring_grouping_categories.xml";
	private static final String monitoringAggregateFunctionsDefaultUrl = "/monitoring_aggregate_functions.xml";
	private static final String monitoringMetricsDefaultUrl = "/monitoring_metrics.xml";
	private static final String monitoringActionsDefaultUrl = "/monitoring_actions.xml";
	
	private URL groupingCategoriesUrl;
	private URL monitoringAggregateFunctionsUrl;
	private URL monitoringMetricsUrl;
	private URL monitoringActionsUrl;
	
	private GroupingCategories groupingCategories;
	private AggregateFunctions monitoringAggregateFunctions;
	private Metrics monitoringMetrics;
	private AvailableActions monitoringActions;
	
	private static Config _instance = null;
	private static final Logger logger = LoggerFactory.getLogger(Config.class); 
	
	private Config() throws ConfigurationException, JAXBException {
		groupingCategoriesUrl = getURL(groupingCategoriesDefaultUrl);
		monitoringAggregateFunctionsUrl = getURL(monitoringAggregateFunctionsDefaultUrl);
		monitoringMetricsUrl = getURL(monitoringMetricsDefaultUrl);
		monitoringActionsUrl = getURL(monitoringActionsDefaultUrl);

		this.groupingCategories = XMLHelper.deserialize(groupingCategoriesUrl, GroupingCategories.class);
		this.monitoringAggregateFunctions =  XMLHelper.deserialize(monitoringAggregateFunctionsUrl, AggregateFunctions.class);
		this.monitoringMetrics =  XMLHelper.deserialize(monitoringMetricsUrl, Metrics.class);
		this.monitoringActions =  XMLHelper.deserialize(monitoringActionsUrl, AvailableActions.class);
	}
	
	/**
	 * 
	 * @return
	 * @throws ConfigurationException
	 * @throws JAXBException
	 */
	public static Config getInstance() throws ConfigurationException, JAXBException {
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

	/**
	 * 
	 * @return
	 */
	public Metrics getMonitoringMetrics() {
		return monitoringMetrics;
	}

	/**
	 * 
	 * @return
	 */
	public AvailableActions getMonitoringActions() {
		return monitoringActions;
	}
	
	/**
	 * 
	 * @return
	 */
	public URL getGroupingCategoriesUrl() {
		return groupingCategoriesUrl;
	}

	/**
	 * 
	 * @return
	 */
	public URL getMonitoringAggregateFunctionsUrl() {
		return monitoringAggregateFunctionsUrl;
	}

	/**
	 * 
	 * @return
	 */
	public URL getMonitoringMetricsUrl() {
		return monitoringMetricsUrl;
	}

	/**
	 * 
	 * @return
	 */
	public URL getMonitoringActionsUrl() {
		return monitoringActionsUrl;
	}
	
	public static URL getURL(String URLName) {
		boolean exists = false;
		URL url = null;
		try {
			url = Config.class.getResource(URLName);
			exists = new File(url.toURI()).exists();
		} catch (Exception e) {
			logger.error("Error checking if file exists from URLName", e);
			exists = false;
		}
		if (exists)
			return url;
		else {
			try {
				String alternativeURLName = new File(Config.class
						.getProtectionDomain().getCodeSource().getLocation()
						.getPath()).getParent()
						+ URLName;

				url = new File(alternativeURLName).toURI().toURL();
				exists = new File(url.toURI()).exists();
			} catch (Exception e) {
				logger.error("Error checking if file exists from alternativeURLName",
						e);
				exists = false;
			}
		}
		return null;
	}

}
