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

	public void render(Graphics g, Player player) {
		g.drawImage(image[(int)(hp)], x, y);
	}
	
	public void update(Player player, int delta){
		if(player.isInHitbox(x, y) == true){
			System.out.println(time);
			if((time - 1000) > 0 && hp != 0 && player.isMining() == true){
				System.out.println("hit rock");
				hp--;
				time = 0;
			}else if(player.isMoving() == true && hp != 0){
				if(player.isLeft(x, y) == true && player.getDx() == 1){
					player.setDx(0);
				}if(player.isRight(x, y) && player.getDx() == -1){
					player.setDx(0);
				}if(player.isTop(x, y) && player.getDy() == 1){
					player.setDy(0);
				}if(player.isBot(x, y) && player.getDy() == -1){
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
