package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class AStar {
	
	private Path path;
	private AStarPathFinder pathFinder;
	private float endLastX;
	private float endLastY;
	private int pathIndex;
	private int time = 0;
	
    public void init(Map map, Player player, Ennemy ennemy){
    	pathFinder = new AStarPathFinder(map, 50, true);	//Initialise A* object
		endLastX = player.getX()/map.getTilesize();
		endLastY = player.getY()/map.getTilesize();
    }

    public void update(Map map, Player player, Ennemy ennemy, int delta){
    	time += delta;
    	
    	if(time - 700 >= 0){
    		updatePath(map, player, ennemy);
    		time = 0;
    	}
		
		//move the ennemy
		if(path != null ){
				if(pathIndex < path.getLength()){
					
					ennemy.setDirection(path.getX(pathIndex)*map.getTilesize(), path.getY(pathIndex)*map.getTilesize());
					
					if(stepOver(map, ennemy) == true){
						pathIndex++;
					}
				}else{
					ennemy.stopMoving();
				}
				
		}else{
			ennemy.stopMoving();
		}
		
    }

	public boolean stepOver(Map map, Ennemy ennemy){
	return (ennemy.getX() > path.getX(pathIndex)*map.getTilesize()-15 && ennemy.getX() < path.getX(pathIndex)*map.getTilesize()+15
			&& ennemy.getY() > path.getY(pathIndex)*map.getTilesize()-15 && ennemy.getY() < path.getY(pathIndex)*map.getTilesize()+15);
	}
    
    public void updatePath(Map map, Player player, Ennemy ennemy){
    	//check if agent has moved
    			if (endLastX != player.getX()/map.getTilesize() || endLastY != player.getY()/map.getTilesize()) {
    				
    				//find new path from start agent to end agent
    				path = pathFinder.findPath(null, (int)ennemy.getX()/map.getTilesize(), (int)ennemy.getY()/map.getTilesize(), (int)player.getX()/map.getTilesize(), (int)player.getY()/map.getTilesize());
    				pathIndex = 0;    				//print path distance
    				
    				//update agent last positions
    				endLastX = player.getX()/map.getTilesize();
    				endLastY = player.getY()/map.getTilesize();
    			}
    }
    
    public void goHome(Map map, Ennemy ennemy){
    	//check if agent has moved
    				path = pathFinder.findPath(null, (int)ennemy.getX()/map.getTilesize(), (int)ennemy.getY()/map.getTilesize(), (int)ennemy.getStartX()/map.getTilesize(), (int)ennemy.getStartY()/map.getTilesize());
    				pathIndex = 0;    			
    				
    			
    }
    
    public void render(Map map,  Graphics g){
    	//draw path
    			if(path != null){
	    			for(int i = 0; i < path.getLength()-1; i++){
	    				g.setColor(org.newdawn.slick.Color.black);
						g.fillRect((path.getX(i)*map.getTilesize())+96, (path.getY(i)*map.getTilesize()-48),7,7);
						g.setColor(org.newdawn.slick.Color.white);
	    			}
    			}
    }

}