package model.operations;

import java.awt.image.BufferedImage;

import model.ImageInterface;

/**
 * Class containing all the functionalities related to loading or saving an
 * image.
 */
public class LoadAndSaveOperation extends AbstractClass {
  /**
   * Function to load an image that is not of filetype ppm.
   *
   * @param bufferedImage input buffered image.
   * @return rgbMatrix of the said buffered image.
   */
  public int[][][] loadImage(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    rgbMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        rgbMatrix[x][y][0] = red;
        rgbMatrix[x][y][1] = green;
        rgbMatrix[x][y][2] = blue;
      }
    }
    return rgbMatrix;
  }

  /**
   * Function to save an image that is not ppm.
   *
   * @param imageData Object of the image.
   * @return buffered Image of input image.
   */
  public BufferedImage saveImage(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int red = rgbMatrix[x][y][0];
        int green = rgbMatrix[x][y][1];
        int blue = rgbMatrix[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Function to save a ppm image.
   *
   * @param imageData Object of the image.
   * @return String generated from the input image.
   */
  public StringBuilder savePPMImage(ImageInterface imageData) {
    StringBuilder s = new StringBuilder();
    int[][][] rgbMatrix = imageData.getImageMatrix();
    int height = rgbMatrix.length;
    int width = rgbMatrix[0].length;
    s.append("P3\n");
    s.append("# PPM Image created.\n");
    s.append(width);
    s.append(" ");
    s.append(height);
    s.append("\n");
    s.append(255);
    s.append("\n");
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        s.append(rgbMatrix[y][x][0]).append(" ");
        s.append(rgbMatrix[y][x][1]).append(" ");
        s.append(rgbMatrix[y][x][2]).append(" ");
//        s.append(rgbMatrix[y][x][0]);
//        s.append("\n");
//        s.append(rgbMatrix[y][x][1]);
//        s.append("\n");
//        s.append(rgbMatrix[y][x][2]);
//        s.append("\n");
      }
      s.append("\n");
    }
    return s;
  }
}
