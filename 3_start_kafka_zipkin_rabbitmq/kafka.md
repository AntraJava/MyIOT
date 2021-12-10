# Install Kafka for the 1st time

 - Install `docker-compose`
 - `docker-compose -v` should return the version

# Start Kafka, RabbitMQ and Zipkin
 - First Build the rabbitMQ Image following [rabbitMQ.md](../_config_rabbitmq/rabbitmq.md)
 - Under the **project root** folder(where the docker-compose.yml file is), run `docker-compose up` or `docker-compose start`
 - kafka will be started. No new containers will be created this time.

# Exit
 - Make sure you stop everything after use it. `docker-compose stop`
 - Otherwise, next time you will not be able to start kafka.(solution is to start again, because kafka didn't disconnect from zookeeper correctly)