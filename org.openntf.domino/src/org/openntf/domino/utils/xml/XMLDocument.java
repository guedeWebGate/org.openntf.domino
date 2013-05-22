/*
 * Copyright OpenNTF 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package org.openntf.domino.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDocument.
 * 
 * @author jgallagher
 */
public class XMLDocument extends XMLNode {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8106159267601656260L;

	/**
	 * Instantiates a new xML document.
	 */
	public XMLDocument() {
	}

	/**
	 * Instantiates a new xML document.
	 * 
	 * @param node
	 *            the node
	 */
	public XMLDocument(Node node) {
		super(node);
	}

	/**
	 * Instantiates a new xML document.
	 * 
	 * @param xml
	 *            the xml
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 */
	public XMLDocument(String xml) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		this.node = builder.parse(xml);
	}

	/**
	 * Load url.
	 * 
	 * @param urlString
	 *            the url string
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 */
	public void loadURL(String urlString) throws SAXException, IOException, ParserConfigurationException {
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		this.node = builder.parse((InputStream) conn.getContent());
	}

	/**
	 * Load input stream.
	 * 
	 * @param is
	 *            the is
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 */
	public void loadInputStream(InputStream is) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		this.node = builder.parse(is);
	}

	/**
	 * Load string.
	 * 
	 * @param s
	 *            the s
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 */
	public void loadString(String s) throws SAXException, IOException, ParserConfigurationException {
		loadInputStream(new ByteArrayInputStream(s.getBytes()));
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.utils.xml.XMLNode#getXml()
	 */
	@Override
	public String getXml() throws IOException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat();
			format.setLineWidth(200);
			format.setIndenting(true);
			format.setIndent(2);
			XMLSerializer serializer = new XMLSerializer(bos, format);
			serializer.serialize(((Document) this.node).getDocumentElement());
			return new String(bos.toByteArray(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}