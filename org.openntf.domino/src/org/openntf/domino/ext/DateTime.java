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

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Interface DateTime.
 * 
 * @author nfreeman
 */
public interface DateTime {

	/**
	 * Compares current date with another and returns boolean of whether they are the same.
	 * 
	 * @param comparDate
	 *            DateTime to compare to current date
	 * @return boolean, whether or not the two dates are the same
	 */
	public boolean equals(org.openntf.domino.DateTime comparDate);

	/**
	 * Equals ignore date.
	 * 
	 * @param comparDate
	 *            DateTime to compare to the current DateTime
	 * @return boolean is time is the same, including millisecond
	 */
	public boolean equalsIgnoreDate(org.openntf.domino.DateTime comparDate);

	/**
	 * Equals ignore time.
	 * 
	 * @param comparDate
	 *            DateTime to compare to the current DateTime
	 * @return boolean is date is the same
	 */
	public boolean equalsIgnoreTime(org.openntf.domino.DateTime comparDate);

	/**
	 * Compares current date with another and returns boolean of whether current date is after parameter.
	 * 
	 * @param comparDate
	 *            DateTime to compare to current date
	 * @return boolean, whether or not current date is after the parameter
	 */
	public boolean isAfter(org.openntf.domino.DateTime comparDate);

	/**
	 * Checks if is any time.
	 * 
	 * @return whether the DateTime is a date-only value (e.g. [1/1/2013])
	 */
	public boolean isAnyTime();

	/*
	 * 
	 * @return whether the DateTime is a time-only value (e.g. [1:00 PM])
	 */
	/**
	 * Checks if is any date.
	 * 
	 * @return true, if is any date
	 */
	public boolean isAnyDate();

	/**
	 * Compares current date with another and returns boolean of whether current date is before parameter.
	 * 
	 * @param comparDate
	 *            DateTime to compare to current date
	 * @return boolean, whether or not current date is before the parameter
	 */
	public boolean isBefore(org.openntf.domino.DateTime comparDate);

	/**
	 * To java cal.
	 * 
	 * @return Java Calendar object, same as used internally by org.openntf.domino.DateTime class
	 */
	public Calendar toJavaCal();
}
