package boot;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.SokobanController;
import controller.server.MyServer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import view.GUIController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));

			BorderPane root = (BorderPane)loader.load();

			GUIController view = loader.getController();
			MyModel model = new MyModel();
			MyServer server=null;
			SokobanController controller = new SokobanController(model, view);


			model.addObserver(controller);
			view.addObserver(controller);


			String args="-server";
			int port=1234;

			if(args.compareTo("-server")==0){
				server=new MyServer(port);
				server.getClient().addObserver(controller);
				server.start();
			}
			controller.setServer(server);



			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);


			/*
			Thread t1=new Thread(new Runnable() {
				@Override
				public void run() {
					controller._server.start();

				}
			});
			t1.start();
			*/

			view.start();
			primaryStage.show();
			view.setStage(primaryStage);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		///////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////
		/*
		 * Thred t=new()
		 * lunch();
		 *
		 * */
		launch(args);
		//controller.start();
	}
}
