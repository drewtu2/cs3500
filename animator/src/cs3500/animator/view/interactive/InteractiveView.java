package cs3500.animator.view.interactive;

import static util.MyUtil.checkNull;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.animator.view.visual.CanvasPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
  private int drawRate; // fps
  private int drawCount;

  private final CanvasPane canvas;

  private final ControlPane cp;

  private Timer timer;

  private boolean running;

  private boolean loop;

  private IModelView myMV;
  private IModelView originalMV;

  private Map<String, Boolean> shapeEnabled;

  /**
   * The visual view implementation.
   */
  public InteractiveView() {

    shapeEnabled = new HashMap<>();

    JFrame frame;
    JScrollPane scroller;
    int panelWidth = 500;
    int panelHeight = 500;
    running = false;
    loop = false;

    drawRate = 50;
    drawCount = 0;

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
    myMV = state;
    originalMV = new AnimatorModel((AnimatorModel) state);
    canvas.setModelView(myMV);
    canvas.setEnabledMap(shapeEnabled);
    cp.setModelView(myMV);
    configureVisibilityMap(myMV);
    System.out.println("Set Model...");
    timer.start();
    System.out.println("Started Timer...");
  }

  @Override
  public void setSpeed(int speed) {
    System.out.println("Speed set to " + Integer.toString(speed));
    this.speed = speed;
    // Update the control panel view of the speed as well if necessary
    cp.setSpeed(speed);
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void start() {
    System.out.println("Started");
    running = true;
  }

  @Override
  public void pause() {
    System.out.println("Paused");
    running = false;
  }

  @Override
  public void reset() {
    System.out.println("Reset");
    canvas.setTickNumber(0);
    canvas.reset();
  }

  @Override
  public void resume() {
    running = true;
    System.out.println("Resume");
  }

  @Override
  public void export() {
    try {
      IView exToSVG = ViewFactory.getView("svg", cp.getExportFilename(), loop);
      IModelView exportModel = new AnimatorModel((AnimatorModel) originalMV);
      exportModel.addMap(exportModel.getFullState(), shapeEnabled);
      exToSVG.show(exportModel, speed);
      System.out.println("Export");
      System.out.println("File name: " + cp.getExportFilename());
    } catch (IOException e) {
      // stuff
    }
  }

  @Override
  public void setLoop(boolean loop) {
    System.out.println("Set Loop");
    this.loop = loop;
  }

  @Override
  public void toggleShape(String name) {
    checkNull(name);

    if (shapeEnabled.containsKey(name)) {
      shapeEnabled.put(name, !shapeEnabled.get(name));
      System.out.println(name + " set to " + shapeEnabled.get(name).toString());
    } else {
      throw new IllegalArgumentException("invalid shape name");
    }
  }

  /**
   * Creates the timer driving the time component. Fires every 1 second.
   *
   * @return the timer
   */
  private Timer createTimer() {
    return new Timer(drawRate,
        (ActionEvent e) -> {
          if (running && speed > 0) {
            canvas.incrementTickNumber((drawRate * speed) / 1000.0);
            if (loop && canvas.getTickNumber() > myMV.getEndTick()) {
              System.out.println("Looping!");
              System.out
                  .println("Current Tick Number: " + Integer.toString(canvas.getTickNumber()));
              System.out.println("End Tick Number: " + Integer.toString(myMV.getEndTick()));
              //int temp = canvas.getTickNumber(); // need value to persist through reset
              this.reset();
              //canvas.setTickNumber(temp - myMV.getEndTick());
            }
            canvas.revalidate();
            canvas.repaint();
            drawCount++;
          }
        });
  }

  /**
   * Initialize all values as being true.
   *
   * @param mv the model view
   */
  private void configureVisibilityMap(IModelView mv) {
    Map<String, IAnimatedShape> state = mv.getFullState();

    for (String key : state.keySet()) {
      shapeEnabled.put(key, true);
    }

  }
}

