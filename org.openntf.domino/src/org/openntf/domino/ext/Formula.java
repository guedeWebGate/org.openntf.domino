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
package org.openntf.domino.ext;

import java.io.Externalizable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Interface Formula.
 * 
 * @author nfreeman
 */
public interface Formula extends Externalizable {
	
	/**
	 * Sets the session.
	 * 
	 * @param session
	 *            the new session
	 */
	public void setSession(org.openntf.domino.Session session);

	/**
	 * Sets the expression.
	 * 
	 * @param expression
	 *            the new expression
	 */
	public void setExpression(String expression);

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@SuppressWarnings("rawtypes")
	public Vector getValue();

	/**
	 * Gets the value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param T
	 *            the t
	 * @return the value
	 */
	public <T> T getValue(Class<?> T);

	/**
	 * Gets the value.
	 * 
	 * @param session
	 *            the session
	 * @return the value
	 */
	@SuppressWarnings("rawtypes")
	public Vector getValue(org.openntf.domino.Session session);

	/**
	 * Gets the value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param session
	 *            the session
	 * @param T
	 *            the t
	 * @return the value
	 */
	public <T> T getValue(org.openntf.domino.Session session, Class<?> T);

	/**
	 * Gets the value.
	 * 
	 * @param document
	 *            the document
	 * @return the value
	 */
	@SuppressWarnings("rawtypes")
	public Vector getValue(org.openntf.domino.Document document);

	/**
	 * Gets the value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param document
	 *            the document
	 * @param T
	 *            the t
	 * @return the value
	 */
	public <T> T getValue(org.openntf.domino.Document document, Class<?> T);

}
