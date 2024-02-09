package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling Compress operation.
 */
public class CompressCommand {
  /**
   * Method to call the model and view for Compress operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 4) {
        String imageToCompress = arguments[2];
        String newImageName = arguments[3];
        int percentage = Integer.parseInt(arguments[1]);
        if (percentage < 0 || percentage > 100) {
          throw new Exception();
        }
        imageModel.compressImage(imageToCompress, percentage, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
