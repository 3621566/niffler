package niffler.test;

import niffler.jupiter.MyAllCallBackExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static niffler.pages.BasePage.allureLog;

@ExtendWith(MyAllCallBackExtension.class)
public class TestLifeCycleTests {
    @Test
    void name() {
        System.out.println("TEST running");
        allureLog("TEST running");
    }
}