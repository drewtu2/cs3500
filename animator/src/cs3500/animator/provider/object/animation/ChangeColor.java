package cs3500.animator.provider.object.animation;

import cs3500.animator.provider.object.Color;
import cs3500.animator.provider.object.ICanvasObject;
import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.shape.AbstractShape;
import cs3500.animator.provider.object.shape.IShape;
import util.MyUtil;

/**
 * Represents a color change animation to alter an object's color to a target color.
 */
public class ChangeColor extends AbstractAnimation {
  public static final String ERROR_TARGET_NULL =
          "Target cannot be null.";

  private IColor startColor;
  private IColor target;

  /**
   * Constructs an scale animation with a given scale factor.
   *
   * @param startTime the time to show the object
   * @param endTime   the time to hide the object
   * @param shape     the shape to apply the animation to
   * @param target    the color to change the shape to
   * @throws IllegalArgumentException if the target is null
   */
<<<<<<< Updated upstream:animator/src/cs3500/animator/provider/object/animation/ChangeColor.java
  public ChangeColor(int startTime, int endTime, AbstractShape shape, IColor target) throws
=======
  public ChangeColor(int startTime, int endTime, IShape shape, Color target) throws
>>>>>>> Stashed changes:animator/src/cs3500/animator/provider/model/animation/ChangeColor.java
          IllegalArgumentException {
    super(startTime, endTime, shape);
    if(startTime > endTime) {
      throw new IllegalArgumentException("invalid time span");
    }
    this.startColor = shape.getColor();
    this.target = target;
  }

  /**
   * A getter for the target.
   *
   * @return the target color
   */
  public IColor getTarget() {
    return target;
  }

  @Override
  public void animate(IShape s) {
    s.changeColor(target);
  }

  @Override
  public void animate(int ticksElapsed) {
    float red = MyUtil.interpolate((float)startColor.getRed(), (float)target.getRed(),
            this.getStartTime(), this.getEndTime(), ticksElapsed);
    float green = MyUtil.interpolate((float)startColor.getGreen(), (float)target.getGreen(),
            this.getStartTime(), this.getEndTime(), ticksElapsed);
    float blue = MyUtil.interpolate((float)startColor.getBlue(), (float)target.getBlue(),
            this.getStartTime(), this.getEndTime(), ticksElapsed);

    shape.changeColor(new Color(red, green, blue));
  }

  @Override
  public boolean sameType(IAnimation other) {
    return other instanceof ChangeColor;
  }

  @Override
  public String getAction(IShape s) {
    StringBuilder builder = new StringBuilder();

    builder.append("changes color from ")
            .append(s.getColor().toString())
            .append(" to ")
            .append(target.toString());

    return builder.toString();
  }

  @Override
  public int compareTo(ICanvasObject o) {
    return o.compareTo(this);
  }
}
