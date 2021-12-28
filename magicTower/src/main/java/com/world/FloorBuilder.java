package com.world;

import java.io.InputStream;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

public class FloorBuilder {

    public static final int FLOOR_NUM = 3;

    public static Floor[] buildFloors(World world) {
        //System.out.println("path in FloorBuilder : " + System.getProperty("user.dir"));
        Floor[] floors = new Floor[FLOOR_NUM];
        for (int i = 1; i <= FLOOR_NUM; i++) {
            try {
                InputStream in = FloorBuilder.class.getClassLoader().getResourceAsStream("maps/floor" + i + ".json");
                String json = IOUtils.toString(in);
                Gson gson = new Gson();
                Floor floor = gson.fromJson(json, Floor.class);
                floors[i - 1] = floor.setWorld(world).buildFromMap();
            } catch (Exception e) {
                System.out.println("floor " + i + " build failed...");
            }
        }
        return floors;
    }
}
