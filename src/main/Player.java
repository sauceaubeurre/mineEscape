
package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class Player {

	private float dx = 0, dy = 0;
	private float x = 11*16, y = 107*16;
	private int direction = 2;
	private float life = .5f;
	private float stamina = 1f;
	private boolean onStair = false;
	private boolean mining = false;
	private static final float speed = .15f;
	
	private Animation[] animations = new Animation[12];
	private Map map;

	public Player(Map map) {
		this.map = map;
	}


	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/character2.png", 32, 32);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 3);
	    this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
	    this.animations[2] = loadAnimation(spriteSheet, 0, 1, 0);
	    this.animations[3] = loadAnimation(spriteSheet, 0, 1, 2);
	    this.animations[4] = loadAnimation(spriteSheet, 1, 3, 3);
	    this.animations[5] = loadAnimation(spriteSheet, 1, 3, 1);
	    this.animations[6] = loadAnimation(spriteSheet, 1, 3, 0);
	    this.animations[7] = loadAnimation(spriteSheet, 1, 3, 2);
	    this.animations[8] = loadAnimation(spriteSheet, 3, 6, 3);
	    this.animations[9] = loadAnimation(spriteSheet, 3, 6, 1);
	    this.animations[10] = loadAnimation(spriteSheet, 3, 6, 0);
	    this.animations[11] = loadAnimation(spriteSheet, 3, 6, 2);
	    
	    
	   x = 11*16;
	   y = 107*16;
	   direction = 2;
	   life = .5f;
		stamina = 1f;
	  }

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public void render(Graphics g) {
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0) + (mining ? 8 : 0)  ], (int) x - 16, (int) y - 30);
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
	
	public boolean isInHitbox(float x, float y, float deltaX, float deltaY){
		if(this.x >= (x - deltaX) && this.x <= (x + deltaX) && this.y >= (y - deltaY) && this.y <= (y + deltaY)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isTop(float x, float y){
		if(this.y >= (y - 1) && this.y <= (y + 5)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isLeft(float x, float y){
		if(this.x >= (x - 1) && this.x <= (x + 5)){
			return true;
		}else{
			return false;
		}
	}

	public boolean isRight(float x, float y){
		if(this.x <= (x + 32) && this.x >= (x + 27)){
			return true;
		}else{
			return false;
		}
	}

	public boolean isBot(float x, float y){
		if(this.y <= (y + 32) && this.y >= (y + 27)){
			return true;
		}else{
			return false;
		}
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
	
	public float getSpeed() {
		return speed;
	}
	
	public boolean isMining() {
		return mining;
	}

	public void setMining(boolean mining) {
		this.mining = mining;
	}

	

	public void setDirection(int direction) {
		switch (direction) {
		case 0:
			dx = 0;
			dy = -1;
			break;
		case 1:
			dx = -1;
			dy = 0;
			break;
		case 2:
			dx = 0;
			dy = 1;
			break;
		case 3:
			dx = 1;
			dy = 0;
			break;
		default:
			dx = 0;
			dy = 0;
			break;
		}
	}
	
	public int getDirection() {
		return direction;
	}

	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}

	public void stopMoving() {
		dx = 0;
		dy = 0;
	}

	public boolean isOnStair() {
		return onStair;
	}

	public void setOnStair(boolean onStair) {
		this.onStair = onStair;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}
	
	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public float getStamina() {
		return stamina;
	}

	public void setStamina(float stamina) {
		this.stamina = stamina;
	}
}
