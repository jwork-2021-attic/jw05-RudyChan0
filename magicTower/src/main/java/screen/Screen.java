package screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 *
 * @author Aeranythe Echosong
 */
public interface Screen {

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);

    public void update();

    public Screen checkPlayer();
}
