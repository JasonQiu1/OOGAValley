package oogasalad.view.editor.MapEditor;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BottomPanel extends TabPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.BottomPanel.";
  private final String tabLanguage = "EnglishTabs";
  private final String tilesLanguage = "EnglishTiles";
  private final String natureLanguage = "EnglishNature";
  private final String buildingsLanguage = "EnglishBuildings";
  private final String plantsLanguage = "EnglishPlants";
  private final ResourceBundle tabResource;
  private final ResourceBundle tilesResource;
  private final ResourceBundle natureResource;
  private final ResourceBundle buildingsResource;
  private final ResourceBundle plantsResource;

  public BottomPanel(Selector ts) {
    super();
    this.setId("BottomPanel");

    tabResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + tabLanguage);
    tilesResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + tilesLanguage);
    natureResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + natureLanguage);
    plantsResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + plantsLanguage);
    buildingsResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + buildingsLanguage);

    Tab titlesTab = new Tab(tabResource.getString("tiles"));
    Tab natureTab = new Tab(tabResource.getString("nature"));
    Tab plantsTab = new Tab(tabResource.getString("plants"));
    Tab buildingsTab = new Tab(tabResource.getString("buildings"));

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
    titlesTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockTiles, ts),
        tabResource.getString("tiles")));
    plantsTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockPlants, ts),
        tabResource.getString("plants")));
    buildingsTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockBuildings, ts),
        tabResource.getString("buildings")));
    natureTab.setContent(new SelectableViewsWrapper(new SelectableViews(mockNature, ts),
        tabResource.getString("nature")));

    super.getTabs().addAll(titlesTab, natureTab, plantsTab, buildingsTab);
  }


  private List<SelectableView> getMockTiles() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new TileView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/dirt.jpg").toURI().toURL()))),
        tilesResource.getString("dirt"));
    temp.add(t1);

    SelectableView t2 = new TileView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/grass.jpg").toURI().toURL()))),
        tilesResource.getString("grass"));
    temp.add(t2);

    SelectableView t3 = new TileView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/lava.jpg").toURI().toURL()))),
        tilesResource.getString("lava"));
    temp.add(t3);

    SelectableView t4 = new TileView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/sand.jpg").toURI().toURL()))),
        tilesResource.getString("sand"));
    temp.add(t4);

    SelectableView t5 = new TileView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/water.jpg").toURI().toURL()))),
        tilesResource.getString("water"));
    temp.add(t5);
    return temp;
  }

  private List<SelectableView> getMockNature() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new NatureView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/tree.png").toURI().toURL()))),
        natureResource.getString("tree"));
    temp.add(t1);

    SelectableView t2 = new NatureView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/boulder.png").toURI().toURL()))),
        natureResource.getString("boulder"));
    temp.add(t2);
    return temp;
  }

  private List<SelectableView> getMockPlants() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new PlantView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/watermelonplant.png").toURI().toURL()))),
        plantsResource.getString("watermelon"));
    temp.add(t1);

    SelectableView t2 = new PlantView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/wheat.png").toURI().toURL()))),
        plantsResource.getString("wheat"));
    temp.add(t2);

    SelectableView t3 = new PlantView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/sunflower.png").toURI().toURL()))),
        plantsResource.getString("sunflower"));
    temp.add(t3);
    return temp;
  }

  private List<SelectableView> getMockBuildings() throws MalformedURLException {
    List<SelectableView> temp = new ArrayList<>();
    SelectableView t1 = new BuildingView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/house.png").toURI().toURL()))),
        buildingsResource.getString("house"));
    temp.add(t1);

    SelectableView t2 = new BuildingView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/barn.png").toURI().toURL()))),
        buildingsResource.getString("barn"));
    temp.add(t2);

    SelectableView t3 = new BuildingView(new ImageView(
        new Image(String.valueOf(new File("src/main/resources/img/shed.png").toURI().toURL()))),
        buildingsResource.getString("shed"));
    temp.add(t3);
    return temp;
  }
}
