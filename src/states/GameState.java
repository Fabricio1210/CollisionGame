package states;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameObjects.Enemies;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.Rivals;
import graphics.Animation;
import graphics.Assets;
import main.Window;
import math.Vector2D;

public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	private ArrayList<Animation> explosion = new ArrayList<Animation>();
	private int enemies;
	
	public GameState() {
		player = new Player(new Vector2D(100,500), new Vector2D(), 5, Assets.player,this);
		movingObjects.add(player);
		enemies = 1;
		startWave();
	}
	
	/*public void divideEnemies(Enemies enemy) {
		Rivals rivals = enemy.getRival();
		BufferedImage[] textures = rivals.textures;
		Rivals newRivals = null;
		switch(rivals) {
			case MONSTER:
				newRivals = rivals.PHANTOM;
				break;
			case ROBOT:
				newRivals = rivals.SOILDER;
				break;
			default:
				return;
		}
		for(int i=0;i<rivals.quantity;i++) {
			movingObjects.add(new Enemies(
					enemy.getPosition(),
					new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
					10*Math.random()+1,
					textures[(int)(Math.random()*textures.length)],
					this,
					newRivals
				));
		}
	}*/
	
	private void startWave() {
		double x, y;
		for(int i = 0; i < enemies;i++) {
			x = i % 2 ==0 ? Math.random()*Window.width:0;
			y = i % 2 ==0 ? 0 : Math.random()*Window.height;
			
			
			BufferedImage texture = Assets.villians[(int)(Math.random()*Assets.villians.length)];
			movingObjects.add(new Enemies(
				new Vector2D(x,y),
				new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
				10*Math.random()+1,
				texture,
				this,
				Rivals.ROBOT
			));
		}
		enemies++;
	}
	
	public void playExplosion(Vector2D position){
		explosion.add(new Animation(
				Assets.exp,
				50,
				position.subtract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
				));
	}
	
	public void update() {
		for(int i = 0; i  < movingObjects.size(); i++) {
			movingObjects.get(i).update();
		}
		
		for(int i = 0; i < explosion.size(); i++){
			Animation anim = explosion.get(i);
			anim.update();
			if(!anim.isRunning()){
				explosion.remove(i);
			}
			
		}
		
		for(int i = 0; i  < movingObjects.size(); i++) {
			if(movingObjects.get(i) instanceof Enemies) {
				return;
			}
		}
		startWave();
	}
	
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		for(int i = 0; i  < movingObjects.size(); i++) {
			movingObjects.get(i).draw(g);
		}
		
		for(int i = 0; i < explosion.size(); i++){
			Animation anim = explosion.get(i);
			System.out.print(anim.getPosition());
			g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),
					null);
			
		}
	}


	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}
	
}
