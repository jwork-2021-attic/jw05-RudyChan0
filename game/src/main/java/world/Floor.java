package world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.omg.PortableInterceptor.INACTIVE;

import javafx.util.Pair;

import java.awt.Color;

import world.creature.Creature;
import world.creature.CreatureFactory;
import world.item.Item;
import world.item.ItemFactory;
import world.tile.Tile;
import world.tile.TileFactory;
import world.tile.TileKind;

public class Floor {
    private String name;
    private String[][] map;
    private int width;
    private int height;
    private Tile[][] tiles;
    private List<Creature> creatures;
    private CreatureFactory creatureFactory;
    private TileFactory tileFactory;
    private ItemFactory itemFactory;

    private Pair<Integer, Integer> upstairCoords;
    private Pair<Integer, Integer> downstairCoords;

    private ExecutorService executorService;
    private final int UPDATE_TIME = 1; // TimeUnit:second
    // private Floor upFloor;
    // private Floor downFloor;

    public void update() {
        creatures.forEach(Creature::update);
    }

    public void activateThreads() {
        executorService = Executors.newCachedThreadPool();
        creatures.forEach(creature -> {
            executorService.submit(creature.thread());
        });
    }

    public void shutdwonThreads() {
        executorService.shutdownNow();
    }

    public void addCreature(Creature creature, int x, int y) {
        creatures.add(creature);
        creature.setFloor(this);
        creature.setX(x);
        creature.setY(y);
    }

    public void remove(Creature creature) {
        creatures.remove(creature);
    }

    public void changeTile(int x,int y,TileKind kind){
        tiles[x][y]=tileFactory.newTile(kind);
    }

    public Floor buildFromMap() {
        height = map.length;
        width = map[0].length;
        tiles = new Tile[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                switch (map[h][w]) {
                    case "0":
                        tiles[w][h] = tileFactory.newFloor();
                        break;
                    case "1":
                        tiles[w][h] = tileFactory.newWall();
                        break;
                    case "upStair":
                        tiles[w][h] = tileFactory.newUpstair();
                        upstairCoords = new Pair<>(w, h);
                        break;
                    case "downStair":
                        tiles[w][h] = tileFactory.newDownstair();
                        downstairCoords = new Pair<>(w, h);
                        break;
                    case "door_yellow":
                        tiles[w][h] = tileFactory.newDoorYellow();
                        break;
                    case "door_blue":
                        tiles[w][h] = tileFactory.newDoorBlue();
                        break;
                    case "door_red":
                        tiles[w][h] = tileFactory.newDoorRed();
                        break;
                    case "monster":
                        tiles[w][h] = tileFactory.newFloor();
                        addCreature(creatureFactory.newMonster(), w, h);
                        break;
                    case "detector":
                        tiles[w][h] = tileFactory.newFloor();
                        addCreature(creatureFactory.newDetector(), w, h);
                        break;
                    case "guard":
                        tiles[w][h] = tileFactory.newFloor();
                        addCreature(creatureFactory.newGuard(), w, h);
                        break;
                    case "heart":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newHeart());
                        break;
                    case "attack":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newAttack());
                        break;
                    case "defence":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newDefence());
                        break;
                    case "key_yellow":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newKeyYellow());
                        break;
                    case "key_blue":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newKeyBlue());
                        break;
                    case "key_red":
                        tiles[w][h] = tileFactory.newFloor().setItem(itemFactory.newKeyRed());
                        break;

                }
            }
        }

        return this;
    }

    public Creature creature(int x, int y) {
        for (Creature creature : creatures) {
            if (creature.x() == x && creature.y() == y)
                return creature;
        }
        return null;
    }

    public List<Creature> creatures() {
        return this.creatures;
    }

    public char glyph(int x, int y) {
        if (tiles[x][y].hasItem())
            return tiles[x][y].item().glyph();
        else
            return tiles[x][y].glyph();
    }

    public Color color(int x, int y) {
        if (tiles[x][y].hasItem())
            return tiles[x][y].item().color();
        else
            return tiles[x][y].color();
    }

    public Tile tile(int x, int y) {
        return tiles[x][y];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Floor setMap(String[][] map) {
        this.map = map;
        return this;
    }

    public String[][] map() {
        return map;
    }

    public Pair<Integer, Integer> upstairCoords() {
        return upstairCoords;
    }

    public Pair<Integer, Integer> downstairCoords() {
        return downstairCoords;
    }

    public Floor setCreatureFactory(CreatureFactory creatureFactory) {
        this.creatureFactory = creatureFactory;
        return this;
    }

    public Floor setTileFactory(TileFactory tileFactory) {
        this.tileFactory = tileFactory;
        return this;
    }

    public Floor setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
        return this;
    }

    // public void setUpFloor(Floor floor){
    // this.upFloor=floor;
    // }

    // public void setDownFloor(Floor floor){
    // this.downFloor=floor;
    // }

    public Floor() {
        map = null;
        tiles = null;
        creatures = new ArrayList<>();

    }

    public static void main(String[] args) {
        // Floor floor = new Floor(1);
        int x = 0;
    }
}
