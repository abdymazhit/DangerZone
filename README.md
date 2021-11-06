<img align="right" src="https://user-images.githubusercontent.com/46745562/140616106-1b049dea-7cb8-47b5-8953-473a8785ed97.png?raw=true" height="200" width="200">

# DangerZone
[Сайт](https://dangerzone.site/) для вывода информации с Discord бота [DangerBot](https://github.com/abdymazhit/DangerBot). Сайт полностью написан на фреймворке [Spring Boot](https://spring.io/). 

## Свойства среды
Вам нужно установить следующие свойства среды:
* `SERVER_PORT` - порт на котором будет работать Spring приложение
* `SPRING_DATASOURCE_URL` - URL базы данных MySQL
* `SPRING_DATASOURCE_USERNAME` - имя пользователя базы данных
* `SPRING_DATASOURCE_PASSWORD` - пароль пользователя базы данных
* `SPRING_JPA_DATABASE_PLATFORM` - `org.hibernate.dialect.MySQL8Dialect`
* `SPRING_JPA_HIBERNATE_DDL_AUTO` - `update`
* `VIME_TOKEN` - токен разработчика VimeWorld
* `YOUTUBE_TOKEN` - токен разработчика YouTube

## Сайт имеет
* Главную [страницу](https://dangerzone.site/) (выводит недавние игры и активные трансляции ютуберов)
* [Страницу](https://dangerzone.site/rating/single) Single рейтинга (выводит лучших 100 игроков)
* [Страницу](https://dangerzone.site/rating/team) Team рейтинга (выводит лучшие 100 команд)
* [Страницу](https://dangerzone.site/rules) правил платформы
* [Страницу](https://dangerzone.site/personal) персонала проекта
* Страницу просмотра информации об игре

## Установка
1. Собрать проект с помощью `mvn package`
2. Настроить [свойства среды](#свойства-среды)
3. Запустить скомпилированный `.jar` файл