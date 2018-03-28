package cs3500.animator.view.visual;

import static util.MyUtil.checkNull;

import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A JPanel that draws out our animation.
 */
public class CanvasPane extends JPanel {

  private Map<String, IAnimatedShape> state;
  private Map<String, IShape> originalState;
  private int tickNum;
  private Dimension area;

  /**
   * Public constructor for the CanvasPane.
   */
  public CanvasPane() {
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    area = new Dimension(500, 500);
  }

  /**
   * Constructor that allows specification of layout.
   *
   * @param bl the layout to use.
   */
  public CanvasPane(BorderLayout bl) {
    tickNum = 0;
    area = new Dimension(500, 500);
    this.setLayout(bl);
  }

  /**
   * Sets the modelView to use and the state variable.
   *
   * @param mv the model view to use.
   */
  public void setModelView(IModelView mv) {
    checkNull(mv);
    IModelView myMv = mv;
    state = myMv.getFullState();
    originalState = duplicate(state);
    checkNull(state);
  }

  public void reset() {
    for(String key: state.keySet()) {
      state.get(key).setState(originalState.get(key));
    }
    this.repaint();
  }

  /**
   * Updates the tick number used to access the state.
   *
   * @param number the tick number.
   */
  public void setTickNumber(int number) {
    tickNum = number;
    System.out.println("Tick num: " + Integer.toString(tickNum));
  }

  /**
   * Increments the tick number used to access the state by a given number
   * of ticks.
   * @param numTicks the number of ticks to increment by.
   */
  public void incrementTickNumber(int numTicks) {
    tickNum += numTicks;
    System.out.println("Tick num: " + Integer.toString(tickNum));
  }

  /**
   * Returns the current tick number of the animation.
   * @return the current tick number of the animation.
   */
  public int getTickNumber() {
    return tickNum;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    RGBColor shapeColor;
    IShape shapeState;

    if (state != null) {
      for (IAnimatedShape shape : state.values()) {
        shapeColor = shape.getColor();
        shapeState = shape.stateAt(tickNum);

        g.setColor(new Color(shapeColor.getRed(), shapeColor.getGreen(), shapeColor.getBlue()));
        if (shape.getOpacity() == 1) {
          switch (shapeState.getType()) {
            case RECTANGLE:
              g.drawRect((int) shapeState.getPosition().getX(),
                  (int) shapeState.getPosition().getY(),
                  (int) ((WidthHeightDim) shapeState.getDimension()).getWidth(),
                  (int) ((WidthHeightDim) shapeState.getDimension()).getHeight());
              break;
            case OVAL:
              g.drawOval((int) shapeState.getPosition().getX(),
                  (int) shapeState.getPosition().getY(),
                  (int) ((WidthHeightDim) shapeState.getDimension()).getWidth(),
                  (int) ((WidthHeightDim) shapeState.getDimension()).getHeight());
              break;
            default:
              System.err.println("Unexpected Shape Type...");
          }
          if (updateArea(shapeState)) {
            setPreferredSize(area);
          }
        }
      }
    }
  }

  private boolean updateArea(IShape shape) {
    boolean changed = false;
    Position2D pos = shape.getPosition();
    WidthHeightDim dim = (WidthHeightDim) shape.getDimension();

    int thisWidth = (int) (pos.getX() + dim.getWidth());
    if (thisWidth > area.width) {
      area.width = (int) (thisWidth * 1.5);
      changed = true;
    }

    int thisHeight = (int) (pos.getY() + dim.getHeight());
    if (thisHeight > area.height) {
      area.height = (int) (thisHeight * 1.5);
      changed = true;
    }

    return changed;
  }

  private Map<String, IShape> duplicate(Map<String, IAnimatedShape> current) {
    Map<String, IShape> newMap = new HashMap<>();

    for(String key:current.keySet()) {
      newMap.put(key, ShapeFactory.getShape(current.get(key)));
    }

    return newMap;
  }
}
