/**
* This class responsible to run the "talk" with clients
* @author Maayan & Eden
*/

package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyClientHandler extends Observable implements ClientHandler {

	private boolean stop = false;
	private BlockingQueue<String> _msg = new ArrayBlockingQueue<String>(10);
	private /*PrintWriter (Eli's solution)*/ PrintStream ps;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
		// Scanner scanner = new Scanner(System.in);
		// BufferedWriter ps=new BufferedWriter(new
		// OutputStreamWriter(outToClient));
		ps = new PrintStream(outToClient);
		String str = "";
		ps.println("\tMenu Options\n" + "Enter:\n" + "-Load file name\n" + "-Display\n" + "-Move up/down/left/right\n"
				+ "-Save file name\n" + "-Exit\n" + "Please enter your selection:");
		do {
			str = new BufferedReader(new InputStreamReader(inFromClient)).readLine();
			str = str.toLowerCase();
			if (str.compareTo("exit") == 0) {
				ps.println("bye");

			}
			else {
				setChanged();
				notifyObservers(str);

				while (!_msg.isEmpty()) {
					try {
						ps.println(_msg.poll(1, TimeUnit.SECONDS));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// GO TO SokobanController

		} while ((str.compareTo("exit") != 0) && (!this.stop));
	}

	public PrintStream getPs() {
		return ps;
	}

	public void stop() {
		this.stop = true;
	}

	public void addMsg(String msg) throws InterruptedException {
		_msg.put(msg);
	}

}
