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
package it.polimi.modaclouds.qos_models.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.jxpath.JXPathContext;
import org.xml.sax.SAXException;

public class XMLHelper {

	public static <T> T getElementByID(Collection<T> elements, String id) {
		for (T element : elements) {
			if (JXPathContext.newContext(element).getValue("id").equals(id))
				return element;
		}
		return null;
	}

	public static <T> boolean containsId(Collection<T> elements, String id) {
		return getElementByID(elements, id) != null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(URL xmlUrl, Class<T> targetClass)
			throws JAXBException, SAXException {
		// SchemaFactory schemaFactory =
		// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// Schema schema = schemaFactory.newSchema();
		Unmarshaller unmarshaller = JAXBContext.newInstance(targetClass)
				.createUnmarshaller();
		// unmarshaller.setSchema(schema);
		T object = (T) unmarshaller.unmarshal(xmlUrl);
		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(FileInputStream xmlPath,
			Class<T> targetClass) throws JAXBException, SAXException {
		// SchemaFactory schemaFactory =
		// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// Schema schema = schemaFactory.newSchema();
		Unmarshaller unmarshaller = JAXBContext.newInstance(targetClass)
				.createUnmarshaller();
		// unmarshaller.setSchema(schema);
		T object = (T) unmarshaller.unmarshal(xmlPath);
		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(InputStream resourceAsStream,
			Class<T> targetClass) throws JAXBException {
		T object = (T) JAXBContext.newInstance(targetClass)
				.createUnmarshaller().unmarshal(resourceAsStream);
		return object;
	}
	
	@Deprecated
	public static <T> void serialize(T object, Class<T> objectClass, OutputStream resultStream)
			throws JAXBException {
		serialize(object, resultStream);
	}
	
	@Deprecated
	public static <T> void serialize(T object, Class<T> objectClass, OutputStream resultStream, String schemaLocation)
			throws JAXBException {
		serialize(object, resultStream, schemaLocation);
	}

	public static <T> void serialize(T object, OutputStream resultStream)
			throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(object, resultStream);
	}

	public static <T> void serialize(T object, OutputStream resultStream,
			String schemaLocation) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				schemaLocation);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(object, resultStream);
	}

	public static <T> String serialize(T object) throws JAXBException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		serialize(object, outputStream);
		return outputStream.toString();
	}

	public static <T> ValidationResult validate(URL xmlUrl, Class<T> targetClass) {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Unmarshaller unmarshaller;
		ValidationResult result;
		try {
			Schema schema = schemaFactory.newSchema();
			unmarshaller = JAXBContext.newInstance(targetClass)
					.createUnmarshaller();
			unmarshaller.setSchema(schema);
		} catch (SAXException | JAXBException e) {
			result = new ValidationResult(false);
			result.addMessage("Error occured in creating the schema");
			result.addMessage(e.getLocalizedMessage());
			return result;
		}
		try {
			unmarshaller.unmarshal(xmlUrl);
		} catch (JAXBException e) {
			result = new ValidationResult(false);
			if (e.getMessage() != null)
				result.addMessage(e.getLocalizedMessage());
			if (e.getLinkedException() != null
					&& e.getLinkedException().getLocalizedMessage() != null)
				result.addMessage(e.getLinkedException().getLocalizedMessage());
			return result;
		}
		return new ValidationResult(true);

	}

	public static <T> ValidationResult validate(FileInputStream xmlPath,
			Class<T> targetClass) {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Unmarshaller unmarshaller;
		ValidationResult result;
		try {
			Schema schema = schemaFactory.newSchema();
			unmarshaller = JAXBContext.newInstance(targetClass)
					.createUnmarshaller();
			unmarshaller.setSchema(schema);
		} catch (SAXException | JAXBException e) {
			result = new ValidationResult(false);
			result.addMessage("Error occured in creating the schema");
			result.addMessage(e.getLocalizedMessage());
			return result;
		}
		try {
			unmarshaller.unmarshal(xmlPath);
		} catch (JAXBException e) {
			result = new ValidationResult(false);
			if (e.getMessage() != null)
				result.addMessage(e.getLocalizedMessage());
			if (e.getLinkedException() != null
					&& e.getLinkedException().getLocalizedMessage() != null)
				result.addMessage(e.getLinkedException().getLocalizedMessage());
			return result;
		}
		return new ValidationResult(true);

	}

	public static <T> ValidationResult validate(InputStream xmlStream,
			Class<T> targetClass) {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Unmarshaller unmarshaller;
		ValidationResult result;
		try {
			Schema schema = schemaFactory.newSchema();
			unmarshaller = JAXBContext.newInstance(targetClass)
					.createUnmarshaller();
			unmarshaller.setSchema(schema);
		} catch (SAXException | JAXBException e) {
			result = new ValidationResult(false);
			result.addMessage("Error occured in creating the schema");
			result.addMessage(e.getLocalizedMessage());
			return result;
		}
		try {
			unmarshaller.unmarshal(xmlStream);
		} catch (JAXBException e) {
			result = new ValidationResult(false);
			if (e.getMessage() != null)
				result.addMessage(e.getLocalizedMessage());
			if (e.getLinkedException() != null
					&& e.getLinkedException().getLocalizedMessage() != null)
				result.addMessage(e.getLinkedException().getLocalizedMessage());
			return result;
		}
		return new ValidationResult(true);

	}

	// /**
	// * Gets the XML block corresponding to <tt>id</tt> at the given
	// * <tt>pathToBlock</tt> (xpath format) from <tt>xmlText</tt>
	// *
	// * @param xmlText
	// * The XML string representation
	// * @param id
	// * The ID identifying the desired block
	// * @param pathToBlock
	// * the path to the XML block in xpath format
	// * @return the string representation of the desired XML block
	// */
	// public static String getXMLBlockById(String xmlText, String id,
	// String pathToBlock) {
	// String xmlBlock = null;
	// Document doc = convertXMLStringToXMLDocument(xmlText);
	// try {
	//
	// XPath xpath = XPathFactory.newInstance().newXPath();
	// String expression = pathToBlock + "[@id='" + id + "']";
	// Node node;
	// node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
	// TransformerFactory transFactory = TransformerFactory.newInstance();
	// Transformer transformer = transFactory.newTransformer();
	// StringWriter buffer = new StringWriter();
	// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
	// "yes");
	// transformer
	// .transform(new DOMSource(node), new StreamResult(buffer));
	// xmlBlock = buffer.toString();
	// } catch (XPathExpressionException | TransformerException e) {
	// e.printStackTrace();
	// }
	// return xmlBlock;
	// }
	//
	// /**
	// * Converts an XML string representation into a org.w3c.dom.Document
	// *
	// * @param monitoringRulesXMLString
	// * the XML string representation to be converted
	// * @return the XML as a org.w3c.dom.Document object
	// */
	// public static Document convertXMLStringToXMLDocument(
	// String monitoringRulesXMLString) {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder builder;
	// try {
	// builder = factory.newDocumentBuilder();
	// Document document = builder.parse(new InputSource(new StringReader(
	// monitoringRulesXMLString)));
	// return document;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// /**
	// * Gets the XML string representation from an XML file at the given URL,
	// * decoding using the specified charset.
	// *
	// * @param urlToXMLFile
	// * @param charsetName
	// * @return
	// * @throws URISyntaxException
	// * @throws UnsupportedEncodingException
	// * @throws IOException
	// */
	// public static String getXMLText(URL urlToXMLFile, String charsetName)
	// throws URISyntaxException, UnsupportedEncodingException,
	// IOException {
	// Path resPath = Paths.get(urlToXMLFile.toURI());
	// return new String(Files.readAllBytes(resPath), charsetName);
	// }
	//
	// public static String getAttributeValue(String attributeName, String
	// xmlBlock) {
	// Document xmlBlockDoc = convertXMLStringToXMLDocument(xmlBlock);
	// return xmlBlockDoc.getAttributes().getNamedItem(attributeName)
	// .getNodeValue();
	// }
	//
	// public static List<String> getXMLBlocks(String xmlText, String path)
	// throws XPathExpressionException {
	// List<String> blocks = new ArrayList<String>();
	// Document xmlTextDoc = convertXMLStringToXMLDocument(xmlText);
	//
	// XPath xpath = XPathFactory.newInstance().newXPath();
	// String expression = path;
	// NodeList nodes;
	// nodes = (NodeList) xpath.evaluate(expression, xmlTextDoc,
	// XPathConstants.NODESET);
	// TransformerFactory transFactory = TransformerFactory.newInstance();
	// Transformer transformer = null;
	// try {
	// transformer = transFactory.newTransformer();
	//
	// for (int i = 0; i < nodes.getLength(); i++) {
	// StringWriter buffer = new StringWriter();
	// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
	// "yes");
	// transformer.transform(new DOMSource(nodes.item(i)),
	// new StreamResult(buffer));
	// blocks.add(buffer.toString());
	// }
	// } catch (TransformerException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return blocks;
	// }

}
