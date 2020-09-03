package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {
  protected static String
          SKIP_BUTTON,
          SEARCH_INIT_ELEMENT,
          SEARCH_INPUT,
          SEARCH_RESULT_BY_SUBSTRING_TPL,
          SEARCH_CANCEL_BUTTON,
          SEARCH_RESULT_ELEMENT,
          SEARCH_EMPTY_RESULT_ELEMENT,
          SEARCH_RESULT_BY_TITLE_AND_SUBTITLE;

  public SearchPageObject(AppiumDriver driver)
  {
    super(driver);
  }
/*TEMPLATES METHODS */
  private static String getResultSearchElement (String substring)
  {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  private static String getSearchResultElementByTitleAndSubtitle(String title, String subtitle){
    return SEARCH_RESULT_BY_TITLE_AND_SUBTITLE.replace("{TITLE}", title).replace("{SUBTITLE}", subtitle);
  }
  /* TEMPLATES METHODS */

  public void skipClick(){
    this.waitForElementAndClick(SKIP_BUTTON,
            "Cannot find 'Skip' button", 5);
  }

  public void initSearchInput()
  {
    this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 15);
    this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
  }

  public void waitForCancelButtonToAppear()
  {
    this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button",5);
  }

  public void waitForCancelButtonToDisappear()
  {
    this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present",5);
  }

  public void clickCancelSearch()
  {
    this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
  }

  public void typeSearchLine(String search_line)
  {
    this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 10);
  }

  public void waitForSearchResult(String substring)
  {
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
  }

  public void clickByArticleWithSubstring(String substring)
  {
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementAndClick(search_result_xpath,
            "Cannot find and click search result with substring " + substring,
            10);
  }

  public int getAmountOfFoundArticles()
  {
    this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
            "Cannot find anything by the request",
            15);
    return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
  }

  public void waitForEmptyResultsLabel()
  {
    this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,
            "Cannot find empty result element",
            15);
  }

  public void assertThereIsNoResultOfSearch()
  {
    this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find results");
  }

  public void waitForElementByTitleAndDescription(String title, String description){
    String searchResultXpath = getSearchResultElementByTitleAndSubtitle(title, description);
    this.waitForElementPresent(searchResultXpath, "Cannot find search result by title '" + title + "' and subtitle '" + description + "'");
  }
}
