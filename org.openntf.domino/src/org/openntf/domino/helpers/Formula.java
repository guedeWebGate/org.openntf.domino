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
package org.openntf.domino.helpers;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Logger;

import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.TypeUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Formula.
 * 
 * @author nfreeman
 */
public class Formula implements org.openntf.domino.ext.Formula, Serializable {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(Formula.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Class NoFormulaSetException.
	 */
	static class NoFormulaSetException extends RuntimeException {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Instantiates a new no formula set exception.
		 */
		NoFormulaSetException() {
			super("No expression has been set. There is nothing to evaluate.");
		}
	}

	/**
	 * The Class FormulaSyntaxException.
	 */
	static class FormulaSyntaxException extends RuntimeException {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The syntax details_. */
		private Vector<?> syntaxDetails_;
		// "errorMessage" : "errorLine" : "errorColumn" : "errorOffset" : "errorLength" : "errorText"
		/** The expression_. */
		private String expression_;

		/**
		 * Instantiates a new formula syntax exception.
		 * 
		 * @param expression
		 *            the expression
		 * @param syntaxDetails
		 *            the syntax details
		 */
		FormulaSyntaxException(String expression, Vector syntaxDetails) {
			super();
			expression_ = expression;
			syntaxDetails_ = syntaxDetails;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Throwable#getMessage()
		 */
		@Override
		public String getMessage() {
			return String.valueOf(syntaxDetails_.get(0));
		}

		/**
		 * Gets the expression.
		 * 
		 * @return the expression
		 */
		public String getExpression() {
			return expression_;
		}

		/**
		 * Gets the error line.
		 * 
		 * @return the error line
		 */
		public String getErrorLine() {
			return String.valueOf(syntaxDetails_.get(1));
		}

		/**
		 * Gets the error column.
		 * 
		 * @return the error column
		 */
		public String getErrorColumn() {
			return String.valueOf(syntaxDetails_.get(2));
		}

		/**
		 * Gets the error offset.
		 * 
		 * @return the error offset
		 */
		public String getErrorOffset() {
			return String.valueOf(syntaxDetails_.get(3));
		}

		/**
		 * Gets the error length.
		 * 
		 * @return the error length
		 */
		public String getErrorLength() {
			return String.valueOf(syntaxDetails_.get(4));
		}

		/**
		 * Gets the error text.
		 * 
		 * @return the error text
		 */
		public String getErrorText() {
			return String.valueOf(syntaxDetails_.get(5));
		}

	}

	/** The parent_. */
	private transient Session parent_;
	
	/** The expression_. */
	private String expression_;

	/**
	 * Instantiates a new formula.
	 */
	public Formula() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new formula.
	 * 
	 * @param parent
	 *            the parent
	 */
	public Formula(Session parent) {
		parent_ = parent;
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.ext.Formula#setSession(org.openntf.domino.Session)
	 */
	public void setSession(Session session) {
		parent_ = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#setExpression(java.lang.String)
	 */
	@Override
	public void setExpression(String expression) {
		Vector vec = getSession().evaluate("@CheckFormulaSyntax(" + expression + ")");
		if (vec.size() > 2) {
			throw new FormulaSyntaxException(expression, vec);
		}
		expression_ = expression;
	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	private Session getSession() {
		if (parent_ == null) {
			parent_ = Factory.getSession();
		}
		return parent_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Vector getValue() {
		if (expression_ == null)
			throw new NoFormulaSetException();
		Vector vec = getSession().evaluate(expression_);
		return vec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public <T> T getValue(Class<?> T) {
		Vector v = getValue();
		return TypeUtils.vectorToClass(v, T, getSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue()
	 */
	@SuppressWarnings("rawtypes")
	public Vector getValue(Session session) {
		if (expression_ == null)
			throw new NoFormulaSetException();
		Vector vec = session.evaluate(expression_);
		return vec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public <T> T getValue(Session session, Class<?> T) {
		Vector v = getValue(session);
		return TypeUtils.vectorToClass(v, T, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue(org.openntf.domino.Document)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Vector getValue(Document document) {
		if (expression_ == null)
			throw new NoFormulaSetException();
		Vector vec = document.getAncestorSession().evaluate(expression_, document);
		return vec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.ext.Formula#getValue(org.openntf.domino.Document, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public <T> T getValue(Document document, Class<?> T) {
		Vector v = getValue(document);
		return TypeUtils.vectorToClass(v, T, document.getAncestorSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		expression_ = in.readUTF();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(expression_);
	}
}
