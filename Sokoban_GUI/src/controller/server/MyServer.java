package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	private boolean stop;
	private MyClientHandler ch;
	private boolean hasClient;
	private int port;
	private String msgToClient;


		public MyServer(int port) {
			this.port=port;
			this.ch=new MyClientHandler();
			this.stop=false;
			this.hasClient=false;
		}
		
		
		public void setMsgToClient(String msg) throws InterruptedException{
			ch.addMsg(msg);
		}
		
		public String getMsgToClient(){
			return this.msgToClient;
		}
	
		private void runServer() throws Exception {
			ServerSocket server=new ServerSocket(1234);//port
			int counter=0;
			server.setSoTimeout(1000);
			while(!this.stop){
				System.out.println(++counter);
				try{
					Socket aClient=server.accept(); // blocking call
					System.out.println("Client has connected");
					this.hasClient=true;
					ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				//	System.out.println("after handleClient func");
					//System.out.println("1");
	
					//aClient.getInputStream().close();
					//aClient.getOutputStream().close();
					aClient.close();
					//System.out.println("lol");
					//ch.notifyObservers("exit");
				}catch(SocketTimeoutException e) {
					//System.out.println("SocketTimeoutException - MyServer - run server");
				}
			}
			server.close(); //we should wait for all threads before closing!
		}
	
	
		public void start(){
			new Thread(new Runnable() {
				public void run() {
					try{
						runServer();
					}catch(Exception e){
						System.out.println("Exception - MyServer - start");
					}
				}
			}).start();
		}
	
		
		public void stop(){
			this.ch.stop();
			this.stop=true;
		}
	
	
		public MyClientHandler getClient() {
			return this.ch;
		}
}