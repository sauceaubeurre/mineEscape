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
	
    public void init(Map map, Player player, Ennemy ennemy){
    	pathFinder = new AStarPathFinder(map, 100, true);	//Initialise A* object
		endLastX = player.getX()/map.getTilesize();
		endLastY = player.getY()/map.getTilesize();
    }

    public void update(Map map, Player player, Ennemy ennemy, int delta){
    	
		updatePath(map, player, ennemy);
		
		//move the ennemy
		if(path != null ){
				if(pathIndex < path.getLength()){
					
					ennemy.setDirection(path.getX(pathIndex)*map.getTilesize(), path.getY(pathIndex)*map.getTilesize());
					//System.out.println("move");
					
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
    				//System.out.println("Path updated "+ pathFinder.getSearchDistance());
    				
    				//update agent last positions
    				endLastX = player.getX()/map.getTilesize();
    				endLastY = player.getY()/map.getTilesize();
    			}
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