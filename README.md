# VR-Tourney — лабораторная работа №5

После создания турнира участник видит на странице «Уведомления» сообщение
со статусом «Новое» и ссылкой на карточку турнира. Турнир и уведомление
сохраняются в одной транзакции. Приложение создаёт таблицу
`tournament_notifications` при запуске через `schema.sql`.

## Запуск

Репозиторий: https://github.com/anteriorm/mispisit-lab1
Ветка: lab5
Pull Request: https://github.com/anteriorm/mispisit-lab1/pull/3
Образ: ghcr.io/anteriorm/mispisit-lab1:0.0.5

После публикации ветки и публичного образа:

```powershell
git clone -b lab5 https://github.com/anteriorm/mispisit-lab1.git
cd mispisit-lab1
docker compose up -d
```

Откройте http://localhost:8080 и перейдите в «Уведомления». Если турниры
ещё не создавались в версии 0.0.5, экран пуст. Создайте турнир и вернитесь
в «Уведомления». Ссылка в новом сообщении откроет его карточку.
