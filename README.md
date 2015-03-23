MODAClouds QoS Models
==========================

In the context of MODAClouds European project (www.modaclouds.eu), Politecnico was
one of the partners involved in the development of the QoS Analysis and Monitoring Tools.

The QoS Models is a library containing all metamodels necessary to describe qos-constraints, monitoring rules
and monitoring ontology together with their java representation.

Please refer to deliverable [D6.3.2](http://www.modaclouds.eu/publications/public-deliverables/) 
to better understand the role of this component in the MODAClouds Monitoring Platform.

Refer to the [Monitoring Platform Wiki](https://github.com/deib-polimi/modaclouds-monitoring-manager/wiki) for installation and usage of the whole platform.

Additional Features:
- Serialization and deserialization between XML and Java objects.
- automatic generation of monitoring rules from qos constraints
- validation of qos-constraints and monitoring rules

## Documentation

Take a look at the [documentation table of contents](doc/TOC.md).

## Change List

v2.4:
* Framework setup for easily extension with new actions, see the dev manual
* RestCall action implemented, see the user manual

v2.3:
* removed any validation check during constraint to rule translation
* added EffectiveResponseTime to metrics lists
* only qos constraints and monitoring rules metrics list is now left configurable through `setDefaultConfiguration`
* monitoring rules actions are now validated through (and soon implemented as) classes extending the AbstractAction class

v2.2.2:
* removed the concept of zones in resource container extension to be coherent with cloudML

v2.2.1:
* rules are now generated from constraints even though the metric is not listed among the valid monitoring metrics, a warning message is simply shown

v2.2:

* validation improvements
* metricAggregation is now optional
* bug fixes
* inserted two new xsd: performances and costs

v2.1.4:

* monitoredTarget in monitoring rules had "id" renamed to "type", so that the rules are now coherent with the deployment model terminology. Look at the available examples. From now on:
  * "class" specify the resource according to the [ontology](https://github.com/deib-polimi/modaclouds-qos-models/blob/master/doc/user-manual.md#the-monitoring-ontology)
  * "type" is the name assigned to a resource during the design phase, univocally identifying the role of the resource
  * "id" is the unique identifier of a deployed instance, currently used only in the [deployment model](https://github.com/deib-polimi/modaclouds-qos-models/blob/master/doc/user-manual.md#the-monitoring-ontology), not in monitoring rules
* installation instructions updated (look at the documentation)
