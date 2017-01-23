/**
* This class is Invoker - Get command as string from the user and pass it
* @author Maayan & Eden
*/

package view;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import model.data.Position2D;


/*
public class Cli extends Observable implements View{

	Scanner scanner = new Scanner(System.in);

	/**
	* This function write the menu to the user and get the command
	*/
/*
	public void start() {
		Thread thread = new Thread(new Runnable() {
			//Scanner scanner = new Scanner(System.in);

			public void run() {
				String str="";
		        System.out.println("\tMenu Options\n" +
		        		"Enter:\n" +
		        		"-Load file name\n" +
		        		"-Display\n" +
		        		"-Move up/down/left/right\n" +
		        		"-Save file name\n" +
		        		"-Exit\n" +
		        		"Please enter your selection:");
				do {
					//Print the menu


			        //Input the string
					str = scanner.nextLine();
			    	str=str.toLowerCase();

		//			List<String> params = new LinkedList<String>();

		//			for (String s: arr) {
		//				params.add(s);
		//			}

					setChanged();
					notifyObservers(str);//GO TO SokobanController

				}while(str.compareTo("exit")!=0);
			}
		});
		thread.start();
	}






}

	/*OUR CLI.RUN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void Run(){
//Start actions
		ConcreteCommandCreator ccc;
		Scanner scanner = new Scanner(System.in);
		String[] parts;

        System.out.println("\tMenu Options\n" +
        		"Enter:\n" +
        		"-Load file name\n" +
        		"-Display\n" +
        		"-Move up/down/left/right\n" +
        		"-Save file name\n" +
        		"-Exit\n" +
        		"Please enter your selection:");
        String str=new String();


        ccc = new ConcreteCommandCreator();

        //WHILE(!EXIT)
        do{
    	  str = scanner.nextLine();//READ from the user
    	  str=str.toLowerCase();
    	  parts=str.split(" ");//separate string
    	  ccc.createCommandGeneral(str);------------------------------------
       }while (str.compareTo("exit")!=0);
	}
}*/

