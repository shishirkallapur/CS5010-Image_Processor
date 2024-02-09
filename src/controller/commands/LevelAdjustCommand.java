package controller.commands;

import java.util.Objects;

import model.ModelInterface;
import view.InterfaceView;

/**
 * Class for controlling level adjustment operation.
 */
public class LevelAdjustCommand {
  /**
   * Method to call the model and view for Level adjustment operation.
   *
   * @param arguments  String array containing parsed tokens.
   * @param imageModel Image model to call.
   * @param imageView  Image view to call.
   */
  public void run(String[] arguments, ModelInterface imageModel,
                  InterfaceView imageView) throws Exception {
    try {
      if (arguments.length == 6) {
        String imageToAdjust = arguments[4];
        String newImageName = arguments[5];
        int black = Integer.parseInt(arguments[1]);
        int mid = Integer.parseInt(arguments[2]);
        int white = Integer.parseInt(arguments[3]);
        if (black < 0 || black > 255 || mid < 0 || mid > 255 || white < 0 || white > 255) {
          throw new Exception();
        }
        if (!(black < mid && mid < white)) {
          throw new Exception();
        }
        imageModel.levelAdjust(imageToAdjust, black, mid, white, newImageName);

      } else if (arguments.length == 8) {
        String imageToAdjust = arguments[4];
        String newImageName = arguments[5];
        int black = Integer.parseInt(arguments[1]);
        int mid = Integer.parseInt(arguments[2]);
        int white = Integer.parseInt(arguments[3]);
        int percent = Integer.parseInt(arguments[7]);
        if (percent < 0 || percent > 100) {
          throw new Exception();
        }
        if (!Objects.equals(arguments[6], "split")) {
          throw new Exception();
        }
        if (black < 0 || black > 255 || mid < 0 || mid > 255 || white < 0 || white > 255) {
          throw new Exception();
        }
        if (!(black < mid && mid < white)) {
          throw new Exception();
        }
        String tempName = "tempName";
        imageModel.split(imageToAdjust, percent, tempName);
        imageModel.levelAdjust(tempName, black, mid, white, tempName);
        imageModel.combine(tempName, imageToAdjust, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
