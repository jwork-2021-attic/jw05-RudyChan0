package com.world;

import com.world.creature.CreatureFactory;
import com.world.item.ItemFactory;
import com.world.tile.Tile;
import com.world.tile.TileFactory;

public class WorldBuilder {
    private int width;
    private int height;
    private FloorBuilder floorBuilder;

    public WorldBuilder(int width, int height){
        this.width = width;
        this.height = height;
    }

    public World build(){
        World world=new World(width, height);
        CreatureFactory creatureFactory=new CreatureFactory(world);
        TileFactory tileFactory=new TileFactory();
        ItemFactory itemFactory=new ItemFactory();
        world.setCreatureFactory(creatureFactory);
        world.setTileFactory(tileFactory);
        world.setItemFactory(itemFactory);
        floorBuilder=new FloorBuilder(creatureFactory,tileFactory,itemFactory);
        world.setFloors(floorBuilder.buildFloors());
        //System.out.println("floor num:"+world.floors().length);
        return world;
    }
}
