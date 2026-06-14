package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.MainPage;

public class MainSteps {
    private final MainPage uiMap = new MainPage();

    public void mainScreenLoad() {
        Allure.step("Ожидание загрузки главного экрана");
        onView(isRoot()).perform(waitDisplayed(uiMap.allNews, 5000));
    }

    public void clickButtonAllNews() {
        Allure.step("Нажатие кнопки 'ВСЕ НОВОСТИ'");
        uiMap.getMainElementsButtonAllNews.perform(click());
    }

    public void showButtonAllNews() {
        Allure.step("Проверка отображения кнопки 'ВСЕ НОВОСТИ'");
        uiMap.getMainElementsButtonAllNews.check(matches(withText("ALL NEWS")));
    }

    public void clickButtonMainMenu() {
        Allure.step("Открытие бокового меню");
        uiMap.getMainElementsButtonMainMenu.perform(click());
    }

    public void clickButtonMain() {
        Allure.step("Переход на главную страницу через меню");
        uiMap.getMainElementsButtonMain.perform(click());
    }

    public void clickButtonToExpandNews() {
        Allure.step("Сворачивание/разворачивание блока новостей");
        uiMap.getMainElementsButtonToRollUpAllNews.perform(click());
    }

    public void showTitleNewsOnMain() {
        Allure.step("Проверка отображения панели 'Новости'");
        uiMap.getMainElementsTitleNews.check(matches(isDisplayed()));
    }

    public int getMainMenuButton() {
        return uiMap.mainMenuButton;
    }

    public int getButtonToExpandNews() {
        return uiMap.buttonToExpandNews;
    }
}