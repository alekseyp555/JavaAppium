package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name='{TITLE}]']";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
