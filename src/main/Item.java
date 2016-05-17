package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Item {
	private int hp = 4;
	private float x = 22*16, y = 27*16;
	private boolean hit;
	
	private Animation animation = new Animation();
	private Map map;

	public Item(Map map) {
		this.map = map;
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/rock.png", 32, 32);
	    animation.addFrame(spriteSheet.getSprite(0, 0), 1200);
	    animation.addFrame(spriteSheet.getSprite(1, 0), 1200);
	    animation.addFrame(spriteSheet.getSprite(2, 0), 1200);
	    animation.addFrame(spriteSheet.getSprite(3, 0), 1200);
	    animation.addFrame(spriteSheet.getSprite(4, 0), 1200);
	}

	public void render(Graphics g) {
		g.drawAnimation(animation, x, y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
