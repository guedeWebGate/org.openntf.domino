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
package org.openntf.domino.design.impl;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Logger;

import org.openntf.domino.Database;
import org.openntf.domino.Document;
import org.openntf.domino.NoteCollection;
import org.openntf.domino.NoteCollection.SelectOption;
import org.openntf.domino.utils.DominoUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseDesign.
 * 
 * @author jgallagher
 */
public class DatabaseDesign implements org.openntf.domino.design.DatabaseDesign {
	
	/** The Constant log_. */
	@SuppressWarnings("unused")
	private static final Logger log_ = Logger.getLogger(DatabaseDesign.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * Some handy constant Note IDs for getting specific elements. h/t http://www.nsftools.com/tips/NotesTips.htm#defaultelements
	 */
	/** The Constant ABOUT_NOTE. */
	private static final String ABOUT_NOTE = "FFFF0002";
	
	/** The Constant DEFAULT_FORM. */
	private static final String DEFAULT_FORM = "FFFF0004";
	
	/** The Constant DEFAULT_VIEW. */
	private static final String DEFAULT_VIEW = "FFFF0008";
	
	/** The Constant ICON_NOTE. */
	private static final String ICON_NOTE = "FFFF0010";
	
	/** The Constant DESIGN_COLLECTION. */
	private static final String DESIGN_COLLECTION = "FFFF0020";
	
	/** The Constant ACL_NOTE. */
	private static final String ACL_NOTE = "FFFF0040";
	
	/** The Constant USING_NOTE. */
	private static final String USING_NOTE = "FFFF0100";
	
	/** The Constant REPLICATION_FORMULA. */
	private static final String REPLICATION_FORMULA = "FFFF0800";

	/** The database_. */
	private final Database database_;

	/**
	 * Instantiates a new database design.
	 * 
	 * @param database
	 *            the database
	 */
	public DatabaseDesign(final Database database) {
		database_ = database;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getAboutDocument()
	 */
	@Override
	public AboutDocument getAboutDocument() {
		Document doc = database_.getDocumentByID(ABOUT_NOTE);
		if (doc != null) {
			return new AboutDocument(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getACL()
	 */
	@Override
	public ACL getACL() {
		return new ACL(database_.getDocumentByID(ACL_NOTE));
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getDefaultForm()
	 */
	@Override
	public Form getDefaultForm() {
		Document formDoc = database_.getDocumentByID(DEFAULT_FORM);
		if (formDoc != null) {
			return new Form(formDoc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getDefaultView()
	 */
	@Override
	public View getDefaultView() {
		Document viewDoc = database_.getDocumentByID(DEFAULT_VIEW);
		if (viewDoc != null) {
			return new View(viewDoc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getFileResource(java.lang.String)
	 */
	@Override
	public FileResource getFileResource(final String name) {
		NoteCollection notes = getNoteCollection(String.format(
				" !@Contains($Flags; '~') & @Contains($Flags; 'g') & @Explode($TITLE; '|')=\"%s\" ", DominoUtils
						.escapeForFormulaString(name)), EnumSet.of(SelectOption.MISC_FORMAT));

		String noteId = notes.getFirstNoteID();
		if (!noteId.isEmpty()) {
			Document doc = database_.getDocumentByID(noteId);
			return new FileResource(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getFileResources()
	 */
	@Override
	public DesignCollection<org.openntf.domino.design.FileResource> getFileResources() {
		NoteCollection notes = getNoteCollection(" !@Contains($Flags; '~') & @Contains($Flags; 'g') ", EnumSet.of(SelectOption.MISC_FORMAT));
		return new DesignCollection<org.openntf.domino.design.FileResource>(notes, FileResource.class);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getHiddenFileResource(java.lang.String)
	 */
	@Override
	public FileResource getHiddenFileResource(final String name) {
		NoteCollection notes = getNoteCollection(String.format(
				" @Contains($Flags; '~') & @Contains($Flags; 'g') & !@Contains($Flags; 'K':';':'[':',') & @Explode($TITLE; '|')=\"%s\" ",
				DominoUtils.escapeForFormulaString(name)), EnumSet.of(SelectOption.MISC_FORMAT));

		String noteId = notes.getFirstNoteID();
		if (!noteId.isEmpty()) {
			Document doc = database_.getDocumentByID(noteId);
			return new FileResource(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getHiddenFileResources()
	 */
	@Override
	public DesignCollection<org.openntf.domino.design.FileResource> getHiddenFileResources() {
		NoteCollection notes = getNoteCollection(" @Contains($Flags; '~') & @Contains($Flags; 'g') & !@Contains($Flags; 'K':';':'[':',')",
				EnumSet.of(SelectOption.MISC_FORMAT));
		return new DesignCollection<org.openntf.domino.design.FileResource>(notes, FileResource.class);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getForm(java.lang.String)
	 */
	@Override
	public Form getForm(String name) {
		// TODO Check if this returns subforms
		NoteCollection notes = getNoteCollection(String.format(" @Explode($TITLE; '|')=\"%s\" ", DominoUtils.escapeForFormulaString(name)),
				EnumSet.of(SelectOption.FORMS));

		String noteId = notes.getFirstNoteID();
		if (!noteId.isEmpty()) {
			Document doc = database_.getDocumentByID(noteId);
			return new Form(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getForms()
	 */
	@Override
	public DesignCollection<org.openntf.domino.design.Form> getForms() {
		NoteCollection notes = getNoteCollection(" @All ", EnumSet.of(SelectOption.FORMS));
		return new DesignCollection<org.openntf.domino.design.Form>(notes, Form.class);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getIconNote()
	 */
	@Override
	public IconNote getIconNote() {
		return new IconNote(database_.getDocumentByID(ICON_NOTE));
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getReplicationFormula()
	 */
	@Override
	public ReplicationFormula getReplicationFormula() {
		Document repNote = database_.getDocumentByID(REPLICATION_FORMULA);
		if (repNote != null) {
			return new ReplicationFormula(repNote);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getUsingDocument()
	 */
	@Override
	public UsingDocument getUsingDocument() {
		Document doc = database_.getDocumentByID(USING_NOTE);
		if (doc != null) {
			return new UsingDocument(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getView(java.lang.String)
	 */
	@Override
	public org.openntf.domino.design.View getView(String name) {
		// TODO Check if this returns folders
		NoteCollection notes = getNoteCollection(String.format(" @Explode($TITLE; '|')=\"%s\" ", DominoUtils.escapeForFormulaString(name)),
				EnumSet.of(SelectOption.VIEWS));

		String noteId = notes.getFirstNoteID();
		if (!noteId.isEmpty()) {
			Document doc = database_.getDocumentByID(noteId);
			return new View(doc);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DatabaseDesign#getViews()
	 */
	@Override
	public DesignCollection<org.openntf.domino.design.View> getViews() {
		NoteCollection notes = getNoteCollection(" @All ", EnumSet.of(SelectOption.VIEWS));
		return new DesignCollection<org.openntf.domino.design.View>(notes, View.class);
	}

	/**
	 * Gets the note collection.
	 * 
	 * @param selectionFormula
	 *            the selection formula
	 * @param options
	 *            the options
	 * @return the note collection
	 */
	private NoteCollection getNoteCollection(final String selectionFormula, final Set<SelectOption> options) {
		NoteCollection notes = database_.createNoteCollection(false);
		notes.setSelectOptions(options);
		notes.setSelectionFormula(selectionFormula);
		notes.buildCollection();
		return notes;
	}

}