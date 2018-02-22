package shape;

import java.util.List;
import Animation.AnimationSummary;
import Animation.IAnimation;

public class AnimatedShape implements IAnimatedShape{

  public AnimatedShape(IShape baseShape){

  }

  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {

  }

  @Override
  public IShape stateAt(float t) {
    return null;
  }

  @Override
  public List<AnimationSummary> getSummary() {
    return null;
  }
}
