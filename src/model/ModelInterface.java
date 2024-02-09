package model;

import java.awt.image.BufferedImage;

/**
 * Interface to represent the model of the image and all the functions
 * representing the different operations that can be performed on the image.
 */
public interface ModelInterface {

  /**
   * Method to load the image into the model. This method takes the imageName
   * and the bufferedImage as parameters and converts the bufferedImage into a
   * 3D RGB Matrix and stores the matrix as an Image object in a HashMap with
   * the imageName as the key.
   *
   * @param imageName     String imageName.
   * @param bufferedImage buffered image.
   */
  void loadImage(String imageName, BufferedImage bufferedImage);

  /**
   * Method to save the model as an image. This method takes the image object
   * as a parameter and converts the image object into a bufferedImage and
   * returns this bufferedImage back to the controller.
   *
   * @param imageName String imageName.
   * @return buffered Image.
   */
  BufferedImage saveImage(String imageName) throws Exception;


  /**
   * Method to brighten an image.
   *
   * @param imageName    String image name.
   * @param newImageName Image name for brightened/dimmed image.
   * @param value        Value for dimming/brightening.
   */
  void brightenImage(String imageName, String newImageName, Integer value) throws Exception;

  /**
   * Method to apply sepia filter on image. This method takes in the image
   * object and newImageName as the parameters and performs sepia operation on
   * the image object and stores the new image in the map with the newImageName
   * as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for sepia image.
   */
  void sepiaImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to apply luma filter on image. This method takes in the image
   * object and newImageName as the parameters and performs luma operation on
   * the image object and stores the new image in the map with the newImageName
   * as the key. New image formed is a grayscale image.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for luma image.
   */
  void lumaImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to apply intensity filter on image. This method takes in the image
   * object and newImageName as the parameters and performs intensity operation
   * on the image object and stores the new image in the map with the
   * newImageName as the key. New image formed is a grayscale image.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for intensity image.
   */
  void intensityImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to apply value filter on image. This method takes in the image
   * object and newImageName as the parameters and performs value operation on
   * the image object and stores the new image in the map with the newImageName
   * as the key. New image formed is a grayscale image.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for value image.
   */
  void valueImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to extract only the red component of an image.This method takes in
   * the image object and newImageName as the parameters and converts the image
   * into only its red component and stores the new image in the map with the
   * newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for red component of image.
   */
  void redImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to extract only the blue component of an image.This method takes in
   * the image object and newImageName as the parameters and converts the image
   * into only its blue component and stores the new image in the map with the
   * newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for blue component of image.
   */
  void blueImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to extract only the green component of an image.This method takes
   * in the image object and newImageName as the parameters and converts the
   * image into only its green component and stores the new image in the map
   * with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for green component of image.
   */
  void greenImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to horizontally flip an image. This method takes in the image
   * object and newImageName as the parameters and flips the image horizontally
   * and stores the new image in the map with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for horizontally flipped image.
   */
  void flipImageHorizontal(String imageName, String newImageName) throws Exception;

  /**
   * Method to vertically flip an image. This method takes in the image
   * object and newImageName as the parameters and flips the image vertically
   * and stores the new image in the map with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for vertically flipped image.
   */
  void flipImageVertical(String imageName, String newImageName) throws Exception;

  /**
   * Method to combine red, green and blue images into 1 image. This method
   * takes in 3 image objects(red, green and blue) and newImageName as the
   * parameters and combines the 3 images into a single image and stores the
   * new image in the map with the newImageName as the key.
   *
   * @param newImageName image name for combined image.
   * @param redImage     red image object.
   * @param greenImage   green image object.
   * @param blueImage    blue image object.
   */
  void combineImage(String newImageName, String redImage, String greenImage,
                    String blueImage) throws Exception;

  /**
   * Method to blur an image. This method takes in the image object and
   * newImageName as the parameters and performs blur operation on the image
   * and stores the new image in the map with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for blurred image.
   */
  void blurImage(String imageName, String newImageName) throws Exception;

  /**
   * Method to sharpen an image. This method takes in the image object and
   * newImageName as the parameters and performs blur operation on the image
   * and stores the new image in the map with the newImageName as the key.
   *
   * @param imageName    String imageName.
   * @param newImageName image name for sharpened image.
   */
  void sharpenImage(String imageName, String newImageName) throws Exception;


  /**
   * Method to load a PPM image into hashmap. This method takes in imageName
   * and 3-dimensional RGB Matrix as the parameters and stores the image in the
   * map with the ImageName as the key.
   *
   * @param imageName image name to load ppm image.
   * @param temp      3 dimensional rgb matrix to be loaded.
   */
  void loadPPMImage(String imageName, int[][][] temp);

  /**
   * Method to save an image object into a PPM image. This method takes in
   * image object and ImageName as the parameters and returns a StringBuilder
   * object containing the specific information and the 3D matrix back to the
   * controller.
   *
   * @param imageName image name to save image object.
   * @return StringBuilder object with 3D matrix and specific information.
   */
  StringBuilder savePPMImage(String imageName) throws Exception;

  /**
   * Method to split an image and apply operation only on said percentage of
   * image.
   *
   * @param imageName Image name.
   * @param percent   Percentage of split.
   * @param tempName  Temporary image name to hold split.
   */
  void split(String imageName, int percent, String tempName) throws Exception;

  /**
   * Method to combine the split image with operation performed on it.
   *
   * @param tempName     Temporary image name holding the split.
   * @param imageName    Original image name.
   * @param newImageName Combined image name.
   */
  void combine(String tempName, String imageName, String newImageName) throws Exception;

  /**
   * Method to compress an image by given percentage.
   *
   * @param imageName    Image name of image to be compressed.
   * @param percentage   Percentage of compression.
   * @param newImageName Compressed image name.
   */
  void compressImage(String imageName, int percentage,
                     String newImageName) throws Exception;

  /**
   * Method to generate histogram of an image.
   *
   * @param imageName    Image name.
   * @param newImageName Name of histogram image.
   */
  void histogram(String imageName, String newImageName) throws Exception;

  /**
   * Method to color correct an image.
   *
   * @param imageName    Image name.
   * @param newImageName Color corrected image name.
   */
  void colorCorrect(String imageName, String newImageName) throws Exception;

  /**
   * Method to level adjust an image.
   *
   * @param imageName    Image name.
   * @param black        Value for black parameter.
   * @param mid          Value for mid parameter.
   * @param white        Value for white parameter.
   * @param newImageName Level adjusted image name.
   */
  void levelAdjust(String imageName, int black, int mid, int white,
                   String newImageName) throws Exception;

}