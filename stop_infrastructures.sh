#!/bin/bash
docker stop mysql_new mongodb_new
docker-compose stop
echo "Done"