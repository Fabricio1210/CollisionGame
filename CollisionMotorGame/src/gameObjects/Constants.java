package gameObjects;

public class Constants {
	
	// frame dimensions
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	// player properties
	
	public static final int FIRERATE = 200;
	public static final double DELTAANGLE = 0.1;
	public static final double ACC = 0.2;
	public static final double PLAYER_MAX_VEL = 7.0;
	public static final long FLICKER_TIME = 200;
	public static final long SPAWNING_TIME = 3000;
	
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
	
}