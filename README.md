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

````
    {
        "username": "paulo",
        "nivel": "ADMIN",
        "password": "12345"
    }
````

## Meu desenvolvimento
  
  Desenvolvi a aplicação separada em 4 camadas principais

- Model
- Service
- Controller
- 
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

