package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthScreenSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
@Epic("Навигация по главному экрану приложения")
public class HomePageNavigationTests {

    private final AuthScreenSteps authActions = new AuthScreenSteps();
    private final MainSteps mainScreen = new MainSteps();
    private final NewsSteps newsSection = new NewsSteps();

    @Rule
    public ActivityTestRule<AppActivity> activityRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void init() {
        try {
            mainScreen.mainScreenLoad();
        } catch (Exception e) {
            authActions.fillLoginField(getLogin());
            authActions.fillPasswordField(getPassword());
            authActions.clickButtonSignIn();
            mainScreen.mainScreenLoad();
        }
    }

    // TC-12: Переход на главную страницу через меню
    @Test
    @Story("TC-12")
    @Description("Переход на вкладку \"Главная страница\" через главное меню")
    public void Main() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        onView(withText("News")).check(matches(isDisplayed()));
        mainScreen.clickButtonMainMenu();
        mainScreen.clickButtonMain();
        onView(withText("News")).check(matches(isDisplayed()));
    }

    // TC-13: Сворачивание/разворачивание блока новостей
    @Test
    @Story("TC-13")
    @Description("Свернуть и развернуть блок новостей на главной странице")
    public void extendNews() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getButtonToExpandNews(), 5000));
        mainScreen.clickButtonToExpandNews();
        mainScreen.clickButtonToExpandNews();
        onView(withId(R.id.all_news_text_view)).check(matches(withText("ALL NEWS")));
    }
}