# Teste Backend - Api de tarefas

Java RESTful API criada para o bootcamp Santander 2024 - Backend com Java

## Table of contents

- [Visão geral](#visão-geral)
- [A applicação](#a-aplicação)
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

### Endpoints

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
- 
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

## Meu desenvolvimento

### Desenvolvido com

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Springdoc openAPI (Swagger)
- JUnit
- Mockito


### Recursos úteis

- [Documentação do springboot](https://docs.spring.io/spring-boot/documentation.html)
- [Documentação do OpenAPI](https://springdoc.org/)
- [Documentação do Railway](https://docs.railway.app/)

## Autor

- Website(Webflow) - [Felipe Dias](https://diass-blog.webflow.io)
- LinkedIn - [Felipe Dias](https://www.linkedin.com/in/felipe-dsprado/)

