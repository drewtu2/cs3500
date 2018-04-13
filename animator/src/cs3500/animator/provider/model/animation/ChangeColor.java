package cs3500.animator.provider.model.animation;

import cs3500.animator.model.AbstractCanvasObject;
import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.shape.AbstractShape;
import cs3500.animator.model.animation.AbstractAnimation;
/**
 * Represents a color change animation to alter an object's color to a target color.
 */
public class ChangeColor extends AbstractAnimation {
  public static final String ERROR_TARGET_NULL =
          "Target cannot be null.";

  private Color startColor;
  private Color target;

  /**
   * Constructs an scale animation with a given scale factor.
   *
   * @param startTime the time to show the object
   * @param endTime   the time to hide the object
   * @param shape     the shape to apply the animation to
   * @param target    the color to change the shape to
   * @throws IllegalArgumentException if the target is null
   */
  public ChangeColor(int startTime, int endTime, AbstractShape shape, Color target) throws
          IllegalArgumentException {
  }

  /**
   * A getter for the target.
   *
   * @return the target color
   */
  public Color getTarget() {
  }

  @Override
  public void animate(AbstractShape s) {
  }

  @Override
  public void animate(int ticksElapsed) {
  }

  @Override
  public boolean sameType(AbstractAnimation other) {
  }

  @Override
  public String getAction(AbstractShape s) {
    StringBuilder builder = new StringBuilder();

    builder.append("changes color from ")
            .append(s.getColor().toString())
            .append(" to ")
            .append(target.toString());

    return builder.toString();
  }

  @Override
  public int compareTo(AbstractCanvasObject o) {
    return 0;
  }
}
