package world.tile;

import asciiPanel.AsciiPanel;
import world.item.Item;

import java.awt.Color;
/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/**
 *
 * @author Aeranythe Echosong
 */
public class Tile {

    // FLOOR((char) 250, AsciiPanel.green),

    // FLOOR((char)0 , AsciiPanel.coldGrey),

    // WALL((char) 219, AsciiPanel.brickRed),

    // UPSTAIR((char)30,AsciiPanel.green),

    // DOWNSTAIR((char)31,AsciiPanel.green),


    // BOUNDS('x', AsciiPanel.magenta),

    // PASSED((char)250,AsciiPanel.green),

    // EXIT((char)31,AsciiPanel.green);
    TileKind kind;

    private char glyph;

    public char glyph() {
        return glyph;
    }

    private Color color;

    public Color color() {
        return color;
    }

    private Item item;

    public Tile setItem(Item item){
        this.item=item;
        item.setTile(this);
        return this;
    }

    public Item item(){
        return this.item;
    }

    public void removeItem(){
        this.item=null;
    }

    public boolean hasItem(){
        return item!=null;
    }

    // public boolean isDiggable() {
    //     return false;
    // }

    public  boolean canBlockSight(){
        return kind==TileKind.Wall;
    }

    public  boolean canGoThrough(){
        return kind==TileKind.Floor;
    }

    public  boolean isGround(){
        return kind==TileKind.Floor;
    }

    public  boolean isWall(){
        return kind==TileKind.Wall;
    }

    public  boolean isUpstair(){
        return kind==TileKind.Upstair;
    }

    public  boolean isDownstair(){
        return kind==TileKind.Downstair;
    }

    Tile(TileKind kind,char glyph, Color color) {
        this.kind=kind;
        this.glyph = glyph;
        this.color = color;
        this.item=null;
    }
}
