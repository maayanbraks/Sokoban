package view;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class KeysDefinitions {
	
	private HashMap<KeyCode, String> keys = new HashMap<KeyCode, String>();
	
	public KeysDefinitions() {
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("Keys.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCommandFromKey(KeyCode key) {
		return keys.get(key);
	}
	
}
