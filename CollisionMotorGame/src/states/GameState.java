package states;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameObjects.Constants;
import gameObjects.Enemies;
import gameObjects.Message;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.Rivals;
import gameObjects.Shooter;
import graphics.Animation;
import graphics.Assets;
import graphics.Text;
import main.Window;
import math.Vector2D;


public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	private ArrayList<Animation> explosion = new ArrayList<Animation>();
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int enemies;
	private int score;
	private int lives = 3;
	private int waves = 1;
	
	public GameState() {
		player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
				Constants.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(),
				Constants.PLAYER_MAX_VEL, Assets.player, this); 
		
		movingObjects.add(player);
		
		enemies = 1;
		startWave();
	}
	
	public void addScore(int value, Vector2D position) {
		score += value;
		messages.add(new Message(position, true, "+"+value+" score", Color.WHITE, false, Assets.fontMed, this));
	}
	
	/*
	public void divideEnemies(Enemies enemy) {
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
	}
	*/
	private void startWave() {
		
		messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), false,
				"WAVE "+waves, Color.WHITE, true, Assets.fontBig, this));
		
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
		waves++;
		enemies++;
		spawnShooter();
	}
	
	public void playExplosion(Vector2D position){
		explosion.add(new Animation(
				Assets.exp,
				50,
				position.subtract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
				));
	}
	
	private void spawnShooter() {
		
		int rand = (int) (Math.random()*2);
		
		double x = rand == 0 ? (Math.random()*Constants.WIDTH): 0;
		double y = rand == 0 ? 0 : (Math.random()*Constants.HEIGHT);
		
		ArrayList<Vector2D> path = new ArrayList<Vector2D>();
		
		double posX, posY;
		
		posX = Math.random()*Constants.WIDTH/2;
		posY = Math.random()*Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));

		posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
		posY = Math.random()*Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		posX = Math.random()*Constants.WIDTH/2;
		posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
		posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
		path.add(new Vector2D(posX, posY));
		
		movingObjects.add(new Shooter(
				new Vector2D(x, y),
				new Vector2D(),
				Constants.SHOOTER_MAX_VEL,
				Assets.shooter,
				path,
				this
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
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


		for(int i = 0; i < messages.size(); i++)
			messages.get(i).draw(g2d);
		
		for(int i = 0; i  < movingObjects.size(); i++) {
			movingObjects.get(i).draw(g);
		}
		
		for(int i = 0; i < explosion.size(); i++){
			Animation anim = explosion.get(i);
			System.out.print(anim.getPosition());
			g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),
					null);		
		}
		drawScore(g);
		drawLives(g);	
	}
	
	private void drawScore(Graphics g) {
		
		Vector2D pos = new Vector2D(700, 25);
		
		String scoreToString = Integer.toString(score);
		
		for(int i = 0; i < scoreToString.length(); i++) {
			
			g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
					(int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX() + 20);
			
		}
		
	}
	
	private void drawLives(Graphics g){
		
		Vector2D livePosition = new Vector2D(25, 25);
		
		g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY(), null);
		
		g.drawImage(Assets.numbers[10], (int)livePosition.getX() + 40,
				(int)livePosition.getY() + 5, null);
		
		String livesToString = Integer.toString(lives);
		
		Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
		
		for(int i = 0; i < livesToString.length(); i ++)
		{
			int number = Integer.parseInt(livesToString.substring(i, i+1));
			
			if(number <= 0)
				break;
			g.drawImage(Assets.numbers[number],
					(int)pos.getX() + 60, (int)pos.getY() + 5, null);
			pos.setX(pos.getX() + 20);
		}
		
	}

	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}	
	
	public ArrayList<Message> getMessages() {
		return messages;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void subtractLife() {lives --;}

	
}
