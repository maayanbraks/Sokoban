package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	private boolean stop=false;
	private ClientHandler ch;

	private void runServer() throws Exception {
		ServerSocket server=new ServerSocket(1234);//port
		server.setSoTimeout(1000);
		while(!this.stop){
			try{
				Socket aClient=server.accept(); // blocking call
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				aClient.getInputStream().close();
				aClient.getOutputStream().close();
				aClient.close();

			}catch(SocketTimeoutException e) {
				System.out.println("SocketTimeoutException - MyServer - run server");
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
}