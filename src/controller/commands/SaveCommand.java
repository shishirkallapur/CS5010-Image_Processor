package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling save operation for all file types.
 */
public class SaveCommand {
  /**
   * Method to call the model and view for Save operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    if (arguments.length == 3) {
      BufferedImage bufferedImage;
      String filePath = arguments[1];
      String filetype = filePath.substring(filePath.indexOf('.') + 1);
      if (!filePath.contains(".")) {
        imageView.wrongPath();
        throw new Exception();
      }
      String imageName = arguments[2];
      if (filetype.equalsIgnoreCase("ppm")) {
        StringBuilder s = imageModel.savePPMImage(imageName);
        PrintWriter pw = null;
        try {
          pw = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
          imageView.wrongPath();
          throw new Exception(e);
        }
        pw.print(s);
        pw.close();
      } else {
        bufferedImage = imageModel.saveImage(imageName);
        try {
          File outputFile = new File(filePath);
          ImageIO.write(bufferedImage, filetype, outputFile);
        } catch (IOException e) {
          imageView.errorWhileReading();
          throw new Exception(e);
        }
      }
    } else {
      imageView.wrongCommand();
      throw new Exception();
    }
  }
}
