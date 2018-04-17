
package cs3500.animator.view;

import static util.MyUtil.checkNull;

import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.IAnimationSummary;
import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IPosition;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Class representing the SVG view of an animator. SVG is a type of xml file that can show an
 * animation when opened in a browser. Methods in this class output a correctly formatted file for
 * shapes and respective animations and looping.
 */
public class SVGView implements IView {

  private int width = 1000;
  private int height = 900;
  private Appendable output;
  private float speed;
  private boolean loopable;
  private float lastAnimTime;
  private StringBuilder resetString;

  /**
   * Constructs an instance of an svgview setting the output to be the appendable. Setting speed
   * default to 1 tick/sec
   *
   * @param out Appendable to write to.
   */
  public SVGView(Appendable out, boolean loop) {
    if (out == null) {
      throw new NullPointerException();
    }

    output = out;
    speed = 1;
    loopable = loop;
    lastAnimTime = 0;
  }


  @Override
  public void show(IModelView model, int tempo) throws IOException {
    checkNull(model);

    speed = tempo;
    Map<String, IAnimatedShape> mMap = model.getFullState();
    output.append("<svg width=\""
        + Integer.toString(width)
        + "\" height=\""
        + Integer.toString(height)
        + "\" version=\"1.1\""
        + "\n\txmlns=\"http://www.w3.org/2000/svg\""
        + ">\n\n");

    List<IAnimatedShape> shapes = new ArrayList<>(mMap.values());
    Collections.sort(shapes);

    printShape(shapes);

    if (loopable) {
      output.append("<rect>\n" + "\t<animate id=\"base\" begin=\"0;base.end\" dur=\""
          + Double.toString(tick2Time(lastAnimTime) + 0.001)
          + "s\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
          + "</rect>\n\n");
    }

    output.append("</svg>\n");

  }


  /**
   * Prints the shape svg desciptions to the appendable.
   *
   * @param shapes list of shapes in the map
   * @throws IOException thrown by appendable
   */
  private void printShape(List<IAnimatedShape> shapes) throws IOException {
    String endTag = "";

    for (IAnimatedShape curShape : shapes) {
      if (curShape.getType().equals(ShapeType.RECTANGLE)) {
        endTag = "</rect>";
        curShape = (Rectangle) curShape;
        output.append(String.format("<rect id=\"%s\" x=\"%s\" y=\"%s\" " +
                "width=\"%s\" height=\"%s\" " + "fill=\"%s\" " +
                "visibility=", curShape.getName(),
            Integer.toString(Math.round(curShape.getPosition().getX())),
            Integer.toString(Math.round(curShape.getPosition().getY())),
            Integer.toString(Math.round(curShape.getDimension()
                .getWidth())),
            Integer.toString(Math.round(curShape.getDimension()
                .getHeight())),
            colorToRGB(curShape.getColor())));
        if (curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        } else {
          output.append("\"invisible\" >");
        }
      } else if (curShape.getType().equals(ShapeType.OVAL)) {
        endTag = "</ellipse>";
        curShape = (Oval) curShape;
        output.append(String.format("<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" " +
                "rx=\"%s\" " + "ry=\"%s\" " + "fill=\"%s\" " +
                "visibility=", curShape.getName(),
            Integer.toString(Math.round(curShape.getPosition().getX())),
            Integer.toString(Math.round(curShape.getPosition().getY())),
            Integer.toString(Math.round((curShape.getDimension())
                .getWidth())),
            Integer.toString(Math.round((curShape.getDimension())
                .getHeight())),
            colorToRGB(curShape.getColor())));
        if (curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        } else {
          output.append("\"invisible\" >");
        }
      }
      resetString = new StringBuilder();

      List<IAnimationSummary> animationSummaries = getSummary(curShape);

      Collections.sort(animationSummaries);

      for (IAnimationSummary summary : animationSummaries) {
        output.append(summary.getDescription());
      }

      if (loopable) {
        output.append(resetString.toString());
      }
      output.append("\n" + endTag + "\n\n");
    }
  }

  /**
   * Appends svg formatted strings representing the animations to the appendable.
   *
   * @param s IAnimatedShape holding list of animations by type
   */
  private String showAnimations(IAnimation animation, IAnimatedShape s) throws IOException {
    String attName1 = "";
    String attName2 = "";
    StringBuilder strBuild = new StringBuilder();

    switch (animation.getType()) {
      case MOVE:
        if (s.getType().equals(ShapeType.OVAL)) {
          attName1 = "cx";
          attName2 = "cy";
        } else if (s.getType().equals(ShapeType.RECTANGLE)) {
          attName1 = "x";
          attName2 = "y";
        }
        if (typeOfMove((animation).getStartPos(),
            (animation).getEndPos()).contains("x")) {
          strBuild.append(printAnimationHelper(
              attName1,
              Integer.toString(Math.round((animation).getStartPos()
                  .getX())),
              Integer.toString(Math.round((animation).getEndPos()
                  .getX())),
              animation.getStartTime(),
              animation.getEndTime() - animation.getStartTime()));
          resetString.append(printAnimationHelper(attName1, Float.toString(s.getPosition().getX()
          )));
        }
        if (typeOfMove((animation).getStartPos(),
            (animation).getEndPos()).contains("y")) {
          strBuild.append(printAnimationHelper(
              attName2,
              Integer.toString(Math.round((animation).getStartPos()
                  .getY())),
              Integer.toString(Math.round((animation).getEndPos()
                  .getY())),
              animation.getStartTime(),
              animation.getEndTime() - animation.getStartTime()));
          resetString.append(printAnimationHelper(attName2, Float.toString(s.getPosition().getY()
          )));
        }
        break;
      case COLOR:
        strBuild.append(printAnimationHelper(
            "fill",
            colorToRGB((animation).getStartColor()),
            colorToRGB((animation).getEndColor()),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(printAnimationHelper("fill", colorToRGB(s.getColor())));
        break;
      case SCALE:
        if (s.getType().equals(ShapeType.OVAL)) {
          attName1 = "rx";
          attName2 = "ry";
        } else if (s.getType().equals(ShapeType.RECTANGLE)) {
          attName1 = "width";
          attName2 = "height";
        }
        strBuild.append(printAnimationHelper(
            attName1,
            Integer.toString(Math.round(((animation)
                .getStartDimension()).getWidth())),
            Integer.toString(Math.round(((animation)
                .getEndDimension())
                .getWidth())),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(printAnimationHelper(attName1, Float.toString((s
            .getDimension()).getWidth())));

        strBuild.append(printAnimationHelper(
            attName2,
            Integer.toString(Math.round(((animation)
                .getStartDimension()).getHeight())),
            Integer.toString(Math.round(((animation)
                .getEndDimension())
                .getHeight())),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(printAnimationHelper(attName2, Float.toString((s
            .getDimension()).getHeight())));
        break;
      case CREATE:
        strBuild.append(printAnimationHelper("visibility", animation.getStartTime()));
        break;
      case DESTROY:
        strBuild.append(printAnimationHelper("visibility", animation.getEndTime()));
        break;
      default:
        throw new IOException("invalid type used");
    }
    return strBuild.toString();
  }


  /**
   * Converts a color to its representation as string for svg formatting.
   *
   * @param startColor rgb to convert
   * @return string rep of rgb
   */
  private String colorToRGB(IRGBColor startColor) {
    String red = Integer.toString(Math.round(startColor.getRed() * 255));
    String green = Integer.toString(Math.round(startColor.getGreen() * 255));
    String blue = Integer.toString(Math.round(startColor.getBlue() * 255));

    return "rgb(" + red + "," + green + "," + blue + ")";
  }

  /**
   * Finds the last animation in the animator to account for when to loopback.
   *
   * @param animation one animation to compare with current last end time
   */
  private void findLastAnimation(IAnimation animation) {
    if (animation.getEndTime() > lastAnimTime) {
      lastAnimTime = animation.getEndTime();
    }
  }

  /**
   * Returns a list of animation summaries of a given shape.
   *
   * @param shape the shape containing the animation summaries.
   * @return the list of summaries.
   */
  private List<IAnimationSummary> getSummary(IAnimatedShape shape) throws IOException {
    checkNull(shape);

    List<IAnimationSummary> animationSummaries = new ArrayList<>();
    Map<AnimationType, List<IAnimation>> animationMap = shape.getAnimations();
    IAnimationSummary summary;

    for (List<IAnimation> aList : animationMap.values()) {
      for (IAnimation animation : aList) {
        findLastAnimation(animation);
        summary = animation.getAnimationSummary(showAnimations(animation, shape));
        animationSummaries.add(summary);
      }
    }

    return animationSummaries;
  }

  /**
   * Returns the string given an animation.
   *
   * @param startTick the start tick.
   * @param endTick the end tick.
   * @param startState the start state as a string.
   * @param endState the end state as a string.
   * @param attName attribute on which the animation occurs
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, String startState, String
      endState, float startTick, float endTick) {

    StringBuilder builder = new StringBuilder();

    if (loopable) {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.begin+");
    } else {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"");
    }
    builder.append(tick2Time(startTick));
    builder.append("s\" dur=\"");
    builder.append(tick2Time(endTick));
    builder.append("s\" attributeName=\"");
    builder.append(attName);
    builder.append("\" from=\"");
    builder.append(startState);
    builder.append("\" to=\"");
    builder.append(endState);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }


  /**
   * Returns the string given an animation to reset during looping.
   *
   * @param attName the attribute on which the animation occurs
   * @param startState value to reset animation to
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, String startState) {

    StringBuilder builder = new StringBuilder();

    builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.end");
    builder.append("\" dur=\"0.5ms\"");
    builder.append(" attributeName=\"");
    builder.append(attName);
    builder.append("\" to=\"");
    builder.append(startState);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }

  /**
   * Returns the string given an animation.
   *
   * @param attName the attribute on which the animation occurs
   * @param tick the tick the action occurs
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, float tick) {

    StringBuilder builder = new StringBuilder();

    if (loopable) {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.begin+");
    } else {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"");
    }
    builder.append(tick2Time(tick));
    builder.append("s\" dur=\"");
    builder.append(0);
    builder.append("s\" attributeName=\"");
    builder.append(attName);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }

  /**
   * Returns the time for a corresponding tick.
   *
   * @param tick the tick.
   * @return the time
   */
  private float tick2Time(float tick) {
    return tick / speed;
  }

  /**
   * Determine if the position moves in either x-dir, y-dir, or both.
   *
   * @param start position from
   * @param end position dest
   * @return string x, y, or xy representing the direction of motion
   */
  private String typeOfMove(IPosition start, IPosition end) {
    String result = "";
    if (start.getX() != end.getX()) {
      result = result + "x";
    }
    if (start.getY() != end.getY()) {
      result = result + "y";
    }
    return result;
  }
}

