/**
 * 
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

/**
 * @author jgallagher
 * 
 */
public class XMLDocument extends XMLNode {
	private static final long serialVersionUID = -8106159267601656260L;

	public XMLDocument() {
	}

	public XMLDocument(final Node node) {
		super(node);
	}

	public XMLDocument(final String xml) throws SAXException, IOException, ParserConfigurationException {
		loadString(xml);
	}

	public XMLNode getDocumentElement() {
		return new XMLNode(((Document) this.node).getDocumentElement());
	}

	public void loadURL(final String urlString) throws SAXException, IOException, ParserConfigurationException {
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		this.node = getBuilder().parse((InputStream) conn.getContent());
	}

	public void loadInputStream(final InputStream is) throws SAXException, IOException, ParserConfigurationException {
		this.node = getBuilder().parse(is);
	}

	public void loadString(final String s) throws SAXException, IOException, ParserConfigurationException {
		loadInputStream(new ByteArrayInputStream(s.getBytes("UTF-8")));
	}

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

	private DocumentBuilder getBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		fac.setValidating(false);
		// fac.setNamespaceAware(true);
		return fac.newDocumentBuilder();
	}
}