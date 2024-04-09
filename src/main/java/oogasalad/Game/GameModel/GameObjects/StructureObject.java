package oogasalad.Game.GameModel.GameObjects;

public interface StructureObject {

  String getCollectableOnDestruction();

  boolean getIsExpiringState();

  boolean isHarvestable();

}
