package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * Class to represent a mock view class to test the controller.
 */
public class MockView implements InterfaceView {
  private final StringBuilder log;

  /**
   * Constructor to initialise the log.
   *
   * @param log String builder log.
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void takeInput() {
    log.append("Input ");
  }

  @Override
  public void exitProgram() {
    log.append("Quit ");
  }

  @Override
  public void errorWhileReading() {
    log.append("Error ");
  }

  @Override
  public void wrongCommand() {
    log.append("Wrong command ");
  }

  @Override
  public void wrongPath() {
    log.append("Wrong Path ");
  }

  @Override
  public void commandNotSupported() {
    log.append("Command not supported ");
  }

  @Override
  public void runFromCommandLine() {
    log.append("Run From Command ");
  }


  @Override
  public void addButtonFeatures(Features features) {
    log.append("Button Features Add ");
  }

  @Override
  public void setDisplay() {
    log.append("Display Set ");
  }

  @Override
  public void displayImage(BufferedImage bufferedImage) {
    log.append("Display Image ");
  }

  @Override
  public BufferedImage getBImage() {
    log.append("Send BImage ");
    return new BufferedImage(1, 1, 3);
  }

  @Override
  public String openJFileChooser(String currentDirectory) {
    log.append("Open JFileChooser ");
    return "koala_test.jpeg";
  }

  @Override
  public String saveJFileChooser() {
    log.append("Save JFileChooser ");
    return "testimg.png";
  }

  @Override
  public String getSelectedItem() {
    log.append("Get Selected Item ");
    return "Blur";
  }

  @Override
  public String getSecondaryInput(String s) {
    log.append("Get Secondary Input ");
    return null;
  }

  @Override
  public void displayHistogram(BufferedImage bufferedImage) {
    log.append("Display Histogram ");
  }

  @Override
  public void addSliderFeatures(Features features) {
    log.append("Add Slider ");
  }

  @Override
  public int getSliderValue() {
    log.append("Get Slider Value ");
    return 20;
  }

  @Override
  public boolean isSliderSelected() {
    log.append("Slider Select ");
    return true;
  }

  @Override
  public void displayMessagePopup(String message) {
    log.append("Msg Popup ");
  }

  @Override
  public void addCheckboxFeatures(Features features) {
    log.append("Checkbox ");
  }

  @Override
  public void enableSlider() {
    log.append("Enable Slider ");
  }

  @Override
  public void disableSlider() {
    log.append("Disable Slider ");
  }

  @Override
  public void unTickCheckbox() {
    log.append("UnTick Checkbox ");
  }

  @Override
  public boolean getTickValue() {
    log.append("Tick Value");
    return true;
  }

  @Override
  public boolean popupConfirmationBox() {
    log.append("Popup Box");
    return true;
  }
}

