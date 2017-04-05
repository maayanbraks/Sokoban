/**
* This class responsible to save XML File
* @author Maayan & Eden
* @version 2D
*/

package model.data;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import common.Level2D;
import javafx.scene.input.KeyCode;

public class MyXMLLevelSaver implements LevelSaver {
	public void SaveLevel(OutputStream out, Level2D lvl) throws IOException {
		XMLEncoder xml=new XMLEncoder(out);
		//xml.writeObject(lvl);
		//XMLSaver(xml,lvl);
		xml.writeObject("UP");
		xml.writeObject("DOWN");
		xml.writeObject("LEFT");
		xml.writeObject("RIGHT");
		xml.close();
	}

	public XMLEncoder XMLSaver(XMLEncoder xml, Level2D lvl) {
		for (int i=0;i<lvl.getHeight();i++)
			for(int j=0;j<lvl.getWidth();j++)
				xml.writeObject(lvl.getMap()[i][j]);
		return xml;
	}


}