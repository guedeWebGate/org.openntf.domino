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

import java.util.Date;

import org.openntf.domino.exceptions.DataNotCompatibleException;
import org.openntf.domino.exceptions.ItemNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Document.
 * 
 * @author withersp
 */
public interface Document {

	/**
	 * Gets the created date.
	 * 
	 * @return the created date
	 */
	public Date getCreatedDate();

	/**
	 * Gets the initially modified date.
	 * 
	 * @return the initially modified date
	 */
	public Date getInitiallyModifiedDate();

	/**
	 * Gets the last accessed date.
	 * 
	 * @return the last accessed date
	 */
	public Date getLastAccessedDate();

	/**
	 * Gets the last modified date.
	 * 
	 * @return the last modified date
	 */
	public Date getLastModifiedDate();

	/**
	 * Gets the parent document.
	 * 
	 * @return parent document for responses / response-to-responses
	 */
	public Document getParentDocument();

	/**
	 * Checks if is dirty.
	 * 
	 * @return whether or not the document has been changed, used for transactions
	 */
	public boolean isDirty();

	/**
	 * Checks for mime entity.
	 * 
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public boolean hasMIMEEntity(String name);

	/**
	 * Gets the item value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param name
	 *            the name
	 * @param T
	 *            the t
	 * @return the item value
	 * @throws ItemNotFoundException
	 *             the item not found exception
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public <T> T getItemValue(String name, Class<?> T) throws ItemNotFoundException, DataNotCompatibleException;

}
