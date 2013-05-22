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
package org.openntf.domino.exceptions;

import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemNotFoundException.
 */
public class ItemNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(ItemNotFoundException.class.getName());

	/**
	 * Instantiates a new item not found exception.
	 * 
	 * @param message
	 *            the message
	 */
	public ItemNotFoundException(String message) {
		super(message);
	}
}
