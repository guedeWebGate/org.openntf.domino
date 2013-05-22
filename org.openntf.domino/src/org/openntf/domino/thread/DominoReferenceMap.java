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
package org.openntf.domino.thread;

import java.util.Arrays;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class DominoReferenceMap.
 * 
 * @author nfreeman
 * 
 *         Experimental class to test whether a primitive-based reference counter would improve performance of iteration and auto-recycling.
 *         No apparent difference occured.
 */
public class DominoReferenceMap {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DominoReferenceMap.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NO_VALUE. */
	public static final int NO_VALUE = Integer.MIN_VALUE;
	
	/** The keys. */
	private int[] keys = new int[1024];
	{
		Arrays.fill(keys, NO_VALUE);
	}

	/** The values. */
	private int[] values = new int[1024];
	
	/** The last key. */
	private int lastKey = keys.length / 2 - 1;

	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * @return the int
	 */
	public int get(int key) {
		int hash = key & lastKey;
		if (keys[hash] == key)
			return values[hash];
		return NO_VALUE;
	}

	/**
	 * Increment and get.
	 * 
	 * @param key
	 *            the key
	 * @return the int
	 */
	public int incrementAndGet(int key) {
		int cur = get(key);
		put(key, ++cur);
		return cur;
	}

	/**
	 * Decrement and get.
	 * 
	 * @param key
	 *            the key
	 * @return the int
	 */
	public int decrementAndGet(int key) {
		int cur = get(key);
		if (cur == NO_VALUE) {
			put(key, 0);
			return 0;
		} else {
			put(key, --cur);
			return cur;
		}
	}

	/**
	 * Put.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void put(int key, int value) {
		int hash = key & lastKey;
		if (keys[hash] != NO_VALUE && keys[hash] != key) {
			resize();
			hash = key & lastKey;
			if (keys[hash] != NO_VALUE && keys[hash] != key)
				throw new UnsupportedOperationException("Unable to handle collision.");
		}
		keys[hash] = key;
		values[hash] = value;
	}

	/**
	 * Resize.
	 */
	private void resize() {
		int len2 = keys.length * 2;
		int[] keys2 = new int[len2];
		Arrays.fill(keys2, NO_VALUE);
		int[] values2 = new int[len2];
		lastKey = len2 - 1;
		for (int i = 0; i < keys.length; i++) {
			int key = keys[i];
			int value = values[i];
			if (key == NO_VALUE)
				continue;
			int hash = key & lastKey;
			if (keys2[hash] != NO_VALUE)
				throw new UnsupportedOperationException("Unable to handle collision.");
			keys2[hash] = key;
			values2[hash] = value;
		}
		keys = keys2;
		values = values2;
	}

	/**
	 * Clear.
	 */
	public void clear() {
		Arrays.fill(keys, NO_VALUE);
	}
}
