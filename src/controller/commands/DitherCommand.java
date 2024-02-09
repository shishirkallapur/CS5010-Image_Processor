package controller.commands;

import java.util.Objects;


import model.ModelInterface2;
import view.InterfaceView;
/**
 * Class for controlling dither operation.
 */
public class DitherCommand {
  public void run(String[] arguments, ModelInterface2 imageModel, InterfaceView imageView)
          throws Exception {
    try {
      if (arguments.length == 3) {
        String imageToDither = arguments[1];
        String newImageName = arguments[2];
        //Image image = imageModel.getImage(imageToIntensity);
        imageModel.ditherImage(imageToDither, newImageName);
      } else if (arguments.length == 5) {
        if (!Objects.equals(arguments[3], "split")) {
          throw new Exception();
        }
        String imageToDither = arguments[1];
        String newImageName = arguments[2];
        int percent = Integer.parseInt(arguments[4]);
        if (percent < 0 || percent > 100) {
          throw new Exception();
        }
        String tempName = "tempName";
        imageModel.split(imageToDither, percent, tempName);
        imageModel.ditherImage(tempName, tempName);
        imageModel.combine(tempName, imageToDither, newImageName);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      imageView.wrongCommand();
      throw new Exception(e);
    }
  }
}
