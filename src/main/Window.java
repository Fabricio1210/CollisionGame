package main;
import javax.swing.JFrame;

import graphics.Assets;
import input.KeyBoard;
import input.MouseInput;
import states.GameState;
import states.LoadingState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable{
	
	public static final int width = 800, height = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double targetTime = 1000000000/FPS;
	private double delta = 0;
	private int averageFPS = FPS;
	
	//private GameState gameState;
	private KeyBoard keyBoard;
	private MouseInput mouseInput;
	
	public Window() {
		setTitle("Collision Game");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		canvas = new Canvas();
		keyBoard = new KeyBoard();
		mouseInput = new MouseInput();
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(true);
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		canvas.addMouseListener(mouseInput);
		canvas.addMouseMotionListener(mouseInput);
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}

	
	
	
	public static void main(String[] args) {
		new Window().start();
		
	}
	
	int x=0;
	private void update() {
		keyBoard.update();
		//gameState.update();
		State.getCurrentState().update();
	}
	
	private void draw() {
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//------------------------------------
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		State.getCurrentState().draw(g);
		//gameState.draw(g);
		
		g.drawString("" + averageFPS, 10, 10);
		
		//------------------------------------
		g.dispose();
		bs.show();
	}
	
	private void init() {
		
		Thread loadingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Assets.init();
			}
		});	
		State.changeState(new LoadingState(loadingThread));
	}
	
	@Override
	public void run() {
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		init();
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/targetTime;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1) {
				update();
				draw();
				delta--;
				frames++;
				//System.out.println(frames);
			}
			if(time >= 1000000000) {
				averageFPS = frames;
				frames = 0;
				time = 0;
			}
		}
		
		stop();
		
	}
	
	
	private void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void stop() {
		try {
			thread.join();
			running = false;
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
