package model.operations;

import model.ImageInterface;

/**
 * Class containing all operations pertaining to Split and Combine
 * functionalities.
 */
public class SplitAndCombineOperation extends AbstractClass {
  /**
   * Function for split functionality that splits the image and applies said
   * operation on image split.
   *
   * @param imageData   Object of image.
   * @param percent Percentage of split.
   * @return rgbMatrix of image.
   * @throws Exception for exception handling.
   */
  public int[][][] split(ImageInterface imageData, int percent) throws Exception {
    rgbMatrix = imageData.getImageMatrix();
    int width = (int) (rgbMatrix.length * (percent / 100.0));
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        newRGBMatrix[i][j][0] = rgbMatrix[i][j][0];
        newRGBMatrix[i][j][1] = rgbMatrix[i][j][1];
        newRGBMatrix[i][j][2] = rgbMatrix[i][j][2];
      }
    }
    return newRGBMatrix;
  }

  /**
   * Function for combining splits. It takes the temporary split image and
   * combines it with original image so that functionality is visible only in
   * the said percentage of the original image.
   *
   * @param tempImageData temporary image for operation.
   * @param oldImageData  original image to combine with.
   * @return RGBMatrix of final output.
   * @throws Exception for exception handling.
   */
  public int[][][] combine(ImageInterface tempImageData, ImageInterface oldImageData)
          throws Exception {
    int[][][] oldRGBMatrix = oldImageData.getImageMatrix();
    int[][][] operatedMatrix = tempImageData.getImageMatrix();
    int operatedWidth = operatedMatrix.length;
    int operatedHeight = operatedMatrix[0].length;
    int oldWidth = oldRGBMatrix.length;
    int oldHeight = oldRGBMatrix[0].length;
    newRGBMatrix = new int[oldWidth][oldHeight][3];
    for (int i = 0; i < oldWidth; i++) {
      for (int j = 0; j < oldHeight; j++) {
        newRGBMatrix[i][j][0] = oldRGBMatrix[i][j][0];
        newRGBMatrix[i][j][1] = oldRGBMatrix[i][j][1];
        newRGBMatrix[i][j][2] = oldRGBMatrix[i][j][2];
      }
    }
    for (int i = 0; i < operatedWidth; i++) {
      for (int j = 0; j < operatedHeight; j++) {
        newRGBMatrix[i][j][0] = operatedMatrix[i][j][0];
        newRGBMatrix[i][j][1] = operatedMatrix[i][j][1];
        newRGBMatrix[i][j][2] = operatedMatrix[i][j][2];
      }
    }
    return newRGBMatrix;
  }
}
