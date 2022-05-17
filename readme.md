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
- the docker compose file **doc.yml** starts a mysql database and imports the sql dump. 
- If you want to run this skript, it might be necessary to replace the relative path to the directory the dump resides: **./init** with the respective absolute path to this file on your machine
- The script also builds and starts the spring boot container app. 
- In addition a phpmyadmin cotainer is added to allow the user to have an easier look at the database

**Note**

It was important to explicitly define a network in the docker compose file or otherwise the spring boot app couldn't connect to the database.

**Test the Application**

curl http://localhost:8084/eutravel/stationbyname?name=Kirchheim

```
curl -X POST http://localhost:8084/eutravel/connections \
   -H 'Content-Type: application/json' \
   -d '{
	"startStation":"Kirchheim (Teck)",
	"destinationStation":"Stuttgart Hbf",
	"travelStartTime":"12:00",
	"travelStartDate":"03/06/2022",
	"tariffClass":"1",	
	"numberOfTravellers":"1"
}'
```
***Selenium***
make sure to download geckodriver and add location to your PATH variable,
if you are using Linux for example like this:
```
'export PATH_$PATH:<path to gecko driver dir>' 
after editing reload bash profile
~/.bash_profile
test installation:
> geckodriver
```

**Navitia Token**
get the Navitia-Token from [Navitia](https://navitia.io/en/subscription/) for free and copy it to the application.properties file 
parameter is 'nativia.authtoken'


**GraalVM**

Install GraalVM on your local machine, forexample with sdkman
```
sdk install java 22.1.0.r11-grl
```
Use the it with this command:
```
sdk use java 22.1.0.r11-grl
```
Compile the application with the following command. Make sure you have cded in the directory where the pom resides. This my take a while 
```
mvn -Pnative -DskipTests package
```
and finally start it
```
./target/eutravelcenter2 
```
have fun
