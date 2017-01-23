package view;

//import model.data.Position2D;

public interface View {

	void start();
	void Exit();
	void save();
	void load();
	void setWarehouse(char[][] map);
	void setCounter(int counter);


//	void displayPosition(Position2D pos);

//	void displayError(String msg);

}
