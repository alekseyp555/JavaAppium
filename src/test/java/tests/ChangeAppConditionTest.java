package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTest extends CoreTestCase {
    @Test
    public void testChangeScreenOrientionOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Objected-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Objected-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Objected-oriented programming language");
    }
}
