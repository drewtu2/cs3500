package cs3500.animator.provider.model.animation;
import cs3500.animator.provider.model.ICanvasObject;
import cs3500.animator.provider.model.shape.IShape;

/**
 * This interface contains all of the methods that an animation should have when being constructed.
 */
public interface IAnimation extends ICanvasObject {

  /**
   * Checks whether this animation makes for an incompatible move with the other animation, meaning
   * that both animations perform the same type of alteration on the same shape at overlapping
   * times.
   *
   * @param other the animation to check this one against
   * @throws IllegalArgumentException if the given animation is null
   */
  public boolean conflictsWithAnimation(IAnimation other) throws IllegalArgumentException;

  /**
   * Checks whether the times of the two animations overlap one another at all, exclusively.
   *
   * @param other the animation to check this one against
   * @return true if the execution periods overlap at all, false otherwise
   */
  public boolean timeOverlaps(IAnimation other);

  /**
   * Performs the full animation on the given shape by altering the shape the necessary amount.
   *
   * @param s the shape to animate
   */
  public abstract void animate(IShape s);

  /**
   * Performs a fraction of an animation based on ticks elapsed.
   *
   * @param ticksElapsed total ticks elapsed
   */
  public abstract void animate(int ticksElapsed);

  /**
   * Checks whether the given anmiation type is the same type as this animation type.
   *
   * @param other the animatino to check this one against
   * @return true if the animation types are the same, false otherwise
   */
  public abstract boolean sameType(IAnimation other);

  /**
   * A getter for the shape.
   *
   * @return this object's shape
   */
  public IShape getShape();

  /**
   * Produces an action descriptor of what this animation does.
   *
   * @param s the shape that the action is being performed on
   * @return the action string
   */
  public abstract String getAction(IShape s);

  /**
   * Produces a string describing this animation's actions.
   *
   * @param s the shape that the action is being performed on
   * @return the animation string description
   */
  public String toString(IShape s);

  /**
   * Calculates the starting coefficient for any temporal animation.
   *
   * @param ticksElapsed total ticks elapsed
   * @return the starting coefficient
   */
  double getStartCoef(int ticksElapsed);

  /**
   * Calculates the ending coefficient for any temporal animation.
   *
   * @param ticksElapsed total ticks elapsed
   * @return the ending coefficient
   */
  double getEndCoef(int ticksElapsed);
}

