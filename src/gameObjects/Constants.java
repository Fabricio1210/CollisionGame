package gameObjects;

import javax.swing.filechooser.FileSystemView;

public class Constants {
	
	// frame dimensions
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	// player properties
	
	public static final int FIRERATE = 200;
	public static final double DELTAANGLE = 0.1;
	public static final double ACC = 0.2;
	public static final double PLAYER_MAX_VEL = 7.0;
	public static final long FLICKER_TIME = 200;
	public static final long SPAWNING_TIME = 3000;
	public static final long GAME_OVER_TIME = 3000;
	
	// Laser properties
	
	public static final double BULLET_VEL = 15.0;
	
	// Meteor properties
	
	public static final double METEOR_VEL = 2.0;
	
	public static final int ENEMIES_SCORE = 20;
	
	// Ufo properties
	
	public static final int NODE_RADIUS = 160;
	
	public static final double SHOOTER_MASS = 60;
	
	public static final int SHOOTER_MAX_VEL = 3;
	
	public static long SHOOTER_FIRE_RATE = 1000;
	
	public static double SHOOTER_ANGLE_RANGE = Math.PI / 2;
	
	public static final int SHOOTER_SCORE = 40;
	
	public static final long SHOOTER_SPAWN_RATE = 10000;
	
	public static final String PLAY = "PLAY";
	
	public static final String EXIT = "EXIT";
	
	public static final int LOADING_BAR_WIDTH = 500;
	
	public static final int LOADING_BAR_HEIGHT = 50;
	
	public static final String RETURN = "RETURN";
	public static final String HIGH_SCORES = "HIGHEST SCORES";
	
	public static final String SCORE = "SCORE";
	public static final String DATE = "DATE";
	
	public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
			"\\Space_Ship_Game\\data.json"; // data.xml if you use XMLParser
	
	// This variables are required to use XMLParser
	
	public static final String PLAYER = "PLAYER";
	public static final String PLAYERS = "PLAYERS";
	
}