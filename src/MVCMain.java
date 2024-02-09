import java.io.InputStreamReader;


import controller.Controller;
import controller.ControllerInterface;
import controller.Features;
import controller.InteractiveController;
import model.Model2;
import model.ModelInterface2;
import view.InterfaceView;

import view.View;

/**
 * Class to represent the main.
 */
public class MVCMain {
  /**
   * Main method for implementing the MVC design architecture.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    ModelInterface2 imageModel = new Model2();
    InterfaceView imageView = new View();
    if (args.length != 0) {
      // old controller
      ControllerInterface control =
              new Controller(imageModel, imageView, new InputStreamReader(System.in), args);
      try {
        control.executeController();
      } catch (Exception e) {
        System.out.println("Something went Wrong");
      }
    } else {
      Features control2 = new InteractiveController(imageModel, imageView);
      try {
        control2.execute();
      } catch (Exception e) {
        System.out.println("Something went Wrong");
      }
    }
  }
}
