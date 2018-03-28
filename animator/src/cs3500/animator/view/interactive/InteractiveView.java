package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.view.visual.CanvasPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;

/**
 * Class representing the visual view of an animator.
 */
public class InteractiveView implements IInteractive {

  private int speed; // tics/second

  private final CanvasPane canvas;

  private final ControlPane cp;

  private Timer timer;

  private int secondsCount;

  private boolean running;


  /**
   * The visual view implementation.
   */
  public InteractiveView() {
    JFrame frame;
    JScrollPane scroller;
    int panelWidth = 500;
    int panelHeight = 500;
    running = false;

    // Overall frame
    frame = new JFrame("Interactive Animator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the Canvas
    canvas = new CanvasPane();
    canvas.setBackground(Color.WHITE);

    // Create the Control Panel
    cp = new ControlPane();

    // Create canvas
    scroller = new JScrollPane(canvas,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroller.setPreferredSize(new Dimension(panelWidth, panelHeight));
    scroller.setOpaque(true);

    JPanel content = new JPanel(new BorderLayout());
    content.add(scroller, BorderLayout.CENTER);
    content.add(cp, BorderLayout.EAST);

    frame.setContentPane(content);
    frame.setPreferredSize(new Dimension(panelWidth, panelHeight));
    frame.pack();
    frame.setVisible(true);

    secondsCount = 0;

    timer = createTimer();

  }

  @Override
  public void setListeners(ActionListener buttons, ChangeListener speed) {
    cp.setListeners(buttons, speed);
  }

  @Override
  public void show(IModelView state, int tempo) throws IOException {
    System.out.println("Entered show...");
    checkNull(state);
    speed = tempo;
    running = true;
    canvas.setModelView(state);
    System.out.println("Set Model...");
    timer.start();
    System.out.println("Started Timer...");
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
    cp.setSpeed(speed);
    System.out.println("Speed set to " + Integer.toString(speed));
  }

  @Override
  public void start() {
    //TODO
    running = true;
    System.out.println("Started");

  }

  @Override
  public void pause() {
    running = false;
    System.out.println("Paused");

    //TODO
  }

  @Override
  public void reset() {
    running = false;
    secondsCount = 0;
    //TODO
    System.out.println("Reset");
  }

  @Override
  public void resume() {
    running = true;
    System.out.println("Resume");

    //TODO
  }

  @Override
  public void export() {
    //TODO
    System.out.println("Export");
    System.out.println("File name: " + cp.getExportFilename());
  }

  @Override
  public void setLoop(boolean loop) {
    //TODO
    System.out.println("Set Loop");
  }

  private Timer createTimer() {
    return new Timer(1000,
        (ActionEvent e) -> {
          if(running && speed > 0) {
            canvas.incrementTickNumber(speed);
            canvas.revalidate();
            canvas.repaint();
            secondsCount++;
          }
        });
  }
}

