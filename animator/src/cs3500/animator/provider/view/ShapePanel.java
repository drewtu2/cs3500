package cs3500.animator.provider.view;

import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.util.NumUtil;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 * This class represents a panel to draw a list of shapes. It can also can show a list of
 * animations.
 */
public class ShapePanel extends JPanel {

  private List<IShape> listOfShapes;

  /**
   * Constructs a panel for the animation of shapes.
   *
   * @param listOfShapes the list of shapes to be drawn
   */
  ShapePanel(List<IShape> listOfShapes) {
    this.listOfShapes = listOfShapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (IShape shape : listOfShapes) {
      int x = (int) shape.getLocation().getX();
      int y = (int) shape.getLocation().getY();
      g.setColor(shape.getColor().transformToAwt());

      if (shape.getType().equalsIgnoreCase("rectangle")) {
        g.draw3DRect(x, y, NumUtil.round(shape.getX()), NumUtil.round(shape.getY()), true);
        g.fill3DRect(x, y, NumUtil.round(shape.getX()), NumUtil.round(shape.getY()), true);
      } else if (shape.getType().equalsIgnoreCase("oval")) {
        int roundedRadiusX = NumUtil.round(shape.getX());
        int roundedRadiusY = NumUtil.round(shape.getY());
        int roundedWidth = NumUtil.round(shape.getX() * 2);
        int roundedHeight = NumUtil.round(shape.getY() * 2);
        x = x - roundedRadiusX;
        y = y - roundedRadiusY;

        g.drawOval(x, y, roundedWidth, roundedHeight);
        g.fillOval(x, y, roundedWidth, roundedHeight);
      }
    }
  }
}
