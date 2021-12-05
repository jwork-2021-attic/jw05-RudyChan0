package world.creature;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Cell;
import world.item.Item;
import world.tile.Tile;

public class PlayerAI extends CreatureAI{
    private List<String> messages;

    public PlayerAI(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
    }  

    @Override
    public void onEnter(int x,int y,Tile tile){
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
            if(tile.hasItem()){
                creature.visitItem(tile.item());
            }
        }
        else if(tile.isUpstair()){
            creature.goUpstair();
        }
        else if(tile.isDownstair()){
            creature.goDownstair();
        }
    }

    @Override
    public void onNotify(String message) {
        this.messages.add(message);
    }

    @Override
    public Map<String,String> status(){
        Map<String,String> status=new LinkedHashMap<>();
        status.put("Player", creature.glyph()+"");
        status.put("HP", creature.hp()+"");
        status.put("Attack", creature.attackValue()+"");
        status.put("Defence", creature.defenseValue()+"");
        // String items="";
        // for(Item item: creature.items()){
        //     items+=(item.glyph()+" ");
        // }
        // status.put("Item", items);

        return status;
    }

}
