package com.qx.lang.v2.composing;

import java.io.IOException;
import java.io.Writer;

import com.qx.lang.v2.JOOS_Context;
import com.qx.lang.v2.type.TypeHandler;

public class ComposingScope {

	private JOOS_Context context;
	
	private Writer writer;

	private char openingChar;

	private char closingChar;

	protected String indent;

	protected String enclosedIndent;
	
	protected String incrementalIndent;

	private boolean hasAlreadyItems = false;

	public ComposingScope(
			JOOS_Context context,
			Writer writer, 
			char openingChar, 
			char closingChar,
			String indent, 
			String incrementalIndent) {
		super();

		this.context = context;
		this.writer = writer;
		this.openingChar = openingChar;
		this.closingChar = closingChar;

		// compute indent
		this.indent = indent;
		this.incrementalIndent = incrementalIndent;
		this.enclosedIndent = indent+incrementalIndent;

	}



	public void open() throws IOException {
		writer.append(openingChar);
	}

	public void newLine() throws IOException {

		/*
		 * handle previous line separator
		 */
		if(hasAlreadyItems) {
			writer.append(',');
		}
		else {
			hasAlreadyItems = true;
		}

		// append new line
		writer.append('\n');

		// indent
		writer.append(enclosedIndent);

	}


	public void append(String str) throws IOException {
		// value
		writer.append(str);
	}
	
	public void append(char c) throws IOException {
		writer.write(c);
	}

	public void close() throws IOException {
		if(hasAlreadyItems) {
			// append new line
			writer.append('\n');

			// indent
			writer.append(indent);

			// value
			writer.append(closingChar);
		}
	}


	public ComposingScope enterSubscope(char openingChar, char closingChar) {
		return new ComposingScope(context, writer, openingChar, closingChar, enclosedIndent, incrementalIndent);
	}




	public TypeHandler getTypeHandler(Object object) {
		return context.get(object.getClass());
	}


	
}