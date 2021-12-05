package world.item;

import asciiPanel.AsciiPanel;
import world.creature.Creature;
import world.tile.Tile;

import java.awt.Color;

public class Item {

    private ItemKind kind;
    private char glyph;
    private Color color;
    private Tile tile;
    private final int healValue=20;
    private final int attackValue=2;
    private final int defenseValue=2;

    Item(ItemKind kind,char glyph, Color color) {
        this.kind=kind;
        this.glyph = glyph;
        this.color = color;
        this.tile=null;
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

    public ItemKind kind(){
        return kind;
    }

    public void accept(Creature player){
        if(this.kind==ItemKind.HEART){
            player.modifyHP(healValue);
        }
        else if(this.kind==ItemKind.ATTACK){
            player.modifyAttack(attackValue);
        }
        else if(this.kind==ItemKind.DEFENCE){
            player.modifyDefence(defenseValue);
        }
        else{
            player.getItem(this);
        }
        tile.removeItem();
    }
}
