package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            NAVIGATE_UP_BUTTON,
            NO_THANKS_BUTTON,
            VIEW_LIST_BUTTON,
            EXPLORE_BUTTON,
            BACK_BUTTON,
            CANCEL_BUTTON,
            CLOSE_POPUP_ICON,
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }

    public void goBackToAddNewArticle(String name_of_folder) {

        this.waitForElementAndClick(NAVIGATE_UP_BUTTON,
                "Cannot find 'Navigate up' button",
                5);

        this.waitForElementAndClick(NO_THANKS_BUTTON,
                "Cannot find 'NO THANKS' button",
                5);

        this.waitForElementAndClick(EXPLORE_BUTTON,
                "Cannot find 'Explore' button",
                5);
    }

    public void goToMyList() {
        this.waitForElementAndClick(VIEW_LIST_BUTTON,
                "Cannot find 'VIEW LIST' button",
                5);
    }

    public void backToSearchList() {
        this.waitForElementAndClick(BACK_BUTTON,
                "Cannot find 'Back' button",
                5);
    }

    public void clickOnCancelButton() {
        this.waitForElementAndClick(CANCEL_BUTTON,
                "Cannot find 'Cancel' button",
                5);
    }

    public void closeSyncSavedArticlesPopUp() {
        this.waitForElementAndClick(CLOSE_POPUP_ICON,
                "Cannot find 'Cancel' icon",
                5);
    }
}
