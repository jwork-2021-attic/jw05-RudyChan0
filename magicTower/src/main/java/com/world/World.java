package com.world;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javafx.util.Pair;
import com.world.creature.Creature;
import com.world.creature.CreatureFactory;
import com.world.item.Item;
import com.world.item.ItemFactory;
import com.world.tile.Tile;
import com.world.tile.TileFactory;

public class World implements Serializable {
    private Floor[] floors;
    private Floor currentFloor;
    private int floorNum;
    private Creature player;
    private int width;
    private int height;
    private CreatureFactory creatureFactory;
    private TileFactory tileFactory;
    private ItemFactory itemFactory;

    public World(int width,int height){
        this.width=width;
        this.height=height;
    }

    //operate floor
    public void activateThreads(){
        currentFloor.activateThreads();
    }

    public void shutdwonThreads(){
        currentFloor.shutdwonThreads();
    }

    public void update(){
        currentFloor.update();
    }

    public void goUpstair(){
        currentFloor.shutdwonThreads();
        currentFloor.remove(player);
        floorNum++;
        currentFloor=floors[floorNum];
        Pair<Integer,Integer> downstairCoords=currentFloor.downstairCoords();
        currentFloor.addCreature(player, downstairCoords.getKey(), downstairCoords.getValue());
        currentFloor.activateThreads();
    }

    public void goDownstair(){
        currentFloor.shutdwonThreads();
        currentFloor.remove(player);
        floorNum--;
        currentFloor=floors[floorNum];
        Pair<Integer,Integer> upstairCoords=currentFloor.upstairCoords();
        currentFloor.addCreature(player, upstairCoords.getKey(), upstairCoords.getValue());
        currentFloor.activateThreads();
    }

    public List<Creature> creatures() {
        return currentFloor.creatures();
    }

    public char glyph(int x, int y) {
        return currentFloor.glyph(x, y);
    }

    public Color color(int x, int y) {
        return currentFloor.color(x, y);
    }

    public Tile tile(int x, int y) {
        return currentFloor.tile(x, y);
    }


    public void setPlayer(Creature player){
        this.player=player;
    }

    public void addPlayerToBegin(Creature player){
        // System.out.println("in addPlayer: "+floors.length);
        // System.out.println("in addPlayer: floor0- "+floors[0]);
        floors[0].addCreature(player, 6, 11);
    }

    public void setFloors(Floor[] floors){
        this.floors=floors;
        this.floorNum=0;
        this.currentFloor=floors[this.floorNum];
    }

    public int floorNum(){
        return floorNum;
    }

    public Floor currenFloor(){
        return currentFloor;
    }

    public Floor[] floors(){
        return floors;
    }

    public void setCreatureFactory(CreatureFactory creatureFactory){
        this.creatureFactory=creatureFactory;
    }

    public void setTileFactory(TileFactory tileFactory){
        this.tileFactory=tileFactory;
    }

    public void setItemFactory(ItemFactory itemFactory){
        this.itemFactory=itemFactory;
    }

    public CreatureFactory creatureFactory(){
        return this.creatureFactory;
    }

    public TileFactory tileFactory(){
        return this.tileFactory;
    }

    public ItemFactory itemFactory(){
        return itemFactory;
    }

    public Creature player(){
        return player;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

}