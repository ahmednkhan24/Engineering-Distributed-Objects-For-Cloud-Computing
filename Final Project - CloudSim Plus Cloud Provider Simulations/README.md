# Cloud Provider Simulations

Names: Alexandru Guler, Ahmed Khan, Edgar Martinez, Hamza Shahid

NETID/UIN: aguler3 660631411, akhan227 652469935, emart9 673079134, hshahi2 654566018

## Execution:
The program can be ran by using the command
"sbt clean compile run"

Test cases are located under /src/test/scala/Tests.scala
Test cases can be ran using the command
"sbt clean compile test"

The application must be run using configuration files that specify various parameters
through the command line argument.
The file name should contain the .conf extension, the file should be located under
/src/main/resources/configuration.conf
and the file should contain all variables within a config {} object.

All dependencies are resolved using SBT via the build.sbt file.
Without a build tool like SBT, every developer that would want to run this program
would have to manually download every dependency and manually add it to the project.
This would be a tedious process, especially considering
projects that depend on dozens of various dependencies. Instead, with SBT,
in a unique file named build.sbt, all of the necessary information to run the project is
included.
This includes meta data, plugins, dependencies, and builds. In turn, when the application
is run, the dependencies will be injected into the project through a central Maven API.

A jar file can be created via running the command
"sbt clean compile assembly"
using the assembly sbt plugin located in the assembly.sbt file under
/project/assembly.sbt

The jar file will be located under
/target/scala-2.12/finalproject-assembly-0.1.jar
and can be ran using the command
"java -jar finalproject-assembly-0.1.jar"


## Project Evaluation:
This project consists of two Cloud Simulations which are done using the Cloudsim Plus framework. The main focus of the
simulations is to look at the impact various load balancing algorithms can have on datacenters. In the case of our
simulations we looked upon non-systematic load balancing algorithms like Random and Round-Robin. We also utilized
a cloudsim plus automation tool provided by a classmate on Piazza in order to evaluate various configurations
in human-readable format by simply loading the config files in a .yml file using the automation tool. The
tool can be found here: http://manoelcampos.com/cloudsim-plus-automation/
To further show the difference that a load balancer can have in a datacenter we kept all the datacenter
configurations/implementation the same by using the same configuration file for both simulations. In our case we
provided parameters for the cloudlets, vm, host, datacenter, and  other unique characteristics. Such configurations are as follows:

General:
  - 1 Datacenter Broker

DataCenter:
  - Linux OS
  - x86 Architecture
  - 10 Hosts
  - 4x(304,510) Mips from 4x(AMD Ryzen 7 1800x)
  - 64 GB ram
  - 10 TB Storage
  - 100 GB bandwidth

Cloudlet:
  - 150 Tasks/Workload
  - 100000 Length
  - 3000 Filesize
  - 3000 Outputsize
  - 1 Core/CPU
  - 90% Utilization

Virtual Machine:
  - 150 Machines
  - 10 GB Storage
  - 6 GB Ram

Results:
    DataCenter Configuration:
        Simulation 1: Random Load Balancing

            This simulation is written in scala and uses the configuration file with the configurations listed above
            and implemented with random load balancing. The random load balancing policy consists of the broker randomly
            distributing cloudlets to virtual machines to complete their workload. This policy doesn't take into
            consideration important aspects of the virtual machines like the amount of ram, storage, and computing
            power that's available. As such, we risk the potential for the broker to assign multiple cloudlets to a
            single virtual machine resulting cloudlets taking longer to process and complete their tasks. Moreover
            our simulation better showed this behavior when compared to other load balancing algorithms due to the
            longer amount of time elapsed by the simulation to complete all 150 cloudlet tasks. Reasons that we drew
            upon as to why this occurred is due to the fact that random allocation load balancing doesn't take advantage
            of a virtual machines configuration.

                Sample Output 1:

                                ================== Simulation finished at time 4.78 ==================

                                                         SIMULATION RESULTS

                Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
                      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
                -----------------------------------------------------------------------------------------------------
                       5|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      15|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      25|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      35|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      45|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      55|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      65|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      75|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      85|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                      95|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                     105|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                     115|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                     125|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                     135|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                     145|SUCCESS| 1|   0|       48| 5|        4|     109000|          1|        0|         5|       5
                       8|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      18|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      28|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      38|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      48|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      58|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      68|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      78|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      88|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                      98|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                     108|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                     118|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                     128|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                     138|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                     148|SUCCESS| 1|   0|       48| 8|        4|     109000|          1|        0|         5|       5
                       9|SUCCESS| 1|   0|       48| 9|        4|     109000|          1|        0|         5|       5
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .


        Simulation 2: Round Robin Load Balancing

            Our second simulation is also written in scala and uses the same configuration file as the above simulation,
            but this simulation is implemented with round robin load balancing. Round robin load balancing policy may
            be expressed as a load balancing algorithm in which the broker distributes cloudlets to the first virtual
            machine that's found in the datacenter, in such a way that the broker is cyclically assigning cloudlets to
            the first available virtual machine. Round robin, as like random load balancing, primarily doesn't take
            into consideration the virtual machines characteristics like ram, storage, and computing power which are
            available, but instead relies on the broker cyclically assigning cloudlets to untasked virtual machines.
            While round robin isn't the most elegant or best performing load balancing algorithm, in our simulation we
            noticed that its overall runtime was shorter when compared to our first simulation when completing the same
            150 cloudlet tasks. As to why this occurs we believe that its due to the limited amount of times the broker
            tries to assign a cloudlet to a virtual machine which already contains a cloudlet and also by not taking
            advantage of the virtual machines configuration.

                Sample Output 1:
                                ================== Simulation finished at time 4.39 ==================

                                                         SIMULATION RESULTS

                Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
                      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
                -----------------------------------------------------------------------------------------------------
                       0|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      10|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      20|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      30|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      40|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      50|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      60|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      70|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      80|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                      90|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                     100|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                     110|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                     120|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                     130|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                     140|SUCCESS| 1|   0|       48| 0|        4|     100000|          1|        0|         4|       5
                       1|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      11|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      21|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      31|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      41|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      51|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      61|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                      71|SUCCESS| 1|   0|       48| 1|        4|     100000|          1|        0|         4|       5
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .



            To further show the difference in the two load balancing algorithms we created a second configuration file
            with different characteristics and ran it against both simulation. Such configurations are as follows:

                General:
                  - 1 Datacenter Broker

                DataCenter:
                  - Linux OS
                  - x86 Architecture
                  - 16 Hosts
                  - 4x(263,080) Mips from 4x(Intel Core i5 8250U)
                  - 64 GB ram
                  - 10 TB Storage
                  - 100 GB bandwidth

                Cloudlet:
                  - 1500 Tasks/Workload
                  - 1000 Length
                  - 300 Filesize
                  - 300 Outputsize
                  - 1 Core/CPU

                Virtual Machine:
                  - 150 Machines
                  - 10 GB Storage
                  - 1 GB Ram

            By increasing the number of cloudlets in the simulation were were able to better notice the discrepancy
            that occurs for the overall runtime of the simulation. As using configuration file two that contained
            more cloudlet task and had some other minor changed gives us the following output:

                Simulation 1 - Output 2:

                            ================== Simulation finished at time 1044.97 ==================

                                                         SIMULATION RESULTS

                Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
                      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
                -----------------------------------------------------------------------------------------------------
                      13|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                      29|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                      45|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                      61|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                      77|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                      93|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     109|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     125|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     141|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     157|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     173|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     189|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     205|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     221|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     237|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     253|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     269|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     285|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     301|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     317|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     333|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     349|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     365|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     381|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     397|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     413|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     429|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     445|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                     461|SUCCESS| 1|   0|       48|13|        1|      10000|          1|        0|      1034|    1034
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .



                Simulation 2 - Output 2:

                            ================== Simulation finished at time 104.97 ==================

                                                         SIMULATION RESULTS

                Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
                      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
                -----------------------------------------------------------------------------------------------------
                      12|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                      28|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                      44|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                      60|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                      76|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                      92|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     108|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     124|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     140|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     156|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     172|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     188|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     204|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     220|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     236|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     252|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     268|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                     284|SUCCESS| 1|   0|       48|12|        1|       1000|          1|        0|       104|     104
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .
                       .   .      .    .         .  .         .         .             .         .          .        .



## CONTAINERIZATION
containerization is the method of machine virtualization that involves encapsulating
an application in a container with its own operating environment to deploy and run
distrubuted applications without launching an entire virtual machine for each application.

The containerization method used in this project is Docker.
Docker can be downloaded here: https://www.docker.com/

The first part of creating the Docker Image is turning our application into a fat jar that can be deployed.

Creating a fat jar by running the command
"sbt clean compile assembly"

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

ADD target/finalproject-assembly-0.1.jar finalproject-assembly-0.1.jar
The ADD keyword adds the fat jar of our application into the docker container.
The first argument specifies the location of the fat jar, and
The second argument is specified to be the root directory so it can run directly from there.

EXPOSE 8080
The EXPOSE keyword allows the container to be exposed to a specific port.

ENTRYPOINT ["java", "-jar", "finalproject-assembly-0.1.jar"]
The ENTRYPOINT keyword specifies the commands with which the docker container needs to run
the application.
We specify Java and -jar since we are using jar files for java, and then the jar file that
should be ran.

In the terminal of the root directory, we can now build, run, and deploy our docker image.

We must build our container in order to create the image by running the command:
docker build -f Dockerfile -t final-project-cs441-docker .
we are building using docker, the file is Dockerfile, and the tag for the image name is given
with which can be ran, and then where the file is present.

4 steps will then take place, which would be the 4 steps from the Dockerfile.
Aftwerwards, to confirm that the image was created, use the command
docker images
and see if the name of the image we gave is visible.

Now to run the image, we run the command
docker run -p 8080:8080 finalproject-assembly-0.1.jar
which pushes the image onto exposed port 8080 on the container.

Finally, to push the docker image to Docker Hub, login to your docker account by typing the command
"docker login"
and then login to your account.
Then push the image by typing the command
* `"docker push <account name>/final-project-cs441-docker"`

Our docker image of the final project can be found at this URL:
https://cloud.docker.com/repository/docker/akhan227/final-project-cs441-docker

You can run our docker image by typing the following command:
"docker run akhan227/final-project-cs441-docker"
