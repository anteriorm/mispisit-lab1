# VR-Tourney

Система управления турнирной сеткой VR-спорта.

**Лабораторная работа №2**  
Студент: Клянченков М.А., гр. ИСТбд-41

## Стек

- Java 21 (LTS)
- Spring Boot 4.1.1 (Web, Thymeleaf)
- Maven 3.9
- Apache Kafka (в стеке проекта)
- Docker (multi-platform: `linux/amd64`, `linux/arm64`)

## Что реализовано

| Функция | Адрес |
|---|---|
| Главная | `/` |
| Список турниров | `/tournaments` |
| Форма создания турнира | `/tournaments/new` |
| Карточка турнира | `/tournaments/{id}` |
| Отказ в доступе | `/access-denied` |

## Запуск

```bash
docker pull gitverse.ru/anteriorm/mispisit-lab1:0.0.2
docker run --rm -p 8080:8080 gitverse.ru/anteriorm/mispisit-lab1:0.0.2