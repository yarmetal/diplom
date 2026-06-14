package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryAdvertisement;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryBirthday;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryCelebration;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryGratitude;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryNeedHelp;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categorySalary;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.categoryUnion;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.customCategory;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.customCategoryDescription;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.customCategoryTitle;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionAdvertisement;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionBirthday;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionBirthdayEdit;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionDonations;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionGratitude;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionGratitudeDonations;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionNeedHelp;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionSalary;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionSalaryEnumerated;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.descriptionUnion;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.numbersCategory;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.numbersCategoryDescription;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.numbersCategoryTitle;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.specialCharactersCategory;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.specialCharactersCategoryDescription;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.specialCharactersCategoryTitle;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleAdvertisement;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleBirthdayEdit;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleCelebration;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleDonations;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleGratitude;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleGratitudeDonations;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleNeedHelp;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleSalary;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleSalaryEnumerated;
import static ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage.titleUnion;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.NewsControlPanelPage;

public class NewsControlPanelSteps {

    private final NewsControlPanelPage page = new NewsControlPanelPage();

    public void clickButtonControlPanel() {
        Allure.step("Открытие панели управления");
        page.getNewsControlPanelElementsButtonControlPanel.perform(click());
    }

    public void clickAddNews() {
        Allure.step("Нажатие кнопки добавления новости");
        page.getNewsControlPanelElementsAddNews.perform(click());
    }

    public void fillTitleCreatingNews(String text) {
        Allure.step("Заполнение заголовка: " + text);
        page.getNewsControlPanelElementsButtonTitleCreatingNews
                .perform(click(), clearText(), replaceText(text), closeSoftKeyboard());
    }

    public void clickButtonTimeCreatingNews() {
        Allure.step("Выбор времени публикации");
        page.getNewsControlPanelElementsButtonTimeCreatingNews.perform(click());
    }

    public void clickButtonOkTimeCreatingNews() {
        Allure.step("Подтверждение времени");
        page.getNewsControlPanelElementsButtonOkTimeCreatingNews.perform(click());
    }

    public void fillDescriptionCreatingNews(String text) {
        Allure.step("Заполнение описания: " + text);
        page.getNewsControlPanelElementsDescriptionCreatingNews.perform(replaceText(text), closeSoftKeyboard());
    }

    public void clickButtonSaveCreatingNews() {
        Allure.step("Сохранение новости");
        page.getNewsControlPanelElementsButtonSaveCreatingNews.perform(scrollTo(), click());
    }

    public void fillInNewsCategoryField(String text) {
        Allure.step("Выбор категории: " + text);
        page.getNewsControlPanelElementsCategoryText.perform(click(), clearText(), replaceText(text), closeSoftKeyboard());
    }

    private static final String futureDate = "15.04.2026";

    public void clickButtonDateCreatingNextDate() {
        Allure.step("Установка будущей даты публикации");
        page.getNewsControlPanelElementsButtonDateCreatingNews.perform(replaceText(futureDate));
    }

    public void clickButtonToExpandNews() {
        Allure.step("Разворачивание карточки новости");
        page.getNewsControlPanelElementsButtonToExpandNews.perform(click());
    }

    public void clickButtonToDeleteNews() {
        Allure.step("Удаление новости");
        page.getNewsControlPanelElementsButtonToDeleteNews.perform(click());
    }

    public void clickButtonToOkDeleteNews() {
        Allure.step("Подтверждение удаления");
        page.getNewsControlPanelElementsButtonToOkDeleteNews.perform(click());
    }

    public void clickButtonToEditNews() {
        Allure.step("Редактирование новости");
        page.getNewsControlPanelElementsButtonToEditNews.perform(click());
    }

    public void clickButtonToSwitchStatusNews() {
        Allure.step("Переключение статуса активности");
        page.getNewsControlPanelElementsButtonToSwitchStatusNews.perform(click());
    }


    public static String getCategorySalary() { return categorySalary; }
    public static String getTitleDonations() { return titleDonations; }
    public static String getDescriptionDonations() { return descriptionDonations; }
    public static String getCategoryBirthday() { return categoryBirthday; }
    public static String getDescriptionBirthday() { return descriptionBirthday; }
    public static String getTitleSalaryEnumerated() { return titleSalaryEnumerated; }
    public static String getDescriptionSalaryEnumerated() { return descriptionSalaryEnumerated; }
    public static String getCategoryUnion() { return categoryUnion; }
    public static String getTitleUnion() { return titleUnion; }
    public static String getDescriptionUnion() { return descriptionUnion; }
    public static String getCategoryCelebration() { return categoryCelebration; }
    public static String getTitleCelebration() { return titleCelebration; }
    public static String getCustomCategory() { return customCategory; }
    public static String getCustomCategoryTitle() { return customCategoryTitle; }
    public static String getCustomCategoryDescription() { return customCategoryDescription; }
    public static String getNumbersCategory() { return numbersCategory; }
    public static String getNumbersCategoryTitle() { return numbersCategoryTitle; }
    public static String getNumbersCategoryDescription() { return numbersCategoryDescription; }
    public static String getSpecialCharactersCategory() { return specialCharactersCategory; }
    public static String getSpecialCharactersCategoryTitle() { return specialCharactersCategoryTitle; }
    public static String getSpecialCharactersCategoryDescription() { return specialCharactersCategoryDescription; }

}