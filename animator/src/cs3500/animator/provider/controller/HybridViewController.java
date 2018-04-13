package cs3500.animator.provider.controller;

import java.util.List;
import java.util.Map;

import cs3500.animator.provider.model.animation.AbstractAnimation;
import cs3500.animator.provider.model.shape.AbstractShape;
import cs3500.animator.provider.view.HybridView;

/**
 * Represents controller for the hybrid view that controls interactive user changes.
 */
public class HybridViewController implements IInteractiveController {

  private HybridView hView;
  private List<AbstractAnimation> animations;
  private List<AbstractShape> shapes;
  private Map<AbstractShape, Integer> shapeOrder;
  private double tempo;


  public HybridViewController(HybridView hView, List<AbstractAnimation> animations,
                              List<AbstractShape> shapes, Map<AbstractShape, Integer> shapeOrder,
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
  public void onShapeVisibilityChanged(AbstractShape shape, boolean selected) {

  }

  @Override
  public void onExportClicked(String filename) {

  }

  @Override
  public void startAnimation() {

  }

  @Override
  public List<AbstractShape> getVisibleShapes() {
    return null;
  }
}
