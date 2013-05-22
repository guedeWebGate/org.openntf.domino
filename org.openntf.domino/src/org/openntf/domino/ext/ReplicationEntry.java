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

import java.util.Collection;

import org.openntf.domino.Replication;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReplicationEntry.
 * 
 * @author withersp
 */
public interface ReplicationEntry {

	/**
	 * Gets the parent.
	 * 
	 * @return get parent Replication
	 */
	public Replication getParent();

	/**
	 * Sets the views.
	 * 
	 * @param views
	 *            collection of views
	 */
	public void setViews(Collection<String> views);

}
