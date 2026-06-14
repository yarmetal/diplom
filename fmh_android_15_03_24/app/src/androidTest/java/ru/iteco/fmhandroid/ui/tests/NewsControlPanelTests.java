package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCategoryBirthday;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCategoryCelebration;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCategorySalary;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCategoryUnion;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCustomCategory;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCustomCategoryDescription;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getCustomCategoryTitle;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getDescriptionBirthday;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getDescriptionDonations;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getDescriptionSalaryEnumerated;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getDescriptionUnion;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getNumbersCategory;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getNumbersCategoryDescription;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getNumbersCategoryTitle;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getSpecialCharactersCategory;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getSpecialCharactersCategoryDescription;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getSpecialCharactersCategoryTitle;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getTitleCelebration;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getTitleDonations;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getTitleSalaryEnumerated;
import static ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps.getTitleUnion;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
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
import ru.iteco.fmhandroid.ui.steps.NewsControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Управление новостями в панели администратора")
public class NewsControlPanelTests {

    private final AuthScreenSteps authActions = new AuthScreenSteps();
    private final MainSteps mainScreen = new MainSteps();
    private final NewsSteps newsSection = new NewsSteps();
    private final NewsControlPanelSteps adminPanel = new NewsControlPanelSteps();

    private View rootView;

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
        activityRule.getScenario().onActivity(activity -> rootView = activity.getWindow().getDecorView());
    }

    // TC-21: Пустая категория
    @Test
    @Story("TC-21")
    @Description("Поле категории пустое (негативный)")
    public void fieldCategoryEmptyCreationNewsInControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillTitleCreatingNews(getTitleDonations());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.fillDescriptionCreatingNews(getDescriptionDonations());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-22: Пустой заголовок
    @Test
    @Story("TC-22")
    @Description("Поле заголовка пустое (негативный)")
    public void fieldTitleEmptyCreationNewsInControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getCategoryBirthday());
        adminPanel.fillTitleCreatingNews("");
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.fillDescriptionCreatingNews(getDescriptionBirthday());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-23: Пустая дата публикации
    @Test
    @Story("TC-23")
    @Description("Поле даты публикации пустое (негативный)")
    public void fieldDateEmptyCreationNewsInControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getCategorySalary());
        adminPanel.fillTitleCreatingNews(getTitleSalaryEnumerated());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.fillDescriptionCreatingNews(getDescriptionSalaryEnumerated());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-24: Пустое время
    @Test
    @Story("TC-24")
    @Description("Поле времени пустое (негативный)")
    public void fieldTimeEmptyCreationNewsInControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getCategoryUnion());
        adminPanel.fillTitleCreatingNews(getTitleUnion());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.fillDescriptionCreatingNews(getDescriptionUnion());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-25: Пустое описание
    @Test
    @Story("TC-25")
    @Description("Поле описания пустое (негативный)")
    public void fieldDescriptionEmptyCreationNewsInControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getCategoryCelebration());
        adminPanel.fillTitleCreatingNews(getTitleCelebration());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.fillDescriptionCreatingNews("");
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-26: Собственное название категории
    @Test
    @Story("TC-26")
    @Description("Ввод собственного названия категории (негативный)")
    public void customCategoryName() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getCustomCategory());
        adminPanel.fillTitleCreatingNews(getCustomCategoryTitle());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.fillDescriptionCreatingNews(getCustomCategoryDescription());
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Saving failed. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-27: Категория из цифр
    @Test
    @Story("TC-27")
    @Description("Поле категории состоит из цифр (негативный)")
    public void fieldCategoryConsistsOfNumbers() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getNumbersCategory());
        adminPanel.fillTitleCreatingNews(getNumbersCategoryTitle());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.fillDescriptionCreatingNews(getNumbersCategoryDescription());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Saving failed. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-28: Категория из спецсимволов
    @Test
    @Story("TC-28")
    @Description("Поле категории состоит из спецсимволов (негативный)")
    public void fieldCategoryConsistsOfSpecialCharacters() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickAddNews();
        adminPanel.fillInNewsCategoryField(getSpecialCharactersCategory());
        adminPanel.fillTitleCreatingNews(getSpecialCharactersCategoryTitle());
        adminPanel.clickButtonDateCreatingNextDate();
        adminPanel.clickButtonTimeCreatingNews();
        adminPanel.clickButtonOkTimeCreatingNews();
        adminPanel.fillDescriptionCreatingNews(getSpecialCharactersCategoryDescription());
        adminPanel.clickButtonSaveCreatingNews();
        onView(withText("Saving failed. Try again later."))
                .inRoot(withDecorView(Matchers.not(rootView)))
                .check(matches(isDisplayed()));
        pressBack();
    }

    // TC-33: Просмотр новостей в панели управления (демонстрационный автотест)
    @Test
    @Story("TC-33")
    @Description("Просмотр деталей новости в панели управления")
    public void watchingNewsControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickButtonToExpandNews();
        onView(withIndex(withId(R.id.news_item_description_text_view), 0))
                .check(matches(isDisplayed()));
    }

    // TC-36: Смена статуса
    @Test
    @Story("TC-36")
    @Description("Смена статуса новости с активной на неактивную (позитивный)")
    public void changingStatusNewsControlPanel() {
        onView(isRoot()).perform(waitDisplayed(mainScreen.getMainMenuButton(), 5000));
        mainScreen.clickButtonMainMenu();
        newsSection.clickButtonNews();
        adminPanel.clickButtonControlPanel();
        adminPanel.clickButtonToEditNews();
        adminPanel.clickButtonToSwitchStatusNews();
        onView(withId(R.id.switcher))
                .check(matches(withText("Not active")))
                .check(matches(isDisplayed()));
        adminPanel.clickButtonSaveCreatingNews();
        onView(withIndex(withId(R.id.news_item_published_text_view), 0)).check(matches(withText("NOT ACTIVE")));
        adminPanel.clickButtonToDeleteNews();
        adminPanel.clickButtonToOkDeleteNews();
    }
}