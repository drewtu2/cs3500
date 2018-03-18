package cs3500.animator.view;

/**
 * Class representing the SVG view of an animator.
 */
public class SVGView implements IView {

  Appendable output;

  public SVGView(Appendable out) {
    if (out == null) {
      throw new NullPointerException();
    }

    out = output;
  }

  @Override
  public void show(IModelView model, int tempo) {

  }
}
