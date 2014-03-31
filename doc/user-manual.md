[Documentation table of contents](TOC.md) / User Manual

# User Manual

## What is a QoS Constraint?

TODO

## What is a Monitoring Rule?

TODO

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

Snapshots repository:
```xml
<repositories>
	...
	<repository>
        <id>deib-polimi-snapshots</id>
        <url>https://github.com/deib-polimi/deib-polimi-mvn-repo/raw/master/snapshots</url>
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