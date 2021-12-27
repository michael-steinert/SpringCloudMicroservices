# Microservices

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/146809794-b8801150-2ca8-441d-b62f-d7cb28012feb.png" alt="Microservice-Architecture" width="100%"/>
</P>

<hr>

## Generate Maven Project

* mvn archetype:generate \
    -DgroupId=com.steinert-michael \
    -DartifactId=spring-microservices \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.4 \
    -DinteractiveMode=false

## Starting Environment through Docker Compose
|Command|Description|
|---|---|
|docker compose up -d|Starts Environment|
|docker compose ps|Shows running Containers|
|docker compose down|Stops running Containers|
