package model.operations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ImageInterface;

/**
 * Class for histogram, color correct and color adjustment operations.
 */
public class HistogramOperations extends AbstractClass {

  /**
   * Method to create a histogram for an image.
   *
   * @param imageData Object of an image.
   * @return Buffered image of the histogram.
   */
  public BufferedImage getHistogram(ImageInterface imageData) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;

    double[] redHistogram = new double[256];
    double[] blueHistogram = new double[256];
    double[] greenHistogram = new double[256];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int red = rgbMatrix[i][j][0];
        int blue = rgbMatrix[i][j][2];
        int green = rgbMatrix[i][j][1];
        redHistogram[red]++;
        blueHistogram[blue]++;
        greenHistogram[green]++;
      }
    }

    double max = getMax(redHistogram, blueHistogram, greenHistogram);

    for (int i = 0; i < 255; i++) {
      redHistogram[i] = 256 * (redHistogram[i] / max);
      greenHistogram[i] = 256 * (greenHistogram[i] / max);
      blueHistogram[i] = 256 * (blueHistogram[i] / max);
    }

    BufferedImage result = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2D = result.createGraphics();

    g2D.setColor(Color.WHITE);
    g2D.fillRect(0, 0, 255, 255);

    g2D.setColor(Color.LIGHT_GRAY);
    int delta = 255 / 20;
    for (int i = -1; i <= 21; i++) {
      int y = 255 - i * delta;
      g2D.drawLine(0, y, 255, y);
    }
    for (int i = 0; i < 255; i++) {
      int x = i * delta;
      g2D.drawLine(x, 0, x, 255);
    }

    plotter(g2D, redHistogram, 0);
    plotter(g2D, greenHistogram, 1);
    plotter(g2D, blueHistogram, 2);
    return result;
  }

  private double getMax(double[] redHistogram, double[] blueHistogram, double[] greenHistogram) {
    double maxRed = 0;
    double maxBlue = 0;
    double maxGreen = 0;

    for (int i = 0; i < 255; i++) {
      if (redHistogram[i] > maxRed) {
        maxRed = redHistogram[i];
      }
      if (blueHistogram[i] > maxBlue) {
        maxBlue = blueHistogram[i];
      }
      if (greenHistogram[i] > maxGreen) {
        maxGreen = greenHistogram[i];
      }
    }

    double max;
    if (maxRed > maxGreen && maxRed > maxBlue) {
      max = maxRed;
    } else if (maxGreen > maxBlue && maxGreen > maxRed) {
      max = maxGreen;
    } else {
      max = maxBlue;
    }
    return max;
  }

  private void plotter(Graphics2D g2D, double[] hist, int c) {
    if (c == 0) {
      g2D.setColor(Color.RED);
    }
    if (c == 1) {
      g2D.setColor(Color.GREEN);
    }
    if (c == 2) {
      g2D.setColor(Color.BLUE);
    }

    for (int i = 0; i < 255; i++) {
      int x1 = i;
      int y1 = (int) (256 - hist[i]);
      int x2 = i + 1;
      int y2 = (int) (256 - hist[i + 1]);

      g2D.drawLine(x1, y1, x2, y2);
    }
  }

  /**
   * Method to color correct an image.
   *
   * @param imageData Object of an image.
   * @return Color corrected resultant RGB Matrix.
   */
  public int[][][] colorCorrect(ImageInterface imageData) {
    int[][][] rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    int[][][] newRGBMatrix = new int[width][height][3];

    int[] redHistogram = new int[256];
    int[] blueHistogram = new int[256];
    int[] greenHistogram = new int[256];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int red = rgbMatrix[i][j][0];
        int blue = rgbMatrix[i][j][2];
        int green = rgbMatrix[i][j][1];
        redHistogram[red]++;
        blueHistogram[blue]++;
        greenHistogram[green]++;
      }
    }

    int maxRed = 0;
    int redIndex = 0;
    int maxBlue = 0;
    int blueIndex = 0;
    int maxGreen = 0;
    int greenIndex = 0;

    for (int i = 9; i < 245; i++) {
      if (redHistogram[i] > maxRed) {
        maxRed = redHistogram[i];
        redIndex = i;
      }
      if (blueHistogram[i] > maxBlue) {
        maxBlue = blueHistogram[i];
        blueIndex = i;
      }
      if (greenHistogram[i] > maxGreen) {
        maxGreen = greenHistogram[i];
        greenIndex = i;
      }
    }
    int meanPeak = (redIndex + blueIndex + greenIndex) / 3;

    int redOffset = meanPeak - redIndex;
    int blueOffset = meanPeak - blueIndex;
    int greenOffset = meanPeak - greenIndex;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        double temp;
        temp = rgbMatrix[i][j][0] + redOffset;
        temp = clampValue(temp);
        newRGBMatrix[i][j][0] = (int) temp;

        temp = rgbMatrix[i][j][1] + greenOffset;
        temp = clampValue(temp);
        newRGBMatrix[i][j][1] = (int) temp;

        temp = rgbMatrix[i][j][2] + blueOffset;
        temp = clampValue(temp);
        newRGBMatrix[i][j][2] = (int) temp;
      }
    }
    return newRGBMatrix;
  }

  /**
   * Method for level adjustment functionality.
   *
   * @param imageData Object of an image.
   * @param black Value of black parameter.
   * @param mid   Value of mid parameter.
   * @param white Value of white parameter.
   * @return Resultant level adjusted RGB Matrix.
   */
  public int[][][] colorAdjust(ImageInterface imageData, int black, int mid, int white) {
    int[][][] rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    int[][][] newRGBMatrix = new int[width][height][3];

    double t =
            ((black * black * (mid - white)) - (black * ((mid * mid) - (white *
                    white))) + (white * mid * mid) - (mid * white * white));
    double ta = (-black * (128 - 255)) + (128 * white) - (255 * mid);
    double tb = (((black * black) * (128 - 255)) + (255 * (mid * mid)) - (128 *
            (white * white)));
    double tc =
            (((black * black) * ((255 * mid) - (128 * white))) - (black * ((255
                    * (mid * mid)) - (128 * (white * white)))));
    double a = ta / t;
    double b2 = tb / t;
    double c = tc / t;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        double tempr = fitting(rgbMatrix[i][j][0], a, b2, c);
        tempr = clampValue(tempr);
        newRGBMatrix[i][j][0] = (int) tempr;

        double tempg = fitting(rgbMatrix[i][j][1], a, b2, c);
        tempg = clampValue(tempg);
        newRGBMatrix[i][j][1] = (int) tempg;

        double tempb = fitting(rgbMatrix[i][j][2], a, b2, c);
        tempb = clampValue(tempb);
        newRGBMatrix[i][j][2] = (int) tempb;
      }
    }
    return newRGBMatrix;
  }

  private double fitting(int item, double a, double b, double c) {
    return ((a * (item * item)) + (b * item) + c);
  }
}