package model.operations;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import model.ImageInterface;

/**
 * Class containing all the methods for compression operation.
 */
public class CompressionOperation extends AbstractClass {

  /**
   * Method to compress an image based on the percentage specified by the user.
   *
   * @param imageData      Object of an image.
   * @param percentage Percentage of compression.
   * @return Resultant compressed RGB matrix.
   */
  public int[][][] compressImage(ImageInterface imageData, int percentage) {
    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    int n = 1;
    int padding;
    if (width > height) {
      while (Math.pow(2, n) < width) {
        n++;
      }
    } else {
      while (Math.pow(2, n) < height) {
        n++;
      }
    }
    padding = (int) Math.pow(2, n);

    int[][][] rgbPadded = new int[padding][padding][3];
    for (int i = 0; i < padding; i++) {
      for (int j = 0; j < padding; j++) {
        rgbPadded[i][j][0] = 0;
        rgbPadded[i][j][1] = 0;
        rgbPadded[i][j][2] = 0;
      }
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        rgbPadded[i][j][0] = rgbMatrix[i][j][0];
        rgbPadded[i][j][1] = rgbMatrix[i][j][1];
        rgbPadded[i][j][2] = rgbMatrix[i][j][2];
      }
    }

    double[][] redChannel = new double[padding][padding];
    double[][] greenChannel = new double[padding][padding];
    double[][] blueChannel = new double[padding][padding];

    for (int x = 0; x < padding; x++) {
      for (int y = 0; y < padding; y++) {
        redChannel[x][y] = rgbPadded[x][y][0];
        greenChannel[x][y] = rgbPadded[x][y][1];
        blueChannel[x][y] = rgbPadded[x][y][2];
      }
    }

    double[][] transformedRedChannel = haar(redChannel);
    double[][] transformedGreenChannel = haar(greenChannel);
    double[][] transformedBlueChannel = haar(blueChannel);

    setToZero(transformedRedChannel, percentage);
    setToZero(transformedGreenChannel, percentage);
    setToZero(transformedBlueChannel, percentage);

    inverseHaar(transformedRedChannel);
    inverseHaar(transformedGreenChannel);
    inverseHaar(transformedBlueChannel);

    int[][][] unpaddedMatrix = new int[width][height][3];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        unpaddedMatrix[i][j][0] = (int) clampValue(transformedRedChannel[i][j]);
        unpaddedMatrix[i][j][1] = (int) clampValue(transformedGreenChannel[i][j]);
        unpaddedMatrix[i][j][2] = (int) clampValue(transformedBlueChannel[i][j]);
      }
    }


    return unpaddedMatrix;
  }

  private void inverseHaar(double[][] matrix) {
    int c = 2;
    int l = matrix.length;
    while (c <= l) {
      for (int i = 0; i < c; i++) {
        double[] row = new double[c];
        for (int j = 0; j < c; j++) {
          row[j] = matrix[i][j];
        }
        row = inverseTransform(row);
        for (int j = 0; j < c; j++) {
          matrix[i][j] = row[j];
        }
      }

      for (int i = 0; i < c; i++) {
        double[] col = new double[c];
        for (int j = 0; j < c; j++) {
          col[j] = matrix[j][i];
        }
        col = inverseTransform(col);
        for (int j = 0; j < c; j++) {
          matrix[j][i] = col[j];
        }
      }
      c *= 2;
    }
  }


  private double[] inverseTransform(double[] data) {
    int l = data.length;
    double[] avg = new double[l / 2];
    double[] diff = new double[l / 2];
    double[] result = new double[l];
    int j = l / 2;

    for (int i = 0; i < l / 2; i++) {
      double av = (data[i] + data[j]) / Math.sqrt(2);
      double di = (data[i] - data[j]) / Math.sqrt(2);
      avg[i] = av;
      diff[i] = di;
      j++;
    }

    int k = 0;

    for (int i = 0; i < l; i += 2) {
      result[i] = avg[k];
      result[i + 1] = diff[k];
      k++;
    }

    return result;
  }

  private void setToZero(double[][] matrix, int percentage) {
    PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> (int) (a - b));
    Set<Double> set = new HashSet<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (matrix[i][j] != 0.0) {
          set.add(Math.abs(matrix[i][j]));
        }
      }
    }
    int k = (int) ((percentage / 100.0) * set.size());

    set.forEach(pq::offer);
    for (int i = 0; i < k; i++) {
      pq.poll();
    }
    double threshold = pq.peek();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (Math.abs(matrix[i][j]) < threshold) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  private double[][] haar(double[][] matrix) {
    int l = matrix.length;
    while (l > 1) {
      for (int i = 0; i < l; i++) {
        double[] row = new double[l];
        System.arraycopy(matrix[i], 0, row, 0, l);
        row = haarTransform(row);
        System.arraycopy(row, 0, matrix[i], 0, l);
      }

      for (int i = 0; i < l; i++) {
        double[] col = new double[l];
        for (int j = 0; j < l; j++) {
          col[j] = matrix[j][i];
        }
        col = haarTransform(col);
        for (int j = 0; j < l; j++) {
          matrix[j][i] = col[j];
        }
      }

      l /= 2;
    }
    return matrix;
  }


  private double[] haarTransform(double[] data) {
    int l = data.length;
    double[] avg = new double[l / 2];
    double[] diff = new double[l / 2];
    double[] result = new double[l];
    int k = 0;

    for (int i = 0; i < l; i += 2) {
      double av = (data[i] + data[i + 1]) / Math.sqrt(2);
      double di = (data[i] - data[i + 1]) / Math.sqrt(2);
      avg[k] = av;
      diff[k] = di;
      k++;
    }

    k = avg.length;

    for (int i = 0; i < l / 2; i++) {
      result[i] = avg[i];
      result[i + k] = diff[i];
    }
    return result;
  }
}
