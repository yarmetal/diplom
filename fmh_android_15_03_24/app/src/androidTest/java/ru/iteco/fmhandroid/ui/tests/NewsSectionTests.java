package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Навигация по разделу новостей")
public class NewsSectionTests {

    private final AuthScreenSteps authActions = new AuthScreenSteps();
    private final MainSteps mainScreen = new MainSteps();
    private final NewsSteps newsSection = new NewsSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule = new ActivityScenarioRule<>(AppActivity.class);

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

    // TC-14: Открытие всех новостей через главное меню
    @Test
    @Story("TC-14")
    @Description("Переход во вкладку \"Все новости\" через боковое меню")
    public void transferToAllNewsThroughMainMenu() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))))).check(matches(isDisplayed()));
    }

    // TC-15: Открытие всех новостей через виджет на главной
    @Test
    @Story("TC-15")
    @Description("Переход во вкладку \"Все новости\" через главную страницу")
    public void transferToAllNewsThroughMainPage() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.showButtonAllNews();
        mainScreen.clickButtonAllNews();
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))))).check(matches(isDisplayed()));
    }
}