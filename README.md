# Microservices

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/144274377-3f435482-5ca2-4788-8860-04b63e5e42c8.png" alt="Microservice-Architecture" width="100%"/>
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
