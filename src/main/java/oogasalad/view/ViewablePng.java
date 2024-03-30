package oogasalad.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.image.Image;
import oogasalad.view.exception.FileNotPngException;

/**
 * Wrapper for choosing a png image for an object
 */
public class ViewablePng {

  private final Image image;

  /**
   * Construct a viewable png class
   *
   * @param url the location of the png file
   * @throws FileNotPngException
   * @throws FileNotFoundException
   */
  public ViewablePng(String url)
      throws FileNotPngException, FileNotFoundException {
    checkUrl(url);
    InputStream inputStream = new FileInputStream(url);
    image = new Image(inputStream);
  }

  /**
   * Construct a viewable png class with the specified width and height
   *
   * @param url    the location of the png file
   * @param width  the width the image should be resized to
   * @param height the height the image should be resized to
   * @throws FileNotPngException
   * @throws FileNotFoundException
   */
  public ViewablePng(String url, double width, double height)
      throws FileNotPngException, FileNotFoundException {
    checkUrl(url);
    InputStream inputStream = new FileInputStream(url);
    image = new Image(inputStream, width, height, true, true);
  }

  /**
   * Get the javafx image class
   *
   * @return javafx image class
   */
  public Image getImage() {
    return image;
  }


  private void checkUrl(String url) throws FileNotFoundException, FileNotPngException {
    if (url == null) {
      throw new FileNotFoundException("file url cannot be null");
    }
    File f = new File(url);
    if (!f.exists()) {
      throw new FileNotFoundException("file not found");
    } else if (!url.endsWith(".png")) {
      throw new FileNotPngException("File type not png");
    }
  }
}
