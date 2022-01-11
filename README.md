# Microservices

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148945164-26f259e4-6e6e-4830-8cc9-76dee52ffdfe.png" alt="Microservice-Architecture" width="100%"/>
</P>

<hr>

## Generate Maven Project

* mvn archetype:generate \
    -DgroupId=com.steinert-michael \
    -DartifactId=spring-microservices \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.4 \
    -DinteractiveMode=false

<hr>

## Starting Environment through Docker Compose
| Command                | Description              |
|------------------------|--------------------------|
| docker compose up -d   | Starts Environment       |
| docker compose ps      | Shows running Containers |
| docker compose down    | Stops running Containers |

<hr>

## Service Discovery
1) The Service Discovery Server allows Clients to register
2) Clients can look up for other Clients in the Service Discovery Server
3) Clients can now connect to other Clients by using the looked up Information from the Server Discovery Server

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148937888-e64a4751-0d32-47d1-b19d-ff8ef04a3d82.png" alt="Service Discovery" width="100%"/>
</P>

<hr>

## Message Queue

* A Messaging Broker receives Messages from Publishers / Producers and route them to Consumers 
* Since it is a Network Protocol, the Publisher, Consumer the Messaging Broker can all reside on different Machines
* A Message is removed from the Messaging Broker as the Consumer send an Acknowledgment

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148028761-9030bdc9-72df-466e-983c-a4e69ec413e3.png" alt="RabbitMQ" width="100%"/>
</P>

### RabbitMQ versus Kafka

* RabbitMQ is a solid, general-purpose Message Broker that supports several Protocols such as AMQP, MQTT, STOMP, etc.
* RabbitMQ can handle high Throughput
* A Use Case for RabbitMQ is to handle Background Jobs or long-running Task, such as File Scanning, Image Scaling or PDF Conversion
* RabbitMQ is also used between Microservices, where it serves as a Means of Communicating between Applications, avoiding Bottlenecks passing Messages

* Kafka is a Message Bus optimized for high-throughput Input and Output Data Streams
* Kafka is used when:
  * a large Amount of Data is moved,
  * Data is processed in Real-Time or
  * Data is analyzed over a Time Period
* Kafka is used where Data need to be collected, stored, and handled
* An Example is User Activity is tracked on a Web Shop and suggested Items to buy are generated
* Another example is Data Analysis for Tracking, Ingestion, Logging or Security

* Kafka is a durable Message Broker where Applications can process and re-process streamed Data on Disk
* Kafka has a very simple Routing Approach
* RabbitMQ has better Options to route Messages in complex Ways to the Consumers
* Kafka supports Batch Consumers that could be offline or Consumers that want Messages at low Latency

* RabbitMQ's Queues are fastest when they are empty, while Kafka retains large Amounts of Data with very little Overhead
* Kafka is designed for Holding and Distributing large Volumes of Messages

* Kafka is designed for horizontal Scaling (scale by adding more Compute Machines)
* RabbitMQ is designed for vertical Scaling (scale by adding more Compute Power)

### Consumers and Consumer Groups in Kafka

* Partitions allow to parallelize a Topic by Splitting the Data across multiple Nodes
* Each Record in a Partition is assigned and identified by its unique Offset
* This Offset points to the Record in a Partition
* Kafka maintains a numerical Offset for each Record in a Partition
* A __Consumer__ in Kafka can either automatically commit Offsets periodically, or he can choose to control this committed Position manually
* RabbitMQ will keep all States about consumed/acknowledged/unacknowledged Messages

<hr>

## JAR
* A JAR is a Package File Format that is used to aggregate Java Class Files and associated Metadata and Resources into on File for Distribution
* JAR Files are Archive Files that include a Java-specific Manifest File
* They are built on the ZIP Format and have a ".jar" File Extension

<hr>

## Kubernetes

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148969737-b8b35cf6-7698-4694-9678-723e3de3b050.png" alt="K8S Ecosystem" width="100%"/>
</P>

### Master Node

* The Master Node runs all Cluster's Control Plane Services
* The __Control Plane__ contains the following Components:
* __API Server__ allows to communicate all Components in the Control Plane with each other
* __Scheduler__ watches for new Workloads
* __Cluster Store__ contains all States of the Cluster
* __Controller Manager__ manages the Control Loop
* __Cloud Controller Manager__ communicates which the underlying Cloud Provider API

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148194096-da1d6543-c22f-4466-9c19-b198164a3aad.png" alt="Control Plane" width="100%"/>
</P>

#### API Server

* The API Server is the Front End to Kubernetes Control Plane with Authentication and Authorisation Checks
* All (external and internal) Communications go through the API Server
* The API Server exposes a Restful API on Port 443

#### Cluster Store

* The Cluster Store stores Configuration and State of the entire Cluster
* It is a distributed Key-Value Date Store that is the single Source of Truth

#### Scheduler

* The Scheduler watches for new Workloads and Pods to assign them to a Node based on several Scheduling Factors
* These Factors for the Nodes are:
* Is Node healthy?
* Has it enough Resources?
* Is its Port available?
* Affinity and Anti-Affinity Rules

#### Controller Manager

* The Controller Manager is a Daemon that manages the Control Loop
* It is a Controller of the following Controllers:
  * ReplicaSet Controller manages Release of new Application and guarantees Zero Downtime Deployments
  * Endpoint Controller
  * Namespace Controller
  * Service Account Controller allows Communication with Pods
* Each Controller watches the API Server for Changes that do not match the desired State with the current State

#### Cloud Controller Manager

* The Cloud Controller Manager interacts with the underlying Cloud Provider

### Worker Node

* A Worker Node is a Virtual Machine or Physical Machine that provides a running Environment for Applications
* In these running Environment runs Pods
* The Worker Node consists of the following Components:
  * __Kublet__ is an Agent that runs on every Node and interacts with the Container Runtime
  * __Container Runtime__ pulls Images from a Container Registry and manages them
  * __Kube Proxy__ is an Agent that runs on every Node and allows Pods to communicate with each other

<p align="center">
    <img src="https://user-images.githubusercontent.com/29623199/148217164-ce168a73-cd13-437c-8a0b-2ad493a2f5ac.png" alt="Worker Node" width="100%"/>
</P>

#### Kublet

* The Kublet is the Main Agent that runs on every Node
* It receives Pod Definitions from the API Server
* It interacts with the Container Runtime to run Containers associated eht the Pod
* It reports Node and Pod State to the Master Node through the API Server

#### Container Runtime

* The Container Runtime pulls Images from a Container Registry
* It starts and stops Containers of pulled Images
* It is responsible for running Containers and abstracts Container Management for Kubernetes

#### Kube Proxy
* Kube Proxy is an Agent that runs on every Node through DaemonSets
* It is responsible for local Cluster Networking that allows Pods to communicate with each other
* It provides each Node an own unique IP Address
* It routes Network Traffic to load balanced Services

### Pod
* A Pod is the smallest deployable Unit in Kubernetes
* It is a Collection of one or more Containers
* It can consist of the following Containers:
  * __Main Container__ is necessary for a Pod
  * __Init Container__ runs before the Main Container and initializes it
  * __Side Container__ runs parallel to the Main Container and supports it
  * __Volumes__ share Data between Containers
* Pods share Network and Volumes
* Pods are created by Controllers