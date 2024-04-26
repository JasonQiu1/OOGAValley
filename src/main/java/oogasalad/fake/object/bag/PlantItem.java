package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.map.Coord;

public class PlantItem extends BagItem {

  private final PlantItemConfig plantItemConfig;

  public PlantItem(PlantItemConfig plantItemConfig, int number) {
    super(number, plantItemConfig.getId());
    this.plantItemConfig = plantItemConfig;
  }

  /**
   * the plant does not interact with the land, it can only be sold or eaten.
   *
   * @param coord
   * @param game
   */
  @Override
  public boolean interact(Coord coord, Game game) {
    return false;
  }

  @Override
  public boolean consume(Game game) {
    double eatEnergy = plantItemConfig.getEatEnergy();
    game.getGameState().addEnergy(eatEnergy);
    addNumber(-1);
    return true;
  }

  @Override
  public boolean sell(Game game) {
    double money = plantItemConfig.getSellPrice();
    game.getGameState().addMoney(money);
    addNumber(-1);
    return true;
  }

  @Override
  public double ifSellReturnPrice() {
    return plantItemConfig.getSellPrice();
  }

  public PlantItemConfig getPlantItemConfig() {
    return plantItemConfig;
  }

  public PlantItemConfig getConfig() {
    return plantItemConfig;
  }
}
