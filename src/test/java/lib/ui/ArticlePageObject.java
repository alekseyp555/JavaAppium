package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ARTICLE_SUBTITLE,
            ARTICLE_TO_DELETE,
            ARTICLE_TO_STAY;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSubtitleElement(String substr) {
        return ARTICLE_SUBTITLE.replace("{SUBTITLE}", substr);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent((TITLE),
                "Cannot find article title on page!",
                15);
    }

    //for IOS
    public WebElement waitForTitleElement(String search_line) {
        String locator = "id:" + search_line;
        return this.waitForElementPresent(
                TITLE = locator,
                "Cannot find article title on page!",
                15
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find article to reading list",
                5);

        this.waitForElementAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                (MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5);

        this.waitForElementAndSendKeys(
                (MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder",
                5);

        this.waitForElementAndClick(
                (MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5);
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5);
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(
                TITLE,
                "Cannot find article title in the article page");
    }

    public void closeSyncYourSavedArticles() {
        this.waitForElementAndClick(
                "id:places auth close",
                "Cannot find places auth close button.",
                5
        );
    }

    public void addArticleToSavedList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
    }
    public void swipeToDeleteOneArticle(){
        this.swipeElementToLeft(ARTICLE_TO_DELETE,
                "Cannot find 'Object-oriented programming language' article topic");

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(ARTICLE_TO_DELETE, "Cannot delete button on article");
        }
    }

    public void checkOneArticleWasDeleted(){
        this.waitForElementNotPresent(ARTICLE_TO_DELETE,
                "Cannot delete saved article",
                25);

        this.waitForElementPresent(ARTICLE_TO_STAY,
                "Cannot find 'JavaScript' topic in my folder",
                25);

        this.waitForElementAndClick(ARTICLE_TO_STAY,
                "Cannot click 'High-level programming language' topic in my folder",
                5);
    }
}

