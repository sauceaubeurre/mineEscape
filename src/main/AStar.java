package main;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class AStar {
	
	private Path path;
	private AStarPathFinder pathFinder;
	private float startLastX;
	private float startLastY;
	private float endLastX;
	private float endLastY;
	private int time = 0;
	private int i = -1;
	
    public void init(Map map, Player player, Ennemy ennemy){
    	pathFinder = new AStarPathFinder(map, 100, true);	//Initialise A* object
		startLastX = player.getX()/map.getTilesize();
		startLastY = player.getY()/map.getTilesize();
		endLastX = ennemy.getX()/map.getTilesize();
		endLastY = ennemy.getY()/map.getTilesize();
    }

    public void update(Map map, Player player, Ennemy ennemy, int delta){
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
		if(path != null && time % 1000 == 0){
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
		}
		time += delta;
    }
    
    public void render(){
    	
    }

}