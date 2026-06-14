package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthScreenSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Функциональное тестирование раздела \"О приложении\"")
public class AppInfoTests {

    private final AuthScreenSteps loginActions = new AuthScreenSteps();
    private final AboutSteps aboutSection = new AboutSteps();
    private final MainSteps mainPage = new MainSteps();

    @Rule
    public IntentsTestRule<AppActivity> intentsRule = new IntentsTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            mainPage.mainScreenLoad();
        } catch (Exception e) {
            loginActions.fillLoginField(getLogin());
            loginActions.fillPasswordField(getPassword());
            loginActions.clickButtonSignIn();
            mainPage.mainScreenLoad();
        }
    }

    @After
    public void tearDown() {
        aboutSection.clickButtonPressBack();
    }

    @Test
    @Story("TC-53")
    @Description("Просмотр ссылки \"Политика конфиденциальности\" в разделе \"О приложении\"")
    public void watchingPrivacyPolicy() {
        onView(isRoot()).perform(waitDisplayed(mainPage.getMainMenuButton(), 5000));
        mainPage.clickButtonMainMenu();
        aboutSection.clickButtonAboutMainMenu();
        aboutSection.clickButtonPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        intended(hasAction(Intent.ACTION_VIEW));
    }

    @Test
    @Story("TC-54")
    @Description("Просмотр ссылки \"Пользовательское соглашение\" в разделе \"О приложении\"")
    public void watchingTermsOfUse() {
        onView(isRoot()).perform(waitDisplayed(mainPage.getMainMenuButton(), 5000));
        mainPage.clickButtonMainMenu();
        aboutSection.clickButtonAboutMainMenu();
        aboutSection.clickButtonTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));
    }
}