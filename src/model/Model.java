package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import model.operations.BrightenOperation;
import model.operations.ColorOperations;
import model.operations.CompressionOperation;
import model.operations.FilterOperations;
import model.operations.FlipOperations;
import model.operations.HistogramOperations;
import model.operations.KernelOperations;
import model.operations.LoadAndSaveOperation;
import model.operations.SplitAndCombineOperation;

/**
 * This class represents all the functionalities to be implemented on the image.
 * Eg. Load image, Save Image, Blur Image, Sharpen Image, Brighten Image etc.
 */
public class Model implements ModelInterface {
  protected final Map<String, ImageData> matrixMap = new HashMap<>();

  @Override
  public void loadImage(String imageName, BufferedImage bufferedImage) {
    LoadAndSaveOperation ob = new LoadAndSaveOperation();
    int[][][] newRGBMatrix = ob.loadImage(bufferedImage);
    matrixMap.put(imageName, new ImageData(newRGBMatrix));
  }

  @Override
  public BufferedImage saveImage(String imageName) throws Exception {
    ImageData imageData = getImage(imageName);
    LoadAndSaveOperation ob = new LoadAndSaveOperation();
    return ob.saveImage(imageData);
  }

  @Override
  public void brightenImage(String imageName, String newImageName, Integer value) throws Exception {
    ImageData imageData = getImage(imageName);
    BrightenOperation ob = new BrightenOperation();
    int[][][] newRGBMatrix = ob.brightenImage(imageData, value);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void sepiaImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FilterOperations ob = new FilterOperations();
    int[][][] newRGBMatrix = ob.sepiaImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void lumaImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FilterOperations ob = new FilterOperations();
    int[][][] newRGBMatrix = ob.lumaImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void intensityImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FilterOperations ob = new FilterOperations();
    int[][][] newRGBMatrix = ob.intensityImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void valueImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FilterOperations ob = new FilterOperations();
    int[][][] newRGBMatrix = ob.valueImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void redImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    ColorOperations ob = new ColorOperations();
    int[][][] newRGBMatrix = ob.redImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void blueImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    ColorOperations ob = new ColorOperations();
    int[][][] newRGBMatrix = ob.blueImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void greenImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    ColorOperations ob = new ColorOperations();
    int[][][] newRGBMatrix = ob.greenImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void flipImageHorizontal(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FlipOperations ob = new FlipOperations();
    int[][][] newRGBMatrix = ob.flipImageHorizontal(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void flipImageVertical(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    FlipOperations ob = new FlipOperations();
    int[][][] newRGBMatrix = ob.flipImageVertical(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void combineImage(String newImageName, String redImage, String greenImage,
                           String blueImage) throws Exception {
    ImageData imageDataRed = getImage(redImage);
    ImageData imageDataGreen = getImage(greenImage);
    ImageData imageDataBlue = getImage(blueImage);
    ColorOperations ob = new ColorOperations();
    int[][][] newRGBMatrix = ob.combineImage(imageDataRed, imageDataGreen, imageDataBlue);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void blurImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    KernelOperations ob = new KernelOperations();
    int[][][] newRGBMatrix = ob.blurImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    KernelOperations ob = new KernelOperations();
    int[][][] newRGBMatrix = ob.sharpenImage(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void loadPPMImage(String imageName, int[][][] temp) {
    matrixMap.put(imageName, new ImageData(temp));
  }

  @Override
  public StringBuilder savePPMImage(String imageName) throws Exception {
    ImageData imageData = getImage(imageName);
    LoadAndSaveOperation ob = new LoadAndSaveOperation();
    StringBuilder temp;
    temp = ob.savePPMImage(imageData);
    return temp;
  }

  @Override
  public void split(String imageName, int percent, String tempName) throws Exception {
    ImageData imageData = getImage(imageName);
    SplitAndCombineOperation ob = new SplitAndCombineOperation();
    int[][][] newRGBMatrix = ob.split(imageData, percent);
    matrixMap.put(tempName, new ImageData(newRGBMatrix));
  }

  @Override
  public void combine(String tempName, String imageName, String newImageName) throws Exception {
    ImageData oldImageData = getImage(imageName);
    ImageData tempImageData = getImage(tempName);
    SplitAndCombineOperation ob = new SplitAndCombineOperation();
    int[][][] newRGBMatrix = ob.combine(tempImageData, oldImageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void compressImage(String imageName, int percentage,
                            String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    CompressionOperation ob = new CompressionOperation();
    int[][][] newRGBMatrix = ob.compressImage(imageData, percentage);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void histogram(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    HistogramOperations ob = new HistogramOperations();
    BufferedImage result = ob.getHistogram(imageData);
    loadImage(newImageName, result);
  }

  @Override
  public void colorCorrect(String imageName, String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    HistogramOperations ob = new HistogramOperations();
    int[][][] newRGBMatrix = ob.colorCorrect(imageData);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  @Override
  public void levelAdjust(String imageName, int black, int mid, int white,
                          String newImageName) throws Exception {
    ImageData imageData = getImage(imageName);
    HistogramOperations ob = new HistogramOperations();
    int[][][] newRGBMatrix = ob.colorAdjust(imageData, black, mid, white);
    matrixMap.put(newImageName, new ImageData(newRGBMatrix));
  }

  ImageData getImage(String imageName) throws Exception {
    ImageData imageData;
    try {
      imageData = matrixMap.get(imageName);
      if (imageData == null) {
        throw new Exception();
      }
    } catch (Exception e) {
      throw new Exception("Entered image does not exist");
    }
    return imageData;
  }
}
