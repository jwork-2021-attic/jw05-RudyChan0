package com.world;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.world.creature.CreatureFactory;
import com.world.item.ItemFactory;
import com.world.tile.TileFactory;

public class FloorBuilder {

    public static final int FLOOR_NUM = 3;
    private CreatureFactory creatureFactory;
    private TileFactory tileFactory;
    private ItemFactory itemFactory;

    public FloorBuilder(CreatureFactory creatureFactory, TileFactory tileFactory, ItemFactory itemFactory) {
        this.creatureFactory = creatureFactory;
        this.tileFactory = tileFactory;
        this.itemFactory = itemFactory;
    }

    public Floor[] buildFloors() {
        System.out.println("path in FloorBuilder : " + System.getProperty("user.dir"));
        Floor[] floors = new Floor[FLOOR_NUM];
        for (int i = 1; i <= FLOOR_NUM; i++) {
            try {
                InputStream in = FloorBuilder.class.getClassLoader().getResourceAsStream("maps/floor" + i + ".json");
                String json = IOUtils.toString(in);
                Gson gson = new Gson();
                Floor floor = gson.fromJson(json, Floor.class);
                floors[i - 1] = floor.setCreatureFactory(creatureFactory).setTileFactory(tileFactory)
                        .setItemFactory(itemFactory).buildFromMap();
            } catch (Exception e) {
                System.out.println("floor " + i + " build failed...");
            }
        }
        return floors;
    }

    public static void main(String[] args) {
        // FloorBuilder floorBuilder=new FloorBuilder();
        // Floor floor=floorBuilder.buildFloors().get(0);
        // int x=0;
    }
}
