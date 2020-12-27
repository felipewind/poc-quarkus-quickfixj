#!/bin/bash

printf "== Executing Docker Compose with Build to start the application ==\n"
printf "\n"

printf "========== Building local Acceptor Project with local Maven ============\n"
printf "\n"
cd poc-fix-acceptor/
mvn package -Dquarkus.package.type=fast-jar
cd ..
printf "\n"
printf "============ Acceptor Project builded successfully by  Maven ===========\n"
printf "\n"

printf "========= Building local Initiator Project with local Maven ============\n"
printf "\n"
cd poc-fix-initiator/
mvn package -Dquarkus.package.type=fast-jar
cd ..
printf "\n"
printf "============ Initiator Project builded successfully by  Maven ==========\n"
printf "\n"

function ctrl_c() {
printf "\n"
printf '==========================================================================\n'
printf '============= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C ==================\n'
printf '==========================================================================\n'
printf "\n"
docker-compose -f ./docker-compose.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose.yml up --build

ctrl_c