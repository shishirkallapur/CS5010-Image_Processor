package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for brighten controller.
 */
public class BrightenCommand {
  /**
   * Method to call the model and view for Brighten operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 4) {
        String imageToBrighten = arguments[2];
        int value = Integer.parseInt(arguments[1]);
        String newImageName = arguments[3];
        imageModel.brightenImage(imageToBrighten, newImageName, value);

      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
