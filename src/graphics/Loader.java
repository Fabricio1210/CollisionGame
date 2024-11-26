package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage ImageLoader(String Path) {
		try {
			return ImageIO.read(Loader.class.getResource(Path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
