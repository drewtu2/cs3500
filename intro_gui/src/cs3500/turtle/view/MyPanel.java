package cs3500.turtle.view;

import cs3500.turtle.model.Position2D;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

  private Position2D rectPos;

  public MyPanel() {
    rectPos = new Position2D(0, 0);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawRect((int)rectPos.getX(), (int)rectPos.getY(), 200, 100);

  }

  public void updateState(Position2D pos){
    rectPos = pos;

  }

}
