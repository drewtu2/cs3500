package cs3500.animator.view;

import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.io.IOException;
import java.util.Map;

/**
 * Class representing the SVG view of an animator.
 */
public class SVGView implements IView {

  private int width;
  private int height;
  protected Appendable output;

  public SVGView(Appendable out) {
    if (out == null) {
      throw new NullPointerException();
    }

    out = output;
  }

  @Override
  public void show(IModelView model, int tempo) throws IOException{
    Map<String, IAnimatedShape> mMap = model.getFullState();
    output.append("<svg width=\"" + Integer.toString(width) +
    "\" height=\"" + Integer.toString(height) + ">\n");

    for(String sName: model.listShapes()) {
      IShape curShape = mMap.get(sName);
      if(curShape.getType().equals(ShapeType.RECTANGLE)) {
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
      }
  }


  //private void showAnimations(IAnimatedShape s){
  //  Map<AnimationType, List<IAnimation>> animaMap = ((AbstractAnimatedShape)s).animationList;
  //  for(List<IAnimation> anim : animaMap.values()) {

  //  }
  //  //write svg format of animations here

  //}
}
