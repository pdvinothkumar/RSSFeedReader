package com.xyz.business;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.xyz.modal.FeedPrototype;

public class RSSFeedReader {

	public static String readRSS(String urlAddress) {

		URL url = null;
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;

		try {

			url = new URL(urlAddress);

			factory = DocumentBuilderFactory.newInstance();

			builder = factory.newDocumentBuilder();

			Document document = builder.parse(url.openStream());

			NodeList nodes = document.getElementsByTagName(new FeedPrototype().toString());

			Path path = Paths.get("D:/feed.txt");

			BufferedWriter writer = Files.newBufferedWriter(path);

			String newLine = System.getProperty("line.separator");

			for (int temp = 0; temp < nodes.getLength(); temp++) {

				Node nNode = nodes.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					for (String list : FeedPrototype.list) {

						String tagName = eElement.getElementsByTagName(list).item(0).getNodeName();

						System.out.println(
								tagName + " : " + (eElement.getElementsByTagName(list).item(0).getTextContent())
										.replaceAll("(?i)NewsPicks", ""));

						writer.write(tagName + " : " + (eElement.getElementsByTagName(list).item(0).getTextContent())
								.replaceAll("(?i)NewsPicks", "") + newLine);
					}
				}

			}

			writer.close();

		} catch (MalformedURLException e) {
			System.err.println("Malformed URL");
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			System.err.println("Error while reading the document tree");
		} catch (IOException e) {
			System.err.println("Error while writing the contents into file");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return "OK";

	}

}
