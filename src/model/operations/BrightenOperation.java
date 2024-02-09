package model.operations;


import model.ImageInterface;

/**
 * Class to Brighten or Dim an image.
 */
public class BrightenOperation extends AbstractClass {

  /**
   * Method to brighten or dim an image.
   *
   * @param imageData Object of an image.
   * @param value Value by which object is to be dimmed or brightened.
   * @return Resultant dimmed or brightened RGB matrix of an image.
   */
  public int[][][] brightenImage(ImageInterface imageData, Integer value) {

    rgbMatrix = imageData.getImageMatrix();
    int width = rgbMatrix.length;
    int height = rgbMatrix[0].length;
    newRGBMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double temp1 =
                rgbMatrix[x][y][0] + value;
        double temp2 =
                rgbMatrix[x][y][1] + value;
        double temp3 =
                rgbMatrix[x][y][2] + value;
        temp1 = clampValue(temp1);
        temp2 = clampValue(temp2);
        temp3 = clampValue(temp3);
        newRGBMatrix[x][y][0] = (int) temp1;
        newRGBMatrix[x][y][1] = (int) temp2;
        newRGBMatrix[x][y][2] = (int) temp3;
      }
    }
    return newRGBMatrix;
  }
}
