package view;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;

import common.Level2D;

public class KeysSetter {

	public void setKeysDef(OutputStream out) throws IOException {
		XMLEncoder xml=new XMLEncoder(out);
		XMLSaver(xml);
		xml.close();
	}

	public void  XMLSaver(XMLEncoder xml) {

		xml.writeObject("w");
		xml.writeObject("s");
		xml.writeObject("d");
		xml.writeObject("a");

	}

}
