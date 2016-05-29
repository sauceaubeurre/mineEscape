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
	private int menuState = 0; // 0 Menu, 1 Game, 2 Options, 3 Crédits
	private Image background;
	private StateBasedGame game;
	
	Color greenGray = new Color(45, 50, 30);
	Color black = new Color(0, 0, 0);
	Color lightGray = new Color(147, 147, 147);

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
				g.setColor(greenGray);
				g.drawString("\"S\"", 137, 550);
				g.drawString("\"O\"", 382, 550);
				g.drawString("\"C\"", 612, 550);
			break;
			
			// Options
			case 2:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(greenGray);
				
				g.drawString("Retour", 680, 545);
				g.drawString("\"R\"", 693, 565);
			break;
		
			// Crédits
			case 3:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(lightGray);
				g.drawString("Romain SEUSSE.......................................chef de projet", 60, 100);
				g.drawString("Arnaud SOULAT.......................................responsable thé", 60, 150);
				g.drawString("Alexandre NOURRAIN..................................responsable café", 60, 200);
				g.drawString("Vincent HUET........................................responsable cookies", 60, 250);
				g.drawString("Antoine TEBOULLE....................................responsable jus d'orange", 60, 300);
				g.drawString("Jérémy ZORGUY.......................................responsable petits LU", 60, 350);
				g.setColor(greenGray);
				g.drawString("Retour", 680, 545);
				g.drawString("\"R\"", 693, 565);
			break;
		}	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	/**
	 * Passer Ã  l'ecran de jeu Ã  l'appui de n'importe quel touche.
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
	 * L'identifiant permet d'identifier les diffÃ©rentes boucles, pour passer de l'une Ã  l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
}
