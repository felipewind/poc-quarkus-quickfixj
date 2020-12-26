# poc-fix project

This is a very simple POC with [Quarkus](https://quarkus.io/) and [QuickfixJ](https://www.quickfixj.org/).

In the project, the `Acceptor` represents the stock exchange and the `Initiator` represents a broker.

They communicate each other with QuickfixJ.

## What can you do in this test

In this test the storage and logs are kept only in memory. 

QuickfixJ allows you to put storage and logs in files or databases.

So, to see the FIX messages exchanged in this test you must see the log console.

`Initiator` makes a login request to `Acceptor`.

They exchange heart beat messages.

The `Initiator` can send a New Single Order message.

The `Acceptor` automatically executes the orders and respond with an Exeution Report message.


## Acceptor

Executes automatically the orders it receives.

Listen on port 8090.

### Swagger

```
localhost:8090/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters


## Initiator

Allows you to insert one Order.

Listen on port 8080.

### Swagger

```
localhost:8080/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters

- POST /new-order-single
  - Send and extremely simple NewOrderSingle message


## Running it locally

### With docker-compose



### Without docker-compose

To run this POC locally, follow the steps below:
1. Enter inside the `poc-fix-acceptor` folder and type:
```bash
./mvnw compile quarkus:dev -Ddebug=5006
```

2. Enter inside the `poc-fix-initiator` folder and type:
```bash
./mvnw compile quarkus:dev
```






## Documentation

### Quickfixj documentation
- https://www.quickfixj.org/
- https://github.com/quickfix-j/quickfixj


### Quarkus
- https://quarkus.io/
