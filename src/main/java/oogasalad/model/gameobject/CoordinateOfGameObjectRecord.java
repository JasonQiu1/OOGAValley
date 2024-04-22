package oogasalad.model.gameobject;

public record CoordinateOfGameObjectRecord(int x, int y, int z) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CoordinateOfGameObjectRecord that = (CoordinateOfGameObjectRecord) o;
    if (that.x != x) {
      return false;
    }
    if (that.y != y) {
      return false;
    }
    return that.z == z;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    result = 31 * result + z;
    return result;
  }

}
