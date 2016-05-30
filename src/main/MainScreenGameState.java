package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
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
	private int menuState = 0; // 0 Menu, 1 Game, 2 Histoire, 3 Options, 4 Crédits, 5 #LikeIfYouCryEverytime
	private Image background;
	private Music music;
	private StateBasedGame game;
	
	Color greenGray = new Color(45, 50, 30);
	Color black = new Color(0, 0, 0);
	Color lightGray = new Color(147, 147, 147);

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		this.background = new Image("background/newMenu.png");
		music = new Music("sound/diggy-hole.ogg");
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
				g.drawString("\"S\"", 145, 545);
				g.drawString("\"L\"", 310, 545);
				g.drawString("\"O\"", 462, 545);
				g.drawString("\"A\"", 635, 545);
			break;
			
			// Histoire
			case 2:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(lightGray);
				g.drawString("Dans les années 80, un pauvre orphelin abandonné par la société...", 60, 100);
				g.drawString("n'avait d'autre choix pour survivre que d'aller travailler aux mines.", 60, 120);
				g.drawString("Il ne pouvait compter que sur lui même, la vie était dure et les autres", 60, 140);
				g.drawString("travailleurs sans pitié malgré son jeune âge.", 60, 160);
				g.drawString("Un jour, un violent tremblement de terre engloutit la mine et ses hôtes.", 60, 200);
				g.drawString("La catastrophe ne laissa aucun survivant sinon le jeune garçon que seule", 60, 220);
				g.drawString("sa faible carrure sauva par miracle. Il devait maintenant sortir au plus", 60, 240);
				g.drawString("vite de la mine pouvant s'effondrer une fois de plus à tout moment.", 60, 260);
				g.drawString("Malheureusement le destin était cruel avec le garçon, car des éboulements", 60, 300);
				g.drawString("rendent difficile sa fuite et de terribles monstres semblent sortir des", 60, 320);
				g.drawString("entrailles de la terre...", 60, 340);
				g.setColor(greenGray);
				g.drawString("Back", 690, 545);
				g.drawString("\"B\"", 695, 565);
			break;
				
			// Options
			case 3:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(lightGray);
				g.drawString("Bonjour ! Tu t'es perdu ?...", 60, 100);
				g.drawString("Oui, je cherchais les options", 350, 150);
				g.drawString("Ahaha ! Sur un petit jeu comme celui là ?", 60, 200);
				g.drawString("La naïveté des humains me fera toujours rire", 60, 220);
				g.drawString("EH ! Les scripts ne sont pas censés mal parler !", 350, 270);
				g.drawString("Tss...", 60, 320);
				g.drawString("Je t'invite à regagner le menu en appuyant sur R", 60, 340);
				g.drawString("Bisou ;)", 60, 450);
				g.setColor(greenGray);
				g.drawString("Back", 690, 545);
				g.drawString("\"B\"", 695, 565);
			break;
		
			// Crédits
			case 4:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(lightGray);
				g.drawString("Romain SEUSSE.......................................chef cuistot", 60, 100);
				g.drawString("Arnaud SOULAT.......................................responsable 4/4", 60, 150);
				g.drawString("Alexandre NOURRAIN..................................responsable café", 60, 200);
				g.drawString("Vincent HUET........................................responsable aligot", 60, 250);
				g.drawString("Antoine TEBOULLE....................................responsable jus d'orange", 60, 300);
				g.drawString("Jérémy ZORGUY.......................................responsable petits LU", 60, 350);
				g.setColor(greenGray);
				g.drawString("Back", 690, 545);
				g.drawString("\"B\"", 695, 565);
			break;
			
			// Like if you cry everytime
			case 5:
				g.setColor(black);
				g.drawRect(0, 0, container.getWidth(), container.getHeight());
				g.setColor(lightGray);
				g.drawString("#LikeIfYouCryEverytime", 300, 250);
				g.setColor(greenGray);
				g.drawString("Back", 690, 545);
				g.drawString("\"B\"", 695, 565);
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
			music.loop();
			menuState = 1;
		}else if(menuState == 0 && (c == 'l' || c == 'L')){
			menuState = 2;
		}else if(menuState == 0 && (c == 'o' || c == 'O')){
			menuState = 3;
		}else if(menuState == 0 && (c == 'a' || c == 'A')){
			menuState = 4;
		}else if(menuState == 2 && (c == 'b' || c == 'B')){
			menuState = 5;
		}else if(menuState == 3 && (c == 'b' || c == 'B')){
			menuState = 0;
		}else if(menuState == 4 && (c == 'b' || c == 'B')){
			menuState = 0;
		}else if(menuState == 5 && (c == 'b' || c == 'B')){
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
