/**
* This class responsible to run the 'move' command
* @author Maayan & Eden
* @version 2D
*/

package controller.commands;

import common.Level2D;
import model.Model;
import model.data.Actor;
import model.data.Box;
import model.data.Item;
import model.data.Position2D;
import model.policy.MySokobanPolicy;


public class MoveCommand extends GeneralCommand{
	String _direction;

//	MySokobanPolicy _msp;
//	Position2D _old;
//	Actor _actor;

/**
* C'TOR
*/
	public MoveCommand(String direction, Model model) {
		super(model);
		_direction=direction;

		//	_actor=_model.getLevel().getActors().get(0);
		//	_old=new Position2D(_actor.getPos());
		//	_msp=new MySokobanPolicy(_old,_model.getLevel());
	}


	public void execute() {

		//init dest position
		Position2D dest=new Position2D(_model.getLevel().getActors().get(0).getPos());

		//Calculate the destination
		switch(this._direction){
		case "right":
			dest.setY(dest.getY()+1);
			break;
		case "left":
			dest.setY(dest.getY()-1);
			break;
		case "up":
			dest.setX(dest.getX()-1);
			break;
		case "down":
			dest.setX(dest.getX()+1);
			break;
		default:
			System.out.println("This direction is unknown!!!\n"
					+ "Please try again:");
		}


	//why????	dest.setWasTarget(_model.getLevel().getItemInPlace(dest).getPos().isWasTarget());//TargetBox


		//END Calculate

		_model.move(dest);

	}
}
