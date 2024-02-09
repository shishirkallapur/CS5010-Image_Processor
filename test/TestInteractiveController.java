import org.junit.Test;

import controller.Features;
import controller.InteractiveController;
import model.MockModel;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the interactive controller and its methods.
 */
public class TestInteractiveController {
  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.loadButton();
    assertEquals("Open JFileChooser Load Image Histogram Image " +
            "Save Image Save Image Display Image Display Histogram Msg Popup ", log.toString());
  }

  @Test
  public void testSave() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.saveButton();
    assertEquals("Send BImage Save JFileChooser Load Image " +
            "Save Image Msg Popup ", log.toString());
  }

  @Test
  public void testApplyBlur() {
    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.loadButton();
    features.applyButton();
    String expectedLog = "Open JFileChooser Load Image Histogram " +
            "Image Save Image Save Image Display Image Display Histogram " +
            "Msg Popup Get Selected Item Send BImage Load Image Blur Image Save " +
            "Image Histogram Image Histogram Image Save Image Save Image Slider " +
            "Select Display Image Display Histogram Msg Popup ";

    assertEquals(expectedLog, log.toString());
  }




  @Test
  public void testCancel() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.cancelButton();
    assertEquals("Disable Slider UnTick Checkbox Display Image" +
            " Display Histogram Msg Popup ", log.toString());
  }

  @Test
  public void testConfirm() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.confirmButton();
    assertEquals("Disable Slider UnTick Checkbox Display Image" +
            " Display Histogram Msg Popup ", log.toString());
  }

  @Test
  public void testSlider() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.applyButton();
    features.sliderButton();
    assertEquals("Get Selected Item Send BImage Load Image Blur Image Save Image " +
            "Histogram Image Histogram Image Save Image Save Image Slider Select Display" +
            " Image Display Histogram Msg Popup Get Slider Value Slider Select PreviewLoad " +
            "Image Histogram Image Save Image Display Image Display Histogram ", log.toString());
  }

  @Test
  public void testCheckbox() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.checkboxButton();
    assertEquals("Tick ValueEnable Slider ", log.toString());
  }

  @Test
  public void testExecute() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.execute();
    assertEquals("Display Set Button Features Add Add Slider Checkbox " +
            "Disable Slider ", log.toString());
  }


  @Test
  public void testMultiple() {

    StringBuilder log = new StringBuilder();
    Features features = new InteractiveController(new MockModel(log), new MockView(log));
    features.loadButton();
    features.applyButton();
    features.confirmButton();
    features.saveButton();
    assertEquals("Open JFileChooser Load Image Histogram Image Save " +
            "Image Save Image Display Image Display Histogram Msg Popup Get Selected " +
            "Item Send BImage Load Image Blur Image Save Image Histogram Image " +
            "Histogram Image Save Image Save Image Slider Select Display Image " +
            "Display Histogram Msg Popup Disable Slider UnTick Checkbox Display " +
            "Image Display Histogram Msg Popup Send BImage Save JFileChooser " +
            "Load Image Save Image Msg Popup ", log.toString());
  }
}
