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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import com.tinkerpop.blueprints.util.DefaultVertexQuery;
import com.tinkerpop.blueprints.util.MultiIterable;
import com.tinkerpop.blueprints.util.VerticesFromEdgesIterable;

// TODO: Auto-generated Javadoc
/**
 * The Class DominoVertex.
 */
public class DominoVertex extends DominoElement implements Vertex {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DominoVertex.class.getName());
	
	/** The Constant GRAPH_TYPE_VALUE. */
	public static final String GRAPH_TYPE_VALUE = "OpenVertex";
	
	/** The Constant IN_NAME. */
	public static final String IN_NAME = "_OPEN_IN";
	
	/** The Constant OUT_NAME. */
	public static final String OUT_NAME = "_OPEN_OUT";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The in dirty_. */
	private transient boolean inDirty_ = false;
	
	/** The in edges_. */
	private Set<String> inEdges_;
	
	/** The out dirty_. */
	private transient boolean outDirty_ = false;
	
	/** The out edges_. */
	private Set<String> outEdges_;

	/**
	 * Instantiates a new domino vertex.
	 * 
	 * @param parent
	 *            the parent
	 * @param doc
	 *            the doc
	 */
	public DominoVertex(DominoGraph parent, org.openntf.domino.Document doc) {
		super(parent, doc);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#addEdge(java.lang.String, com.tinkerpop.blueprints.Vertex)
	 */
	@Override
	public Edge addEdge(String label, Vertex vertex) {
		return parent_.addEdge(null, this, vertex, label);
	}

	/**
	 * Adds the in edge.
	 * 
	 * @param edge
	 *            the edge
	 */
	void addInEdge(Edge edge) {
		if (!getInEdges().contains((String) edge.getId())) {
			getParent().startTransaction();
			inDirty_ = true;
			getInEdges().add((String) edge.getId());
		}
		// setProperty(DominoVertex.IN_NAME, inEdges_);
	}

	/**
	 * Adds the out edge.
	 * 
	 * @param edge
	 *            the edge
	 */
	void addOutEdge(Edge edge) {
		if (!getOutEdges().contains((String) edge.getId())) {
			getParent().startTransaction();
			outDirty_ = true;
			getOutEdges().add((String) edge.getId());
		}
		// setProperty(DominoVertex.OUT_NAME, outEdges_);
	}

	/**
	 * Gets the both edges.
	 * 
	 * @return the both edges
	 */
	public java.util.Set<String> getBothEdges() {
		Set<String> result = getInEdges();
		result.addAll(getOutEdges());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getEdges(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Edge> getEdges(Direction direction, String... labels) {
		if (direction == Direction.IN) {
			return getParent().getEdgesFromIds(getInEdges(), labels);
		} else if (direction == Direction.OUT) {
			return getParent().getEdgesFromIds(getOutEdges(), labels);
		} else {
			return getParent().getEdgesFromIds(getBothEdges(), labels);
		}
	}

	/**
	 * Gets the in edges.
	 * 
	 * @return the in edges
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getInEdges() {
		if (inEdges_ == null) {
			Object o = getProperty(DominoVertex.IN_NAME, java.util.Collection.class);

			if (o != null) {
				if (o instanceof LinkedHashSet) {
					inEdges_ = (LinkedHashSet) o;
					// if (getRawDocument().getItemValueString("form").equalsIgnoreCase("container")) {
					// log_.log(Level.WARNING,
					// "Retrieved InEdges for " + getProperty("Filepath") + " to a LinkedHashSet of " + inEdges_.size());
					// }
				} else if (o instanceof java.util.Collection) {
					inEdges_ = new LinkedHashSet<String>((Collection<String>) o);
					// if (getRawDocument().getItemValueString("form").equalsIgnoreCase("container")) {
					// log_.log(Level.WARNING, "Retrieved InEdges for " + getProperty("Filepath")
					// + " to a Collection that made a new LinkedHashSet of " + inEdges_.size());
					// }
				} else {
					log_.log(Level.WARNING, "ALERT! InEdges returned something other than a Collection " + o.getClass().getName());
				}
			} else {
				// if (getRawDocument().getItemValueString("form").equalsIgnoreCase("container")) {
				// log_.log(Level.WARNING, "No InEdges found for Container " + getProperty("Filepath") + " creating new...");
				// }
				inEdges_ = new LinkedHashSet<String>();
			}
		}
		return inEdges_;
	}

	/**
	 * Gets the out edges.
	 * 
	 * @return the out edges
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getOutEdges() {
		if (outEdges_ == null) {
			Object o = getProperty(DominoVertex.OUT_NAME, java.util.Collection.class);
			if (o != null) {
				if (o instanceof LinkedHashSet) {
					outEdges_ = (LinkedHashSet) o;
				} else if (o instanceof java.util.Collection) {
					outEdges_ = new LinkedHashSet<String>((Collection<String>) o);

				} else {
					log_.log(Level.WARNING, "ALERT! OutEdges returned something other than a Collection " + o.getClass().getName());
				}
			} else {
				outEdges_ = new LinkedHashSet<String>();
			}
		}
		return outEdges_;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getVertices(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Vertex> getVertices(Direction direction, String... labels) {
		if (direction == Direction.BOTH) {
			List<Iterable<Vertex>> list = new ArrayList<Iterable<Vertex>>();
			list.add(new VerticesFromEdgesIterable(this, Direction.IN, labels));
			list.add(new VerticesFromEdgesIterable(this, Direction.OUT, labels));
			return new MultiIterable<Vertex>(list);
		} else {
			return new VerticesFromEdgesIterable(this, direction, labels);
		}
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#query()
	 */
	@Override
	public VertexQuery query() {
		return new DefaultVertexQuery(this);
	}

	/**
	 * Removes the edge.
	 * 
	 * @param edge
	 *            the edge
	 */
	public void removeEdge(Edge edge) {
		getParent().startTransaction();
		getInEdges().remove(edge.getId());
		inDirty_ = true;
		getOutEdges().remove(edge.getId());
		outDirty_ = true;
	}

	/**
	 * Write edges.
	 */
	void writeEdges() {
		if (inDirty_) {
			setProperty(DominoVertex.IN_NAME, inEdges_);
			setProperty(DominoVertex.IN_NAME + "_COUNT", inEdges_.size());
			inDirty_ = false;
		}
		if (outDirty_) {
			setProperty(DominoVertex.OUT_NAME, outEdges_);
			setProperty(DominoVertex.OUT_NAME + "_COUNT", outEdges_.size());
			outDirty_ = false;
		}
	}

}
