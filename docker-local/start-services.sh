#!/bin/sh
set -e

echo;echo "### Parando....";echo
docker-compose -f docker-compose-local.yml down
echo;echo "### STARTING....";echo
docker-compose -f docker-compose-local.yml up --build -d
docker cp replicaset.sh mongo.me1:/
docker exec mongo.me1 bash ./replicaset.sh
docker cp mongo_import.json mongo.me.authorization:/
docker cp mongo_import.sh mongo.me.authorization:/
docker exec mongo.me.authorization bash ./mongo_import.sh