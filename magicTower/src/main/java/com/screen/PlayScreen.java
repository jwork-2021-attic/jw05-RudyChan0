package com.screen;

import com.world.World;
import com.world.WorldBuilder;
import com.world.creature.Creature;
import com.world.creature.CreatureFactory;
import com.world.item.Item;
import com.ApplicationMain;
import com.asciiPanel.AsciiPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlayScreen extends AbstractScreen {
    private World world;
    // private Floor[] floors;
    // private int floorNum;
    // private Floor floor;
    private Creature player;
    private CreatureFactory creatureFactory;
    private final int screenWidth = 24;
    private final int screenHeight = 24;
    private final int FLOOR_WIDTH = 13;
    private final int FLOOR_HEIGHT = 13;
    private List<String> messages;
    private List<String> oldMessages;

    private ScheduledExecutorService scheduledExecutorService;

    public PlayScreen() {
        createWorld();
        // this.floors=world.floors();
        // this.floorNum=0;
        // this.floor=floors[floorNum];
        this.creatureFactory = world.creatureFactory();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();
        this.player = creatureFactory.newPlayer(this.messages);
        this.scheduledExecutorService = Executors.newScheduledThreadPool(10);
        controlThreads();
        world.activateThreads();
    }

    public PlayScreen shutdwonThreads(){
        world.shutdwonThreads();
        return this;
    }

    public PlayScreen resumeThreads(){
        world.activateThreads();
        return this;
    }

    public void controlThreads() {

    }

    private void createWorld() {
        this.world = new WorldBuilder(this.screenHeight, this.screenWidth).build();
    }

    private void displayFloor(AsciiPanel terminal) {
        // Show terrain
        final int X_OFFSET = 2;
        for (int x = 0; x < FLOOR_WIDTH; x++) {
            for (int y = 0; y < FLOOR_HEIGHT; y++) {
                int wx = x;
                int wy = y;
                // judge if there is item in tile in class Floor
                terminal.write(world.glyph(wx, wy), x + X_OFFSET, y, world.color(wx, wy));
            }
        }

        // Show creatures
        for (Creature creature : world.creatures()) {
            if (creature.x() >= 0 && creature.x() < FLOOR_WIDTH && creature.y() >= 0 && creature.y() < FLOOR_HEIGHT) {
                terminal.write(creature.glyph(), creature.x() + X_OFFSET, creature.y(), creature.color());
            }
        }
        // Creatures can choose their next action now
        // world.update();
    }

    private void displayStatus(AsciiPanel terminal, Map<String, String> status) {
        final int offsetX = FLOOR_WIDTH + 4;
        int x = 0;
        int y = 0;
        for (String key : status.keySet()) {
            terminal.write(key + ": " + status.get(key), x + offsetX, y);
            y++;
            y++;
        }
        displayItems(terminal, y);
    }

    private void displayItems(AsciiPanel terminal, int offsetY) {
        int offsetX = FLOOR_WIDTH + 4;
        terminal.write("Item: ", offsetX, offsetY);
        for (Item item : player.items()) {
            terminal.write(item.glyph(), item.color());
        }
    }

    private void displayItemInfo(AsciiPanel terminal) {
        final int X_OFFSET = 31;
        int y = 0;
        terminal.write("Item ", X_OFFSET, y);
        y++;
        terminal.write((char) 3, X_OFFSET, y + 1, AsciiPanel.pink);
        terminal.write(" : hp+20", X_OFFSET + 1, y + 1);
        terminal.write((char) 229, X_OFFSET, y + 2, AsciiPanel.pink);
        terminal.write(" : attack+2", X_OFFSET + 1, y + 2);
        terminal.write((char) 229, X_OFFSET, y + 3, AsciiPanel.blue);
        terminal.write(" : defence+2", X_OFFSET + 1, y + 3);
        terminal.write((char) 28, X_OFFSET, y + 4, AsciiPanel.brightYellow);
        terminal.write((char) 28, X_OFFSET + 1, y + 4, AsciiPanel.blue);
        terminal.write((char) 28, X_OFFSET + 2, y + 4, AsciiPanel.red);
        terminal.write(" : key", X_OFFSET + 3, y + 4);
    }

    private void displayMonsterInfo(AsciiPanel terminal) {
        int top = FLOOR_HEIGHT + 1;
        terminal.write("Monster", 0, top);
        terminal.write("HP", 8, top);
        terminal.write("Attack", 12, top);
        terminal.write("Defence", 20, top);
        terminal.write("Note", 29, top);
        Creature monster = creatureFactory.newMonster();
        Creature guard = creatureFactory.newGuard();
        Creature detector = creatureFactory.newDetector();
        top++;
        displayCreatureInfo(terminal, monster, top + 1);
        displayCreatureInfo(terminal, guard, top + 2);
        displayCreatureInfo(terminal, detector, top + 3);
    }

    private void displayCreatureInfo(AsciiPanel terminal, Creature creature, int top) {
        Map<String, String> status = creature.status();
        terminal.write(status.get("glyph"), 2, top);
        terminal.write(status.get("HP"), 8, top);
        terminal.write(status.get("Attack"), 13, top);
        terminal.write(status.get("Defence"), 21, top);
        terminal.write(status.get("Note"), 28, top);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayFloor(terminal);
        displayStatus(terminal, player.status());
        displayItemInfo(terminal);
        displayMonsterInfo(terminal);
        // Player
        // terminal.write(player.glyph(), player.x() - getScrollX(), player.y() -
        // getScrollY(), player.color());
        // Stats
        // String stats = String.format("%3d/%3d hp", player.hp(), player.maxHP());
        // terminal.write(stats, 1, 23);
        // Messages
        displayMessages(terminal, this.messages);
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = this.FLOOR_HEIGHT + 9;
        terminal.write("Log:", 0, top);
        for (int i = 0; i < Math.min(oldMessages.size(), 5); i++) {
            terminal.write(oldMessages.get(oldMessages.size() - 1 - i), 1, top + i + 1);
        }
        terminal.write("...", 1, top + Math.min(oldMessages.size(), 5) + 1);
        this.oldMessages.addAll(messages);
        messages.clear();
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                player.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
                player.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                player.moveBy(0, 1);
                break;
            case KeyEvent.VK_ESCAPE:
                return new OptionScreen(this.shutdwonThreads());
        }
        // if(world.tile(player.x(), player.y())==Tile.EXIT){
        // return new WinScreen();
        // }
        // world.pass(player.x(), player.y(),Tile.PASSED);
        // world.update();
        // world.playerUpdate();
        return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x() - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y() - screenHeight / 2, world.height() - screenHeight));
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public Screen checkPlayer() {
        if (player.isAlive())
            return this;
        else
            return new DeadScreen();
    }

}
