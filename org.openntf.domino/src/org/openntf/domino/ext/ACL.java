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

import org.openntf.domino.ACL.Level;
import org.openntf.domino.ACLEntry;

// TODO: Auto-generated Javadoc
/**
 * The Interface ACL.
 * 
 * @author withersp
 */
public interface ACL {

	/**
	 * Creates an entry in the ACL with the name and level that you specify.
	 * 
	 * @param name
	 *            The name of the person, group, or server for whom you want to create an entry in the ACL. You must supply the complete
	 *            name, but hierarchical names can be in abbreviated format. Case is not significant.
	 * @param level
	 *            The level that you want to assign to this person, group, or server in the ACL, of type ACL.Level.
	 * 
	 * @return The newly-created {@link org.openntf.domino.ACLEntry}.
	 * @since openntf.domino 1.0.0
	 * 
	 */
	public ACLEntry createACLEntry(String name, Level level);
}
