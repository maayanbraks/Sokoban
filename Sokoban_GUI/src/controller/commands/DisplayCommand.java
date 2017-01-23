/**
* This class responsible to run the 'display' command
* @author Maayan & Eden
*/

package controller.commands;

import common.Level2D;
import model.Model;
import view.Displayer;
import view.GUIController;
import view.View;
import view.WarehouseDisplayer;

public class DisplayCommand extends GeneralCommand{
	View _view;

	public DisplayCommand(View view, Model model) {
		super(model);
		this._view=view;

	}

	public void execute(){
		char[][] map=_model.getLevel().toChar();
		_view.setCounter(_model.getLevel().getCounter());
		System.out.println(_model.getLevel().getCounter());
		_view.setWarehouse(map);
		//WarehouseDisplayer warehouseDisplayer= new WarehouseDisplayer();
		//warehouseDisplayer.setWarehouse(map);
		//view.WarehouseDisplayer.setWarehouse(map);

	}
}
