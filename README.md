# poc-fix project

This is a very simple POC with Quarkus and Quickfixj.

## Running it locally

To run this POC locally, follow the steps below:
1. Install Maven;
2. Install JDK 11 or above;
3. Enter inside the `acceptor` folder and type:
```bash
mvn compile quarkus:dev -Ddebug=5006
```

4. Enter inside the `initiator` folder and type:
```bash
mvn compile quarkus:dev
```

## Rest API

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


### Quickfixj documentation
- https://www.quickfixj.org/
- https://github.com/quickfix-j/quickfixj


### Quarkus
- https://quarkus.io/
