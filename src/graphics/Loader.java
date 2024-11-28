package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage ImageLoader(String Path) {
		try {
			if (Loader.class.getResource(Path) == null) {
				throw new IllegalArgumentException("Resource not found: " + Path);
			}
			return ImageIO.read(Loader.class.getResource(Path));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static Font loadFont(String path, int size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
