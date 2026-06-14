package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.NewsPage;

public class NewsSteps {

    private final NewsPage uiMap = new NewsPage();

    public void clickButtonNews() {
        Allure.step("Открытие раздела новостей через меню");
        uiMap.getNewsButton.perform(click());
    }
}