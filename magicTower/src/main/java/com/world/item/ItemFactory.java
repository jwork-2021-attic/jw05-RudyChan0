package com.world.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.asciiPanel.AsciiPanel;

public class ItemFactory implements Serializable{

    public Item newKeyYellow(){
        return new Item(ItemKind.KEY_YELLOW,(char)28,AsciiPanel.brightYellow);
    }

    public Item newKeyBlue(){
        return new Item(ItemKind.KEY_BLUE,(char)28,AsciiPanel.blue);
    }

    public Item newKeyRed(){
        return new Item(ItemKind.KEY_RED,(char)28,AsciiPanel.red);
    }

    public Item newHeart(){
        return new Item(ItemKind.HEART,(char)3,AsciiPanel.pink);
    }

    public Item newAttack(){
        return new Item(ItemKind.ATTACK,(char)229,AsciiPanel.pink);
    }

    public Item newDefence(){
        return new Item(ItemKind.DEFENCE,(char)229,AsciiPanel.blue);
    }
}
