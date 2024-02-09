import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.Controller;
import model.MockModel;
import view.MockView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the controller and its methods.
 */
public class TestController {

  @Test
  public void testControllerRunScript() throws Exception {
    String str = "run test_script.txt";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    String expected = "Input Load Image Brighten Image Sepia Image Luma Image Value Image" +
            " Intensity Image Red Image Green Image Blue Image Flip Horizontal Flip Vertical" +
            " Save Image Save Image Save Image Save Image Save Image Save Image Save Image" +
            " Save Image Save Image Save Image Input ";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testLoadController() throws Exception {
    String str = "load Koala.jpeg koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Load Image Input ", log.toString());
  }

  @Test
  public void testLoadPPMController() throws Exception {
    String str = "load Koala.ppm koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Load PPM Image Input ", log.toString());
  }

  @Test
  public void testSavePPMController() throws Exception {
    String str = "save Koala123.ppm koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Save PPM Image Input ", log.toString());
  }

  @Test
  public void testControllerBlur() throws Exception {
    String str = "blur koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Blur Image Input ", log.toString());
  }

  @Test
  public void testControllerSharpen() throws Exception {
    String str = "sharpen koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Sharpen Image Input ", log.toString());
  }

  @Test
  public void testControllerRed() throws Exception {
    String str = "red-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Red Image Input ", log.toString());
  }

  @Test
  public void testControllerBlue() throws Exception {
    String str = "blue-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Blue Image Input ", log.toString());
  }

  @Test
  public void testControllerGreen() throws Exception {
    String str = "green-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Green Image Input ", log.toString());
  }

  @Test
  public void testControllerVFlip() throws Exception {
    String str = "vertical-flip koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Flip Vertical Input ", log.toString());
  }

  @Test
  public void testControllerHFlip() throws Exception {
    String str = "horizontal-flip koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Flip Horizontal Input ", log.toString());
  }

  @Test
  public void testControllerRGBSplit() throws Exception {
    String str = "rgb-split koala.png koala-red koala-green koala-blue";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);
    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Red Image Green Image Blue Image Input ",
            log.toString());
  }

  @Test
  public void testControllerValue() throws Exception {
    String str = "value-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Value Image Input ", log.toString());
  }

  @Test
  public void testControllerIntensity() throws Exception {
    String str = "intensity-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Intensity Image Input ", log.toString());
  }

  @Test
  public void testControllerLuma() throws Exception {
    String str = "luma-component koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Luma Image Input ", log.toString());
  }

  @Test
  public void testControllerSepia() throws Exception {
    String str = "sepia koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Sepia Image Input ", log.toString());
  }

  @Test
  public void testControllerRGBCombine() throws Exception {
    String str = "rgb-combine koala koala-red koala-green koala-blue";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Combine Image Input ",
            log.toString());
  }

  @Test
  public void testControllerCompress() throws Exception {
    String str = "compress 20 koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Compress Image Input ", log.toString());
  }

  @Test
  public void testControllerHistogram() throws Exception {
    String str = "histogram koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Histogram Image Input ", log.toString());
  }

  @Test
  public void testControllerColorCorrect() throws Exception {
    String str = "color-correct koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input ColorCorrect Image Input ", log.toString());
  }

  @Test
  public void testControllerLevelAdjust() throws Exception {
    String str = "levels-adjust 5 10 15 koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input LevelAdjust Image Input ", log.toString());
  }

  @Test
  public void testControllerBlurSplit() throws Exception {
    String str = "blur koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Blur Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerIntensitySplit() throws Exception {
    String str = "intensity-component koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Intensity Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerLumaSplit() throws Exception {
    String str = "luma-component koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Luma Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerSepiaSplit() throws Exception {
    String str = "sepia koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Sepia Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerSharpenSplit() throws Exception {
    String str = "sharpen koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Sharpen Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerValueSplit() throws Exception {
    String str = "value-component koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image Value Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerColorCorrectSplit() throws Exception {
    String str = "color-correct koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image ColorCorrect Image Combine Split " +
            "Images Input ", log.toString());
  }

  @Test
  public void testControllerLevelAdjustSplit() throws Exception {
    String str = "levels-adjust 5 10 15 koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Split Image LevelAdjust Image Combine Split Images Input ", log.toString());
  }

  @Test
  public void testControllerCommandLine() throws Exception {
    String str = "";
    String[] commandLineArgs = {"-file", "test_script.txt"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    String expected = "Run From Command Load Image Brighten Image Sepia Image Luma Image Value" +
            " Image Intensity Image Red Image Green Image Blue Image Flip Horizontal Flip " +
            "Vertical Save Image Save Image Save Image Save Image Save Image Save Image" +
            " Save Image Save Image Save Image Save Image Quit ";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testControllerInvalidCommand() throws Exception {
    String str = "test abc";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Command not supported Input ", log.toString());
  }


  @Test
  public void testExceptionReadScript() throws Exception {
    String str = "run str.txt";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Error Input ", log.toString());
  }

  @Test()
  public void testExceptionLoadPPM() throws Exception {
    String str = "load Koal.ppm koal";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Error Input ", log.toString());
  }

  @Test()
  public void testExceptionLoadOther() throws Exception {
    String str = "load Koal.png koal";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Error Input ", log.toString());
  }

  @Test(expected = Exception.class)
  public void testExceptionSaveWrongFilePath() throws Exception {
    String str = "save /images/sss koal";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    fail("This should not be printed!");
  }


  @Test()
  public void testExceptionLuma() throws Exception {
    String str = "luma-component koala.png ";
    Reader in = new StringReader(str);
    String[] commandLineArgs = new String[]{"-text"};

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionBrighten() throws Exception {
    String str = "brighten koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionLoad() throws Exception {
    String str = "load koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test(expected = Exception.class)
  public void testExceptionSave() throws Exception {
    String str = "save koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    fail("This should not be printed!");
  }

  @Test()
  public void testExceptionSepia() throws Exception {
    String str = "sepia koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionValue() throws Exception {
    String str = "value-component koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionIntensity() throws Exception {
    String str = "intensity-component koala.png ";
    Reader in = new StringReader(str);
    String[] commandLineArgs = new String[]{"-text"};

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionSharpen() throws Exception {
    String str = "sharpen koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionBlur() throws Exception {
    String str = "blur koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionRGBSplit() throws Exception {
    String str = "rgb-split koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionRGBCombine() throws Exception {
    String str = "rgb-combine koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionVerticalFlip() throws Exception {
    String str = "vertical-flip koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionHorizontalFlip() throws Exception {
    String str = "horizontal-flip koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionRedComponent() throws Exception {
    String str = "red-component koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionGreenComponent() throws Exception {
    String str = "green-component koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionBlueComponent() throws Exception {
    String str = "blue-component koala.png ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionBlur2() throws Exception {
    String str = "blur koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionBlur3() throws Exception {
    String str = "blur koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust2() throws Exception {
    String str = "levels-adjust 5 10 256 koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust3() throws Exception {
    String str = "levels-adjust 10 5 25 koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust4() throws Exception {
    String str = "levels-adjust 5 10 25 koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust5() throws Exception {
    String str = "levels-adjust 10 15 25 koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust6() throws Exception {
    String str = "levels-adjust 10 5 25 koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust7() throws Exception {
    String str = "levels-adjust 10 15 256 koala.png koala split 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorAdjust8() throws Exception {
    String str = "levels-adjust 10 15 256 koala.png koala split";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorCorrect2() throws Exception {
    String str = "color-correct koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorCorrect3() throws Exception {
    String str = "color-correct koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionColorCorrect4() throws Exception {
    String str = "color-correct koala.png koala split ";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionCompress2() throws Exception {
    String str = "compress 150 koala.png koala";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionCompress3() throws Exception {
    String str = "compress 50 koala.png koala asdasdf";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionHistogram() throws Exception {
    String str = "histogram koala.png koala asdasdf";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionIntensityComponent() throws Exception {
    String str = "intensity-component koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionIntensityComponent2() throws Exception {
    String str = "intensity-component koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionLumaComponent() throws Exception {
    String str = "luma-component koala.png koala splt 50";
    Reader in = new StringReader(str);
    String[] commandLineArgs = new String[]{"-text"};

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionLumaComponent2() throws Exception {
    String str = "luma-component koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionValueComponent() throws Exception {
    String str = "value-component koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionValueComponent2() throws Exception {
    String[] commandLineArgs = new String[]{"-text"};
    String str = "value-component koala.png koala split 150";
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionSepiaComponent() throws Exception {
    String[] commandLineArgs = new String[]{"-text"};
    String str = "sepia koala.png koala splt 50";
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionSepiaComponent2() throws Exception {
    String str = "sepia koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionSharpenComponent() throws Exception {
    String str = "sharpen koala.png koala splt 50";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }

  @Test()
  public void testExceptionSharpenComponent2() throws Exception {
    String str = "sharpen koala.png koala split 150";
    String[] commandLineArgs = new String[]{"-text"};
    Reader in = new StringReader(str);

    StringBuilder log = new StringBuilder();
    Controller cont = new Controller(new MockModel(log), new MockView(log), in, commandLineArgs);
    cont.executeController();
    assertEquals("Input Wrong command Input ", log.toString());
  }
}
