package com.qx.lang.v2;

public class ParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066459485992721695L;

	public int line;
	
	public int column;
	
	public ParsingException(String message){
		super(message);
	}
	
	public ParsingException(int line, int column, String message){
		super(message);
		this.line = line;
		this.column = column;
	}
	
	public int getLine(){
		return line;
	}
	
	public int getColumn(){
		return column;
	}
}