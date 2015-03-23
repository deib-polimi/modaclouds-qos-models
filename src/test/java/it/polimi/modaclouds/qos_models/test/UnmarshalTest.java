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
package it.polimi.modaclouds.qos_models.test;

import it.polimi.modaclouds.qos_models.schema.MonitoringRules;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;


public class UnmarshalTest {

	@Test
	public void test() throws JAXBException, SAXException {
		InputStream testRulesStream = getClass().getResourceAsStream("/MonitoringRules.xml");
		XMLHelper.deserialize(testRulesStream, MonitoringRules.class);
	}

}
