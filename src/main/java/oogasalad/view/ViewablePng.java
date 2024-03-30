package oogasalad.view;

import javafx.scene.image.Image;
import oogasalad.view.exception.FileDoesNotExistException;
import oogasalad.view.exception.FileNotPngException;

/**
 * Wrapper for choosing a png image for an object
 */
public class ViewablePng {

  private final Image image;
  public ViewablePng(String url) throws FileNotPngException, FileDoesNotExistException{
    if (url==null){
      throw new FileDoesNotExistException("file does not exist");
    }
    if (!checkValidFile(url)){
      throw new FileNotPngException("File type not png");
    }
    image = new Image(url);
  }

  public Image getImage(){
    return image;
  }

  private boolean checkValidFile(String url){
    return url.endsWith(".png");
  }



}
