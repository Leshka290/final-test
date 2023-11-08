## Food Service

## Сервис по доставке еды.
Многомодульное Spring-Boot приложение, в котором каждый модуль отвечает за свою часть функциональности.

## Функционал приложения: 
- Авторизация пользователя
- Формирование заказа
- Доставка
- Рассылка курьерам уведомлений

##  Модули приложения
## dependency_bom
- Модуль отвечает за общие зависимости сервисов. Содержит версии и настройки зависимостей.
## auth-service
- Модуль отвечает за авторизацию и аутентификацию.
## common
- Модуль в котором представлены все общие сущности и общие методы.
## notification-service
- Модуль отвечает за отправку уведомлений курьерам.
## migration
- Модуль в котором представлены все скрипты миграции.
## delivery-service
- Модуль отвечает за доставку заказа.
## kitchen-service
- Модуль отвечает приготовление блюд.
## order-service
- Модуль отвечает за обработку заказа.

## Сборка проекта
1. При необходимости измените параметры доступа к базе данных в файлах
 - application.properties
>spring.datasource.url=jdbc:postgresql://localhost:5432/food_service

>spring.datasource.password=postgres

>spring.datasource.username=postgres

2. Установить если не установлен RabbitMQ и подключиться login:guest, password:guest
 - $ docker pull rabbitmq
 - $ docker run --restart always -d --network host rabbitmq
 - $ rabbitmq-plugins enable rabbitmq_management
3. Для теста добавить начальные данные в модуле migration
   - init.sql
   - test_data_filling.sql
  
## Используемые технологии
<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/spring/spring-original-wordmark.svg" title="Spring" alt="Spring" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/postgresql/postgresql-original.svg" title="PostgreSQL"  alt="PostgreSQL" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/git/git-original-wordmark.svg" title="Git" **alt="Git" width="40" height="40"/>
</div>
