# Teste Backend - Api de tarefas

## Table of contents

- [Visão geral](#visão-geral)
- [A applicação](#a-aplicação)
    - [Setup](#setup)
    - [Endpoints](#endpoints) 
- [Meu desenvolvimento](#meu-desenvolvimento)
    - [Ferramentas utilizadas](#built-with)
    - [Recursos úteis](#recursos-úteis)
- [Autor](#autor)


## Visão geral

Essa aplicação foi construída com o simples propósito de ser uma api para
registrar tarefas.

## A aplicação

Esta aplicação Java RESTful API foi criada como resposta
ao teste proposto pela empresa SELAZ para a vaga de Desenvolvedor Backend
### Setup

Verifique se você tem instalado em sua máquina:

- Java versão 17 ou superior
- Apache Maven

Passos para executar a aplicação:

1. Clone este repositório em sua máquina
````
git clone https://github.com/FelipeDiasD/felipe-backend-java-spring-test.git
````
2. Na pasta raiz do projeto, abra um terminal de sua preferência e
execute o comando para fazer a build do projeto
````
mvn clean install -DskipTests
````
Caso opte por já executar os testes da aplicação 

````
mvn clean install
````
3. Com isso, para executar a aplicação, excecute o comando

````
mvn spring-boot:run
````
A aplicação deve ser executada e disponível localmente em:

- URL raiz: http://localhost:8080/

O swagger da aplicação fica disponível logo após sua execução em:

- http://localhost:8080/swagger-ui/index.html

Recomendo utilizar uma ferramenta de API client, como o Postman, 
para ter mais flexibilidade e também para testar a autenticação
via Bearer Token

Também é disponibilizado um banco de dados em memória para testes:

- http://localhost:8080/h2-console

Adicionalmente é também disponibilizado a ferramenta de cobertura
de testes Jacoco. Para acessar a ferramenta, vá na pasta raiz do projeto e
procure pela pasta target. Dentro da pasta busque pela pasta site e dentro
dela abra o arquivo index.html.

### Endpoints

Como indicado por esta sessão deste readme, para acessar alguns
endpoints é necessário estar autenticado ou ter um nível maior
de autoridade. Neste momento ainda estou trabalhando na autorização
em certos endpoints, mas a aplicação deve resgistrar e autenticar
usuários corretamente, gerando para os mesmos um token de acesso com duração
de 2 horas.

Registrar seu usuário
- URL: /register
- Method: POST
- Response: CODE 201
- Acessível a todos
- Request Body:
````
{
    "username": "paulo",
    "nivel": "ADMIN",
    "password": "12345"
}
````

Logar adiquirir token de autenticação
- URL: /login
- Method: POST
- Response: CODE 200
- Acessível a todos
- Request Body:
````
{
    "username": "paulo",
    "password": "12345"
}
````
- Response Body:
````
{
 "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InBhdWxvIiwiZXhwIjoxNzIzMjQyMTUwfQ.7km1T7sdPyKQ8VAFblbtwbAjuCdsqlmuLUzfrng0Wxo"
}
````

Retornar todos os usuários
- URL: /users
- Method: GET
- Response: CODE 200
- Auth Type: Bearer Token
- Autorizada apenas para usuários ADMIN
- Response Body:
````
[
    {
        "username": "pedro",
        "nivel": "USER",
        "password": "$2a$10$BRsOcw4aK7vh2tscNKbNU.MfxSU22BREdu2l3Qp1IgGVoIWpemdE6"
    },
    {
        "username": "paulo",
        "nivel": "ADMIN",
        "password": "$2a$10$VlvFuCCQYEJOvNysWCAh7u4SKiKgh/ddOLfkyZJmAdb.6W7LwJDKi"
    }
]
````

Criar um usuário
- URL: /users
- Method: POST
- Response: CODE 201 (No response body)
- Auth Type: Bearer Token
- Autorizada apenas para usuários ADMIN
- Request Body:
````
    {
        "username": "paulo",
        "nivel": "ADMIN",
        "password": "12345"
    }
````

Atualizar um usuário

- URL: /users/{id}
- Method: PUT
- Response: CODE 200
- Auth Type: Bearer Token
- Autorizada apenas para usuários ADMIN
- Request Body:
````
    {
        "username": "pauloNovoUsername",
        "password": "12345NovaSenha"
    }
````
- Response body:
````
   "UPDATED USER!"
````

Remover um usuário

- URL: /users/{id}
- Method: DELETE
- Response: CODE 200
- Auth Type: Bearer Token
- Autorizada apenas para usuários ADMIN
- Nota: Todas as tarefas do usuário também são deletadas
- Response Body:
````
"DELETED USER"
````
Retornar todas as tarefas

- URL: /tasks
- Method: GET
- Response: CODE 200
- Response body:
````
[
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-09T02:31:17.445Z",
    "dueDate": "2024-08-12T02:31:17.445Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "1234"
    }
  },
  
    {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T02:31:17.445Z",
    "dueDate": "2024-08-15T02:31:17.445Z",
    "status": "EM_ANDAMENTO",
    "user": {
      "id": 2,
      "username": "user2",
      "nivel": "USER",
      "password": "1234"
    }
  }
]
````
Criar uma nova tarefa

- URL: /tasks
- Method: POST
- Response: CODE 201 (No response body)
- Request Body:
````
{
    "title": "string",
    "description": "string",
    "dueDate": "2024-08-12T02:31:17.445Z",
    "user": {
      "id": 1,
    }
  }
````

Atualizar uma tarefa

- URL: /tasks/task/{id}
- Method: POST
- Response: CODE 200 
- Request Body:
````
{
    "title": "string",
    "description": "string",
    "dueDate": "2024-08-12T02:31:17.445Z",
    "status": "PENDENTE"
}
````
- Response Body
````
"UPDATED TASK"
````
Remover uma tarefa

- URL: /tasks/task/{id}
- Method: POST
- Response: CODE 200
- Response Body
````
"DELETED TASK"
````
Retorna as tarefas por status

- URL: /tasks?statusCode={statusCode}
- Method: GET
- Response: CODE 200
- Nota: Pendente(1) - Em andamento(2) - Concluída(3)
- Response Body

````
[
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-10T03:17:31.649Z",
    "dueDate": "2024-08-20T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T03:17:31.649Z",
    "dueDate": "2024-08-15T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  ]
````
Retornar as tarefas de um usuário

- URL: /tasks/{id}/tasks
- Method: GET
- Response: CODE 200
- Response Body

````
[
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-10T03:17:31.649Z",
    "dueDate": "2024-08-20T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T03:17:31.649Z",
    "dueDate": "2024-08-17T03:17:31.649Z",
    "status": "EM_ANDAMENTO",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  ]
````

Retorna as tarefas por status

- URL: /tasks/status?statusCode={statusCode}
- Method: GET
- Response: CODE 200
- Nota: Pendente(1) - Em andamento(2) - Concluída(3)
- Response Body
````
[
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-10T03:17:31.649Z",
    "dueDate": "2024-08-20T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T03:17:31.649Z",
    "dueDate": "2024-08-17T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 2,
      "username": "user2",
      "nivel": "USER",
      "password": "12345"
    }
  },
  ]
````
Retornar as tarefas ordenadas pela data de término
- URL: /tasks/sort?sort={sortMethod}
- Method: GET
- Response: CODE 200
- Nota: Só há um método de organização no momento - dueDate
- Response Body
````
[
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-10T03:17:31.649Z",
    "dueDate": "2024-08-15T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  },
  
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T03:17:31.649Z",
    "dueDate": "2024-08-17T03:17:31.649Z",
    "status": "PENDENTE",
    "user": {
      "id": 2,
      "username": "user2",
      "nivel": "USER",
      "password": "12345"
    }
  },
  
    
  {
    "title": "string",
    "description": "string",
    "createdAt": "2024-08-12T03:17:31.649Z",
    "dueDate": "2024-08-20T03:17:31.649Z",
    "status": "EM_ANDAMENTO",
    "user": {
      "id": 1,
      "username": "user1",
      "nivel": "USER",
      "password": "12345"
    }
  }
  ]
````

## Meu desenvolvimento
  
Desenvolvi a aplicação separada em 4 camadas principais

- Model: Onde as entidades e seus DTO's são modeladas em POJO
- Repository: Camada que interage e faz as operações diretmente com o banco de dados 
- Service: Camada que determina toda a lógica de negócio da aplicação
- Controller: Camada que expõe a aplicação através dos endpoints

Apliquei DTO's para as duas entidades que estão sendo utilizadas (User e Task),
para fazer uma separação melhor do código.

Nas classes de serviço utilizei algumas interfaces para definir os contratos
que as implementações das regras de negócio das entidades principais.



### Desenvolvido com

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Springdoc openAPI (Swagger)
- JUnit
- Mockito
- Jacoco


### Recursos úteis

- [Documentação do springboot](https://docs.spring.io/spring-boot/documentation.html)
- [Documentação do SpringDoc OpenAPI](https://springdoc.org/)


## Autor

- Website(Webflow) - [Felipe Dias](https://diass-blog.webflow.io)
- LinkedIn - [Felipe Dias](https://www.linkedin.com/in/felipe-dsprado/)

