package cs3500.animator.view.visual;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.view.IView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.Timer;

/**
 * Class representing the visual view of an animator.
 */
public class VisualView extends JFrame implements IView {

  private int speed; // tics/second

  private final CanvasPanel canvas;

  private Timer timer;

  private int secondsCount;

  /**
   * The visual view implementation.
   */
  public VisualView() {
    super();

    JScrollBar hbar;
    JScrollBar vbar;
    int frameWidth;
    int frameHeight;

    frameHeight = 500;
    frameWidth = 500;

    this.setTitle("Simple Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(frameWidth, frameHeight);

    //canvas = new JPanel(new BorderLayout());
    canvas = new CanvasPanel(new BorderLayout());

    hbar = new JScrollBar(JScrollBar.HORIZONTAL);
    vbar = new JScrollBar(JScrollBar.VERTICAL);

    canvas.add(hbar, BorderLayout.SOUTH);
    canvas.add(vbar, BorderLayout.EAST);

    this.setContentPane(canvas);

    //myFrame.pack();
    this.setVisible(true);

    secondsCount = 0;
    timer = new Timer(1000,
        (ActionEvent e) -> {
          System.out.println("Timer Event fired");
          canvas.setTickNumber(secondsCount * speed);
          secondsCount++;
          repaint();
        });

  }

  @Override
  public void show(IModelView state, int tempo) throws IOException {
    System.out.println("Entered show...");
    checkNull(state);
    speed = tempo;
    canvas.setModelView(state);
    System.out.println("Set Model...");
    timer.start();
    System.out.println("Started Timer...");
  }
}

