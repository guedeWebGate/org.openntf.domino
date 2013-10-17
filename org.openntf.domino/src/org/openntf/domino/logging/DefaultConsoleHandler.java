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
package org.openntf.domino.logging;

import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultConsoleHandler.
 * 
 * @author withersp
 */
public class DefaultConsoleHandler extends ConsoleHandler {

	/** The ol debug level. */
	private static String olDebugLevel = "1";

	/**
	 * Instantiates a new default file handler.
	 * 
	 */
	public DefaultConsoleHandler() {

	}

	/**
	 * Gets the ol debug level.
	 * 
	 * @return String debug level.
	 * @see #setOlDebugLevel(String) for options
	 */
	public static String getOlDebugLevel() {
		return olDebugLevel;
	}

	/**
	 * This sets the "debug level" of all the methods. Currently the valid debug levels are:
	 * 
	 * @param olDebugLevel
	 *            String debug level. Options are:
	 *            <ul>
	 *            <li>0 -- internal errors are discarded</li>
	 *            <li>1 -- Exception messages from internal errors are printed</li>
	 *            <li>2 -- stack traces from internal errors are also printed</li>
	 *            </ul>
	 */
	public static void setOlDebugLevel(final String olDebugLevel) {
		DefaultConsoleHandler.olDebugLevel = olDebugLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.ConsoleHandler#close()
	 */
	@Override
	public void close() {
		super.close();
	}

	private void superPub(final LogRecord record) {
		super.publish(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.ConsoleHandler#publish(java.util.logging.LogRecord)
	 */
	@Override
	public void publish(final LogRecord record) {
		int debugLevel = 0;
		try {
			debugLevel = Integer.parseInt(olDebugLevel);
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + ": Error getting debug level - non-numeric");
		}
		if (debugLevel > 0) {
			try {
				AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
					@Override
					public Object run() throws Exception {
						DefaultConsoleHandler.this.superPub(record);
						return null;
					}
				});
			} catch (Throwable t) {
				t.printStackTrace();
			}
			if (debugLevel > 1) {
				if (record.getThrown() != null && record.getThrown() instanceof Exception) {
					Exception ee = (Exception) record.getThrown();
					ee.printStackTrace();
				}
			}
		}
	}
}
