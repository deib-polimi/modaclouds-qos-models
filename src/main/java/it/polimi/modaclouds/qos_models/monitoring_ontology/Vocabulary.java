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

    
    
    public static final String Component = "Component";
    public static final String ExternalComponent = "ExternalComponent";
    public static final String InternalComponent = "InternalComponent";
    public static final String PaaSService = "PaaSService";
    public static final String VM = "VM";
    public static final String Method = "Method";
    public static final String Location = "Location";
    
    public static final String hasProvider = "hasProvider";
    public static final String requires = "requires";
    public static final String provides = "provides";
    public static final String calls = "calls";
    public static final String isIn = "isIn";
    public static final String hasNCPU = "hasNCPU";
    public static final String hasName = "hasName";
    public static final String hasPath = "hasPath";
    public static final String hasType = "hasType";
    public static final String hasURL = "hasURL";

    /* --- Monitoring Specific --- */
    public static final String MonitoringDatum = "MonitoringDatum";
    public static final String SDAFactory = "SDAFactory";
    public static final String DCFactory = "DCFactory";
    public static final String StatisticalDataAnalyzer = "StatisticalDataAnalyzer";
    public static final String DataCollector = "DataCollector";
    public static final String Parameter = "Parameter";
    public static final String MonitoringComponent = "MonitoringComponent";
    public static final String MonitorableResource = "MonitorableResource";

    public static final String isAbout = "isAbout";
    public static final String hasParameter = "hasParameter";
    public static final String hasTargetResource = "hasTargetResource";
    public static final String instantiates = "instantiates";
    public static final String hasMetric = "hasMetric";
    public static final String hasValue = "hasValue";
    public static final String hasTimestamp = "hasTimestamp";
    public static final String hasPeriod = "hasPeriod";
    public static final String hasMethod = "hasMethod";
    public static final String hasReturnedMetric = "hasReturnedMetric";
    public static final String hasTargetMetric = "hasTargetMetric";
    public static final String isStarted = "isStarted";
    public static final String isEnabled = "isEnabled";
    public static final String hasCollectedMetric = "hasCollectedMetric";
}
