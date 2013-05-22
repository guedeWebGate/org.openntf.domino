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
package org.openntf.domino.design.impl;

import java.util.Iterator;
import java.util.logging.Logger;

import org.openntf.domino.Document;
import org.openntf.domino.NoteCollection;
import org.openntf.domino.design.DesignBase;

// TODO: Auto-generated Javadoc
/**
 * The Class DesignCollection.
 * 
 * @param <E>
 *            the element type
 * @author jgallagher
 */
public class DesignCollection<E extends DesignBase> implements org.openntf.domino.design.DesignCollection<E> {
	
	/** The Constant log_. */
	@SuppressWarnings("unused")
	private static final Logger log_ = Logger.getLogger(DesignCollection.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The collection_. */
	private final NoteCollection collection_;
	
	/** The clazz_. */
	private final Class<? extends AbstractDesignBase> clazz_;

	/**
	 * Instantiates a new design collection.
	 * 
	 * @param collection
	 *            the collection
	 * @param clazz
	 *            the clazz
	 */
	public DesignCollection(final NoteCollection collection, final Class<? extends AbstractDesignBase> clazz) {
		collection_ = collection;
		clazz_ = clazz;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DesignCollection#getCount()
	 */
	public int getCount() {
		return collection_.getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new DesignIterator<E>();
	}

	/**
	 * The Class DesignIterator.
	 * 
	 * @param <T>
	 *            the generic type
	 */
	public class DesignIterator<T extends E> implements Iterator<T> {
		
		/** The iterator_. */
		private final Iterator<String> iterator_;

		/**
		 * Instantiates a new design iterator.
		 */
		protected DesignIterator() {
			iterator_ = collection_.iterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return iterator_.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			String noteId = iterator_.next();
			Document doc = collection_.getAncestorDatabase().getDocumentByID(noteId);
			return DesignFactory.fromDocument(doc, clazz_);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
