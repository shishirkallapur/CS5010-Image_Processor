package model.operations;

/**
 * Abstract class for commonly recurring functions and code.
 */
abstract class AbstractClass {
  protected int[][][] newRGBMatrix;
  protected int[][][] rgbMatrix;

  protected double clampValue(double temp) {

    if (temp > 255) {
      temp = 255;
    }
    if (temp < 0) {
      temp = 0;
    }
    return temp;
  }
}
