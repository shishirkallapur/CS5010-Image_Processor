package model.operations;

import model.ImageInterface;

/**
 * Class containing all color specific operations.
 */
public class ColorOperations extends AbstractClass {

  /**
   * Method to obtain only the red component of an image.
   *
   * @param imageData Object of an image.
   * @return Red component of image.
   */
  public int[][][] redImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y][0] = rgbMatrix[x][y][0];
        newRGBMatrix[x][y][1] = 0;
        newRGBMatrix[x][y][2] = 0;
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to obtain only the blue component of an image.
   *
   * @param imageData Object of an image.
   * @return Blue component of an image.
   */
  public int[][][] blueImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y][0] = 0;
        newRGBMatrix[x][y][1] = 0;
        newRGBMatrix[x][y][2] = rgbMatrix[x][y][2];
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to obtain only the green component of an image.
   *
   * @param imageData Object of an image.
   * @return Green component of an image.
   */
  public int[][][] greenImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y][0] = 0;
        newRGBMatrix[x][y][1] = rgbMatrix[x][y][1];
        newRGBMatrix[x][y][2] = 0;
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to combine various components of an image and merge them into one.
   *
   * @param redImageData   Object of red component of an image.
   * @param greenImageData Object of green component of an image.
   * @param blueImageData  Object of blue component of an image.
   * @return RGB matrix containing combined components of image.
   */
  public int[][][] combineImage(ImageInterface redImageData, ImageInterface greenImageData,
                                ImageInterface blueImageData) {
    int[][][] redMatrix;
    int[][][] greenMatrix;
    int[][][] blueMatrix;
    redMatrix = redImageData.getImageMatrix();
    greenMatrix = greenImageData.getImageMatrix();
    blueMatrix = blueImageData.getImageMatrix();
    int width = redMatrix.length;
    int height = redMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newRGBMatrix[x][y][0] = redMatrix[x][y][0];
        newRGBMatrix[x][y][1] = greenMatrix[x][y][1];
        newRGBMatrix[x][y][2] = blueMatrix[x][y][2];
      }
    }
    return newRGBMatrix;
  }
}
