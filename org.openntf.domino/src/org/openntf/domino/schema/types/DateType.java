/**
 * 
 */
package org.openntf.domino.schema.types;

import java.util.logging.Logger;

/**
 * @author nfreeman
 * 
 */
public class DateType extends AbstractDominoType {
	private static final Logger log_ = Logger.getLogger(DateType.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DateType() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.schema.types.IDominoType#getUITypeName()
	 */
	@Override
	public String getUITypeName() {
		return "Date";
	}
}
