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
package org.openntf.domino.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openntf.domino.Document;
import org.openntf.domino.Item;
import org.openntf.domino.RichTextItem;
import org.openntf.domino.utils.DominoUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentScanner.
 */
public class DocumentScanner {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DocumentScanner.class.getName());

	/** The field token map_. */
	private Map<String, NavigableSet<String>> fieldTokenMap_;

	/** The field value map_. */
	private Map<String, NavigableSet<Comparable>> fieldValueMap_;

	/** The field type map_. */
	private Map<String, Integer> fieldTypeMap_;

	/** The stop token list_. */
	private Set<String> stopTokenList_;

	/** The token freq map_. */
	private NavigableMap<String, Integer> tokenFreqMap_;

	/** The ignore dollar_. */
	private boolean ignoreDollar_ = true;

	/**
	 * Instantiates a new document scanner.
	 */
	public DocumentScanner() {
		stopTokenList_ = Collections.emptySet();
	}

	/**
	 * Instantiates a new document scanner.
	 * 
	 * @param stopTokenList
	 *            the stop token list
	 */
	public DocumentScanner(Set<String> stopTokenList) {
		stopTokenList_ = stopTokenList;
	}

	/**
	 * Sets the ignore dollar.
	 * 
	 * @param ignore
	 *            the new ignore dollar
	 */
	public void setIgnoreDollar(boolean ignore) {
		ignoreDollar_ = ignore;
	}

	/**
	 * Gets the ignore dollar.
	 * 
	 * @return the ignore dollar
	 */
	public boolean getIgnoreDollar() {
		return ignoreDollar_;
	}

	/**
	 * Gets the field token map.
	 * 
	 * @return the field token map
	 */
	public Map<String, NavigableSet<String>> getFieldTokenMap() {
		if (fieldTokenMap_ == null) {
			fieldTokenMap_ = new HashMap<String, NavigableSet<String>>();
		}
		return fieldTokenMap_;
	}

	/**
	 * Gets the field value map.
	 * 
	 * @return the field value map
	 */
	public Map<String, NavigableSet<Comparable>> getFieldValueMap() {
		if (fieldValueMap_ == null) {
			fieldValueMap_ = new HashMap<String, NavigableSet<Comparable>>();
		}
		return fieldValueMap_;
	}

	/**
	 * Gets the field type map.
	 * 
	 * @return the field type map
	 */
	public Map<String, Integer> getFieldTypeMap() {
		if (fieldTypeMap_ == null) {
			fieldTypeMap_ = new HashMap<String, Integer>();
		}
		return fieldTypeMap_;
	}

	/**
	 * Gets the token freq map.
	 * 
	 * @return the token freq map
	 */
	public NavigableMap<String, Integer> getTokenFreqMap() {
		if (tokenFreqMap_ == null) {
			tokenFreqMap_ = new ConcurrentSkipListMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
		}
		return tokenFreqMap_;
	}

	/**
	 * Process document.
	 * 
	 * @param doc
	 *            the doc
	 */
	@SuppressWarnings("rawtypes")
	public void processDocument(Document doc) {

		Map<String, NavigableSet<String>> tmap = getFieldTokenMap();
		Map<String, NavigableSet<Comparable>> vmap = getFieldValueMap();
		Map<String, Integer> typeMap = getFieldTypeMap();
		Map<String, Integer> tfmap = getTokenFreqMap();
		Vector<Item> items = doc.getItems();
		for (Item item : items) {
			try {
				String name = item.getName();
				if (name.startsWith("$") && getIgnoreDollar())
					break;
				if (!typeMap.containsKey(name)) {
					typeMap.put(name, item.getType());
				}
				if (typeMap.get(name).equals(item.getType())) {
					Vector<Object> vals = null;
					vals = item.getValues();
					if (vals != null && !vals.isEmpty()) {
						NavigableSet<Comparable> valueSet = null;
						if (!vmap.containsKey(name)) {
							valueSet = new ConcurrentSkipListSet<Comparable>();
							vmap.put(name, valueSet);
						} else {
							valueSet = vmap.get(name);
						}
						java.util.Collection<Comparable> c = DominoUtils.toComparable(vals);
						if (!c.isEmpty()) {
							valueSet.addAll(c);
						}
					}
				}
				String value = null;
				switch (item.getType()) {
				case Item.TEXT:
					value = item.getValueString();
					break;
				case Item.RICHTEXT:
					value = ((RichTextItem) item).getUnformattedText();
					break;
				default:

				}
				if (value != null && value.length() > 0 && !DominoUtils.isNumber(value)) {
					NavigableSet<String> tokenSet = null;

					if (!tmap.containsKey(item.getName())) {
						tokenSet = new ConcurrentSkipListSet<String>(String.CASE_INSENSITIVE_ORDER);
						tmap.put(name, tokenSet);
					} else {
						tokenSet = tmap.get(name);
					}

					Scanner s = new Scanner(value);

					while (s.hasNext()) {
						String token = s.next();
						token = token.replaceAll("\\W*$", "");
						token = token.replaceAll("^\\W*", "");
						token = token.trim();
						if ((token.length() > 2) && !(stopTokenList_.contains(token))) {
							tokenSet.add(token);
							if (tfmap.containsKey(token)) {
								tfmap.put(token, tfmap.get(token) + 1);
							} else {
								tfmap.put(token, 1);
							}
						}
					}
				}
			} catch (Exception e) {
				log_.log(Level.WARNING, "Unable to scan next item in Document " + doc.getNoteID() + " in database "
						+ doc.getAncestorDatabase().getFilePath());
			}

		}
	}

	/**
	 * Sets the field token map.
	 * 
	 * @param fieldTokenMap
	 *            the field token map
	 */
	public void setFieldTokenMap(Map<String, NavigableSet<String>> fieldTokenMap) {
		fieldTokenMap_ = fieldTokenMap;
	}

	/**
	 * Sets the field value map.
	 * 
	 * @param fieldValueMap
	 *            the field value map
	 */
	public void setFieldValueMap(Map<String, NavigableSet<Comparable>> fieldValueMap) {
		fieldValueMap_ = fieldValueMap;
	}

	/**
	 * Sets the field type map.
	 * 
	 * @param fieldTypeMap
	 *            the field type map
	 */
	public void setFieldTypeMap(Map<String, Integer> fieldTypeMap) {
		fieldTypeMap_ = fieldTypeMap;
	}

	/**
	 * Sets the token freq map.
	 * 
	 * @param tokenFreqMap
	 *            the token freq map
	 */
	public void setTokenFreqMap(NavigableMap<String, Integer> tokenFreqMap) {
		tokenFreqMap_ = tokenFreqMap;
	}

}
