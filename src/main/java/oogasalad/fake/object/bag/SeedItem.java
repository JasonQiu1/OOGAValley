package oogasalad.fake.object.bag;

import java.util.Map;
import oogasalad.fake.Game;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.map.Coord;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeedItem extends BagItem {

  private static final Logger LOG = LogManager.getLogger(SeedItem.class);

  private final SeedConfig seedConfig;

  public SeedItem(SeedConfig seedConfig, int number) {
    super(number, seedConfig.getId());
    this.seedConfig = seedConfig;
  }

  @Override
  public boolean interact(Coord coord, Game game) {
    if (game.getGameMap().getPlantPositionMap().get(coord) != null) {
      return false;
    }
    if (game.getGameMap().getLandPositionMap().get(coord) == null) {
      return false;
    } else {
      Land land = game.getGameMap().getLandPositionMap().get(coord);
      Map<String, String> seedGrown = land.getLandConfig().getSeedGrown();
      if (!seedGrown.containsKey(this.seedConfig.getId())) {
        return false;
      }
      String plantId = seedGrown.get(this.seedConfig.getId());
      PlantConfig plantConfig = game.getGameConfig().getPlantConfigMap().get(plantId);
      Plant plant = new Plant(plantConfig, game.getGameState().getGameTime().copy());
      game.getGameMap().setPlant(coord, plant);
      addNumber(-1);
      return true;
    }
  }

  @Override
  public boolean consume(Game game) {
    return false;
  }

  @Override
  public boolean sell(Game game) {
    double money = this.seedConfig.getSellPrice();
    game.getGameState().addMoney(money);
    addNumber(-1);
    return true;
  }

  @Override
  public double ifSellReturnPrice() {
    return seedConfig.getSellPrice();
  }

  public SeedConfig getSeedConfig() {
    return seedConfig;
  }

  public SeedConfig getConfig() {
    return seedConfig;
  }
}
