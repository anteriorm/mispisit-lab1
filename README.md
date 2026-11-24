# VR-Tourney — лабораторная работа №4

VR-Tourney помогает организатору создавать турниры, а участнику просматривать
расписание и карточки соревнований. Единый интерфейс работает в Spring Boot.
Thymeleaf формирует страницы, Bootstrap 5.3.3 оформляет таблицу, форму и кнопки.
CSS загружается из WebJars внутри приложения. Турниры хранятся в PostgreSQL 16
через Spring Data JPA.

## Запуск

- Репозиторий: https://github.com/anteriorm/mispisit-lab1
- Ветка: `lab4`
- Pull Request: добавить ссылку после открытия PR из `lab4` в `main`.
- Образ: `ghcr.io/anteriorm/mispisit-lab1:0.0.4`.

После публикации ветки и публичного образа выполните:

```bash
git clone -b lab4 https://github.com/anteriorm/mispisit-lab1.git
cd mispisit-lab1
docker compose up -d
```

Откройте http://localhost:8080. Перейдите в «Турниры», создайте турнир
и откройте его карточку. Пункт «Панель администратора» ведёт на экран отказа.
База сохраняется в томе dbdata проекта mispisit-klyanchenkov.

## Публикация образа

```bash
docker buildx build --platform linux/amd64,linux/arm64 -t ghcr.io/anteriorm/mispisit-lab1:0.0.4 --push .
docker buildx imagetools inspect ghcr.io/anteriorm/mispisit-lab1:0.0.4
```
