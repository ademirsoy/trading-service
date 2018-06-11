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

#####Exception Handling:
Since Trading Service is mostly a CRUD application and business logic is not complex,
exceptions are mostly handled for request validation purposes.
Java Bean Validation is used to validate Request DTOs.
Validation Exceptions are then mapped to custom response messages on the global exception
handler via Spring @ControllerAdvice.

## Usage

####Build
The project can be built via maven, and the below command will check the project for code quality
and output the result _checkstyle.html_ file under target directory
### `mvn clean install`

####Run
To start the project and its dependencies go to folder that has docker-compose.yml and run:

### `docker-compose up`

Open [http://localhost:8080](http://localhost:8080) to view it in the browser.

####Login Credentials
Default username is "**_admin_**" and password is "**_password_**". 
These values can be changed in application.yml file.
 