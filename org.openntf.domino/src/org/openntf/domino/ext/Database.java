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

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Map;

import org.openntf.domino.ACL;
import org.openntf.domino.Database.CompactOption;
import org.openntf.domino.Database.DBOption;
import org.openntf.domino.Database.FTDomainSearchOption;
import org.openntf.domino.Database.FTDomainSortOption;
import org.openntf.domino.Database.FTIndexFrequency;
import org.openntf.domino.Database.FTIndexOption;
import org.openntf.domino.Database.FTSearchOption;
import org.openntf.domino.Database.FTSortOption;
import org.openntf.domino.Database.FixupOption;
import org.openntf.domino.Database.ModifiedDocClass;
import org.openntf.domino.Database.SignDocType;
import org.openntf.domino.Document;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.design.DatabaseDesign;
import org.openntf.domino.transactions.DatabaseTransaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface Database.
 * 
 * @author withersp
 */
public interface Database {

	/**
	 * Compact with options.
	 * 
	 * @param options
	 *            the options
	 * @return the int
	 */
	public int compactWithOptions(EnumSet<CompactOption> options);

	/**
	 * Compact with options.
	 * 
	 * @param options
	 *            the options
	 * @param spaceThreshold
	 *            the space threshold
	 * @return the int
	 */
	public int compactWithOptions(EnumSet<CompactOption> options, String spaceThreshold);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	/**
	 * Contains key.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public boolean containsKey(Object key);

	/**
	 * Creates the document.
	 * 
	 * @param itemValues
	 *            Map of fields and values with which to initialize a document
	 * @return the newly created document
	 */
	public Document createDocument(Map<String, Object> itemValues);

	/**
	 * Creates the document.
	 * 
	 * @param keyValuePairs
	 *            an object of key value pairs with which to initialize a document
	 * @return the newly created document
	 */
	public Document createDocument(Object... keyValuePairs);

	/**
	 * Creates the ft index.
	 * 
	 * @param options
	 *            the options
	 * @param recreate
	 *            the recreate
	 */
	public void createFTIndex(EnumSet<FTIndexOption> options, boolean recreate);

	/**
	 * Fixup.
	 * 
	 * @param options
	 *            the options
	 */
	public void fixup(EnumSet<FixupOption> options);

	/**
	 * FT domain search.
	 * 
	 * @param query
	 *            the query
	 * @param maxDocs
	 *            the max docs
	 * @param sortOpt
	 *            the sort option
	 * @param otherOpt
	 *            the other option
	 * @param start
	 *            the start
	 * @param count
	 *            the count
	 * @param entryForm
	 *            the entry form
	 * @return a document
	 */
	public Document FTDomainSearch(String query, int maxDocs, FTDomainSortOption sortOpt, EnumSet<FTDomainSearchOption> otherOpt,
			int start, int count, String entryForm);

	/**
	 * FT search.
	 * 
	 * @param query
	 *            the query
	 * @param maxDocs
	 *            the max docs
	 * @param sortOpt
	 *            the sort option
	 * @param otherOpt
	 *            the other option
	 * @return a DocumentCollection
	 */
	public DocumentCollection FTSearch(String query, int maxDocs, FTSortOption sortOpt, EnumSet<FTSearchOption> otherOpt);

	/**
	 * FT search range.
	 * 
	 * @param query
	 *            the query
	 * @param maxDocs
	 *            the max docs
	 * @param sortOpt
	 *            the sort option
	 * @param otherOpt
	 *            the other option
	 * @param start
	 *            the start
	 * @return a DocumentCollection
	 */
	public DocumentCollection FTSearchRange(String query, int maxDocs, FTSortOption sortOpt, EnumSet<FTSearchOption> otherOpt, int start);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * @return the document
	 */
	public Document get(Object key);

	/**
	 * Gets the design.
	 * 
	 * @return A DatabaseDesign object representing the various design elements of this database.
	 */
	public DatabaseDesign getDesign();

	/**
	 * Retrieves a document by a String key.
	 * <p>
	 * The key is hased using MD5 and treated as a UNID.
	 * </p>
	 * 
	 * @param key
	 *            The arbitrary-length string key.
	 * 
	 * @return The Document corresponding to the key, or null if no matching document exists.
	 * @since org.openntf.domino 1.0.0
	 */
	public Document getDocumentByKey(Serializable key);

	/**
	 * Retrieves a document by a String key, allowing for creation of a new document if no match was found.
	 * <p>
	 * The key is hased using MD5 and treated as a UNID.
	 * </p>
	 * 
	 * @param key
	 *            The arbitrary-length string key.
	 * @param createOnFail
	 *            Whether or not a new document should be created when the key was not found. Defaults to false.
	 * 
	 * @return The Document corresponding to the key, or null if no matching document exists and createOnFail is false.
	 * @since org.openntf.domino 1.0.0
	 */
	public Document getDocumentByKey(Serializable key, boolean createOnFail);

	/**
	 * Gets the modified documents.
	 * 
	 * @param since
	 *            the since
	 * @param noteClass
	 *            the note class
	 * @return the modified documents
	 */
	public DocumentCollection getModifiedDocuments(lotus.domino.DateTime since, ModifiedDocClass noteClass);

	/**
	 * Gets the option.
	 * 
	 * @param optionName
	 *            the option name
	 * @return the option
	 */
	public boolean getOption(DBOption optionName);

	/**
	 * Grant access.
	 * 
	 * @param name
	 *            name of a user to grant access to
	 * @param level
	 *            ACL.Level for access
	 */
	public void grantAccess(String name, ACL.Level level);

	/**
	 * Sets the fT index frequency.
	 * 
	 * @param frequency
	 *            the new fT index frequency
	 */
	public void setFTIndexFrequency(FTIndexFrequency frequency);

	/**
	 * Sets the option.
	 * 
	 * @param optionName
	 *            DBOption option name
	 * @param flag
	 *            the flag
	 */
	public void setOption(DBOption optionName, boolean flag);

	/**
	 * Sign.
	 * 
	 * @param documentType
	 *            sign document type
	 */
	public void sign(SignDocType documentType);

	/**
	 * Sign.
	 * 
	 * @param documentType
	 *            the document type
	 * @param existingSigsOnly
	 *            the existing sigs only
	 */
	public void sign(SignDocType documentType, boolean existingSigsOnly);

	/**
	 * Sign.
	 * 
	 * @param documentType
	 *            sign document type
	 * @param existingSigsOnly
	 *            whether to only update existing signatures
	 * @param name
	 *            the name
	 */
	public void sign(SignDocType documentType, boolean existingSigsOnly, String name);

	/**
	 * Sign.
	 * 
	 * @param documentType
	 *            sign document type
	 * @param existingSigsOnly
	 *            whether to only update existing signatures
	 * @param name
	 *            the name
	 * @param nameIsNoteid
	 *            whether or not the name is a note id
	 */
	public void sign(SignDocType documentType, boolean existingSigsOnly, String name, boolean nameIsNoteid);

	/**
	 * Start transaction.
	 * 
	 * @return Database transaction
	 */
	public DatabaseTransaction startTransaction();

	/**
	 * Close transaction.
	 */
	public void closeTransaction();

	/**
	 * Gets the transaction.
	 * 
	 * @return Database transaction
	 */
	public DatabaseTransaction getTransaction();

	/**
	 * Gets the domino server.
	 * 
	 * @return the domino server
	 */
	public lotus.notes.addins.DominoServer getDominoServer();

	/**
	 * Refresh design.
	 */
	public void refreshDesign();
}