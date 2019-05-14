# RESTful Web Service

Homework 4 involves creating a chess VAP through consuming a RESTful service and a containerization toolkit.

## OPEN CHESS ENGINE
The first task was obtaining a open engine legacy code base that plays the 
classic game of chess. 
The original code base can be found here:
https://github.com/AKBoles/Command-Line-Chess
The legacy code base is a project written in vanilla Java that allows two players
to play a local game of chess.

After downloading/cloning the repository, it can then be opened via IntelliJ. 
In order to run the program through IntelliJ, the following steps must take place:

1. File -> New -> Project From Existing Sources...
2. Click through the file directory: javaopenchess -> src -> main

The root directory will now be main. The program uses Apache Maven, which is a software
project management and comprehension tool. Maven is based on the concept of a project 
object model (POM) which can manage a project's build by reporting from a central piece 
of information.
Without a build kit like Maven, every developer that would want to run this program
would have to manually download every dependency (in our case jar files, since this is 
a Java project) and manually add it to the project. 
This would be a tedious process, especially considering
projects that depend on dozens of various dependencies. Instead, with Maven, 
in a unique file named POM.xml, all of the necessary information to run the project is 
included.
This includes meta data, plugins, dependencies, and builds. In turn, when the application 
is run,the dependencies will be injected into the project through a central Maven API.

3. Right click the main directory (main) and click "Add Framework Support..." and 
   then click Maven
4. A POM.xml file will be created in the root directory.
5. Delete the generated pom.xml file and replace it with the pom.xml file located at 
   /javaopenchess/pom.xml
5. The project can now be ran through JChessApp.java file. 

The Model View Controller architecture pattern seperates an application into three 
main components:
Model: Back-end logic
View: Front-end user interface
Controller: Interface connecting the model to the view

This architecture is recommended by various software engineers because it is extremely easy to
swap out any component. For example, if a developer wanted to create a checkers game, 
he/she could easily simply change the model and use the same view since 
checkers and chess have very similar boards.

## REST
REST stands for Representational State Transfer, which is an architectural style where
data and functionality are considered resources and are accessed using Uniform Resource 
Identifiers (URIs). These resources are acted upon through a set of defined operations. 
They are designed to be a stateless communication protocol using HTTP.
There are multiple RESTful commands, but the most common four are 
* GET: Read
* POST: Create 
* PUT: Update
* DELETE: Delete 

## SPRING BOOT
Spring Boot is a Java annotation based framework that makes it easy to create stand-alone, 
production grade Spring based applications that are easy to run. 
Spring Boot is a powerful and robust tool that removes a lot of the troubles 
a developer would otherwise have when trying to develop Java applications, like a RESTful 
web service. Spring Boot utilizes build tools like Maven and Gradle, but I used 
Maven in order to create this project. 

## CREATING RESTFUL WEB SERVICE 
The RESTful web service I created can be found at /src/main/java/hello
The most important part of any Spring Boot application is the main driver class.
This is a class annoated with @SpringBootApplication, which launches the application 
on startup. This annotation is the same as adding the annotations,
@Configuration and @EnableAutoConfiguration @EnableWebMvc @ComponentScan.
These annotations tag the class as a source for the application context,
adds the beans necessary for the application with the property settings,
allows Web MVC functionality, and to find other components and configurations. 
The driver for this class is named App.java.

In Spring, HTTP requests are handled by a controller, which is annotated with @RestController.
The controller for this service is called Controller.java. This class handles any and all
routing that must be defined when creating the web service. 

* All endpoints are defined using the @RequestMapping annotation.
* All parameters to the endpoints are defined using the @RequestParam annotation.
* All JSON body's to the endpoint are defined using the @RequestBody annotation.

### GET /status
A user hits the endpoint / with GET which means the user is requesting data from the database or server. Hitting this endpoint returns information about the game that corresponds to the given session name. 

An example request: `localhost:8080/status?sessionName=MarkGrechanik1`

The endpoint will search the database for a session whose name corresponds to the value 
stored in the sessionName parameter and return the current status of that game.

### POST /newGame
A user hits the endpoint / with POST which means the user is requesting to insert
new data into the database or server. 
Hitting this endpoint creates a game session with the given name 
if it does not already exists and makes the first move.

An example request: localhost:8080/newGame

```json
{
  "sessionName": "MarkGrechanik1",
  "playerColor": "white",
  "startSquare": "f2",
  "endSquare": "f4"
}
```

where session is the name of the session to be created, 
playerColor must be white since white always goes first in chess, 
startSquare corresponds to the algebraic notation location of the square to be moved,
and endSquare corresponds to the algebraic notation location of the square to be moved to.

The return value of this request is a JSON object containing a message indicating
if the session was able to be created and if the move was able to be made.

### PUT /move
A user hits the endpoint / with PUT which means the user is requesting to 
update already existing data into the database by making the move in chess.

The body should be in JSON format exactly in the form that matches /newGame, 
but the playerColor should be either "white" or "black" depending on whose turn it is.

The return value of this request is a JSON object similar to /newGame.

### Delete /quit
A user hits the endpoint / with DELETE which means the user is requesting to 
delete data from the database by finding a game session with the given 
name if it exists and deletes it.

The Request Body should be in JSON format in this form:

```json
{
    "sessionName": "MarkGrechanik1"
}
```

where sessionName corresponds to the name of the session to be deleted.

The beautiful part about Spring Boot is that we as developers need to simply define
a POJO that has member variables that match the keys of the JSON objects and it will
automatically parse the JSON into a POJO by instantiating the class constructor.

## CONSUMING WEB SERVICE
Once the web service is up and running via Spring Boot, it isn't hard to hit the endpoints 
corresponding to GET requests because those can be done in the browser since they only 
request data to be read. Anything actually changing data in the database needs a more
elegant approach.
Postman is a complete API development environment that integrates well with the 
software development cycle. It is available on Mac, Windows, and Linux. Using Postman 
allows you to hit the RESTful web services POST PUT and DELETE endpoints while 
providing the JSON bodies. 

## CONTAINERIZATION 
containerization is the method of machine virtualization that involves encapsulating
an application in a container with its own operating environment to deploy and run
distrubuted applications without launching an entire virtual machine for each application.

The containerization method used in this project is Docker. 
Docker can be downloaded here: https://www.docker.com/

The first part of creating the Docker Image is turning our Spring Boot application 
into a fat jar that can be deployed. 

Creating a fat jar In IntelliJ
Navigate to View -> Tool Window -> Maven 
Expand Lifecycle tab, and highlight 'clean' and 'install' and then click the green play button
In order to create a more readable jar file, I added this line of code to the pom.xml file
<finalName>ahmed_khan_hw4</finalName>
which will rename the fat jar to ahmed_khan_hw4.jar.

The next step is to create a Dockerfile in the root directory of the project. This file is
extremely important because it has the necessary configuration sources for building 
the docker image. 

Creating the Dockerfile
Right click the root directory and create a new file named exactly 'Dockerfile' as 
it is case-sensitive. 

The Dockerfile for the project can be found at /Dockerfile

FROM openjdk:8
The FROM keyword specifies what kind of application it is. This downloads the image 
from the docker hub which contains various images to import, similar to the Maven repository.
We are specifying to use the JDK 8 for our image. 

ADD target/ahmed_khan_hw4.jar ahmed_khan_hw4.jar
The ADD keyword adds the fat jar of our application into the docker container.
The first argument specifies the location of the fat jar, and
The second argument is specifed to be the root directory so it can run directly from there.

EXPOSE 8080
The EXPOSE keyword allows the container to be exposed to a specific port. 
Spring Boot applications run by default on port 8080, therfore for simplicitys sake 
we exposed the port 8080 to remove any confusion.

ENTRYPOINT ["java", "-jar", "ahmed_khan_hw4.jar"]
The ENTRYPOINT keyword specifies the commands with wich the docker container needs to run
the application.
We specify Java and -jar since we are using jar files for java, and then the jar file that
should be ran. 

In the terminal of the root directory, we can now build, run, and deploy our docker image.

We must build our container in order to create the image by running the command:
docker build -f Dockerfile -t hw4_docker_image . 
we are building using docker, the file is Dockerfile, and the tag for the image name is given
with which can be ran, and then where the file is present.

4 steps will then take place, which would be the 4 steps from the Dockerfile. 
Aftwerwards, to confirm that the image was created, use the command
docker images
and see if the name of the image we gave is visible. 

Now to run the image, we run the command
docker run -p 8080:8080 hw4_docker_image
which pushes the image onto exposed port 8080 on the container, 
and then 8080 for the Spring Boot application. 

## UPLOAD DOCKER IMAGE TO AWS 

First tag the image with my Docker Hub account:
docker tag ahmed_khan_hw4 akhan227/ahmed_khan_hw4

Then push the image to my Docker Hub account:
docker push akhan227/ahmed_khan_hw4

Provision an EC2 instance via the AWS console with default free tier settings
https://us-east-2.console.aws.amazon.com/ec2/v2/home?region=us-east-2#Home:

Select default security and the created SSH/HTTP/HTTPS security settings

Launch via a new keypair and download pem file

make the pem file protected via terminal command:
chmod 400 /location_of_file

Connect to the instance via terminal

update the instance with the latest updates
sudo yum update -y 

install docker to the instance
sudo yum install docker -y

start docker
sudo service docker start

run our application by mapping the 80 docker container port to 8080 for the EC2 instance
sudo docker run -p 80:8080 akhan227/ahmed_khan_hw4

obtain the endpoint access via the public DNS 

Link to video: https://youtu.be/3esMBjw1d4Q
