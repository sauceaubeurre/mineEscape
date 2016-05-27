package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Ennemy {
	
	private float dx = 0, dy = 0;
	//private float x = 488, y = 488;
	private float x = 25*16, y = 100*16;
	private int direction = 2;
	private static final float speed = .05f;
	private boolean onStair;
	
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
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0)], (int) x - 16, (int) y - 30);
		}
	
	
	
	public void update(int delta) {
		if (this.isMoving()) {
			updateDirection();
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = this.map.isCollision(futurX, futurY);
			if (collision) {
				stopMoving();
			} else {
				this.x = futurX;
				this.y = futurY;
			}
		}
	}
	
	private float getFuturX(int delta) {
		return this.x + speed * delta * dx;
	}

	private float getFuturY(int delta) {
		float futurY = this.y + speed * delta * dy;
		if (this.onStair) {
			futurY = futurY - speed * dx * delta;
		}
		return futurY;
	}
	
	private void updateDirection() {
		if (dx > 0 && dx >= Math.abs(dy)) {
			direction = 3;
		} else if (dx < 0 && -dx >= Math.abs(dy)) {
			direction = 1;
		} else if (dy < 0) {
			direction = 0;
		} else if (dy > 0) {
			direction = 2;
		}
	}
	
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
	public void setDirection(int px, int py) {
		//set ennemy Dx
				if(x > px + 5){
					dx = -1;
				}
				else if(x < px - 5){
					dx = 1;
				}
				else{
					dx = 0;
				}
				
				//set ennemy Dy
				if(y > py + 5){
					dy = -1;
				}
				else if(y < py - 5){
					dy = 1;
				}
				else{
					dy = 0;
				}
	
	}
	
	public void stopMoving() {
		dx = 0;
		dy = 0;
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
	
}
