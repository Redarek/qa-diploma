# Планирование автоматизации

### 1. [Перечень автоматизируемых сценариев](https://docs.google.com/spreadsheets/d/1UqI8QPI4CFlLypL6ZwX-dr8x_BfT9CW7sZ4vYoJh1po/edit?usp=sharing)

##### Позитивные
<img width="1114" alt="Screenshot 2022-10-30 at 16 59 24" src="https://user-images.githubusercontent.com/80126370/198882637-53d35cac-93da-4e6a-981f-15b42646cc5e.png">

##### Негативные для поля «Номер карты»
<img width="1315" alt="Screenshot 2022-10-30 at 17 19 21" src="https://user-images.githubusercontent.com/80126370/198883571-a5fa0253-e66e-4f22-8294-4a18c81f94f9.png">
<img width="1312" alt="Screenshot 2022-10-30 at 17 19 41" src="https://user-images.githubusercontent.com/80126370/198883573-2f4ebd13-87ad-47cb-947b-95066ade85b9.png">
<img width="1313" alt="Screenshot 2022-10-30 at 17 19 46" src="https://user-images.githubusercontent.com/80126370/198883577-6a682e0f-a906-4e91-97a1-478ed2bdbde3.png">

##### Негативные для поля «Дата»
<img width="1173" alt="Screenshot 2022-10-30 at 17 07 54" src="https://user-images.githubusercontent.com/80126370/198883066-47e18ae6-c5b7-4c89-bd64-136e2d3195d8.png">
<img width="1171" alt="Screenshot 2022-10-30 at 17 08 06" src="https://user-images.githubusercontent.com/80126370/198883073-d57dfccc-abc4-47a4-977b-b9e45dc9c70a.png">

##### Негативные для поля «CVV»
<img width="1307" alt="Screenshot 2022-10-30 at 17 14 49" src="https://user-images.githubusercontent.com/80126370/198883376-d835593a-e212-4cf2-bd87-dc3c8cff2973.png">
<img width="1306" alt="Screenshot 2022-10-30 at 17 15 05" src="https://user-images.githubusercontent.com/80126370/198883386-dcd58162-d6be-4059-842c-1423c494c600.png">
<img width="1307" alt="Screenshot 2022-10-30 at 17 15 12" src="https://user-images.githubusercontent.com/80126370/198883392-db341419-4675-411e-8356-80a287bd6019.png">

##### Негативные для поля «Имя держателя карты»
<img width="1330" alt="Screenshot 2022-10-30 at 17 16 23" src="https://user-images.githubusercontent.com/80126370/198883453-c1e574c0-2170-4a70-b90c-854435453975.png">
<img width="1332" alt="Screenshot 2022-10-30 at 17 16 32" src="https://user-images.githubusercontent.com/80126370/198883465-9c5f544c-26f7-40d3-8192-c6fbd18eb67f.png">
<img width="1329" alt="Screenshot 2022-10-30 at 17 16 38" src="https://user-images.githubusercontent.com/80126370/198883473-1c5502f9-ace1-4a42-a73c-9d2a4f16ad54.png">

### 2. Перечень используемых инструментов
#### Инструменты выбраны согласно компетентности тестировщика.
* Git и Github. Система контроля версий, для хранения кода автотестов и настроек окружения
* Java. Язык программирования, на котором написано приложение и будут написаны автотесты.
* IntelliJ IDEA. Платформа для написания кода
* Node JS - для развертывания БД
* Gradle. Система управления зависимости, которая позволит установить необходимые фреймворки
* JUnit. Библиотека для создания и запуска автотестов
* Selenide. Фреймворк для автоматизированного тестирования веб-приложений
* Allure. Фреймворк, предназначенный для создания подробных отчетов о выполнении тестов

### 3. Перечень и описание возможных рисков при автоматизации
* Неработающий функционал приложения
* Проблемы с идентификацией полей ввода
* Проблемы настройки приложения и необходимых БД

### 4. Интервальная оценка с учётом рисков (в часах)
* Подготовка окружения, развертывание БД - 10 часов
* Написание автотестов, тестирование и отладка автотестов -  30 часов
* Формирование и анализ отчетов - 8 часов

### 5. План сдачи работ (когда будут авто-тесты, результаты их прогона и отчёт по автоматизации)
1. До 30 октября - планирование автоматизации тестирования
1. С 31 октября по 16 ноября - настройка окружения, написание и отладка автотестов, тестирование
1. C 16 по 20 ноября - подготовка отчетных документов
