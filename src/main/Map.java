package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;


public class Map implements TileBasedMap {
	private TiledMap tiledMap;
	private  final int tileSize = 16;
	private int blockedTileId = 9;
	public TiledMap getTiledMap() {
		return tiledMap;
	}




	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}




	public int getBlockedTileId() {
		return blockedTileId;
	}




	public void setBlockedTileId(int blockedTileId) {
		this.blockedTileId = blockedTileId;
	}




	public boolean[][] getVisited() {
		return visited;
	}




	public void setVisited(boolean[][] visited) {
		this.visited = visited;
	}




	public boolean[][] getBlocked() {
		return blocked;
	}




	public void setBlocked(boolean[][] blocked) {
		this.blocked = blocked;
	}




	public int getTilesize() {
		return tileSize;
	}

	private boolean [][] visited;
	private boolean [][] blocked;
	

	public void init() throws SlickException {
		this.tiledMap = new TiledMap("map/testRF.tmx");
		//this.tiledMap = new TiledMap("map/maptest.tmx");
		blocked = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];
		visited = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];
	
		for (int x = 0; x < tiledMap.getWidth(); x++){
            for (int y = 0; y < tiledMap.getHeight(); y++){
            		int tileID = tiledMap.getTileId(x, y, blockedTileId);
                    String value = tiledMap.getTileProperty(tileID, "blocked", "false");
                    if ("true".equals(value)){
                   	 	blocked[x][y] = true;
            	}
           	 
                
            }
        }
	
	}

	
	
	
	public void renderBackground() {
		this.tiledMap.render(0, 0, 0);
		this.tiledMap.render(0, 0, 1);
		this.tiledMap.render(0, 0, 2);
		this.tiledMap.render(0, 0, 3);
		this.tiledMap.render(0, 0, 4);
		//this.tiledMap.render(0, 0, 7);
	}

	public void renderForeground() {
		this.tiledMap.render(0, 0, 5);
		this.tiledMap.render(0, 0, 6);
		
	}
	
	public void setTiledId(int x,int y,int layerIndex,int tileid) {
		this.tiledMap.setTileId(x, y, layerIndex, tileid);
	}

	public boolean isCollision(float x, float y) {
		int tileW = this.tiledMap.getTileWidth();
		int tileH = this.tiledMap.getTileHeight();
		int logicLayer = this.tiledMap.getLayerIndex("logic");
		Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
		boolean collision = tile != null;
		if (collision) {
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			collision = color.getAlpha() > 0;
		}
		return collision;
	}

	public void changeMap(String file) throws SlickException {
		this.tiledMap = new TiledMap(file);
	}

	public int getObjectCount() {
		return this.tiledMap.getObjectCount(0);
	}

	public String getObjectType(int objectID) {
		return this.tiledMap.getObjectType(0, objectID);
	}

	public float getObjectX(int objectID) {
		return this.tiledMap.getObjectX(0, objectID);
	}

	public float getObjectY(int objectID) {
		return this.tiledMap.getObjectY(0, objectID);
	}

	public float getObjectWidth(int objectID) {
		return this.tiledMap.getObjectWidth(0, objectID);
	}

	public float getObjectHeight(int objectID) {
		return this.tiledMap.getObjectHeight(0, objectID);
	}

	public String getObjectProperty(int objectID, String propertyName, String def) {
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}

	@Override
	public int getWidthInTiles() {
		return tiledMap.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		 return tiledMap.getHeight();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
		
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return tiledMap.getTileId(tx, ty, blockedTileId) != 0;
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 1.0f;
	}
	
	public void clearVisited() {
		for (int x = 0;x < tiledMap.getWidth();x++) {
			for (int y = 0; y < tiledMap.getHeight();y++) {
				visited[x][y] = false;
			}
		}
	}
	
	 public boolean isBlocked(float x, float y){  
	        try{
	        	int xBlock = (int)x / tileSize;
	            int yBlock = (int)y / tileSize;
	            return blocked[xBlock][yBlock];
	         }catch (ArrayIndexOutOfBoundsException e){
	            e.printStackTrace();
	            return blocked[0][0];
	         }
	    }

}
