package oogasalad.fake.config;

public abstract class BaseConfig {

  private final String imagePath;

  private final String id;

  public BaseConfig(String imagePath, String id) {
    this.imagePath = imagePath;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getImagePath() {
    return imagePath;
  }

}
