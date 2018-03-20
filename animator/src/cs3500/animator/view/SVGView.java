package cs3500.animator.view;

import static cs3500.animator.util.MyUtil.checkNull;

import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.concrete.ColorAnimation;
import cs3500.animator.animation.concrete.MoveAnimation;
import cs3500.animator.animation.concrete.ScaleAnimation;
import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Class representing the SVG view of an animator.
 */
public class SVGView implements IView {

  private int width;
  private int height;
  private Appendable output;
  private float speed;

  public SVGView(Appendable out) {
    if (out == null) {
      throw new NullPointerException();
    }

    output = out;
    speed = 1;
  }

  @Override
  public void show(IModelView model, int tempo) throws IOException{
    checkNull(model);

    speed = tempo;
    String endTag = "";
    Map<String, IAnimatedShape> mMap = model.getFullState();
    output.append("<svg width=\"" + Integer.toString(width) +
    "\" height=\"" + Integer.toString(height) + ">\n");

    for(String sName: model.listShapes()) {
      IShape curShape = mMap.get(sName);
      if(curShape.getType().equals(ShapeType.RECTANGLE)) {
        endTag = "</rect>";
        curShape = (Rectangle) curShape;
        output.append("<rect id=\"");
        output.append(curShape.getName());
        output.append("\" x=\"");
        output.append(Float.toString(curShape.getPosition().getX()));
        output.append("\" y=\"");
        output.append(Float.toString(curShape.getPosition().getY()));
        output.append("\" width=\"");
        output.append(Float.toString(((WidthHeightDim) curShape.getDimension()).getWidth()));
        output.append("\" height=\"");
        output.append(Float.toString(((WidthHeightDim) curShape.getDimension()).getHeight()));
        output.append(" fill=\"rgb(");
        output.append(Float.toString(curShape.getColor().getRed()));
        output.append("," + Float.toString(curShape.getColor().getGreen()));
        output.append(","+ Float.toString(curShape.getColor().getBlue()));
        output.append(")\" visibility=\"");
        if(curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        }
        else {
          output.append("\"invisible\" >");
        }
      }
      else if(mMap.get(sName).getType().equals(ShapeType.OVAL)){
        endTag = "</ellipse>";
        curShape = (Oval) curShape;
        output.append("<ellipse id=\"");
        output.append(curShape.getName());
        output.append("\" cx=\"");
        output.append(Float.toString(curShape.getPosition().getX()));
        output.append("\" cy=\"");
        output.append(Float.toString(curShape.getPosition().getY()));
        output.append("\" rx=\"");
        output.append(Float.toString(((WidthHeightDim) curShape.getDimension()).getWidth()));
        output.append("\" ry=\"");
        output.append(Float.toString(((WidthHeightDim) curShape.getDimension()).getHeight()));
        output.append("\" fill=\"rgb(");
        output.append(Float.toString(curShape.getColor().getRed()));
        output.append("," + Float.toString(curShape.getColor().getGreen()));
        output.append(","+ Float.toString(curShape.getColor().getBlue()));
        output.append(")\" visibility=\"");
        if(curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        }
        else {
          output.append("\"invisible\" >");
        }
      }
      showAnimations(sName, mMap.get(sName));
      output.append(endTag);
    }
    output.append("\n\n</svg>");
  }


  private void showAnimations(String shapeName, IAnimatedShape s) throws IOException{
    Map<AnimationType, List<IAnimation>> animaMap = s.getAnimations();
    for(List<IAnimation> loAnim : animaMap.values()) {
      for(IAnimation animation : loAnim) {
        switch(animation.getType()){
          case MOVE:
            output.append(printAnimationHelper(
                    shapeName,
                    ((MoveAnimation) animation).getStartPos().toString(),
                    ((MoveAnimation) animation).getEndPos().toString(),
                    animation.getStartTime(),
                    animation.getEndTime(),
                    "freeze"));
            break;
          case COLOR:
            output.append(printAnimationHelper(
                    shapeName,
                    ((ColorAnimation) animation).getStartColor().toString(),
                    ((ColorAnimation) animation).getEndColor().toString(),
                    animation.getStartTime(),
                    animation.getEndTime(),
                    "freeze"));
            break;
          case SCALE:
            output.append(printAnimationHelper(
                    shapeName,
                    ((ScaleAnimation) animation).getStartDimension().toString(),
                    ((ScaleAnimation) animation).getEndDimension().toString(),
                    animation.getStartTime(),
                    animation.getEndTime(),
                    "freeze"));
            break;
          case CREATE:
            output.append(printAnimationHelper(shapeName, animation.getStartTime(),
                    "freeze"));
            break;
          case DESTROY:
            output.append(printAnimationHelper(shapeName, animation.getEndTime(),
                    "remove"));
            break;
            default:
              throw new IOException("invalid type used");
        }
      }

    }
  }

  /**
   * Returns the string given an animation.
   * @param startTick the start tick.
   * @param endTick the end tick.
   * @param startState the start state as a string.
   * @param endState the end state as a string.
   * @param sName  name of the shape on which the animation occurs
   * @param fillType string determining if the animation is reset or stays on screen
   * @return a string representing the animation
   */
  private String printAnimationHelper(String sName, String startState, String endState, float
          startTick, float endTick, String fillType) {

    StringBuilder builder =  new StringBuilder();

    builder.append("\n<animate attributeType=\"xml\" begin=\"");
    builder.append(tick2Time(startTick));
    builder.append("ms\" dur=\"");
    builder.append(tick2Time(endTick));
    builder.append("ms\" attributeName=\"");
    builder.append(sName);
    builder.append("\" from=\"");
    builder.append(startState);
    builder.append("\" to=\"");
    builder.append(endState);
    builder.append("\" fill=\"");
    builder.append(fillType);
    builder.append("\" />");
    builder.append("\n");

    return builder.toString();
  }

  /**
   * Returns the string given an animation.
   * @param sName the name of the shape on which the animation occurs
   * @param tick the tick the action occurs
   * @param fillType string determining if the animation is reset or stays on screen
   * @return a string representing the animation
   */
  private String printAnimationHelper(String sName, float tick, String fillType) {

    StringBuilder builder =  new StringBuilder();

    builder.append("\n<animate attributeType=\"xml\" begin=\"");
    builder.append(tick2Time(tick));
    builder.append("ms\" dur=\"");
    builder.append(tick2Time(tick));
    builder.append("ms\" attributeName=\"");
    builder.append(sName);
    builder.append("\" fill=\"");
    builder.append(fillType);
    builder.append("\" />");
    builder.append("\n");

    return builder.toString();
  }

  /**
   * Returns the time for a corresponding tick.
   * @param tick the tick.
   * @return the time
   */
  private float tick2Time(float tick) {
    return tick * speed;
  }

}
