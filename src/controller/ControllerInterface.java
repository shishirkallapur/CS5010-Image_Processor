package controller;


/**
 * Interface to represent the controller component of the MVC Design. It is
 * in charge of directing the flow of the program and controls its processing
 * aspect.
 */
public interface ControllerInterface {

  /**
   * Method to represent the actual controller and controls both the model
   * and the view components of the MVC design.  It takes input from the user
   * and according to the command provided, informs the model to perform
   * certain image processing operations. It also has the capability to take
   * input from the user in the form of a script. It can also send things to be
   * displayed to the user to the view component.
   *
   */
  void executeController() throws Exception;
}