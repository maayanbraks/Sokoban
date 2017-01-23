package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Command;
import controller.commands.GeneralCommand;




public class Controller{

	private BlockingQueue<GeneralCommand> queue;
	private boolean isStopped = false;

	public Controller() {
		queue = new ArrayBlockingQueue<GeneralCommand>(10);
	}

	public void insertCommand(GeneralCommand command) {
		try {
			queue.put(command);
		} catch (InterruptedException e) {
			System.out.println("instercommand exce[tion");
			e.printStackTrace();
		}
	}


	public void start(){
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (!isStopped) {
					try {
						GeneralCommand cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null){
							cmd.execute();
							}

					} catch (InterruptedException e) {
						System.out.println("queue exaption");
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}


	public void stop(){
		isStopped = true;
	}
}
