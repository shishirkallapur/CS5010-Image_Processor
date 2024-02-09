package controller.commands;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling histogram operations.
 */
public class HistogramCommand {
  /**
   * Method to call the model and view for Histogram operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 3) {
        String imageToHistogram = arguments[1];
        String newImageName = arguments[2];
        imageModel.histogram(imageToHistogram, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
