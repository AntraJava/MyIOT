# Install Kafka for the 1st time

 - Install `docker-compose`
 - `docker-compose -v` should return the version
 - Under the _Config_Kafka folder, run `docker-compose up`
 - kafka will be created and started 

# Start Kafka after the 1st time
 - Under the _Config_Kafka folder, run `docker-compose start`
 - kafka will be started. No new containers will be created this time.

# Reference
 - https://www.baeldung.com/ops/kafka-docker-setup