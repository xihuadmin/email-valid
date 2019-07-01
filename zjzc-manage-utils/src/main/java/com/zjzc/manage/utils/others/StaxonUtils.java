package com.zjzc.manage.utils.others;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

public class StaxonUtils {
	
	/**
	 * json格式的数据转换为xml
	 * @param json
	 * @return
	 */
	public static String json2xml(String json) {
		StringReader input = new StringReader(json);
		StringWriter output = new StringWriter();
		JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false)
				.repairingNamespaces(false).build();
		try {
			XMLEventReader reader = new JsonXMLInputFactory(config)
					.createXMLEventReader(input);
			XMLEventWriter writer = XMLOutputFactory.newInstance()
					.createXMLEventWriter(output);
			writer = new PrettyXMLEventWriter(writer);
			writer.add(reader);
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (output.toString().length() >= 38) {

			return output.toString().substring(39);
		}
		return output.toString();
	}

	/**
	 * xml格式的数据转换为json
	 * @param xml
	 * @param prettyFlag - 是否美化
	 * @return
	 */
	public static String xml2json(String xml, boolean prettyFlag) {
		StringReader input = new StringReader(xml);
		StringWriter output = new StringWriter();
		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true)
				.autoPrimitive(true).prettyPrint(prettyFlag).build();
		try {
			XMLEventReader reader = XMLInputFactory.newInstance()
					.createXMLEventReader(input);
			XMLEventWriter writer = new JsonXMLOutputFactory(config)
					.createXMLEventWriter(output);
			writer.add(reader);
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output.toString();
	}
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><Pub><Ver>A1.0</Ver></Pub></root>";
		String jsonStr = StaxonUtils.xml2json(xml, true);
		System.out.println(jsonStr);
	}
}
