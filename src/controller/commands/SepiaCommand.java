package controller.commands;

import java.util.Objects;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling Sepia operation.
 */
public class SepiaCommand {
  /**
   * Method to call the model and view for Sepia operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 3) {
        String imageToSepia = arguments[1];
        String newImageName = arguments[2];
        imageModel.sepiaImage(imageToSepia, newImageName);
      } else if (arguments.length == 5) {
        if (!Objects.equals(arguments[3], "split")) {
          throw new Exception();
        }
        String imageToSepia = arguments[1];
        String newImageName = arguments[2];
        int percent = Integer.parseInt(arguments[4]);
        if (percent < 0 || percent > 100) {
          throw new Exception();
        }
        String tempName = "tempName";
        imageModel.split(imageToSepia, percent, tempName);
        imageModel.sepiaImage(tempName, tempName);
        imageModel.combine(tempName, imageToSepia, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
