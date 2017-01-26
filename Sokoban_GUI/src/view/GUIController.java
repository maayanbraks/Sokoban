package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.scene.layout.region.BackgroundSizeConverter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIController extends Observable implements View,Initializable{

	char [][] _map=null;
	int count;
	@FXML
	WarehouseDisplayer warehouseDisplayer;
	@FXML
	Text counter;
	@FXML
	Text timer;
	@FXML
	Text comment;
	StringProperty CounterTimer;
	Timer t;
	String musicFile="./resources/media/trololo.mp3";
	private KeysDefinitions keysDefinitions;
//music
	Media song=new Media(new File(musicFile).toURI().toString());
	MediaPlayer mp=new MediaPlayer(song);
//music - END
	Stage _primaryStage;
	@FXML
	BorderPane bp;
	boolean isTimerOn;





	public void setWarehouse(char[][] map) {
		this._map=map;
		warehouseDisplayer.setWarehouse(map);
		warehouseDisplayer.redraw();
	}


	public void startCounter()
	{

		t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				setTimerCounter(getTimerCounter()+1);
			}
		}, 0, 1000);
	}


	public void setTimerCounter (int num) {
		this.count=num;
		if (num==0)
			this.CounterTimer.set(String.valueOf(num));
		else
			this.CounterTimer.set(""+count);
	}

	public int getTimerCounter() {
		return count;
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
		isTimerOn=false;
		CounterTimer=new SimpleStringProperty();
		this.setTimerCounter(0);
		timer.textProperty().bind(CounterTimer);

//Background
//		BackgroundFill fill=new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY);
//		this.bp.setBackground(new Background(fill));
//Background - END
//music
		mp.play();
//music -end

		try {
			this.keysDefinitions = new KeysDefinitions(new FileInputStream("resources/settings/DefaultKeys.xml"));

		} catch (FileNotFoundException e1) {
			System.out.println("asdasd");
			setKeys();
		}

		warehouseDisplayer.setWarehouse(_map);
		warehouseDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->warehouseDisplayer.requestFocus() );
//set keys
		warehouseDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
				public void handle(KeyEvent event) {
				move(event.getCode().toString().toLowerCase());
				}
			});

	}

//Keys settings functions
	public void setKeys(){

		//////////////////////////////////////////////////////////////////TO OD path for setting
	//	KeysSetter sk=new KeysSetter();
		try {
			this.keysDefinitions = new KeysDefinitions(new FileInputStream("resources/settings/Keys.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void move(String str) {

		if(_map!=null){
			String command = this.keysDefinitions.getCommandFromKey(str);//////////name 'command'---------------------
			if(command!=null){
				warehouseDisplayer.setActorFileName("./resources/images/"+command+".png");
				setChanged();
				notifyObservers("move "+command);
			}
		}
	}


	@Override
	public void start(){
		warehouseDisplayer.printBackground();
	}

	public void load(){
		this.comment.setText("SOKOBAN");//////////this is ok?!!??!!?
		if (_map!=null) {//fixxxxxxxxxxxxxxxxx t.cancel
			System.out.println("SAdsad");
			t.cancel();
		}

		this.setTimerCounter(0);

		FileChooser fc=new FileChooser();
		fc.setTitle("Load Level");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)","*.txt"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML(*.xml)","*.xml"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object(*.obj)","*.obj"));
		File chosen=fc.showOpenDialog(null);

		if (chosen!=null){
			String path=chosen.getName();


			setChanged();
			notifyObservers("load ./resources/levels/"+path);
			this.startCounter();
			isTimerOn=true;

		}
		else
			System.out.println("WHAT??");

	}



	public void save(){

		FileChooser fc=new FileChooser();
		fc.setTitle("Save Level");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)","*.txt"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML(*.xml)","*.xml"));
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

	public void closeStage() {
		this._primaryStage.close();
	}

	@Override
	public void setStage(Stage primaryStage) {
		this._primaryStage=primaryStage;
	}

	public void stopTimer(){
		if (isTimerOn)
			t.cancel();
	}



}
