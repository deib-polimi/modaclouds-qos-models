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

import static org.junit.Assert.*;
import it.polimi.modaclouds.qos_models.schema.MultiCloudExtensions;
import it.polimi.modaclouds.qos_models.schema.ResourceModelExtension;
import it.polimi.modaclouds.qos_models.schema.UsageModelExtensions;
import it.polimi.modaclouds.qos_models.util.ValidationResult;
import it.polimi.modaclouds.qos_models.util.XMLHelper;

import java.io.InputStream;

import org.junit.Test;

public class ExtensionValidation {


	@Test
	public void usageModeExtensionlShouldValidate() {
		InputStream extensionStream = getClass().getResourceAsStream(
				"/usageModelExtension.xml");
		ValidationResult validation = XMLHelper.validate(extensionStream,
				UsageModelExtensions.class);
		if (validation == null || !validation.isValid()) {
			if (validation.getMessages() != null)
				for (String message : validation.getMessages()) {
					System.out.println(message);
				}
			fail();
		}
	}
	
	@Test
	public void multiCloudExtensionShouldValidate() {
		InputStream extensionStream = getClass().getResourceAsStream(
				"/multiCloudExtension.xml");
		ValidationResult validation = XMLHelper.validate(extensionStream,
				MultiCloudExtensions.class);
		if (validation == null || !validation.isValid()) {
			if (validation.getMessages() != null)
				for (String message : validation.getMessages()) {
					System.out.println(message);
				}
			fail();
		}
	}
	
	
	@Test
	public void resourceEnvironmentShouldValidate() {
		InputStream extensionStream = getClass().getResourceAsStream(
				"/resourceEnvironmentExtension.xml");
		ValidationResult validation = XMLHelper.validate(extensionStream,
				ResourceModelExtension.class);
		if (validation == null || !validation.isValid()) {
			if (validation.getMessages() != null)
				for (String message : validation.getMessages()) {
					System.out.println(message);
				}
			fail();
		}
	}

	@Test
	public void oldResourceEnvironmentShouldNotValidate() {
		InputStream extensionStream = getClass().getResourceAsStream(
				"/oldResourceEnvironmentExtension.xml");
		ValidationResult validation = XMLHelper.validate(extensionStream,
				ResourceModelExtension.class);
		if (validation == null || validation.isValid()) {
			if (validation.getMessages() != null)
				for (String message : validation.getMessages()) {
					System.out.println(message);
				}
			fail();
		}
	}

}
