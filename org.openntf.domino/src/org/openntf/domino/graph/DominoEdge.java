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
package org.openntf.domino.graph;

import java.util.logging.Logger;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class DominoEdge.
 */
public class DominoEdge extends DominoElement implements Edge {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DominoEdge.class.getName());
	
	/** The Constant GRAPH_TYPE_VALUE. */
	public static final String GRAPH_TYPE_VALUE = "OpenEdge";
	
	/** The Constant IN_NAME. */
	public static final String IN_NAME = "_OPEN_IN";
	
	/** The Constant LABEL_NAME. */
	public static final String LABEL_NAME = "_OPEN_LABEL";
	
	/** The Constant OUT_NAME. */
	public static final String OUT_NAME = "_OPEN_OUT";
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The in_. */
	transient Vertex in_;
	
	/** The in key_. */
	private String inKey_;
	
	/** The out_. */
	transient Vertex out_;
	
	/** The out key_. */
	private String outKey_;

	/**
	 * Instantiates a new domino edge.
	 * 
	 * @param parent
	 *            the parent
	 * @param doc
	 *            the doc
	 */
	public DominoEdge(DominoGraph parent, org.openntf.domino.Document doc) {
		super(parent, doc);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Edge#getLabel()
	 */
	@Override
	public String getLabel() {
		return getProperty(DominoEdge.LABEL_NAME, String.class);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Edge#getVertex(com.tinkerpop.blueprints.Direction)
	 */
	@Override
	public Vertex getVertex(Direction direction) throws IllegalArgumentException {
		return parent_.getVertex(getVertexId(direction));
	}

	/**
	 * Gets the vertex id.
	 * 
	 * @param direction
	 *            the direction
	 * @return the vertex id
	 */
	public String getVertexId(Direction direction) {
		if (direction == Direction.IN) {
			if (inKey_ == null) {
				inKey_ = getProperty(DominoEdge.IN_NAME, String.class);
			}
			return inKey_;
		}
		if (direction == Direction.OUT) {
			if (outKey_ == null) {
				outKey_ = getProperty(DominoEdge.OUT_NAME, String.class);
			}
			return outKey_;
		}
		return null;
	}

	/**
	 * Relate.
	 * 
	 * @param in
	 *            the in
	 * @param out
	 *            the out
	 */
	public void relate(DominoVertex in, DominoVertex out) {
		setInDoc(in);
		setOutDoc(out);
	}

	/**
	 * Sets the in doc.
	 * 
	 * @param in
	 *            the new in doc
	 */
	public void setInDoc(Vertex in) {
		((DominoVertex) in).addInEdge(this);
		in_ = in;
		inKey_ = (String) in.getId();
		setProperty(DominoEdge.IN_NAME, inKey_);
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the new label
	 */
	void setLabel(String label) {
		setProperty(DominoEdge.LABEL_NAME, label);
	}

	/**
	 * Sets the out doc.
	 * 
	 * @param out
	 *            the new out doc
	 */
	public void setOutDoc(Vertex out) {
		((DominoVertex) out).addOutEdge(this);
		out_ = out;
		outKey_ = (String) out.getId();
		setProperty(DominoEdge.OUT_NAME, outKey_);
	}

	// @Override
	// public void save() {
	// Object o = getProperty("Form", String.class);
	// if (o == null || ((String) o).length() < 1) {
	// setProperty("Form", DominoEdge.GRAPH_TYPE_VALUE);
	// }
	// super.save();
	// }

}
