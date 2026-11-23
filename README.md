# VR-Tourney — ЛР3 на Spring Data JPA

Клянченков Михаил, ИСТбд-41. Модуль управления турнирами.
Java 21, Spring Boot 4.1.1, Thymeleaf, Spring Data JPA, Hibernate, PostgreSQL 16.
Драйвер PostgreSQL JDBC 42.7.10; версии JPA и Hibernate управляются Spring Boot.

## Хранение

Tournament — @Entity, таблица tournaments, первичный ключ id (IDENTITY).
Название, дисциплина и формат отображаются в VARCHAR(80), LocalDate date — в DATE event_date.
TournamentRepository extends JpaRepository<Tournament, Long>.
TournamentService использует save/findAll/findById и @Transactional.
Схему автоматически создаёт schema.sql без демо-записей; Hibernate проверяет её через validate.
Таблица и столбцы прежней JDBC-версии сохранены. Новые изменения схемы оформляются миграциями.
JdbcTemplate в Java-коде больше не используется. JDBC остаётся драйвером под Hibernate.
Реальных ролей и авторизации здесь нет: сохранён отказ из ЛР2 по прежнему адресу.

## Запуск

Репозиторий: https://gitverse.ru/anteriorm/mispisit-lab1
Ветка: lab3
Pull Request: вставить фактическую ссылку.
Образ: gitverse.ru/anteriorm/mispisit-lab1:0.0.3
Страница образа: https://gitverse.ru/anteriorm/-/packages/container/mispisit-lab1/0.0.3

После публикации ветки и публичного образа:

```powershell
git clone -b lab3 https://gitverse.ru/anteriorm/mispisit-lab1
cd mispisit-lab1
docker compose up -d
```

Главная: http://localhost:8080
Список: http://localhost:8080/tournaments
Форма: http://localhost:8080/tournaments/new
Отказ: http://localhost:8080/tournaments/admin-panel

## Первый локальный запуск

```powershell
docker build -t gitverse.ru/anteriorm/mispisit-lab1:0.0.3 .
docker compose up -d
docker compose up -d --force-recreate app
```

После создания турнира проверьте сохранность:

```powershell
docker compose down
docker compose up -d
```

Не используйте down -v. Пустой список ожидается на новой базе; существующие записи сохраняются.
Именованный том dbdata относится к проекту mispisit-klyanchenkov. Порт БД наружу не открыт.

## Публикация

```powershell
docker login -u anteriorm gitverse.ru
docker buildx build --platform linux/amd64,linux/arm64 -t gitverse.ru/anteriorm/mispisit-lab1:0.0.3 --push .
docker buildx imagetools inspect gitverse.ru/anteriorm/mispisit-lab1:0.0.3
```

Отправьте изменения в lab3, создайте или обновите PR в main, сохраните ветку после слияния.
Образ должен скачиваться публично. Теги 0.0.1 и 0.0.2 не перезаписывайте.

## Тест

Интеграционный HTTP-тест использует настоящую отдельную пустую PostgreSQL-базу.
Задайте DATABASE_URL, DATABASE_USER и DATABASE_PASSWORD для тестовой базы,
затем выполните .\mvnw.cmd clean verify. Рабочую базу с турнирами не используйте.
Тест создаёт и удаляет свою запись. Он проверяет ORM, HTTP и валидацию;
проверка Docker-тома выполняется отдельно через down/up.
