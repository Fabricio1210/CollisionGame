package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import main.Window;
import math.Vector2D;
import states.GameState;

public class Enemies extends MovingObject{
	
	private Rivals rivals;

	public Enemies(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Rivals rivals) {
		super(position, velocity, maxVel, texture, gameState);
		this.rivals = rivals;
		this.velocity = velocity.scale(maxVel);
	}

	@Override
	public void update() {
		position = position.add(velocity);
		if(position.getX() > Window.width) {
			position.setX(-width);
		}
		if(position.getX() < -width) {
			position.setX(Window.width);
		}
		
		if(position.getY() > Window.height) {
			position.setY(-height);
		}
		if(position.getY() < -height) {
			position.setY(Window.height);
		}
	}

	@Override
	public void destroy(){
		//gameState.divideMeteor(this);
		gameState.addScore(Constants.ENEMIES_SCORE, position);
		super.destroy();
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, width/2, height/2);
		g2d.drawImage(texture,at,null);
	}
	
	public Rivals getRival() {
		return rivals;
	}

}
