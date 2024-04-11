package oogasalad.view.editor.MapEditor;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class CellTest {

  @BeforeAll
  public static void init() {
    // Initializes JavaFX Toolkit required for JavaFX operations in tests
    new JFXPanel();
  }

  @Test
  public void testIncrementRow() {
    Cell cell = new Cell(null, null, 0, 0);
    int initialRow = cell.getRow();
    cell.incrementRow();
    assertEquals(initialRow + 1, cell.getRow());
  }

  @Test
  public void testIncrementColumn() {
    Cell cell = new Cell(null, null, 0, 0);
    int initialColumn = cell.getColumn();
    cell.incrementColumn();
    assertEquals(initialColumn + 1, cell.getColumn());
  }

  @Test
  public void testDecrementRow() {
    Cell cell = new Cell(null, null, 0, 0);
    int initialRow = cell.getRow();
    cell.decrementRow();
    assertEquals(initialRow - 1, cell.getRow());
  }

  @Test
  public void testDecrementColumn() {
    Cell cell = new Cell(null, null, 0, 0);
    int initialColumn = cell.getColumn();
    cell.decrementColumn();
    assertEquals(initialColumn - 1, cell.getColumn());
  }

  @Test
  public void testGetColumnRow() {
    Cell cell = new Cell(null, null, 0, 0);
    assertEquals(0, cell.getColumn());
    assertEquals(0, cell.getRow());
  }

  @Test
  public void testGetSize() {
    double[] size = Cell.getSize();
    assertEquals(50, size[0]);
    assertEquals(37, size[1]);
  }

  @Test
  public void testIdInitialization() {
    Cell cell = new Cell(null, null, 3, 4);
    int[] id = cell.getID();
    assertEquals(3, id[0]);
    assertEquals(4, id[1]);
  }

  @Test
  public void testBaseFillAndStroke() {
    Cell cell = new Cell(null, null, 0, 0);
    assertEquals(Color.WHITE, cell.getBase().getFill());
    assertEquals(Color.BLACK, cell.getBase().getStroke());
    assertEquals(2, cell.getBase().getStrokeWidth());
  }

  // Add more tests as needed
}

