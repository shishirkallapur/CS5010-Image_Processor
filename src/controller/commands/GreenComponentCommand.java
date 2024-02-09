package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling green component operations.
 */
public class GreenComponentCommand {
  /**
   * Method to call the model and view for Green component operation.
   * @param arguments String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 3) {
        String imageToGreen = arguments[1];
        String newImageName = arguments[2];
        imageModel.greenImage(imageToGreen, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
