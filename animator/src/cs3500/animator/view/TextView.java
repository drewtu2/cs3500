package cs3500.animator.view;

public class TextView implements IView{
  private Appendable output;

  public TextView(Appendable outputFile) {
    output  = outputFile;
  }

  @Override
  public void show(int tempo) {

  }
}
