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

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Interface DateRange.
 * 
 * @author jgallagher
 */
public interface DateRange {
	
	/**
	 * Contains.
	 * 
	 * @param dt
	 *            the dt
	 * @return true, if successful
	 */
	public boolean contains(org.openntf.domino.DateTime dt);

	/**
	 * Contains.
	 * 
	 * @param date
	 *            the date
	 * @return true, if successful
	 */
	public boolean contains(Date date);
}
