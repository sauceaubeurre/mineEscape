package main;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class PlayerController implements KeyListener {

	private Player player;
	private Input input;

	public PlayerController(Player player) {
		this.player = player;
	}

	public void update() {
		if (input.getControllerCount() > 0) {
			player.setDx(input.getAxisValue(0, 1));
			player.setDy(input.getAxisValue(0, 0));
			
		}
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_Z:	this.player.setDy(-1);this.player.setMining(false);break;
		case Input.KEY_Q:	this.player.setDx(-1);this.player.setMining(false);break;
		case Input.KEY_S:	this.player.setDy(1);this.player.setMining(false);break;
		case Input.KEY_D:	this.player.setDx(1);this.player.setMining(false);break;
		case Input.KEY_SPACE:	this.player.setMining(true);this.player.stopMoving();break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_Z:if(player.getDy() == -1){player.setDy(0);} break;
		case Input.KEY_S:if(player.getDy() == 1){player.setDy(0);} break;	
		case Input.KEY_Q:if(player.getDx() == -1){player.setDx(0);} break;
		case Input.KEY_D:if(player.getDx() == 1){player.setDx(0);} break;
		case Input.KEY_SPACE:	player.setMining(false);
		}
	}

}
