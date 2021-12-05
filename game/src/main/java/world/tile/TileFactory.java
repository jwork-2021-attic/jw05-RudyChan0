package world.tile;

import asciiPanel.AsciiPanel;
import java.awt.Color;

public class TileFactory {

    public Tile newFloor(){
        return new Tile(TileKind.Floor,(char)0 , AsciiPanel.coldGrey);
    }

    public Tile newWall(){
        return new Tile(TileKind.Wall,(char) 219, AsciiPanel.brickRed);
    }

    public Tile newUpstair(){
        return new Tile(TileKind.Upstair,(char)30,AsciiPanel.green);
    }

    public Tile newDownstair(){
        return new Tile(TileKind.Downstair,(char)31,AsciiPanel.green);
    }
}
