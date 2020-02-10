# JAVA WEB SERVER

http://ucd-server.herokuapp.com/

Projeto responsável por prover acesso a base de dados, assim como seus modelos e regras de negócio para as aplicações web e android.

Tecnologias utilizadas no projeto:

- Projeto Web Java
- Projeto Maven
- Spring Boot
- Spring MVC
- Spring REST
- Spring Data JPA
- Hibernate
- PostgreSQL
- Heroku PaaS

###### ENDPOINTS

|REQUEST|ENDPOINT                         |RETURN         |METHOD/PARAMETERS                                                                |
|-------|---------------------------------|---------------|---------------------------------------------------------------------------------|
|GET    |"/user"                          |List User      |fetchAll()                                                                       |
|GET    |"/user/{user}"                   |User           |fetchOne()                                                                       |
|POST   |"/user"                          |User           |insert(String email, String name, String password, Boolean inspector, Byte score)|
|PUT    |"/user/{user}"                   |User           |update(String email, String name, String password, Boolean inspector, Byte score)|
|DELETE |"/user/{user}"                   |Boolean        |delete()                                                                         |
|GET    |"/ranking"                       |List User      |ranking()                                                                        |
|GET    |"/login"                         |User           |login(String email, String password)                                             |
|GET    |"/complaint"                     |List Complaint |fetchAll()                                                                       |
|GET    |"/complaint{complaint}"          |Complaint      |fetchOne()                                                                       |
|POST   |"/complaint"                     |Complaint      |insert(String latitude, String longitude, String description, Integer idUser)    |
|PUT    |"/complaint/{complaint}"         |Complaint      |update(String description)                                                       |
|DELETE |"/complaint/{complaint}"         |Boolean        |delete()                                                                         |
|GET    |"/complaint/{complaint}/inspect" |Complaint      |inspect(Integer idInspector)                                                     |
|GET    |"/complaint/{complaint}/check"   |Complaint      |check()                                                                          |
|GET    |"/complaint/{complaint}/denounce"|Complaint      |denounce()                                                                       |
