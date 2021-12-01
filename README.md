## Learning-ddd

![image](https://user-images.githubusercontent.com/74345393/144260985-579bd77b-8957-41f7-a1da-e38cb3d9a2f1.png)




## Deploy in local environment

Requeriments
* Docker --> https://www.docker.com/get-started
* Open-jdk11 
    * https://wiki.openjdk.java.net/display/JDKUpdates/JDK11u
    * https://github.com/AdoptOpenJDK/openjdk11-upstream-binaries/releases/tag/jdk-11.0.12%2B7

Docker general environment requeriments
1. Deploy rabbitMQ
2. Open cmd in the proyect 
3. Execute --> docker-compose up -d
4. RabbitMQ is running in --> http://localhost:8090/  --> user: rabbitmq, password: 2020

Docker input-data microservice environment requeriments
1. Deploy postgresql
2. Open cmd in the proyect and go to input-data 
3. Execute docker-compose up -d
4. Postgresql is running in localhost:15432, user: postgres, password: 2020
5. Enter in database and execute the initional script, it is located in input-data/src/main/resources/db/migration

Docker dashboard microservice environment requeriments
1. Deploy postgresql
2. Open cmd in the proyect and go to dashboard
3. Execute docker-compose up -d
4. Postgresql is running in localhost:15433, user: postgres, password: 2020
5. Enter in database and execute the initional script, it is located in dashboard/src/main/resources/db/migration


Deploying input-data microservice

* gradle clean
* gradle build
* gradle bootRun

Running in localhost:8081

Deploying dashboard microservice

* gradle clean
* gradle build
* gradle bootRun

Running in localhost:8082

