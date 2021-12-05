package world;

import world.creature.CreatureFactory;
import world.item.ItemFactory;
import world.tile.Tile;
import world.tile.TileFactory;

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
        return world;
    }
}
