package world.creature;

import java.util.Random;

public class GuardAI extends MonsterAI{

    public GuardAI(Creature creature){
        super(creature);
    }
    
    @Override
    public void onUpdate(){
        int searchRes=searchPlayer();
        switch (searchRes) {
            case -1:
                break;
            case 1:
                creature.moveBy(0, 1);
                break;
            case 2:
                creature.moveBy(0, -1);
                break;
            case 3:
                creature.moveBy(-1, 0);
                break;
            case 4:
                creature.moveBy(1, 0);
                break;
        }
    }

}
