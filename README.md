# 🎮 DSList API

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/H2-Database-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
</p>

<p align="center">
  API REST para gerenciamento de listas de jogos, com suporte a múltiplas categorias e reordenação de posições.
</p>

---

## 📋 Sobre o Projeto

O **DSList** é uma API REST desenvolvida em **Java com Spring Boot**, que permite gerenciar coleções de jogos organizados em listas por categoria. O projeto foi construído seguindo boas práticas de arquitetura em camadas, com suporte a múltiplos ambientes (teste, desenvolvimento e produção).

---

## 🏗️ Arquitetura

```
dslist/
├── controllers/       # Camada de apresentação (endpoints REST)
├── services/          # Regras de negócio
├── repositories/      # Acesso ao banco de dados (Spring Data JPA)
├── entities/          # Entidades JPA (mapeamento das tabelas)
├── dto/               # Objetos de transferência de dados
├── projections/       # Interfaces para queries nativas
└── config/            # Configurações (CORS, etc.)
```

### Modelo de Dados

```
tb_game_list          tb_game
─────────────         ──────────────────
id (PK)               id (PK)
name                  title
                      game_year
                      genre
         tb_belonging  platforms
         ────────────  score
         list_id (FK)  img_url
         game_id (FK)  short_description
         position      long_description
```

---

## 📡 Endpoints

### 🎮 Games

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/games` | Retorna todos os jogos (versão resumida) |
| `GET` | `/games/{id}` | Retorna os detalhes completos de um jogo |

### 📋 Lists

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/lists` | Retorna todas as listas de jogos |
| `GET` | `/lists/{id}` | Retorna uma lista específica |
| `GET` | `/lists/{listId}/games` | Retorna os jogos de uma lista, ordenados por posição |
| `POST` | `/lists/{listId}/replacement` | Reordena a posição de um jogo na lista |

### Exemplo de resposta — `GET /games`

```json
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "year": 2012,
    "imgUrl": "https://...",
    "shortDescription": "Lorem ipsum..."
  }
]
```

### Exemplo de resposta — `GET /games/{id}`

```json
{
  "id": 1,
  "title": "Mass Effect Trilogy",
  "year": 2012,
  "genre": "Role-playing (RPG), Shooter",
  "platforms": "XBox, Playstation, PC",
  "score": 4.8,
  "imgUrl": "https://...",
  "shortDescription": "Lorem ipsum...",
  "longDescription": "Lorem ipsum..."
}
```

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (produção e desenvolvimento)
- **H2 Database** (testes em memória)
- **Maven**

---

## ⚙️ Perfis de Ambiente

O projeto possui 3 perfis configurados:

| Perfil | Banco de Dados | Uso |
|--------|---------------|-----|
| `test` | H2 (in-memory) | Padrão local / testes |
| `dev` | PostgreSQL local | Desenvolvimento |
| `prod` | PostgreSQL via variáveis de ambiente | Produção |

O perfil ativo é definido em `application.properties`:
```properties
spring.profiles.active=${APP_PROFILE:test}
```

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL (opcional, apenas para perfis `dev` e `prod`)

### Clonando o repositório

```bash
git clone https://github.com/seu-usuario/dslist.git
cd dslist
```

### Rodando com perfil de teste (H2 — sem precisar de banco)

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

Console H2 disponível em: `http://localhost:8080/h2-console`

### Rodando com PostgreSQL (perfil dev)

1. Crie o banco de dados:
```sql
CREATE DATABASE dslist;
```

2. Configure as credenciais em `application-dev.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/dslist
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

3. Rode com o perfil `dev`:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## 🌐 Variáveis de Ambiente (Produção)

| Variável | Descrição |
|----------|-----------|
| `APP_PROFILE` | Perfil ativo (`prod`) |
| `DB_URL` | URL de conexão com o banco |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `CORS_ORIGINS` | Origins permitidas pelo CORS (ex: `https://meusite.com`) |

---

## 🔒 CORS

O CORS é configurado via `application.properties`. Por padrão, aceita requisições de:

```
http://localhost:5173
http://localhost:3000
```

Em produção, defina a variável `CORS_ORIGINS` com o domínio do seu front-end.

---

## 👨‍💻 Autor

Desenvolvido por **Matheus Soares**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/seu-perfil)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/seu-usuario)
[![Portfolio](https://img.shields.io/badge/Portfolio-matheussoares.dev.br-blueviolet?style=for-the-badge)](https://matheussoares.dev.br)
