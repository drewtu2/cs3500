package cs3500.animator.provider.controller;

import cs3500.animator.provider.adapter.ProviderFactory;
import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.view.IInteractiveView;
import cs3500.animator.provider.view.IView;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

/**
 * Represents controller for the hybrid view that controls interactive user changes.
 */
public class HybridViewController implements IInteractiveController {

  private IInteractiveView hView;
  private List<IAnimation> animations;
  private List<IShape> shapes;
  private Map<IShape, Integer> shapeOrder;
  private double tempo;
  private double tickNumber;
  private Timer timer;
  private boolean playing;
  private boolean looping;


  /**
   * Constructs a HybridViewController with the given inputs.
   *
   * @param hView the hybrid view to use.
   * @param animations a list of animations to apply.
   * @param shapes the list of shapes in this animation.
   * @param shapeOrder the order of the shapes.
   * @param tempoIn the speed in.
   */
  public HybridViewController(IInteractiveView hView, List<IAnimation> animations,
      List<IShape> shapes, Map<IShape, Integer> shapeOrder,
      double tempoIn) {
    this.hView = hView;
    this.animations = animations;
    this.shapes = shapes;
    this.shapeOrder = shapeOrder;
    this.tempo = tempoIn;
    this.playing = false;
    this.looping = false;

    int drawRate = 50;
    this.tickNumber = 0;

    Collections.sort(shapes, (IShape o1, IShape o2) -> {
      if (o1.getEndTime() == o2.getEndTime()) {
        return 0;
      }
      return o1.getEndTime() - o2.getEndTime();
    });

    int endTick = shapes.get(shapes.size() - 1).getEndTime();

    Collections.sort(shapes, (IShape o1, IShape o2) -> {
      return shapeOrder.get(o1) - shapeOrder.get(o2);
    });

    Collections.sort(animations);
    timer = new Timer(drawRate,
        (ActionEvent e) -> {
          //System.out.println("Timer Event fired");
          // Only perform these actions if we're running...
          if (playing) {
            this.tickNumber += (drawRate * this.tempo / 1000.0);

            for (IAnimation animation : animations) {
              setAnimation(animation, this.tickNumber);
            }

            if (looping && tickNumber > endTick) {
              onRestartClicked();
            }

            hView.refresh();
          }
        });
  }

  @Override
  public void onStartClicked() {
    System.out.println("Start Clicked!");
    timer.restart();
    this.playing = true;

  }

  @Override
  public void onPauseClicked() {
    System.out.println("Paused Clicked!");
    this.playing = false;
  }

  @Override
  public void onResumeClicked() {
    System.out.println("Resume Clicked!");
    this.playing = true;
  }

  @Override
  public void onRestartClicked() {
    System.out.println("Restart!");
    this.tickNumber = 0;

    for (IShape shape : this.shapes) {
      shape.reset();
    }
  }

  @Override
  public void onLoopingChanged(boolean selected) {
    System.out.println("Looping Clicked!");
    this.looping = selected;
  }

  @Override
  public void onTempoChanged(double tempo) {
    System.out.println("Tempo set to " + Double.toString(tempo));
    this.tempo = tempo;
    hView.updateTempoLabel(tempo);
  }

  @Override
  public void onShapeVisibilityChanged(IShape shape, boolean selected) {
    System.out.println("Visibility changed");

    if (selected) {
      shapes.add(shape);
    } else {
      shapes.remove(shape);
    }

    Collections.sort(shapes, (IShape o1, IShape o2) -> {
      return shapeOrder.get(o1).compareTo(shapeOrder.get(o2));
    });

  }

  @Override
  public void onExportClicked(String filename) {
    try {
      Appendable myApp = new FileWriter(filename);

      List<IAnimation> newAnimations = getVisibleAnimationCopies(animations);

      Collections.sort(newAnimations);
      IView view = ProviderFactory
          .getView(newAnimations, "providersvg", myApp, (int) this.tempo);

      view.writeAnimatorDescription();
      ((FileWriter) myApp).flush();
      ((FileWriter) myApp).close();

    } catch (IOException e) {
      hView.showExportError(e.getMessage());
      System.err.println(e);
    }
  }

  @Override
  public void startAnimation() {
    System.out.println("Animation changed");
    timer.start();
  }

  @Override
  public List<IShape> getVisibleShapes() {
    return shapes;
  }


  /**
   * Returns negative if the animation ends before the given test time or positive if the animation
   * occurs after the given start time. Returns 0 if test occurs during.
   *
   * @param animation the animation
   * @param test the test time
   * @return positive or negative int
   */
  private int compare(IAnimation animation, double test) {
    if (animation.getStartTime() > test) {
      return -1;
    } else if (animation.getEndTime() < test) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Applies the animation if applicable.
   *
   * @param animation the animation to apply
   * @param tick the tick we're currently on
   */
  private void setAnimation(IAnimation animation, double tick) {
    int comparision = compare(animation, tick);

    if (comparision == 1) {
      animation.animate(animation.getShape());
    } else if (comparision == 0) {
      animation.animate((int) tick);
    } else {
      // Do nothing, this animation hasn't started yet
      return;
    }
  }

  /**
   * Produces a sorted list of visible animaitons of all the animaitons in this model. Each shape is
   * cloned so that it can be modified.
   *
   * @return a sorted list of copied animaitons
   */
  private List<IAnimation> getVisibleAnimationCopies(List<IAnimation> animationsIn) {
    List<IAnimation> newAnimations = new ArrayList<IAnimation>();
    Map<String, IShape> shapesTracker = new HashMap<>();
    double currentX;
    double currentY;

    /*
    for (IAnimation animation : animations_in) {
      IShape tempShape = animation.getShape();

      if (!shapesTracker.containsKey(tempShape.getName())) {
        IColor currentColor = tempShape.getColor();
        Posn currentPosn = tempShape.getLocation();
        currentX = tempShape.getX();
        currentY = tempShape.getY();

        tempShape.reset();
        shapesTracker.put(tempShape.getName(), tempShape.clone());

        tempShape.updateSize(currentX, currentY);
        tempShape.changeColor(currentColor);
        tempShape.move(currentPosn);
      }

      if (animation instanceof Move) {
        newAnimations.add(new Move(animation.getStartTime(), animation.getEndTime(),
            shapesTracker.get(tempShape.getName()), ((Move) animation).getDestination()));
      } else if (animation instanceof ChangeColor) {
        newAnimations.add(new ChangeColor(animation.getStartTime(), animation.getEndTime(),
            shapesTracker.get(tempShape.getName()), ((ChangeColor) animation).getTarget()));
      } else if (animation instanceof Scale) {
        newAnimations.add(new Scale(animation.getStartTime(), animation.getEndTime(),
            shapesTracker.get(tempShape.getName()), ((Scale) animation).getScaleX(),
            ((Scale) animation).getScaleY()));
      }
    }

    return newAnimations;
    */

    for (IShape shape : this.shapes) {
      shape.reset();
    }

    for (IAnimation animation : animations_in) {
      if (this.shapes.contains(animation.getShape())) {
        newAnimations.add(animation);
      }
    }
    return newAnimations;
  }
}
