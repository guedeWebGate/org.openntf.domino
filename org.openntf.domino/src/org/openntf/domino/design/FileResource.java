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
package org.openntf.domino.design;

// TODO: Auto-generated Javadoc
/**
 * The Interface FileResource.
 */
public interface FileResource extends DesignBase, org.openntf.domino.types.DatabaseDescendant {

	/**
	 * Gets the file data.
	 * 
	 * @return the file resource's data as a byte array
	 */
	public byte[] getFileData();

	/**
	 * Sets the file data.
	 * 
	 * @param fileData
	 *            The new data for the file resource, as a byte array
	 */
	public void setFileData(byte[] fileData);

	/**
	 * Gets the mime type.
	 * 
	 * @return mime type
	 */
	public String getMimeType();

	/**
	 * Sets the mime type.
	 * 
	 * @param mimeType
	 *            The new MIME type for the file resource
	 */
	public void setMimeType(String mimeType);

	/**
	 * Checks if is read only.
	 * 
	 * @return whether the file resource is marked as read-only
	 */
	public boolean isReadOnly();
}
