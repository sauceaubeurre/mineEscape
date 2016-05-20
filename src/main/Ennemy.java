package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Ennemy {
	
	public float getDx() {
		return dx;
	}
	public void setDx(float dx) {
		this.dx = dx;
	}
	public float getDy() {
		return dy;
	}
	public void setDy(float dy) {
		this.dy = dy;
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
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public long getTimeLoader() {
		return timeLoader;
	}
	public void setTimeLoader(long timeLoader) {
		this.timeLoader = timeLoader;
	}
	public Animation[] getAnimations() {
		return animations;
	}
	public void setAnimations(Animation[] animations) {
		this.animations = animations;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public static float getSpeed() {
		return speed;
	}

	private float dx = 0, dy = 0;
	private float x = 500, y = 450;
	private int direction = 2;
	private static final float speed = .15f;
	private long timeLoader;
	
	private Animation[] animations = new Animation[12];
	private Map map;

	public Ennemy(Map map) {
		this.map = map;
	}
	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/character2.png", 32, 32);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 7);
	    this.animations[1] = loadAnimation(spriteSheet, 0, 1, 5);
	    this.animations[2] = loadAnimation(spriteSheet, 0, 1, 4);
	    this.animations[3] = loadAnimation(spriteSheet, 0, 1, 6);
	    this.animations[4] = loadAnimation(spriteSheet, 1, 3, 7);
	    this.animations[5] = loadAnimation(spriteSheet, 1, 3, 5);
	    this.animations[6] = loadAnimation(spriteSheet, 1, 3, 4);
	    this.animations[7] = loadAnimation(spriteSheet, 1, 3, 6);
	    this.animations[8] = loadAnimation(spriteSheet, 3, 6, 7);
	    this.animations[9] = loadAnimation(spriteSheet, 3, 6, 5);
	    this.animations[10] = loadAnimation(spriteSheet, 3, 6, 4);
	    this.animations[11] = loadAnimation(spriteSheet, 3, 6, 6);
	  }

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}
	
	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x - 12, (int) y - 5, 25, 14);
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0)], (int) x - 16, (int) y - 30);
		}
}
