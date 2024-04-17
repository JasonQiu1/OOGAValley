package oogasalad.view.editor.MapEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.EditorScene;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.StartScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class EditorSceneTest extends DukeApplicationTest {
  private Stage stage;
  private EditorScene editorScene;
  private Cell cell;
  private CellInfoPane cellInfoPane;
  private SelectableView lava;
  @Override
  public void start(Stage stage) {
    this.stage = stage;
    editorScene = new EditorScene(stage, new GameConfiguration());
    editorScene.start();
//    this.cell = (Cell) lookup("#0 0").query();

    // When trying to find the Cell with id "0 0"
    cell = lookup("#EditorGridPane #0_0").queryAs(Cell.class);
    cellInfoPane = lookup("#CellInfoPane").queryAs(CellInfoPane.class);
    lava = lookup("#BottomPanel #Lava").queryAs(SelectableView.class);

  }

  @Test
  @DisplayName("Test CellInfoPane Coordinates")
  public void testCellInfoPaneCoordinates() {
    int xCor = 0;
    int yCor = 0;
    sleep(500);
    clickOn(cell);
    sleep(500);
    assertEquals(cellInfoPane.getxCor(), xCor);
    assertEquals(cellInfoPane.getyCor(), yCor);
  }

  @Test
  @DisplayName("Test CellInfoPane Info")
  public void testCellInfoPaneInfo() {
    String lava = "Lava";
    sleep(500);
    clickOn(lava);
    sleep(100);
    clickOn(cell);
    sleep(500);
    assertTrue(cellInfoPane.getSvsToStringCopy().equals(lava));

  }


}
