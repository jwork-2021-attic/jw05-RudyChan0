package com.world;

import java.io.Serializable;
import java.util.List;

import com.world.creature.Creature;
import com.world.tile.Tile;

public class Archive implements Serializable{

    


    public static Archive saveWorld(World world){
        Archive archive=new Archive();



        return archive;
    }

    class FloorData{
        private Tile[][] tiles;
        private List<Creature> creatures;

        public FloorData getFloorData(Floor floor){
            FloorData floorData=new FloorData();


            return floorData;
        }
    }
}