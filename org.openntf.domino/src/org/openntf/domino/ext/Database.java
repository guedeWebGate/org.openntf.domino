/**
 * 
 */
package org.openntf.domino.ext;

import java.util.Collection;
import java.util.Map;

import org.openntf.domino.ACL;
import org.openntf.domino.Database.DBOption;
import org.openntf.domino.Database.SignDocType;
import org.openntf.domino.Database.SortOption;
import org.openntf.domino.Document;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.FileResource;
import org.openntf.domino.transactions.DatabaseTransaction;

/**
 * @author withersp
 * 
 */
public interface Database {

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key);

	/**
	 * @param itemValues
	 *            Map of fields and values with which to initialize a document
	 * @return the newly created document
	 */
	public Document createDocument(Map<String, Object> itemValues);

	/**
	 * @param keyValuePairs
	 *            an object of key value pairs with which to initialize a document
	 * @return the newly created document
	 */
	public Document createDocument(Object... keyValuePairs);

	/**
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
	public Document FTDomainSearch(String query, int maxDocs, SortOption sortOpt, int otherOpt, int start, int count, String entryForm);

	/**
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
	public DocumentCollection FTSearch(String query, int maxDocs, SortOption sortOpt, int otherOpt);

	/**
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
	public DocumentCollection FTSearchRange(String query, int maxDocs, SortOption sortOpt, int otherOpt, int start);

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Document get(Object key);

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
	public Document getDocumentByKey(String key);

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
	public Document getDocumentByKey(String key, boolean createOnFail);

	/**
	 * @param name
	 *            name of a file resource
	 * @return a file resource
	 */
	public FileResource getFileResource(String name);

	/**
	 * @return collection of all file resources
	 */
	public Collection<FileResource> getFileResources();

	/**
	 * @param name
	 *            name of a user to grant access to
	 * @param level
	 *            ACL.Level for access
	 */
	public void grantAccess(String name, ACL.Level level);

	/**
	 * @param optionName
	 *            DBOption option name
	 * @param flag
	 *            the flag
	 */
	public void setOption(DBOption optionName, boolean flag);

	/**
	 * @param documentType
	 *            sign document type
	 */
	public void sign(SignDocType documentType);

	/**
	 * @param documentType
	 *            sign document type
	 * @param existingSigsOnly
	 *            whether to only update existing signatures
	 * @param name
	 *            the name
	 */
	public void sign(SignDocType documentType, boolean existingSigsOnly, String name);

	/**
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
	 * @return Database transaction
	 */
	public DatabaseTransaction startTransaction();

	public void closeTransaction();

	/**
	 * @return Database transaction
	 */
	public DatabaseTransaction getTransaction();
}