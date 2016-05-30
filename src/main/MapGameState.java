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
	/* Instanciation des ennemis */
	private Ennemy ennemy1 = new Ennemy(player, map, 109*16, 94*16);
	private Ennemy ennemy2 = new Ennemy(player, map, 145*16, 72*16);
	private Ennemy ennemy3 = new Ennemy(player, map, 163*16, 102*16);
	private Ennemy ennemy4 = new Ennemy(player, map, 86*16, 40*16);
	private Ennemy ennemy5 = new Ennemy(player, map, 80*16, 16*16);
	private Ennemy ennemy6 = new Ennemy(player, map, 45*16, 15*16);
	private Ennemy ennemy7 = new Ennemy(player, map, 140*16, 10*16);
	/* Instanciation des ennemis */
	
	/* Instanciation des rochers cassables */
	private Item rock1 = new Item(78*16, 102*16);
	private Item rock2 = new Item(78*16, 104*16);
	private Item rock3 = new Item(78*16, 106*16);
	private Item rock4 = new Item(106*16, 86*16);
	private Item rock5 = new Item(108*16, 86*16);
	private Item rock6 = new Item(127*16, 84*16);
	private Item rock7 = new Item(129*16, 84*16);
	private Item rock8 = new Item(131*16, 84*16);
	private Item rock9 = new Item(133*16, 84*16);
	private Item rock10 = new Item(130*16, 82*16);
	private Item rock11 = new Item(132*16, 82*16);
	private Item rock12 = new Item(134*16, 82*16);
	private Item rock13 = new Item(136*16, 82*16);
	private Item rock20 = new Item(138*16, 82*16);
	private Item rock14 = new Item(42*16, 23*16);
	private Item rock15 = new Item(42*16, 25*16);
	private Item rock16 = new Item(42*16, 27*16);
	private Item rock17 = new Item(90*16, 54*16);
	private Item rock18 = new Item(90*16, 56*16);
	private Item rock19 = new Item(90*16, 58*16);
	/* Instanciation des rochers cassables */
	
	/* Instanciation des ponts */
	private Item pont1 = new Item(149*16, 100*16);
	private Item pont2 = new Item(162*16, 68*16);
	private Item pont3 = new Item(152*16, 35*16);
	private Item pont4 = new Item(154*16, 54*16);
	/* Instanciation des ponts */
	
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private PlayerController controller = new PlayerController(player);
	private Hud hud = new Hud(player);
	private AStar aStar1 = new AStar();
	private AStar aStar2 = new AStar();
	private AStar aStar3 = new AStar();
	private AStar aStar4 = new AStar();
	private AStar aStar5 = new AStar();
	private AStar aStar6 = new AStar();
	private AStar aStar7 = new AStar();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		Music background = new Music("sound/lost-in-the-meadows.ogg");
		background.loop();
		this.map.init();
		this.player.init();
		
		this.ennemy1.init();
		this.ennemy2.init();
		this.ennemy3.init();
		this.ennemy4.init();
		this.ennemy5.init();
		this.ennemy6.init();
		this.ennemy7.init();
		
		this.rock1.init("rock");
		this.rock2.init("rock");
		this.rock3.init("rock");
		this.rock4.init("rock");
		this.rock5.init("rock");
		this.rock6.init("rock");
		this.rock7.init("rock");
		this.rock8.init("rock");
		this.rock9.init("rock");
		this.rock10.init("rock");
		this.rock11.init("rock");
		this.rock12.init("rock");
		this.rock13.init("rock");
		this.rock14.init("rock");
		this.rock15.init("rock");
		this.rock16.init("rock");
		this.rock17.init("rock");
		this.rock18.init("rock");
		this.rock19.init("rock");
		this.rock20.init("rock");
		
		this.pont1.init("bridge");
		this.pont2.init("bridge");
		this.pont3.init("bridge");
		this.pont4.init("bridge");
		
		this.hud.init();
		
		this.aStar1.init(map, player, ennemy1);
		this.aStar2.init(map, player, ennemy2);
		this.aStar3.init(map, player, ennemy3);
		this.aStar4.init(map, player, ennemy4);
		this.aStar5.init(map, player, ennemy5);
		this.aStar6.init(map, player, ennemy6);
		this.aStar7.init(map, player, ennemy7);
		
		container.getInput().addKeyListener(controller);
		container.getInput().addControllerListener(controller);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		
		this.rock1.render(g, player, "rock");
		this.rock2.render(g, player, "rock");
		this.rock3.render(g, player, "rock");
		this.rock4.render(g, player, "rock");
		this.rock5.render(g, player, "rock");
		this.rock6.render(g, player, "rock");
		this.rock7.render(g, player, "rock");
		this.rock8.render(g, player, "rock");
		this.rock9.render(g, player, "rock");
		this.rock10.render(g, player, "rock");
		this.rock11.render(g, player, "rock");
		this.rock12.render(g, player, "rock");
		this.rock13.render(g, player, "rock");
		this.rock14.render(g, player, "rock");
		this.rock15.render(g, player, "rock");
		this.rock16.render(g, player, "rock");
		this.rock17.render(g, player, "rock");
		this.rock18.render(g, player, "rock");
		this.rock19.render(g, player, "rock");
		this.rock20.render(g, player, "rock");
		
		this.pont1.render(g, player, "bridge");
		this.pont2.render(g, player, "bridge");
		this.pont3.render(g, player, "bridge");
		this.pont4.render(g, player, "bridge");
		
		this.player.render(g);
		
		this.ennemy1.render(g);
		this.ennemy2.render(g);
		this.ennemy3.render(g);
		this.ennemy4.render(g);
		this.ennemy5.render(g);
		this.ennemy6.render(g);
		this.ennemy7.render(g);
		
		this.map.renderForeground();
		this.hud.render(g);
		
		//this.aStar.render(map, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
		this.triggers.update();
		
		this.rock1.update(player, delta);
		this.rock2.update(player, delta);
		this.rock3.update(player, delta);
		this.rock4.update(player, delta);
		this.rock5.update(player, delta);
		this.rock6.update(player, delta);
		this.rock7.update(player, delta);
		this.rock8.update(player, delta);
		this.rock9.update(player, delta);
		this.rock10.update(player, delta);
		this.rock11.update(player, delta);
		this.rock12.update(player, delta);
		this.rock13.update(player, delta);
		this.rock14.update(player, delta);
		this.rock15.update(player, delta);
		this.rock16.update(player, delta);
		this.rock17.update(player, delta);
		this.rock18.update(player, delta);
		this.rock19.update(player, delta);
		this.rock20.update(player, delta);
		
		this.pont1.update(player, delta);
		this.pont2.update(player, delta);
		this.pont3.update(player, delta);
		this.pont4.update(player, delta);
		
		this.player.update(delta);
		
		this.ennemy1.update(delta);
		this.ennemy2.update(delta);
		this.ennemy3.update(delta);
		this.ennemy4.update(delta);
		this.ennemy5.update(delta);
		this.ennemy6.update(delta);
		this.ennemy7.update(delta);
		
		this.camera.update(container);
		
		this.aStar1.update(map, player, ennemy1, delta);
		this.aStar2.update(map, player, ennemy2, delta);
		this.aStar3.update(map, player, ennemy3, delta);
		this.aStar4.update(map, player, ennemy4, delta);
		this.aStar5.update(map, player, ennemy5, delta);
		this.aStar6.update(map, player, ennemy6, delta);
		this.aStar7.update(map, player, ennemy7, delta);
		
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
