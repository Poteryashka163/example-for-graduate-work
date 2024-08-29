# **Бэкэнд часть платформы для перепродажи вещей**

*Дипломная работа в рамках обучения Skypro "Java-разработчик"*



---

## **Участники команды**

*Kovpik Igor*


---

## **Реализованы следующие функциональные требования:**

:white_check_mark: регистрация

:white_check_mark: вход/выход

:white_check_mark: получение и обновление профиля

:white_check_mark: смена пароля

:white_check_mark: просмотр объявлений без авторизации

:white_check_mark: просмотр объявлений, редактирование, удаление с авторизацией

:white_check_mark: Просмотр комментариев без авторизации

:white_check_mark: Просмотр, редактирование, удаление комментариев с авторизацией

---

## **Используемый язык програмирования при написании бэкэнд части приложения**

:computer: Java 11 и выше

## **Платформа приложения:**

:small_blue_diamond: - [Spring Boot](https://spring.io/projects/spring-boot): Фреймворк для создания веб-приложений на языке Java.

## **Используемые библиотеки**

:small_blue_diamond: - [Liquid Base](https://www.liquibase.org/): Инструмент для управления схемой базы данных.
:small_blue_diamond: - [Lombok](https://www.oracle.com/corporate/features/project-lombok.html): Библиотека, упрощающая написание кода.
:small_blue_diamond: - [Hibernate](https://hibernate.org/): Фреймворк для работы с базой данных
:small_blue_diamond: - [Postgresql](https://www.postgresql.org/): Система управления реляционными базами данных.
:small_blue_diamond: - [TestContainers](https://testcontainers.com/):библиотека Java, которая предназначена для создания и управления контейнерами Docker в процессе выполнения тестов.
:small_blue_diamond: - [Docker](https://www.docker.com/): Платформа для разработки, доставки и выполнения приложений в контейнерах.
:small_blue_diamond: - [Mockito](https://site.mockito.org/):библиотека для создания mock-объектов (заменителей реальных объектов) при написании модульных тестов.
:small_blue_diamond: - [JUnit](https://junit.org/):фреймворк для написания и выполнения модульных тестов на языке программирования Java.
:small_blue_diamond: - [H2database](https://h2database.github.io/html/main.html): Встроенная база данных для разработки и тестирования.
:small_blue_diamond: - [Swagger](https://docs.swagger.io/spec.html) и [OpenAPI](https://spec.openapis.org/oas/latest.html): Инструменты для создания и документирования API.

---

## **Запуск приложения**

* Для запуска приложения Вам потребуется выполнить несколько шагов:
    - Клонировать проект и открыть его в среде разработки (например, *IntelliJ IDEA* или *VSCode*);
    - В файле **application.properties** указать путь к Вашей базе данных;
    - Запустить **Docker**;
    - В командной строке прописать ```docker pull ghcr.io/bizinmitya/front-react-avito:latest``` и скачать образ;
    - Запустить **Docker image** с помощью
      команды ```docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:latest```;
    - Запустить метод **main** в файле **HomeworkApplication.java**.

После выполнения всех шагов, веб-приложение будет доступно по адресу: http://localhost:3000