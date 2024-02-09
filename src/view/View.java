package view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


import controller.Features;

/**
 * Class for view component of the MVC design. It contains all the methods
 * for viewing in GUI alongside the error messages to be displayed when an
 * exception is thrown.
 */
public class View extends JFrame implements InterfaceView {

  private final JPanel imagePanel;
  private final JPanel histogramPanel;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton applyButton;
  private final JButton cancelButton;
  private final JButton confirmButton;
  private final JComboBox<String> optionsDropdown;
  private final JSlider previewSlider;
  private final JCheckBox previewCheckbox;
  private BufferedImage currentImage;

  /**
   * Constructor for initialising values for the View class and creating the layout of GUI.
   */
  public View() {
    setTitle("Image Processing Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1000, 800));
    // Initialize components
    imagePanel = new JPanel();
    histogramPanel = new JPanel();
    JPanel histogramContainer = new JPanel();
    histogramContainer.setLayout(new BoxLayout(histogramContainer, BoxLayout.Y_AXIS));
    histogramContainer.add(Box.createVerticalGlue());
    histogramContainer.add(histogramPanel);
    histogramContainer.add(Box.createVerticalGlue());
    imagePanel.setLayout(new BorderLayout());
    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    previewSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    previewSlider.setMajorTickSpacing(10);
    previewSlider.setPaintTicks(true);
    previewSlider.setPaintLabels(true);
    previewCheckbox = new JCheckBox();
    JPanel sliderPanel = new JPanel();
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
    sliderPanel.add(new JLabel("Operation Preview: "));
    sliderPanel.add(previewSlider);

    loadButton = new JButton("Load");
    loadButton.setActionCommand("load button");
    saveButton = new JButton("Save");
    saveButton.setActionCommand("save button");
    applyButton = new JButton("Apply");
    applyButton.setActionCommand("apply button");
    cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("cancel button");
    confirmButton = new JButton("Confirm");
    confirmButton.setActionCommand("confirm button");
    optionsDropdown = new JComboBox<>(new String[]{"--Select--", "Blur", "Sepia", "Luma",
        "Horizontal-Flip", "Vertical-Flip", "Red", "Green", "Blue", "Value",
        "Intensity", "Sharpen", "Color-Correct", "Levels-Adjust", "Compress", "Brighten", "Dither"});


    //Set the layout
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(loadButton);
    topPanel.add(saveButton);
    topPanel.add(optionsDropdown);
    topPanel.add(applyButton);
    topPanel.add(previewCheckbox);
    topPanel.add(sliderPanel);
    topPanel.add(cancelButton);
    topPanel.add(confirmButton);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setLeftComponent(imageScrollPane);
    splitPane.setRightComponent(histogramContainer);
    splitPane.setResizeWeight(0.7);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(topPanel, BorderLayout.NORTH);
    getContentPane().add(splitPane, BorderLayout.CENTER);

    pack();
    setLocationRelativeTo(null);
  }

  @Override
  public void addButtonFeatures(Features features) {
    loadButton.addActionListener(e -> features.loadButton());
    saveButton.addActionListener(e -> features.saveButton());
    applyButton.addActionListener(e -> features.applyButton());
    cancelButton.addActionListener(e -> features.cancelButton());
    confirmButton.addActionListener(e -> features.confirmButton());
  }

  @Override
  public void addSliderFeatures(Features features) {
    previewSlider.addChangeListener(e -> features.sliderButton());
  }

  @Override
  public void addCheckboxFeatures(Features features) {
    previewCheckbox.addItemListener(e -> features.checkboxButton());
  }

  @Override
  public void enableSlider() {
    previewSlider.setVisible(true);
  }

  @Override
  public void disableSlider() {
    previewSlider.setVisible(false);
  }

  @Override
  public void unTickCheckbox() {
    previewCheckbox.setSelected(false);
  }

  @Override
  public boolean getTickValue() {
    return previewCheckbox.isSelected();
  }

  @Override
  public boolean popupConfirmationBox() {
    int val = JOptionPane.showConfirmDialog(null,
            "Changes have been made. Do you want to load a new image without saving?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
    return (val == JOptionPane.YES_OPTION);
  }

  @Override
  public void takeInput() {
    System.out.print("Please Enter Command(or enter quit to close):");
  }

  @Override
  public void exitProgram() {
    System.out.println("Exiting the program.");
  }

  @Override
  public void errorWhileReading() {
    System.out.println("Error while reading file.");
  }

  @Override
  public void wrongCommand() {
    System.out.println("Wrong Command entered.");
  }

  @Override
  public void wrongPath() {
    System.out.println("Wrong Filepath provided.");
  }

  @Override
  public void commandNotSupported() {
    System.out.println("Command not supported.");
  }

  @Override
  public void runFromCommandLine() {
    System.out.println("Running the Script File provided in Command Line");
  }

  @Override
  public void setDisplay() {
    setVisible(true);
  }

  @Override
  public void displayImage(BufferedImage bufferedImage) {
    currentImage = bufferedImage;
    ImageIcon icon = new ImageIcon(bufferedImage);
    JLabel label = new JLabel(icon);
    imagePanel.removeAll();
    imagePanel.add(label);
    imagePanel.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    imagePanel.revalidate();
    imagePanel.repaint();
  }

  @Override
  public void displayHistogram(BufferedImage bufferedImage) {
    ImageIcon histogramIcon = new ImageIcon(bufferedImage);
    JLabel label = new JLabel(histogramIcon);
    histogramPanel.removeAll();
    histogramPanel.add(label);
    histogramPanel.setPreferredSize(new Dimension(histogramIcon.getIconWidth(),
            histogramIcon.getIconHeight()));
    histogramPanel.revalidate();
    histogramPanel.repaint();
  }

  @Override
  public BufferedImage getBImage() {
    return currentImage;
  }

  @Override
  public String saveJFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return "";
  }

  @Override
  public String openJFileChooser(String currentDirectory) {
    JFileChooser fileChooser = new JFileChooser(currentDirectory);
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return "";
  }

  @Override
  public String getSelectedItem() {

    return (String) optionsDropdown.getSelectedItem();
  }

  @Override
  public String getSecondaryInput(String s) {
    String userInput = JOptionPane.showInputDialog(this, s);
    if (Objects.equals(userInput, null)) {
      return "null";
    }
    return userInput;
  }

  @Override
  public int getSliderValue() {
    return previewSlider.getValue();
  }

  @Override
  public boolean isSliderSelected() {
    return previewCheckbox.isSelected();
  }

  @Override
  public void displayMessagePopup(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

}