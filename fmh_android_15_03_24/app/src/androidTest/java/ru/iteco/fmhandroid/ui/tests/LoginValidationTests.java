package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getDifferentRegexLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getDifferentRegexPassword;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLoginWithSpecialCharacters;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getOneLetterLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getOneLetterPassword;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPasswordWithSpecialCharacters;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getUnregisteredLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getUnregisteredPassword;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthScreenSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тест-кейсы для проведения функционального тестирования вкладки \"Авторизация\" мобильного приложения \"Мобильный хоспис\".")
public class LoginValidationTests {

    private final AuthScreenSteps authSteps = new AuthScreenSteps();
    private final MainSteps mainPage = new MainSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> scenarioRule = new ActivityScenarioRule<>(AppActivity.class);
    private View rootView;

    @Before
    public void init() {
        try {
            authSteps.loadAuthorizationPage();
        } catch (Exception e) {
            authSteps.clickButtonExit();
            authSteps.clickButtonLogOut();
            authSteps.loadAuthorizationPage();
        }
        scenarioRule.getScenario().onActivity(activity -> rootView = activity.getWindow().getDecorView());
    }

    @After
    public void cleanup() {
        try {
            authSteps.clickButtonExit();
            authSteps.clickButtonLogOut();
        } catch (Exception ignored) {
        }
    }

    // TC-1: Успешная авторизация
    @Test
    @Story("TC-1")
    @Description("Авторизация в мобильном приложении \"Мобильный хоспис\" (Позитивный).")
    public void validCredentialsLogin() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 3000));
        mainPage.showTitleNewsOnMain();
        authSteps.clickButtonExit();
        authSteps.clickButtonLogOut();
    }

    // TC-2: Пустой логин
    @Test
    @Story("TC-2")
    @Description("Поле \"Логин\" (Login) пустое, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void emptyLoginField() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField("");
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Login and password cannot be empty"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-3: Несуществующий логин
    @Test
    @Story("TC-3")
    @Description("Поле \"Логин\" (Login) заполнено данными незарегистрированного пользователя, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void unknownUserLogin() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getUnregisteredLogin());
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-4: Логин со спецсимволами
    @Test
    @Story("TC-4")
    @Description("Поле \"Логин\" (Login) состоит из спецсимволов, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void specialSymbolsLogin() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLoginWithSpecialCharacters());
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-5: Логин из одного символа
    @Test
    @Story("TC-5")
    @Description("Поле \"Логин\" (Login) состоит из одного символа, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void singleCharacterLogin() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getOneLetterLogin());
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-6: Логин в разном регистре
    @Test
    @Story("TC-6")
    @Description("Поле \"Логин\" (Login) состоит из букв разного регистра, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void mixedCaseLogin() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getDifferentRegexLogin());
        authSteps.fillPasswordField(getPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-7: Пустой пароль
    @Test
    @Story("TC-7")
    @Description("Поле \"Пароль\" (Password) пустое, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void emptyPasswordField() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField("");
        authSteps.clickButtonSignIn();
        onView(withText("Login and password cannot be empty"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-8: Несуществующий пароль
    @Test
    @Story("TC-8")
    @Description("Поле \"Пароль\" (Password) заполнено данными незарегистрированного пользователя, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void unknownUserPassword() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField(getUnregisteredPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-9: Пароль со спецсимволами
    @Test
    @Story("TC-9")
    @Description("Поле \"Пароль\" (Password) состоит из спецсимволов, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void specialSymbolsPassword() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField(getPasswordWithSpecialCharacters());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-10: Пароль из одного символа
    @Test
    @Story("TC-10")
    @Description("Поле \"Пароль\" (Password) состоит из одного символа, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный)")
    public void singleCharacterPassword() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField(getOneLetterPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }

    // TC-11: Пароль в разном регистре
    @Test
    @Story("TC-11")
    @Description("Поле \"Пароль\" (Password) состоит из букв разного регистра, при авторизации, в мобильном приложении \"Мобильный хоспис\" (Негативный).")
    public void mixedCasePassword() {
        onView(isRoot()).perform(waitDisplayed(authSteps.getLoginLayout(), 5000));
        authSteps.textAuthorization();
        authSteps.fillLoginField(getLogin());
        authSteps.fillPasswordField(getDifferentRegexPassword());
        authSteps.clickButtonSignIn();
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
    }
}