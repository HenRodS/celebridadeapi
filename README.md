# 🎬 Celebridades API

API REST desenvolvida com **Spring Boot** para gerenciamento de celebridades, permitindo operações de CRUD completo com validações e tratamento de erros padronizado.

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.0 |
| Spring Data JPA | — |
| Spring Validation | — |
| Banco de Dados | H2 (em memória) |
| Lombok | — |
| Maven | — |

---

## ▶️ Como Rodar a Aplicação

### Pré-requisitos

- **Java 21** ou superior instalado
- **Maven** instalado (ou use o wrapper incluso `./mvnw`)

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd memeapi
```

### 2. Execute a aplicação

**Com o Maven Wrapper (recomendado):**

```bash
./mvnw spring-boot:run
```

**Com Maven instalado globalmente:**

```bash
mvn spring-boot:run
```

**Ou compile e execute o JAR:**

```bash
./mvnw package -DskipTests
java -jar target/celebridades-0.0.1-SNAPSHOT.jar
```

### 3. Verifique que está rodando

A aplicação sobe na porta **8080** por padrão. Acesse:

```
http://localhost:8080/api/celebridades
```

> **Banco de dados:** O H2 é em memória — os dados são apagados ao reiniciar a aplicação. O console do H2 está disponível em `http://localhost:8080/h2-console`.

---

## 📋 Endpoints da API

**Base URL:** `http://localhost:8080/api/celebridades`

---

### 📦 Modelo de Dados

#### Request Body (`CelebridadeRequestDTO`)

```json
{
  "nome": "Anitta",
  "idade": 31,
  "motivoFama": "Cantora"
}
```

| Campo | Tipo | Obrigatório | Validações |
|---|---|---|---|
| `nome` | `String` | ✅ | Entre 2 e 150 caracteres |
| `idade` | `Integer` | ✅ | Entre 0 e 150 |
| `motivoFama` | `String` | ✅ | Entre 2 e 200 caracteres |

#### Response Body (`CelebridadeResponseDTO`)

```json
{
  "id": 1,
  "nome": "Anitta",
  "idade": 31,
  "motivoFama": "Cantora"
}
```

---

### `GET /api/celebridades` — Listar todas as celebridades

Retorna a lista completa de celebridades cadastradas. Suporta filtragem por query params.

**Query Parameters (opcionais):**

| Parâmetro | Tipo | Descrição | Exemplo |
|---|---|---|---|
| `nome` | `String` | Filtra pelo nome (busca parcial, case-insensitive) | `?nome=messi` |
| `motivoFama` | `String` | Filtra pelo motivo de fama (busca parcial, case-insensitive) | `?motivoFama=ator` |

**Exemplos de requisição:**

```http
GET http://localhost:8080/api/celebridades
GET http://localhost:8080/api/celebridades?nome=messi
GET http://localhost:8080/api/celebridades?motivoFama=ator
```

**Resposta de sucesso — `200 OK`:**

```json
[
  {
    "id": 1,
    "nome": "Lionel Messi",
    "idade": 37,
    "motivoFama": "Futebolista"
  },
  {
    "id": 2,
    "nome": "Anitta",
    "idade": 31,
    "motivoFama": "Cantora"
  }
]
```

---

### `GET /api/celebridades/{id}` — Buscar celebridade por ID

Retorna uma celebridade específica pelo seu identificador.

**Path Parameter:**

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | ID da celebridade |

**Exemplo de requisição:**

```http
GET http://localhost:8080/api/celebridades/1
```

**Resposta de sucesso — `200 OK`:**

```json
{
  "id": 1,
  "nome": "Lionel Messi",
  "idade": 37,
  "motivoFama": "Futebolista"
}
```

**Resposta de erro — `404 Not Found`:**

```json
{
  "timestamp": "2026-06-26T17:00:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Celebridade com ID 999 não encontrada"
}
```

---

### `POST /api/celebridades` — Cadastrar nova celebridade

Cria um novo registro de celebridade.

**Exemplo de requisição:**

```http
POST http://localhost:8080/api/celebridades
Content-Type: application/json

{
  "nome": "Anitta",
  "idade": 31,
  "motivoFama": "Cantora"
}
```

**Resposta de sucesso — `201 Created`:**

```json
{
  "id": 3,
  "nome": "Anitta",
  "idade": 31,
  "motivoFama": "Cantora"
}
```

**Resposta de erro — `400 Bad Request` (validação):**

```json
{
  "timestamp": "2026-06-26T17:00:00",
  "status": 400,
  "erro": "Dados inválidos",
  "campos": {
    "nome": "O nome é obrigatório",
    "idade": "A idade não pode ser negativa",
    "motivoFama": "O motivo da fama é obrigatório"
  }
}
```

---

### `PUT /api/celebridades/{id}` — Atualizar celebridade

Atualiza todos os dados de uma celebridade existente.

**Path Parameter:**

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | ID da celebridade a ser atualizada |

**Exemplo de requisição:**

```http
PUT http://localhost:8080/api/celebridades/1
Content-Type: application/json

{
  "nome": "Lionel Messi",
  "idade": 38,
  "motivoFama": "Futebolista - GOAT"
}
```

**Resposta de sucesso — `200 OK`:**

```json
{
  "id": 1,
  "nome": "Lionel Messi",
  "idade": 38,
  "motivoFama": "Futebolista - GOAT"
}
```

**Resposta de erro — `404 Not Found`:**

```json
{
  "timestamp": "2026-06-26T17:00:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Celebridade com ID 999 não encontrada"
}
```

---

### `DELETE /api/celebridades/{id}` — Deletar celebridade

Remove uma celebridade pelo seu identificador.

**Path Parameter:**

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | ID da celebridade a ser deletada |

**Exemplo de requisição:**

```http
DELETE http://localhost:8080/api/celebridades/5
```

**Resposta de sucesso — `204 No Content`** *(sem corpo)*

**Resposta de erro — `404 Not Found`:**

```json
{
  "timestamp": "2026-06-26T17:00:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Celebridade com ID 999 não encontrada"
}
```

---

## 🗂️ Resumo dos Endpoints

| Método | Endpoint | Descrição | Status de Sucesso |
|---|---|---|---|
| `GET` | `/api/celebridades` | Listar todas (com filtros opcionais) | `200 OK` |
| `GET` | `/api/celebridades/{id}` | Buscar por ID | `200 OK` |
| `POST` | `/api/celebridades` | Cadastrar nova celebridade | `201 Created` |
| `PUT` | `/api/celebridades/{id}` | Atualizar celebridade | `200 OK` |
| `DELETE` | `/api/celebridades/{id}` | Deletar celebridade | `204 No Content` |

---

## 🧪 Testando a API

O arquivo `test_celebridades.http` na raiz do projeto contém exemplos prontos para todos os cenários. Ele pode ser executado com a extensão **REST Client** do VS Code ou com o cliente HTTP do **IntelliJ IDEA**.

---

## 📁 Estrutura do Projeto

```
src/main/java/com/ifsul/celebridades/
├── CelebridadesApplication.java   # Classe principal
├── controller/                    # Camada de Controllers (REST)
├── service/                       # Camada de Serviços (regras de negócio)
├── repository/                    # Camada de Repositório (JPA)
├── model/                         # Entidades JPA
├── dto/                           # DTOs de Request e Response
└── exception/                     # Tratamento global de exceções
```
