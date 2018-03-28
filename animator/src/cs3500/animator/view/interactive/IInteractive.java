package cs3500.animator.view.interactive;

import cs3500.animator.view.IView;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;

public interface IInteractive extends IView {

  void setSpeed(int speed);

  void start();

  void pause();

  void reset();

  void resume();

  void export();

  void setLoop(boolean loop);

  void setListeners(ActionListener button, ChangeListener slider);


}
