package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling RGB Combine operation.
 */
public class RGBCombineCommand {
  /**
   * Method to call the model and view for RGB combine operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 5) {
        String newImageName = arguments[1];
        String redImageName = arguments[2];
        String greenImageName = arguments[3];
        String blueImageName = arguments[4];
        imageModel.combineImage(newImageName, redImageName, greenImageName, blueImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
