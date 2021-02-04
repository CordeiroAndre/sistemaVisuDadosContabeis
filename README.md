<h1 align="center">Sistema para a vizualização de dados contábeis</h1>
<p align="center">sistema criado para que o "cliente" possa disponibilizar dados publicamente no formato de um gráfico de linhas.</p>
<p>Esse sistema foi inteiramente desenvolvido com o springFramework, então para o iniciar, basta ir para o site do <a href="https://start.spring.io">Spring framework</a>, gerar um projeto vazio e adicionar/substituir os pacotes disponibilizados nesse repositório.</p>

### As dependencias utilizadas no projeto (parte de servidor) foram: 
- Spring Web: Fazer a parte de requisições HTTP 
- Spring Security: Realizar a parte de autenticação do usuario
- Spring Data JPA: Simplificar as interações com o banco de dados
- PostgreSQL driver: driver para conectar com o postgres
- Thymeleaf: template de visualização

### As dependencias utilizadas no projeto (parte cliente) são:
- Bootstrap: facilitar a criação de interfaces de usuário (Integrada via CDN, sem necessidade de instalar na maquina para testes).
- Chart.js: facilitar a criação criação do gráfico de linhas

### Para testes:
- Substitua seu arquivo pom.xml pelo arquivo pom.xml disponibilizado no repositório.
- Insira os diretórios configuration, controller, model, repository, service dentro do seu diretório raiz (Se o projeto foi gerado sem nenhuma alteração, deve ser: com.demo.demo).
- Insira no application.properties a url do seu banco de dados (PostgreSQL).
- Insira no application.properties o seu nome de usuário.
- Insira no application.properties a sua senha.

### Explicando os diretórios:
- Contoller: Se refere a toda a parte de comunicação de dados do cliente com o servidor, classe controller do MVC. 
- Configuration: Configuração do springSecurity (Quais paginas possuem acesso restrito e qual a senha e o login do "cliente").
- Model: Se refere a quais os dados vão ser persistidos no banco de dados, classe modelo do MVC.
- Repository: se refere a parte de queries (SQL) no banco de dados dentro do Java.
- Service: Se refere a parte de implementação das queries geradas pelo repository.
- Resources: se refere a tudo que deve ficar disponível (publicamente) ao cliente.
  - Templates: contém todas as classes HTML5 utilizadas nesse projeto.  


