package com.s8.blocks.joos.demos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import com.s8.blocks.joos.demos.repo00.MyRootType;
import com.s8.core.io.json.JSON_Lexicon;
import com.s8.core.io.json.parsing.JSON_ParsingException;
import com.s8.core.io.json.types.JSON_CompilingException;
import com.s8.core.io.json.utilities.JOOS_BufferedFileReader;


/**
 * 
 * @author pierreconvert
 *
 */
public class LaunchParsingTest {

	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws JSON_CompilingException
	 */
	public static void main(String[] args) throws IOException, JSON_CompilingException {

		JSON_Lexicon context = JSON_Lexicon.from(MyRootType.class);
		

		System.out.println("Go!");
		


		String pathname = "data/V2_test_input2.js";

		RandomAccessFile file = new RandomAccessFile(new File(pathname), "r");


		try {
			JOOS_BufferedFileReader reader = new JOOS_BufferedFileReader(file.getChannel(), StandardCharsets.UTF_8, 64);

			MyRootType result = (MyRootType) context.parse(reader, true);
			System.out.println(result);	
			reader.close();
		}
		catch (JSON_ParsingException e) {
			e.printStackTrace();
		}
		finally {
			file.close();
		}
	}
}
