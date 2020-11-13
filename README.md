# poc-fix project

This is a very simple POC with Quarkus and Quickfixj.

## Running it locally

To run this POC locally, follow the steps below:
1. Install JDK 11 or above;
2. Enter inside the `poc-fix-acceptor` folder and type:
```bash
./mvnw compile quarkus:dev -Ddebug=5006
```

4. Enter inside the `poc-fix-initiator` folder and type:
```bash
./mvnw compile quarkus:dev
```

## Swagger

### Initiator
```
localhost:8080/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters

- POST /new-order-single
  - Send and extremely simple NewOrderSingle message


### Acceptor
```
localhost:8090/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters

## Documentation

### Quickfixj documentation
- https://www.quickfixj.org/
- https://github.com/quickfix-j/quickfixj


### Quarkus
- https://quarkus.io/
