package main;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class EndGameState extends BasicGameState {

		private GameContainer container;
		public static final int ID = 3;
		private Image background;
		private static StateBasedGame game;

		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {
			this.container = container;
			EndGameState.game = game;
			this.background = new Image("background/gameover.png");

		}

		
		@Override
		public void render(GameContainer container, StateBasedGame game, Graphics g)
				throws SlickException {
			background.draw(0, 0, container.getWidth(), container.getHeight());
		}

		@Override
		public void update(GameContainer container, StateBasedGame game, int delta)
				throws SlickException {
		}

		
		@Override
		public void keyReleased(int key, char c) {
			this.container.exit();
		}

		
		@Override
		public int getID() {
			return ID;
		}

		public static void enterState(int id2) {
			game.enterState(EndGameState.ID);
		}
}