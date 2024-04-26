package oogasalad.view.editor.MapEditor;

import java.util.*;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import oogasalad.model.api.ReadOnlyProperties;

public class BottomPanel extends TabPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.BottomPanel.";
  private final String tabLanguage = "EnglishTabs";
  private final String tilesLanguage = "EnglishTiles";
  private final String natureLanguage = "EnglishNature";
  private final String buildingsLanguage = "EnglishBuildings";
  private final String plantsLanguage = "EnglishPlants";
  //private final ResourceBundle tabResource;

  public BottomPanel(Selector ts, Map<String, ReadOnlyProperties> allConfigurables) {
    super();
    this.setId("BottomPanel");

    //tabResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + tabLanguage);

//    Tab titlesTab = new Tab(tabResource.getString("tiles"));
//    Tab natureTab = new Tab(tabResource.getString("nature"));
//    Tab plantsTab = new Tab(tabResource.getString("plants"));
//    Tab buildingsTab = new Tab(tabResource.getString("buildings"));
//
//    titlesTab.setClosable(false);
//    natureTab.setClosable(false);
//    plantsTab.setClosable(false);
//    buildingsTab.setClosable(false);

//    List<SelectableView> mockTiles;
//    List<SelectableView> mockPlants;
//    List<SelectableView> mockBuildings;
//    List<SelectableView> mockNature;
    Map<String, List<SelectableView>> mapOfSelectables = getMapOfSelectables(allConfigurables);
    createTabs(ts, mapOfSelectables);


//    titlesTab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(mockTiles, ts),
//        tabResource.getString("tiles")));
//    plantsTab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(mockPlants, ts),
//        tabResource.getString("plants")));
//    buildingsTab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(mockBuildings, ts),
//        tabResource.getString("buildings")));
//    natureTab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(mockNature, ts),
//        tabResource.getString("nature")));

    //super.getTabs().addAll(titlesTab, natureTab, plantsTab, buildingsTab);
  }

  private void createTabs(Selector ts, Map<String, List<SelectableView>> mapOfSelectables) {
    for(Map.Entry<String, List<SelectableView>> entry : mapOfSelectables.entrySet()){
      Tab tab = new Tab(entry.getKey());
      tab.setClosable(false);
      tab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(entry.getValue(), ts),
              (entry.getKey())));
      super.getTabs().add(tab);
    }
  }


  private Map<String, List<SelectableView>> getMapOfSelectables(Map<String, ReadOnlyProperties> allConfigurables) {
    Map<String, List<SelectableView>> mapOfSelectables = new TreeMap<>();
    for(ReadOnlyProperties properties : allConfigurables.values()){
      String type = properties.getString("type");
      if(!mapOfSelectables.containsKey(type)){
        mapOfSelectables.put(type, new ArrayList<>());
      }
      mapOfSelectables.get(type).add(new SelectableView(properties.getString("image"), properties.getString("name")));
    }
    return mapOfSelectables;
  }

}
