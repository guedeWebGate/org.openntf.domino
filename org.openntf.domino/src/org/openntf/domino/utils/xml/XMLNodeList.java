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
package org.openntf.domino.utils.xml;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLNodeList.
 * 
 * @author jgallagher
 */
public class XMLNodeList extends ArrayList<XMLNode> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5345253808779456477L;

	/**
	 * Instantiates a new xML node list.
	 */
	public XMLNodeList() {
		super();
	}

	/**
	 * Instantiates a new xML node list.
	 * 
	 * @param initialCapacity
	 *            the initial capacity
	 */
	public XMLNodeList(int initialCapacity) {
		super(initialCapacity);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public XMLNode remove(int i) {
		XMLNode result = super.remove(i);
		if (result != null) {
			result.getParentNode().removeChild(result);
		}
		return result;
	}

	/**
	 * Swap.
	 * 
	 * @param indexA
	 *            the index a
	 * @param indexB
	 *            the index b
	 */
	public void swap(int indexA, int indexB) {
		XMLNode a = this.get(indexA);
		XMLNode b = this.get(indexB);
		swap(a, b);
	}

	/**
	 * Swap.
	 * 
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 */
	public void swap(XMLNode a, XMLNode b) {
		XMLNode parentA = a.getParentNode();
		XMLNode siblingA = a.getNextSibling();

		XMLNode parentB = b.getParentNode();
		XMLNode siblingB = b.getNextSibling();

		if (siblingA == null) {
			parentA.appendChild(b);
		} else {
			parentA.insertBefore(b, siblingA);
		}
		if (siblingB == null) {
			parentB.appendChild(a);
		} else {
			parentB.insertBefore(a, siblingB);
		}
	}
}