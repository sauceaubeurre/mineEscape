/**
 * 
 */
package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Camera {

	private Player player;
	private float xCamera, yCamera;

	public Camera(Player player) {
		this.player = player;
		this.xCamera = player.getX();
		this.yCamera = player.getY();
	}

	public void place(GameContainer container, Graphics g) {
		g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2 - (int) this.yCamera);
	}

	public void update(GameContainer container) {
		if(player.getX() < 400){
			xCamera = 400;
		}else if(player.getX() > 2800){
			xCamera = 2800;
		}else{
			xCamera = player.getX();	
		}
		
		if(player.getY() < 300){
			yCamera = 300;
		}else if(player.getY() > 1620){
			yCamera = 1620;
		}else{
			yCamera = player.getY();	
		}
		
		System.out.println("x = "+xCamera+";y = "+yCamera);
		
		/*int w = container.getWidth() / 10;
		if (this.player.getX() > this.xCamera + w) {
			this.xCamera = this.player.getX() - w;
		} else if (this.player.getX() < this.xCamera - w) {
			this.xCamera = this.player.getX() + w;
		}
		int h = container.getHeight() / 10;
		if (this.player.getY() > this.yCamera + h) {
			this.yCamera = this.player.getY() - h;
		} else if (this.player.getY() < this.yCamera - h) {
			this.yCamera = this.player.getY() + h;
		}*/
	}

}
