package controller;

/**
 * Interface to represent the controller component of the MVC Design. It is
 * in charge of directing the flow of the program and controls its processing
 * aspect.
 */
public interface Features {

  /**
   * Method to initialise the GUI and make all the necessary features
   * like buttons, sliders, checkboxes and display panes visible in the GUI.
   */
  void execute();

  /**
   * Button that controls the load image functionality. It throws a popup
   * after successfully loading the image.
   */
  void loadButton();

  /**
   * Button that controls the save image functionality. It throws a popup
   * after successfully saving the image.
   */
  void saveButton();

  /**
   * Button to control applying the selected operation on the image. It
   * throws a popup after successfully performing the operation on the image.
   */
  void applyButton();

  /**
   * Button to control the operation cancellation functionality. It throws a
   * popup after successfully cancelling the operation that was performed on
   * the image after clicking the Apply button. The image reverts back to the
   * original and allows for other operations to be performed on it.
   */
  void cancelButton();

  /**
   * Button to control the operation confirmation functionality. It throws a
   * popup after successfully confirming the operation that was performed on
   * the image after clicking the Apply button. The image in the display pane
   * stays the operated image and allows for other operations to be performed on it.
   */
  void confirmButton();

  /**
   * Slider that controls the preview functionality. Slider only activates if the
   * slider checkbox is selected and the operation is permitted for preview.
   */
  void sliderButton();

  /**
   * Checkbox that controls activation of slider for preview. When checked,
   * it allows slider to be used for preview for operations that support preview.
   */
  void checkboxButton();
}
