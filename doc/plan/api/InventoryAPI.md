### API for Inventory class

```java
public class InventoryAPI{
//  API for class Data

  /**
   * Removes one instance of parameter from the inventory
   * @param Item
   * @return
   * true if bagItem is removed
   * false if no items are removed
   */
  public boolean consume (Item thing);
  

/**
 * Decrements durability of object
 * @param Item
 * @return
 * true if bagItem durability is decremented
 * false if durability is not decremented
 */
public boolean weaken (Item thing);

}

``` 