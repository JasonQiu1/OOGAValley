package oogasalad.view.editor.MapEditor;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BottomPanel extends TabPane {

  public BottomPanel(Selector ts) {
    super();
    this.setId("BottomPanel");
    Tab titlesTab = new Tab("Tiles");
    Tab natureTab = new Tab("Nature");
    Tab plantsTab = new Tab("Plants");
    Tab buildingsTab = new Tab("Buildings");

    titlesTab.setClosable(false);
    natureTab.setClosable(false);
    plantsTab.setClosable(false);
    buildingsTab.setClosable(false);

    List<SelectableView> mockTiles;
    List<SelectableView> mockPlants;
    List<SelectableView> mockBuildings;
    List<SelectableView> mockNature;
    try {
      mockTiles = getMockTiles();
      mockPlants = getMockPlants();
      mockBuildings = getMockBuildings();
      mockNature = getMockNature();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    titlesTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockTiles, ts), "Tiles"));
    plantsTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockPlants, ts), "Plants"));
    buildingsTab.setContent(
        new SelectableViewsWrapper(new SelectableViews(mockBuildings, ts), "Buildings"));
    natureTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockNature, ts), "Nature"));

    super.getTabs().addAll(titlesTab, natureTab, plantsTab, buildingsTab);
  }


  private List<SelectableView> getMockTiles() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/dirt.jpg").toURI().toURL()))), "Dirt");
    temp.add(t1);

    SelectableView t2 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/grass.jpg").toURI().toURL()))), "Grass");
    temp.add(t2);

    SelectableView t3 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/lava.jpg").toURI().toURL()))), "Lava");
    temp.add(t3);

    SelectableView t4 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/sand.jpg").toURI().toURL()))), "Sand");
    temp.add(t4);

    SelectableView t5 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/water.jpg").toURI().toURL()))), "Water");
    temp.add(t5);
    return temp;
  }

  private List<SelectableView> getMockNature() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new NatureView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/tree.png").toURI().toURL()))), "Tree");
    temp.add(t1);

    SelectableView t2 = new NatureView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/boulder.png").toURI().toURL()))),
        "Boulder");
    temp.add(t2);
    return temp;
  }

  private List<SelectableView> getMockPlants() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new PlantView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/watermelonplant.png").toURI().toURL()))),
        "Watermelon");
    temp.add(t1);

    SelectableView t2 = new PlantView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/wheat.png").toURI().toURL()))), "Wheat");
    temp.add(t2);

    SelectableView t3 = new PlantView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/sunflower.png").toURI().toURL()))),
        "Sunflower");
    temp.add(t3);
    return temp;
  }

  private List<SelectableView> getMockBuildings() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new BuildingView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/house.png").toURI().toURL()))), "House");
    temp.add(t1);

    SelectableView t2 = new BuildingView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/barn.png").toURI().toURL()))), "Barn");
    temp.add(t2);

    SelectableView t3 = new BuildingView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/shed.png").toURI().toURL()))), "Shed");
    temp.add(t3);
    return temp;
  }
}
