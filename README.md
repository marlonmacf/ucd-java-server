#JAVA WEB SERVER

Projeto responsável por prover acesso a base de dados, assim como seus modelos e regras de negócio para as aplicações web e android.

Tecnologias utilizadas no projeto:

- Projeto Web Java
- Projeto Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Heroku PaaS

######ENDPOINTS

|REQUEST|ENDPOINT                |RETURN         |METHOD/PARAMETERS                                                                |
|-------|------------------------|---------------|---------------------------------------------------------------------------------|
|GET    |"/user"                 |List<User>     |fetch()                                                                          |
|POST   |"/user"                 |User           |insert(String email, String name, String password, Boolean inspector, Byte score)|
|PUT    |"/user/{user}"          |User           |update(String email, String name, String password, Boolean inspector, Byte score)|
|DELETE |"/user/{user}"          |User           |delete(Integer userId)                                                           |
|GET    |"/user/login"           |User           |login(String email, String password)                                             |
|GET    |"/user/ranking"         |List<User>     |ranking()                                                                        |
|GET    |"/complaint"            |List<Complaint>|fetch()                                                                          |
|POST   |"/complaint"            |Complaint      |insert(Complaint complaint)                                                      |
|PUT    |"/complaint/{complaint}"|Complaint      |update(Complaint complaint)                                                      |
|DELETE |"/complaint/{complaint}"|Complaint      |delete (Integer complaintId)                                                     |