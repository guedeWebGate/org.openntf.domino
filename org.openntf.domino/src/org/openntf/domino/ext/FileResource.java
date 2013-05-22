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

import java.io.InputStream;

import org.openntf.domino.Database;

// TODO: Auto-generated Javadoc
/**
 * The Interface FileResource.
 * 
 * @author withersp
 */
public interface FileResource {

	/**
	 * Gets the input stream.
	 * 
	 * @return File resource as InputStream
	 */
	public InputStream getInputStream();

	/**
	 * Gets the mime type.
	 * 
	 * @return mime type
	 */
	public String getMimeType();

	/**
	 * Gets the name.
	 * 
	 * @return file resource name
	 */
	public String getName();

	/**
	 * Gets the parent.
	 * 
	 * @return parent database
	 */
	public Database getParent();

	/**
	 * Checks if is hide from web.
	 * 
	 * @return whether hidden from web
	 */
	public boolean isHideFromWeb();

	/**
	 * Checks if is hide from notes.
	 * 
	 * @return whether hidden from notes
	 */
	public boolean isHideFromNotes();

	/**
	 * Checks if is needs refresh.
	 * 
	 * @return whether refresh flag is set
	 */
	public boolean isNeedsRefresh();

	/**
	 * Checks if is prevent changes.
	 * 
	 * @return whether prohibit design refresh is set
	 */
	public boolean isPreventChanges();

	/**
	 * Checks if is propagate prevent changes.
	 * 
	 * @return true, if is propagate prevent changes
	 */
	public boolean isPropagatePreventChanges();

	/**
	 * Checks if is read only.
	 * 
	 * @return true, if is read only
	 */
	public boolean isReadOnly();

}
