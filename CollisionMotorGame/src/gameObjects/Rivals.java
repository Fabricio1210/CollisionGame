package gameObjects;

import java.awt.image.BufferedImage;

import graphics.Assets;

public enum Rivals {
	SOILDER(0,null),PHANTOM(0,null),MONSTER(2,Assets.villians),ROBOT(3,Assets.villians);
	public int quantity;
	public BufferedImage[] textures;
	private Rivals(int quantity, BufferedImage[] textures) {
		this.quantity = quantity;
		this.textures = textures;
	}
}
