package cs3500.animator.provider.controller;

import java.util.List;
import java.util.Map;

import cs3500.animator.provider.object.animation.AbstractAnimation;
import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.shape.AbstractShape;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.view.HybridView;

/**
 * Represents controller for the hybrid view that controls interactive user changes.
 */
public class HybridViewController implements IInteractiveController {

  private HybridView hView;
  private List<IAnimation> animations;
  private List<IShape> shapes;
  private Map<IShape, Integer> shapeOrder;
  private double tempo;


  public HybridViewController(HybridView hView, List<IAnimation> animations,
                              List<IShape> shapes, Map<IShape, Integer> shapeOrder,
                              double tempo){
    this.hView = hView;
    this.animations = animations;
    this.shapes = shapes;
    this.shapeOrder = shapeOrder;
    this.tempo = tempo;
  }

  @Override
  public void onStartClicked() {

  }

  @Override
  public void onPauseClicked() {

  }

  @Override
  public void onResumeClicked() {

  }

  @Override
  public void onRestartClicked() {

  }

  @Override
  public void onLoopingChanged(boolean selected) {

  }

  @Override
  public void onTempoChanged(double tempo) {

  }

  @Override
  public void onShapeVisibilityChanged(IShape shape, boolean selected) {

  }

  @Override
  public void onExportClicked(String filename) {

  }

  @Override
  public void startAnimation() {

  }

  @Override
  public List<IShape> getVisibleShapes() {
    return null;
  }
}
