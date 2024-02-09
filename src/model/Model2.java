package model;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.operations.FilterOperations;
import model.operations.KernelOperations;

/**
 * Model class containing new methods implemented, namely the preview functionality.
 */
public class Model2 extends Model implements ModelInterface2 {
  @Override
  public BufferedImage getPreviewImage(BufferedImage originalImage, BufferedImage operatedImage,
                                       int percent) {
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    BufferedImage previewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int operationWidth = (int) ((percent / 100.0) * width);
    Graphics2D g = previewImage.createGraphics();
    g.drawImage(operatedImage, 0, 0, operationWidth, height, 0, 0,
            operationWidth, height, null);
    g.drawImage(originalImage, operationWidth, 0, width, height,
            operationWidth, 0, width, height, null);
    g.dispose();
    return previewImage;
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
    ImageData imageData = getImage(imageName);
    FilterOperations ob = new FilterOperations();
    int[][][] newRGBMatrix = ob.ditherImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }
}
