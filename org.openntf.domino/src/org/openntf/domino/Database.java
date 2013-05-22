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

import java.util.Map;
import java.util.Vector;

import org.openntf.domino.annotations.Legacy;
import org.openntf.domino.types.Resurrectable;
import org.openntf.domino.types.SessionDescendant;

// TODO: Auto-generated Javadoc
/**
 * The Interface Database.
 */
public interface Database extends lotus.domino.Database, org.openntf.domino.Base<lotus.domino.Database>, org.openntf.domino.ext.Database,
		Map<String, Document>, Resurrectable, SessionDescendant {

	/**
	 * The Enum DBOption.
	 */
	public static enum DBOption {
		
		/** The L z1. */
		LZ1(Database.DBOPT_LZ1), 
 /** The lzcompression. */
 LZCOMPRESSION(Database.DBOPT_LZCOMPRESSION), 
 /** The maintainlastaccessed. */
 MAINTAINLASTACCESSED(Database.DBOPT_MAINTAINLASTACCESSED), 
 /** The morefields. */
 MOREFIELDS(
				Database.DBOPT_MOREFIELDS), 
 /** The noheadlinemonitors. */
 NOHEADLINEMONITORS(Database.DBOPT_NOHEADLINEMONITORS), 
 /** The nooverwrite. */
 NOOVERWRITE(Database.DBOPT_NOOVERWRITE), 
 /** The noresponseinfo. */
 NORESPONSEINFO(
				Database.DBOPT_NORESPONSEINFO), 
 /** The notransactionlogging. */
 NOTRANSACTIONLOGGING(Database.DBOPT_NOTRANSACTIONLOGGING), 
 /** The nounread. */
 NOUNREAD(Database.DBOPT_NOUNREAD), 
 /** The optimizaion. */
 OPTIMIZAION(
				Database.DBOPT_OPTIMIZATION), 
 /** The replicateunreadmarkstoany. */
 REPLICATEUNREADMARKSTOANY(Database.DBOPT_REPLICATEUNREADMARKSTOANY), 
 /** The replicateunreadmarkstocluster. */
 REPLICATEUNREADMARKSTOCLUSTER(
				Database.DBOPT_REPLICATEUNREADMARKSTOCLUSTER), 
 /** The replicateunreadmarksnever. */
 REPLICATEUNREADMARKSNEVER(Database.DBOPT_REPLICATEUNREADMARKSNEVER), 
 /** The softdelete. */
 SOFTDELETE(
				Database.DBOPT_SOFTDELETE), 
 /** The compressdesign. */
 COMPRESSDESIGN(Database.DBOPT_COMPRESSDESIGN), 
 /** The compressdocuments. */
 COMPRESSDOCUMENTS(
				Database.DBOPT_COMPRESSDOCUMENTS), 
 /** The outofofficeenabled. */
 OUTOFOFFICEENABLED(Database.DBOPT_OUTOFOFFICEENABLED), 
 /** The nosimplesearch. */
 NOSIMPLESEARCH(
				Database.DBOPT_NOSIMPLESEARCH), 
 /** The usedaos. */
 USEDAOS(Database.DBOPT_USEDAOS);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new dB option.
		 * 
		 * @param value
		 *            the value
		 */
		private DBOption(int value) {
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
		 * @return the dB option
		 */
		public static DBOption valueOf(int value) {
			for (DBOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum SignDocType.
	 */
	public static enum SignDocType {
		
		/** The acl. */
		ACL(Database.DBSIGN_DOC_ACL), 
 /** The agent. */
 AGENT(Database.DBSIGN_DOC_AGENT), 
 /** The all. */
 ALL(Database.DBSIGN_DOC_ALL), 
 /** The data. */
 DATA(Database.DBSIGN_DOC_DATA), 
 /** The form. */
 FORM(
				Database.DBSIGN_DOC_FORM), 
 /** The help. */
 HELP(Database.DBSIGN_DOC_HELP), 
 /** The icon. */
 ICON(Database.DBSIGN_DOC_ICON), 
 /** The replformula. */
 REPLFORMULA(
				Database.DBSIGN_DOC_REPLFORMULA), 
 /** The sharedfield. */
 SHAREDFIELD(Database.DBSIGN_DOC_SHAREDFIELD), 
 /** The view. */
 VIEW(Database.DBSIGN_DOC_VIEW);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new sign doc type.
		 * 
		 * @param value
		 *            the value
		 */
		private SignDocType(int value) {
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
		 * @return the sign doc type
		 */
		public static SignDocType valueOf(int value) {
			for (SignDocType opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum CompactOption.
	 */
	public static enum CompactOption {
		
		/** The archive delete compact. */
		ARCHIVE_DELETE_COMPACT(Database.CMPC_ARCHIVE_DELETE_COMPACT), 
 /** The archive delete only. */
 ARCHIVE_DELETE_ONLY(Database.CMPC_ARCHIVE_DELETE_ONLY), 
 /** The chk overlap. */
 CHK_OVERLAP(
				Database.CMPC_CHK_OVERLAP), 
 /** The copystyle. */
 COPYSTYLE(Database.CMPC_COPYSTYLE), 
 /** The disable doctblbit optmzn. */
 DISABLE_DOCTBLBIT_OPTMZN(
				Database.CMPC_DISABLE_DOCTBLBIT_OPTMZN), 
 /** The disable large unktbl. */
 DISABLE_LARGE_UNKTBL(Database.CMPC_DISABLE_LARGE_UNKTBL), 
 /** The disable response info. */
 DISABLE_RESPONSE_INFO(
				Database.CMPC_DISABLE_RESPONSE_INFO), 
 /** The disable transactionlogging. */
 DISABLE_TRANSACTIONLOGGING(Database.CMPC_DISABLE_TRANSACTIONLOGGING), 
 /** The disable unread marks. */
 DISABLE_UNREAD_MARKS(
				Database.CMPC_DISABLE_UNREAD_MARKS), 
 /** The discard view indicies. */
 DISCARD_VIEW_INDICIES(Database.CMPC_DISCARD_VIEW_INDICES), 
 /** The enable doctblbit optmzn. */
 ENABLE_DOCTBLBIT_OPTMZN(
				Database.CMPC_ENABLE_DOCTBLBIT_OPTMZN), 
 /** The enable large unktbl. */
 ENABLE_LARGE_UNKTBL(Database.CMPC_ENABLE_LARGE_UNKTBL), 
 /** The enable response info. */
 ENABLE_RESPONSE_INFO(
				Database.CMPC_ENABLE_RESPONSE_INFO), 
 /** The enable transactionlogging. */
 ENABLE_TRANSACTIONLOGGING(Database.CMPC_ENABLE_TRANSACTIONLOGGING), 
 /** The enable unread marks. */
 ENABLE_UNREAD_MARKS(
				Database.CMPC_ENABLE_UNREAD_MARKS), 
 /** The ignore copystyle errors. */
 IGNORE_COPYSTYLE_ERRORS(Database.CMPC_IGNORE_COPYSTYLE_ERRORS), 
 /** The MA x_4 gb. */
 MAX_4GB(
				Database.CMPC_MAX_4GB), 
 /** The no lockout. */
 NO_LOCKOUT(Database.CMPC_NO_LOCKOUT), 
 /** The recover inplace. */
 RECOVER_INPLACE(Database.CMPC_RECOVER_INPLACE), 
 /** The recover reduce inplace. */
 RECOVER_REDUCE_INPLACE(
				Database.CMPC_RECOVER_REDUCE_INPLACE), 
 /** The revert fileformat. */
 REVERT_FILEFORMAT(Database.CMPC_REVERT_FILEFORMAT);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new compact option.
		 * 
		 * @param value
		 *            the value
		 */
		private CompactOption(int value) {
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
		 * @return the compact option
		 */
		public static CompactOption valueOf(int value) {
			for (CompactOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTIndexOption.
	 */
	public static enum FTIndexOption {
		
		/** The all breaks. */
		ALL_BREAKS(Database.FTINDEX_ALL_BREAKS), 
 /** The attached bin files. */
 ATTACHED_BIN_FILES(Database.FTINDEX_ATTACHED_BIN_FILES), 
 /** The attached files. */
 ATTACHED_FILES(
				Database.FTINDEX_ATTACHED_FILES), 
 /** The case sensitive. */
 CASE_SENSITIVE(Database.FTINDEX_CASE_SENSITIVE), 
 /** The encrypted fields. */
 ENCRYPTED_FIELDS(
				Database.FTINDEX_ENCRYPTED_FIELDS);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT index option.
		 * 
		 * @param value
		 *            the value
		 */
		private FTIndexOption(int value) {
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
		 * @return the fT index option
		 */
		public static FTIndexOption valueOf(int value) {
			for (FTIndexOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FixupOption.
	 */
	public static enum FixupOption {
		
		/** The incremental. */
		INCREMENTAL(Database.FIXUP_INCREMENTAL), 
 /** The nodelete. */
 NODELETE(Database.FIXUP_NODELETE), 
 /** The noviews. */
 NOVIEWS(Database.FIXUP_NOVIEWS), 
 /** The quick. */
 QUICK(
				Database.FIXUP_QUICK), 
 /** The revert. */
 REVERT(Database.FIXUP_REVERT), 
 /** The txlogged. */
 TXLOGGED(Database.FIXUP_TXLOGGED), 
 /** The verify. */
 VERIFY(Database.FIXUP_VERIFY);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fixup option.
		 * 
		 * @param value
		 *            the value
		 */
		private FixupOption(int value) {
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
		 * @return the fixup option
		 */
		public static FixupOption valueOf(int value) {
			for (FixupOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTIndexFrequency.
	 */
	public static enum FTIndexFrequency {
		
		/** The daily. */
		DAILY(Database.FTINDEX_DAILY), 
 /** The hourly. */
 HOURLY(Database.FTINDEX_HOURLY), 
 /** The immediate. */
 IMMEDIATE(Database.FTINDEX_IMMEDIATE), 
 /** The scheduled. */
 SCHEDULED(
				Database.FTINDEX_SCHEDULED);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT index frequency.
		 * 
		 * @param value
		 *            the value
		 */
		private FTIndexFrequency(int value) {
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
		 * @return the fT index frequency
		 */
		public static FTIndexFrequency valueOf(int value) {
			for (FTIndexFrequency opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTDomainSortOption.
	 */
	public static enum FTDomainSortOption {
		
		/** The scores. */
		SCORES(Database.FT_SCORES), 
 /** The date des. */
 DATE_DES(Database.FT_DATE_DES), 
 /** The date asc. */
 DATE_ASC(Database.FT_DATE_ASC);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT domain sort option.
		 * 
		 * @param value
		 *            the value
		 */
		private FTDomainSortOption(int value) {
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
		 * @return the fT domain sort option
		 */
		public static FTDomainSortOption valueOf(int value) {
			for (FTDomainSortOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTDomainSearchOption.
	 */
	public static enum FTDomainSearchOption {
		
		/** The database. */
		DATABASE(Database.FT_DATABASE), 
 /** The filesystem. */
 FILESYSTEM(Database.FT_FILESYSTEM), 
 /** The fuzzy. */
 FUZZY(Database.FT_FUZZY), 
 /** The stems. */
 STEMS(Database.FT_STEMS);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT domain search option.
		 * 
		 * @param value
		 *            the value
		 */
		private FTDomainSearchOption(int value) {
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
		 * @return the fT domain search option
		 */
		public static FTDomainSearchOption valueOf(int value) {
			for (FTDomainSearchOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTSortOption.
	 */
	public static enum FTSortOption {
		
		/** The scores. */
		SCORES(Database.FT_SCORES), 
 /** The date des. */
 DATE_DES(Database.FT_DATE_DES), 
 /** The date asc. */
 DATE_ASC(Database.FT_DATE_ASC), 
 /** The datecreated des. */
 DATECREATED_DES(
				Database.FT_DATECREATED_DES), 
 /** The datecreated asc. */
 DATECREATED_ASC(Database.FT_DATECREATED_ASC);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT sort option.
		 * 
		 * @param value
		 *            the value
		 */
		private FTSortOption(int value) {
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
		 * @return the fT sort option
		 */
		public static FTSortOption valueOf(int value) {
			for (FTSortOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum FTSearchOption.
	 */
	public static enum FTSearchOption {
		
		/** The fuzzy. */
		FUZZY(Database.FT_FUZZY), 
 /** The stems. */
 STEMS(Database.FT_STEMS);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new fT search option.
		 * 
		 * @param value
		 *            the value
		 */
		private FTSearchOption(int value) {
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
		 * @return the fT search option
		 */
		public static FTSearchOption valueOf(int value) {
			for (FTSearchOption opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum ModifiedDocClass.
	 */
	public static enum ModifiedDocClass {
		
		/** The acl. */
		ACL(Database.DBMOD_DOC_ACL), 
 /** The agent. */
 AGENT(Database.DBMOD_DOC_AGENT), 
 /** The all. */
 ALL(Database.DBMOD_DOC_ALL), 
 /** The data. */
 DATA(Database.DBMOD_DOC_DATA), 
 /** The form. */
 FORM(
				Database.DBMOD_DOC_FORM), 
 /** The help. */
 HELP(Database.DBMOD_DOC_HELP), 
 /** The icon. */
 ICON(Database.DBMOD_DOC_ICON), 
 /** The replformula. */
 REPLFORMULA(
				Database.DBMOD_DOC_REPLFORMULA), 
 /** The sharedfield. */
 SHAREDFIELD(Database.DBMOD_DOC_SHAREDFIELD), 
 /** The view. */
 VIEW(Database.DBMOD_DOC_VIEW);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new modified doc class.
		 * 
		 * @param value
		 *            the value
		 */
		private ModifiedDocClass(int value) {
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
		 * @return the modified doc class
		 */
		public static ModifiedDocClass valueOf(int value) {
			for (ModifiedDocClass opt : values()) {
				if (opt.getValue() == value) {
					return opt;
				}
			}
			return null;
		}
	}

	/**
	 * The Enum Type.
	 */
	public static enum Type {
		
		/** The addr book. */
		ADDR_BOOK(Database.DBTYPE_ADDR_BOOK), 
 /** The imap svr proxy. */
 IMAP_SVR_PROXY(Database.DBTYPE_IMAP_SVR_PROXY), 
 /** The library. */
 LIBRARY(Database.DBTYPE_LIBRARY), 
 /** The light addr book. */
 LIGHT_ADDR_BOOK(
				Database.DBTYPE_LIGHT_ADDR_BOOK), 
 /** The mailbox. */
 MAILBOX(Database.DBTYPE_MAILBOX), 
 /** The mailfile. */
 MAILFILE(Database.DBTYPE_MAILFILE), 
 /** The multidb srch. */
 MULTIDB_SRCH(
				Database.DBTYPE_MULTIDB_SRCH), 
 /** The news svr proxy. */
 NEWS_SVR_PROXY(Database.DBTYPE_NEWS_SVR_PROXY), 
 /** The pers journal. */
 PERS_JOURNAL(Database.DBTYPE_PERS_JOURNAL), 
 /** The portfolio. */
 PORTFOLIO(
				Database.DBTYPE_PORTFOLIO), 
 /** The standard. */
 STANDARD(Database.DBTYPE_STANDARD), 
 /** The subscriptions. */
 SUBSCRIPTIONS(Database.DBTYPE_SUBSCRIPTIONS), 
 /** The web app. */
 WEB_APP(
				Database.DBTYPE_WEB_APP);

		/** The value_. */
		private final int value_;

		/**
		 * Instantiates a new type.
		 * 
		 * @param value
		 *            the value
		 */
		private Type(int value) {
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
		 * @return the type
		 */
		public static Type valueOf(int value) {
			for (Type opt : values()) {
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
	 * @see lotus.domino.Database#getACLActivityLog()
	 */
	@Override
	@Deprecated
	@Legacy(Legacy.INTERFACES_WARNING)
	public Vector<String> getACLActivityLog();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#compact()
	 */
	@Override
	public int compact();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#compactWithOptions(int)
	 */
	@Override
	@Deprecated
	public int compactWithOptions(int options);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#compactWithOptions(int, java.lang.String)
	 */
	@Override
	@Deprecated
	public int compactWithOptions(int options, String spaceThreshold);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#compactWithOptions(java.lang.String)
	 */
	@Override
	public int compactWithOptions(String options);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see lotus.domino.Database#createCopy(java.lang.String, java.lang.String)
	 */
	@Override
	public Database createCopy(String server, String dbFile);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createCopy(java.lang.String, java.lang.String, int)
	 */
	@Override
	public Database createCopy(String server, String dbFile, int maxSize);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createDocument()
	 */
	@Override
	public Document createDocument();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createDocumentCollection()
	 */
	@Override
	public DocumentCollection createDocumentCollection();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createFromTemplate(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Database createFromTemplate(String server, String dbFile, boolean inherit);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createFromTemplate(java.lang.String, java.lang.String, boolean, int)
	 */
	@Override
	public Database createFromTemplate(String server, String dbFile, boolean inherit, int maxSize);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createFTIndex(int, boolean)
	 */
	@Override
	@Deprecated
	public void createFTIndex(int options, boolean recreate);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createNoteCollection(boolean)
	 */
	@Override
	public NoteCollection createNoteCollection(boolean selectAllFlag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createOutline(java.lang.String)
	 */
	@Override
	public Outline createOutline(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createOutline(java.lang.String, boolean)
	 */
	@Override
	public Outline createOutline(String name, boolean defaultOutline);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createQueryView(java.lang.String, java.lang.String)
	 */
	@Override
	public View createQueryView(String viewName, String query);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createQueryView(java.lang.String, java.lang.String, lotus.domino.View)
	 */
	@Override
	public View createQueryView(String viewName, String query, lotus.domino.View templateView);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createQueryView(java.lang.String, java.lang.String, lotus.domino.View, boolean)
	 */
	@Override
	public View createQueryView(String viewName, String query, lotus.domino.View templateView, boolean prohibitDesignRefresh);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createReplica(java.lang.String, java.lang.String)
	 */
	@Override
	public Database createReplica(String server, String dbFile);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createView()
	 */
	@Override
	public View createView();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createView(java.lang.String)
	 */
	@Override
	public View createView(String viewName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createView(java.lang.String, java.lang.String)
	 */
	@Override
	public View createView(String viewName, String selectionFormula);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createView(java.lang.String, java.lang.String, lotus.domino.View)
	 */
	@Override
	public View createView(String viewName, String selectionFormula, lotus.domino.View templateView);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#createView(java.lang.String, java.lang.String, lotus.domino.View, boolean)
	 */
	@Override
	public View createView(String viewName, String selectionFormula, lotus.domino.View templateView, boolean prohibitDesignRefresh);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#enableFolder(java.lang.String)
	 */
	@Override
	public void enableFolder(String folder);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#fixup()
	 */
	@Override
	public void fixup();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#fixup(int)
	 */
	@Override
	@Deprecated
	public void fixup(int options);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#FTDomainSearch(java.lang.String, int, int, int, int, int, java.lang.String)
	 */
	@Override
	@Deprecated
	public Document FTDomainSearch(String query, int maxDocs, int sortOpt, int otherOpt, int start, int count, String entryForm);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#FTSearch(java.lang.String)
	 */
	@Override
	public DocumentCollection FTSearch(String query);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#FTSearch(java.lang.String, int)
	 */
	@Override
	public DocumentCollection FTSearch(String query, int maxDocs);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#FTSearch(java.lang.String, int, int, int)
	 */
	@Override
	@Deprecated
	public DocumentCollection FTSearch(String query, int maxDocs, int sortOpt, int otherOpt);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#FTSearchRange(java.lang.String, int, int, int, int)
	 */
	@Override
	@Deprecated
	public DocumentCollection FTSearchRange(String query, int maxDocs, int sortOpt, int otherOpt, int start);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see lotus.domino.Database#getACL()
	 */
	@Override
	public ACL getACL();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAgent(java.lang.String)
	 */
	@Override
	public Agent getAgent(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAgents()
	 */
	@Override
	@Legacy(Legacy.INTERFACES_WARNING)
	public Vector<Agent> getAgents();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAllDocuments()
	 */
	@Override
	public DocumentCollection getAllDocuments();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAllReadDocuments()
	 */
	@Override
	public DocumentCollection getAllReadDocuments();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAllReadDocuments(java.lang.String)
	 */
	@Override
	public DocumentCollection getAllReadDocuments(String userName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAllUnreadDocuments()
	 */
	@Override
	public DocumentCollection getAllUnreadDocuments();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getAllUnreadDocuments(java.lang.String)
	 */
	@Override
	public DocumentCollection getAllUnreadDocuments(String userName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getCategories()
	 */
	@Override
	public String getCategories();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getCreated()
	 */
	@Override
	public DateTime getCreated();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getCurrentAccessLevel()
	 */
	@Override
	public int getCurrentAccessLevel();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDB2Schema()
	 */
	@Override
	public String getDB2Schema();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDesignTemplateName()
	 */
	@Override
	public String getDesignTemplateName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDocumentByID(java.lang.String)
	 */
	@Override
	public Document getDocumentByID(String noteid);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDocumentByUNID(java.lang.String)
	 */
	@Override
	public Document getDocumentByUNID(String unid);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDocumentByURL(java.lang.String, boolean)
	 */
	@Override
	public Document getDocumentByURL(String url, boolean reload);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getDocumentByURL(java.lang.String, boolean, boolean, boolean, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Document getDocumentByURL(String url, boolean reload, boolean reloadIfModified, boolean urlList, String charSet, String webUser,
			String webPassword, String proxyUser, String proxyPassword, boolean returnImmediately);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getFileFormat()
	 */
	@Override
	public int getFileFormat();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getFileName()
	 */
	@Override
	public String getFileName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getFilePath()
	 */
	@Override
	public String getFilePath();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getFolderReferencesEnabled()
	 */
	@Override
	public boolean getFolderReferencesEnabled();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getForm(java.lang.String)
	 */
	@Override
	public Form getForm(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getForms()
	 */
	@Override
	@Legacy(Legacy.INTERFACES_WARNING)
	public Vector<Form> getForms();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getFTIndexFrequency()
	 */
	@Override
	public int getFTIndexFrequency();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getHttpURL()
	 */
	@Override
	public String getHttpURL();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getLastFixup()
	 */
	@Override
	public DateTime getLastFixup();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getLastFTIndexed()
	 */
	@Override
	public DateTime getLastFTIndexed();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getLastModified()
	 */
	@Override
	public DateTime getLastModified();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getLimitRevisions()
	 */
	@Override
	public double getLimitRevisions();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getLimitUpdatedBy()
	 */
	@Override
	public double getLimitUpdatedBy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getListInDbCatalog()
	 */
	@Override
	public boolean getListInDbCatalog();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getManagers()
	 */
	@Override
	@Legacy(Legacy.INTERFACES_WARNING)
	public Vector<String> getManagers();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getMaxSize()
	 */
	@Override
	public long getMaxSize();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getModifiedDocuments()
	 */
	@Override
	public DocumentCollection getModifiedDocuments();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getModifiedDocuments(lotus.domino.DateTime)
	 */
	@Override
	public DocumentCollection getModifiedDocuments(lotus.domino.DateTime since);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getModifiedDocuments(lotus.domino.DateTime, int)
	 */
	@Override
	@Deprecated
	public DocumentCollection getModifiedDocuments(lotus.domino.DateTime since, int noteClass);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getNotesURL()
	 */
	@Override
	public String getNotesURL();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getOption(int)
	 */
	@Override
	@Deprecated
	public boolean getOption(int optionName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getOutline(java.lang.String)
	 */
	@Override
	public Outline getOutline(String outlineName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getParent()
	 */
	@Override
	public Session getParent();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getPercentUsed()
	 */
	@Override
	public double getPercentUsed();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getProfileDocCollection(java.lang.String)
	 */
	@Override
	public DocumentCollection getProfileDocCollection(String profileName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getProfileDocument(java.lang.String, java.lang.String)
	 */
	@Override
	public Document getProfileDocument(String profileName, String profileKey);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getReplicaID()
	 */
	@Override
	public String getReplicaID();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getReplicationInfo()
	 */
	@Override
	public Replication getReplicationInfo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getServer()
	 */
	@Override
	public String getServer();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getSize()
	 */
	@Override
	public double getSize();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getSizeQuota()
	 */
	@Override
	public int getSizeQuota();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getSizeWarning()
	 */
	@Override
	public long getSizeWarning();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getTemplateName()
	 */
	@Override
	public String getTemplateName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getTitle()
	 */
	@Override
	public String getTitle();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getType()
	 */
	@Override
	public int getType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getUndeleteExpireTime()
	 */
	@Override
	public int getUndeleteExpireTime();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getURL()
	 */
	@Override
	public String getURL();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getURLHeaderInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getURLHeaderInfo(String url, String header, String webUser, String webPassword, String proxyUser, String proxyPassword);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getView(java.lang.String)
	 */
	@Override
	public View getView(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#getViews()
	 */
	@Override
	@Legacy(Legacy.INTERFACES_WARNING)
	public Vector<View> getViews();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#grantAccess(java.lang.String, int)
	 */
	@Override
	@Deprecated
	public void grantAccess(String name, int level);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isAllowOpenSoftDeleted()
	 */
	@Override
	public boolean isAllowOpenSoftDeleted();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isClusterReplication()
	 */
	@Override
	public boolean isClusterReplication();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isConfigurationDirectory()
	 */
	@Override
	public boolean isConfigurationDirectory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isCurrentAccessPublicReader()
	 */
	@Override
	public boolean isCurrentAccessPublicReader();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isCurrentAccessPublicWriter()
	 */
	@Override
	public boolean isCurrentAccessPublicWriter();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isDB2()
	 */
	@Override
	@Legacy(Legacy.INTERFACES_WARNING)
	public boolean isDB2();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isDelayUpdates()
	 */
	@Override
	public boolean isDelayUpdates();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isDesignLockingEnabled()
	 */
	@Override
	public boolean isDesignLockingEnabled();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isDirectoryCatalog()
	 */
	@Override
	public boolean isDirectoryCatalog();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isDocumentLockingEnabled()
	 */
	@Override
	public boolean isDocumentLockingEnabled();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isFTIndexed()
	 */
	@Override
	public boolean isFTIndexed();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isInMultiDbIndexing()
	 */
	@Override
	public boolean isInMultiDbIndexing();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isInService()
	 */
	@Override
	public boolean isInService();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isLink()
	 */
	@Override
	public boolean isLink();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isMultiDbSearch()
	 */
	@Override
	public boolean isMultiDbSearch();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isOpen()
	 */
	@Override
	public boolean isOpen();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isPendingDelete()
	 */
	@Override
	public boolean isPendingDelete();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isPrivateAddressBook()
	 */
	@Override
	public boolean isPrivateAddressBook();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#isPublicAddressBook()
	 */
	@Override
	public boolean isPublicAddressBook();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#markForDelete()
	 */
	@Override
	public void markForDelete();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#open()
	 */
	@Override
	public boolean open();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#openByReplicaID(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean openByReplicaID(String server, String replicaId);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#openIfModified(java.lang.String, java.lang.String, lotus.domino.DateTime)
	 */
	@Override
	public boolean openIfModified(String server, String dbFile, lotus.domino.DateTime modifiedSince);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#openWithFailover(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean openWithFailover(String server, String dbFile);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#queryAccess(java.lang.String)
	 */
	@Override
	public int queryAccess(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#queryAccessPrivileges(java.lang.String)
	 */
	@Override
	public int queryAccessPrivileges(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#queryAccessRoles(java.lang.String)
	 */
	public Vector<String> queryAccessRoles(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#remove()
	 */
	@Override
	public void remove();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#removeFTIndex()
	 */
	@Override
	public void removeFTIndex();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#replicate(java.lang.String)
	 */
	@Override
	public boolean replicate(String server);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#revokeAccess(java.lang.String)
	 */
	@Override
	public void revokeAccess(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#search(java.lang.String)
	 */
	@Override
	public DocumentCollection search(String formula);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#search(java.lang.String, lotus.domino.DateTime)
	 */
	@Override
	public DocumentCollection search(String formula, lotus.domino.DateTime startDate);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#search(java.lang.String, lotus.domino.DateTime, int)
	 */
	@Override
	public DocumentCollection search(String formula, lotus.domino.DateTime startDate, int maxDocs);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setAllowOpenSoftDeleted(boolean)
	 */
	@Override
	public void setAllowOpenSoftDeleted(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setCategories(java.lang.String)
	 */
	@Override
	public void setCategories(String categories);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setDelayUpdates(boolean)
	 */
	@Override
	public void setDelayUpdates(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setDesignLockingEnabled(boolean)
	 */
	@Override
	public void setDesignLockingEnabled(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setDocumentLockingEnabled(boolean)
	 */
	@Override
	public void setDocumentLockingEnabled(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setFolderReferencesEnabled(boolean)
	 */
	@Override
	public void setFolderReferencesEnabled(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setFTIndexFrequency(int)
	 */
	@Override
	@Deprecated
	public void setFTIndexFrequency(int frequency);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setInMultiDbIndexing(boolean)
	 */
	@Override
	public void setInMultiDbIndexing(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setInService(boolean)
	 */
	@Override
	public void setInService(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setLimitRevisions(double)
	 */
	@Override
	public void setLimitRevisions(double revisions);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setLimitUpdatedBy(double)
	 */
	@Override
	public void setLimitUpdatedBy(double updatedBys);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setListInDbCatalog(boolean)
	 */
	@Override
	public void setListInDbCatalog(boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setOption(int, boolean)
	 */
	@Override
	@Deprecated
	public void setOption(int optionName, boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setSizeQuota(int)
	 */
	@Override
	public void setSizeQuota(int quota);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setSizeWarning(int)
	 */
	@Override
	public void setSizeWarning(int warning);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(final String title);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#setUndeleteExpireTime(int)
	 */
	@Override
	public void setUndeleteExpireTime(int hours);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#sign()
	 */
	@Override
	public void sign();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#sign(int)
	 */
	@Override
	public void sign(int documentType);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#sign(int, boolean)
	 */
	@Override
	@Deprecated
	public void sign(int documentType, boolean existingSigsOnly);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#sign(int, boolean, java.lang.String)
	 */
	@Override
	@Deprecated
	public void sign(int documentType, boolean existingSigsOnly, String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#sign(int, boolean, java.lang.String, boolean)
	 */
	@Override
	@Deprecated
	public void sign(int documentType, boolean existingSigsOnly, String name, boolean nameIsNoteid);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.Database#updateFTIndex(boolean)
	 */
	@Override
	public void updateFTIndex(boolean create);

}
