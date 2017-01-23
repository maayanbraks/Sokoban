package view;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class GUIController extends Observable implements View,Initializable{

	char [][] _map=null;

	@FXML
	WarehouseDisplayer warehouseDisplayer;
	@FXML
	Text counter;
	@FXML
	Text timer;
	@FXML
	Text comment;

	String musicFile="./resources/media/trololo.mp3";

	public void setWarehouse(char[][] map) {
		this._map=map;
		warehouseDisplayer.setWarehouse(map);
		warehouseDisplayer.redraw();
	}

	public void setCounter(int counter) {
		String str=String.valueOf(counter);
		this.counter.setText(str);
	}


	public void setComment(String comment) {
		String str=String.valueOf(comment);
		this.comment.setText(str);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//music
		Media song=new Media(new File(musicFile).toURI().toString());
		MediaPlayer mp=new MediaPlayer(song);
		mp.play();

//music -end

		warehouseDisplayer.setWarehouse(_map);
		warehouseDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->warehouseDisplayer.requestFocus() );
		warehouseDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
				public void handle(KeyEvent event) {
				move(event);
				}
			});

	}

	@Override
	public void move(KeyEvent event) {

		if(_map!=null){

					if(event.getCode() == KeyCode.UP){
						setChanged();
						notifyObservers("move up");
					}

					else if(event.getCode() == KeyCode.DOWN){
						setChanged();
						notifyObservers("move down");
					}

					else if(event.getCode() == KeyCode.LEFT){
						setChanged();
						notifyObservers("move left");
					}

					else if(event.getCode() == KeyCode.RIGHT){
						setChanged();
						notifyObservers("move right");
					}

				}
	}


	@Override
	public void start(){
		warehouseDisplayer.printBackground();
		//this.load();
	}

	public void load(){
		FileChooser fc=new FileChooser();
		fc.setTitle("Load Level");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)","*.txt"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XMC(*.xml)","*.xml"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object(*.obj)","*.obj"));
		File chosen=fc.showOpenDialog(null);
		if (chosen!=null){
			String path=chosen.getName();


			setChanged();
			notifyObservers("load ./resources/levels/"+path);

		}
		else
			System.out.println("WHAT??");

	}



	public void save(){

		FileChooser fc=new FileChooser();
		fc.setTitle("Save Level");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)","*.txt"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XMC(*.xml)","*.xml"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object(*.obj)","*.obj"));
		File chosen=fc.showSaveDialog(null);
		if (chosen!=null){
			String path=chosen.getName();
			System.out.println(path);

			setChanged();
			notifyObservers("save ./resources/levels/"+path);

		}
	}


	@Override
	public void exit() {
		setChanged();
		notifyObservers("exit");

	}


}
