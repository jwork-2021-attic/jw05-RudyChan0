package world.tile;

import asciiPanel.AsciiPanel;
import java.awt.Color;

public class TileFactory {

    public Tile newTile(TileKind kind){
        if(kind==TileKind.FLOOR)return newFloor();
        else if(kind==TileKind.WALL)return newWall();
        else if(kind==TileKind.UPSTAIR)return newUpstair();
        else if(kind==TileKind.DOWNSTAIR)return newDownstair();
        else if(kind==TileKind.DOOR_YELLOW)return newDoorYellow();
        else if(kind==TileKind.DOOR_BLUE)return newDoorBlue();
        else if(kind==TileKind.DOOR_RED)return newDoorRed();
        else return null;
    }

    public Tile newFloor(){
        return new Tile(TileKind.FLOOR,(char)0 , AsciiPanel.coldGrey);
    }

    public Tile newWall(){
        return new Tile(TileKind.WALL,(char) 178, AsciiPanel.wallRed);
    }

    public Tile newUpstair(){
        return new Tile(TileKind.UPSTAIR,(char)24,AsciiPanel.brightGreen);
    }

    public Tile newDownstair(){
        return new Tile(TileKind.DOWNSTAIR,(char)25,AsciiPanel.brightGreen);
    }

    public Tile newDoorYellow(){
        return new Tile(TileKind.DOOR_YELLOW,(char)8,AsciiPanel.brightYellow);
    }

    public Tile newDoorBlue(){
        return new Tile(TileKind.DOOR_BLUE,(char)8,AsciiPanel.blue);
    }

    public Tile newDoorRed(){
        return new Tile(TileKind.DOOR_RED,(char)8,AsciiPanel.red);
    }
}
