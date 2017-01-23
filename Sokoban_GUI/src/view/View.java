package view;

import javafx.scene.input.KeyEvent;

//import model.data.Position2D;

public interface View {

	void start();
	void move(KeyEvent event);
	void exit();
	void save();
	void load();
	void setWarehouse(char[][] map);
	void setCounter(int counter);
	void setComment(String comment);


//	void displayPosition(Position2D pos);

//	void displayError(String msg);

}
