package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Objected-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Ex11: Рефакторинг тестов
    @Test
    public void saveTwoArticlesAndDeleteFirst() {
        String name_of_folder = "Myfolder";
        String first_expected_article_title = "Java (programming language)";
        String second_expected_article_title = "Programming language";

        //Сохраняем первую статью
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForTitleElement(first_expected_article_title);
        }

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeSyncYourSavedArticles();
        }

        ArticlePageObject.closeArticle();

        //Поиск и сохранение второй статьи
        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine("javascript");
        }

        SearchPageObject.clickByArticleWithSubstring("Programming language");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToSavedList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElement(second_expected_article_title);
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.findMyLists();
        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.openFolderByName(name_of_folder);
        } else {
            navigationUI.backToSearchList();
            navigationUI.clickOnCancelButton();
            navigationUI.goToMyList();
            navigationUI.closeSyncSavedArticlesPopUp();
        }

        //2. Удаляем одну из статей
        ArticlePageObject.swipeToDeleteOneArticle();
        ArticlePageObject.checkOneArticleWasDeleted();

        //3. Убеждамся, что вторая статья осталась
        String article_subtitle = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected subtitle!",
                "High-level programming language",
                article_subtitle);
    }
}

