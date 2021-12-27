package screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;


public class AbstractScreen implements Screen {
    public void displayOutput(AsciiPanel terminal){

    }

    public Screen respondToUserInput(KeyEvent key){
        return this;
    }

    public void update(){
        
    }

    public Screen checkPlayer(){
        return this;
    }
}
