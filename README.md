**Trading Service**
-
- Trading Service is a Restful Api to simulate and manage market data.
It's developed with Spring Boot framework on Java 8 and has external dependency 
to MySQL database.

- On repository level, Spring Data is used, which automatically provides CRUD methods
for a given entity.

- Controller level is kept as thin as possible. Controllers are intended to call services
(possibly multiple) and manage exception handling, response codes etc. 
Controllers deal with only DTOs (not entities).

- Service layer is where the business logic resides. 
Services take request DTOs, interact with repository and pass response DTOs to Controller.
Conversions from Entity to DTOs (vice versa) are performed at this level. 
ModelMapper library is mostly used for simplicity. Custom converters are used when necessary.

- Randomized market data is initialized on startup via reading from the configuration file.
If multiple nodes are started for backend, only the first node will initialize the data.

### Exception Handling:
Since Trading Service is mostly a CRUD application and business logic is not complex,
exceptions are mostly handled for request validation purposes.
Java Bean Validation is used to validate Request DTOs.
Validation Exceptions are then mapped to custom response messages on the global exception
handler via Spring @ControllerAdvice.

## Usage

### Build
The project can be built via maven, and the below command will check the project for code quality
and output the result _checkstyle.html_ file under target directory
### `mvn clean install`

### Run
To start the project and its dependencies go to folder that has docker-compose.yml and run:

### `docker-compose up`

**NOTE**: Docker compose might fail due to mysql not being ready before Spring Application.
In this case stopping docker compose and rerunning it will solve the problem.

Open [http://localhost:8080](http://localhost:8080) to view it in the browser.

### Login Credentials
Default username is "**_admin_**" and password is "**_password_**". 
These values can be changed in application.yml file.

### Assumptions

- There's only one user with for the application that has access to all pages and resources.
The user credentials are stored in the application.yml file. 
Hence there's no role based authorization for the scope of this project.

- The persisted trade information does not include the user who actually buys or sells assets.
This is omitted due to simplicity and having a single user, 
which is mainly used as an API key and API secret.

- Asset rates are only initialized at the beginning and assumed they do not change later.
Only the spread values can change.

- The balance for selling and buying assets are assumed as unlimited.