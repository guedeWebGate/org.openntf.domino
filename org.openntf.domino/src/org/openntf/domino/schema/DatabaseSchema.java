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
package org.openntf.domino.schema;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.openntf.domino.Database;
import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Item;
import org.openntf.domino.annotations.Incomplete;
import org.openntf.domino.ext.Formula;
import org.openntf.domino.utils.DominoUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseSchema.
 * 
 * @author nfreeman
 */
@Incomplete
public class DatabaseSchema implements Externalizable {
	
	/** The Constant log_. */
	private static final Logger log_ = Logger.getLogger(DatabaseSchema.class.getName());
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Enum Flags.
	 */
	public static enum Flags {
		
		/** The summary. */
		SUMMARY, 
 /** The readers. */
 READERS, 
 /** The authors. */
 AUTHORS, 
 /** The protected. */
 PROTECTED, 
 /** The signed. */
 SIGNED, 
 /** The encrypted. */
 ENCRYPTED
	}

	/**
	 * The Class DocumentDefinition.
	 */
	public static class DocumentDefinition implements Externalizable {
		
		/** The name_. */
		private String name_; // will be used as Form field value
		
		/** The item definition keys_. */
		private Set<String> itemDefinitionKeys_ = new HashSet<String>();
		
		/** The item defs_. */
		private transient Set<ItemDefinition> itemDefs_;
		
		/** The override labels_. */
		private Map<String, String> overrideLabels_ = new HashMap<String, String>();
		
		/** The default summary_. */
		private boolean defaultSummary_ = true;
		
		/** The parent schema_. */
		private transient DatabaseSchema parentSchema_;

		/**
		 * Instantiates a new document definition.
		 */
		public DocumentDefinition() {

		}

		/**
		 * Sets the parent.
		 * 
		 * @param parent
		 *            the new parent
		 */
		public void setParent(DatabaseSchema parent) {
			parentSchema_ = parent;
		}

		/**
		 * Gets the name.
		 * 
		 * @return the name
		 */
		public String getName() {
			return name_;
		}

		/**
		 * Sets the name.
		 * 
		 * @param name
		 *            the new name
		 */
		public void setName(String name) {
			name_ = name;
		}

		/**
		 * Checks if is default summary.
		 * 
		 * @return true, if is default summary
		 */
		public boolean isDefaultSummary() {
			return defaultSummary_;
		}

		/**
		 * Sets the default summary.
		 * 
		 * @param defaultSummary
		 *            the new default summary
		 */
		public void setDefaultSummary(boolean defaultSummary) {
			defaultSummary_ = defaultSummary;
		}

		/**
		 * Gets the item definitions.
		 * 
		 * @return the item definitions
		 */
		public Set<ItemDefinition> getItemDefinitions() {
			if (itemDefs_ == null) {
				itemDefs_ = new HashSet<ItemDefinition>();
				for (String key : getItemDefinitionKeys()) {
					ItemDefinition id = parentSchema_.getItemDefinitions().get(key);
					if (id != null) {
						itemDefs_.add(id);
					}
				}
			}
			return itemDefs_;
		}

		/**
		 * Gets the item definition keys.
		 * 
		 * @return the item definition keys
		 */
		public Set<String> getItemDefinitionKeys() {
			return itemDefinitionKeys_;
		}

		/**
		 * Sets the item definition keys.
		 * 
		 * @param itemDefinitionKeys
		 *            the new item definition keys
		 */
		public void setItemDefinitionKeys(Set<String> itemDefinitionKeys) {
			itemDefinitionKeys_ = itemDefinitionKeys;
		}

		/**
		 * Adds the item definition.
		 * 
		 * @param itemDef
		 *            the item def
		 */
		public void addItemDefinition(ItemDefinition itemDef) {
			if (itemDefs_ != null) {
				itemDefs_.add(itemDef);
			}
			String key = itemDef.getName();
			addItemDefinitionKey(key);
		}

		/**
		 * Adds the item definition key.
		 * 
		 * @param key
		 *            the key
		 */
		public void addItemDefinitionKey(String key) {
			itemDefinitionKeys_.add(key);
		}

		/**
		 * Gets the override labels.
		 * 
		 * @return the override labels
		 */
		public Map<String, String> getOverrideLabels() {
			return overrideLabels_;
		}

		// public void setOverrideLabels(Map<String, String> overrideLabels) {
		// overrideLabels_ = overrideLabels;
		// }

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
		 */
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			name_ = in.readUTF();
			defaultSummary_ = in.readBoolean();
			int defCount = in.readInt();
			for (int i = 0; i < defCount; i++) {
				itemDefinitionKeys_.add(in.readUTF());
			}
			int labelCount = in.readInt();
			for (int i = 0; i < labelCount; i++) {
				String key = in.readUTF();
				String value = in.readUTF();
				overrideLabels_.put(key, value);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(name_);
			out.writeBoolean(defaultSummary_);
			out.writeInt(itemDefinitionKeys_.size());
			for (String key : itemDefinitionKeys_) {
				out.writeUTF(key);
			}
			out.writeInt(overrideLabels_.size());
			for (Map.Entry<String, String> entry : overrideLabels_.entrySet()) {
				out.writeUTF(entry.getKey());
				out.writeUTF(entry.getValue());
			}
		}
	}

	/**
	 * The Class ItemDefinition.
	 */
	public static class ItemDefinition implements Externalizable {
		
		/** The name_. */
		private String name_;
		
		/** The default label_. */
		private String defaultLabel_;
		
		/** The type_. */
		private Class<?> type_;
		
		/** The flags_. */
		private Set<Flags> flags_ = new HashSet<Flags>();
		
		/** The default value_. */
		private Object defaultValue_;
		
		/** The validator_. */
		private ItemValidation validator_;
		
		/** The parent schema_. */
		private transient DatabaseSchema parentSchema_;

		/**
		 * Instantiates a new item definition.
		 */
		public ItemDefinition() {

		}

		/**
		 * Sets the parent.
		 * 
		 * @param parent
		 *            the new parent
		 */
		public void setParent(DatabaseSchema parent) {
			parentSchema_ = parent;
		}

		/**
		 * Gets the name.
		 * 
		 * @return the name
		 */
		public String getName() {
			return name_;
		}

		/**
		 * Sets the name.
		 * 
		 * @param name
		 *            the new name
		 */
		public void setName(String name) {
			name_ = name;
		}

		/**
		 * Gets the default label.
		 * 
		 * @return the default label
		 */
		public String getDefaultLabel() {
			return defaultLabel_;
		}

		/**
		 * Sets the default label.
		 * 
		 * @param defaultLabel
		 *            the new default label
		 */
		public void setDefaultLabel(String defaultLabel) {
			defaultLabel_ = defaultLabel;
		}

		/**
		 * Gets the type.
		 * 
		 * @return the type
		 */
		public Class<?> getType() {
			return type_;
		}

		/**
		 * Sets the type.
		 * 
		 * @param type
		 *            the new type
		 */
		public void setType(Class<?> type) {
			type_ = type;
		}

		/**
		 * Gets the flags.
		 * 
		 * @return the flags
		 */
		public Set<Flags> getFlags() {
			return flags_;
		}

		/**
		 * Sets the flags.
		 * 
		 * @param flags
		 *            the new flags
		 */
		public void setFlags(Set<Flags> flags) {
			flags_ = flags;
		}

		/**
		 * Adds the flag.
		 * 
		 * @param flag
		 *            the flag
		 */
		public void addFlag(Flags flag) {
			flags_.add(flag);
		}

		/**
		 * Gets the default value.
		 * 
		 * @return the default value
		 */
		public Object getDefaultValue() {
			return defaultValue_;
		}

		/**
		 * Sets the default value.
		 * 
		 * @param defaultValue
		 *            the new default value
		 */
		public void setDefaultValue(Object defaultValue) {
			defaultValue_ = defaultValue;
		}

		/**
		 * Gets the validator.
		 * 
		 * @return the validator
		 */
		public ItemValidation getValidator() {
			return validator_;
		}

		/**
		 * Sets the validator.
		 * 
		 * @param validator
		 *            the new validator
		 */
		public void setValidator(ItemValidation validator) {
			validator_ = validator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
		 */
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			name_ = in.readUTF();
			defaultLabel_ = in.readUTF();
			type_ = Class.forName(in.readUTF());
			int flagCount = in.readInt();
			for (int i = 0; i < flagCount; i++) {
				flags_.add((Flags) in.readObject());
			}
			defaultValue_ = in.readObject();
			validator_ = (ItemValidation) in.readObject();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(name_);
			out.writeUTF(defaultLabel_);
			out.writeUTF(type_.getCanonicalName());
			out.writeInt(flags_.size());
			for (Flags flag : flags_) {
				out.writeObject(flag);
			}
			out.writeObject(defaultValue_);
			out.writeObject(validator_);
		}

		/**
		 * Creates the default item.
		 * 
		 * @param doc
		 *            the doc
		 * @param def
		 *            the def
		 * @return the item
		 */
		public Item createDefaultItem(Document doc, DocumentDefinition def) {
			String name = getName();
			Object defaultValue = getDefaultValue();
			if (defaultValue == null) {
				Class<?> checkType = getType();
				if (checkType.isArray()) {
					checkType = getType().getComponentType();
				}
				if (checkType == Integer.TYPE || checkType.equals(Integer.class)) {
					defaultValue = 0;
				} else if (checkType == Long.TYPE || checkType.equals(Long.class)) {
					defaultValue = 0;
				} else if (checkType == Double.TYPE || checkType.equals(Double.class)) {
					defaultValue = 0;
				} else if (checkType == Float.TYPE || checkType.equals(Float.class)) {
					defaultValue = 0;
				} else if (checkType == Short.TYPE || checkType.equals(Short.class)) {
					defaultValue = 0;
				} else if (checkType == Long.TYPE || checkType.equals(Long.class)) {
					defaultValue = 0;
				} else if (checkType.equals(String.class)) {
					defaultValue = "";
				} else if (checkType.equals(DateTime.class)) {
					DateTime dt = doc.getAncestorSession().createDateTime(new Date());
					dt.setAnyDate();
					dt.setAnyTime();
					defaultValue = dt;
				} else {
					defaultValue = null;
				}
			}
			Item item = null;
			if (defaultValue != null) {
				item = doc.replaceItemValue(name, defaultValue);
			} else {
				try {
					item = doc.replaceItemValueCustomDataBytes(name, "", new byte[1]);
				} catch (IOException e) {
					DominoUtils.handleException(e);
				}
			}
			if (!def.isDefaultSummary()) {
				item.setSummary(false);
			}
			for (Flags flag : getFlags()) {
				switch (flag) {
				case SUMMARY:
					item.setSummary(true);
					break;
				case AUTHORS:
					item.setAuthors(true);
					break;
				case READERS:
					item.setReaders(true);
					break;
				case PROTECTED:
					item.setProtected(true);
					break;
				case SIGNED:
					item.setSigned(true);
					break;
				case ENCRYPTED:
					item.setEncrypted(true);
					break;
				}
			}
			return item;
		}

	}

	/**
	 * The Class ItemValidation.
	 */
	public static class ItemValidation implements Externalizable {
		
		/** The required_. */
		private boolean required_;
		
		/** The unique_. */
		private boolean unique_;
		
		/** The unique formula_. */
		private Formula uniqueFormula_;
		
		/** The expression_. */
		private Pattern expression_;
		
		/** The max value_. */
		private long maxValue_;
		
		/** The min value_. */
		private long minValue_;
		
		/** The max members_. */
		private int maxMembers_;
		
		/** The min members_. */
		private int minMembers_;

		/**
		 * Gets the pattern.
		 * 
		 * @return the pattern
		 */
		public Pattern getPattern() {
			return expression_;
		}

		/**
		 * Sets the regex.
		 * 
		 * @param expression
		 *            the new regex
		 */
		public void setRegex(String expression) {
			expression_ = Pattern.compile(expression);
		}

		/**
		 * Checks if is required.
		 * 
		 * @return true, if is required
		 */
		public boolean isRequired() {
			return required_;
		}

		/**
		 * Sets the required.
		 * 
		 * @param required
		 *            the new required
		 */
		public void setRequired(boolean required) {
			required_ = required;
		}

		/**
		 * Checks if is unique.
		 * 
		 * @return true, if is unique
		 */
		public boolean isUnique() {
			return unique_;
		}

		/**
		 * Sets the unique.
		 * 
		 * @param unique
		 *            the new unique
		 */
		public void setUnique(boolean unique) {
			unique_ = unique;
		}

		/**
		 * Gets the unique formula.
		 * 
		 * @return the unique formula
		 */
		public Formula getUniqueFormula() {
			return uniqueFormula_;
		}

		/**
		 * Sets the unique formula.
		 * 
		 * @param uniqueFormula
		 *            the new unique formula
		 */
		public void setUniqueFormula(Formula uniqueFormula) {
			uniqueFormula_ = uniqueFormula;
		}

		/**
		 * Gets the max value.
		 * 
		 * @return the max value
		 */
		public long getMaxValue() {
			return maxValue_;
		}

		/**
		 * Sets the max value.
		 * 
		 * @param maxValue
		 *            the new max value
		 */
		public void setMaxValue(long maxValue) {
			maxValue_ = maxValue;
		}

		/**
		 * Gets the min value.
		 * 
		 * @return the min value
		 */
		public long getMinValue() {
			return minValue_;
		}

		/**
		 * Sets the min value.
		 * 
		 * @param minValue
		 *            the new min value
		 */
		public void setMinValue(long minValue) {
			minValue_ = minValue;
		}

		/**
		 * Gets the max members.
		 * 
		 * @return the max members
		 */
		public int getMaxMembers() {
			return maxMembers_;
		}

		/**
		 * Sets the max members.
		 * 
		 * @param maxMembers
		 *            the new max members
		 */
		public void setMaxMembers(int maxMembers) {
			maxMembers_ = maxMembers;
		}

		/**
		 * Gets the min members.
		 * 
		 * @return the min members
		 */
		public int getMinMembers() {
			return minMembers_;
		}

		/**
		 * Sets the min members.
		 * 
		 * @param minMembers
		 *            the new min members
		 */
		public void setMinMembers(int minMembers) {
			minMembers_ = minMembers;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
		 */
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			required_ = in.readBoolean();
			unique_ = in.readBoolean();
			maxValue_ = in.readLong();
			minValue_ = in.readLong();
			maxMembers_ = in.readInt();
			minMembers_ = in.readInt();
			uniqueFormula_ = (Formula) in.readObject();
			expression_ = (Pattern) in.readObject();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeBoolean(required_);
			out.writeBoolean(unique_);
			out.writeLong(maxValue_);
			out.writeLong(minValue_);
			out.writeInt(maxMembers_);
			out.writeInt(minMembers_);
			out.writeObject(uniqueFormula_);
			out.writeObject(expression_);
		}

	}

	/** The document definitions_. */
	private Map<String, DocumentDefinition> documentDefinitions_ = new HashMap<String, DocumentDefinition>();
	
	/** The item definitions_. */
	private Map<String, ItemDefinition> itemDefinitions_ = new HashMap<String, ItemDefinition>();

	/**
	 * Instantiates a new database schema.
	 */
	public DatabaseSchema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the document definitions.
	 * 
	 * @return the document definitions
	 */
	public Map<String, DocumentDefinition> getDocumentDefinitions() {
		return documentDefinitions_;
	}

	/**
	 * Sets the document definitions.
	 * 
	 * @param definitions
	 *            the definitions
	 */
	public void setDocumentDefinitions(Map<String, DocumentDefinition> definitions) {
		documentDefinitions_ = definitions;
	}

	/**
	 * Gets the item definitions.
	 * 
	 * @return the item definitions
	 */
	public Map<String, ItemDefinition> getItemDefinitions() {
		return itemDefinitions_;
	}

	/**
	 * Sets the item definitions.
	 * 
	 * @param definitions
	 *            the definitions
	 */
	public void setItemDefinitions(Map<String, ItemDefinition> definitions) {
		itemDefinitions_ = definitions;
	}

	/**
	 * Save.
	 * 
	 * @param db
	 *            the db
	 */
	public void save(Database db) {

	}

	/**
	 * Creates the document.
	 * 
	 * @param db
	 *            the db
	 * @param doctype
	 *            the doctype
	 * @return the document
	 */
	public Document createDocument(Database db, String doctype) {
		DocumentDefinition def = getDocumentDefinitions().get(doctype);
		if (def == null)
			return null;
		Document result = db.createDocument();
		result.replaceItemValue("$$SchemaType", doctype);
		result.replaceItemValue("form", def.getName());
		Set<ItemDefinition> itemDefs = def.getItemDefinitions();
		for (ItemDefinition itemDef : itemDefs) {
			Item item = itemDef.createDefaultItem(result, def);
		}

		return result;
	}

	/**
	 * Validate document.
	 * 
	 * @param doc
	 *            the doc
	 * @return true, if successful
	 */
	public boolean validateDocument(Document doc) {
		String doctype = doc.getItemValueString("$$SchemaType");
		DocumentDefinition def = getDocumentDefinitions().get(doctype);
		if (def == null)
			return true;

		boolean result = true;
		Set<ItemDefinition> itemDefs = def.getItemDefinitions();
		for (ItemDefinition itemDef : itemDefs) {
			// TODO NTF
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int defCount = in.readInt();
		for (int i = 0; i < defCount; i++) {
			String key = in.readUTF();
			DocumentDefinition def = (DocumentDefinition) in.readObject();
			documentDefinitions_.put(key, def);
			def.setParent(this);
		}
		int itemCount = in.readInt();
		for (int i = 0; i < itemCount; i++) {
			String key = in.readUTF();
			ItemDefinition def = (ItemDefinition) in.readObject();
			itemDefinitions_.put(key, def);
			def.setParent(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(documentDefinitions_.size());
		for (Map.Entry<String, DocumentDefinition> entry : documentDefinitions_.entrySet()) {
			out.writeUTF(entry.getKey());
			out.writeObject(entry.getValue());
		}
		out.writeInt(itemDefinitions_.size());
		for (Map.Entry<String, ItemDefinition> entry : itemDefinitions_.entrySet()) {
			out.writeUTF(entry.getKey());
			out.writeObject(entry.getValue());
		}
	}

}
