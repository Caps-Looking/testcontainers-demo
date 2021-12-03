# Testcontainers Demo App

A demo application to demonstrate how to use testcontainers.

## Running

To run locally, you need to have jdk 11, docker and docker compose installed. This command will build the project and run all tests.

```bash
./gradlew clean build
```

To run the API, you need to have postgreSQL installed and the credentials of `application.yml` must match your local credentials.

```bash
./gradlew bootRun
```