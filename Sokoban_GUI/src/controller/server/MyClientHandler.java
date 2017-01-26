package controller.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import controller.commands.GeneralCommand;

public class MyClientHandler extends Observable implements ClientHandler{

	private boolean stop=false;
	private BlockingQueue<String> _msg =  new ArrayBlockingQueue<String>(10);
	private PrintStream ps;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
		//Scanner scanner = new Scanner(System.in);
		//BufferedWriter ps=new BufferedWriter(new OutputStreamWriter(outToClient));
		ps=new PrintStream(outToClient);
		System.out.println("handle client func");
		String str="";
        ps.println("\tMenu Options\n" +
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
	    	//System.out.println(str);
	    	if (str.compareTo("exit")==0) {
	    		ps.println("bye");

	    	}
	    	else {
			setChanged();
			notifyObservers(str);

			while(!_msg.isEmpty()){
					try {
						ps.println(_msg.poll(1, TimeUnit.SECONDS));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    	}
//GO TO SokobanController

			}while((str.compareTo("exit")!=0) && (!this.stop));
		System.out.println("after while");
		//System.out.println("after while command in MYCLIENTHANDLER");
	}


		public PrintStream getPs() {
		return ps;
	}


		public void stop(){
			this.stop=true;
		}

		public void addMsg(String msg) throws InterruptedException{
			_msg.put(msg);
		}

}
/*
public class MyClientHander extends Observable implements ClientHandler {
	private ArrayBlockingQueue<String> queue;
	private volatile boolean hasClient;
	private boolean keepRunning;

	public MyClientHandler() {
		queue=new ArrayBlockingQueue<String>(200);
		this.hasClient=true;
	}
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		try {
		this.keepRunning=true;
		this.hasClient=true;
		BufferedReader readFromClient=new BufferedReader(new InputStreamReader(in));
		BufferedReader readFromServer=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writeToClient=new PrintWriter(out);
		PrintWriter writeToServer=new PrintWriter(System.out);

		Thread t1=aSyncReadInputAndSent(readFromClient, writeToServer, "cya");
		Thread t3=aSyncSendToClient(writeToClient);
		t1.join();
		t3.join();
		readFromClient.close();
		//readFromServer.close();
		writeToClient.close();
		//writeToServer.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private Thread aSyncSendToClient(PrintWriter writeToClient) {
		Thread t=new Thread(new Runnable() {

			@Override
			public void run() {
				sendToClient(writeToClient);
			}
		});
		t.start();
		return t;
	}
	private void readInputAndSend(BufferedReader in, PrintWriter out,String exitStr){
		String line;
		try {
			while(this.keepRunning){
			//	if(in.ready()){
				line=in.readLine();
				System.out.println(line);
				if(line.equals(exitStr)){
					addMessageToQueue("bye");
					setChanged();
					notifyObservers("disconnected");
					this.keepRunning=false;
					this.stop();
					continue;
				}
					out.println(line);

					setChanged();
					notifyObservers(line);
					out.flush();
				}

					//end of while
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Thread aSyncReadInputAndSent(BufferedReader in, PrintWriter out,String exitStr){
		Thread t=new Thread(new Runnable() {
			@Override
			public void run() {
				try{
				readInputAndSend(in, out, exitStr);
				}catch(Exception e){ e.printStackTrace();}
			}
		});
		t.start();

		return t;

	}

	private void sendToClient(PrintWriter writeToClient){
		while(hasClient){
			try {
				String line=queue.take();
				if(line !=null){
					writeToClient.println(line);
					writeToClient.flush();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void stop(){
		this.hasClient=false;
	}
	public void start(){
		this.hasClient=true;
	}
	public void addMessageToQueue(String line) {
			try {
				this.queue.put(line);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

*/
