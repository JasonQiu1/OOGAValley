package oogasalad.fake.object.bag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oogasalad.fake.Game;
import oogasalad.fake.config.item.ToolConfig;
import oogasalad.fake.map.Coord;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;

public class ToolItem extends BagItem {

  private final ToolConfig toolConfig;

  public ToolItem(ToolConfig toolConfig, int number) {
    super(number, toolConfig.getId());
    this.toolConfig = toolConfig;
  }

  @Override
  public boolean interact(Coord coord, Game game) {
    // if we found the plant
    if (game.getGameMap().getPlantPositionMap().get(coord) != null) {
      Plant plant = game.getGameMap().getPlantPositionMap().get(coord);
      Map<String, Map<String, Integer>> dropMap = plant.getPlantConfig().getDropMap();
      // check the plant to see if it is interacted by the tool
      if (dropMap.containsKey(toolConfig.getId())) {
        // if it is true then remove the plant from the map and reduce energy
        // also add the items from the dropout to the menu
        game.getGameMap().getPlantPositionMap().remove(coord);
        game.getGameState().addEnergy(-toolConfig.getEnergyConsume());
        addItems(dropMap.get(toolConfig.getId()), game);
        return true;
      } else {
        return false;
      }
    }
    // if there is only land on the coord
    else {
      // if the tool cannot be applied to the land
      Land land = game.getGameMap().getLandPositionMap().get(coord);
      if (!(land.getLandConfig().getTransFromLand().containsKey(toolConfig.getId()))) {
        return false;
      } else {
        String landId = land.getLandConfig().getTransFromLand().get(toolConfig.getId());
        Land newLand = new Land(game.getGameConfig().getLandConfigMap().get(landId));
        game.getGameMap().setLand(coord, newLand);
        return true;
      }
    }
  }

  @Override
  public boolean consume(Game game) {
    return false;
  }

  @Override
  public boolean sell(Game game) {
    return false;
  }

  public ToolConfig getConfig() {
    return toolConfig;
  }

  @Override
  public boolean ifSell() {
    return false;
  }

  private void addItems(Map<String, Integer> dropMap, Game game) {
    List<BagItem> itemList = game.getGameState().getItemList();
    List<BagItem> newItemList = new ArrayList<>();
    for (Map.Entry<String, Integer> e : dropMap.entrySet()) {
      BagItem bagItem = game.getGameConfig().getItemFromId(e.getKey(), e.getValue());
      if (!(checkAndAdd(itemList, bagItem))) {
        newItemList.add(bagItem);
      }
    }
    // add new ItemList back to itemList
    itemList.addAll(newItemList);
  }

  private boolean checkAndAdd(List<BagItem> itemList, BagItem item) {
    for (BagItem b : itemList) {
      if (b.getId().equals(item.getId())) {
        b.addNumber(item.getNumber());
        return true;
      }
    }
    return false;
  }


  public ToolConfig getToolConfig() {
    return toolConfig;
  }
}
