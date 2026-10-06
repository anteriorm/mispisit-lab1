# VR-Tourney

Система управления турнирной сеткой VR-спорта.

**Лабораторная работа №1**  
Дисциплина: Методы и средства проектирования информационных систем  
Студент: Клянченков М.А., гр. ИСТбд-31

## Стек

- Java 21 (LTS)
- Spring Boot 3.3.4 (Web, Thymeleaf)
- Maven 3.9
- Apache Kafka (в стеке проекта, подключается на следующих этапах)
- Docker (multi-platform: `linux/amd64`, `linux/arm64`)

## Образ

Опубликован в GitVerse: `gitverse.ru/anteriorm/mispisit-lab1:0.0.1`

Поддерживаются две архитектуры: **Intel/AMD** и **ARM** (Apple Silicon, ARM-серверы).  
Docker автоматически выбирает нужный вариант под платформу пользователя.

## Запуск

```bash
docker pull gitverse.ru/anteriorm/mispisit-lab1:0.0.1
docker run --rm -p 8080:8080 gitverse.ru/anteriorm/mispisit-lab1:0.0.1