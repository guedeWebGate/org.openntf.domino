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

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static javax.xml.bind.DatatypeConverter.printBase64Binary;

import java.util.List;
import java.util.logging.Logger;

import org.openntf.domino.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class FileResource.
 */
public class FileResource extends AbstractDesignBase implements org.openntf.domino.design.FileResource {
	
	/** The Constant log_. */
	@SuppressWarnings("unused")
	private static final Logger log_ = Logger.getLogger(FileResource.class.getName());

	/**
	 * Instantiates a new file resource.
	 * 
	 * @param document
	 *            the document
	 */
	protected FileResource(final Document document) {
		super(document);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DesignBase#setName(java.lang.String)
	 */
	@Override
	public void setName(final String name) {
		if (name.contains("|")) {
			String[] bits = name.split("\\|");
			getDxlNode("/fileresource").setAttribute("name", name);

			List<String> aliases = getAliases();
			for (int i = 1; i < bits.length; i++) {
				aliases.add(bits[1]);
			}
			setAliases(aliases);
		}
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DesignBase#setAlias(java.lang.String)
	 */
	@Override
	public void setAlias(final String alias) {
		getDxlNode("/fileresource").setAttribute("alias", alias);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.DesignBase#setAliases(java.lang.Iterable)
	 */
	@Override
	public void setAliases(final Iterable<String> aliases) {
		StringBuilder result = new StringBuilder();
		boolean appended = false;
		for (String alias : aliases) {
			if (alias != null) {
				if (appended) {
					result.append('|');
				} else {
					appended = true;
				}
				result.append(alias);
			}
		}

		setAlias(result.toString());
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.FileResource#getFileData()
	 */
	@Override
	public byte[] getFileData() {
		return parseBase64Binary(getDxlNode("/fileresource/filedata").getTextContent());
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.FileResource#setFileData(byte[])
	 */
	@Override
	public void setFileData(final byte[] fileData) {
		String base64 = printBase64Binary(fileData);
		getDxlNode("/fileresource/filedata").setTextContent(base64);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.FileResource#getMimeType()
	 */
	@Override
	public String getMimeType() {
		return getDocument().getItemValueString("$MimeType");
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.FileResource#setMimeType(java.lang.String)
	 */
	@Override
	public void setMimeType(final String mimeType) {
		getDxlNode("/fileresource").setAttribute("mimetype", mimeType);
	}

	/* (non-Javadoc)
	 * @see org.openntf.domino.design.FileResource#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() {
		return getFlags().contains("&");
	}

}