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
	
	private boolean interaction(Player player){
		if(player.getX() >= this.x && player.getX() <= (this.x + 32) && player.getY() >= this.y && player.getY() <= (this.y + 32)){
			return true;
		}else{
			return false;
		}
	}

	public void render(Graphics g, Player player) {
		g.drawImage(image[(int)(hp)], x, y);
	}
	
	public void update(Player player, int delta){
		if(this.interaction(player) == true){
			if(time % 1000 == 0 && hp != 0 && player.isMining() == true){
				hp--;
			}else if(player.isMoving() == true && hp != 0){
				if(this.getX()+32 - player.getX() > 0 && player.getDx() == 1){
					player.setDx(0);
				}else if(this.getX() - player.getX() < 0 && player.getDx() == -1){
					player.setDx(0);
				}else if(this.getY()+32 - player.getY() > 0 && player.getDy() == 1){
					player.setDy(0);
				}else if(this.getY() - player.getY() < 0 && player.getDy() == -1){
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
