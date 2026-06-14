package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getLogin;
import static ru.iteco.fmhandroid.ui.steps.AuthScreenSteps.getPassword;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
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
import ru.iteco.fmhandroid.ui.steps.ThematicQuoteSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка тематических цитат")
public class QuoteFlowTests {

    private final AuthScreenSteps authActions = new AuthScreenSteps();
    private final ThematicQuoteSteps quoteInteractions = new ThematicQuoteSteps();
    private final MainSteps mainScreen = new MainSteps();

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

    // TC-52: Разворачивание и сворачивание цитаты
    @Test
    @Story("TC-52")
    @Description("Развернуть и свернуть тематическую цитату в разделе \"Love is all\"")
    public void expandThematicQuote() {
        onView(isRoot()).perform(waitDisplayed(quoteInteractions.getMissionImageButton(), 5000));
        quoteInteractions.clickButtonThematicQuote();
        quoteInteractions.checkTitleThematicQuote();
        quoteInteractions.clickButtonToExpandThematicQuote();
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0)).check(matches(isDisplayed()));
    }
}