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
