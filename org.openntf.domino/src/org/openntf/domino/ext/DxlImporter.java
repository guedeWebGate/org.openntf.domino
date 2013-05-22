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
package org.openntf.domino.ext;

import org.openntf.domino.DxlImporter.AclImportOption;
import org.openntf.domino.DxlImporter.DesignImportOption;
import org.openntf.domino.DxlImporter.DocumentImportOption;
import org.openntf.domino.DxlImporter.InputValidationOption;

// TODO: Auto-generated Javadoc
/**
 * The Interface DxlImporter.
 * 
 * @author withersp
 */
public interface DxlImporter {
	
	/**
	 * Sets the acl import option.
	 * 
	 * @param option
	 *            the new acl import option
	 */
	public void setAclImportOption(AclImportOption option);

	/**
	 * Sets the design import option.
	 * 
	 * @param option
	 *            the new design import option
	 */
	public void setDesignImportOption(DesignImportOption option);

	/**
	 * Sets the document import option.
	 * 
	 * @param option
	 *            the new document import option
	 */
	public void setDocumentImportOption(DocumentImportOption option);

	/**
	 * Sets the input validation option.
	 * 
	 * @param option
	 *            the new input validation option
	 */
	public void setInputValidationOption(InputValidationOption option);
}
