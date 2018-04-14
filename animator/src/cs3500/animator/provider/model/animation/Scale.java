package cs3500.animator.provider.model.animation;

import cs3500.animator.provider.model.AbstractCanvasObject;
import cs3500.animator.provider.model.shape.AbstractShape;
import cs3500.animator.provider.model.shape.Oval;
import cs3500.animator.provider.model.shape.Rectangle;
import util.MyUtil;

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
    super(startTime, endTime, shape);
    if(startTime > endTime) {
      throw new IllegalArgumentException("invalid time");
    }
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    if(shape instanceof Rectangle) {
      this.startWidth = ((Rectangle)shape).getWidth();
      this.startHeight = ((Rectangle)shape).getHeight();
      this.endWidth = startWidth * scaleX;
      this.endHeight = startHeight * scaleY;
    }

    if(shape instanceof Oval) {
      this.startWidth = ((Oval)shape).getRadiusX();
      this.startHeight = ((Oval)shape).getRadiusY();
      this.endWidth = startWidth * scaleX;
      this.endHeight = startHeight * scaleY;
    }
  }

  /**
   * A getter for the x scale factor.
   *
   * @return the x scale factor value
   */
  public double getScaleX() {
    return scaleX;
  }

  /**
   * A getter for the y scale factor.
   *
   * @return the y scale factor value
   */
  public double getScaleY() {
    return scaleY;
  }

  @Override
  public void animate(AbstractShape s) {
    s.scale(scaleX, scaleY);
  }

  @Override
  public void animate(int ticksElapsed) {
    float width = MyUtil.interpolate((float)startWidth, (float)endWidth,
            this.getStartTime(), this.getEndTime(), ticksElapsed);
    float height = MyUtil.interpolate((float)startHeight, (float)endHeight,
            this.getStartTime(), this.getEndTime(), ticksElapsed);

    shape.scale(width, height);
  }

  @Override
  public boolean sameType(AbstractAnimation other) {
    return other instanceof Scale;
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
    return o.compareTo(this);
  }
}
