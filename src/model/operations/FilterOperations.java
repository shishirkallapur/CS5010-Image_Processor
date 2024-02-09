package model.operations;

import model.ImageInterface;

/**
 * Class containing all the operations involving applying a filter to an image.
 */
public class FilterOperations extends AbstractClass {

  /**
   * Method to apply sepia filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] sepiaImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    double[][] mat = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return getOutputMatrix(width, height, mat);
  }

  /**
   * Method to apply luma filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] lumaImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    double[][] mat = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    return getOutputMatrix(width, height, mat);
  }

  private int[][][] getOutputMatrix(int width, int height, double[][] mat) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double[] pixel = {rgbMatrix[x][y][0], rgbMatrix[x][y][1], rgbMatrix[x][y][2]};
        double[] result = matrixMultiply(mat, pixel);
        for (int i = 0; i < 3; i++) {
          result[i] = clampValue(result[i]);
          newRGBMatrix[x][y][i] = (int) result[i];
        }
      }
    }
    return newRGBMatrix;
  }

  private double[] matrixMultiply(double[][] matrix, double[] vector) {
    double[] result = new double[matrix.length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < vector.length; j++) {
        result[i] += matrix[i][j] * vector[j];
      }
    }
    return result;
  }

  /**
   * Method to apply intensity filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] intensityImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double temp = (double) (rgbMatrix[x][y][0] + rgbMatrix[x][y][1] +
                rgbMatrix[x][y][2]) / 3;
        temp = clampValue(temp);
        newRGBMatrix[x][y][0] = (int) temp;
        newRGBMatrix[x][y][1] = (int) temp;
        newRGBMatrix[x][y][2] = (int) temp;
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method to apply value filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] valueImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double temp = Math.max(rgbMatrix[x][y][0], Math.max(rgbMatrix[x][y][1],
                rgbMatrix[x][y][2]));
        temp = clampValue(temp);
        newRGBMatrix[x][y][0] = (int) temp;
        newRGBMatrix[x][y][1] = (int) temp;
        newRGBMatrix[x][y][2] = (int) temp;
      }
    }
    return newRGBMatrix;
  }
  /**
   * Method to apply dither filter on an image.
   *
   * @param imageData Object of an image.
   * @return Resultant filter applied RGB matrix.
   */
  public int[][][] ditherImage(ImageInterface imageData) {
    rgbMatrix = this.intensityImage(imageData);
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int oldRed = rgbMatrix[x][y][0];
        int oldGreen = rgbMatrix[x][y][1];
        int oldBlue = rgbMatrix[x][y][2];
        int newRed = (oldRed < 128) ? 0 : 255;
        int newGreen = (oldGreen < 128) ? 0 : 255;
        int newBlue = (oldBlue < 128) ? 0 : 255;
        rgbMatrix[x][y][0] = newRed;
        rgbMatrix[x][y][1] = newGreen;
        rgbMatrix[x][y][2] = newBlue;
        int errorRed = oldRed - newRed;
        int errorGreen = oldGreen - newGreen;
        int errorBlue = oldBlue - newBlue;
        if (x + 1 < width) {
          diffuseError(rgbMatrix, x + 1, y, errorRed, errorGreen, errorBlue,
                  7.0 / 16.0);
        }
        if (y + 1 < height && x - 1 >= 0) {
          diffuseError(rgbMatrix, x - 1, y + 1, errorRed, errorGreen, errorBlue,
                  3.0 / 16.0);
        }
        if (y + 1 < height) {
          diffuseError(rgbMatrix, x, y + 1, errorRed, errorGreen, errorBlue,
                  5.0 / 16.0);
        }
        if (y + 1 < height && x + 1 < width) {
          diffuseError(rgbMatrix, x + 1, y + 1, errorRed, errorGreen, errorBlue,
                  1.0 / 16.0);
        }
      }
    }
    return rgbMatrix;
  }

  private void diffuseError(int[][][] rgbMatrix, int x, int y, int errorRed, int errorGreen,
                            int errorBlue, double factor) {
    int newRed = (int) clampValue(rgbMatrix[x][y][0] + (factor * errorRed));
    int newGreen = (int) clampValue(rgbMatrix[x][y][0] + (factor * errorGreen));
    int newBlue = (int) clampValue(rgbMatrix[x][y][0] + (factor * errorBlue));
    rgbMatrix[x][y][0] = newRed;
    rgbMatrix[x][y][1] = newGreen;
    rgbMatrix[x][y][2] = newBlue;

  }
}