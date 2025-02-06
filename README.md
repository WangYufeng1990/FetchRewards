# Receipt Processor - Docker Setup Guide
## Environment
Machine: MacBook Pro M1

Java: Java 8 (Amazon Corretto version 1.8.0_422)

Build Tool: Maven

Containerization: Docker

## Prerequisites
### Docker:
Make sure Docker is installed and running on your machine.


## Steps to Build and Run the Application in Docker
### 1. Build Docker Image
The JAR file has already been created in the repository. To build the Docker image, follow these steps:

Open a terminal and navigate to your project folder.

Execute the following command to build the Docker image:

```bash
docker build -t receipt-processor .
```
This command will use the Dockerfile in the current directory to build the Docker image and tag it as receipt-processor.

### 2. Run Docker Container
Once the image is built, you can run the Docker container with the following command:

```bash
docker run --rm -p 8080:8080 receipt-processor
```
The --rm flag ensures that the container is automatically removed when it stops.

The -p 8080:8080 option maps port 8080 from the container to port 8080 on your local machine.

### 3. Accessing the Application
Once the container is running, you can access the endpoint via postman at:
POST http://localhost:8080/receipts/process
GET http://localhost:8080/receipts/{id}/points // The ID can be obtained from the POST response.
You should be able to interact with the application on the specified port.

## troubleshoot
If you got issue on pull jdk from docker during build docker image, you can change the jdk version in 
**Dockerfile** (first line) from *amazoncorretto:8-alpine-jdk* to **openjdk:8-alpine** 


