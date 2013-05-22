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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLNode.
 * 
 * @author jgallagher
 */
public class XMLNode implements Map<String, Object>, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2304991412510751453L;

	/** The node. */
	protected org.w3c.dom.Node node = null;
	
	/** The x path. */
	private transient XPath xPath = null;
	
	/** The get results. */
	private Map<String, Object> getResults = new HashMap<String, Object>();

	/**
	 * Instantiates a new xML node.
	 */
	protected XMLNode() {
	}

	/**
	 * Instantiates a new xML node.
	 * 
	 * @param node
	 *            the node
	 */
	public XMLNode(org.w3c.dom.Node node) {
		this.node = node;
	}

	/**
	 * Select single node.
	 * 
	 * @param xpathString
	 *            the xpath string
	 * @return the xML node
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 */
	public XMLNode selectSingleNode(String xpathString) throws XPathExpressionException {
		List<XMLNode> result = this.selectNodes(xpathString);
		return result.size() == 0 ? null : result.get(0);
	}

	/**
	 * Select nodes.
	 * 
	 * @param xpathString
	 *            the xpath string
	 * @return the list
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 */
	public List<XMLNode> selectNodes(String xpathString) throws XPathExpressionException {

		NodeList nodes = (NodeList) this.getXPath().compile(xpathString).evaluate(node, XPathConstants.NODESET);
		List<XMLNode> result = new XMLNodeList(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			result.add(new XMLNode(nodes.item(i)));
		}

		return result;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @return the attribute
	 */
	public String getAttribute(String attribute) {
		if (this.node == null) {
			return "";
		}
		NamedNodeMap attributes = this.node.getAttributes();
		Node attr = attributes.getNamedItem(attribute);
		if (attr == null) {
			return "";
		}
		return attr.getTextContent();
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 */
	public void setAttribute(String attribute, String value) {
		Node attr = this.node.getAttributes().getNamedItem(attribute);
		if (attr == null) {
			attr = getDocument().createAttribute(attribute);
		}
		attr.setNodeValue(value);
		this.node.getAttributes().setNamedItem(attr);
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		if (node == null) {
			return "";
		}
		return node.getTextContent();
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		if (node == null) {
			return;
		}
		node.setTextContent(text);
	}

	/**
	 * Gets the text content.
	 * 
	 * @return the text content
	 */
	public String getTextContent() {
		return this.getText();
	}

	/**
	 * Sets the text content.
	 * 
	 * @param textContent
	 *            the new text content
	 */
	public void setTextContent(String textContent) {
		this.setText(textContent);
	}

	/**
	 * Gets the node value.
	 * 
	 * @return the node value
	 */
	public String getNodeValue() {
		if (node == null) {
			return "";
		}
		return node.getNodeValue();
	}

	/**
	 * Sets the node value.
	 * 
	 * @param value
	 *            the new node value
	 */
	public void setNodeValue(String value) {
		if (node == null) {
			return;
		}
		node.setNodeValue(value);
	}

	/**
	 * Adds the child element.
	 * 
	 * @param elementName
	 *            the element name
	 * @return the xML node
	 */
	public XMLNode addChildElement(String elementName) {
		Node node = this.getDocument().createElement(elementName);
		this.node.appendChild(node);
		return new XMLNode(node);
	}

	/**
	 * Insert child element before.
	 * 
	 * @param elementName
	 *            the element name
	 * @param refNode
	 *            the ref node
	 * @return the xML node
	 */
	public XMLNode insertChildElementBefore(String elementName, XMLNode refNode) {
		Node node = this.getDocument().createElement(elementName);
		this.node.insertBefore(node, refNode.getNode());
		return new XMLNode(node);
	}

	/**
	 * Gets the first child.
	 * 
	 * @return the first child
	 */
	public XMLNode getFirstChild() {
		Node node = this.getNode().getFirstChild();
		if (node != null) {
			return new XMLNode(node);
		}
		return null;
	}

	/**
	 * Gets the parent node.
	 * 
	 * @return the parent node
	 */
	public XMLNode getParentNode() {
		Node node = this.getNode().getParentNode();
		if (node != null) {
			return new XMLNode(node);
		}
		return null;
	}

	/**
	 * Removes the child.
	 * 
	 * @param childNode
	 *            the child node
	 */
	public void removeChild(XMLNode childNode) {
		this.getNode().removeChild(childNode.getNode());
	}

	/**
	 * Gets the next sibling.
	 * 
	 * @return the next sibling
	 */
	public XMLNode getNextSibling() {
		Node node = this.getNode().getNextSibling();
		if (node != null) {
			return new XMLNode(node);
		}
		return null;
	}

	/**
	 * Append child.
	 * 
	 * @param node
	 *            the node
	 */
	public void appendChild(XMLNode node) {
		this.getNode().appendChild(node.getNode());
	}

	/**
	 * Insert before.
	 * 
	 * @param newChild
	 *            the new child
	 * @param refChild
	 *            the ref child
	 */
	public void insertBefore(XMLNode newChild, XMLNode refChild) {
		this.getNode().insertBefore(newChild.getNode(), refChild.getNode());
	}

	/**
	 * Gets the node.
	 * 
	 * @return the node
	 */
	public org.w3c.dom.Node getNode() {
		return this.node;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object arg0) {
		String path = String.valueOf(arg0);

		if (path.equals("nodeValue")) {
			return this.getNode().getNodeValue();
		} else if (path.equals("textContent")) {
			return this.getNode().getTextContent();
		}

		if (!this.getResults.containsKey(path)) {
			try {
				List<XMLNode> nodes = this.selectNodes(path);
				if (nodes.size() == 1) {
					// this.getResults.put(path, nodes.get(0).getNode());
					this.getResults.put(path, nodes.get(0));
				} else {
					this.getResults.put(path, nodes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.getResults.get(path);
	}

	/**
	 * Gets the xml.
	 * 
	 * @return the xml
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getXml() throws IOException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat();
			format.setLineWidth(200);
			format.setIndenting(true);
			format.setIndent(2);
			XMLSerializer serializer = new XMLSerializer(bos, format);
			serializer.serialize((Element) this.node);
			return new String(bos.toByteArray(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the x path.
	 * 
	 * @return the x path
	 */
	private XPath getXPath() {
		if (this.xPath == null) {
			xPath = XPathFactory.newInstance().newXPath();
		}
		return this.xPath;
	}

	/**
	 * Gets the document.
	 * 
	 * @return the document
	 */
	private Document getDocument() {
		return this.node.getOwnerDocument();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	public void clear() {
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object arg0) {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object arg0) {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String arg0, Object arg1) {
		if (arg0.equals("nodeValue")) {
			this.getNode().setNodeValue(String.valueOf(arg1));
			return arg1;
		} else if (arg0.equals("textContent")) {
			this.getNode().setNodeValue(String.valueOf(arg1));
			return arg1;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Object> arg0) {
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object arg0) {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	public int size() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	public Collection<Object> values() {
		return null;
	}
}