import model.Model;
import model.Model2;
import model.ModelInterface;
import model.ModelInterface2;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test an Image model on its functionalities and outputs.
 */
public class TestModel {
  private final int[][][] img = {{{1, 2, 3},
          {4, 5, 6},
          {7, 8, 9}}};
  ModelInterface model;
  ModelInterface2 model2;
  private BufferedImage imgx;

  @Before
  public void setUp() {
    model = new Model();
    model2 = new Model2();
    int w = img.length;
    int l = img[0].length;
    imgx = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img[x][y][0];
        int green = img[x][y][1];
        int blue = img[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx.setRGB(x, y, rgb);
      }
    }
  }

  @Test
  public void testLoadImage() throws Exception {
    model.loadImage("load", imgx);
    BufferedImage b = model.saveImage("load");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  private int[][][] getMatrix(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int[][][] rgbMatrix = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        rgbMatrix[x][y][0] = red;
        rgbMatrix[x][y][1] = green;
        rgbMatrix[x][y][2] = blue;
      }
    }
    return rgbMatrix;
  }

  @Test
  public void testLoadPPM() throws Exception {
    model.loadPPMImage("loadPPM", img);
    BufferedImage b = model.saveImage("loadPPM");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSavePPM() {
    model.loadImage("SavePPM", imgx);
    StringBuilder s;
    try {
      s = model.savePPMImage("SavePPM");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String c = "P3\n" +
            "1 3\n" +
            "255\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5\n" +
            "6\n" +
            "7\n" +
            "8\n" +
            "9\n";
    assertEquals(c, s.toString());
  }

  @Test
  public void testSaveImage() {
    model.loadImage("Save", imgx);
    BufferedImage b;
    try {
      b = model.saveImage("Save");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSepia() throws Exception {
    model.loadImage("Sepia", imgx);
    model.sepiaImage("Sepia", "Sepia2");
    BufferedImage b = model.saveImage("Sepia2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{2, 2, 1}, {6, 5, 4}, {10, 9, 7}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testGetImage() throws Exception {
    model.loadImage("load", imgx);
    BufferedImage b = model.saveImage("load");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testLuma() throws Exception {
    model.loadImage("Luma", imgx);
    model.lumaImage("Luma", "Luma2");
    BufferedImage b = model.saveImage("Luma2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 1, 1}, {4, 4, 4}, {7, 7, 7}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }


  @Test
  public void testBrighten() throws Exception {
    model.loadImage("Brighten", imgx);
    model.brightenImage("Brighten", "Brighten2", 5);
    BufferedImage b = model.saveImage("Brighten2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{6, 7, 8}, {9, 10, 11}, {12, 13, 14}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testDimming() throws Exception {
    model.loadImage("Dimming", imgx);
    model.brightenImage("Dimming", "Dimming2", -5);
    BufferedImage b = model.saveImage("Dimming2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 0, 0}, {0, 0, 1}, {2, 3, 4}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testIntensity() throws Exception {
    model.loadImage("Intensity", imgx);
    model.intensityImage("Intensity", "Intensity2");
    BufferedImage b = model.saveImage("Intensity2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{2, 2, 2}, {5, 5, 5}, {8, 8, 8}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testValue() throws Exception {
    model.loadImage("Value", imgx);
    model.valueImage("Value", "Value2");
    BufferedImage b = model.saveImage("Value2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{3, 3, 3}, {6, 6, 6}, {9, 9, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testRed() throws Exception {
    model.loadImage("Red", imgx);
    model.redImage("Red", "Red2");
    BufferedImage b = model.saveImage("Red2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 0, 0}, {4, 0, 0}, {7, 0, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testGreen() throws Exception {
    model.loadImage("Green", imgx);
    model.greenImage("Green", "Green2");
    BufferedImage b = model.saveImage("Green2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 2, 0}, {0, 5, 0}, {0, 8, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlue() throws Exception {
    model.loadImage("Blue", imgx);
    model.blueImage("Blue", "Blue2");
    BufferedImage b = model.saveImage("Blue2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 0, 3}, {0, 0, 6}, {0, 0, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testFlipVertical() throws Exception {
    model.loadImage("Vertical", imgx);
    model.flipImageVertical("Vertical", "Vertical2");
    BufferedImage b = model.saveImage("Vertical2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testFlipHorizontal() throws Exception {
    model.loadImage("Horizontal", imgx);
    model.flipImageHorizontal("Horizontal", "Horizontal2");
    BufferedImage b = model.saveImage("Horizontal2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlur() throws Exception {
    model.loadImage("Blur", imgx);
    model.blurImage("Blur", "Blur2");
    BufferedImage b = model.saveImage("Blur2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 1, 1}, {2, 2, 3}, {2, 2, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpen() throws Exception {
    model.loadImage("Sharpen", imgx);
    model.sharpenImage("Sharpen", "Sharpen2");
    BufferedImage b = model.saveImage("Sharpen2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3}, {6, 7, 9}, {7, 9, 10}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testCombine() throws Exception {
    model.loadImage("Blue", imgx);
    model.blueImage("Blue", "B");
    model.loadImage("Red", imgx);
    model.redImage("Red", "R");
    model.loadImage("Green", imgx);
    model.greenImage("Green", "G");
    model.combineImage("Combine", "R", "G", "B");
    BufferedImage c = model.saveImage("Combine");
    int[][][] actualOutput = getMatrix(c);
    int[][][] expectedOutput = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testRedBlur() throws Exception {
    model.loadImage("Red", imgx);
    model.redImage("Red", "Red2");
    model.blurImage("Red2", "output");
    BufferedImage rb = model.saveImage("output");
    int[][][] actualOutput = getMatrix(rb);
    int[][][] expectedOutput = {{{0, 0, 0}, {2, 0, 0}, {2, 0, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlueBlur() throws Exception {
    model.loadImage("Blue", imgx);
    model.blueImage("Blue", "Blue2");
    model.blurImage("Blue2", "output");
    BufferedImage bb = model.saveImage("output");
    int[][][] actualOutput = getMatrix(bb);
    int[][][] expectedOutput = {{{0, 0, 1}, {0, 0, 3}, {0, 0, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testGreenBlur() throws Exception {
    model.loadImage("Green", imgx);
    model.greenImage("Green", "Green2");
    model.blurImage("Green2", "output");
    BufferedImage rb = model.saveImage("output");
    int[][][] actualOutput = getMatrix(rb);
    int[][][] expectedOutput = {{{0, 1, 0}, {0, 2, 0}, {0, 2, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testRedSharpen() throws Exception {
    model.loadImage("Red", imgx);
    model.redImage("Red", "Red2");
    model.sharpenImage("Red2", "output");
    BufferedImage rs = model.saveImage("output");
    int[][][] actualOutput = getMatrix(rs);
    int[][][] expectedOutput = {{{1, 0, 0}, {6, 0, 0}, {7, 0, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testGreenSharpen() throws Exception {
    model.loadImage("Green", imgx);
    model.greenImage("Green", "Green2");
    model.sharpenImage("Green2", "output");
    BufferedImage gs = model.saveImage("output");
    int[][][] actualOutput = getMatrix(gs);
    int[][][] expectedOutput = {{{0, 2, 0}, {0, 7, 0}, {0, 9, 0}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlueSharpen() throws Exception {
    model.loadImage("Blue", imgx);
    model.blueImage("Blue", "Blue2");
    model.sharpenImage("Blue2", "output");
    BufferedImage bs = model.saveImage("output");
    int[][][] actualOutput = getMatrix(bs);
    int[][][] expectedOutput = {{{0, 0, 3}, {0, 0, 9}, {0, 0, 10}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlurBrighten() throws Exception {
    model.loadImage("Blur", imgx);
    model.blurImage("Blur", "Blur2");
    model.brightenImage("Blur2", "output", 5);
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{5, 6, 6}, {7, 7, 8}, {7, 7, 8}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlurIntensity() throws Exception {
    model.loadImage("Blur", imgx);
    model.intensityImage("Blur", "Blur2");
    model.blurImage("Blur2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{1, 1, 1}, {2, 2, 2}, {2, 2, 2}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlurValue() throws Exception {
    model.loadImage("Blur", imgx);
    model.valueImage("Blur", "Blur2");
    model.blurImage("Blur2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{1, 1, 1}, {3, 3, 3}, {3, 3, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlurLuma() throws Exception {
    model.loadImage("Blur", imgx);
    model.lumaImage("Blur", "Blur2");
    model.blurImage("Blur2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{0, 0, 0}, {2, 2, 2}, {2, 2, 2}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testBlurSepia() throws Exception {
    model.loadImage("Blur", imgx);
    model.sepiaImage("Blur", "Blur2");
    model.blurImage("Blur2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{1, 1, 0}, {3, 2, 2}, {3, 2, 2}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpenBrighten() throws Exception {
    model.loadImage("Sharp", imgx);
    model.sharpenImage("Sharp", "Sharp2");
    model.brightenImage("Sharp2", "output", 5);
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{6, 7, 8}, {11, 12, 14}, {12, 14, 15}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpenIntensity() throws Exception {
    model.loadImage("Sharp", imgx);
    model.intensityImage("Sharp", "Sharp2");
    model.sharpenImage("Sharp2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{2, 2, 2}, {7, 7, 7}, {9, 9, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpenValue() throws Exception {
    model.loadImage("Sharp", imgx);
    model.sharpenImage("Sharp", "Sharp2");
    model.valueImage("Sharp2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{3, 3, 3}, {9, 9, 9}, {10, 10, 10}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpenLuma() throws Exception {
    model.loadImage("Sharp", imgx);
    model.lumaImage("Sharp", "Sharp2");
    model.sharpenImage("Sharp2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{1, 1, 1}, {6, 6, 6}, {7, 7, 7}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSharpenSepia() throws Exception {
    model.loadImage("Sharp", imgx);
    model.sepiaImage("Sharp", "Sharp2");
    model.sharpenImage("Sharp2", "output");
    BufferedImage out = model.saveImage("output");
    int[][][] actualOutput = getMatrix(out);
    int[][][] expectedOutput = {{{2, 2, 1}, {9, 7, 6}, {11, 10, 7}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testMultipleBlurs() throws Exception {
    model.loadImage("Blur", imgx);
    model.blurImage("Blur", "Blur2");
    model.blurImage("Blur2", "Blur3");
    BufferedImage b = model.saveImage("Blur3");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 0, 0}, {0, 0, 1}, {0, 0, 1}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testMultipleSharpen() throws Exception {
    model.loadImage("Sharp", imgx);
    model.sharpenImage("Sharp", "Sharp2");
    model.sharpenImage("Sharp2", "Sharp3");
    model.sharpenImage("Sharp3", "Sharp4");
    BufferedImage b = model.saveImage("Sharp4");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{2, 3, 5}, {10, 12, 15}, {9, 12, 13}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testMultipleFlips() throws Exception {
    model.loadImage("Horizontal", imgx);
    model.flipImageHorizontal("Horizontal", "Horizontal2");
    model.flipImageVertical("Horizontal2", "Vertical");
    model.flipImageHorizontal("Vertical", "Horizontal3");
    model.flipImageHorizontal("Horizontal3", "Horizontal4");
    BufferedImage b = model.saveImage("Horizontal4");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testColorCorrect() throws Exception {
    model.loadImage("Color", imgx);
    model.colorCorrect("Color", "Color2");
    BufferedImage b = model.saveImage("Color2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{255, 255, 253}, {7, 8, 0}, {10, 11, 3}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testColorAdjust() throws Exception {
    model.loadImage("Adjust", imgx);
    model.levelAdjust("Adjust", 5, 10, 15, "Adjust2");
    BufferedImage b = model.saveImage("Adjust2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{0, 0, 0}, {0, 0, 25}, {51, 76, 102}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testCompression() throws Exception {
    model.loadImage("Compress", imgx);
    model.compressImage("Compress", 50, "Compress2");
    BufferedImage b = model.saveImage("Compress2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 3, 4}, {4, 3, 4}, {6, 7, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testSplit() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage imgx2 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx2.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Split", imgx2);
    model.split("Split", 50, "Split2");
    BufferedImage b = model.saveImage("Split2");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
      System.out.println("\n");
    }
  }

  @Test
  public void testCombineSplitImage() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage imgx2 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx2.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Split", imgx2);
    model.split("Split", 50, "tempName");
    model.combine("tempName", "Split", "Combine");
    BufferedImage b = model.saveImage("Combine");
    int[][][] actualOutput = getMatrix(b);

    int[][][] expectedOutput = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testCombineSplitSepiaImage() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage imgx2 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx2.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Split", imgx2);
    model.sepiaImage("Split", "Sepia");
    model.split("Sepia", 50, "tempName");
    model.combine("tempName", "Split", "Combine");
    BufferedImage b = model.saveImage("Combine");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{2, 2, 1}, {6, 5, 4}, {10, 9, 7}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testCombineSplitBlurImage() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage imgx2 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx2.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Split", imgx2);
    model.blurImage("Split", "Blur");
    model.split("Blur", 50, "tempName");
    model.combine("tempName", "Split", "Combine");
    BufferedImage b = model.saveImage("Combine");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{1, 1, 2}, {3, 3, 4}, {3, 3, 4}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testCombineSplitValueImage() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage imgx2 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgx2.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Split", imgx2);
    model.valueImage("Split", "Value");
    model.split("Value", 50, "tempName");
    model.combine("tempName", "Split", "Combine");
    BufferedImage b = model.saveImage("Combine");
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{3, 3, 3}, {6, 6, 6}, {9, 9, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test
  public void testHistogram() throws Exception {
    int[][][] hist = {{{0, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0}}};
    int w = hist.length;
    int l = hist[0].length;
    BufferedImage imgy = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = hist[x][y][0];
        int green = hist[x][y][1];
        int blue = hist[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        imgy.setRGB(x, y, rgb);
      }
    }
    model.loadImage("Histogram", imgy);
    model.histogram("Histogram", "Histogram2");
    BufferedImage b = model.saveImage("Histogram2");
    int[][][] matrix = getMatrix(b);
    int[][] actualOutput = new int[6][3];
    for (int x = 0; x < actualOutput.length; x++) {
      System.arraycopy(matrix[0][x], 0, actualOutput[x], 0, 3);
    }
    int[][] expectedOutput = {{0, 0, 255}, {0, 0, 255}, {0, 0, 255}, {0, 0, 255},
                              {0, 0, 255}, {0, 0, 255}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int z = 0; z < 3; z++) {
        assertEquals(expectedOutput[x][z], actualOutput[x][z]);
      }
    }
  }

  @Test
  public void testPreview() throws Exception {
    int[][][] img2 = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                      {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    int w = img2.length;
    int l = img2[0].length;
    BufferedImage img3 = new BufferedImage(w, l, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < l; y++) {
        int red = img2[x][y][0];
        int green = img2[x][y][1];
        int blue = img2[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        img3.setRGB(x, y, rgb);
      }
    }
    model2.loadImage("Preview",img3);
    model2.sepiaImage("Preview","Sepia");
    BufferedImage img4 = model2.saveImage("Sepia");
    BufferedImage b = model2.getPreviewImage(img3,img4,50);
    int[][][] actualOutput = getMatrix(b);
    int[][][] expectedOutput = {{{2, 2, 1}, {6, 5, 4}, {10, 9, 7}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    for (int x = 0; x < actualOutput.length; x++) {
      for (int y = 0; y < actualOutput[0].length; y++) {
        for (int z = 0; z < 3; z++) {
          assertEquals(expectedOutput[x][y][z], actualOutput[x][y][z]);
        }
      }
    }
  }

  @Test(expected = Exception.class)
  public void testException() throws Exception {
    model.saveImage("test");
    fail("This should not be printed");
  }
}
