package model;

/**
 * Class to represent an Image object.
 */
public class ImageData implements ImageInterface {
  private final int[][][] imageMatrix;

  /**
   * Constructor to initialise the Image object.
   *
   * @param imageMatrix 3 dimensional array matrix of pixels to represent an
   *                    Image.
   */
  public ImageData(int[][][] imageMatrix) {
    this.imageMatrix = imageMatrix;
  }

  /**
   * Method to return a 3x3 array matrix for Image object.
   *
   * @return 3x3 array matrix for image.
   */
  public int[][][] getImageMatrix() {
    int[][][] temp = new int[imageMatrix.length][imageMatrix[0].length][imageMatrix[0][0].length];
    for (int i = 0; i < imageMatrix.length; i++) {
      for (int j = 0; j < imageMatrix[0].length; j++) {
        for (int k = 0; k < imageMatrix[0][0].length; k++) {
          temp[i][j][k] = imageMatrix[i][j][k];
        }
      }
    }
    return temp;
  }
}

