> ● Рассказать о выбранной архитектуре, принятых при разработке решениях 

Проект разделен на несколько пакетов, имеющих свою зону ответственности.
*     Presentation - содержит в себе все, что связано с отображением пользовательского интерфейса
*     Data - содержит в себе все, что связано с получением/хранением данных
*     App - содержит в себе все, что связано с DI + Application class приложения



> ● Рассказать о выборе сторонних библиотек (если таковые были использованы) 

При разработке проекта были использованы следующие сторонние библиотеки:
*     Stetho - позволяет просматривать сетевые запросы и состояние хранилищь данных в браузере
*     Retrofit - упрощает работу с сетевыми запросами
*     Gson - сериализация/десериализация данных
*     Picasso - загрузка и кеширование фотографий
*     Room - ORM, предоставляет удобный интерфейс для работы с БД

    Можно было бы отказаться от использования Retrofit и Room, но они много рутины берут на себя 
    Так же, в тестовом режиме попробовал поработать с корутинами

    

> ● Что бы вы добавили/изменили в проекте если бы он был “боевым”? 

*     Переработка дизайна
*     Проведение анализа производительности
*     Тесты (не успел их написать)
*     В рамках расширения функционала можно интегрировать в приложение интерактивные карты (yandex/gogle...)

