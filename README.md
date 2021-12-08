## Learning-ddd

This proyect follows the event driven architure and it has composed by two microservices and a library. Moreover, each microservice have created with the domain driven desing approach. 

Microservices:
1. **Input-data**, it has the responsibility of process data of the users.
2. **Dashboard**, it´s purpose is transforming the user-data and presenting the results.

Libraries:
1. **Shared**, It contains the common code for the microservices.

The purpose of this project is **improving technical and soft skills**:
1.  Inproving writting and reading english level.
2.  Practising with architectures like event driven and domain driven design.
3. Building this project let the opportuniy of playing with deploys in plataforms like AWS.
4. Enjoying with docker.
5. Increasing the understood of gradle.
6. Learning rabbitMq.

![image](https://user-images.githubusercontent.com/74345393/145246809-75644d9f-43d2-48b6-8186-d676d072b793.png)




## Deploy in local environment

Requeriments
* Docker --> https://www.docker.com/get-started
* Git --> https://git-scm.com/
* Gradle --> https://gradle.org/
* Open-jdk11 
    * https://wiki.openjdk.java.net/display/JDKUpdates/JDK11u
    * https://github.com/AdoptOpenJDK/openjdk11-upstream-binaries/releases/tag/jdk-11.0.12%2B7
 

Docker **general** environment **requeriments**:<br />
<br />
  **-Deploy rabbitMQ**:
1. Open cmd in the proyect 
2. Execute --> docker-compose up -d
3. RabbitMQ is running in --> http://localhost:8090/  --> user: rabbitmq, password: 2020


Docker **Input-data** microservice environment **requeriments**:<br />
<br />
  **-Deploy postgresql**
1. Open cmd in the proyect and go to input-data 
2. Execute docker-compose up -d
3. Postgresql is running in localhost:15432, user: postgres, password: 2020
4. Enter in database and execute the initional script, it is located in input-data/src/main/resources/db/migration

Docker **Dashboard** microservice environment **requeriments**:<br />
<br />
  **-Deploy postgresql**
1. Open cmd in the proyect and go to dashboard
2. Execute docker-compose up -d
3. Postgresql is running in localhost:15433, user: postgres, password: 2020
4. Enter in database and execute the initional script, it is located in dashboard/src/main/resources/db/migration

Deploying input-data microservice

* gradlew clean
* gradlew build
* gradlew bootRun

Running in localhost:8081

Deploying dashboard microservice

* gradlew clean
* gradlew build
* gradlew bootRun

Running in localhost:8082

