package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
import controller.commands.BrightenCommand;
import controller.commands.ColorCorrectCommand;
import controller.commands.CompressCommand;
import controller.commands.DitherCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.HistogramCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.IntensityComponentCommand;
import controller.commands.LevelAdjustCommand;
import controller.commands.LoadCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.RGBCombineCommand;
import controller.commands.RGBSplitCommand;
import controller.commands.RedComponentCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.ValueComponentCommand;
import controller.commands.VerticalFlipCommand;
import model.ModelInterface2;
import view.InterfaceView;

/**
 * This class contains utility methods to read inputs from the user or test
 * case at runtime and returns an appropriate output after performing
 * operations on the user's input.
 */
public class Controller implements ControllerInterface {
  private final Readable inputReader;
  private final ModelInterface2 imageModel;
  private final InterfaceView imageView;
  private final String[] commandLineArguments;

  /**
   * This is the constructor for the controller class.
   * @param imageModel is the model in the MVC and contains the logic for operations.
   * @param imageView is the view in the MVC and contains the UI/text display for the operation.
   * @param inputReader is the readable object which can take input from the user.
   * @param args is the command line arguments provided by the user.
   */
  public Controller(ModelInterface2 imageModel, InterfaceView imageView,
                    Readable inputReader, String[] args) {
    this.inputReader = inputReader;
    this.imageModel = imageModel;
    this.imageView = imageView;
    this.commandLineArguments = args;
  }

  @Override
  public void executeController()
          throws Exception {
    if (commandLineArguments.length != 0) {
      if (commandLineArguments.length == 2 && commandLineArguments[0].equals("-file")) {
        imageView.runFromCommandLine();
        readScriptFile(commandLineArguments[1]);
        imageView.exitProgram();
        System.exit(0);
      }
    }
    if (commandLineArguments.length == 1 && commandLineArguments[0].equals("-text")) {
      while (true) {
        imageView.takeInput();
        Scanner sc = new Scanner(this.inputReader);
        if (!sc.hasNext()) {
          break;
        }
        String command = sc.nextLine();
        String[] finalCommand = command.split(" ");
        if (finalCommand.length == 2 && finalCommand[0].equals("run")) {
          readScriptFile(finalCommand[1]);
          continue;
        }

        runCommands(finalCommand);
      }
    }
  }

  private void readScriptFile(String scriptFileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(scriptFileName))) {
      String command;
      while ((command = br.readLine()) != null) {
        runCommands(command.split(" "));
      }
    } catch (Exception e) {
      imageView.errorWhileReading();
    }
  }

  protected void runCommands(String[] arguments)
          throws Exception {
    String param = arguments[0];
    switch (param) {
      case "load":
        LoadCommand loadCommand = new LoadCommand();
        try {
          loadCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "save":
        SaveCommand saveCommand = new SaveCommand();
        try {
          saveCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          throw new Exception(e);
        }
        break;
      case "brighten":
        BrightenCommand brightenCommand = new BrightenCommand();
        try {
          brightenCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "vertical-flip":
        VerticalFlipCommand verticalFlipCommand = new VerticalFlipCommand();
        try {
          verticalFlipCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "horizontal-flip":
        HorizontalFlipCommand horizontalFlipCommand = new HorizontalFlipCommand();
        try {
          horizontalFlipCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "sepia":
        SepiaCommand sepiaCommand = new SepiaCommand();
        try {
          sepiaCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "red-component":
        RedComponentCommand redComponentCommand = new RedComponentCommand();
        try {
          redComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "blue-component":
        BlueComponentCommand blueComponentCommand = new BlueComponentCommand();
        try {
          blueComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "green-component":
        GreenComponentCommand greenComponentCommand = new GreenComponentCommand();
        try {
          greenComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "value-component":
        ValueComponentCommand valueComponentCommand = new ValueComponentCommand();
        try {
          valueComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "luma-component":
        LumaComponentCommand lumaComponentCommand = new LumaComponentCommand();
        try {
          lumaComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "intensity-component":
        IntensityComponentCommand intensityComponentCommand = new IntensityComponentCommand();
        try {
          intensityComponentCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "blur":
        BlurCommand blurCommand = new BlurCommand();
        try {
          blurCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "sharpen":
        SharpenCommand sharpenCommand = new SharpenCommand();
        try {
          sharpenCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "rgb-split":
        RGBSplitCommand rgbSplitCommand = new RGBSplitCommand();
        try {
          rgbSplitCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "rgb-combine":
        RGBCombineCommand rgbCombineCommand = new RGBCombineCommand();
        try {
          rgbCombineCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "compress":
        CompressCommand compressCommand = new CompressCommand();
        try {
          compressCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "histogram":
        HistogramCommand histogramCommand = new HistogramCommand();
        try {
          histogramCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "color-correct":
        ColorCorrectCommand colorCorrectCommand = new ColorCorrectCommand();
        try {
          colorCorrectCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "levels-adjust":
        LevelAdjustCommand levelAdjustCommand = new LevelAdjustCommand();
        try {
          levelAdjustCommand.run(arguments, imageModel, imageView);
        } catch (Exception e) {
          //Do Nothing.
        }
        break;
      case "dither":
        DitherCommand ditherCommand = new DitherCommand();
        try {
          ditherCommand.run(arguments,imageModel,imageView);
        } catch (Exception e){
          //Do Nothing.
        }
        break;
      case "quit":
        imageView.exitProgram();
        System.exit(0);
        break;
      default:
        imageView.commandNotSupported();
        break;
    }
  }
}


