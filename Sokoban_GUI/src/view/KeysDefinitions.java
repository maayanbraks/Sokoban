package view;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class KeysDefinitions {
	String up="";
	String down="";
	String left="";
	String right="";
	private HashMap<String, String> keys = new HashMap<String, String>();
	
	public KeysDefinitions() {
		try {
		//	System.out.println(up);
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("./resources/levels/Keys.xml"));
			up=(String)decoder.readObject();
			down=(String)decoder.readObject();
			left=(String)decoder.readObject();
			right=(String)decoder.readObject();
			//System.out.println(up);
			
		} catch (FileNotFoundException e) {
			System.out.println("cant");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		keys.put(up,"move up");
		keys.put(down,"move down");
		keys.put(left,"move left");
		keys.put(right,"move right");

	}
	
	
	public String getCommandFromKey(KeyCode key) {
		//String keyss=key.get
		String keyss=key.toString();
		//System.out.println(keyss);
		String lol=keys.get(keyss);
		System.out.println(lol);
		return lol;
	}


}
