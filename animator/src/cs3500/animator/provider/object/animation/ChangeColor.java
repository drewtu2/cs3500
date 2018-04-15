package cs3500.animator.provider.object.animation;

import cs3500.animator.provider.object.Color;
import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.shape.IShape;

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
  public ChangeColor(int startTime, int endTime, IShape shape, IColor target) throws
          IllegalArgumentException {
    super(startTime, endTime, shape);

    if (target == null) {
      throw new IllegalArgumentException(ERROR_TARGET_NULL);
    }

    this.target = target;
  }

  /**
   * A getter for the target.
   *
   * @return the target color
   */
  public IColor getTarget() {
    return this.target;
  }

  @Override
  public void animate(IShape s) {
    s.changeColor(this.target);
  }

  @Override
  public void animate(int ticksElapsed) {
    if (!this.animationStarted) {
      this.startColor = shape.getColor();
      this.animationStarted = true;
    }
    double newR = this.startColor.getRed() * this.getStartCoef(ticksElapsed)
            + this.target.getRed() * this.getEndCoef(ticksElapsed);
    double newG = this.startColor.getGreen() * this.getStartCoef(ticksElapsed)
            + this.target.getGreen() * this.getEndCoef(ticksElapsed);
    double newB = this.startColor.getBlue() * this.getStartCoef(ticksElapsed)
            + this.target.getBlue() * this.getEndCoef(ticksElapsed);
    shape.changeColor(new Color(newR, newG, newB));
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

  }
