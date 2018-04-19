package cs3500.animator.view.visual;

import static util.IUtil.checkNull;
import static util.IUtil.duplicateMap;

import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IPosition;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.dimension.IDimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A JPanel that draws out our animation.
 */
public class CanvasPane extends JPanel {

  private Map<String, IAnimatedShape> state;
  private Map<String, Boolean> enabledMap;
  private Map<String, IAnimatedShape> originalState;
  private double tickNum;
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
    originalState = duplicateMap(state);
    checkNull(state);
  }

  /**
   * Sets the isEnabledMap.
   *
   * @param map the map.
   */
  public void setEnabledMap(Map<String, Boolean> map) {
    checkNull(map);
    enabledMap = map;
  }

  /**
   * Reset the state of the canvas. Animation essentially starts over.
   */
  public void reset() {

    for (String key : state.keySet()) {
      state.get(key).setState(originalState.get(key));
    }
    tickNum = 0;
    this.repaint();
  }

  /**
   * Updates the tick number used to access the state.
   *
   * @param number the tick number.
   */
  public void setTickNumber(double number) {
    tickNum = number;
    System.out.println("Tick num: " + Integer.toString((int) tickNum));
  }

  /**
   * Increments the tick number used to access the state by a given number of ticks.
   *
   * @param numTicks the number of ticks to increment by.
   */
  public void incrementTickNumber(double numTicks) {
    tickNum += numTicks;
    System.out.println("Tick num: " + Integer.toString((int) tickNum));
  }

  /**
   * Returns the current tick number of the animation.
   *
   * @return the current tick number of the animation.
   */
  public int getTickNumber() {
    return (int) tickNum;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    IRGBColor shapeColor;
    IShape shapeState;

    List<IAnimatedShape> shapes = new ArrayList<>(state.values());
    Collections.sort(shapes);

    if (state != null) {
      for (IAnimatedShape shape : shapes) {
        shapeState = shape.stateAt((int) tickNum);
        shapeColor = shapeState.getColor();

        g.setColor(new Color(shapeColor.getRed(), shapeColor.getGreen(), shapeColor.getBlue()));
        if (shouldDisplay(shape)) {
          switch (shapeState.getType()) {
            case RECTANGLE:
              drawRectangle(g, shapeState);
              break;
            case OVAL:
              drawOval(g, shapeState);
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


  /**
   * Returns true if the given shape should be displayed. Opacity cannot be 0 and the shape must be
   * enabled.
   *
   * @param shape the shape
   * @return true if the shape can be displayed
   */
  private boolean shouldDisplay(IShape shape) {
    if (enabledMap != null && enabledMap.containsKey(shape.getName())) {
      return enabledMap.get(shape.getName()) && shape.getOpacity() != 0;
    } else {
      return shape.getOpacity() != 0;
    }
  }

  /**
   * Update the area to allow for scrolling.
   *
   * @param shape the shape we've added
   * @return true if the shape area was changed.
   */
  private boolean updateArea(IShape shape) {
    boolean changed = false;
    IPosition pos = shape.getPosition();
    IDimension dim = shape.getDimension();

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

  /**
   * Draw a rotated rectangle.
   * @param g the graphics this is being drawn on
   * @param state the state of the rectangle to be drawn
   */
  private void drawRectangle(Graphics g, IShape state) {
    Graphics2D gg = setupDrawShape(g, state);

    gg.fillRect((int) state.getPosition().getX(), (int) state.getPosition().getY(),
        (int) state.getDimension().getWidth(), (int) state.getDimension().getHeight());
  }

  /**
   * Draw a rotated oval.
   * @param g the graphics this is being drawn on
   * @param state the state of the oval to be drawn
   */
  private void drawOval(Graphics g, IShape state) {
    Graphics2D gg = setupDrawShape(g, state);

    gg.fillOval((int) state.getPosition().getX(), (int) state.getPosition().getY(),
        (int) state.getDimension().getWidth(), (int) state.getDimension().getHeight());

  }

  /**
   * The Rotates the canvas that the rectangle will be drawn on.
   * @param g the graphics that is being drawn on
   * @param state the state of the shape to be drawn (needed for dimensions)
   * @return the 2D graphics to that a shape can be drawn on
   */
  private Graphics2D setupDrawShape(Graphics g, IShape state) {
    Graphics2D gg = (Graphics2D) g.create();

    System.out.println(state.getName() + " is rotated at " + state.getRotation());

    AffineTransform transform = new AffineTransform();
    transform.rotate(-Math.toRadians(state.getRotation()),
        state.getPosition().getX() + state.getDimension().getWidth()/2,
        state.getPosition().getY() + state.getDimension().getHeight()/2);

    AffineTransform old = gg.getTransform();
    gg.transform(transform);

    return gg;
  }


}
