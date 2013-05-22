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
package org.openntf.domino;

import org.openntf.domino.types.SessionDescendant;

// TODO: Auto-generated Javadoc
/**
 * The Interface DxlImporter.
 */
public interface DxlImporter extends Base<lotus.domino.DxlImporter>, lotus.domino.DxlImporter, org.openntf.domino.ext.DxlImporter,
		SessionDescendant {

	/**
	 * The Enum DocumentImportOption.
	 */
	public static enum DocumentImportOption {
		
		/** The ignore. */
		IGNORE(DxlImporter.DXLIMPORTOPTION_IGNORE), 
 /** The create. */
 CREATE(DxlImporter.DXLIMPORTOPTION_CREATE), 
 /** The replace else create. */
 REPLACE_ELSE_CREATE(
				DxlImporter.DXLIMPORTOPTION_REPLACE_ELSE_CREATE), 
 /** The replace else ignore. */
 REPLACE_ELSE_IGNORE(DxlImporter.DXLIMPORTOPTION_REPLACE_ELSE_IGNORE), 
 /** The update else create. */
 UPDATE_ELSE_CREATE(
				DxlImporter.DXLIMPORTOPTION_UPDATE_ELSE_CREATE), 
 /** The update else ignore. */
 UPDATE_ELSE_IGNORE(DxlImporter.DXLIMPORTOPTION_UPDATE_ELSE_IGNORE);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new document import option.
		 * 
		 * @param value
		 *            the value
		 */
		private DocumentImportOption(int value) {
			value_ = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public int getValue() {
			return value_;
		}

		/**
		 * Value of.
		 * 
		 * @param value
		 *            the value
		 * @return the document import option
		 */
		public static DocumentImportOption valueOf(int value) {
			for (DocumentImportOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum DesignImportOption.
	 */
	public static enum DesignImportOption {
		
		/** The ignore. */
		IGNORE(DxlImporter.DXLIMPORTOPTION_IGNORE), 
 /** The create. */
 CREATE(DxlImporter.DXLIMPORTOPTION_CREATE), 
 /** The replace else create. */
 REPLACE_ELSE_CREATE(
				DxlImporter.DXLIMPORTOPTION_REPLACE_ELSE_CREATE), 
 /** The replace else ignore. */
 REPLACE_ELSE_IGNORE(DxlImporter.DXLIMPORTOPTION_REPLACE_ELSE_IGNORE);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new design import option.
		 * 
		 * @param value
		 *            the value
		 */
		private DesignImportOption(int value) {
			value_ = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public int getValue() {
			return value_;
		}

		/**
		 * Value of.
		 * 
		 * @param value
		 *            the value
		 * @return the design import option
		 */
		public static DesignImportOption valueOf(int value) {
			for (DesignImportOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum AclImportOption.
	 */
	public static enum AclImportOption {
		
		/** The ignore. */
		IGNORE(DxlImporter.DXLIMPORTOPTION_IGNORE), 
 /** The replace else ignore. */
 REPLACE_ELSE_IGNORE(DxlImporter.DXLIMPORTOPTION_REPLACE_ELSE_IGNORE), 
 /** The update else create. */
 UPDATE_ELSE_CREATE(
				DxlImporter.DXLIMPORTOPTION_UPDATE_ELSE_CREATE), 
 /** The update else ignore. */
 UPDATE_ELSE_IGNORE(DxlImporter.DXLIMPORTOPTION_UPDATE_ELSE_IGNORE);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new acl import option.
		 * 
		 * @param value
		 *            the value
		 */
		private AclImportOption(int value) {
			value_ = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public int getValue() {
			return value_;
		}

		/**
		 * Value of.
		 * 
		 * @param value
		 *            the value
		 * @return the acl import option
		 */
		public static AclImportOption valueOf(int value) {
			for (AclImportOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum InputValidationOption.
	 */
	public static enum InputValidationOption {
		
		/** The never. */
		NEVER(DxlImporter.DXLVALIDATIONOPTION_VALIDATE_NEVER), 
 /** The always. */
 ALWAYS(DxlImporter.DXLVALIDATIONOPTION_VALIDATE_ALWAYS), 
 /** The auto. */
 AUTO(
				DxlImporter.DXLVALIDATIONOPTION_VALIDATE_AUTO);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new input validation option.
		 * 
		 * @param value
		 *            the value
		 */
		private InputValidationOption(int value) {
			value_ = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public int getValue() {
			return value_;
		}

		/**
		 * Value of.
		 * 
		 * @param value
		 *            the value
		 * @return the input validation option
		 */
		public static InputValidationOption valueOf(int value) {
			for (InputValidationOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getAclImportOption()
	 */
	@Override
	public int getAclImportOption();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getCompileLotusScript()
	 */
	@Override
	public boolean getCompileLotusScript();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getCreateFTIndex()
	 */
	@Override
	public boolean getCreateFTIndex();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getDesignImportOption()
	 */
	@Override
	public int getDesignImportOption();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getDocumentImportOption()
	 */
	@Override
	public int getDocumentImportOption();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getExitOnFirstFatalError()
	 */
	@Override
	public boolean getExitOnFirstFatalError();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getFirstImportedNoteID()
	 */
	@Override
	public String getFirstImportedNoteID();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getImportedNoteCount()
	 */
	@Override
	public int getImportedNoteCount();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getInputValidationOption()
	 */
	@Override
	public int getInputValidationOption();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getLog()
	 */
	@Override
	public String getLog();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getLogComment()
	 */
	@Override
	public String getLogComment();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getNextImportedNoteID(java.lang.String)
	 */
	@Override
	public String getNextImportedNoteID(String noteid);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getReplaceDbProperties()
	 */
	@Override
	public boolean getReplaceDbProperties();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getReplicaRequiredForReplaceOrUpdate()
	 */
	@Override
	public boolean getReplicaRequiredForReplaceOrUpdate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#getUnknownTokenLogOption()
	 */
	@Override
	public int getUnknownTokenLogOption();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#importDxl(lotus.domino.RichTextItem, lotus.domino.Database)
	 */
	@Override
	public void importDxl(lotus.domino.RichTextItem rtitem, lotus.domino.Database database);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#importDxl(lotus.domino.Stream, lotus.domino.Database)
	 */
	@Override
	public void importDxl(lotus.domino.Stream stream, lotus.domino.Database database);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#importDxl(java.lang.String, lotus.domino.Database)
	 */
	@Override
	public void importDxl(String dxl, lotus.domino.Database database);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setAclImportOption(int)
	 */
	@Override
	@Deprecated
	public void setAclImportOption(int option);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setCompileLotusScript(boolean)
	 */
	@Override
	public void setCompileLotusScript(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setCreateFTIndex(boolean)
	 */
	@Override
	public void setCreateFTIndex(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setDesignImportOption(int)
	 */
	@Override
	@Deprecated
	public void setDesignImportOption(int option);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setDocumentImportOption(int)
	 */
	@Override
	@Deprecated
	public void setDocumentImportOption(int option);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setExitOnFirstFatalError(boolean)
	 */
	@Override
	public void setExitOnFirstFatalError(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setInputValidationOption(int)
	 */
	@Override
	@Deprecated
	public void setInputValidationOption(int option);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setLogComment(java.lang.String)
	 */
	@Override
	public void setLogComment(String comment);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setReplaceDbProperties(boolean)
	 */
	@Override
	public void setReplaceDbProperties(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setReplicaRequiredForReplaceOrUpdate(boolean)
	 */
	@Override
	public void setReplicaRequiredForReplaceOrUpdate(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.DxlImporter#setUnknownTokenLogOption(int)
	 */
	@Override
	public void setUnknownTokenLogOption(int option);
}
