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

public class Vocabulary {

    
	public static final String CloudProvider = "CloudProvider";
    public static final String Component = "Component";
    public static final String ExternalComponent = "ExternalComponent";
    public static final String InternalComponent = "InternalComponent";
    public static final String PaaSService = "PaaSService";
    public static final String VM = "VM";
    public static final String Method = "Method";
    public static final String Location = "Location";
    
    
    public static final String cloudProvider = "cloudProvider";
    public static final String requiredComponent = "requiredComponent";
    public static final String providedMethod = "providedMethod";
    public static final String location = "location";
    public static final String type = "type";

    /* --- Monitoring Specific --- */
    public static final String MonitoringDatum = "MonitoringDatum";
    public static final String StatisticalDataAnalyzer = "StatisticalDataAnalyzer";
    public static final String DeterministicDataAnalyzer = "DeterministicDataAnalyzer";
    public static final String DataCollector = "DataCollector";
    public static final String Parameter = "Parameter";
    public static final String MonitoringComponent = "MonitoringComponent";
    public static final String MonitorableResource = "MonitorableResource";

    public static final String aboutResource = "aboutResource";
    public static final String parameter = "parameter";
    public static final String monitoredResource = "monitoredResource";
    public static final String metric = "metric";
    public static final String value = "value";
    public static final String timestamp = "timestamp";
    public static final String returnedMetric = "returnedMetric";
    public static final String targetMetric = "targetMetric";
    public static final String monitoredMetric = "monitoredMetric";
    public static final String name = "name";
    public static final String id = "id";
	
	//metrics
//	public static final String CpuUtilization = "CpuUtilization";
	
	//actions
	public static final String OutputMetric = "OutputMetric";
	public static final String EnableMonitoringRule = "EnableMonitoringRule";
	public static final String DisableMonitoringRule = "DisableMonitoringRule";
	public static final String SetSamplingProbability = "SetSamplingProbability";
	public static final String SetSamplingTime = "SetSamplingTime";
	
	public static final String samplingTime = "samplingTime";
	public static final String samplingProbability = "samplingProbability";
	public static final String timeWindow = "timeWindow";
	public static final String timeStep = "timeStep";
	public static final String forecastPeriod = "forecastPeriod";
	public static final String order = "order";
	public static final String autoregressive = "autoregressive";
	public static final String movingAverage = "movingAverage";
	
}
