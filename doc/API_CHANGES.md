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