package screen;

import world.*;
import world.creature.Creature;
import world.creature.CreatureFactory;
import asciiPanel.AsciiPanel;
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
    //private Floor[] floors;
    //private int floorNum;
    //private Floor floor;
    private Creature player;
    private CreatureFactory creatureFactory;
    private final int screenWidth=20;
    private final int screenHeight=20;
    private final int FLOOR_WIDTH=13;
    private final int FLOOR_HEIGHT=13;
    private List<String> messages;
    private List<String> oldMessages;

    private ScheduledExecutorService scheduledExecutorService;

    public PlayScreen() {
        createWorld();
        //this.floors=world.floors();
        //this.floorNum=0;
        //this.floor=floors[floorNum];
        this.creatureFactory=world.creatureFactory();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();
        this.player=creatureFactory.newPlayer(this.messages);
        this.scheduledExecutorService=Executors.newScheduledThreadPool(10);
        controlThreads();
        world.activateThreads();
    }

    public void controlThreads(){

    }

    private void createWorld(){
        this.world=new WorldBuilder(this.screenHeight, this.screenWidth).build();
    }

    private void displayFloor(AsciiPanel terminal) {
        // Show terrain
        for (int x = 0; x < FLOOR_WIDTH; x++) {
            for (int y = 0; y < FLOOR_HEIGHT; y++) {
                int wx = x;
                int wy = y;
                //judge if there is item in tile in class Floor
                terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
            }
        }

        // Show creatures
        for (Creature creature : world.creatures()) {
            if (creature.x() >= 0 && creature.x() < FLOOR_WIDTH && creature.y() >= 0&& creature.y() < FLOOR_HEIGHT) {
                    terminal.write(creature.glyph(), creature.x(), creature.y(), creature.color());
            }
        }
        // Creatures can choose their next action now
        //world.update();
    }

    private void displayStatus(AsciiPanel terminal,Map<String,String>status){
        final int offsetX=13;
        int x=1;
        int y=0;
        for(String key:status.keySet()){
            terminal.write(key+": "+status.get(key),x+offsetX,y);
            y++;
        }
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayFloor(terminal);
        displayStatus(terminal, player.status());
        // Player
        terminal.write(player.glyph(), player.x() - getScrollX(), player.y() - getScrollY(), player.color());
        // Stats
        // String stats = String.format("%3d/%3d hp", player.hp(), player.maxHP());
        // terminal.write(stats, 1, 23);
        // Messages
        displayMessages(terminal, this.messages);
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = this.screenHeight - messages.size();
        for (int i = 0; i < messages.size(); i++) {
            terminal.write(messages.get(i), 1, top + i + 1);
        }
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
        }
        // if(world.tile(player.x(), player.y())==Tile.EXIT){
        //     return new WinScreen();
        // }
        //world.pass(player.x(), player.y(),Tile.PASSED);
        //world.update();
        //world.playerUpdate();
        return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x() - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y() - screenHeight / 2, world.height() - screenHeight));
    }

    @Override
    public void update(){
        world.update();
    }

    
}
