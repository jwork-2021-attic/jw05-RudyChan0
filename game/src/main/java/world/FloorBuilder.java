package world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import world.creature.CreatureFactory;
import world.tile.TileFactory;

public class FloorBuilder {

    public static final int FLOOR_NUM = 2;
    private CreatureFactory creatureFactory;
    private TileFactory tileFactory;

    public FloorBuilder(CreatureFactory creatureFactory,TileFactory tileFactory){
        this.creatureFactory=creatureFactory;
        this.tileFactory=tileFactory;
    }

    public Floor[] buildFloors() {
        Floor[] floors = new Floor[FLOOR_NUM];
        for (int i = 1; i <= FLOOR_NUM; i++) {
            File file = new File("src/main/java/maps/floor" + i + ".json");
            String json = "";
            try {
                json = FileUtils.readFileToString(file);
            } catch (Exception e) {
            }
            Gson gson = new Gson();
            Floor floor = gson.fromJson(json, Floor.class);
            floors[i-1]=floor.setCreatureFactory(creatureFactory).setTileFactory(tileFactory).buildFromMap();
        }
        return floors;
    }


    public static void main(String[] args) {
        //FloorBuilder floorBuilder=new FloorBuilder();
        //Floor floor=floorBuilder.buildFloors().get(0);
        //int x=0;
    }
}
