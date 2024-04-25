Jason: Added a static factory method in `ReadOnlyGameTime` to create copies of `GameTime`:

```
/**
  * Read-only copy constructor.
  *
  * @param other the other GameTime to copy.
  */
  static ReadOnlyGameTime copyOf(ReadOnlyGameTime other);
```