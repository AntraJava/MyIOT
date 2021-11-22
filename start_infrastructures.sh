#!/bin/bash
echo "Starting MySQL....."
docker start mysql_new
echo "Starting MongoDB..."
docker start mongodb_new
echo "Starting Kafka....."
cd ./_config_kafka || (echo "ERROR! Cannot find kafka config" && exit 1)
docker-compose start
echo "Starting Zipkin....."
docker start zipkin_new
echo "******************MySQL, MongoDB, Zipkin, Kafka and RabbitMQ Started*******************"
echo "Verify......."
#In case the computer is slow
sleep 3s
containers=$(docker ps --format '{{.Names}}')
if [[ $containers == *"mysql_new"* ]]; then
  echo "MySQL is running.."
  else echo "ERROR: MySQL is NOT running, exiting..." && exit 1
fi
if [[ $containers == *"mongodb_new"* ]]; then
  echo "MongoDB is running.."
  else echo "ERROR: MongoDB is NOT running, exiting..." && exit 1
fi
if [[ $containers == *"rabbitMQ_new"* ]]; then
  echo "RabbitMQ is running.."
  else echo "ERROR: RabbitMQ is NOT running, exiting..." && exit 1
fi
if [[ $containers == *"config_kafka_kafka_1"* ]]; then
  echo "Kafka is running.."
  else echo "ERROR: Kafka is NOT running, Try to run this script again, exiting..." && exit 1
fi
if [[ $containers == *"config_kafka_zookeeper_1"* ]]; then
  echo "Zookeeper is running.."
  else echo "ERROR: Zookeeper is NOT running, exiting..." && exit 1
fi
if [[ $containers == *"zipkin_new"* ]]; then
  echo "Zipkin is running.."
  else echo "WARN: Zipkin is NOT running, it is not required, ignore this error"
fi
echo "******************All Done, everything is up and running********************************"