package graphics;
import java.awt.image.BufferedImage;


public class Assets {
	
	public static BufferedImage player;
	public static BufferedImage fire;
	public static BufferedImage bullet1;
	public static BufferedImage phantom;
	public static BufferedImage soilder;
	public static BufferedImage robot;
	public static BufferedImage monster;
	public static BufferedImage[] villians = new BufferedImage[4];
	public static BufferedImage[] exp = new BufferedImage[9];
	
	
	public static void init() {
		player = Loader.ImageLoader("/character/soilder.png");
		fire = Loader.ImageLoader("/effects/fire.png");
		bullet1 = Loader.ImageLoader("/bullets/gun1.png");
		robot = Loader.ImageLoader("/character/enemy1.png");
		monster = Loader.ImageLoader("/character/enemy2.png");
		phantom = Loader.ImageLoader("/character/enemy4.png");
		soilder = Loader.ImageLoader("/character/enemy3.png");
		for(int i=0; i < villians.length;i++) {
			villians[i] = Loader.ImageLoader("/character/enemy"+(i+1)+".png");
		}
		for(int i=0; i < exp.length;i++) {
			exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
		}
	}
	
}
