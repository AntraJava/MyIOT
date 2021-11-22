# Installation
### DB
* MongoDB and MySQL should be installed using docker.
* Check [db.md](_config_databases/db.md) for detailed instructions

### Apache Kafka and RabbitMQ
* Apache Kafka and RabbitMQ should be installed using docker.
* Check [kafka.md](_config_kafka/kafka.md) for detailed instructions

### Start Script
* use [start_infrastructures.sh](start_infrastructures.sh) to start all db/message system in one command.
* Linux/Mac: `./start_infrastructures.sh`
* Windows: TODO

# Useful URLs
API Route Url : http://localhost:9090/actuator/gateway/routes
