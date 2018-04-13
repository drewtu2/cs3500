package cs3500.animator.provider.model.animation;

import cs3500.animator.model.AbstractCanvasObject;
import cs3500.animator.provider.model.shape.AbstractShape;
import cs3500.animator.provider.model.shape.Oval;
import cs3500.animator.provider.model.shape.Rectangle;
import cs3500.animator.model.animation.AbstractAnimation;

/**
 * Represents a scaling animation to resize an object based on a scaling factor.
 */
public class Scale extends AbstractAnimation {
  public static final String ERROR_SCALE_FACTOR_BOUNDS =
          "The scale factor must be greater than 0.";

  private double scaleX;
  private double scaleY;
  private double startWidth;
  private double endWidth;
  private double startHeight;
  private double endHeight;

  /**
   * Constructs an scale animation with a given scale factor.
   *
   * @param startTime the time to show the object
   * @param endTime   the time to hide the object
   * @param shape     the shape to apply the animation to
   * @param scaleX    the factor to scale the shape to in the x direction
   * @param scaleY    the factor to scale the shape to in the y direction
   * @throws IllegalArgumentException if the either scaleX or scaleY is null
   */
  public Scale(int startTime, int endTime, AbstractShape shape, double scaleX, double scaleY)
          throws IllegalArgumentException {
  }

  /**
   * A getter for the x scale factor.
   *
   * @return the x scale factor value
   */
  public double getScaleX() {
  }

  /**
   * A getter for the y scale factor.
   *
   * @return the y scale factor value
   */
  public double getScaleY() {
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

    builder.append("scales from ")
            .append(s.getSizeDescription())
            .append(" to ")
            .append(s.getSizeDescriptionWithScale(scaleX, scaleY));

    return builder.toString();
  }

  @Override
  public int compareTo(AbstractCanvasObject o) {
    return 0;
  }
}
