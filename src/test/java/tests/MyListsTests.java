package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Learnqamobile",
            password = "P@ssword1";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bjected-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
            if (Platform.getInstance().isMw()) {
                AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
                Auth.clickAuthButton();
                Auth.enterLoginData(login, password);
                Auth.submitForm();

                ArticlePageObject.waitForTitleElement();

                assertEquals("We are not on the same page after login",
                        article_title,
                        ArticlePageObject.getArticleTitle());
            }

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Ex17: Рефакторинг
    @Test
    public void saveTwoArticlesAndDeleteFirst() {
        String name_of_folder = "Myfolder",
                first_expected_article_title = "Java (programming language)",
                second_expected_article_title = "Programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

//Сохраняем первую статью
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    first_expected_article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

//Поиск и сохранение второй статьи
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("javascript");
        SearchPageObject.clickByArticleWithSubstring("Programming language");


        if(Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    second_expected_article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToSavedList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElement(second_expected_article_title);
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

//Удаляем одну из статей
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
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

