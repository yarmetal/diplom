package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.AboutPage;

public class AboutSteps {
    private final AboutPage uiMap = new AboutPage();

    public void clickButtonAboutMainMenu() {
        Allure.step("Открытие раздела 'О приложении' через меню");
        uiMap.getABoutElementButtonAbout.perform(click());
    }

    public void clickButtonPrivacyPolicy() {
        Allure.step("Переход по ссылке 'Политика конфиденциальности'");
        uiMap.getABoutElementButtonPrivacyPolicy.perform(click());
    }

    public void clickButtonTermsOfUse() {
        Allure.step("Переход по ссылке 'Пользовательское соглашение'");
        uiMap.getABoutElementButtonTermsOfUse.perform(click());
    }

    public void clickButtonPressBack() {
        Allure.step("Нажатие кнопки возврата (назад)");
        uiMap.getPressBackButton.perform(click());
    }
}