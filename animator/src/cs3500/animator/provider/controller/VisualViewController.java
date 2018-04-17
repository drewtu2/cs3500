package cs3500.animator.provider.controller;

import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.view.VisualView;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

public class VisualViewController implements IVisualController {

  private List<IShape> shapes;
  private Timer timer;
  private double tickNumber;

  /**
   * Construct a visual view controller.
   *
   * @param view the visual view to use.
   * @param animations the list of animations.
   * @param shapes the list of shapes to use.
   * @param shapeOrder the order of the given shapes.
   * @param tempo the speed to play at.
   */
  public VisualViewController(VisualView view, List<IAnimation> animations,
      List<IShape> shapes, Map<IShape, Integer> shapeOrder,
      double tempo) {

    this.shapes = shapes;
    int drawRate = 50;
    this.tickNumber = 0;

    Collections.sort(shapes, (IShape o1, IShape o2) -> {
      return shapeOrder.get(o1).compareTo(shapeOrder.get(o2));
    });

    Collections.sort(animations);

    timer = new Timer(drawRate,
        (ActionEvent e) -> {
          //System.out.println("Timer Event fired");
          this.tickNumber += (drawRate * tempo / 1000.0);

          for (IAnimation animation : animations) {
            setAnimation(animation, this.tickNumber);
          }

          view.refresh();
        });
  }

  @Override
  public void startAnimation() {
    timer.start();
  }

  @Override
  public List<IShape> getVisibleShapes() {
    return shapes;
  }

  /**
   * Returns if the given value is in bounds of the start and end.
   *
   * @param animation the animation we're testing bounds for
   * @param test the test value
   * @return true if the given is in bounds
   */
  private boolean inBounds(IAnimation animation, double test) {
    return animation.getStartTime() <= test && animation.getEndTime() >= test;
  }

  /**
   * Returns negative if the animation ends before the given test time or positive if the animation
   * occurs after the given start time. Returns 0 if test occurs during.
   *
   * @param animation the animation
   * @param test the test time
   * @return positive or negative int
   */
  private int compare(IAnimation animation, double test) {
    if (animation.getStartTime() > test) {
      return -1;
    } else if (animation.getEndTime() < test) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Applies the animaiton if applicable.
   *
   * @param animation the animation to apply
   * @param tick the tick we're currently on
   */
  private void setAnimation(IAnimation animation, double tick) {
    int comparision = compare(animation, tick);

    if (comparision == 1) {
      animation.animate(animation.getShape());
    } else if (comparision == 0) {
      animation.animate((int) tick);
    } else {
      // Do nothing...
    }
  }
}
