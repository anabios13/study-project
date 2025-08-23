<!-- README.md — Оформленный вариант на русском языке -->

<h1 align="center" style="margin:24px 0 8px;">🏦 Полноценный REST API для страховой компании</h1>
<p align="center" style="color:#586069; font-size:14px; margin:0 0 20px;">
  Сервис обработки страховых случаев с авторизацией по <b>JWT</b>, чётким разграничением ролей и полным набором функций для работы со страховыми кейсами.
</p>

<div align="center" style="margin-bottom:22px; display:inline-flex; gap:8px; flex-wrap:wrap;">
  <img alt="api" src="https://img.shields.io/badge/API-REST-blue">
  <img alt="auth" src="https://img.shields.io/badge/Авторизация-JWT-orange">
  <img alt="roles" src="https://img.shields.io/badge/Роли-Клиент%20%7C%20Страховая%20%7C%20Ремонт-brightgreen">
</div>

<!-- Иллюстрации -->
<div align="center" style="margin: 16px 0 20px;">
  <img
    src="https://github.com/user-attachments/assets/6c2bb1da-7366-4d68-9d41-5a24fa8f8e5a"
    alt="Общая схема API"
    style="max-width: 960px; width: 100%; height: auto; border-radius: 12px; box-shadow: 0 8px 28px rgba(0,0,0,.15);"
  />
</div>
<div align="center" style="margin: 10px 0 28px;">
  <img
    src="https://github.com/user-attachments/assets/cb79b1a0-768d-40ea-b887-7c8446506db0"
    alt="Диаграмма API"
    style="max-width: 960px; width: 100%; height: auto; border-radius: 12px; box-shadow: 0 8px 28px rgba(0,0,0,.15);"
  />
</div>

<hr/>

<h2 id="summary">🧭 Краткое описание</h2>
<p>
  Данный REST API реализует полный цикл работы со страховыми случаями:
  <b>создание инцидента, назначение ремонтного центра, оценка ущерба, принятие решения (ремонт / тотал-лосс)</b>.
  Все операции выполняются через защищённый API с JWT-аутентификацией и ролевой моделью доступа.
</p>

<h3>📘 Термины</h3>
<ul>
  <li><b>Case / Assignment</b> — страховой случай (авто, контакты, фото, оценки, дополнения, итог).</li>
  <li><b>Estimate</b> — часть кейса с расчётом стоимости ремонта.</li>
  <li><b>Estimator</b> — представитель ремонтного центра, оценивающий ущерб.</li>
  <li><b>Total loss</b> — случай, когда ремонт экономически нецелесообразен.</li>
  <li><b>VIN</b> — идентификационный номер автомобиля (17 символов).</li>
</ul>

<hr/>

<h2 id="actors">🎭 Роли и их возможности</h2>

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap:16px;">

  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3>Клиент</h3>
    <ul>
      <li>Авторизация в системе</li>
      <li>Просмотр истории инцидентов и статусов</li>
      <li>Создание нового кейса:
        <ul>
          <li>Ввод VIN и данных авто</li>
          <li>Описание повреждений</li>
          <li>Загрузка фото</li>
          <li>Контактная информация</li>
        </ul>
      </li>
    </ul>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3>Страховая компания</h3>
    <ul>
      <li>Авторизация</li>
      <li>Получение и просмотр заданий</li>
      <li>Добавление деталей и комментариев</li>
      <li>Назначение ремонтного центра</li>
      <li>Рассмотрение Estimate (возврат на доработку)</li>
      <li>Финальное решение: <b>ремонт</b> или <b>тотал-лосс</b></li>
      <li>Учёт дополнительных соглашений (supplements)</li>
    </ul>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3>Ремонтный центр</h3>
    <ul>
      <li>Авторизация</li>
      <li>Получение заданий</li>
      <li>Настройка ставок и налогов</li>
      <li>Формирование Estimate:
        <ul>
          <li>Запчасти для замены</li>
          <li>Расчёт по ставкам, налогам, транспортировке</li>
        </ul>
      </li>
      <li>Отправка результата в страховую</li>
    </ul>
  </div>

</div>

<hr/>

<h2 id="workflow">🔄 Общий сценарий работы</h2>
<ol>
  <li>Клиент создаёт новый кейс (VIN, описание, фото).</li>
  <li>Страховая принимает задание и назначает ремонтный центр.</li>
  <li>Ремонтный центр формирует Estimate и отправляет в страховую.</li>
  <li>Страховая компания проверяет и принимает финальное решение.</li>
  <li>Результат фиксируется, возможны дополнительные дополнения (supplements).</li>
</ol>

<hr/>

<h2 id="auth">🔐 Авторизация (JWT)</h2>
<p>
  Авторизация основана на токенах JWT. После входа пользователь получает <code>access_token</code>, который передаётся в заголовке <code>Authorization</code>.
</p>

<pre><code># Пример: логин
POST /auth/login
Content-Type: application/json

{
  "username": "client@example.com",
  "password": "secret"
}

# Ответ
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR..."
}

# Использование токена
GET /api/v1/cases
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
</code></pre>

<hr/>

<h2 id="endpoints">🛣️ Примеры эндпоинтов</h2>

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap:16px;">
  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4>Клиент</h4>
    <pre><code>POST   /auth/login
GET    /clients/{id}/cases
POST   /cases
POST   /cases/{id}/photos
</code></pre>
  </div>
  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4>Страховая компания</h4>
    <pre><code>GET    /assignments
POST   /assignments/{id}/details
POST   /assignments/{id}/advice
POST   /assignments/{id}/send-to-repair/{facilityId}
POST   /estimates/{id}/review
POST   /cases/{id}/decision
POST   /cases/{id}/supplements
</code></pre>
  </div>
  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4>Ремонтный центр</h4>
    <pre><code>GET    /repair/assignments
POST   /repair/estimates
POST   /repair/estimates/{id}/submit
</code></pre>
  </div>
</div>

<hr/>

<h2 id="examples">📦 Примеры запросов</h2>

<details>
  <summary><b>Создание кейса (Клиент)</b></summary>
  <pre><code>POST /cases
{
  "vin": "1HGCM82633A004352",
  "vehicle": {"make": "Honda", "model": "Accord", "year": 2003},
  "description": "Повреждение заднего бампера.",
  "contact": {"name": "Иван Иванов", "phone": "+7-900-123-45-67", "email": "client@example.com"}
}
</code></pre>
</details>

<details>
  <summary><b>Estimate (Ремонтный центр)</b></summary>
  <pre><code>POST /repair/estimates
{
  "assignmentId": "A-10231",
  "parts": [
    {"partNumber":"RB-200","name":"Задний бампер","qty":1,"price":240.00},
    {"partNumber":"PB-011","name":"Окраска","qty":1,"price":120.00}
  ],
  "rates": {"laborPerHour": 60.0, "laborHours": 6},
  "taxes": 0.07,
  "transportation": 0.00,
  "total": 240 + 120 + (60*6) * 1.07
}
</code></pre>
</details>

<details>
  <summary><b>Финальное решение (Страховая)</b></summary>
  <pre><code>POST /cases/{id}/decision
{
  "decision": "total-loss",
  "notes": "Стоимость ремонта превышает стоимость автомобиля."
}
</code></pre>
</details>

<hr/>

<h2 id="lifecycle">🧩 Жизненный цикл кейса</h2>
<p><code>Создан → Назначен → Оценка → Проверка → Решение (ремонт/тотал-лосс) → Завершён</code></p>

<hr/>

<h2 id="security">🛡️ Безопасность</h2>
<ul>
  <li>Авторизация: JWT (заголовок <code>Authorization: Bearer &lt;token&gt;</code>)</li>
  <li>Разграничение прав по ролям (Клиент, Страховая, Ремонтный центр)</li>
  <li>Валидация данных: VIN, контакты, список деталей</li>
</ul>

<hr/>

<p align="center" style="font-size:13px; color:#6a737d;">
  Полный цикл страховых кейсов в виде REST API. Чёткие роли, безопасность и гибкость.
</p>
