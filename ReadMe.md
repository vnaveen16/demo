# Spring Boot Device User Agent Example Project

This is a sample Java / Maven / Spring Boot (version 3.3.5) application that can be used as a starter for creating a spring boot appliction complete with built-in create, update, find devices. I hope it helps you.

## How to Run 

This application is packaged as a war which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 11 or above and Maven 3.8.1
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/demo-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8089 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```


### Get information about system health, configurations, etc.

```
http://localhost:8089/userAgent/createOrParse
http://localhost:8089/userAgent/detailsById
http://localhost:8089/userAgent/allDevicesByOs
http://localhost:8089/userAgent/deleteAllDevicesById
```

### Create or Update  a device resource

```
GET /userAgent/createOrParse
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 201 (Created)

```

### Retrieve a list of devices by os Name

```
GET /userAgent/allDevicesByOs
Accept: application/json
Content-Type: application/json
http://localhost:8089/userAgent/allDevicesByOs?osName=Windows

Response: HTTP 200
Content:  list of Devices 
```

### Delete a devices by deviceids resource

```
PUT /userAgent/deleteAllDevicesById
Accept: application/json
Content-Type: application/json
http://localhost:8089/userAgent/deleteAllDevicesById?ids=1,2,3


RESPONSE: HTTP 204 (No Content)
```
### To view Swagger 2 API docs
```
Run the server and browse to localhost:8089/swagger-ui/index.html
```


# Running the project with MySQL

This project uses an in-memory database so that you don't have to install a database in order to run it. However, converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB. 

Here is what you would do to back the services with MySQL, for example: 

### In pom.xml add: 

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

### Append this to the end of application.yml: 

```
---
spring.datasource.url=jdbc:mysql://localhost:3306/useragentdb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=admin
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Then run is using the 'mysql' profile:

```
        java -jar -Dspring.profiles.active=mysql target/demo-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=mysql"
```

# Attaching to the app remotely from your IDE

```
STS
import using exisiting maven project and then build the app and run as spring boot applicaiton.

IntelliJ Idea
open the project from the file menu and select the project folder and trust the project,open in new window and the right click spring boot main file and the run;
```
# Questions and Comments: vnaveen.0716@gmail.com


