package main;

//import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class MapGameState extends BasicGameState {
	public static final int ID = 2;

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	private Ennemy ennemy = new Ennemy(map);
	private Item item = new Item(22*16, 28*16);
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private PlayerController controller = new PlayerController(player);
	private Hud hud = new Hud();
	
	private Path path;
	private AStarPathFinder pathFinder;
	private float startLastX;
	private float startLastY;
	private float endLastX;
	private float endLastY;
	private int time = 0;
	private int i = -1;

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
		
		//this.controller.setInput(container.getInput());
		container.getInput().addKeyListener(controller);
		container.getInput().addControllerListener(controller);
		
		pathFinder = new AStarPathFinder(map, 100, true);	//Initialise A* object
		startLastX = player.getX()/map.getTilesize();
		startLastY = player.getY()/map.getTilesize();
		endLastX = ennemy.getX()/map.getTilesize();
		endLastY = ennemy.getY()/map.getTilesize();
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
		
		//draw path
		for(int x = 0; x < map.getWidthInTiles(); x++) {
			for(int y = 0; y < map.getHeightInTiles(); y++) {	
				if(path != null) {
					if(path.contains(x, y)) {
						g.setColor(org.newdawn.slick.Color.black);
						g.fillRect((x*map.getTilesize()), (y*map.getTilesize()),7,7);
						g.setColor(org.newdawn.slick.Color.white);
					}	
				}
			}
		}
		

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		//this.controller.update();
		this.triggers.update();
		this.item.update(player, delta);
		this.player.update(delta);
		this.camera.update(container);
		
		//check if agent has moved
				if (startLastX != player.getX()/map.getTilesize()  || startLastY != player.getY()/map.getTilesize() 
						|| endLastX != ennemy.getX()/map.getTilesize() || endLastY != ennemy.getY()/map.getTilesize()) {
					
					//find new path from start agent to end agent
					path = pathFinder.findPath(null, (int)player.getX()/map.getTilesize(), (int)player.getY()/map.getTilesize(), (int)ennemy.getX()/map.getTilesize(), (int)ennemy.getY()/map.getTilesize());
					
					//print path distance
					System.out.println("Path updated "+ pathFinder.getSearchDistance());
					
					//update agent last positions
					startLastX = player.getX()/map.getTilesize();
					startLastY = player.getY()/map.getTilesize();
					endLastX = ennemy.getX()/map.getTilesize();
					endLastY = ennemy.getY()/map.getTilesize();
				}
				
				//move the ennemy
				/*if(path != null && time % 1000 == 0){
					if(i == -1){
						i = path.getLength() -1;
					}
					else if( i > 0 ){
						ennemy.setX(path.getX(i)*map.getTilesize());
						ennemy.setY(path.getY(i)*map.getTilesize());
						i--;
					}
					else{
						i = -1;
					}
				}*/
				time += delta;
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
