package com.s8.blocks.joos.fields;

import java.io.IOException;
import java.lang.reflect.Field;

import com.s8.blocks.joos.JOOS_Lexicon;
import com.s8.blocks.joos.ParsingException;
import com.s8.blocks.joos.composing.ComposingScope;
import com.s8.blocks.joos.composing.JOOS_ComposingException;
import com.s8.blocks.joos.parsing.JOOS_ParsingException;
import com.s8.blocks.joos.parsing.ParsingScope;
import com.s8.blocks.joos.parsing.PrimitiveScope;
import com.s8.blocks.joos.parsing.ParsingScope.OnParsedValue;

/**
 * 
 * @author pc
 *
 */
public abstract class PrimitiveFieldHandler extends FieldHandler {

	
	
	/**
	 * 
	 * @param name
	 * @param field
	 */
	public PrimitiveFieldHandler(String name, Field field) {
		super(name, field);
	}


	/**
	 * 
	 * @param object
	 * @param value
	 * @throws ParsingException 
	 * @throws Exception
	 */
	public abstract void parse(Object object, String value) throws JOOS_ParsingException;
	
	
	@Override
	public ParsingScope openScope(Object object) {
		return new PrimitiveScope(new OnParsedValue() {
			public @Override void set(String value) throws JOOS_ParsingException {
				parse(object, value);
			}
		});
	}
	
	
	
	/**
	 * 
	 * @param object
	 * @param writer
	 * @return has actually output something
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws JOOS_ComposingException 
	 */
	@Override
	public abstract boolean compose(Object object, ComposingScope scope) throws IOException, JOOS_ComposingException;


	
	@Override
	public Class<?> getSubType() {
		return null;
	}
	
	
	
	@Override
	public void subDiscover(JOOS_Lexicon context) {
		// nothing to sub-discover
	}
}