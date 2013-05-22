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

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Session.RunContext;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewEntryCollection;
import org.openntf.domino.transactions.DatabaseTransaction;
import org.openntf.domino.utils.DominoUtils;
import org.openntf.domino.utils.Factory;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.MetaGraph;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.DefaultGraphQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class DominoGraph.
 */
public class DominoGraph implements Graph, MetaGraph, TransactionalGraph {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DominoGraph.class.getName());

	/** The Constant EDGE_VIEW_NAME. */
	public static final String EDGE_VIEW_NAME = "(_OPEN_Edges)";
	
	/** The Constant VERTEX_VIEW_NAME. */
	public static final String VERTEX_VIEW_NAME = "(_OPEN_Vertices)";
	
	/** The Constant FEATURES. */
	private static final Features FEATURES = new Features();
	
	/** The Constant COMPRESS_IDS. */
	public static final boolean COMPRESS_IDS = false;

	static {
		DominoGraph.FEATURES.supportsDuplicateEdges = true;
		DominoGraph.FEATURES.supportsSelfLoops = true;
		DominoGraph.FEATURES.supportsSerializableObjectProperty = false;
		DominoGraph.FEATURES.supportsBooleanProperty = true;
		DominoGraph.FEATURES.supportsDoubleProperty = true;
		DominoGraph.FEATURES.supportsFloatProperty = false;
		DominoGraph.FEATURES.supportsIntegerProperty = true;
		DominoGraph.FEATURES.supportsPrimitiveArrayProperty = false;
		DominoGraph.FEATURES.supportsUniformListProperty = true;
		DominoGraph.FEATURES.supportsMixedListProperty = false;
		DominoGraph.FEATURES.supportsLongProperty = false;
		DominoGraph.FEATURES.supportsMapProperty = false;
		DominoGraph.FEATURES.supportsStringProperty = true;

		DominoGraph.FEATURES.ignoresSuppliedIds = false;

		DominoGraph.FEATURES.isWrapper = false;

		DominoGraph.FEATURES.supportsIndices = false;
		DominoGraph.FEATURES.supportsKeyIndices = false;
		DominoGraph.FEATURES.supportsVertexKeyIndex = false;
		DominoGraph.FEATURES.supportsEdgeKeyIndex = false;
		DominoGraph.FEATURES.supportsVertexIndex = false;
		DominoGraph.FEATURES.supportsEdgeIndex = false;
		DominoGraph.FEATURES.supportsTransactions = false;
		DominoGraph.FEATURES.supportsVertexIteration = false;
		DominoGraph.FEATURES.supportsEdgeIteration = false;
		DominoGraph.FEATURES.supportsEdgeRetrieval = true;
		DominoGraph.FEATURES.supportsVertexProperties = true;
		DominoGraph.FEATURES.supportsEdgeProperties = true;
		DominoGraph.FEATURES.supportsThreadedTransactions = false;
		DominoGraph.FEATURES.isPersistent = true;

	}

	/** The cache_. */
	private java.util.Map<Object, DominoElement> cache_;

	/** The database_. */
	private transient org.openntf.domino.Database database_;
	
	/** The filepath_. */
	private String filepath_;
	
	/** The server_. */
	private String server_;
	
	/** The session_. */
	private transient org.openntf.domino.Session session_;

	/**
	 * Instantiates a new domino graph.
	 * 
	 * @param database
	 *            the database
	 */
	public DominoGraph(org.openntf.domino.Database database) {
		setRawDatabase(database);
		RunContext rc = Factory.getRunContext();
		// System.out.println("Context: " + rc.toString());
	}

	/**
	 * Sets the raw database.
	 * 
	 * @param database
	 *            the new raw database
	 */
	public void setRawDatabase(org.openntf.domino.Database database) {
		if (database != null) {
			database_ = database;
			session_ = database.getParent();
			server_ = database.getServer();
			filepath_ = database.getFilePath();
		}
	}

	/**
	 * Gets the cache.
	 * 
	 * @return the cache
	 */
	private java.util.Map<Object, DominoElement> getCache() {
		if (cache_ == null) {
			cache_ = new HashMap<Object, DominoElement>();
		}
		return cache_;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#addEdge(java.lang.Object, com.tinkerpop.blueprints.Vertex, com.tinkerpop.blueprints.Vertex, java.lang.String)
	 */
	@Override
	public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
		startTransaction();
		if (id == null)
			id = (outVertex.getId() + label + inVertex.getId());
		Document d = getDocument(id, true);
		d.replaceItemValue(DominoElement.TYPE_FIELD, DominoEdge.GRAPH_TYPE_VALUE);
		DominoEdge ed = new DominoEdge(this, d);
		getCache().put(id == null ? ed.getId() : id, ed);
		ed.setLabel(label);
		ed.setOutDoc(outVertex);
		ed.setInDoc(inVertex);
		return ed;
	}

	/**
	 * Gets the or add edge.
	 * 
	 * @param id
	 *            the id
	 * @param outVertex
	 *            the out vertex
	 * @param inVertex
	 *            the in vertex
	 * @param label
	 *            the label
	 * @return the or add edge
	 */
	public Edge getOrAddEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
		Edge result = null;
		if (id == null) {
			id = (outVertex.getId() + label + inVertex.getId());
			result = getEdge(id);
			((DominoEdge) result).setLabel(label);
			((DominoEdge) result).setOutDoc(outVertex);
			((DominoEdge) result).setInDoc(inVertex);
		}
		if (result == null) {
			for (Edge e : outVertex.getEdges(Direction.OUT, label)) {
				Vertex v = e.getVertex(Direction.IN);
				if (v.getId().equals(inVertex.getId())) {
					result = e;
					((DominoEdge) result).setLabel(label);
					((DominoEdge) result).setOutDoc(outVertex);
					((DominoEdge) result).setInDoc(inVertex);
					break;
				}
			}
		}
		if (result == null) {
			result = addEdge(id, outVertex, inVertex, label);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#addVertex(java.lang.Object)
	 */
	@Override
	public Vertex addVertex(Object id) {
		startTransaction();
		Document d = getDocument(id, true);
		d.replaceItemValue(DominoElement.TYPE_FIELD, DominoVertex.GRAPH_TYPE_VALUE);
		DominoVertex result = new DominoVertex(this, d);
		getCache().put(id == null ? result.getId() : id, result);
		return result;
	}

	/**
	 * Gets the database.
	 * 
	 * @return the database
	 */
	private org.openntf.domino.Database getDatabase() {
		return getRawSession().getDatabase(server_, filepath_);
	}

	/**
	 * Gets the raw session.
	 * 
	 * @return the raw session
	 */
	public org.openntf.domino.Session getRawSession() {
		if (session_ == null) {
			session_ = Factory.getSession();
		} else {
			try {
				session_.isTrustedSession();
			} catch (Exception xPagesDidThis) {
				session_ = Factory.getSession();
			}
		}
		return session_;
	}

	/**
	 * Gets the raw database.
	 * 
	 * @return the raw database
	 */
	public org.openntf.domino.Database getRawDatabase() {
		if (database_ == null) {
			database_ = getDatabase();
		}
		return database_;
	}

	/**
	 * Gets the document.
	 * 
	 * @param id
	 *            the id
	 * @param createOnFail
	 *            the create on fail
	 * @return the document
	 */
	private Document getDocument(Object id, boolean createOnFail) {
		Document result = null;
		String unid = "";
		if (id == null) {
			result = getRawDatabase().createDocument();
		} else if (id instanceof String) {
			String sid = (String) id;
			if (DominoUtils.isUnid(sid)) {
				unid = sid;
			} else if (sid.length() > 32) {
				unid = DominoUtils.toUnid(sid);
			} else {

				unid = DominoUtils.toUnid(sid);
			}
		} else if (id instanceof Serializable) {
			unid = DominoUtils.toUnid((Serializable) id);
		}
		if (id != null && !DominoUtils.isUnid(unid)) {
			log_.log(Level.WARNING, "ALERT! INVALID UNID FROM id type " + (id == null ? "null" : id.getClass().getName()) + ": " + id);
		}
		if (result == null) {
			result = getRawDatabase().getDocumentByUNID(unid);
			if (result == null) {
				// TODO replace with a straight .get call to Database
				result = getRawDatabase().createDocument();
				result.setUniversalID(unid);
				DateTime now = getRawDatabase().getParent().createDateTime(new Date());
				result.replaceItemValue("$Created", now);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getEdge(java.lang.Object)
	 */
	@Override
	public Edge getEdge(Object id) {
		if (!getCache().containsKey(id)) {
			Document d = getDocument(id, false);
			if (d == null)
				return null;
			if (d.isDeleted()) {
				// System.out.println("Found edge for id " + String.valueOf(id) + " but it's been deleted.");
				return null;
			}
			DominoEdge result = new DominoEdge(this, d);
			getCache().put(id, result);
		}
		return (Edge) getCache().get(id);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getEdges()
	 */
	@Override
	public Iterable<Edge> getEdges() {
		Set<Edge> result = new LinkedHashSet<Edge>();
		ViewEntryCollection vec = getEdgeView().getAllEntries();
		for (ViewEntry entry : vec) {
			result.add(getEdge(entry.getUniversalID()));
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getEdges(java.lang.String, java.lang.Object)
	 */
	@Override
	public Iterable<Edge> getEdges(String key, Object value) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the edges from ids.
	 * 
	 * @param set
	 *            the set
	 * @return the edges from ids
	 */
	public Iterable<Edge> getEdgesFromIds(Set<String> set) {
		Set<Edge> result = new HashSet<Edge>();
		for (String id : set) {
			result.add(getEdge(id));
		}
		return result;
	}

	/**
	 * Gets the edges from ids.
	 * 
	 * @param set
	 *            the set
	 * @param labels
	 *            the labels
	 * @return the edges from ids
	 */
	public Iterable<Edge> getEdgesFromIds(Set<String> set, String... labels) {
		Set<Edge> result = new HashSet<Edge>();
		for (String id : set) {
			Edge edge = getEdge(id);
			for (String label : labels) {
				if (label.equals(edge.getLabel())) {
					result.add(edge);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Gets the edge view.
	 * 
	 * @return the edge view
	 */
	private View getEdgeView() {
		View result = getRawDatabase().getView(DominoGraph.EDGE_VIEW_NAME);
		if (result == null) {
			result = getRawDatabase().createView(DominoGraph.EDGE_VIEW_NAME,
					"SELECT " + DominoElement.TYPE_FIELD + "=\"" + DominoEdge.GRAPH_TYPE_VALUE + "\"", null, false);
			org.openntf.domino.ViewColumn column1 = result.createColumn();
			column1.setTitle("Created");
			column1.setFormula("@Created");
			column1.setSortDescending(true);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getFeatures()
	 */
	@Override
	public Features getFeatures() {
		return DominoGraph.FEATURES;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getVertex(java.lang.Object)
	 */
	@Override
	public Vertex getVertex(Object id) {
		if (!getCache().containsKey(id)) {
			Document d = getDocument(id, false);
			if (d == null)
				return null;
			if (d.isDeleted()) {
				// System.out.println("Found vertex for id " + String.valueOf(id) + " but it's been deleted.");
				return null;
			}
			DominoVertex result = new DominoVertex(this, d);
			getCache().put(id, result);
		}
		return (Vertex) getCache().get(id);
	}

	/**
	 * Gets the vertex view.
	 * 
	 * @return the vertex view
	 */
	private View getVertexView() {
		View result = getRawDatabase().getView(DominoGraph.VERTEX_VIEW_NAME);
		if (result == null) {
			result = getRawDatabase().createView(DominoGraph.VERTEX_VIEW_NAME,
					"SELECT " + DominoElement.TYPE_FIELD + "=\"" + DominoVertex.GRAPH_TYPE_VALUE + "\"", null, false);
			org.openntf.domino.ViewColumn column1 = result.createColumn();
			column1.setTitle("Created");
			column1.setFormula("@Created");
			column1.setSortDescending(true);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getVertices()
	 */
	@Override
	public Iterable<Vertex> getVertices() {
		Set<Vertex> result = new LinkedHashSet<Vertex>();
		ViewEntryCollection vec = getVertexView().getAllEntries();
		for (ViewEntry entry : vec) {
			result.add(getVertex(entry.getUniversalID()));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#getVertices(java.lang.String, java.lang.Object)
	 */
	@Override
	public Iterable<Vertex> getVertices(String key, Object value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#query()
	 */
	@Override
	public GraphQuery query() {
		return new DefaultGraphQuery(this);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#removeEdge(com.tinkerpop.blueprints.Edge)
	 */
	@Override
	public void removeEdge(Edge edge) {
		startTransaction();
		Vertex in = edge.getVertex(Direction.IN);
		((DominoVertex) in).removeEdge(edge);
		Vertex out = edge.getVertex(Direction.OUT);
		((DominoVertex) out).removeEdge(edge);
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#removeVertex(com.tinkerpop.blueprints.Vertex)
	 */
	@Override
	public void removeVertex(Vertex vertex) {
		startTransaction();
		DominoVertex dv = (DominoVertex) vertex;
		for (Edge edge : dv.getEdges(Direction.BOTH)) {
			removeEdge(edge);
		}

	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Graph#shutdown()
	 */
	@Override
	public void shutdown() {
		commit();
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.MetaGraph#getRawGraph()
	 */
	@Override
	public Object getRawGraph() {
		return getRawDatabase();
	}

	/** The in transaction_. */
	private boolean inTransaction_ = false;
	
	/** The txn_. */
	private DatabaseTransaction txn_;

	/**
	 * Start transaction.
	 */
	public void startTransaction() {
		if (!inTransaction_) {
			// System.out.println("Not yet in transaction. Starting...");
			txn_ = getRawDatabase().startTransaction();
			inTransaction_ = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tinkerpop.blueprints.TransactionalGraph#stopTransaction(com.tinkerpop.blueprints.TransactionalGraph.Conclusion)
	 */
	@Override
	@Deprecated
	public void stopTransaction(Conclusion conclusion) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tinkerpop.blueprints.TransactionalGraph#commit()
	 */
	@Override
	public void commit() {
		if (inTransaction_) {
			// System.out.println("Committing transaction");

			if (txn_ == null) {
				// System.out.println("Transaction is null!?!?!");
			} else {
				if (getCache().size() > 0) {
					for (DominoElement elem : getCache().values()) {
						if (elem instanceof DominoVertex) {
							((DominoVertex) elem).writeEdges();
						}
					}
				} else {
					// System.out.println("ELEMENT CACHE IS EMPTY!??!");
				}
				txn_.commit();
				txn_ = null;
			}
			inTransaction_ = false;

		} else {
			// System.out.println("Not in transaction!");
		}
		getCache().clear();
		// System.out.println("Transaction complete");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tinkerpop.blueprints.TransactionalGraph#rollback()
	 */
	@Override
	public void rollback() {
		if (inTransaction_) {
			// System.out.println("Rollbacking transaction");

			if (txn_ == null) {
				// System.out.println("Transaction is null!?!?!");
			} else {
				txn_.rollback();
				txn_ = null;
			}
			inTransaction_ = false;

		} else {
			// System.out.println("Not in transaction!");
		}
		getCache().clear();
		System.out.println("Transaction rollbacked");
	}

}
