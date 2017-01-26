/**
* This class responsible to run the 'display' command from Client (in server mode)
* @author Maayan & Eden
*/
package controller.commands;


import common.Level2D;
import controller.server.MyServer;
import model.Model;
import view.Displayer;
import view.GUIController;
import view.View;
import view.WarehouseDisplayer;

public class ServerDisplayCommand extends GeneralCommand{
	View _view;
	MyServer _server;

	public ServerDisplayCommand(Model model,MyServer server) {
		super(model);
		_server=server;

	}

	public void execute(){
		char[][] map=_model.getLevel().toChar();
		setComment(matToString(map));


		/*
		try {
			_server.setMsgToClient(matToString(map));
		} catch (InterruptedException e) {
			System.out.println("ServerDisplay problem");
		}
		/*/

		//WarehouseDisplayer warehouseDisplayer= new WarehouseDisplayer();
		//warehouseDisplayer.setWarehouse(map);
		//view.WarehouseDisplayer.setWarehouse(map);
	}

	public String matToString(char [][]map){
		if (map!=null)
		{
			String str="";
			for (int i=0;i<map.length;i++){
				for(int j=0;j<map[0].length;j++)
					str+=(map[i][j]);
				str+="\n";
			}
			return str;
		}
		return null;
	}
}

