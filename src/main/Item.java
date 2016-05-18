package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Item {
	private int time = 0;
	private float hp = 4;
	private float x, y;
	
	private Image[] image = new Image[5];

	public Item(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/rock.png", 32, 32);
		image[4] = spriteSheet.getSprite(0, 0);
		image[3] = spriteSheet.getSprite(1, 0);
		image[2] = spriteSheet.getSprite(2, 0);
		image[1] = spriteSheet.getSprite(3, 0);
		image[0] = spriteSheet.getSprite(4, 0);
	}
	
	private boolean isInHitbox(Player player){
		if(player.getX() >= this.x && player.getX() <= (this.x + 32) && player.getY() >= this.y && player.getY() <= (this.y + 32)){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isTop(Player player){
		if(player.getY() >= (this.y - 1) && player.getY() <= (this.y + 5)){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isLeft(Player player){
		if(player.getX() >= (this.x - 1) && player.getX() <= (this.x + 5)){
			return true;
		}else{
			return false;
		}
	}

	private boolean isRight(Player player){
		if(player.getX() <= (this.x + 32) && player.getX() >= (this.x + 27)){
			return true;
		}else{
			return false;
		}
	}

	private boolean isBot(Player player){
		if(player.getY() <= (this.y + 32) && player.getY() >= (this.y + 27)){
			return true;
		}else{
			return false;
		}
	}

	public void render(Graphics g, Player player) {
		g.drawImage(image[(int)(hp)], x, y);
	}
	
	public void update(Player player, int delta){
		if(this.isInHitbox(player) == true){
			if(time % 1000 == 0 && hp != 0 && player.isMining() == true){
				hp--;
			}else if(player.isMoving() == true && hp != 0){
				if(this.isLeft(player) == true && player.getDx() == 1){
					player.setDx(0);
				}if(this.isRight(player) && player.getDx() == -1){
					player.setDx(0);
				}if(this.isTop(player) && player.getDy() == 1){
					player.setDy(0);
				}if(this.isBot(player) && player.getDy() == -1){
					player.setDy(0);
				}
				time = 0;
			}
		}
		time += delta;
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
