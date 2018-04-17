package cs3500.animator.provider.adapter;

import static util.MyUtil.checkNull;

import cs3500.animator.animation.AnimationType;
import cs3500.animator.model.IModelView;
import cs3500.animator.provider.model.IAnimatorModel;
import cs3500.animator.provider.object.Color;
import cs3500.animator.provider.object.IColor;
import cs3500.animator.provider.object.Posn;
import cs3500.animator.provider.object.animation.ChangeColor;
import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.animation.Move;
import cs3500.animator.provider.object.animation.Scale;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IPosition;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.dimension.IDimension;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for converting our model implementation to our provider's implementation.
 */
public class ModelAdapter implements IAnimatorModel {

  protected IModelView myMv;
  protected Map<String, IShape> shapeMap;
  protected Map<IShape, Integer> shapeOrder;
  protected List<IAnimation> animationList;

  /**
   * Constructor for the model view.
   *
   * @param inputMv the model view.
   */
  public ModelAdapter(cs3500.animator.model.IAnimatorModel inputMv) {
    checkNull(inputMv);

    myMv = inputMv;
    shapeMap = new HashMap<>();
    getShapeMapFromMv();
    shapeOrder = getShapeOrderFromMv();
    animationList = getAllIAnimations();
  }

  @Override
  public void addAnimation(IAnimation anim) throws IllegalArgumentException {
    checkNull(anim);

    // Check to make sure this is a valid animaiton
    for (IAnimation animation : animationList) {
      if (anim.conflictsWithAnimation(animation)) {
        throw new IllegalArgumentException("Overlap with another animation. cannot add!");
      }
    }

    // If we got here, we can safely add.
    animationList.add(anim);
  }

  @Override
  public String getAnimatorDescription() {
    StringBuilder out = new StringBuilder();

    out.append("Shapes:\n");

    for (IShape shape : shapeMap.values()) {
      out.append(shape.toString());
    }

    for (IAnimation animation : animationList) {
      out.append(animation.toString(animation.getShape()));
    }

    return out.toString();
  }

  @Override
  public List<IAnimation> getAnimations() {
    Collections.sort(animationList);
    return animationList;
  }

  @Override
  public List<IShape> getShapes() {
    ArrayList shapesOut = new ArrayList(shapeOrder.keySet());
    Collections.sort(shapesOut);

    return shapesOut;
  }

  @Override
  public void setShapeOrder(Map<IShape, Integer> shapeOrder) {
    this.shapeOrder = shapeOrder;
  }

  @Override
  public Map<IShape, Integer> getShapeOrder() {
    return shapeOrder;
  }

  /**
   * Returns a map of IShapes to the keys where key is the shape name.
   */
  private Map<String, IShape> getShapeMapFromMv() {
    Map<String, IAnimatedShape> animatedShapes = myMv.getFullState();
    Map<String, IShape> order = new HashMap<>();

    for (String key : animatedShapes.keySet()) {
      order.put(key, convertShape(animatedShapes.get(key)));
    }

    return order;
  }

  /**
   * Returns a map of IShapes to the order its produced.
   */
  private Map<IShape, Integer> getShapeOrderFromMv() {
    Map<String, IAnimatedShape> animatedShapes = myMv.getFullState();
    Map<IShape, Integer> order = new HashMap<>();

    for (String key : animatedShapes.keySet()) {
      order.put(shapeMap.get(key), animatedShapes.get(key).getCreationIndex());
    }

    return order;
  }

  /**
   * Converts our IRGB color object to provider implementation.
   *
   * @param ourColor our RGB color.
   * @return the converted color.
   */
  private IColor convertColor(IRGBColor ourColor) {
    checkNull(ourColor);

    return new Color(ourColor.getRed(), ourColor.getGreen(), ourColor.getBlue());
  }

  /**
   * Converts the provider Color into our RGBColor.
   *
   * @param providerColor our RGB color.
   * @return the converted color.
   */
  private IRGBColor convertColor(IColor providerColor) {
    checkNull(providerColor);

    return new RGBColor((float) providerColor.getRed(), (float) providerColor.getGreen(),
        (float) providerColor.getBlue());
  }

  /**
   * Converts our IPosition object to provider implementation.
   *
   * @param ourPosn our IPosition object
   * @return the converted Posn
   */
  private Posn convertPosn(IPosition ourPosn) {
    checkNull(ourPosn);

    return new Posn(ourPosn.getX(), ourPosn.getY());
  }

  /**
   * Converts the provider implementation of position to our implementation.
   *
   * @param providerPosn IPosition object
   * @return the converted Posn
   */
  private IPosition convertPosn(Posn providerPosn) {
    checkNull(providerPosn);

    return new Position2D((float) providerPosn.getX(), (float) providerPosn.getY());
  }

  /**
   * Converts our IPosition object to provider implementation. If the shape has not been seen
   * before, add it to the shape map. If the shape name already exists in the shape map, return the
   * reference from the ShapeMap
   */
  private IShape convertShape(IAnimatedShape shape) {
    checkNull(shape);

    // Short circuit: if the shape already exists in the map, just return it directly
    if (shapeMap != null && shapeMap.containsKey(shape.getName())) {
      return shapeMap.get(shape.getName());
    }

    Map<AnimationType, List<cs3500.animator.animation.IAnimation>> animations = shape
        .getAnimations();

    // Start and end time are indicated in our model by Create and Destroy animations. Ensure the
    // given shape has those.
    if (animations.get(AnimationType.CREATE).size() != 1) {
      throw new IllegalStateException("Malformed animation, missing creation.");
    }

    if (animations.get(AnimationType.DESTROY).size() != 1) {
      throw new IllegalStateException("Malformed animation, missing creation.");
    }

    // Pull together everything needed for creation of the new shape
    int startTick = animations.get(AnimationType.CREATE).get(0).getStartTime();
    int endTick = animations.get(AnimationType.DESTROY).get(0).getStartTime();
    Posn thisPosn = convertPosn(shape.getPosition());
    IColor thisColor = convertColor(shape.getColor());
    WidthHeightDim thisDim = (WidthHeightDim) shape.getDimension();

    // Add the shape to our map so we can access the reference later (without having to create it again)
    shapeMap.put(shape.getName(), ProviderShapeFactory
        .getShape(shape.getType(), shape.getName(), startTick, endTick, thisPosn, thisColor,
            thisDim.getWidth(), thisDim.getHeight()));

    // Return the shape (we stored it in the map soooo....)
    return shapeMap.get(shape.getName());

  }

  /**
   * Returns a list of provider IAnimations from a single IAnimatedShape.
   *
   * @return a list of IAnimations.
   */
  private List<IAnimation> getIAnimationsFromShape(IAnimatedShape shape) {
    List<IAnimation> loa = new ArrayList<>();

    // Grab the move animaitons
    if (shape.getAnimations().get(AnimationType.MOVE) != null) {
      for (cs3500.animator.animation.IAnimation animation : shape.getAnimations()
          .get(AnimationType.MOVE)) {
        Posn target = convertPosn(animation.getEndPos());

        loa.add(
            new Move(animation.getStartTime(), animation.getEndTime(), convertShape(shape),
                target));
      }
    }

    // Grab the color animations
    if (shape.getAnimations().get(AnimationType.COLOR) != null) {
      for (cs3500.animator.animation.IAnimation animation : shape.getAnimations()
          .get(AnimationType.COLOR)) {
        IColor target = convertColor((animation).getEndColor());

        loa.add(
            new ChangeColor(animation.getStartTime(), animation.getEndTime(), convertShape(shape),
                target));

      }
    }

    // Grab the scale animations
    if (shape.getAnimations().get(AnimationType.SCALE) != null) {
      for (cs3500.animator.animation.IAnimation animation : shape.getAnimations()
          .get(AnimationType.SCALE)) {
        IDimension start = animation.getStartDimension();
        IDimension end = animation.getStartDimension();

        float scaleWidth = end.getWidth() / start.getWidth();
        float scaleHeight = end.getHeight() / start.getHeight();

        loa.add(
            new Scale(animation.getStartTime(), animation.getEndTime(), convertShape(shape),
                scaleWidth, scaleHeight));

      }
    }

    return loa;
  }

  private List<IAnimation> getAllIAnimations() {
    Map<String, IAnimatedShape> animatedShapes = myMv.getFullState();
    List<IAnimation> loa = new ArrayList<>();

    for (IAnimatedShape shape : animatedShapes.values()) {
      loa.addAll(getIAnimationsFromShape(shape));
    }

    return loa;
  }

}
