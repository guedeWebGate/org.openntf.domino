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
package org.openntf.domino.impl;

import java.io.InputStream;
import java.util.logging.Logger;

import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;

// TODO: Auto-generated Javadoc
/**
 * The Class FileResource.
 */
public class FileResource extends Base<org.openntf.domino.FileResource, lotus.domino.Base> implements org.openntf.domino.FileResource {
	
	/** The Constant log_. */
	@SuppressWarnings("unused")
	private static final Logger log_ = Logger.getLogger(FileResource.class.getName());
	
	/** The document_. */
	private Document document_;

	/**
	 * Instantiates a new file resource.
	 * 
	 * @param delegate
	 *            the delegate
	 * @param parent
	 *            the parent
	 */
	protected FileResource(lotus.domino.Document delegate, Database parent) {
		super(delegate, parent);
		document_ = Factory.fromLotus(delegate, Document.class, parent);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.types.Design#getDocument()
	 */
	@Override
	public Document getDocument() {
		// I don't know if it'll matter whether or not I create a new Document instance or wrap the delegate
		return this.getParent().getDocumentByUNID(document_.getUniversalID());
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#getInputStream()
	 */
	public InputStream getInputStream() {
		return document_.getFirstItem("$FileData").getInputStream();
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#getMimeType()
	 */
	public String getMimeType() {
		return document_.getItemValueString("$MimeType");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#getName()
	 */
	@Override
	public String getName() {
		return document_.getItemValueString("$TITLE");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.types.Design#getNoteID()
	 */
	@Override
	public String getNoteID() {
		return document_.getNoteID();
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.impl.Base#getParent()
	 */
	@Override
	public Database getParent() {
		return (Database) super.getParent();
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.types.Design#getUniversalID()
	 */
	@Override
	public String getUniversalID() {
		return document_.getUniversalID();
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isHideFromWeb()
	 */
	public boolean isHideFromWeb() {
		return getFlags().contains("w");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isHideFromNotes()
	 */
	public boolean isHideFromNotes() {
		return getFlags().contains("n");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isNeedsRefresh()
	 */
	@Override
	public boolean isNeedsRefresh() {
		return getFlags().contains("$");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isPreventChanges()
	 */
	@Override
	public boolean isPreventChanges() {
		return getFlags().contains("P");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isPropagatePreventChanges()
	 */
	@Override
	public boolean isPropagatePreventChanges() {
		return getFlags().contains("r");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.FileResource#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() {
		return getFlags().contains("&");
	}

	/**
	 * Gets the flags.
	 * 
	 * @return the flags
	 */
	private String getFlags() {
		return document_.getItemValueString("$Flags");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.types.DatabaseDescendant#getAncestorDatabase()
	 */
	@Override
	public Database getAncestorDatabase() {
		return this.getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.types.SessionDescendant#getAncestorSession()
	 */
	@Override
	public Session getAncestorSession() {
		return this.getAncestorDatabase().getAncestorSession();
	}
}
