# poc-fix project

This is a very simple POC with [Quarkus](https://quarkus.io/) and [QuickfixJ](https://www.quickfixj.org/).

In this project, the `Acceptor` represents a `stock exchange` and the `Initiator` represents a `broker`.

They communicate each other using the [Finantial Information eXchange (FIX) protocol] (https://en.wikipedia.org/wiki/Financial_Information_eXchange) with QuickfixJ.



## What can you do in this test

In this test the storage is kept only in memory and the logs are printed on the console.

QuickfixJ allows you to put storage and logs in files or databases.

To see the FIX messages exchanged in this test you must look at the log console.

`Initiator` makes a login request to `Acceptor`.

They exchange heart beat messages.

The `Initiator` can send a New Single Order message using one endpoint with Swagger.

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

Inside the root folder of the project, execute:
```
chmod +x ./run.sh
./run.sh
```

### Without docker-compose

1. Enter inside the `poc-fix-acceptor` folder and type:
```
./mvnw compile quarkus:dev -Ddebug=5006
```

2. Enter inside the `poc-fix-initiator` folder and type:
```
./mvnw compile quarkus:dev
```


## Warning about the tag separator in the Linux console

The tag separator is the SOH (start of heading) character. Unfortunately, it does not appear on the Linux console.
```
Oct   Dec   Hex   Char                        Oct   Dec   Hex   Char
────────────────────────────────────────────────────────────────────────
001   1     01    SOH (start of heading)      101   65    41    A
```       

As a workaround, you can run the application and send the data to a file with the `>>` terminal operator, like:
```bash
./run.sh >> ~/my_log_file
```


## Documentation

### Quickfixj documentation
- https://www.quickfixj.org/
- https://github.com/quickfix-j/quickfixj

### FIX protocol
- https://en.wikipedia.org/wiki/Financial_Information_eXchange
- https://www.fixtrading.org/

### Quarkus
- https://quarkus.io/
