package cs3500.animator.view.visual;

import static util.IUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.view.IView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

/**
 * Class representing the visual view of an animator. Displays the given model view animation on a
 * canvas pane at the specified speed.
 */
public class VisualView implements IView {

  private int speed; // tics/second

  private final CanvasPane canvas;

  private Timer timer;
  private Timer timer2;

  private int drawRate;

  private int drawCount;

  /**
   * The visual view implementation.
   */
  public VisualView() {
    JFrame frame;
    JScrollPane scroller;

    int panelWidth = 500;
    int panelHeight = 500;

    drawRate = 50;

    // Overall frame
    frame = new JFrame("Simple Animator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the Canvas
    canvas = new CanvasPane();
    canvas.setBackground(Color.WHITE);

    // Create Content Pane
    scroller = new JScrollPane(canvas,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroller.setPreferredSize(new Dimension(panelWidth, panelHeight));
    scroller.setOpaque(true);

    frame.setContentPane(scroller);
    frame.setPreferredSize(new Dimension(panelWidth, panelHeight));
    frame.pack();
    frame.setVisible(true);

    drawCount = 0;

    timer = new Timer(drawRate,
        (ActionEvent e) -> {
          //System.out.println("Timer Event fired");
          canvas.incrementTickNumber((drawRate * speed) / 1000.0);
          //canvas.setTickNumber((drawCount * drawRate /1000.0) * speed);
          canvas.revalidate();
          canvas.repaint();
          drawCount++;
        });
    //timer2 = new Timer(1000, (ActionEvent e) -> System.out.println("1second"));

  }

  @Override
  public void show(IModelView state, int tempo) throws IOException {
    System.out.println("Entered show...");
    checkNull(state);
    speed = tempo;
    canvas.setModelView(state);
    System.out.println("Set Model...");
    timer.start();
    //timer2.start();
    System.out.println("Started Timer...");
  }

}

