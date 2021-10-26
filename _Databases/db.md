# 1. Docker Installation
* Make sure Docker is installed. Terminal run `docker info`.
* https://docs.docker.com/get-docker/

# 2. MongoDB 
* Open the terminal window.
* Start MongoDb using command:
`docker run -d -p 27017:27017 --name mongodb_new -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=12345678 mongo:4.0.16 --auth`

* An instance of Mongodb will be started, username admin password 12345678
* Login to mongo cli using: `docker exec -it mongodb_new mongo -u admin -p`
* Give password 12345678 then enter(terminal will not display the typing)
* Switch db:  `use iot` then enter
* Create user for iot collection
`db.createUser(
    {
        user: "iot_user",
        pwd: "aabbccdd",
        roles: [
           { role: "readWrite", db: "iot" }
        ]
    }
)`
* You should see "Successfully added user: {"user" : "iot_user","roles" : [{"role" : "readWrite","db" : "iot"}]}"
* Now we can use Spring or Robo3T to connect using iot_user/aabbccdd to iot db.

# MySQL 
* Open Terminal
* Install mysql
`docker run --name=mysql_new -d -p 3306:3306 mysql/mysql-server:8.0`
* Get password
`docker logs mysql_new 2>&1 | grep GENERATED` (copy the password)
* Login `docker exec -it mysql_new mysql -uroot -p` (when you paste it, it won't show in the scren)
* Now you are in the mysql cli like this:`mysql>`
* Change password: mysql> `ALTER USER 'root'@'localhost' IDENTIFIED BY 'aabbccdd';`
* Now we have a MySQL with root/aabbccdd

