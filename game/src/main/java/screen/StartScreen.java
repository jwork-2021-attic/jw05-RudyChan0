package screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen extends AbstractScreen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("Made in Rudy.", 0, 0);
        terminal.write("Press ENTER to continue...", 0, 1);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                return new PlayScreen();
            default:
                return this;
        }
    }
}
