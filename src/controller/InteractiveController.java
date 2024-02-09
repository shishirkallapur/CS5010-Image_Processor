package controller;

import java.awt.image.BufferedImage;
import java.util.Objects;

import controller.commands.LoadCommand;
import controller.commands.SaveCommand;
import model.ModelInterface2;
import view.InterfaceView;

/**
 * This class contains utility methods to read inputs from the user from the GUI
 * or test case at runtime and returns an appropriate output after performing
 * operations on the user's input.
 */
public class InteractiveController implements Features {
  private final ModelInterface2 imageModel;
  private final InterfaceView imageView;
  private String[] command;
  private String filePath;
  private BufferedImage originalImage;
  private BufferedImage operatedImage;
  private BufferedImage originalHistogramImage;
  private BufferedImage operatedHistogramImage;
  private boolean isOperationValidForPreview;
  private boolean confirmClicked;
  private boolean cancelClicked;
  private String currentImageName;
  private String newImageName;
  private String currentHistImageName;
  private String newHistImageName;
  private String previewImageName;
  private String previewHistImageName;
  private boolean isImageModified;

  /**
   * This is the constructor for the interactive controller class.
   *
   * @param imageModel is the model in the MVC and contains the logic for operations.
   * @param imageView  is the view in the MVC and contains the UI/text display for the operation.
   */
  public InteractiveController(ModelInterface2 imageModel, InterfaceView imageView) {
    this.imageModel = imageModel;
    this.imageView = imageView;
    this.isOperationValidForPreview = false;
    this.cancelClicked = true;
    this.confirmClicked = true;
    this.isImageModified = false;
    this.currentImageName = "currentImage";
    this.newImageName = "newImage";
    this.currentHistImageName = "currentHistImage";
    this.newHistImageName = "newHistImage";
    this.previewImageName = "previewImage";
    this.previewHistImageName = "previewHistogramImage";
  }

  @Override
  public void execute() {
    imageView.setDisplay();
    imageView.addButtonFeatures(this);
    imageView.addSliderFeatures(this);
    imageView.addCheckboxFeatures(this);
    imageView.disableSlider();
  }

  @Override
  public void loadButton() {
    if (isImageModified) {
      boolean response = imageView.popupConfirmationBox();
      if (response) {
        isImageModified = false;
        loadAndDisplayImage();
      }
    } else {
      loadAndDisplayImage();
    }
  }

  private void loadAndDisplayImage() {
    filePath = imageView.openJFileChooser(".");
    if (!Objects.equals(filePath, "")) {
      LoadCommand load = new LoadCommand();
      try {
        command = new String[]{"load", filePath, currentImageName};
        load.run(command, imageModel, imageView);
        imageModel.histogram(currentImageName, "histNewImage");
        originalImage = imageModel.saveImage(currentImageName);
        originalHistogramImage = imageModel.saveImage("histNewImage");
        imageView.displayImage(originalImage);
        imageView.displayHistogram(originalHistogramImage);
        imageView.displayMessagePopup("Image has been loaded successfully!");
      } catch (Exception ex) {
        //Do Nothing.
      }
    }
  }

  @Override
  public void saveButton() {
    try {
      originalImage = imageView.getBImage();
      if (originalImage == null) {
        imageView.displayMessagePopup("Image has not been loaded properly");
        throw new Exception();
      }
      filePath = imageView.saveJFileChooser();
      if (!Objects.equals(filePath, "")) {
        imageModel.loadImage(currentImageName, originalImage);
        command = new String[]{"save", filePath, currentImageName};
        SaveCommand save = new SaveCommand();
        save.run(command, imageModel, imageView);
        imageView.displayMessagePopup("Image has been saved successfully!");
        isImageModified = false;
      }
    } catch (Exception ex) {
      imageView.displayMessagePopup("Image not saved successfully!");
      //Do Nothing.
    }
  }

  @Override
  public void applyButton() {
    try {
      if (!cancelClicked && !confirmClicked) {
        imageView.displayMessagePopup("Operation still underway. Please CONFIRM or CANCEL!");
        throw new Exception();
      }
      confirmClicked = false;
      cancelClicked = false;
      isImageModified = true;
      String selectedOption = imageView.getSelectedItem();
      switch (selectedOption) {
        case "Blur":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.blurImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Blur function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Sepia":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.sepiaImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Sepia function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Luma":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.lumaImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Luma function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Horizontal-Flip":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.flipImageHorizontal(currentImageName, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Horizontal flip has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Vertical-Flip":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.flipImageVertical(currentImageName, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Vertical flip has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Red":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.redImage(currentImageName, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Red component has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Green":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.greenImage(currentImageName, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Green component has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Blue":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.blueImage(currentImageName, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Blue component has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Value":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.valueImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Value function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Intensity":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.intensityImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Intensity function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Dither":
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.ditherImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Dither function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Brighten":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            int value = Integer.parseInt(imageView.getSecondaryInput(
                    "Enter value for brighten: "));
            imageModel.brightenImage(currentImageName, newImageName, value);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Brighten function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Sharpen":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.sharpenImage(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Sharpen function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Compress":
          isOperationValidForPreview = false;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            int value = Integer.parseInt(imageView.getSecondaryInput(
                    "Enter percentage of compression:"));
            if (value < 0 || value > 100) {
              imageView.displayMessagePopup("Invalid Compression value!");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.compressImage(currentImageName, value, newImageName);
            applyHelper();
            imageView.displayImage(operatedImage);
            imageView.displayHistogram(operatedHistogramImage);
            imageView.displayMessagePopup("Compression has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Levels-Adjust":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            int black = Integer.parseInt(imageView.getSecondaryInput("Enter value for black: "));
            int mid = Integer.parseInt(imageView.getSecondaryInput("Enter value for mid: "));
            int white = Integer.parseInt(imageView.getSecondaryInput("Enter value for white: "));
            if (black < 0 || black > 255 || mid < 0 || mid > 255 || white < 0 || white > 255) {
              imageView.displayMessagePopup("Invalid level values provided!");
              throw new Exception();
            }
            if (!(black < mid && mid < white)) {
              imageView.displayMessagePopup("Invalid level values provided!");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.levelAdjust(currentImageName, black, mid, white, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Levels adjust function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        case "Color-Correct":
          isOperationValidForPreview = true;
          try {
            originalImage = imageView.getBImage();
            if (originalImage == null) {
              imageView.displayMessagePopup("Image has not been loaded properly");
              throw new Exception();
            }
            imageModel.loadImage(currentImageName, originalImage);
            imageModel.colorCorrect(currentImageName, newImageName);
            applyHelper();
            if (imageView.isSliderSelected()) {
              imageView.displayImage(originalImage);
              imageView.displayHistogram(originalHistogramImage);
            } else {
              imageView.displayImage(operatedImage);
              imageView.displayHistogram(operatedHistogramImage);
            }
            imageView.displayMessagePopup("Color correct function has been executed!");
          } catch (Exception ex) {
            //Do nothing
          }
          break;
        default:
          imageView.displayMessagePopup("Please select a valid option!");
          //Do Nothing
      }
    } catch (Exception ex) {
      //Do nothing.
    }
  }

  private void applyHelper() throws Exception {
    operatedImage = imageModel.saveImage(newImageName);
    imageModel.histogram(currentImageName, currentHistImageName);
    imageModel.histogram(newImageName, newHistImageName);

    originalHistogramImage = imageModel.saveImage(currentHistImageName);
    operatedHistogramImage = imageModel.saveImage(newHistImageName);
  }

  @Override
  public void cancelButton() {
    cancelClicked = true;
    imageView.disableSlider();
    imageView.unTickCheckbox();
    if (!originalImage.equals(operatedImage)) {
      operatedImage = originalImage;
      operatedHistogramImage = originalHistogramImage;
      imageView.displayImage(originalImage);
      imageView.displayHistogram(originalHistogramImage);
      imageView.displayMessagePopup("Execution has been cancelled!");
    }

  }

  @Override
  public void confirmButton() {
    confirmClicked = true;
    imageView.disableSlider();
    imageView.unTickCheckbox();
    if (!originalImage.equals(operatedImage)) {
      originalImage = operatedImage;
      originalHistogramImage = operatedHistogramImage;
      imageView.displayImage(operatedImage);
      imageView.displayHistogram(operatedHistogramImage);
      imageView.displayMessagePopup("Execution has been confirmed!");
    }
  }

  @Override
  public void sliderButton() {
    int percent = imageView.getSliderValue();
    boolean selected = imageView.isSliderSelected();
    if (selected && isOperationValidForPreview) {
      if (percent == 0) {
        imageView.displayImage(originalImage);
        imageView.displayHistogram(originalHistogramImage);
      } else {
        try {
          BufferedImage previewImage = imageModel.getPreviewImage(originalImage,
                  operatedImage, percent);
          imageModel.loadImage(previewImageName, previewImage);
          imageModel.histogram(previewImageName, previewHistImageName);
          BufferedImage previewHistogramImage = imageModel.saveImage(previewHistImageName);
          imageView.displayImage(previewImage);
          imageView.displayHistogram(previewHistogramImage);
        } catch (Exception ex) {
          //Do Nothing.
        }
      }
    }
  }

  @Override
  public void checkboxButton() {
    boolean val = imageView.getTickValue();
    if (val) {
      imageView.enableSlider();
    } else {
      imageView.disableSlider();
    }
  }
}
