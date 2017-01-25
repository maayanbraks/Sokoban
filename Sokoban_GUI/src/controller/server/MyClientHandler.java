package controller.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Scanner;


public class MyClientHandler extends Observable implements ClientHandler{

	private boolean stop=false;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
		//Scanner scanner = new Scanner(System.in);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(outToClient));

		String str="";
        bw.write("\tMenu Options\n" +
        		"Enter:\n" +
        		"-Load file name\n" +
        		"-Display\n" +
        		"-Move up/down/left/right\n" +
        		"-Save file name\n" +
        		"-Exit\n" +
        		"Please enter your selection:");
		do {

			str=new BufferedReader(new InputStreamReader(inFromClient)).readLine();
	    	str=str.toLowerCase();
	    	if (str.compareTo("exit")==0) {
	    		bw.write("Bye...");
	    	}
			setChanged();
			notifyObservers(str);//GO TO SokobanController

			}while(str.compareTo("exit")!=0 && !this.stop);
		}


		public void stop(){
			this.stop=true;
		}



}
