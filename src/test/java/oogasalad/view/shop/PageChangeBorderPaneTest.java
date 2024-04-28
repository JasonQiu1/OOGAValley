package oogasalad.view.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javafx.scene.layout.GridPane;
import oogasalad.Main;
import oogasalad.view.shopping.components.PageChangeBorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import org.testfx.framework.junit5.ApplicationTest;

public class PageChangeBorderPaneTest extends ApplicationTest {

  private PageChangeBorderPane<GridPane> pageChangeBorderPane;
  private GridPane gridPane1;
  private GridPane gridPane2;
  @BeforeAll
  public static void setUpClass() throws Exception {
    ApplicationTest.launch(Main.class);
  }


  @BeforeEach
  public void setUp() {
    gridPane1 = new GridPane();
    gridPane2 = new GridPane();
    pageChangeBorderPane = new PageChangeBorderPane<>(Arrays.asList(gridPane1, gridPane2));
  }

  @Test
  public void initialPageIsFirstGridPane() {
    assertEquals(gridPane1, pageChangeBorderPane.getCurrentGridPane());
  }

  @Test
  public void canNavigateToNextPage() {
    pageChangeBorderPane.getRightButton().fire();
    assertEquals(gridPane2, pageChangeBorderPane.getCurrentGridPane());
  }

  @Test
  public void canNavigateToPreviousPage() {
    pageChangeBorderPane.getRightButton().fire();
    pageChangeBorderPane.getLeftButton().fire();
    assertEquals(gridPane1, pageChangeBorderPane.getCurrentGridPane());
  }

  @Test
  public void cannotNavigatePastLastPage() {
    pageChangeBorderPane.getRightButton().fire();
    pageChangeBorderPane.getRightButton().fire();
    assertEquals(gridPane2, pageChangeBorderPane.getCurrentGridPane());
  }

  @Test
  public void cannotNavigateBeforeFirstPage() {
    pageChangeBorderPane.getLeftButton().fire();
    assertEquals(gridPane1, pageChangeBorderPane.getCurrentGridPane());
  }

  @Test
  public void rightButtonIsDisabledOnLastPage() {
    pageChangeBorderPane.getRightButton().fire();
    assertTrue(pageChangeBorderPane.getRightButton().isDisabled());
  }

  @Test
  public void leftButtonIsDisabledOnFirstPage() {
    assertTrue(pageChangeBorderPane.getLeftButton().isDisabled());
  }


  @Test
  public void buttonsAreDisabledWhenOnlyOnePage() {
    pageChangeBorderPane = new PageChangeBorderPane<>(Collections.singletonList(gridPane1));
    assertTrue(pageChangeBorderPane.getLeftButton().isDisabled());
    assertTrue(pageChangeBorderPane.getRightButton().isDisabled());
  }
}
