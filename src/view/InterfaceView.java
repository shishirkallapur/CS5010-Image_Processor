package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * Interface to represent the view component of the MVC Design.
 */
public interface InterfaceView {

  /**
   * Method to print the prompt for taking an input from the user.
   */
  void takeInput();

  /**
   * Method to print status when exiting the program.
   */
  void exitProgram();

  /**
   * Method to print the prompt when exception encountered while reading the
   * file.
   */
  void errorWhileReading();

  /**
   * Method to print the prompt when wrong command is entered.
   */
  void wrongCommand();

  /**
   * Method to print the prompt when wrong filepath is provided to the
   * controller.
   */
  void wrongPath();

  /**
   * Method to print the prompt when a command provided by the user is not
   * supported.
   */
  void commandNotSupported();

  /**
   * Method to indicate the script file is being run from the command line.
   */
  void runFromCommandLine();

  /**
   * Method that links each button to their callback methods.
   *
   * @param features Object of GUI controller.
   */
  void addButtonFeatures(Features features);

  /**
   * Method to set visibility of GUI to visible (True).
   */
  void setDisplay();

  /**
   * Method to display the image in the image panel created.
   *
   * @param bufferedImage bufferedImage of the image.
   */
  void displayImage(BufferedImage bufferedImage);

  /**
   * Getter for the current image (with or without operation performed).
   *
   * @return bufferedImage of the relevant image.
   */
  BufferedImage getBImage();

  /**
   * Method to open a window that allows the user to choose the file to
   * be loaded.
   *
   * @return blank String or the absolute path of the selected file.
   */
  String openJFileChooser(String currentDirectory);

  /**
   * Method to open a window that allows the user to name and choose the
   * location for the file to be saved.
   *
   * @return Blank string or the absolute path of where the file is to be saved.
   */
  String saveJFileChooser();

  /**
   * Method to return the operation selected from the dropdown.
   *
   * @return String that indicates which operation is to be performed.
   */
  String getSelectedItem();

  /**
   * Method that ask the users for value inputs for operations that
   * require it, namely Compress, Levels adjust and Brighten operations.
   *
   * @param s Message specifying what input user must put in.
   * @return User input as String.
   */
  String getSecondaryInput(String s);

  /**
   * Method to display the histogram in the display pane assigned for histograms.
   *
   * @param bufferedImage bufferedImage of the histogram.
   */
  void displayHistogram(BufferedImage bufferedImage);

  /**
   * Method that links the slider to its callback method.
   *
   * @param features Object of GUI controller.
   */
  void addSliderFeatures(Features features);

  /**
   * Method that returns the value that the preview slider is set to between 0 and 100.
   *
   * @return Integer value that the preview slider is set to.
   */
  int getSliderValue();

  /**
   * Method that returns status of the preview slider whether it is selected or not.
   *
   * @return Boolean slider value(true or false).
   */
  boolean isSliderSelected();

  /**
   * Method to throw a popup with the provided relevant message.
   *
   * @param message String message to be shown in popup.
   */
  void displayMessagePopup(String message);

  /**
   * Method that links the checkbox to its callback method.
   *
   * @param features Object of GUI controller.
   */
  void addCheckboxFeatures(Features features);

  /**
   * Method to activate the slider.
   */
  void enableSlider();

  /**
   * Method to disable the slider.
   */
  void disableSlider();

  /**
   * Method to set the value of the preview checkbox to false.
   */
  void unTickCheckbox();

  /**
   * Method that returns status of the preview checkbox whether it is selected or not.
   *
   * @return Boolean checkbox value(true or false).
   */
  boolean getTickValue();

  /**
   * Method that displays a confirmation box when user tries to load an image without
   * cancelling or confirming existing operation.
   *
   * @return Boolean value based on whether user proceeds to go ahead or not.
   */
  boolean popupConfirmationBox();
}
