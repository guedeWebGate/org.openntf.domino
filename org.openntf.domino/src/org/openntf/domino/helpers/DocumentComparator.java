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

import java.util.Comparator;
import java.util.logging.Logger;

import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentComparator.
 * 
 * @author nfreeman
 * 
 *         Copyright Michael Zischeck and licensed under Apache License 2.0 from http://in-mood.blogspot.com/
 */
public class DocumentComparator implements Comparator<Document> {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DocumentComparator.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sort fields. */
	String[] sortFields = null;

	/**
	 * Instantiates a new document comparator.
	 * 
	 * @param sortFields
	 *            the sort fields
	 */
	public DocumentComparator(String... sortFields) {
		this.sortFields = sortFields;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Document doc1, Document doc2) {

		int compared = 0;
		// loop all sortFields
		for (String field : sortFields) {
			Item item1 = doc1.getFirstItem(field);
			Item item2 = doc2.getFirstItem(field);
			switch (item1.getType()) {
			case Item.TEXT:
			case Item.AUTHORS:
			case Item.NAMES:
			case Item.READERS:
				String val1 = doc1.getItemValueString(field);
				String val2 = doc2.getItemValueString(field);
				compared = val1.compareTo(val2);
				if (0 != compared) {
					return compared;
				}
				break;
			case Item.NUMBERS:
				Double d1 = doc1.getItemValueDouble(field);
				Double d2 = doc2.getItemValueDouble(field);
				compared = d1.compareTo(d2);
				if (0 != compared) {
					return compared;
				}
				break;

			case Item.DATETIMES:

				DateTime dt1 = item1.getDateTimeValue();
				DateTime dt2 = item2.getDateTimeValue();
				compared = dt2.timeDifference(dt1);
				if (0 != compared) {
					return compared;
				}
				break;
			}

		}
		return 0;
	}
}
