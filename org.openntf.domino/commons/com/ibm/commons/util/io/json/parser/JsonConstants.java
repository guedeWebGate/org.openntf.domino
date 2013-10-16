/* Generated By:JavaCC: Do not edit this line. JsonConstants.java */
package com.ibm.commons.util.io.json.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface JsonConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int EOL = 5;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 8;
  /** RegularExpression Id. */
  int FORMAL_COMMENT = 10;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 11;
  /** RegularExpression Id. */
  int FALSE = 13;
  /** RegularExpression Id. */
  int NULL = 14;
  /** RegularExpression Id. */
  int TRUE = 15;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 16;
  /** RegularExpression Id. */
  int DECIMAL_LITERAL = 17;
  /** RegularExpression Id. */
  int HEX_LITERAL = 18;
  /** RegularExpression Id. */
  int OCTAL_LITERAL = 19;
  /** RegularExpression Id. */
  int FLOATING_POINT_LITERAL = 20;
  /** RegularExpression Id. */
  int EXPONENT = 21;
  /** RegularExpression Id. */
  int STRING_LITERAL = 22;
  /** RegularExpression Id. */
  int ESCAPE_SEQUENCE = 23;
  /** RegularExpression Id. */
  int IDENTIFIER = 24;
  /** RegularExpression Id. */
  int LETTER = 25;
  /** RegularExpression Id. */
  int DIGIT = 26;
  /** RegularExpression Id. */
  int LBRACE = 27;
  /** RegularExpression Id. */
  int RBRACE = 28;
  /** RegularExpression Id. */
  int LBRACKET = 29;
  /** RegularExpression Id. */
  int RBRACKET = 30;
  /** RegularExpression Id. */
  int COLON = 31;
  /** RegularExpression Id. */
  int COMMA = 32;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_FORMAL_COMMENT = 2;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 3;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\f\"",
    "\"\\u000b\"",
    "<EOL>",
    "<token of kind 6>",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "<token of kind 9>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 12>",
    "\"false\"",
    "\"null\"",
    "\"true\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<HEX_LITERAL>",
    "<OCTAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "<ESCAPE_SEQUENCE>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\":\"",
    "\",\"",
    "\"(\"",
    "\")\"",
  };

}
