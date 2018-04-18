package cs3500.animator.provider.view;

//import java.awt.*;

import cs3500.animator.provider.controller.IInteractiveController;
import cs3500.animator.provider.object.animation.IAnimation;
import cs3500.animator.provider.object.shape.IShape;
import cs3500.animator.provider.util.NumUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class represents a hybrid view of the animations and shapes. The animation will be played
 * inside a window. Each animation will be displayed as specified by the list of animations and
 * according the the tempo. The user will also be provided with the option to export the view to an
 * svg file.
 */
public class HybridView extends AbstractView implements IInteractiveView {

  private JPanel shapePanel;
  private JSlider slider;
  private JLabel sliderText;
  private JTextField svgFileName;

  /**
   * Constructs a HybridView with the given animations.
   *
   * @param animations the animations associated with this view
   * @param shapes the shapes associated with this view
   * @param shapeOrder the mapping describing the ordering for each shape
   * @param tempo the speed of the animation in ticks per second
   */
  public HybridView(List<IAnimation> animations, List<IShape> shapes,
      Map<IShape, Integer> shapeOrder, double tempo) {
    super(animations, shapes, tempo);
  }

  @Override
  public void animate() {
    this.setVisible(true);
  }

  @Override
  public void setController(IInteractiveController controller) {
    JScrollPane scrollPane;
    JScrollPane checkboxScrollPane;
    JButton start;
    JButton pause;
    JButton resume;
    JButton restart;
    JButton exportToSVG;
    JCheckBox loopCheckBox;
    JPanel buttonPanel;
    JPanel shapeCheckboxPanel;
    Map<JCheckBox, IShape> shapeCheckboxes;
    this.setTitle("Shapes!");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // create a borderlayout with drawing panel in center
    this.setLayout(new BorderLayout());
    shapePanel = new ShapePanel(controller.getVisibleShapes());
    initializeShapePanelSize();
    scrollPane = new JScrollPane(shapePanel);
    scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH + 250, PANEL_HEIGHT + 250));

    this.add(scrollPane, BorderLayout.CENTER);
    start = new JButton("Start");
    pause = new JButton("Pause");
    resume = new JButton("Resume");
    restart = new JButton("Restart");
    loopCheckBox = new JCheckBox("Loop", false);
    sliderText = new JLabel("Tempo: " + String.valueOf(tempo));
    DefaultBoundedRangeModel model = new DefaultBoundedRangeModel((int) tempo, 0, 0,
        Integer.max(100, (int) tempo));
    this.slider = new JSlider(model);

    start.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onStartClicked();
      }
    });
    pause.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onPauseClicked();
      }
    });
    resume.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onResumeClicked();
      }
    });
    restart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onRestartClicked();
      }
    });
    loopCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onLoopingChanged(loopCheckBox.isSelected());
      }
    });
    this.slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        controller.onTempoChanged(slider.getValue());
      }
    });
    buttonPanel = new JPanel();
    buttonPanel.add(start);
    buttonPanel.add(pause);
    buttonPanel.add(resume);
    buttonPanel.add(restart);
    buttonPanel.add(loopCheckBox);
    buttonPanel.add(slider);
    buttonPanel.add(sliderText);
    this.add(buttonPanel, BorderLayout.SOUTH);

    // shape loopCheckBox
    shapeCheckboxPanel = new JPanel();
    shapeCheckboxPanel.setLayout(new BoxLayout(shapeCheckboxPanel, BoxLayout.Y_AXIS));
    shapeCheckboxes = new HashMap<JCheckBox, IShape>();
    for (IShape shape : shapes) {
      JCheckBox currentCheckBox = new JCheckBox(shape.getName(), true);
      shapeCheckboxes.put(currentCheckBox, shape);
      shapeCheckboxPanel.add(currentCheckBox);
      currentCheckBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          controller.onShapeVisibilityChanged(shape, currentCheckBox.isSelected());
        }
      });
    }

    checkboxScrollPane = new JScrollPane(shapeCheckboxPanel);
    checkboxScrollPane.setPreferredSize(new Dimension(150, scrollPane.getHeight()));
    this.add(checkboxScrollPane, BorderLayout.EAST);

    // export to svg
    this.svgFileName = new JTextField("Enter SVG Filename.");
    buttonPanel.add(this.svgFileName);
    exportToSVG = new JButton("Export to SVG");
    exportToSVG.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onExportClicked(svgFileName.getText());
      }
    });
    buttonPanel.add(exportToSVG);

    this.pack();
    this.animate();
  }

  /**
   * Determines the optimal size for the shape panel based on the shapes and where they move. Sets
   * this calculated size as the preferred size of the shape panel.
   */
  private void initializeShapePanelSize() {
    int maxWidth = PANEL_WIDTH;
    int maxHeight = PANEL_HEIGHT;

    for (IShape shape : shapes) {
      maxWidth = Integer.max(maxWidth, NumUtil.round(shape.getLocation().getX()));
      maxHeight = Integer.max(maxHeight, NumUtil.round(shape.getLocation().getY()));
    }

    for (IAnimation animation : animations) {
      if (animation.getType().equalsIgnoreCase("move")) {
        maxWidth = Integer.max(maxWidth, NumUtil.round(animation.getEndPosition().getX()));
        maxHeight = Integer.max(maxHeight, NumUtil.round(animation.getEndPosition().getY()));
      }
    }

    shapePanel.setPreferredSize(new Dimension(maxWidth + 200, maxHeight + 200));
  }

  @Override
  public void updateTempoLabel(double tempo) {
    this.sliderText.setText("Tempo: " + String.valueOf(tempo));
  }

  @Override
  public void showExportError(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
