[Documentation table of contents](TOC.md) / User Manual

# User Manual

## The Monitoring Ontology

The monitoring ontology defines concepts concerning the system deployment model (cloud provider
resources hosting the application and the application) and their relations.

Here is a graphical of the ontology representation:

![monitoring ontology](img/ontology.jpg "Monitoring Ontology").

The ontology has been recently adapted to the ongoing development of [CloudML2.0](http://cloudml.org/)
which is currently synchronizing with 
[TOSCA](https://www.oasis-open.org/committees/tc_home.php?wg_abbrev=tosca).

## What is a QoS Constraint?

TODO

## What is a Monitoring Rule?

A Monitoring Rule specifies how incoming data have to be processed, what conditions have to be
 verified and what output should be produced by the monitoring platform.

The metamodel of a monitoring rule is formally specified as an XML schema:
[http://www.modaclouds.eu/xsd/1.0/monitoring_rules_schema](https://raw.githubusercontent.com/deib-polimi/modaclouds-qos-models/master/metamodels/monitoringrules/monitoring_rules_schema.xsd).

Here is a graphical representation of the schema:

![monitoring rule schema](img/monitoring_rules_schema.png "monitoring_rules_schema")

Here is a list of the attributes with a short description:
- *metricName*: the name of the target metric
- *monitoredTargets*: the list of targets resources targeted by the rule
- *metricAggregation*: specifies if and how data should be transformed
- *timeStep*: time interval in seconds between two consecutive evaluations of the rule
- *timeWindow*: time range in seconds in which data should be considered to be aggregated at every time step
- *startEnabled*: specifies whether the rule evaluation should start once installed
- *samplingTime*: time interval in seconds between two consecutive collections (may be not applicable for some metrics)
- *samplingProbability*: probability of collecting the metric (used to reduce the monitoring workload, may be not applicable for some metrics)
- *relatedQosConstraintId*: if specified, identifies the id of the qos constraint from which the rule is generated
- *parentMonitoringRuleId*: if specified, identifies the id of the parent monitoring rule
- *condition*: the condition to be evaluated on the aggregated metric or, in case no aggregation is specified, on raw data
- *actions*: the list of actions to be executed for each monitoring datum (produced by aggregation or raw datum).
If a condition is specified the action is performed if and only if the condition is verified.

### Condition

#### Syntax

```
<condition> ::= <term> | <term> ` || ' <condition>

<term> ::= <factor> | <factor> ` && ' <term>

<factor> ::= <atom> | `!' <factor> | `(' <condition> `)'

<atom> ::= <var> <operator> <var> 
		| `maxOccurrence(' <metric_id> `,' <int> `)'
		| `minOccurrence(' <metric_id> `,' <int> `)'
		| `parentCondition'
		
<var> ::= `METRIC' | <decimal>

<operator> ::= `>=' | `<=' | `=' | `<>' | `>' | `<'
```

#### Semantics

- <metric_id> in maxOccurrence and minOccurrence must refer to a *returnedMetric* of an existing monitoring
rule executing action *OutputMetric*.
- parentCondition can be used only if the monitoring rule whose condition is under evaluation has a parent rule

### Available Actions

- OutputMetric

## Installation

You can download the jar from https://github.com/deib-polimi/modaclouds-qos-models/releases.
The project is also deployed on our Maven Repo.

Releases repository:
```xml
<repositories>
	...
	<repository>
        <id>deib-polimi-releases</id>
        <url>https://github.com/deib-polimi/deib-polimi-mvn-repo/raw/master/releases</url>
	</repository>
	...
</repositories>
```

Dependency:
```xml
<dependencies>
	<dependency>
		<groupId>it.polimi.modaclouds.qos-models</groupId>
		<artifactId>qos-models</artifactId>
		<version>VERSION</version>
	</dependency>
</dependencies>
```


##Usage

Have a look at test classes for more examples on the usage.

### Convert XML to Java Object

Example:
```java
import it.polimi.modaclouds.qos_models.util.XMLHelper;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
...
URL monitoringRulesURL = new URL("path/to/monitoringRules.xml");
MonitoringRules monitoringRules = XMLHelper.deserialize(monitoringRulesURL, MonitoringRules.class);
```

### Convert Java Object to XML

Example:
```java
import it.polimi.modaclouds.qos_models.util.XMLHelper;
import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
...
XMLHelper.serialize(monitoringRules, MonitoringRules.class, System.out);
```

### Automatic Generation of Monitoring Rules

Use the MonitoringRuleFactory class to generate monitoring rules from QoS constraints.

Example:
```java
import it.polimi.modaclouds.qos_models.monitoring_rules.MonitoringRuleFactory;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;
import it.polimi.modaclouds.qos_models.schema.Constraint;
...
MonitoringRuleFactory factory = new MonitoringRuleFactory();
MonitoringRule monitoringRule = factory.makeRuleFromConstraint(constraint);
```

### How to validate a monitoring rule

Use the RuleValidator class to validate rules.
