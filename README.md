
# Ecommerce Serratec Pub

Esta API é o **Projeto Final da disciplina API RESTful** do programa de Residência Full Stack do **Serratec** 2024.2. O objetivo é simular a API de um ecommerce.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Postman**
- **Swagger**
- **API Via Cep**

## 📋 Funcionalidades

- Cadastro de clientes, produtos, pedidos e itens.
- Busca de CEP utilizando a API Via Cep.
- Geração de relatório de itens pedidos.
- Consultas de clientes, produtos e itens pedidos.

## 🔗 Endpoints Principais

- `/clientes` - Cadastro e gerenciamento de clientes.
- `/produtos` - Cadastro e gerenciamento de produtos.
- `/pedidos` - Gerenciamento de pedidos e seus itens.
- `/itenspedidos` - Detalhes e consultas dos itens pedidos.

## 📦 Como rodar o projeto localmente

### Pré-requisitos

- Java 17 instalado
- Eclipse IDE
- PostgreSQL instalado e configurado
- Postman

### Passos

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/ecommerce-serratec-pub.git ecommerce-serratec-pub
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd ecommerce-serratec-pub
   ```

3. Instale as dependências:
   ```bash
   mvn install
   ```

4. Configure o banco de dados no arquivo `application.properties` com suas credenciais do PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

5. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

6. Acesse a documentação Swagger para testar os endpoints:
   ```
   http://localhost:8080/swagger-ui.html
   ```
   *importante verificar se a aplicação estará apta a rodar na porta 8080. Caso não aconteça, tente mudar para 8081, por exemplo.

## 🛠️ Estrutura do Projeto

- **src/main/java**: Contém a lógica da aplicação e a estrutura de pacotes.
- **src/main/resources**: Contém arquivos de configuração, como `application.properties`.

## 📝 Relatório de Pedidos

A API gera um relatório detalhado dos itens pedidos, que pode ser acessado por meio do endpoint `/itenspedidos`.

## ⚙️ Integração com API Via Cep

O projeto está integrado à API Via Cep para facilitar a busca de endereços a partir de um CEP informado. Para utilizar essa funcionalidade, basta acessar o endpoint `/cep` com o CEP desejado.

## 👥 Autores

- [Eduarda Goular](https://github.com/eduarda-goular)
- [Gustavo Santos](https://github.com/gustavo-c-s)
- [Lucas Gomes](https://github.com/lucauxs/)
- [Murilo Bongard](https://github.com/murilobongard)
- [Renata Rodrigues](https://github.com/itsrerodrigs)
- [Thiago Branco](https://github.com/thiagobranconf)

