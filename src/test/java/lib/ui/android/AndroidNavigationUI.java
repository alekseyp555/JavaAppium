package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }

}
