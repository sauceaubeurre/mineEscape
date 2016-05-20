package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class AStar {
	
	private Path path;
	private AStarPathFinder pathFinder;
	private float endLastX;
	private float endLastY;
	private int pathIndex = -1;
	
    public void init(Map map, Player player, Ennemy ennemy){
    	pathFinder = new AStarPathFinder(map, 100, true);	//Initialise A* object
		endLastX = player.getX()/map.getTilesize();
		endLastY = player.getY()/map.getTilesize();
    }

    public void update(Map map, Player player, Ennemy ennemy, int delta){
    	
		updatePos(map, player, ennemy);
		
		/*if(path != null && time % 1000 == 0){
			if( pathIndex < path.getLength()){
				ennemy.setX(path.getX(pathIndex)*map.getTilesize());
				ennemy.setY(path.getY(pathIndex)*map.getTilesize());
				pathIndex++;
			}
			
		}*/
		
		//move the ennemy
		if(path != null ){
			if(stepOver(map, ennemy) && pathIndex < path.getLength()-1){
					pathIndex++;
					System.out.println("increase " +pathIndex);
			}
				setEnnemyDirection(map, ennemy);
				ennemy.move(delta);
				System.out.println("move");
			
			
		}
			
    }

	public boolean stepOver(Map map, Ennemy ennemy){
	return (ennemy.getX() > path.getX(pathIndex)*map.getTilesize()-15 && ennemy.getX() < path.getX(pathIndex)*map.getTilesize()+15
			&& ennemy.getY() > path.getY(pathIndex)*map.getTilesize()-15 && ennemy.getY() < path.getY(pathIndex)*map.getTilesize()+15);
	}
    
    public void updatePos(Map map, Player player, Ennemy ennemy){
    	//check if agent has moved
    			if (endLastX != player.getX()/map.getTilesize() || endLastY != player.getY()/map.getTilesize()) {
    				
    				//find new path from start agent to end agent
    				path = pathFinder.findPath(null, (int)ennemy.getX()/map.getTilesize(), (int)ennemy.getY()/map.getTilesize(), (int)player.getX()/map.getTilesize(), (int)player.getY()/map.getTilesize());
    				pathIndex = 0;
    				//print path distance
    				System.out.println("Path updated "+ pathFinder.getSearchDistance());
    				
    				//update agent last positions
    				endLastX = player.getX()/map.getTilesize();
    				endLastY = player.getY()/map.getTilesize();
    			}
    }
    
    public void setEnnemyDirection(Map map, Ennemy ennemy){
    	//set ennemy Dx
		if(ennemy.getX() > path.getX(pathIndex)*map.getTilesize() + 15){
			ennemy.setDx(-1);
		}
		else if(ennemy.getX() < path.getX(pathIndex)*map.getTilesize() - 15){
			ennemy.setDx(1);
		}
		else{
			ennemy.setDx(0);
		}
		
		//set ennemy Dy
		if(ennemy.getY() > path.getY(pathIndex)*map.getTilesize() + 15){
			ennemy.setDy(-1);
		}
		else if(ennemy.getY() < path.getY(pathIndex)*map.getTilesize() - 15){
			ennemy.setDy(1);
		}
		else{
			ennemy.setDy(0);
		}
    }
    
    public void render(Map map,  Graphics g){
    	//draw path
    			for(int x = 0; x < map.getWidthInTiles(); x++) {
    				for(int y = 0; y < map.getHeightInTiles(); y++) {	
    					if(path != null) {
    						if(path.contains(x, y)) {
    							g.setColor(org.newdawn.slick.Color.black);
    							g.fillRect((x*map.getTilesize())+96, (y*map.getTilesize()-48),7,7);
    							g.setColor(org.newdawn.slick.Color.white);
    						}	
    					}
    				}
    			}
    }

}