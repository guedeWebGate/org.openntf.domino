/*
 * Copyright 2013
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

import java.util.Collection;

import org.openntf.domino.types.DatabaseDescendant;

/**
 * The Interface ReplicationEntry.
 */
public interface ReplicationEntry extends Base<lotus.domino.ReplicationEntry>, lotus.domino.ReplicationEntry,
		org.openntf.domino.ext.ReplicationEntry, DatabaseDescendant {

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#getDestination()
	 */
	public String getDestination();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#getFormula()
	 */
	@Override
	public String getFormula();

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Replication getParent();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#getSource()
	 */
	@Override
	public String getSource();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#getViews()
	 */
	@Override
	public String getViews();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#isIncludeACL()
	 */
	@Override
	public boolean isIncludeACL();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#isIncludeAgents()
	 */
	@Override
	public boolean isIncludeAgents();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#isIncludeDocuments()
	 */
	@Override
	public boolean isIncludeDocuments();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#isIncludeForms()
	 */
	@Override
	public boolean isIncludeForms();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#isIncludeFormulas()
	 */
	@Override
	public boolean isIncludeFormulas();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#remove()
	 */
	@Override
	public int remove();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#save()
	 */
	@Override
	public int save();

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setFormula(java.lang.String)
	 */
	@Override
	public void setFormula(final String formula);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setIncludeACL(boolean)
	 */
	@Override
	public void setIncludeACL(final boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setIncludeAgents(boolean)
	 */
	@Override
	public void setIncludeAgents(final boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setIncludeDocuments(boolean)
	 */
	@Override
	public void setIncludeDocuments(final boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setIncludeForms(boolean)
	 */
	@Override
	public void setIncludeForms(final boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setIncludeFormulas(boolean)
	 */
	@Override
	public void setIncludeFormulas(final boolean flag);

	/*
	 * (non-Javadoc)
	 * 
	 * @see lotus.domino.ReplicationEntry#setViews(java.lang.String)
	 */
	@Override
	public void setViews(final String views);

	/**
	 * Sets the views.
	 * 
	 * @param views
	 *            the new views
	 */
	public void setViews(final Collection<String> views);

}
