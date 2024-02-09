package model.operations;

import model.ImageInterface;

/**
 * Class containing all the operations pertaining to using a kernel, mainly
 * blur and sharpen functionality on the image.
 */
public class KernelOperations extends AbstractClass {

  /**
   * Method to Blur an image.
   *
   * @param imageData Object of an image.
   * @return RGB matrix of a blurred image.
   */
  public int[][][] blurImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    float[][] kernel = {
            {1 / 16f, 1 / 8f, 1 / 16f},
            {1 / 8f, 1 / 4f, 1 / 8f},
            {1 / 16f, 1 / 8f, 1 / 16f}};
    applyKernel(kernel, height, width, rgbMatrix, newRGBMatrix);
    return newRGBMatrix;
  }

  /**
   * Method to sharpen an image.
   *
   * @param imageData Object of an image.
   * @return RGB matrix of a sharpened image.
   */
  public int[][][] sharpenImage(ImageInterface imageData) {

    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    float[][] kernel = {
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}
    };
    applyKernel(kernel, height, width, rgbMatrix, newRGBMatrix);
    return newRGBMatrix;
  }

  private void applyKernel(float[][] kernel,
                           int height, int width, int[][][] rgbMatrix, int[][][] newRGBMatrix) {
    int kernelSize = kernel.length;
    int kernelRadius = kernelSize / 2;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        float sumR = 0;
        float sumG = 0;
        float sumB = 0;
        for (int i = -kernelRadius; i <= kernelRadius; i++) {
          for (int j = -kernelRadius; j <= kernelRadius; j++) {
            int xi = x + i;
            int yi = y + j;
            if (xi >= 0 && xi < width && yi >= 0 && yi < height) {
              int red = rgbMatrix[xi][yi][0];
              int green = rgbMatrix[xi][yi][1];
              int blue = rgbMatrix[xi][yi][2];
              float weight = kernel[i + kernelRadius][j + kernelRadius];
              sumR += red * weight;
              sumG += green * weight;
              sumB += blue * weight;
            }
          }
        }
        sumR = (float) clampValue(sumR);
        sumG = (float) clampValue(sumG);
        sumB = (float) clampValue(sumB);
        newRGBMatrix[x][y][0] = (int) sumR;
        newRGBMatrix[x][y][1] = (int) sumG;
        newRGBMatrix[x][y][2] = (int) sumB;
      }
    }
  }
}
