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

2.1.4:

* monitoredTarget in monitoring rules had "id" renamed to "type" because of its ambiguity. From now on:
  * "class" specify the resource according to the [ontology](https://github.com/deib-polimi/modaclouds-qos-models/blob/master/doc/user-manual.md#the-monitoring-ontology)
  * "type" is the name assigned to a resource during the design phase, univocally identifying the role of the resource
  * "id" (currently not available in monitoring rules) is the unique identifier of a deployed instance
* installation instructions updated (look at the documentation)