MODAClouds QoS Models
==========================

#Usage

The classes are automatically generated from the XML schemas in metamodels folder.

Add jaxb dependencies to your project:
```xml
<dependency>
	<groupId>javax.xml.bind</groupId>
	<artifactId>jaxb-api</artifactId>
	<version>2.2.11</version>
</dependency>
```

## Convert XML to Java Object

Example:
```java
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import it.polimi.modaclouds.qos_models.MonitoringRules;
...
URL xmlFileURL = new URL("https://raw.github.com/deib-polimi/modaclouds-qos-models/v1.0-alpha/metamodels/samples/monitoring_rules_example.xml");
JAXBContext jaxbContext = JAXBContext.newInstance(MonitoringRules.class);
Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
monitoringRules = (MonitoringRules) jaxbUnmarshaller.unmarshal(xmlFileURL);
```

Click [here](http://deib-polimi.github.io/modaclouds-qos-models/) to see the Javadoc.

#Maven repository

Add this to your repositories for releases:
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

Add this to your repositories for snapshots:
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