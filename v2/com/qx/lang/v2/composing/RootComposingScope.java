package com.qx.lang.v2.composing;

import java.io.Writer;

import com.qx.lang.v2.JOOS_Context;

public class RootComposingScope extends ComposingScope {

	private JOOS_Context context;

	private Writer writer;

	public RootComposingScope(
			JOOS_Context context,
			Writer writer, 
			String incrementalIndent) {
		super(context, writer, '{', '}', "", incrementalIndent);

		this.context = context;
		this.writer = writer;
	}

	@Override
	public ComposingScope enterSubscope(char openingChar, char closingChar) {
		return new ComposingScope(context, writer, openingChar, closingChar, "", incrementalIndent);
	}

}