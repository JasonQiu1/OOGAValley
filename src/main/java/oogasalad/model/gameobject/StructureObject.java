package oogasalad.model.gameobject;

import java.util.Map;

public interface StructureObject {

  Map<String, Integer> getItemsOnDestruction();

  boolean isHarvestable();

}
