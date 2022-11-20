# Дипломный проект «Тестировщик ПО»

[План автоматизации](https://github.com/Redarek/qa-diploma/blob/master/docs/Plan.md)

[Отчет по итогам тестирования](https://github.com/Redarek/qa-diploma/blob/master/docs/Report.md)

[Отчет по итогам автоматизации](https://github.com/Redarek/qa-diploma/blob/master/docs/Summary.md)

# Процедура запуска

## 1. Склонировать репозиторий 
```
git clone https://github.com/Redarek/qa-diploma.git
```
## 2. Запустить Docker контейнеры
```
docker compose -f docker-compose.yml up -d
```
## 3. Для запуска сервиса с указанием пути к базе данных использовать следующие команды:
Для mysql
```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```
Для postgresql
```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```
## 4. Запуск тестов выполнить с параметрами, указав путь к базе данных в командной строке:
Для mysql
```
gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```
Для postgresql
```
gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```

## 5. Сформировать отчёт
```
gradlew allureServe
```
