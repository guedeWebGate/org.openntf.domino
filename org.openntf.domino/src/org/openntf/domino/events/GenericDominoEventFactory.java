/**
 * 
 */
package org.openntf.domino.events;

import java.util.logging.Logger;

import org.openntf.domino.Base;

/**
 * @author nfreeman
 * 
 */
public class GenericDominoEventFactory implements IDominoEventFactory {
	private static final Logger log_ = Logger.getLogger(GenericDominoEventFactory.class.getName());
	private static final long serialVersionUID = 1L;

	public static class GenericDominoEvent extends AbstractDominoEvent {

		/**
		 * @param event
		 * @param source
		 * @param target
		 * @param payload
		 */
		public GenericDominoEvent(final EnumEvent event, final Base source, final Base target, final Object payload) {
			super(event, source, target, payload);
		}

	}

	/**
	 * 
	 */
	public GenericDominoEventFactory() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.events.IDominoEventFactory#wrap(org.openntf.domino.events.IDominoEvent)
	 */
	@Override
	public IDominoEvent wrap(final IDominoEvent event) {
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.events.IDominoEventFactory#generate(java.lang.Object[])
	 */
	@Override
	public IDominoEvent generate(final EnumEvent event, final org.openntf.domino.Base source, final org.openntf.domino.Base target,
			final Object payload) {
		return new GenericDominoEvent(event, source, target, payload);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.events.IDominoEventFactory#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.events.IDominoEventFactory#terminate()
	 */
	@Override
	public void terminate() {
		// TODO Auto-generated method stub

	}
}
