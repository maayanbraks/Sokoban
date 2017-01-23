package controller.commands;

import common.Level2D;

public interface LevelLoaderCreator {
	public void create();
	public Level2D getNewLevel();
}
