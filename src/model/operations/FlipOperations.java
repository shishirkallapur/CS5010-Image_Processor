package model.operations;

import model.ImageInterface;

/**
 * Class containing all operations pertaining to flipping an image in any
 * specified direction.
 */
public class FlipOperations extends AbstractClass {

  /**
   * Method to flip an image horizontally.
   *
   * @param imageData Object of an image.
   * @return Flipped RGB Matrix.
   */
  public int[][][] flipImageHorizontal(ImageInterface imageData) {

    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y] = rgbMatrix[width - x - 1][y];
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to flip an image vertically.
   *
   * @param imageData Object of an image.
   * @return Flipped RGB Matrix.
   */
  public int[][][] flipImageVertical(ImageInterface imageData) {

    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y] = rgbMatrix[x][height - y - 1];
      }
    }
    return newRGBMatrix;
  }
}