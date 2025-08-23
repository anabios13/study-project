<!-- README.md — HTML-styled for Full REST API for Insurance Company -->

<h1 align="center" style="margin:24px 0 8px;">🏦 Full REST API for Insurance Company</h1>
<p align="center" style="color:#586069; font-size:14px; margin:0 0 20px;">
  Полноценный REST API для страховой компании с авторизацией по <b>JWT</b> и четко выделенными ролями: Client, Insurance Company, Repair Facility.
</p>

<div align="center" style="margin-bottom:22px; display:inline-flex; gap:8px; flex-wrap:wrap;">
  <img alt="api" src="https://img.shields.io/badge/API-REST-blue">
  <img alt="auth" src="https://img.shields.io/badge/Auth-JWT-orange">
  <img alt="roles" src="https://img.shields.io/badge/Roles-Client%20%7C%20Insurer%20%7C%20Repair-brightgreen">
</div>

<!-- Hero images -->
<div align="center" style="margin: 16px 0 20px;">
  <img
    src="https://github.com/user-attachments/assets/6c2bb1da-7366-4d68-9d41-5a24fa8f8e5a"
    alt="Insurance API Overview 1"
    style="max-width: 960px; width: 100%; height: auto; border-radius: 12px; box-shadow: 0 8px 28px rgba(0,0,0,.15);"
  />
</div>
<div align="center" style="margin: 10px 0 28px;">
  <img
    src="https://github.com/user-attachments/assets/cb79b1a0-768d-40ea-b887-7c8446506db0"
    alt="Insurance API Overview 2"
    style="max-width: 960px; width: 100%; height: auto; border-radius: 12px; box-shadow: 0 8px 28px rgba(0,0,0,.15);"
  />
</div>

<hr style="margin: 12px 0 24px;"/>

<h2 id="summary">🧭 Summary</h2>
<p>
  Платформенная основа для обработки страховых случаев: регистрация инцидента, назначение ремонтной станции,
  оценка ущерба, принятие решения (repair / total loss) и фиксация итогов. Все взаимодействие — через защищённый
  REST API с ролями и JWT-аутентификацией.
</p>

<h3>📘 Domain glossary</h3>
<ul>
  <li><b>Case / Assignment</b> — основная сущность страхового случая: автомобиль, контакты, оценки ремонта, доп. соглашения, итог.</li>
  <li><b>Estimate</b> — часть Assignment с расчетом стоимости ремонта.</li>
  <li><b>Estimator</b> — представитель ремонтного центра, предоставляющий оценку.</li>
  <li><b>Total loss</b> — случай, когда ремонт экономически нецелесообразен (или авто неремонтопригодно).</li>
  <li><b>VIN</b> — 17-символьный идентификатор транспортного средства.</li>
</ul>

<hr style="margin: 12px 0 24px;"/>

<h2 id="actors">🎭 Actors & Capabilities</h2>

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap:16px;">
  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3 style="margin:6px 0 8px;">Client</h3>
    <ul>
      <li>Логин в систему</li>
      <li>Просмотр истории инцидентов и статусов</li>
      <li>Создание нового «unassigned» кейса:
        <ul>
          <li>VIN + сведения об авто</li>
          <li>Описание повреждений (текст)</li>
          <li>Фото повреждений</li>
          <li>Контактная информация</li>
        </ul>
      </li>
    </ul>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3 style="margin:6px 0 8px;">Insurance Company</h3>
    <ul>
      <li>Логин</li>
      <li>Получение задания (assignment)</li>
      <li>Добавление деталей / советов для оценщика</li>
      <li>Назначение repair facility для кейса</li>
      <li>Ревью Estimate (возврат на доработку при необходимости)</li>
      <li>Финальное решение: <b>repair</b> или <b>total loss</b></li>
      <li>Рассмотрение/учет доп. соглашений (supplements)</li>
    </ul>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:14px;">
    <h3 style="margin:6px 0 8px;">Repair Facility</h3>
    <ul>
      <li>Логин</li>
      <li>Получение assignment</li>
      <li>Настройка налогов/ставок работ</li>
      <li>Подготовка Estimate:
        <ul>
          <li>Список запчастей для замены</li>
          <li>Доп. начисления: work rates, taxes, транспортировка</li>
        </ul>
      </li>
      <li>Отправка оцененного assignment обратно Insurer</li>
    </ul>
  </div>
</div>

<hr style="margin: 16px 0 24px;"/>

<h2 id="flow">🔄 High-level workflow</h2>
<ol>
  <li><b>Client</b> создаёт Case (unassigned) → прикладывает VIN, описание, фото.</li>
  <li><b>Insurance Company</b> принимает Assignment → назначает <b>Repair Facility</b>.</li>
  <li><b>Repair Facility</b> формирует <b>Estimate</b> → отправляет на ревью.</li>
  <li><b>Insurance Company</b> утверждает/возвращает на доработку → выносит <b>Final Decision</b> (repair/total loss).</li>
  <li>Фиксация результатов, возможны <b>supplements</b> и изменение решения.</li>
</ol>

<hr style="margin: 12px 0 20px;"/>

<h2 id="auth">🔐 Authentication (JWT)</h2>
<p><b>Логин</b> → получаем <code>access_token</code> → используем в <code>Authorization: Bearer &lt;token&gt;</code> для всех защищённых эндпоинтов.</p>

<pre><code># Login (пример)
POST /auth/login
Content-Type: application/json

{
  "username": "client@example.com",
  "password": "secret"
}

# Ответ
200 OK
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR..."
}

# Запрос с токеном
GET /api/v1/cases
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
</code></pre>

<hr style="margin: 12px 0 20px;"/>

<h2 id="endpoints">🛣️ Example endpoints (draft)</h2>

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap:16px;">
  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4 style="margin:6px 0;">Client</h4>
    <pre><code>POST   /auth/login
GET    /clients/{id}/cases
POST   /cases           # create unassigned
POST   /cases/{id}/photos
</code></pre>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4 style="margin:6px 0;">Insurance Company</h4>
    <pre><code>GET    /assignments
POST   /assignments/{id}/details
POST   /assignments/{id}/advice
POST   /assignments/{id}/send-to-repair/{facilityId}
POST   /estimates/{id}/review
POST   /cases/{id}/decision   # repair | total-loss
POST   /cases/{id}/supplements
</code></pre>
  </div>

  <div style="border:1px solid #eaecef; border-radius:10px; padding:12px;">
    <h4 style="margin:6px 0;">Repair Facility</h4>
    <pre><code>GET    /repair/assignments
POST   /repair/estimates
POST   /repair/estimates/{id}/submit
</code></pre>
  </div>
</div>

<p style="font-size:12px; color:#6a737d; margin-top:8px;">
  Конкретные пути/контракты могут отличаться в реализации. Блок — ориентир для организации API.
</p>

<hr style="margin: 12px 0 20px;"/>

<h2 id="payload">📦 Примеры полезных payload’ов</h2>

<details>
  <summary><b>Создание кейса (Client → Case)</b></summary>
  <pre><code>POST /cases
Content-Type: application/json

{
  "vin": "1HGCM82633A004352",
  "vehicle": {
    "make": "Honda",
    "model": "Accord",
    "year": 2003
  },
  "description": "Rear bumper damage after minor collision.",
  "contact": {
    "name": "John Doe",
    "phone": "+1-555-123-45-67",
    "email": "client@example.com"
  }
}
</code></pre>
</details>

<details>
  <summary><b>Estimate (Repair Facility → Insurer)</b></summary>
  <pre><code>POST /repair/estimates
Content-Type: application/json

{
  "assignmentId": "A-10231",
  "parts": [
    {"partNumber":"RB-200","name":"Rear Bumper","qty":1,"price":240.00},
    {"partNumber":"PB-011","name":"Paint & Materials","qty":1,"price":120.00}
  ],
  "rates": {
    "laborPerHour": 60.0,
    "laborHours": 6
  },
  "taxes": 0.07,
  "transportation": 0.00,
  "total": 240 + 120 + (60*6) * 1.07
}
</code></pre>
</details>

<details>
  <summary><b>Final decision (Insurer)</b></summary>
  <pre><code>POST /cases/{id}/decision
Content-Type: application/json

{
  "decision": "total-loss",  // or "repair"
  "notes": "Repair cost exceeds vehicle value."
}
</code></pre>
</details>

<hr style="margin: 12px 0 20px;"/>

<h2 id="status">🧩 Жизненный цикл кейса (пример)</h2>
<p>
  <code>Created</code> → <code>Assigned</code> → <code>Estimating</code> → <code>Review</code> →
  <code>Decision(repair | total-loss)</code> → <code>Closed</code>
</p>

<hr style="margin: 12px 0 20px;"/>

<h2 id="security">🛡️ Security & Roles</h2>
<ul>
  <li><b>JWT</b> для аутентификации: заголовок <code>Authorization: Bearer &lt;token&gt;</code></li>
  <li>Ролевой доступ (RBAC): Client / Insurance / Repair Facility</li>
  <li>Валидация и нормализация входных данных (VIN, контактные данные, список запчастей)</li>
</ul>

<hr style="margin: 16px 0 24px;"/>

<p align="center" style="font-size:13px; color:#6a737d;">
  Чёткая модель, ясные роли, защищённый REST. Готово к интеграции и развитию.
</p>
