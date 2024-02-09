package controller.commands;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling load operations for all file types.
 */
public class LoadCommand {
  /**
   * Method to call the model and view for Load operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    if (arguments.length == 3) {
      String filePath = arguments[1];
      String filetype = filePath.substring(filePath.indexOf('.') + 1);
      String imageName = arguments[2];
      if (filetype.equalsIgnoreCase("ppm")) {
        int[][][] tempRGBMatrix;
        try {
          tempRGBMatrix = readPPMFile(filePath);
        } catch (Exception e) {
          imageView.errorWhileReading();
          throw new Exception(e);
        }
        imageModel.loadPPMImage(imageName, tempRGBMatrix);
      } else {
        BufferedImage bufferedImage;
        try {
          bufferedImage = ImageIO.read(new File(filePath));
        } catch (Exception e) {
          imageView.errorWhileReading();
          throw new Exception(e);
        }
        imageModel.loadImage(imageName, bufferedImage);
      }
    } else {
      imageView.wrongCommand();
      throw new Exception();
    }
  }

  private int[][][] readPPMFile(String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String magicNumber = readLineIgnoringComments(br).trim();
      int maxColorValue;
      String dimensions = readLineIgnoringComments(br);
      String[] dimensionTokens = dimensions.split("\\s+");
      int width = Integer.parseInt(dimensionTokens[0]);
      int height = Integer.parseInt(dimensionTokens[1]);
      maxColorValue = Integer.parseInt(readLineIgnoringComments(br).trim());
      int[][][] rgbMatrix = new int[height][width][3];
      if ("P3".equals(magicNumber)) {
        for (int y = 0; y < height; y++) {
          String line = readLineIgnoringComments(br);
          String[] pixelValues = line.trim().split("\\s+");
          for (int x = 0; x < width; x++) {
            int red = Integer.parseInt(pixelValues[(x * 3)]);
            int green = Integer.parseInt(pixelValues[(x * 3) + 1]);
            int blue = Integer.parseInt(pixelValues[(x * 3) + 2]);
//            int red = Integer.parseInt(readLineIgnoringComments(br).trim());
//            int green = Integer.parseInt(readLineIgnoringComments(br).trim());
//            int blue = Integer.parseInt(readLineIgnoringComments(br).trim());
            rgbMatrix[y][x][0] = red;
            rgbMatrix[y][x][1] = green;
            rgbMatrix[y][x][2] = blue;
          }
        }
        br.close();
      } else {
        throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
      }
      return rgbMatrix;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String readLineIgnoringComments(BufferedReader br) throws IOException {
    String line = br.readLine();
    while (line != null && line.trim().startsWith("#")) {
      line = br.readLine();
    }
    return line;
  }
}
