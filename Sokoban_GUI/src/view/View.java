package view;

import javafx.stage.Stage;


public interface View {

	void start();
	void exit();
	void save();
	void load();
	void setWarehouse(char[][] map);
	void setCounter(int counter);
	void setTitle(String comment);
	void setStage(Stage primaryStage);
	void closeStage();
	void move(String str);
	void stopTimer();


}
