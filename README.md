# Дипломный проект профессии «Инженер по тестированию»

## Предварительные условия

1. **Установить Android Studio**  
   Скачать можно с [официального сайта](https://developer.android.com/studio).

2. **Клонировать репозиторий**  
   ```bash
   git clone https://github.com/yarmetal/diplom
3. **Открыть проект в Android Studio. Выбрать папку ```fmh_android_15_03_24``` как корень проекта.**

4. **Настроить эмулятор.
Рекомендуется использовать Pixel 4 с API 30 (Android 11).
Эмулятор можно создать через Device Manager в Android Studio.**

5. **Собрать и установить приложение
Нажать ```Run``` (зелёная стрелка) в Android Studio или выполнить в терминале:** ```./gradlew installDebug```

6. **Данные для авторизации:**

    Логин: ```login2```

    Пароль: ```password2```






## Инструкция по запуску тестов
**Все тесты находятся в директории:**
```app/src/androidTest/java/ru/iteco/fmhandroid/ui/tests```

## Способ 1: Запуск через Android Studio (рекомендуемый)
- Открыть нужный тестовый класс (например, ```LoginValidationTests```).
- Нажать правой кнопкой мыши по имени класса → ```Run``` 'ClassName'.
- Для запуска всех тестов сразу: выбрать папку ```tests``` → ```Run``` 'Tests in …'.

## Способ 2: Запуск через терминал (Gradle)
**Из корня проекта выполнить:**

### Запуск всех тестов
`./gradlew connectedAndroidTest`

### Запуск конкретного класса
`./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.tests.LoginValidationTests`

### Запуск одного метода
`./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.tests.LoginValidationTests#validCredentialsLogin`


## Документация

1. [План автоматизации тестирования](https://github.com/yarmetal/diplom/blob/main/Plan.md)
   
3. [Чек-лист](https://github.com/yarmetal/diplom/blob/main/Check.xlsx)

   
5. [Тест-кейсы](https://github.com/yarmetal/diplom/blob/main/Cases.xlsx)

   
7. [Отчёт о проведённом тестировании]()

## Технические детали
- ОС: macOS (совместимо с любым окружением)

- Среда разработки: Android Studio Flamingo (или новее)

- JDK: 11 (настроить в Project Structure → SDK Location)

- Gradle: 7.5 (указан в gradle/wrapper/gradle-wrapper.properties)

- Android Gradle Plugin: 7.4.2

- Kotlin: 1.6.21

- Allure: 2.4.0 (kotlin-android)


## Примечания
- Если тесты не запускаются из-за ошибок `NoMatchingViewException`, проверьте, что в классе `AuthorizationPage` подсказки полей соответствуют русскому языку ("`Логин`", "`Пароль`", "`Авторизация`").

- Для успешной работы Allure необходимо добавить аннотации `@RunWith(AllureAndroidJUnit4.class)` в каждый тестовый класс (уже сделано).

- При первом запуске может потребоваться синхронизация (`Sync Now`) и пересборка проекта (`Build → Rebuild Project`).




### Дата составления инструкции: 14.06.2026
### Автор: Давыдов Игорь Евгеньевич
