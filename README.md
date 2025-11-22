# Relatório Final do Projeto – APISample

**Professor:** Luciano Rodrigo Ferretto
**Data de Entrega:** 22/11/2025

---

## 1. Nome do Aplicativo

**[TICKET PLUS]**

## 2. Logo do Aplicativo

**[ANEXAR LOGO/ÍCONE AQUI]**

## 3. Integrantes do Grupo

| Nome Completo | RA (Registro Acadêmico) | E-mail Institucional |
| :--- | :--- | :--- |
| [GABRIEL ANDRIOLLI] | [1136706] | [1136706@ATITUS.EDU.BR] |


## 4. Explicação do Tema do Aplicativo

O aplicativo desenvolvido, baseado na estrutura **TICKET PLUS**, tem como tema central a **Gestão de Eventos e Venda de Ingressos**.

A API atua como o *backend* de uma plataforma que permite a criação, gerenciamento e listagem de eventos, bem como a emissão e controle de ingressos e pedidos de compra. O sistema é projetado para ser a espinha dorsal de um serviço de bilheteria digital, suportando as operações CRUD (Create, Read, Update, Delete) para as entidades principais.

A escolha do tema se justifica pela sua **relevância prática** no contexto de aplicações modernas. O gerenciamento de eventos e transações financeiras (venda de ingressos) exige robustez, segurança e escalabilidade, fornecendo um excelente cenário para a aplicação de conceitos avançados de *backend* como autenticação via JWT, persistência de dados relacional e arquitetura de serviços RESTful.

## 5. Explicação das Funcionalidades do Aplicativo

O *backend* implementa um conjunto de funcionalidades essenciais para a gestão completa do ciclo de vida de eventos e ingressos. As principais funcionalidades são:

1.  **Autenticação e Autorização (Auth):** Permite que novos usuários se cadastrem (`/auth/signup`) e que usuários existentes obtenham um *token* de acesso JWT (`/auth/signin`) para interagir com as rotas protegidas da API.
2.  **Gestão de Usuários:** O sistema diferencia usuários comuns (`TypeUser.Common`) e, implicitamente, suporta a base para futuros papéis de administrador ou organizador de eventos.
3.  **Gestão de Eventos:** Permite a criação, atualização, listagem e exclusão de eventos. Inclui uma funcionalidade de busca específica por título do evento.
4.  **Gestão de Ingressos:** Permite o gerenciamento dos tipos de ingressos associados a um evento (e.g., preço, quantidade, tipo).
5.  **Gestão de Pedidos:** Funcionalidade central para registrar a compra de ingressos, associando-os a um comprador (`compradorId`) e permitindo a listagem de pedidos por comprador.

## 6. Descrição Técnica do Back-End do Aplicativo

### Tecnologias e Arquitetura

O *backend* foi desenvolvido utilizando a *stack* **Java** com o *framework* **Spring Boot** na versão `3.5.7`. A arquitetura segue o padrão **RESTful** para comunicação via HTTP e utiliza o paradigma de **Programação Orientada a Objetos (POO)**, com a separação clara de responsabilidades em camadas: *Controllers*, *Services* e *Repositories*.

| Componente | Tecnologia/Padrão | Versão/Detalhe |
| :--- | :--- | :--- |
| Linguagem | Java | 17 |
| Framework Principal | Spring Boot | 3.5.7 |
| Persistência | Spring Data JPA / Hibernate | Padrão Spring Boot |
| Banco de Dados | PostgreSQL | Conexão via JDBC |
| Segurança | Spring Security | Autenticação e Autorização |
| Autenticação | JSON Web Token (JWT) | `io.jsonwebtoken:jjwt-api:0.11.5` |
| Documentação | SpringDoc OpenAPI | `springdoc-openapi-starter-webmvc-ui:2.6.0` |

### Conexão com Banco de Dados

A conexão com o banco de dados **PostgreSQL** é gerenciada pelo Spring Data JPA. A configuração utiliza um banco de dados externo, conforme especificado no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://ep-icy-unit-ad3zwpme-pooler.c-2.us-east-1.aws.neon.tech/neondb?user=neondb_owner&password=npg_eRmHqXZu8jo9&sslmode=require&channelBinding=require
spring.jpa.hibernate.ddl-auto=update
```

O parâmetro `spring.jpa.hibernate.ddl-auto=update` indica que o Hibernate deve atualizar o esquema do banco de dados automaticamente com base nas entidades Java (`@Entity`), facilitando o desenvolvimento.

### Segurança Aplicada

A segurança é implementada através do **Spring Security** e do **JSON Web Token (JWT)**.

1.  **Autenticação:** O *endpoint* `/auth/signin` recebe as credenciais do usuário e, se válidas, gera um **JWT** assinado.
2.  **Autorização:** O *token* JWT é enviado em requisições subsequentes no cabeçalho `Authorization: Bearer <token>`. Um filtro (`JwtFilter.java`) intercepta essas requisições, valida a assinatura do *token* e extrai a identidade do usuário, garantindo que apenas usuários autenticados e autorizados acessem os recursos protegidos (Eventos, Ingressos, Pedidos).

### Principais Dependências Utilizadas

As dependências cruciais para a funcionalidade do projeto, listadas no `pom.xml`, incluem:

*   `spring-boot-starter-web`: Para construir aplicações web e APIs RESTful.
*   `spring-boot-starter-data-jpa`: Para persistência de dados e mapeamento objeto-relacional.
*   `postgresql`: Driver JDBC para conexão com o banco de dados PostgreSQL.
*   `spring-boot-starter-security`: Para controle de acesso e segurança.
*   `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Implementação do padrão JWT para autenticação *stateless*.
*   `springdoc-openapi-starter-webmvc-ui`: Para geração automática da documentação da API (Swagger UI).

### Documentação dos Endpoints

A seguir, a documentação detalhada dos principais *endpoints* da API:

#### **Endpoints de Autenticação (`/auth`)**

| Método HTTP | Caminho da URL | Descrição da Finalidade |
| :--- | :--- | :--- |
| `POST` | `/auth/signup` | Cria um novo usuário no sistema. |
| `POST` | `/auth/signin` | Autentica um usuário e retorna um JWT para acesso. |

**Exemplo de Requisição: Cadastro de Usuário (`/auth/signup`)**

```bash
curl --location 'https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Valdomiro Geremias Pinto da Silva",
"email": "pintodasilva@gmail.com",
"password": "pintinhoamarelinho"
}'
```

**Exemplo de Requisição: Login de Usuário (`/auth/signin`)**

```bash
curl --location 'https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "pintodasilva@gmail.com",
"password": "pintinhoamarelinho"
}'
```

#### **Endpoints de Eventos (`/eventos`)**

**Nota:** Todos os *endpoints* abaixo, exceto os de autenticação, requerem o cabeçalho `Authorization: Bearer <token>`.

| Método HTTP | Caminho da URL | Descrição da Finalidade |
| :--- | :--- | :--- |
| `POST` | `/eventos` | Cria um novo evento. |
| `GET` | `/eventos` | Lista todos os eventos cadastrados. |
| `GET` | `/eventos/{id}` | Busca um evento específico pelo seu ID. |
| `GET` | `/eventos/getByTitulo/{titulo}` | Busca um evento pelo seu título. |
| `PUT` | `/eventos/{id}` | Atualiza os dados de um evento existente. |
| `DELETE` | `/eventos/{id}` | Remove um evento do sistema. |

**Exemplo de Requisição: Criação de Evento (`/eventos`)**

```bash
curl --location 'https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/eventos' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Content-Type: application/json' \
--data-raw '{
"titulo": "Show da Banda XYZ",
"data": "2025-12-31T22:00:00",
"local": "Arena Multiuso"
}'
```

#### **Endpoints de Ingressos (`/ingressos`)**

| Método HTTP | Caminho da URL | Descrição da Finalidade |
| :--- | :--- | :--- |
| `POST` | `/ingressos` | Cria um novo tipo de ingresso para um evento. |
| `GET` | `/ingressos` | Lista todos os tipos de ingressos cadastrados. |
| `GET` | `/ingressos/{id}` | Busca um tipo de ingresso específico pelo seu ID. |
| `PUT` | `/ingressos/{id}` | Atualiza os dados de um tipo de ingresso. |
| `DELETE` | `/ingressos/{id}` | Remove um tipo de ingresso. |

**Exemplo de Requisição: Criação de Ingresso (`/ingressos`)**

```bash
curl --location 'https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/ingressos' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Content-Type: application/json' \
--data-raw '{
"eventoId": 1,
"tipo": "VIP",
"preco": 150.00,
"quantidadeDisponivel": 50
}'
```

#### **Endpoints de Pedidos (`/pedidos`)**

| Método HTTP | Caminho da URL | Descrição da Finalidade |
| :--- | :--- | :--- |
| `POST` | `/pedidos` | Cria um novo pedido de compra de ingressos. |
| `GET` | `/pedidos` | Lista todos os pedidos de compra. |
| `GET` | `/pedidos/{id}` | Busca um pedido específico pelo seu ID. |
| `GET` | `/pedidos/comprador/{compradorId}` | Lista todos os pedidos feitos por um comprador. |
| `PUT` | `/pedidos/{id}` | Atualiza os dados de um pedido. |
| `DELETE` | `/pedidos/{id}` | Remove um pedido. |

**Exemplo de Requisição: Criação de Pedido (`/pedidos`)**

```bash
curl --location 'https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/pedidos' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Content-Type: application/json' \
--data-raw '{
"compradorId": "UUID_DO_COMPRADOR",
"itens": [
{
"ingressoId": 1,
"quantidade": 2
}
]
}'
```

## 7. Descrição da Contribuição Individual

**[TRABALHO FEITO DE FORMA INDIVIDUAL]** |

## 8. Link do Repositório do Back-End

**[LINK DO REPOSITÓRIO GITHUB]**

## 9. Link da API em Produção (nuvem)

**[https://philosophical-martguerita-aaaaaasdasdas-da4b9204.koyeb.app/swagger-ui/index.html#/]**
