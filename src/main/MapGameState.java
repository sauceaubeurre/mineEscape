package main;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MapGameState extends BasicGameState {
	public static final int ID = 2;

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	private Ennemy ennemy = new Ennemy(map);
	private Item item = new Item(320, 2944);
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private PlayerController controller = new PlayerController(player);
	private Hud hud = new Hud();
	private AStar aStar = new AStar();
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		Music background = new Music("sound/lost-in-the-meadows.ogg");
		background.loop();
		this.map.init();
		this.player.init();
		this.ennemy.init();
		this.item.init();
		this.hud.init();
		this.aStar.init(map, player, ennemy);
		container.getInput().addKeyListener(controller);
		container.getInput().addControllerListener(controller);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		this.item.render(g, player);
		this.player.render(g);
		this.ennemy.render(g);
		this.map.renderForeground();
		this.hud.render(g);
		this.aStar.render(map, g);
		
		

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		this.triggers.update();
		this.item.update(player, delta);
		this.player.update(delta);
		this.ennemy.update(delta);
		this.camera.update(container);
		this.aStar.update(map, player, ennemy, delta);
		
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
