package cs3500.animator.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

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
  void setInputFile(String inputFile);

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
   * @throws FileNotFoundException
   */
  IController build() throws FileNotFoundException, IOException;

  /**
   * Constructs the controller from the input arguments. Acceptable arguments are as follows:
   * - if: input file
   * - iv: input view
   * - o: output file
   * - speed: speed
   * @param args list of arguments
   * @return a controller constructed from the given arguments
   * @throws FileNotFoundException
   */
  IController buildFromInputArgs(String[] args) throws FileNotFoundException, IOException;

}
