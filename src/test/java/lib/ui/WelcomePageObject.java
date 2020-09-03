package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred language",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementNotPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementNotPresent(STEP_NEW_WAYS_TO_EXPORE_TEXT,
                "Cannot find 'New ways to explore' link",
                10);
    }

    public void waitForAddorEditPreferredLangText() {
        this.waitForElementNotPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred language' link",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementNotPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link",
                10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find and click 'Next' link",
                10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' link",
                10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find click skip button", 5);
    }
}
