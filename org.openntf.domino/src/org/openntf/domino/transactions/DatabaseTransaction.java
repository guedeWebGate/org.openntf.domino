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
package org.openntf.domino.transactions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import org.openntf.domino.Agent;
import org.openntf.domino.Database;
import org.openntf.domino.Document;
import org.openntf.domino.Outline;
import org.openntf.domino.types.DatabaseDescendant;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseTransaction.
 */
public class DatabaseTransaction {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DatabaseTransaction.class.getName());

	/** The database_. */
	private final Database database_;
	
	/** The is committing_. */
	private boolean isCommitting_ = false;
	
	/** The update queue_. */
	private Queue<DatabaseDescendant> updateQueue_;
	
	/** The remove queue_. */
	private Queue<DatabaseDescendant> removeQueue_;

	/**
	 * Instantiates a new database transaction.
	 * 
	 * @param database
	 *            the database
	 */
	public DatabaseTransaction(org.openntf.domino.Database database) {
		database_ = database;
	}

	/**
	 * Checks if is doc lock.
	 * 
	 * @return true, if is doc lock
	 */
	private boolean isDocLock() {
		return database_.isDocumentLockingEnabled();
	}

	/**
	 * Checks if is design lock.
	 * 
	 * @return true, if is design lock
	 */
	private boolean isDesignLock() {
		return database_.isDesignLockingEnabled();
	}

	/**
	 * Gets the update queue.
	 * 
	 * @return the update queue
	 */
	protected Queue<DatabaseDescendant> getUpdateQueue() {
		if (updateQueue_ == null) {
			updateQueue_ = new LinkedList<DatabaseDescendant>(); // TODO NTF - Switch to ArrayBlockingQueue and manage total handles?
		}
		return updateQueue_;
	}

	/**
	 * Gets the removes the queue.
	 * 
	 * @return the removes the queue
	 */
	protected Queue<DatabaseDescendant> getRemoveQueue() {
		if (removeQueue_ == null) {
			removeQueue_ = new LinkedList<DatabaseDescendant>(); // TODO NTF - Switch to ArrayBlockingQueue and manage total handles?
		}
		return removeQueue_;
	}

	/**
	 * Queue update.
	 * 
	 * @param base
	 *            the base
	 */
	public void queueUpdate(DatabaseDescendant base) {
		getUpdateQueue().add(base); // TODO - NTF get locks when stuff is queued
		if (isDocLock() && base instanceof Document) {
			((Document) base).lock();
		}
		if (isDesignLock()) {
			if (base instanceof Agent) {
				((Agent) base).lock();
			} else if (base instanceof Outline) {
				// TODO - what? Outline doesn't have a .lock() method
			} else {
				// TODO - NTF build View/Form change transaction cache. Not fun. :-(
			}
		}
	}

	/**
	 * Queue remove.
	 * 
	 * @param base
	 *            the base
	 */
	public void queueRemove(DatabaseDescendant base) {
		getRemoveQueue().add(base); // TODO - NTF get locks when stuff is queued
		if (isDocLock() && base instanceof Document) {
			((Document) base).lock();
		}
		if (isDesignLock()) {
			if (base instanceof Agent) {
				((Agent) base).lock();
			} else if (base instanceof Outline) {
				// TODO - what? Outline doesn't have a .lock() method
			} else {
				// TODO - NTF build View/Form change transaction cache. Not fun. :-(
			}
		}
	}

	// public boolean isCommitting() {
	// return isCommitting_;
	// }

	/**
	 * Commit.
	 */
	public void commit() {
		// System.out.println("Committing transaction with update size " + getUpdateQueue().size());
		isCommitting_ = true;
		DatabaseDescendant next = getUpdateQueue().poll();
		while (next != null) {
			if (next instanceof Document) {
				boolean result = ((Document) next).save();
				if (!result) {
					// TODO NTF - take some action to indicate that the save failed, potentially cancelling the transaction
				} else {
					if (isDocLock())
						((Document) next).unlock();
				}
			}
			// TODO NTF - Implement other database objects
			next = getUpdateQueue().poll();
		}
		next = getRemoveQueue().poll();
		while (next != null) {
			if (next instanceof org.openntf.domino.impl.Document) {
				org.openntf.domino.impl.Document doc = (org.openntf.domino.impl.Document) next;
				if (isDocLock())
					doc.unlock();
				doc.forceDelegateRemove();
			}
			// TODO NTF - Implement other database objects
			next = getUpdateQueue().poll();
		}

		database_.closeTransaction();
	}

	/**
	 * Rollback.
	 */
	public void rollback() {
		// TODO - NTF release locks
		DatabaseDescendant next = getUpdateQueue().poll();
		while (next != null) {
			if (next instanceof org.openntf.domino.impl.Document) {
				org.openntf.domino.impl.Document doc = (org.openntf.domino.impl.Document) next;
				doc.rollback();
				if (isDocLock()) {
					doc.unlock();
				}
			}
			// TODO NTF - Implement other database objects
			next = getUpdateQueue().poll();
		}
		next = getRemoveQueue().poll();
		while (next != null) {
			if (next instanceof org.openntf.domino.impl.Document) {
				org.openntf.domino.impl.Document doc = (org.openntf.domino.impl.Document) next;
				doc.rollback();
				if (isDocLock())
					doc.unlock();
			}
			// TODO NTF - Implement other database objects
			next = getUpdateQueue().poll();
		}
		database_.closeTransaction();
	}

}
