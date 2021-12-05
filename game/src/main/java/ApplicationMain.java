
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import screen.Screen;
import screen.StartScreen;
import world.Floor;

public class ApplicationMain extends JFrame implements KeyListener {
    private AsciiPanel terminal;
    private Screen screen;

    private ScheduledExecutorService scheduledExecutorService;

    public ApplicationMain() {
        super();
        terminal = new AsciiPanel(35, 40, AsciiFont.CP437_16x16);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        controlThread();
        repaint();
    }

    public void controlThread(){
        scheduledExecutorService = Executors.newScheduledThreadPool(10);

        Runnable repaintRunnable = () -> {
            repaint();
        };
        Thread repaintThread = new Thread(repaintRunnable);

        Runnable monitorRunnable = () -> {
            repaint();
        };
        Thread monitorThread = new Thread(monitorRunnable);

        scheduledExecutorService.scheduleAtFixedRate(repaintThread,0,50, TimeUnit.MILLISECONDS);
    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        //repaint();
    }

    /**
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}
