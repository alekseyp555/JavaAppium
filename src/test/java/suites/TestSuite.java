package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTest.class,
//        GetStartedTest.class,
        MyListsTests.class,
        SearchTests.class
})
public class TestSuite {
}
