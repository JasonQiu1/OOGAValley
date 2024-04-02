package oogasalad.Game.GameModel;

public class EXPLE<T extends GameObject> {

  public T myObject;

  public static void main(String[] args) {
    EXPLE<Tile> ex =  new EXPLE<>();
    Tile t = ex.myObject;
  }

}
