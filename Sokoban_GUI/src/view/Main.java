package view;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.SokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
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
			SokobanController controller = new SokobanController(model, view);

			model.addObserver(controller);
			view.addObserver(controller);


			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
