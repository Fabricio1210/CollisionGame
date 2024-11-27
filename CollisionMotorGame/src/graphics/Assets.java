package graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;


public class Assets {
	
	public static BufferedImage player;
	public static BufferedImage fire;
	public static BufferedImage bullet1;
	public static BufferedImage bullet2;
	public static BufferedImage phantom;
	public static BufferedImage soilder;
	public static BufferedImage robot;
	public static BufferedImage monster;
	public static BufferedImage shooter;
	public static BufferedImage[] villians = new BufferedImage[4];
	public static BufferedImage[] exp = new BufferedImage[9];
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	public static Font fontBig;
	public static Font fontMed;
	
	public static void init() {
		player = Loader.ImageLoader("/character/soilder.png");
		fire = Loader.ImageLoader("/effects/fire.png");
		bullet1 = Loader.ImageLoader("/bullets/gun1.png");
		bullet2 = Loader.ImageLoader("/bullets/gun2.png");
		robot = Loader.ImageLoader("/character/enemy1.png");
		monster = Loader.ImageLoader("/character/enemy2.png");
		phantom = Loader.ImageLoader("/character/enemy4.png");
		soilder = Loader.ImageLoader("/character/enemy3.png");
		shooter = Loader.ImageLoader("/character/UfoEnemy.png");
		life = Loader.ImageLoader("/others/life.png");
		
		fontBig = Loader.loadFont("/font/futureFont.ttf", 42);
		fontMed = Loader.loadFont("/font/futureFont.ttf", 20);
		
		//shooter = Loader.ImageLoader("/character/Ufo-enemy.png");
		for(int i=0; i < villians.length;i++) {
			villians[i] = Loader.ImageLoader("/character/enemy"+(i+1)+".png");
		}
		for(int i=0; i < exp.length;i++) {
			exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
		}
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = Loader.ImageLoader("/numbers/n"+i+".png");
	}
	
}
