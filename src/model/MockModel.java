package model;

import java.awt.image.BufferedImage;

/**
 * Class to represent a mock model for testing the controller.
 */
public class MockModel implements ModelInterface2 {
  private final StringBuilder log;

  /**
   * Constructor to initialise the log for testing the controller.
   *
   * @param log Stringbuilder log to keep track of functions.
   */
  public MockModel(StringBuilder log) {
    ImageData imageData;
    this.log = log;
    int[][][] x = new int[1][1][3];
    x[0][0][0] = 1;
    x[0][0][1] = 1;
    x[0][0][2] = 1;
    imageData = new ImageData(x);
  }

  @Override
  public void loadImage(String imageName, BufferedImage bufferedImage) {
    log.append("Load Image ");
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    log.append("Sharpen Image ");
  }


  @Override
  public void loadPPMImage(String imageName, int[][][] temp) {
    log.append("Load PPM Image ");
  }

  @Override
  public StringBuilder savePPMImage(String imageName) {
    log.append("Save PPM Image ");
    return new StringBuilder();
  }

  @Override
  public void split(String imageName, int percent, String newImageName) {
    log.append("Split Image ");

  }

  @Override
  public void combine(String tempName, String imageName, String newImageName) {
    log.append("Combine Split Images ");

  }

  @Override
  public void compressImage(String imageName, int percentage,
                            String newImageName) {
    log.append("Compress Image ");
  }

  @Override
  public void histogram(String imageName, String newImageName) throws Exception {
    log.append("Histogram Image ");
  }

  @Override
  public void colorCorrect(String imageName, String newImageName) {
    log.append("ColorCorrect Image ");
  }

  @Override
  public void levelAdjust(String imageName, int black, int mid, int white,
                          String newImageName) {
    log.append("LevelAdjust Image ");
  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    log.append("Blur Image ");
  }

  @Override
  public void combineImage(String newImageName, String redImage,
                           String greenImage, String blueImage) {
    log.append("Combine Image ");
  }

  @Override
  public void flipImageVertical(String imageName, String newImageName) {
    log.append("Flip Vertical ");
  }

  @Override
  public void flipImageHorizontal(String imageName, String newImageName) {
    log.append("Flip Horizontal ");
  }

  @Override
  public void greenImage(String imageName, String newImageName) {
    log.append("Green Image ");
  }

  @Override
  public void blueImage(String imageName, String newImageName) {
    log.append("Blue Image ");
  }

  @Override
  public void redImage(String imageName, String newImageName) {
    log.append("Red Image ");
  }

  @Override
  public void valueImage(String imageName, String newImageName) {
    log.append("Value Image ");
  }

  @Override
  public void intensityImage(String imageName, String newImageName) {
    log.append("Intensity Image ");
  }

  @Override
  public void lumaImage(String imageName, String newImageName) {
    log.append("Luma Image ");
  }

  @Override
  public void sepiaImage(String imageName, String newImageName) {
    log.append("Sepia Image ");
  }

  @Override
  public void brightenImage(String imageName, String newImageName, Integer value) {
    log.append("Brighten Image ");
  }

  @Override
  public BufferedImage saveImage(String imageName) {
    log.append("Save Image ");
    return new BufferedImage(1, 1, 3);
  }

  @Override
  public BufferedImage getPreviewImage(BufferedImage originalImage, BufferedImage operatedImage,
                                       int percent) {
    log.append("Preview");
    return new BufferedImage(1, 1, 3);
  }

  /**
   * Method to dither an image. This method takes in the image object and
   * newImageName as the parameters and performs dither operation on the image
   * and stores the new image in the map with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for sharpened image.
   */
  @Override
  public void ditherImage(String imageName, String newImageName) throws Exception {
    log.append("Dither");
  }
}
