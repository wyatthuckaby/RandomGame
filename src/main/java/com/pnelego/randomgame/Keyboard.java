package com.pnelego.randomgame;

import org.json.*;
import java.io.*;
public class Keyboard {
	/* Our keyboard will be bound by the constants contained in the GLFW
	 * key system. When this class is loaded, it will call the JSON config
	 * and pull all the data off it. The data stored in the keyboard JSON file
	 * wont be the keys themselves, but rather the actual code the key represents
	 * this means we wont have to do any strange (and costly) conversion from
	 * CHAR to GLFW_KEY enumerations.
	 * 
	 * 
	 * the indexes in the array for each bind are as follows
	 * [0] = Jump
	 * [1] = Crouch
	 * [2] = Right
	 * [3] = Left
	 * [4] = Fire
	 * [5] = Alt-Fire
	 * [6] = Pause
	 */
	
	private int[] keys;
	
	public int init() {
		//get the contents of the keyboard file and put them
		//into the JSON convert
		String keyboardConf =  "";
		String line;
		try {
			FileReader keyboardFile = new FileReader("keyboard.json");
			BufferedReader fileReader = new BufferedReader(keyboardFile);

			while ((line = fileReader.readLine()) != null) {
				keyboardConf.concat(line);
			}
			
			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.err.println("keyboard.json not found; Fatal Error");
			return 1;
			
		} catch (IOException ex) {
			System.err.println("File Not working");
			return 1;
		}
		
		System.out.println(keyboardConf);
		return 0;
	}
	
}
