# Install Kafka for the 1st time

 - Install `docker-compose`
 - `docker-compose -v` should return the version
 - Under the _**_Config_Kafka**_ folder, run `docker-compose up`
 - kafka will be created and started 

# Start Kafka after the 1st time
 - Under the **__Config_Kafka_** folder, run `docker-compose start`
 - kafka will be started. No new containers will be created this time.

# Exit
 - Make sure you stop everything after use it. `docker-compose stop`
 - Otherwise, next time you will not be able to start kafka.(solution is to start again, because kafka didn't disconnect from zookeeper correctly)