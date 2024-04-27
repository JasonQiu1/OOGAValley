Jason: Added a static factory method in `ReadOnlyGameTime` to create copies of `GameTime`:

```
/**
  * Read-only copy constructor.
  *
  * @param other the other GameTime to copy.
  */
  static ReadOnlyGameTime copyOf(ReadOnlyGameTime other);
```

Jason: Moved `getSelectedItem` method from `GameInterface` to `ReadOnlyGameState` since that it
where it should be stored.

Jason: Added `save` method to `GameInterface`, because we just forgot to give view a way to save:
```
  /**
   * Saves the current GameState to 'data/gamesaves' with the given filename.
   *
   * @param fileName the name of the file to save to.
   * @throws IOException if writing to the file fails.
  */
  void save(String fileName) throws IOException;
```

* Method changed:
  Several methods within GameWorld, Tile, and GameObject that previously took in Items now take in ReadOnlyItems.
    * Why was the change made?
  Taking in the interface rather than the Item increases flexiblity.
    * Major or Minor (how much they affected your team mate's code)
This had no affect on teammate's code as they can still send in Items to the methods
    * Better or Worse (and why)

* Method changed:
ItemsToAddToInventory
  * Why was the change made?
This method still exists within GameWorld, but it was deprecated in the external model API,
as it does not need to be accessed by the view.
  * Major or Minor (how much they affected your team mate's code)
This was a Minor change. This method was already only being used in the model, so this change had not affect.
  * Better or Worse (and why)
Better since it promotes further model view separation.


* Method changed:
setTileGameObject in GameWorld.
  * Why was the change made?
This method was changed to take in an id of a gameObject rather than the GameObject. 
This way outside classes are not responsible for creating the GameObject.
  * Major or Minor (how much they affected your team mate's code)
Minor. This method has not yet been used, so it did not affect teammates code at all.
This method is also only accessed through the controller.
  * Better or Worse (and why)
Better as it makes it so the controller or whoever accesses the method is not responsibile 
for creating GameObjects.

 
