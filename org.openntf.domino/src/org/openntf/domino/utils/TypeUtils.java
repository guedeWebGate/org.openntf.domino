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
package org.openntf.domino.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.openntf.domino.Document;
import org.openntf.domino.Item;
import org.openntf.domino.Session;
import org.openntf.domino.exceptions.DataNotCompatibleException;
import org.openntf.domino.exceptions.ItemNotFoundException;
import org.openntf.domino.exceptions.UnimplementedException;
import org.openntf.domino.impl.DateTime;
import org.openntf.domino.impl.Name;

// TODO: Auto-generated Javadoc
/**
 * The Enum TypeUtils.
 * 
 * @author nfreeman
 */
public enum TypeUtils {
	;

	/**
	 * Item value to class.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param doc
	 *            the doc
	 * @param itemName
	 *            the item name
	 * @param T
	 *            the t
	 * @return the t
	 */
	public static <T> T itemValueToClass(Document doc, String itemName, Class<?> T) {
		String noteid = doc.getNoteID();
		boolean hasItem = doc.hasItem(itemName);
		if (!hasItem) {
			Class<?> CType = null;
			if (T.isArray()) {
				CType = T.getComponentType();
			}
			if (T.isArray()) {
				if (CType.isPrimitive()) {
					throw new ItemNotFoundException("Item " + itemName + " was not found on document " + noteid
							+ " so we cannot return an array of " + CType.getName());
				} else {
					return null;
				}
			} else if (T.isPrimitive()) {
				throw new ItemNotFoundException("Item " + itemName + " was not found on document " + noteid + " so we cannot return a "
						+ T.getName());
			} else {
				return null;
			}
		}
		return itemValueToClass(doc.getFirstItem(itemName), T);
	}

	/**
	 * Item value to class.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param item
	 *            the item
	 * @param T
	 *            the t
	 * @return the t
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T itemValueToClass(Item item, Class<?> T) {
		// Object o = item.getAncestorDocument().getItemValue(item.getName());
		Vector v = item.getValues();
		Session session = Factory.getSession(item);
		T result = null;
		try {
			result = vectorToClass(v, T, session);
		} catch (DataNotCompatibleException e) {
			String noteid = item.getAncestorDocument().getNoteID();
			throw new DataNotCompatibleException(e.getMessage() + " for field " + item.getName() + " in document " + noteid);
		} catch (UnimplementedException e) {
			String noteid = item.getAncestorDocument().getNoteID();
			throw new UnimplementedException(e.getMessage() + ", so cannot auto-box for field " + item.getName() + " in document " + noteid);
		}

		return result;
	}

	/**
	 * Vector to class.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param v
	 *            the v
	 * @param T
	 *            the t
	 * @param session
	 *            the session
	 * @return the t
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T vectorToClass(Vector v, Class<?> T, Session session) {
		Object result = null;
		Class<?> CType = null;
		if (T.isArray()) {
			CType = T.getComponentType();
		}
		if (T.isArray()) {
			if (CType.isPrimitive()) {
				try {
					result = toPrimitiveArray(v, CType);
				} catch (DataNotCompatibleException e) {
					throw e;
				}
			} else {
				if (CType.isAssignableFrom(String.class)) {
					result = toStrings(v);
				} else if (CType.isAssignableFrom(Date.class)) {
					result = toDates(v);
				} else if (CType.isAssignableFrom(DateTime.class)) {
					result = toDateTimes(v, session);
				} else if (CType.isAssignableFrom(Name.class)) {
					result = toNames(v, session);
				}
				throw new UnimplementedException("Arrays for " + CType.getName() + " not yet implemented");
			}
		} else if (T.isPrimitive()) {
			try {
				result = toPrimitive(v, CType);
			} catch (DataNotCompatibleException e) {
				throw e;
			}
		} else {
			if (T.isAssignableFrom(String.class)) {
				result = join(v);
			} else if (T.isAssignableFrom(Date.class)) {
				result = toDate(v);
			} else if (T.isAssignableFrom(org.openntf.domino.DateTime.class)) {
				result = session.createDateTime(toDate(v));
			} else if (T.isAssignableFrom(org.openntf.domino.Name.class)) {
				result = session.createName(String.valueOf(v.get(0)));
			} else {
				if (!v.isEmpty()) {
					if (T.isAssignableFrom(Integer.class)) {
						result = ((Double) v.get(0)).intValue();
					} else {
						result = v.get(0);
					}
				}
			}
		}
		return (T) result;
	}

	/**
	 * To boolean.
	 * 
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean toBoolean(Object value) {
		if (value instanceof String) {
			char[] c = ((String) value).toCharArray();
			if (c.length > 1 || c.length == 0) {
				return false;
			} else {
				return c[0] == '1';
			}
		} else if (value instanceof Double) {
			if (((Double) value).intValue() == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to boolean primitive.");
		}
	}

	/**
	 * To int.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int toInt(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).intValue();
		} else if (value instanceof Double) {
			return ((Double) value).intValue();
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to int primitive.");
		}
	}

	/**
	 * To double.
	 * 
	 * @param value
	 *            the value
	 * @return the double
	 */
	public static double toDouble(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).doubleValue();
		} else if (value instanceof Double) {
			return ((Double) value).doubleValue();
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to double primitive.");
		}
	}

	/**
	 * To long.
	 * 
	 * @param value
	 *            the value
	 * @return the long
	 */
	public static long toLong(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).longValue();
		} else if (value instanceof Double) {
			return ((Double) value).longValue();
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to long primitive.");
		}
	}

	/**
	 * To short.
	 * 
	 * @param value
	 *            the value
	 * @return the short
	 */
	public static short toShort(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).shortValue();
		} else if (value instanceof Double) {
			return ((Double) value).shortValue();
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to short primitive.");
		}

	}

	/**
	 * To float.
	 * 
	 * @param value
	 *            the value
	 * @return the float
	 */
	public static float toFloat(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).floatValue();
		} else if (value instanceof Double) {
			return ((Double) value).floatValue();
		} else {
			throw new DataNotCompatibleException("Cannot convert a " + value.getClass().getName() + " to float primitive.");
		}

	}

	/**
	 * To primitive.
	 * 
	 * @param values
	 *            the values
	 * @param ctype
	 *            the ctype
	 * @return the object
	 */
	public static Object toPrimitive(Vector<Object> values, Class<?> ctype) {
		if (ctype.isPrimitive()) {
			throw new DataNotCompatibleException(ctype.getName() + " is not a primitive type.");
		}
		if (values.size() > 1) {
			throw new DataNotCompatibleException("Cannot create a primitive " + ctype + " from data because we have a multiple values.");
		}
		if (values.isEmpty()) {
			throw new DataNotCompatibleException("Cannot create a primitive " + ctype + " from data because we don't have any values.");
		}
		if (ctype == Boolean.TYPE)
			return toBoolean(values.get(0));
		if (ctype == Integer.TYPE)
			return toInt(values.get(0));
		if (ctype == Short.TYPE)
			return toShort(values.get(0));
		if (ctype == Long.TYPE)
			return toLong(values.get(0));
		if (ctype == Float.TYPE)
			return toFloat(values.get(0));
		if (ctype == Double.TYPE)
			return toDouble(values.get(0));
		if (ctype == Byte.TYPE)
			throw new UnimplementedException("Primitive conversion for byte not yet defined");
		if (ctype == Character.TYPE)
			throw new UnimplementedException("Primitive conversion for char not yet defined");
		throw new DataNotCompatibleException("");
	}

	/**
	 * Join.
	 * 
	 * @param values
	 *            the values
	 * @param separator
	 *            the separator
	 * @return the string
	 */
	public static String join(Collection<Object> values, String separator) {
		if (values == null)
			return "";
		StringBuilder sb = new StringBuilder();
		Iterator<Object> it = values.iterator();
		while (it.hasNext()) {
			sb.append(String.valueOf(it.next()));
			if (it.hasNext())
				sb.append(separator);
		}
		return sb.toString();
	}

	/**
	 * Join.
	 * 
	 * @param values
	 *            the values
	 * @return the string
	 */
	public static String join(Collection<Object> values) {
		return join(values, ", ");
	}

	/**
	 * To primitive array.
	 * 
	 * @param values
	 *            the values
	 * @param ctype
	 *            the ctype
	 * @return the object
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static Object toPrimitiveArray(Vector<Object> values, Class<?> ctype) throws DataNotCompatibleException {
		Object result = null;
		int size = values.size();
		if (ctype == Boolean.TYPE) {
			boolean[] outcome = new boolean[size];
			// TODO NTF - should allow for String fields that are binary sequences: "1001001" (SOS)
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toBoolean(o);
			}
			result = outcome;
		} else if (ctype == Byte.TYPE) {
			byte[] outcome = new byte[size];
			// TODO
			result = outcome;
		} else if (ctype == Character.TYPE) {
			char[] outcome = new char[size];
			// TODO How should this work? Just concatenate the char arrays for each String?
			result = outcome;
		} else if (ctype == Short.TYPE) {
			short[] outcome = new short[size];
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toShort(o);
			}
			result = outcome;
		} else if (ctype == Integer.TYPE) {
			int[] outcome = new int[size];
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toInt(o);
			}
			result = outcome;
		} else if (ctype == Long.TYPE) {
			long[] outcome = new long[size];
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toLong(o);
			}
			result = outcome;
		} else if (ctype == Float.TYPE) {
			float[] outcome = new float[size];
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toFloat(o);
			}
			result = outcome;
		} else if (ctype == Double.TYPE) {
			double[] outcome = new double[size];
			for (int i = 0; i < size; i++) {
				Object o = values.get(i);
				outcome[i] = toDouble(o);
			}
			result = outcome;
		}
		return result;
	}

	/**
	 * To date.
	 * 
	 * @param value
	 *            the value
	 * @return the date
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static Date toDate(Object value) throws DataNotCompatibleException {
		if (value == null)
			return null;
		if (value instanceof Vector) {
			value = ((Vector) value).get(0);
		}
		if (value instanceof Long) {
			return new Date(((Long) value).longValue());
		} else if (value instanceof String) {
			// TODO finish
			DateFormat df = new SimpleDateFormat();
			try {
				return df.parse((String) value);
			} catch (ParseException e) {
				throw new DataNotCompatibleException("Cannot create a Date from String value " + (String) value);
			}
		} else if (value instanceof lotus.domino.DateTime) {
			return DominoUtils.toJavaDateSafe((lotus.domino.DateTime) value);
		} else {
			throw new DataNotCompatibleException("Cannot create a Date from a " + value.getClass().getName());
		}
	}

	/**
	 * To dates.
	 * 
	 * @param vector
	 *            the vector
	 * @return the date[]
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static Date[] toDates(Collection<Object> vector) throws DataNotCompatibleException {
		if (vector == null)
			return null;

		Date[] result = new Date[vector.size()];
		int i = 0;
		for (Object o : vector) {
			result[i++] = toDate(o);
		}
		return result;
	}

	/**
	 * To date times.
	 * 
	 * @param vector
	 *            the vector
	 * @param session
	 *            the session
	 * @return the org.openntf.domino. date time[]
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static org.openntf.domino.DateTime[] toDateTimes(Collection<Object> vector, org.openntf.domino.Session session)
			throws DataNotCompatibleException {
		if (vector == null)
			return null;

		org.openntf.domino.DateTime[] result = new org.openntf.domino.DateTime[vector.size()];
		int i = 0;
		for (Object o : vector) {
			result[i++] = session.createDateTime(toDate(o));
		}
		return result;
	}

	/**
	 * To names.
	 * 
	 * @param vector
	 *            the vector
	 * @param session
	 *            the session
	 * @return the org.openntf.domino. name[]
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static org.openntf.domino.Name[] toNames(Collection<Object> vector, org.openntf.domino.Session session)
			throws DataNotCompatibleException {
		if (vector == null)
			return null;

		org.openntf.domino.Name[] result = new org.openntf.domino.Name[vector.size()];
		int i = 0;
		for (Object o : vector) {
			result[i++] = session.createName(String.valueOf(o));
		}
		return result;
	}

	/**
	 * To strings.
	 * 
	 * @param vector
	 *            the vector
	 * @return the string[]
	 * @throws DataNotCompatibleException
	 *             the data not compatible exception
	 */
	public static String[] toStrings(Collection<Object> vector) throws DataNotCompatibleException {
		if (vector == null)
			return null;
		String[] strings = new String[vector.size()];
		int i = 0;
		for (Object o : vector) {
			if (o instanceof DateTime) {
				strings[i++] = ((DateTime) o).getGMTTime();
			} else {
				strings[i++] = String.valueOf(o);
			}
		}
		return strings;
	}

}
