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

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface DesignBase.
 * 
 * @author jgallagher
 */
public interface DesignBase extends org.openntf.domino.types.Design, org.openntf.domino.types.DatabaseDescendant {

	/**
	 * Gets the name.
	 * 
	 * @return the design element's name
	 */
	public String getName();

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            The new name for the design element; any |-delimited values will be appended to the aliases
	 */
	public void setName(String name);

	/**
	 * Gets the aliases.
	 * 
	 * @return a List of the design element's aliases
	 */
	public List<String> getAliases();

	/**
	 * Sets the alias.
	 * 
	 * @param alias
	 *            The new alias(es) for the design element, |-delimited
	 */
	public void setAlias(String alias);

	/**
	 * Sets the aliases.
	 * 
	 * @param aliases
	 *            The new aliases for the design element; any |-delimited values will be exploded into the final list
	 */
	public void setAliases(Iterable<String> aliases);

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
	 * @return whether the design element propagates its prevent-changes settings
	 */
	public boolean isPropagatePreventChanges();

	/**
	 * Save any changes to the design element (may change the Note ID).
	 */
	public void save();

}