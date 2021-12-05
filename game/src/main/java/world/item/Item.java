package world.item;

import asciiPanel.AsciiPanel;
import world.creature.Creature;
import world.tile.Tile;

import java.awt.Color;

public enum Item {

    KEY_YELLOW((char)170,AsciiPanel.yellow),

    KEY_BLUE((char)170,AsciiPanel.blue),

    KEY_RED((char)170,AsciiPanel.red),

    HEART((char)3,AsciiPanel.red),

    ATTACK((char)9,AsciiPanel.red),

    DEFENCE((char)9,AsciiPanel.blue);

    private char glyph;
    private Color color;
    private Tile tile;
    private final int healValue=20;
    private final int attackValue=2;
    private final int defenseValue=2;

    Item(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    public void setTile(Tile tile){
        this.tile=tile;
    }

    public char glyph() {
        return glyph;
    }

    public Color color() {
        return color;
    }

    public void accept(Creature player){
        if(this==Item.HEART){
            player.modifyHP(healValue);
        }
        else if(this==Item.ATTACK){
            player.modifyAttack(attackValue);
        }
        else if(this==Item.DEFENCE){
            player.modifyDefence(defenseValue);
        }
        else{
            player.getItem(this);
        }
        tile.removeItem();
    }
}
