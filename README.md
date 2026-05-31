# OrbitShield API

## Descrição do Projeto

**OrbitShield** é uma API REST B2B para cadastro e monitoramento de satélites artificiais em órbita. Empresas clientes registram seus satélites na plataforma e consultam alertas de risco de colisão e status orbital em tempo real.

O projeto implementa uma arquitetura em camadas com autenticação via API Key, seguindo princípios RESTful e boas práticas de desenvolvimento.

---

## ODS — Objetivo de Desenvolvimento Sustentável

**ODS 9 — Indústria, Inovação e Infraestrutura**

O projeto se alinha a este ODS por:
- Promover inovação tecnológica no monitoramento espacial
- Garantir resiliência de infraestrutura crítica (satélites)
- Facilitar infraestrutura de informação confiável para o setor espacial

---

## Integrantes

| Nome            | RM     |
|-----------------|--------|
| Arthur Pagani   | RM554510 |
| Diogo Leles     | RM558487 |
| Felipe Oliveira | RM559085 |
| Ryan Brito      | RM554497 |
| Vitor Chaves    | RM557067 |

---

## Arquitetura

### Diagrama de Camadas

```
┌──────────────────────────────────────────────────────────────┐
│                        Cliente (B2B)                         │
│              (Postman / sistema externo / curl)              │
└───────────────────────────┬──────────────────────────────────┘
                            │ HTTPS + X-API-Key header
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                   API HTTP Layer                             │
│  ┌─────────────────────────────────────────────────────┐    │
│  │           Controllers (Spring REST)                  │    │
│  │  SatelliteController │ CollisionEventController      │    │
│  │  SubscriptionController │ AlertController            │    │
│  └──────────────┬──────────────────────────────────────┘    │
│                 │                                            │
│  ┌──────────────▼──────────────────────────────────────┐    │
│  │        Business Logic Layer (Services)              │    │
│  │  SatelliteService │ CollisionEventService            │    │
│  │  SubscriptionService │ AlertService                  │    │
│  └──────────────┬──────────────────────────────────────┘    │
│                 │                                            │
│  ┌──────────────▼──────────────────────────────────────┐    │
│  │        Data Access Layer (Repositories)             │    │
│  │  SatelliteRepository │ CollisionEventRepository      │    │
│  │  SubscriptionRepository │ ApiKeyRepository           │    │
│  └──────────────┬──────────────────────────────────────┘    │
│                 │                                            │
│  ┌──────────────▼──────────────────────────────────────┐    │
│  │           Persistence (Database)                    │    │
│  │           H2 (Development) / SQLite                 │    │
│  └─────────────────────────────────────────────────────┘    │
└──────────────────────────────────────────────────────────────┘
```

### Componentes Principais

| Componente | Responsabilidade |
|---|---|
| **Controllers** | Receber requisições HTTP, validar entrada, retornar respostas padronizadas |
| **Services** | Implementar regras de negócio, orquestração, validações de domínio |
| **Repositories** | Acesso aos dados via JPA |
| **Models/Entities** | Estruturas de dados mapeadas para tabelas do banco |
| **DTOs** | Contratos de entrada/saída da API (request/response) |
| **Filters** | Interceptar requisições para autenticação via API Key |
| **Exceptions** | Tratamento centralizado de erros |

### Responsabilidades

- **Controller**: Nunca acessa Repository diretamente; sempre passa pelo Service
- **Service**: Contém toda lógica de negócio e validações
- **Repository**: Interface pura de persistência; sem lógica
- **Model**: POJO simples; sem lógica de negócio

---

## Stack Tecnológico

- **Java 21 LTS** - Linguagem de programação
- **Spring Boot 3.4.3** - Framework web
- **Spring Data JPA** - ORM e persistência
- **H2 Database** - Banco de dados em memória (desenvolvimento)
- **SpringDoc OpenAPI 2.8.5** - Documentação Swagger/OpenAPI
- **Gradle** - Gerenciador de dependências
- **SLF4J / Logback** - Logging

---

## Como Executar Localmente

### Pré-requisitos

- Java 21 JDK instalado
- Gradle instalado (ou use `./gradlew` do projeto)
- Git

### Passos

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/OrbitShield-api.git
   cd OrbitShield-api
   ```

2. **Compile e inicie a aplicação**
   ```bash
   ./gradlew bootRun
   ```
   
   ou (Windows):
   ```powershell
   .\gradlew.bat bootRun
   ```

3. **Verifique se a aplicação iniciou**
   ```bash
   curl http://localhost:8080/health
   ```
   
   Resposta esperada:
   ```json
   {
     "status": "UP",
     "service": "OrbitShield API"
   }
   ```

4. **Acesse a documentação Swagger**
   - URL: http://localhost:8080/swagger-ui.html

### API Keys de Teste

Use uma das seguintes chaves nos testes:

| Chave | Empresa | Hash SHA-256 |
|-------|---------|------|
| `OrbitShield_testkey_alpha` | SpaceX | `93e08d75d40523d42bac5777cd4bab24147f0d12287fffc22bf4cbad4b7034be` |
| `OrbitShield_testkey_beta` | ESA | `d34d4715554805a55ba4b0f69e6d749ccf6f243ef9f4e35d0db1b951ebaeb8ed` |

---

## Endpoints da API

Base URL: `http://localhost:8080/api/v1`

### Satélites

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/satellites` | Listar satélites (paginado) | 200 |
| `GET` | `/satellites/{id}` | Obter satélite por ID | 200/404 |
| `POST` | `/satellites` | Criar novo satélite | 201/400/409 |
| `PUT` | `/satellites/{id}` | Atualizar satélite | 200/404/409 |
| `DELETE` | `/satellites/{id}` | Deletar satélite | 204/404 |

### Eventos de Colisão

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/satellites/{satelliteId}/events` | Listar eventos de um satélite | 200/404 |
| `GET` | `/events/{id}` | Obter evento específico | 200/404 |
| `POST` | `/satellites/{satelliteId}/events` | Criar novo evento de colisão | 201/400/404 |
| `PUT` | `/events/{id}/resolve` | Marcar evento como resolvido | 200/404 |
| `DELETE` | `/events/{id}` | Deletar evento | 204/404 |

### Subscriptions (Seguir Satélite)

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/subscriptions` | Listar satélites seguidos | 200 |
| `POST` | `/subscriptions` | Seguir um satélite | 201/404/409 |
| `DELETE` | `/subscriptions/{satelliteId}` | Deixar de seguir | 204/404 |

### Alertas

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/alerts` | Listar alertas ativos dos satélites seguidos | 200 |

### Utilitários

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/health` | Health check (sem autenticação) | 200 |
| `GET` | `/swagger-ui.html` | Documentação interativa | 200 |

---

## Exemplos de Requisições

### 1. Health Check (sem autenticação)
```bash
curl -X GET http://localhost:8080/health
```

### 2. Listar Satélites (com autenticação)
```bash
curl -X GET http://localhost:8080/api/v1/satellites?page=0&size=20 \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (200 OK):**
```json
{
  "status": "success",
  "data": [
    {
      "id": "bbb00000-0000-0000-0000-000000000001",
      "name": "StarLink-1234",
      "ownerCompany": "SpaceX",
      "noradId": "48274",
      "orbitType": "LEO",
      "altitudeKm": 550.0,
      "inclination": 53.0,
      "status": "ACTIVE",
      "createdAt": "2026-05-25T10:00:00Z",
      "updatedAt": "2026-05-25T10:00:00Z"
    }
  ],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 3,
    "totalPages": 1
  },
  "timestamp": "2026-05-25T10:00:00Z"
}
```

### 3. Criar Satélite
```bash
curl -X POST http://localhost:8080/api/v1/satellites \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "GOES-18",
    "ownerCompany": "NOAA",
    "noradId": "45749",
    "orbitType": "GEO",
    "altitudeKm": 35786.0,
    "inclination": 0.5,
    "status": "ACTIVE"
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "name": "GOES-18",
    "ownerCompany": "NOAA",
    "noradId": "45749",
    "orbitType": "GEO",
    "altitudeKm": 35786.0,
    "inclination": 0.5,
    "status": "ACTIVE",
    "createdAt": "2026-05-25T10:05:00Z",
    "updatedAt": "2026-05-25T10:05:00Z"
  },
  "timestamp": "2026-05-25T10:05:00Z"
}
```

### 4. Criar Evento de Colisão
```bash
curl -X POST http://localhost:8080/api/v1/satellites/bbb00000-0000-0000-0000-000000000001/events \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "objectName": "Debris-2020-055",
    "probability": 0.05,
    "closestApproach": "2026-06-01T14:30:00Z",
    "distanceKm": 0.5
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "satelliteId": "bbb00000-0000-0000-0000-000000000001",
    "satelliteName": "StarLink-1234",
    "objectName": "Debris-2020-055",
    "probability": 0.05,
    "closestApproach": "2026-06-01T14:30:00Z",
    "distanceKm": 0.5,
    "severity": "HIGH",
    "resolved": false,
    "createdAt": "2026-05-25T10:10:00Z"
  },
  "timestamp": "2026-05-25T10:10:00Z"
}
```

### 5. Seguir um Satélite (Subscribe)
```bash
curl -X POST http://localhost:8080/api/v1/subscriptions \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "satelliteId": "bbb00000-0000-0000-0000-000000000002"
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "satelliteId": "bbb00000-0000-0000-0000-000000000002",
    "satelliteName": "Sentinel-2A",
    "createdAt": "2026-05-25T10:15:00Z"
  },
  "timestamp": "2026-05-25T10:15:00Z"
}
```

### 6. Obter Alertas Ativos
```bash
curl -X GET http://localhost:8080/api/v1/alerts \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (200 OK):**
```json
{
  "status": "success",
  "data": [
    {
      "id": "ccc00000-0000-0000-0000-000000000001",
      "satelliteId": "bbb00000-0000-0000-0000-000000000001",
      "satelliteName": "StarLink-1234",
      "objectName": "Debris-2019-006",
      "probability": 0.032,
      "closestApproach": "2026-06-01T14:30:00Z",
      "distanceKm": 0.8,
      "severity": "HIGH",
      "resolved": false,
      "createdAt": "2026-05-25T10:00:00Z"
    }
  ],
  "timestamp": "2026-05-25T10:20:00Z"
}
```

### 7. Erro — Satélite Não Encontrado (404)
```bash
curl -X GET http://localhost:8080/api/v1/satellites/id-inexistente \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (404 Not Found):**
```json
{
  "status": "error",
  "error": {
    "code": "SATELLITE_NOT_FOUND",
    "message": "Satellite with id 'id-inexistente' not found.",
    "path": "/api/v1/satellites/id-inexistente"
  },
  "timestamp": "2026-05-25T10:25:00Z"
}
```

### 8. Erro — API Key Ausente (401)
```bash
curl -X GET http://localhost:8080/api/v1/satellites
```

**Resposta (401 Unauthorized):**
```json
{
  "status": "error",
  "error": {
    "code": "UNAUTHORIZED",
    "message": "API Key is required.",
    "path": "/api/v1/satellites"
  },
  "timestamp": "2026-05-25T10:30:00Z"
}
```

---

## Respostas às Perguntas Discursivas

### Pergunta 1: Escalabilidade com Milhares de Usuários Simultâneos

Os principais desafios seriam:

**(a) Concorrência no Banco de Dados**  
Um banco H2/SQLite não suporta escrita concorrente em alta carga. Seria necessário migrar para PostgreSQL ou MySQL com connection pooling (HikariCP) para distribuir as conexões entre as requisições.

**(b) Latência de Autenticação**  
A validação de API Key em cada requisição implica um SELECT no banco. Seria necessário adicionar **cache distribuído (Redis)** para as chaves, diminuindo a latência de lookup de ms para µs.

**(c) Escalabilidade Horizontal**  
A aplicação monolítica precisaria ser replicada atrás de um **load balancer** (Nginx, HAProxy). Isso exige que o estado não fique armazenado na memória local da instância. O estado atual (contexto da API Key) está em ThreadLocal, o que é aceitável para uma única instância, mas requer redesenho para múltiplas instâncias.

**(d) Rate Limiting e Proteção**  
Sem controle de taxa, um único cliente poderia monopolizar recursos. Seria necessário implementar **rate limiting** via biblioteca como Resilience4j ou Spring Cloud Gateway, limitando requisições por API Key.

**(e) Observabilidade**  
Em escala, seria difícil debugar problemas sem instrumentação. Seria necessário adicionar **métricas (Micrometer)**, **rastreamento distribuído (OpenTelemetry)** e logs estruturados.

---

### Pergunta 2: Melhorias Futuras na Arquitetura

(a) **Substituir SQLite/H2 por PostgreSQL**  
Para suporte a transações ACID mais robustas, resiliência e escalabilidade em produção.

(b) **Cache Distribuído (Redis)**  
Para armazenar API Keys validadas e consultas frequentes de satélites, reduzindo latência.

(c) **Implementar Eventos Assíncronos**  
Ao invés de o cliente fazer polling em `/alerts`, emitir notificações via **webhooks** ou **message broker (Kafka/RabbitMQ)**. Quando um novo evento de colisão é criado, seria publicado em um tópico que subscribers consumem em tempo real.

(d) **Módulo de Cálculo Orbital Real**  
Consumir dados TLE (Two-Line Elements) reais de Space-Track.org e calcular probabilidades de colisão automaticamente, em vez de depender da entrada manual.

(e) **Autenticação OAuth2 / OIDC**  
Para cenários mais sofisticados de autenticação, integrando com provedores como Auth0 ou Keycloak.

(f) **Observabilidade**  
Adicionar métricas (Micrometer + Prometheus), rastreamento (OpenTelemetry + Jaeger) e dashboards (Grafana).

(g) **Versionamento de API**  
Usar versionamento de endpoint (`/api/v2`, `/api/v3`) para evolução sem quebrar clientes existentes.

---

### Pergunta 3: Evolução para Arquitetura Distribuída (Microsserviços)

A evolução natural seria decompor em microsserviços alinhados aos **bounded contexts**:

**(a) Satellite Service**  
- Gerencia cadastro, metadados e status dos satélites
- Endpoints: CRUD de satélites, busca, filtros
- Banco de dados: PostgreSQL dedicado
- Expõe eventos: `SatelliteCreated`, `SatelliteUpdated`, `SatelliteDeleted`

**(b) Collision Service**  
- Responsável pelo cálculo e armazenamento de eventos de colisão
- Potencialmente alimentado por um job externo que consome dados TLE e calcula probabilidades
- Endpoints: CRUD de eventos, listagem por satélite
- Banco de dados: PostgreSQL dedicado (otimizado para time-series)
- Expõe eventos: `CollisionEventCreated`, `CollisionResolved`

**(c) Subscription & Alert Service**  
- Gerencia assinaturas (quem segue qual satélite)
- Consome eventos de colisão e os distribui para subscribers
- Expõe eventos: `AlertGenerated`, `AlertDismissed`
- Suporta múltiplos canais: webhooks, email, SMS, SSE (Server-Sent Events)
- Banco de dados: PostgreSQL

**(d) API Gateway**  
- Ponto de entrada único para clientes
- Responsável por autenticação (API Key com cache distribuído)
- Rate limiting global
- Roteamento inteligente entre serviços
- Stack: Spring Cloud Gateway ou Kong

**(e) Message Broker (Event Bus)**  
- Kafka ou RabbitMQ para desacoplamento entre serviços
- Tópicos: `satellite-events`, `collision-events`, `alert-events`
- Garante entrega at-least-once e ordering por satélite

**(f) Padrão Database per Service**  
- Cada microsserviço tem seu próprio banco de dados
- Evita acoplamento via banco e permite evolução independente

**Fluxo de Colaboração**  
```
Cliente → API Gateway → (autenticação) → Satellite Service │ Collision Service
         (rate limiting)                    ↓                    ↓
                                       Message Broker (Kafka)
                                       Subscription Service ← Alertas → Cliente
                                       (webhooks/SSE)
```

---

## Debugging e Testes

### Testar Sem Autenticação (Health)
```bash
curl http://localhost:8080/health -i
```

### Testar com Autenticação Inválida
```bash
curl http://localhost:8080/api/v1/satellites \
  -H "X-API-Key: invalid_key" -i
```

### Ver Logs da Aplicação
A aplicação usa SLF4J. Logs são exibidos no console ao iniciar.

### Acessar H2 Console (Admin)
Se desejar inspecionar o banco de dados em tempo de execução:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:OrbitShield`
- User: `sa`
- Password: (deixar em branco)

---

## Deploy e CI/CD

### Build da Aplicação
```bash
./gradlew clean build
```
 
Gera um JAR executável em `build/libs/orbitshield-0.0.1-SNAPSHOT.jar`.
 
### Executar JAR
```bash
java -jar build/libs/orbitshield-0.0.1-SNAPSHOT.jar
```

---

## Estrutura de Diretórios

```
orbitshield/
├── src/
│   ├── main/
│   │   ├── java/com/gs/orbitshield/
│   │   │   ├── controller/          ← REST Controllers
│   │   │   ├── service/             ← Business logic
│   │   │   ├── repository/          ← Data access
│   │   │   ├── model/               ← Entities
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   └── response/
│   │   │   ├── exception/           ← Custom exceptions
│   │   │   ├── filter/              ← API Key auth filter
│   │   │   ├── context/             ← Thread-local context
│   │   │   ├── util/                ← Utilities
│   │   │   ├── config/              ← Configuration beans
│   │   │   └── OrbitShieldApplication.java
│   │   └── resources/
│   │       ├── application.yaml     ← Configuração Spring
│   │       └── data.sql             ← Seed data
│   └── test/
│       └── java/com/gs/orbitshield/
│           └── OrbitShieldApplicationTests.java
├── build.gradle                     ← Dependências Gradle
├── README.md                        ← Este arquivo
└── .gitignore
```

---

## Contato & Suporte

Para dúvidas ou sugestões, contacte a equipe de desenvolvimento.

---

## Licença

Este projeto é parte da Global Solution FIAP 2026 e está protegido pelos termos da instituição.

