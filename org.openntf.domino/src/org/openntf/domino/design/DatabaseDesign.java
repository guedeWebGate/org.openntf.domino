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
 * The Interface DatabaseDesign.
 * 
 * @author jgallagher
 */
public interface DatabaseDesign {

	/**
	 * Gets the about document.
	 * 
	 * @return the about document
	 */
	public AboutDocument getAboutDocument();

	/**
	 * Gets the acl.
	 * 
	 * @return the acl
	 */
	public ACL getACL();

	/**
	 * Gets the default form.
	 * 
	 * @return the default form
	 */
	public Form getDefaultForm();

	/**
	 * Gets the default view.
	 * 
	 * @return the default view
	 */
	public View getDefaultView();

	/**
	 * Gets the file resource.
	 * 
	 * @param name
	 *            name of a file resource
	 * @return a file resource
	 */
	public FileResource getFileResource(String name);

	/**
	 * Gets the file resources.
	 * 
	 * @return collection of all file resources
	 */
	public DesignCollection<FileResource> getFileResources();

	/**
	 * Gets the hidden file resource.
	 * 
	 * @param name
	 *            name of a hidden file resource
	 * @return a hidden file resource
	 */
	public FileResource getHiddenFileResource(String name);

	/**
	 * Gets the hidden file resources.
	 * 
	 * @return the hidden file resources
	 */
	public DesignCollection<FileResource> getHiddenFileResources();

	/**
	 * Gets the form.
	 * 
	 * @param name
	 *            the name
	 * @return the form
	 */
	public Form getForm(String name);

	/**
	 * Gets the forms.
	 * 
	 * @return the forms
	 */
	public DesignCollection<Form> getForms();

	/**
	 * Gets the icon note.
	 * 
	 * @return the icon note of the database
	 */
	public IconNote getIconNote();

	/**
	 * Gets the replication formula.
	 * 
	 * @return the replication formula
	 */
	public ReplicationFormula getReplicationFormula();

	/**
	 * Gets the using document.
	 * 
	 * @return the using document
	 */
	public UsingDocument getUsingDocument();

	/**
	 * Gets the view.
	 * 
	 * @param name
	 *            the name
	 * @return the view
	 */
	public View getView(String name);

	/**
	 * Gets the views.
	 * 
	 * @return the views
	 */
	public DesignCollection<View> getViews();
}
