package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Item {
	private int time = 0;
	private float hp = 4;
	private float x, y;
	private SpriteSheet spriteSheet;
	
	private Image[] imageRock = new Image[5];
	private Image[] imageBridge = new Image[6];

	public Item(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void init(String itemType) throws SlickException {
		if(itemType == "bridge"){
			spriteSheet = new SpriteSheet("sprites/" + itemType + ".png", 224, 96);
			
			imageBridge[5] = spriteSheet.getSprite(0, 0);
			imageBridge[4] = spriteSheet.getSprite(1, 0);
			imageBridge[3] = spriteSheet.getSprite(2, 0);
			imageBridge[2] = spriteSheet.getSprite(3, 0);
			imageBridge[1] = spriteSheet.getSprite(4, 0);
			imageBridge[0] = spriteSheet.getSprite(5, 0);
			
			hp = 5;
		}else if(itemType == "rock"){
			spriteSheet = new SpriteSheet("sprites/" + itemType + ".png", 32, 32);
			
			imageRock[4] = spriteSheet.getSprite(0, 0);
			imageRock[3] = spriteSheet.getSprite(1, 0);
			imageRock[2] = spriteSheet.getSprite(2, 0);
			imageRock[1] = spriteSheet.getSprite(3, 0);
			imageRock[0] = spriteSheet.getSprite(4, 0);
			
			hp = 4;
		}
	}

	public void render(Graphics g, Player player, String itemType) {
		if(itemType == "bridge"){
			g.drawImage(imageBridge[(int)(hp)], x, y);
		}else if(itemType == "rock"){
			g.drawImage(imageRock[(int)(hp)], x, y);
		}
	}
	
	public void update(Player player, int delta){
		if(player.isInHitbox(x, y, 32, 32) == true){
			if((time - 1000) > 0 && hp != 0 && player.isMining() == true){
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
