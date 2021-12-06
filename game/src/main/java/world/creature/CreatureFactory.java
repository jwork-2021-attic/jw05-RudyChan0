package world.creature;


import java.security.Guard;
import java.util.List;

import world.World;
import world.Floor;
import asciiPanel.AsciiPanel;

public class CreatureFactory {
    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages){
        Creature player = new Creature(this.world, (char)2, AsciiPanel.brightWhite, 200, 20, 5, 5);
        world.setPlayer(player);
        world.addPlayerToBegin(player);
        new PlayerAI(player,messages);
        new CreatureThread(player,"player");
        return player;
    }

    public Creature newMonster(){
        Creature monster = new Creature(this.world, (char)64, AsciiPanel.yellow, 20, 10, 5, 2);
        new MonsterAI(monster);
        new CreatureThread(monster,"monster");
        return monster;
    }

    public Creature newDetector(){
        Creature detector = new Creature(this.world, (char)143, AsciiPanel.green, 10, 20, 5, 4);
        new GuardAI(detector);
        new CreatureThread(detector,"detetor");
        return detector;
    }

    public Creature newGuard(){
        Creature guard = new Creature(this.world, (char)234, AsciiPanel.cyan, 30, 15, 10, 1);
        new GuardAI(guard);
        new CreatureThread(guard,"guard");
        return guard;
    }
}
