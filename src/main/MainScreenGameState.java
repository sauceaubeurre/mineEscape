package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private int menuState = 0; // 0 Menu, 1 Game, 2 Options, 3 Cr�dits
	private Image background;
	private StateBasedGame game;
	
	Color color = new Color(45, 50, 30);
	Color color1 = new Color(0, 0, 0);

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		this.background = new Image("background/menu.png");
	}

	/**
	 * Contenons nous d'afficher l'image de fond. .
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		switch(menuState){
			// Menu principal
			case 0:
				background.draw(0, 0, container.getWidth(), container.getHeight());
				g.setColor(color);
				g.drawString("\"S\"", 137, 550);
				g.drawString("\"O\"", 382, 550);
				g.drawString("\"C\"", 612, 550);
			break;
			
			// Options
			case 2:
				g.setColor(color1);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(color);
				g.drawString("Retour", 680, 545);
				g.drawString("\"R\"", 693, 565);
			break;
		
			// Cr�dits
			case 3:
				g.setColor(color1);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(color);
				g.drawString("Retour", 680, 545);
				g.drawString("\"R\"", 693, 565);
			break;
		}	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	/**
	 * Passer à l'ecran de jeu à l'appui de n'importe quel touche.
	 */
	@Override
	public void keyReleased(int key, char c) {
		if(menuState == 0 && (c == 's' || c == 'S')){
			game.enterState(MapGameState.ID);
			menuState = 1;
		}else if(menuState == 0 && (c == 'o' || c == 'O')){
			menuState = 2;
		}else if(menuState == 0 && (c == 'c' || c == 'C')){
			menuState = 3;
		}else if(menuState == 2 && (c == 'r' || c == 'R')){
			menuState = 0;
		}else if(menuState == 3 && (c == 'r' || c == 'R')){
			menuState = 0;
		}
	}

	/**
	 * L'identifiant permet d'identifier les différentes boucles, pour passer de l'une à l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
}
