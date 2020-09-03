package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name='{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_SUBTITLE = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        SKIP_BUTTON = "id:Skip";
    }
        public IOSSearchPageObject(RemoteWebDriver driver) {
            super(driver);
    }
}
