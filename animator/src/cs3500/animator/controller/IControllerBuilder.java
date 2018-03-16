package cs3500.animator.controller;

import java.io.FileNotFoundException;

/**
 * Interface of a controller builder. The controller builder will construct a builder with the following
 * default values.
 * - speed: 1
 * - o: System.out
 * The input file and the view type MUST be set before construction
 */
public interface IControllerBuilder {

  /**
   * Sets the given input file to create our model from
   * @param inputFile name of the input file
   */
  void setInputFile(String inputFile) throws FileNotFoundException;

  /**
   * Sets the output file to create our model from
   * @param outputFile
   */
  void setOutputFile(String outputFile);


  /**
   * Sets the given input speed.
   * @param speed the input speed.
   */
  void setSpeed(int speed);

  /**
   * Sets the given view type.
   * @param type the view type
   */
  void setView(String type);

  /**
   * Builds the a controller.
   * @return a IController
   */
  IController build();

  /**
   * Constructs the controller from the input arguments. Acceptable arguments are as follows:
   * - if: input file
   * - iv: input view
   * - o: output file
   * - speed: speed
   * @param args list of arguments
   * @return a controller constructed from the given arguments
   */
  IController buildFromInputArgs(String[] args);

}
