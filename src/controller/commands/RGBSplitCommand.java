package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling RGB Split operation.
 */
public class RGBSplitCommand {
  /**
   * Method to call the model and view for RGB Split operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 5) {
        String imageNameOriginal = arguments[1];
        String newImageNameRed = arguments[2];
        String newImageNameGreen = arguments[3];
        String newImageNameBlue = arguments[4];
        imageModel.redImage(imageNameOriginal, newImageNameRed);
        imageModel.greenImage(imageNameOriginal, newImageNameGreen);
        imageModel.blueImage(imageNameOriginal, newImageNameBlue);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
