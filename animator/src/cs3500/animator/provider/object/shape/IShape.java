package cs3500.animator.provider.object.shape;

import cs3500.animator.provider.object.ICanvasObject;
import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.Posn;

/**
 * This interface contains all of the methods that a shape should have when being constructed
 * for an animation.
 */

public interface IShape extends ICanvasObject {

  /**
   * gets the x dimension of this shape
   * @return x dimension of this shape
   */
  double getX();

  /**
   * gets the y dimension of this shape
   * @return the y dimension of this shape
   */
  double getY();

  /**
   * A getter for the name.
   *
   * @return the name String
   */
  public String getName();

  /**
   * A getter for the location.
   *
   * @return the location Posn
   */
  public Posn getLocation();

  /**
   * A getter for the color.
   *
   * @return the color object
   */
  public IColor getColor();

  /**
   * Produces a string describing the type of the object, usually the object's class name.
   *
   * @return the type description string
   */
  public String getType();

  /**
   * Produces a string describing the attributes associated with the shape.
   *
   * @return the attribute description string
   */
  public abstract String getAttributes();

  /**
   * Produces a string describing the attributes associated with this shape's size if the size is
   * multiplied by a scale factor in the x and y directions.
   *
   * @param scaleX the amount to scale the shape in the x direction
   * @param scaleY the amount to scale the shape in the y direction
   * @return the size attribute description string with the scaling factor taken into place
   */
  public abstract String getSizeDescriptionWithScale(double scaleX, double scaleY);

  /**
   * Produces an identical copy of this shape in a different memory location.
   *
   * @return the cloned shape
   */
  public abstract IShape clone();

  /**
   * A convenience method describing the attributes associated with this shape's size and without
   * a scale factor.
   *
   * @return the size attribute description string
   */
  public String getSizeDescription();

  /**
   * Modifies this shape to be located at the new given location.
   *
   * @param newLocation the location to move this shape to
   */
  public void move(Posn newLocation);

  /**
   * Modifies this shape to scale itself by the given x and y scaling factors.
   *
   * @param scaleX the factor to scale in the x direction
   * @param scaleY the factor to scale in the y direction
   */
  public abstract void scale(double scaleX, double scaleY);

  /**
   * Modifies this shape's width and height to the new width and height.
   * @param width width of the shape
   * @param height height of the shape
   */
  public abstract void updateSize(double width, double height);

  /**
   * Modifies this shape to be colored the new given color.
   *
   * @param newColor the color to change this shape to
   */
  public void changeColor(IColor newColor);

  /**
   * Sets the properties of this shape to its original properties at construction.
   */
  public void reset();
}

