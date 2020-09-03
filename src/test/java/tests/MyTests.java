//package tests;
//
//import lib.CoreTestCase;
//import lib.ui.MainPageObject;
//import lib.ui.SearchPageObject;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.ScreenOrientation;
//
//public class MyTests extends CoreTestCase {
//
//  private MainPageObject MainPageObject;
//  protected void setUp() throws Exception
//  {
//    super.setUp();
//
//    MainPageObject = new MainPageObject(driver);
//  }
///*
//Когда нибудь я доберусь до рефакторинга этих тестов или оставлю для примера (*_*)
//*/
//
//  //Ex2: Создание метода
//  @Test
//  public void testCompareElementText() {
//    SearchPageObject searchPageObject = new SearchPageObject(driver);
//    searchPageObject.initSearchInput();
//  }
//
//  //Ex4*: Тест: проверка слов в поиске
//  @Test
//  public void testCheckTitleSearchResults() {
//    MainPageObject.waitForElementAndClick(
//            By.id("org.wikipedia:id/search_container"),
//            "Cannot find 'Search Wikipedia' input",
//            5);
//
//    MainPageObject.waitForElementAndSendKeys(
//            By.xpath("//*[contains(@text, 'Search…')]"),
//            "Java",
//            "Cannot find search input",
//            5);
//
//    MainPageObject.assertElementsListTitleHasText(
//            By.id("org.wikipedia:id/page_list_item_container"),
//            "Java text not found",
//            15);
//  }
//
//  //Ex7*: Поворот экрана
//  @Test
//  public void testScreenOrientationOnSearchResults() {
//    //Меняем ориентацию на альбомную принудительно для теста
//    driver.rotate(ScreenOrientation.LANDSCAPE);
//    MainPageObject.waitForElementAndClick(
//            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                    "Cannot find search input",
//                    5);
//
//    String search_line = "Java";
//    MainPageObject.waitForElementAndSendKeys(
//            By.xpath("//*[contains(@text,'Search…')]"),
//            search_line,
//            "Cannot type search line into search bar",
//            15);
//
//    MainPageObject.waitForElementAndClick(
//            By.xpath("//*[@text='Java (programming language)']"),
//            "Cannot find Java (programming language)' topic searching by " + search_line,
//                    15);
//
//    String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
//            By.xpath("//*[@text='Java (programming language)']"),
//                    "text",
//                    "Cannot find title of article before rotation",
//                    15);
//
//    driver.rotate(ScreenOrientation.PORTRAIT);
//
//    String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
//            By.xpath("//*[@text='Java (programming language)']"),
//            "text",
//            "Cannot find title of article after rotation",
//                    15);
//
//    assertEquals(
//            "Article title changed after rotation",
//            title_before_rotation,
//            title_after_rotation);
//  }
//  //Ex9*: Рефакторинг темплейта
//  @Test
//  public void testCheckSearchResultByTitleAndSubtitle(){
//    SearchPageObject searchPageObject = new SearchPageObject(driver);
//
//    String expectedTitle1 = "Java";
//    String expectedSubtitle1 = "Island of Indonesia";
//    String expectedTitle2 = "JavaScript";
//    String expectedSubtitle2 = "Programming language";
//    String expectedTitle3 = "Java (programming language)";
//    String expectedSubtitle3 = "Object-oriented programming language";
//    String searchRequest = "Java";
//
//    searchPageObject.initSearchInput();
//    searchPageObject.typeSearchLine(searchRequest);
//
//    searchPageObject.waitForElementByTitleAndDescription(expectedTitle1, expectedSubtitle1);
//    searchPageObject.waitForElementByTitleAndDescription(expectedTitle2, expectedSubtitle2);
//    searchPageObject.waitForElementByTitleAndDescription(expectedTitle3, expectedSubtitle3);
//  }
//}
