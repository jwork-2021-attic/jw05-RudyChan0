package world.item;

import asciiPanel.AsciiPanel;

public class ItemFactory {

    public Item newKeyYellow(){
        return new Item(ItemKind.KEY_YELLOW,(char)28,AsciiPanel.yellow);
    }

    public Item newKeyBlue(){
        return new Item(ItemKind.KEY_BLUE,(char)28,AsciiPanel.blue);
    }

    public Item newKeyRed(){
        return new Item(ItemKind.KEY_RED,(char)28,AsciiPanel.red);
    }

    public Item newHeart(){
        return new Item(ItemKind.HEART,(char)3,AsciiPanel.red);
    }

    public Item newAttack(){
        return new Item(ItemKind.ATTACK,(char)229,AsciiPanel.red);
    }

    public Item newDefence(){
        return new Item(ItemKind.DEFENCE,(char)229,AsciiPanel.blue);
    }
}
