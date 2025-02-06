# Setup Instructions for Receipt Processor

## Environment

- **Machine**: MacBook Pro M1
- **Java**: Java 8
- **Build Tool**: Maven
- **Containerization**: Docker

## 1. Build Docker Image

The jar file has already been created in the repository. To build the Docker image, simply execute the following command in your project folder:

```bash
docker build -t receipt-processor .

## 2. Run docker file:
```bash
docker run --rm -p 8080:8080 receipt-processor
