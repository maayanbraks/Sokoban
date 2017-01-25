package view;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

//import model.data.Position2D;

public interface View {

	void start();
	void exit();
	void save();
	void load();
	void setWarehouse(char[][] map);
	void setCounter(int counter);
	void setComment(String comment);
	void setStage(Stage primaryStage);
	void closeStage();
	void move(String str);
	void stopTimer();

//	void displayPosition(Position2D pos);

//	void displayError(String msg);

}
