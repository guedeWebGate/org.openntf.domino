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
package org.openntf.domino.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.openntf.domino.Database;
import org.openntf.domino.Document;
import org.openntf.domino.Item;

import com.tinkerpop.blueprints.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class DominoElement.
 */
public abstract class DominoElement implements Element, Serializable {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DominoElement.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant TYPE_FIELD. */
	public static final String TYPE_FIELD = "_OPEN_GRAPHTYPE";
	
	/** The doc_. */
	transient org.openntf.domino.Document doc_;
	
	/** The key_. */
	private String key_;
	
	/** The parent_. */
	transient DominoGraph parent_;
	
	/** The unid_. */
	private String unid_;

	/**
	 * Instantiates a new domino element.
	 * 
	 * @param parent
	 *            the parent
	 * @param doc
	 *            the doc
	 */
	public DominoElement(DominoGraph parent, Document doc) {
		doc_ = doc;
		parent_ = parent;
		unid_ = doc.getUniversalID();
	}

	/**
	 * Adds the property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 */
	public void addProperty(String propertyName, Object value) {
		setProperty(propertyName, value);
	}

	/**
	 * Gets the database.
	 * 
	 * @return the database
	 */
	private Database getDatabase() {
		return getParent().getRawDatabase();
	}

	/**
	 * Gets the raw document.
	 * 
	 * @return the raw document
	 */
	public Document getRawDocument() {
		if (doc_ == null) {
			doc_ = getDocument();
		}
		return doc_;
	}

	/**
	 * Gets the raw id.
	 * 
	 * @return the raw id
	 */
	public String getRawId() {
		String prefix = getDatabase().getServer() + "!!" + getDatabase().getFilePath();
		return prefix + ": " + getRawDocument().getNoteID();
	}

	/**
	 * Increment property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the int
	 */
	public int incrementProperty(String propertyName) {
		Integer result = getProperty(propertyName, Integer.class);
		if (result == null)
			result = 0;
		setProperty(propertyName, ++result);
		return result;
	}

	/**
	 * Decrement property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the int
	 */
	public int decrementProperty(String propertyName) {
		Integer result = getProperty(propertyName, Integer.class);
		if (result == null)
			result = 0;
		setProperty(propertyName, --result);
		return result;
	}

	/**
	 * Gets the document.
	 * 
	 * @return the document
	 */
	private Document getDocument() {
		return getDatabase().getDocumentByKey(unid_, true);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getId()
	 */
	@Override
	public String getId() {
		if (key_ == null) {
			key_ = unid_;
		}
		return key_;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public DominoGraph getParent() {
		return parent_;
	}

	/**
	 * Checks for property.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public boolean hasProperty(String key) {
		return getPropertyKeys().contains(key);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getProperty(java.lang.String)
	 */
	@Override
	public <T> T getProperty(String key) {
		return getProperty(key, Object.class);
	}

	/**
	 * Gets the property.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param propertyName
	 *            the property name
	 * @param T
	 *            the t
	 * @return the property
	 */
	public <T> T getProperty(String propertyName, Class<?> T) {
		return getRawDocument().getItemValue(propertyName, T);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getPropertyKeys()
	 */
	@Override
	public Set<String> getPropertyKeys() {
		Set<String> result = new HashSet<String>();
		for (Item i : getRawDocument().getItems()) {
			result.add(i.getName());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#remove()
	 */
	@Override
	public void remove() {
		getParent().startTransaction();
		getRawDocument().removePermanently(true);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#removeProperty(java.lang.String)
	 */
	@Override
	public <T> T removeProperty(String key) {
		getParent().startTransaction();
		T result = getProperty(key);
		getRawDocument().removeItem(key);
		return result;
	}

	// public void save() {
	// getRawDocument().save();
	// }

	/**
	 * Sets the raw document.
	 * 
	 * @param doc
	 *            the new raw document
	 */
	public void setRawDocument(org.openntf.domino.Document doc) {
		doc_ = doc;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String propertyName, java.lang.Object value) {
		getParent().startTransaction();
		getRawDocument().replaceItemValue(propertyName, value);
	}

}
