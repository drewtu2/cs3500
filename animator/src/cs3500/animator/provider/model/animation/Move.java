package cs3500.animator.provider.model.animation;

import cs3500.animator.provider.model.Posn;
import cs3500.animator.provider.model.shape.AbstractShape;
import util.MyUtil;

public class Move extends AbstractAnimation{

  private Posn start;
  private Posn end;

  /**
   * Represents a move animation.
   * @param startTime the start time.
   * @param endTime the end time.
   * @param shape the shape this is being applied to.
   */
  public Move(Posn startPos, Posn endPos, int startTime, int endTime, AbstractShape shape) {
    super(startTime, endTime, shape);
    start = startPos;
    end = endPos;
  }

  @Override
  public void animate(AbstractShape s) {
    s.move(end);

  }

  @Override
  public void animate(int ticksElapsed) {
    float x = MyUtil.interpolate(start.getX(), end.getX(), this.getStartTime(), this.getEndTime(), ticksElapsed);
    float y = MyUtil.interpolate(start.getX(), end.getX(), this.getStartTime(), this.getEndTime(), ticksElapsed);

    shape.move(new Posn(x, y));
  }

  @Override
  public boolean sameType(AbstractAnimation other) {
    return other instanceof Move;
  }

  @Override
  public String getAction(AbstractShape s) {
    StringBuilder myBuilder = new StringBuilder();

    myBuilder.append("Shape ");
    myBuilder.append(s.getName());
    myBuilder.append(" moves from ");
    myBuilder.append(start.toString());
    myBuilder.append(" to ");
    myBuilder.append(end.toString());
    myBuilder.append(" from time t=");
    myBuilder.append(this.getStartTime());
    myBuilder.append(" to time t=");
    myBuilder.append(this.getEndTime());

    return myBuilder.toString();
  }

  /**
   * Returns the destination of this animation.
   * @return the destination of this animation.
   */
  public Posn getDestination() {
    return end;
  }
}
