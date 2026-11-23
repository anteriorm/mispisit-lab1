# VR-Tourney — лабораторная работа №3

Модуль управления турнирами VR-спорта информационной системы VR-Tourney. Версия 0.0.3.

В работе реализовано постоянное хранение турниров в PostgreSQL 16. Приложение позволяет создать турнир, посмотреть список и карточку сохранённого турнира. Попытка открыть панель администратора перенаправляет на страницу отказа в доступе.

Используются Java 21, Spring Boot 4.1.1, Thymeleaf, Spring Data JPA, Hibernate, PostgreSQL 16 и Docker Compose.

## Запуск

Репозиторий: https://github.com/anteriorm/mispisit-lab1

Ветка: lab3

Pull Request: https://github.com/anteriorm/mispisit-lab1/pull/1

Образ: `ghcr.io/anteriorm/mispisit-lab1:0.0.3` (GitHub Container Registry).

Для запуска необходимы Git и Docker с поддержкой Docker Compose. На Windows запустите Docker Desktop и дождитесь его готовности. Порт 8080 должен быть свободен.

Команды предполагают, что обновлённый образ с JPA опубликован в GitHub Container Registry под тегом 0.0.3 и доступен для скачивания без авторизации. В файле Docker Compose для приложения должен быть указан образ `ghcr.io/anteriorm/mispisit-lab1:0.0.3`.

Откройте PowerShell, CMD или терминал и выполните команды по очереди:

```powershell
git clone -b lab3 https://github.com/anteriorm/mispisit-lab1.git mispisit-klyanchenkov-lab3
cd mispisit-klyanchenkov-lab3
docker compose pull
docker compose up -d
```

Команда `docker compose pull` скачивает образы приложения и PostgreSQL. Команда `docker compose up -d` запускает оба сервиса. Таблица турниров создаётся автоматически при первом запуске без демонстрационных записей.

Откройте в браузере: http://localhost:8080

На главной странице должна отображаться версия 0.0.3.

## Повторный запуск

Из папки третьей лабораторной работы выполните:

```powershell
docker compose up -d
```

## Остановка

Из той же папки выполните:

```powershell
docker compose stop
```

Данные PostgreSQL сохраняются в томе dbdata проекта mispisit-klyanchenkov и остаются доступными после перезапуска контейнеров.

Для проверки сохранности после пересоздания контейнеров создайте турнир через форму, затем выполните:

```powershell
docker compose down
docker compose up -d
```

Откройте список и убедитесь, что турнир сохранился. Не используйте `docker compose down -v`: параметр `-v` удаляет том с данными.

## Страницы приложения

| Адрес | Назначение |
| --- | --- |
| / | Главная страница |
| /tournaments | Список турниров |
| /tournaments/new | Создание турнира |
| /tournaments/{id} | Карточка турнира |
| /tournaments/admin-panel | Перенаправление на страницу отказа |
| /access-denied | Страница отказа |
