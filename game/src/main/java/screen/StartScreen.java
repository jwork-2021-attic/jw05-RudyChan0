package screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen extends AbstractScreen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("|  \\/  |           (_)       |__   __|", 0, 1);
        terminal.write("| \\  / | __ _  __ _ _  ___      | |", 0, 2);
        terminal.write("| |\\/| |/ _` |/ _` | |/ __|     | |/ _ \\ \\ /\\ / / _ \\ '__|", 0, 3);
        terminal.write("| |  | | (_| | (_| | | (__      | | (_) \\ V  V /  __/ |", 0, 4);
        terminal.write("|_|  |_|\\__,_|\\__, |_|\\___|     |_|\\___/ \\_/\\_/ \\___|_|", 0, 5);
        terminal.write("               __/ |", 0, 6);
        terminal.write("              |___/ ", 0, 7);
        terminal.write("Press ENTER to continue...", 0, 9);
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
