package oogasalad.view.editor.MapEditor;

import java.util.*;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.data.GameConfiguration;

public class BottomPanel extends TabPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.BottomPanel.";
  private final String tabLanguage = "EnglishTabs";
  private final String tilesLanguage = "EnglishTiles";
  private final String natureLanguage = "EnglishNature";
  private final String buildingsLanguage = "EnglishBuildings";
  private final String plantsLanguage = "EnglishPlants";
  //private final ResourceBundle tabResource;

  public BottomPanel(Map<String, ReadOnlyProperties> allConfigurables) {
    super();
    this.setId("BottomPanel");
    Map<String, List<SelectableView>> mapOfSelectables = getMapOfSelectables(allConfigurables);
    createTabs(mapOfSelectables);
  }

  private void createTabs(Map<String, List<SelectableView>> mapOfSelectables) {
    for(Map.Entry<String, List<SelectableView>> entry : mapOfSelectables.entrySet()){
      Tab tab = new Tab(entry.getKey());
      tab.setId(entry.getKey());
      tab.setClosable(false);
      tab.setContent(new SelectableViewBoxWrapper(new SelectableViewBox(entry.getValue()),
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
