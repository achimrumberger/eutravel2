# Eurotravelcenter for trains V2
It is planned to develop a single simple interface for major european train travel sites
Used libs and frameworks
- JSOUP 1.14.3
- Spring Boot 2.6.3
- Maven 3.8.4

European Station Data are comming from:
[stations](https://github.com/trainline-eu/stations) 
by [lucavezz](https://github.com/lucavezz)
and are now stored in a sql dump
for local use import the init.sql file into a mysql database
- mysql -u username -p database_name < data/init.sql

- build the spring boot application from the command line with:

- ***mvn clean package spring-boot:repackage -Dmaven.test.skip=true***

run the application from the command line with:

- ***mvn spring-boot:run***


docker stuff:

- there is a docker file for the spring boot app **eutravel2.dockerfile**
which basically just copies and runs the jar file to the docker container
- the docker compose file **doc.yml** starts a mysql database and imports the sql dump. If you want to run this skript on your machine point this directory to the place where the dump resides: **/home/achim/Dokumente/mysql/init**
- The script also builds and starts the spring boot container app. 
- In addition a phpmyadmin cotainer is added to allow you to have an easier look at the database

**Note**

It was important to explicitly define a network in the docker compose file or otherwise the spring boot app couldn't connect to the database.

