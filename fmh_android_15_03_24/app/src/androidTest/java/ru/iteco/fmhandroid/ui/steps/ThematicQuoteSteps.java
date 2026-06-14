package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.ThematicQuotePage;

public class ThematicQuoteSteps {

    private final ThematicQuotePage uiMap = new ThematicQuotePage();

    public void clickButtonThematicQuote() {
        Allure.step("Открытие раздела цитат (иконка бабочки)");
        uiMap.getQuoteElementButtonThematicQuote.perform(click());
    }

    public void checkTitleThematicQuote() {
        Allure.step("Проверка заголовка 'Love is all'");
        uiMap.getQuoteElementTitleThematicQuote
                .check(matches(allOf(withText("Love is all"), isDisplayed())));
    }

    public void clickButtonToExpandThematicQuote() {
        Allure.step("Разворачивание/сворачивание карточки цитаты");
        uiMap.getQuoteElementButtonToExpandThematicQuote.perform(click());
    }

    public int getMissionImageButton() {
        return uiMap.missionImageButton;
    }
}