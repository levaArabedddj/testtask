# Test Task: Backend Developer – WinWin Travel

## Опис

Проєкт складається з двох Spring Boot сервісів:  

- **testtask (Service A)** – відповідає за реєстрацію, логін користувачів та обробку тексту через `/process`.  
- **serviceB (Service B)** – обробляє текстові запити і повертає результат (наприклад, реверс та uppercase).  
- **Postgres** – зберігає користувачів та логи обробки.

Всі сервіси запускаються через Docker Compose.

---

## Структура проєкту

## 1.до кожного з сервісів виконати команду збирання у jar-файл testtask - mvn clean package -DskipTests, serviceB - mvn clean package -DskipTests

## 2.У корені проекту testtask є файл docker compose , виконати команду - docker compose up -d --build
## 3.По порядку виконати 3 контролери - 
### 1.curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"email\":\"Leva2348@gmail.com\",\"password\":\"123456\"}"
### 2.curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"email\":\"Leva2348@gmail.com\",\"password\":\"123456\"}"
### 3.curl -X POST http://localhost:8080/api/process -H "Content-Type: application/json" -H "Authorization: Bearer <token>" -d "{\"text\":\"hello world\"}"
