package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import input.KeyBoard;
import main.Window;
import math.Vector2D;
import states.GameState;

public class Player extends MovingObject{

	private Vector2D heading;
	private Vector2D acceleration;
	private final double acc = 0.1;
	private final double deltaAngle = 0.1;
	private boolean accelerating = false;
	private Chronometer fireRate;

	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		heading = new Vector2D(1,0);
		acceleration = new Vector2D();
		fireRate = new Chronometer();
		}

	@Override
	public void update() {
		if(KeyBoard.shoot && !fireRate.isRunning()) {
			double offsetX = 0;
	        double offsetY = 0;

	        double rotatedX = offsetX * Math.cos(angle) - offsetY * Math.sin(angle);
	        double rotatedY = offsetX * Math.sin(angle) + offsetY * Math.cos(angle);

	        Vector2D cannonPosition = getCenter().add(new Vector2D(rotatedX, rotatedY));
	        gameState.getMovingObjects().add(new Bullets(
			        cannonPosition, 
			        new Vector2D(Math.cos(angle), Math.sin(angle)),
			        10,
			        angle, 
			        Assets.bullet1, 
			        gameState));
			fireRate.run(200);
		}
		if(KeyBoard.right) {
			angle+= deltaAngle;
		}
		if(KeyBoard.left) {
			angle-= deltaAngle;
		}
		if(KeyBoard.up) {
			acceleration = heading.scale(acc);
			accelerating = true;
		}else {
			if(velocity.getMagnitude() != 0) {
				acceleration = (velocity.scale(-1).normalize()).scale(acc);
			}
			accelerating = false;
		}
		
		velocity = velocity.add(acceleration);
		velocity = velocity.limit(maxVel);
		heading = heading.setDirection(angle - Math.PI/2);
		position = position.add(velocity);
		
		if(position.getX() > (Window.width-89)) {
		    position.setX(Window.width-89);
		}
		if(position.getY() >= Window.height-89) {
		    position.setY(Window.height - 89);
		}
		if(position.getX() < 0) {
		    position.setX(0);
		}
		if(position.getY() < 0) {
		    position.setY(0);
		}
		fireRate.update();
		collidesWith();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, width/2, height/2);
		AffineTransform at1 = AffineTransform.getTranslateInstance((position.getX()) -10 ,(position.getY()));
		at1.rotate(angle, width/2 + 10, height/2);
		
		if(accelerating) {
			g2d.drawImage(Assets.fire,at1,null);
		}
		g2d.drawImage(texture,at,null);
		
		
	}
	
}
