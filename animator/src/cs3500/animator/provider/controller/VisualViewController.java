package cs3500.animator.provider.controller;

import cs3500.animator.provider.object.animation.AbstractAnimation;
import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.shape.AbstractShape;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.view.VisualView;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

public class VisualViewController implements IVisualController {

  private VisualView visualView;
  private List<IAnimation> animations;
  private List<IShape> shapes;
  private Map<IShape, Integer> order;
  private double tempo;
  private Timer timer;

  /**
   * Construct a visual view controller.
   * @param view
   * @param animations
   * @param shapes
   * @param shapeOrder
   * @param tempo
   */
  public VisualViewController(VisualView view, List<IAnimation> animations,
                              List<IShape> shapes, Map<IShape, Integer> shapeOrder,
                              double tempo) {
      this.visualView = view;
      this.animations = animations;
      this.shapes = shapes;
      this.order = shapeOrder;
      this.tempo = tempo;

      double drawRate = 1/tempo * 1000;

      this.timer = new Timer((int)drawRate,
        (ActionEvent e) -> {
        //TODO fill this in
       });
  }

    @Override
    public void startAnimation () {
      timer.start();
    }

    @Override
    public List<IShape> getVisibleShapes () {
      return null;
    }
  }
