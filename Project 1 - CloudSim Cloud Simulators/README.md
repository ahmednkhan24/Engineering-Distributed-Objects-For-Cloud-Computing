# Cloud Simulators
The application was developed within JetBrains IntelliJ IDEA Professional and must also be run within this IDE. 
The main source code can be found within the “src/src/main/scala/ahmed_khan_hw1.scala”.

In this homework I experimented creating cloud computing data centers and running jobs on them. I used a cloud simulator called CloudSim, which is a software package that models the cloud environments and operates different cloud models that we studied in the lectures. 

In order to load the CloudSim 3.0.3 jar and it’s one dependency, you must download the two jars and load them in IntelliJ by clicking file/project structure/dependencies. Then click the plus icon and the necessary jars. The CloudSim file can be found here https://code.google.com/archive/p/cloudsim/downloads
And it’s dependency can be found here http://commons.apache.org/proper/commons-math/download_math.cgi.

The application must be run using configuration files that specify various parameters through the command line argument. 
The file name should contain the .conf extension, and the structure should look like this:
config {
  num_user = Int
  dc {
    characteristics {
      arch = String
      os = String
      vmm = String
      time_zone = Double
      cost = Double
      cost_per_memory = Double
      cost_per_storage = Double
      cost_per_bandwidth = Double
    }

    dc_0 {
      core = Int
      mips = Int
      num_hosts = Int
      host {
        ram = Int
        storage = Int
        bandwidth = Int
      }
    }

    dc_1 {
      core = Int
      mips = Int
      num_hosts = Int
      host {
        ram = Int
        storage = Int
        bandwidth = Int
      }
    }
  }

  vm {
    num_vms = Int
    size = Int
    ram = Int
    mips = Int
    bw = Int
    num_cpu = Int
    vmm = String
  }

  cloudlet {
    num_cloudlets = Int
    length= Int
    filesize = Int
    outputsize = Int
    num_cpu = Int
  }
}

The main object should be named config, which then houses the number of users, the datacenters, the virtual machines, and the cloudlet specifications. 
The data center characteristics include the architecture, operating system, virtual machine manager, the time zone, and then the costs associated with the datacenter. 
Each data center is then listed, which includes its number of processing elements, mips, number of hosts, and the ram storage and bandwidth associated with the host. 
Next, the virtual machine characteristics are housed which includes the number of virtual machines, the size, the ram, mips, bandwidth, number of processing elements, and the virtual machine manager. 
Finally, the cloudlet description is shown which consists of the number of cloudlets, the length, the file size, the output size, and the number of processing elements. 
All configuration files should be located under src/main/resources. 

In order to run the program, the name of the configuration file must be passed. The application then reads from the configuration file and attempts to create the necessary entities to begin a simulation. 

The CloudSim library must first be initialized, and then the datacenters are created using the fields from the configuration files. Creating a data center involves creating its processing elements, the hosts involved, and creating a characteristic model for the data center.

Next, the broker is created by using the submitted virtual machines and cloudlets according to the specific rules of the simulation. 
The virtual machines are then created based on the description found in the configuration file, and then finally the cloudlets are created.

A simulation is finally able to begin, process, and then finish. The final results include the status of each cloudlet, the data center and virtual machines it ran on, and the allotted time used to complete. 

The model I chose to simulate based on the lectures was the Software as a Service model. 
I just this model because the underlying cloud infrastructure is invisible to customers. 
This is also commonly known as “Web Services.” I chose to use SaaS for many reasons, 
but the main benefits include a very modest tool footprint, efficient use of s
oftware licenses, and savings in up-front costs to clients. 
Of course, there are some limitations and concerns when it comes to software as a service. 
Some of these limitations include network dependence and a lack of portability between clouds. 

A simulation was ran simulating the Intel Core i3. 
A simulation running quad core cpu with 1000 virtual machines and 10000 cloudlets using 
2 gb of ram was tested.
The results are consistent with the Software as a Service model that was implemented. 
In SaaS model, the cloudlet represents basic computation, which is why I was able to 
employ many cloudlets to the same virtual machine. This simulation was deemed successful
and would allow my business to thrive in a SaaS scenario.
Running the same algorithm on a PaaS, IaaS, or FaaS would significantly change 
the output values and time taken, since those stacks employ different allocation mechanisms. 
For example, the IaaS model uses one virtual machine per processing element, 
which would make this simulation fail due to the lower amount of virtual machines. 

Cloudlet ID    STATUS    Data center ID    VM ID    Time    Start Time    Finish Time
    4        SUCCESS           2            4        832.17        0.2        832.37
    16        SUCCESS          2            4        832.17        0.2        832.37
    28        SUCCESS          2            4        832.17        0.2        832.37
    40        SUCCESS          2            4        832.17        0.2        832.37
    52        SUCCESS          2            4        832.17        0.2        832.37
    64        SUCCESS          2            4        832.17        0.2        832.37
    76        SUCCESS          2            4        832.17        0.2        832.37
    88        SUCCESS          2            4        832.17        0.2        832.37
    100        SUCCESS         2            4        832.17        0.2        832.37
    112        SUCCESS         2            4        832.17        0.2        832.37
    124        SUCCESS         2            4        832.17        0.2        832.37
    136        SUCCESS         2            4        832.17        0.2        832.37
    148        SUCCESS         2            4        832.17        0.2        832.37
    160        SUCCESS         2            4        832.17        0.2        832.37
    172        SUCCESS         2            4        832.17        0.2        832.37
    184        SUCCESS         2            4        832.17        0.2        832.37
    196        SUCCESS         2            4        832.17        0.2        832.37
    208        SUCCESS         2            4        832.17        0.2        832.37
    220        SUCCESS         2            4        832.17        0.2        832.37
    232        SUCCESS         2            4        832.17        0.2        832.37
    244        SUCCESS         2            4        832.17        0.2        832.37
    256        SUCCESS         2            4        832.17        0.2        832.37
    268        SUCCESS         2            4        832.17        0.2        832.37
    280        SUCCESS         2            4        832.17        0.2        832.37
    292        SUCCESS         2            4        832.17        0.2        832.37
    304        SUCCESS         2            4        832.17        0.2        832.37
    316        SUCCESS         2            4        832.17        0.2        832.37
    328        SUCCESS         2            4        832.17        0.2        832.37
    340        SUCCESS         2            4        832.17        0.2        832.37
    352        SUCCESS         2            4        832.17        0.2        832.37
    364        SUCCESS         2            4        832.17        0.2        832.37
    376        SUCCESS         2            4        832.17        0.2        832.37
    388        SUCCESS         2            4        832.17        0.2        832.37
    400        SUCCESS         2            4        832.17        0.2        832.37
    412        SUCCESS         2            4        832.17        0.2        832.37
    424        SUCCESS         2            4        832.17        0.2        832.37
    436        SUCCESS         2            4        832.17        0.2        832.37
    448        SUCCESS         2            4        832.17        0.2        832.37
    460        SUCCESS         2            4        832.17        0.2        832.37
    472        SUCCESS         2            4        832.17        0.2        832.37
    484        SUCCESS         2            4        832.17        0.2        832.37
    496        SUCCESS         2            4        832.17        0.2        832.37
    508        SUCCESS         2            4        832.17        0.2        832.37
    520        SUCCESS         2            4        832.17        0.2        832.37
    532        SUCCESS         2            4        832.17        0.2        832.37
    544        SUCCESS         2            4        832.17        0.2        832.37
    556        SUCCESS         2            4        832.17        0.2        832.37
    568        SUCCESS         2            4        832.17        0.2        832.37
    580        SUCCESS         2            4        832.17        0.2        832.37
    592        SUCCESS         2            4        832.17        0.2        832.37
    604        SUCCESS         2            4        832.17        0.2        832.37
    616        SUCCESS         2            4        832.17        0.2        832.37
    628        SUCCESS         2            4        832.17        0.2        832.37
    640        SUCCESS         2            4        832.17        0.2        832.37
    652        SUCCESS         2            4        832.17        0.2        832.37
    664        SUCCESS         2            4        832.17        0.2        832.37
    676        SUCCESS         2            4        832.17        0.2        832.37
    688        SUCCESS         2            4        832.17        0.2        832.37
    700        SUCCESS         2            4        832.17        0.2        832.37
    712        SUCCESS         2            4        832.17        0.2        832.37
    724        SUCCESS         2            4        832.17        0.2        832.37
    736        SUCCESS         2            4        832.17        0.2        832.37
    748        SUCCESS         2            4        832.17        0.2        832.37
    760        SUCCESS         2            4        832.17        0.2        832.37
    772        SUCCESS         2            4        832.17        0.2        832.37
    784        SUCCESS         2            4        832.17        0.2        832.37
    796        SUCCESS         2            4        832.17        0.2        832.37
    808        SUCCESS         2            4        832.17        0.2        832.37
    820        SUCCESS         2            4        832.17        0.2        832.37
    832        SUCCESS         2            4        832.17        0.2        832.37
    844        SUCCESS         2            4        832.17        0.2        832.37
    856        SUCCESS         2            4        832.17        0.2        832.37
    868        SUCCESS         2            4        832.17        0.2        832.37
    880        SUCCESS         2            4        832.17        0.2        832.37
    892        SUCCESS         2            4        832.17        0.2        832.37
    904        SUCCESS         2            4        832.17        0.2        832.37
    916        SUCCESS         2            4        832.17        0.2        832.37
    928        SUCCESS         2            4        832.17        0.2        832.37
    940        SUCCESS         2            4        832.17        0.2        832.37
    952        SUCCESS         2            4        832.17        0.2        832.37
    964        SUCCESS         2            4        832.17        0.2        832.37
    976        SUCCESS         2            4        832.17        0.2        832.37
    988        SUCCESS         2            4        832.17        0.2        832.37
    1000        SUCCESS        2            4        832.17        0.2        832.37
    1012        SUCCESS        2            4        832.17        0.2        832.37
    1024        SUCCESS        2            4        832.17        0.2        832.37
    1036        SUCCESS        2            4        832.17        0.2        832.37
    1048        SUCCESS        2            4        832.17        0.2        832.37
    1060        SUCCESS        2            4        832.17        0.2        832.37
    1072        SUCCESS        2            4        832.17        0.2        832.37
    1084        SUCCESS        2            4        832.17        0.2        832.37
    1096        SUCCESS        2            4        832.17        0.2        832.37
    1108        SUCCESS        2            4        832.17        0.2        832.37
    1120        SUCCESS        2            4        832.17        0.2        832.37
    1132        SUCCESS        2            4        832.17        0.2        832.37
    1144        SUCCESS        2            4        832.17        0.2        832.37
    1156        SUCCESS        2            4        832.17        0.2        832.37
    1168        SUCCESS        2            4        832.17        0.2        832.37
    1180        SUCCESS        2            4        832.17        0.2        832.37
    1192        SUCCESS        2            4        832.17        0.2        832.37
    1204        SUCCESS        2            4        832.17        0.2        832.37
    1216        SUCCESS        2            4        832.17        0.2        832.37
    1228        SUCCESS        2            4        832.17        0.2        832.37
    1240        SUCCESS        2            4        832.17        0.2        832.37
    1252        SUCCESS        2            4        832.17        0.2        832.37
    1264        SUCCESS        2            4        832.17        0.2        832.37
    1276        SUCCESS        2            4        832.17        0.2        832.37
    1288        SUCCESS        2            4        832.17        0.2        832.37
    1300        SUCCESS        2            4        832.17        0.2        832.37
    1312        SUCCESS        2            4        832.17        0.2        832.37
    1324        SUCCESS        2            4        832.17        0.2        832.37
    1336        SUCCESS        2            4        832.17        0.2        832.37
    1348        SUCCESS        2            4        832.17        0.2        832.37
    1360        SUCCESS        2            4        832.17        0.2        832.37
    1372        SUCCESS        2            4        832.17        0.2        832.37
    1384        SUCCESS        2            4        832.17        0.2        832.37
    1396        SUCCESS        2            4        832.17        0.2        832.37
    1408        SUCCESS        2            4        832.17        0.2        832.37
    1420        SUCCESS        2            4        832.17        0.2        832.37
    1432        SUCCESS        2            4        832.17        0.2        832.37
    1444        SUCCESS        2            4        832.17        0.2        832.37
    1456        SUCCESS        2            4        832.17        0.2        832.37
    1468        SUCCESS        2            4        832.17        0.2        832.37
    1480        SUCCESS        2            4        832.17        0.2        832.37
    1492        SUCCESS        2            4        832.17        0.2        832.37
    1504        SUCCESS        2            4        832.17        0.2        832.37
    1516        SUCCESS        2            4        832.17        0.2        832.37
    1528        SUCCESS        2            4        832.17        0.2        832.37
    1540        SUCCESS        2            4        832.17        0.2        832.37
    1552        SUCCESS        2            4        832.17        0.2        832.37
    1564        SUCCESS        2            4        832.17        0.2        832.37
    1576        SUCCESS        2            4        832.17        0.2        832.37
    1588        SUCCESS        2            4        832.17        0.2        832.37
    1600        SUCCESS        2            4        832.17        0.2        832.37
    1612        SUCCESS        2            4        832.17        0.2        832.37
    1624        SUCCESS        2            4        832.17        0.2        832.37
    1636        SUCCESS        2            4        832.17        0.2        832.37
    1648        SUCCESS        2            4        832.17        0.2        832.37
    1660        SUCCESS        2            4        832.17        0.2        832.37
    1672        SUCCESS        2            4        832.17        0.2        832.37
    1684        SUCCESS        2            4        832.17        0.2        832.37
    1696        SUCCESS        2            4        832.17        0.2        832.37
    1708        SUCCESS        2            4        832.17        0.2        832.37
    1720        SUCCESS        2            4        832.17        0.2        832.37
    1732        SUCCESS        2            4        832.17        0.2        832.37
    1744        SUCCESS        2            4        832.17        0.2        832.37
    1756        SUCCESS        2            4        832.17        0.2        832.37
    1768        SUCCESS        2            4        832.17        0.2        832.37
    1780        SUCCESS        2            4        832.17        0.2        832.37
    1792        SUCCESS        2            4        832.17        0.2        832.37
    1804        SUCCESS        2            4        832.17        0.2        832.37
    1816        SUCCESS        2            4        832.17        0.2        832.37
    1828        SUCCESS        2            4        832.17        0.2        832.37
    1840        SUCCESS        2            4        832.17        0.2        832.37
    1852        SUCCESS        2            4        832.17        0.2        832.37
    1864        SUCCESS        2            4        832.17        0.2        832.37
    1876        SUCCESS        2            4        832.17        0.2        832.37
    1888        SUCCESS        2            4        832.17        0.2        832.37
    1900        SUCCESS        2            4        832.17        0.2        832.37
    1912        SUCCESS        2            4        832.17        0.2        832.37
    1924        SUCCESS        2            4        832.17        0.2        832.37
    1936        SUCCESS        2            4        832.17        0.2        832.37
    1948        SUCCESS        2            4        832.17        0.2        832.37
    1960        SUCCESS        2            4        832.17        0.2        832.37
    1972        SUCCESS        2            4        832.17        0.2        832.37
    1984        SUCCESS        2            4        832.17        0.2        832.37
    1996        SUCCESS        2            4        832.17        0.2        832.37
    2008        SUCCESS        2            4        832.17        0.2        832.37
    2020        SUCCESS        2            4        832.17        0.2        832.37
    2032        SUCCESS        2            4        832.17        0.2        832.37
    2044        SUCCESS        2            4        832.17        0.2        832.37
    2056        SUCCESS        2            4        832.17        0.2        832.37
    2068        SUCCESS        2            4        832.17        0.2        832.37
    2080        SUCCESS        2            4        832.17        0.2        832.37
    2092        SUCCESS        2            4        832.17        0.2        832.37
    2104        SUCCESS        2            4        832.17        0.2        832.37
    2116        SUCCESS        2            4        832.17        0.2        832.37
    2128        SUCCESS        2            4        832.17        0.2        832.37
    2140        SUCCESS        2            4        832.17        0.2        832.37
    2152        SUCCESS        2            4        832.17        0.2        832.37
    2164        SUCCESS        2            4        832.17        0.2        832.37
    2176        SUCCESS        2            4        832.17        0.2        832.37
    2188        SUCCESS        2            4        832.17        0.2        832.37
    2200        SUCCESS        2            4        832.17        0.2        832.37
    2212        SUCCESS        2            4        832.17        0.2        832.37
    2224        SUCCESS        2            4        832.17        0.2        832.37
    2236        SUCCESS        2            4        832.17        0.2        832.37
    2248        SUCCESS        2            4        832.17        0.2        832.37
    2260        SUCCESS        2            4        832.17        0.2        832.37
    2272        SUCCESS        2            4        832.17        0.2        832.37
    2284        SUCCESS        2            4        832.17        0.2        832.37
    2296        SUCCESS        2            4        832.17        0.2        832.37
    2308        SUCCESS        2            4        832.17        0.2        832.37
    2320        SUCCESS        2            4        832.17        0.2        832.37
    2332        SUCCESS        2            4        832.17        0.2        832.37
    2344        SUCCESS        2            4        832.17        0.2        832.37
    2356        SUCCESS        2            4        832.17        0.2        832.37
    2368        SUCCESS        2            4        832.17        0.2        832.37
    2380        SUCCESS        2            4        832.17        0.2        832.37
    2392        SUCCESS        2            4        832.17        0.2        832.37
    2404        SUCCESS        2            4        832.17        0.2        832.37
    2416        SUCCESS        2            4        832.17        0.2        832.37
    2428        SUCCESS        2            4        832.17        0.2        832.37
    2440        SUCCESS        2            4        832.17        0.2        832.37
    2452        SUCCESS        2            4        832.17        0.2        832.37
    2464        SUCCESS        2            4        832.17        0.2        832.37
    2476        SUCCESS        2            4        832.17        0.2        832.37
    2488        SUCCESS        2            4        832.17        0.2        832.37
    2500        SUCCESS        2            4        832.17        0.2        832.37
    2512        SUCCESS        2            4        832.17        0.2        832.37
    2524        SUCCESS        2            4        832.17        0.2        832.37
    2536        SUCCESS        2            4        832.17        0.2        832.37
    2548        SUCCESS        2            4        832.17        0.2        832.37
    2560        SUCCESS        2            4        832.17        0.2        832.37
    2572        SUCCESS        2            4        832.17        0.2        832.37
    2584        SUCCESS        2            4        832.17        0.2        832.37
    2596        SUCCESS        2            4        832.17        0.2        832.37
    2608        SUCCESS        2            4        832.17        0.2        832.37
    2620        SUCCESS        2            4        832.17        0.2        832.37
    2632        SUCCESS        2            4        832.17        0.2        832.37
    2644        SUCCESS        2            4        832.17        0.2        832.37
    2656        SUCCESS        2            4        832.17        0.2        832.37
    2668        SUCCESS        2            4        832.17        0.2        832.37
    2680        SUCCESS        2            4        832.17        0.2        832.37
    2692        SUCCESS        2            4        832.17        0.2        832.37
    2704        SUCCESS        2            4        832.17        0.2        832.37
    2716        SUCCESS        2            4        832.17        0.2        832.37
    2728        SUCCESS        2            4        832.17        0.2        832.37
    2740        SUCCESS        2            4        832.17        0.2        832.37
    2752        SUCCESS        2            4        832.17        0.2        832.37
    2764        SUCCESS        2            4        832.17        0.2        832.37
    2776        SUCCESS        2            4        832.17        0.2        832.37
    2788        SUCCESS        2            4        832.17        0.2        832.37
    2800        SUCCESS        2            4        832.17        0.2        832.37
    2812        SUCCESS        2            4        832.17        0.2        832.37
    2824        SUCCESS        2            4        832.17        0.2        832.37
    2836        SUCCESS        2            4        832.17        0.2        832.37
    2848        SUCCESS        2            4        832.17        0.2        832.37
    2860        SUCCESS        2            4        832.17        0.2        832.37
    2872        SUCCESS        2            4        832.17        0.2        832.37
    2884        SUCCESS        2            4        832.17        0.2        832.37
    2896        SUCCESS        2            4        832.17        0.2        832.37
    2908        SUCCESS        2            4        832.17        0.2        832.37
    2920        SUCCESS        2            4        832.17        0.2        832.37
    2932        SUCCESS        2            4        832.17        0.2        832.37
    2944        SUCCESS        2            4        832.17        0.2        832.37
    2956        SUCCESS        2            4        832.17        0.2        832.37
    2968        SUCCESS        2            4        832.17        0.2        832.37
    2980        SUCCESS        2            4        832.17        0.2        832.37
    2992        SUCCESS        2            4        832.17        0.2        832.37
    3004        SUCCESS        2            4        832.17        0.2        832.37
    3016        SUCCESS        2            4        832.17        0.2        832.37
    3028        SUCCESS        2            4        832.17        0.2        832.37
    3040        SUCCESS        2            4        832.17        0.2        832.37
    3052        SUCCESS        2            4        832.17        0.2        832.37
    3064        SUCCESS        2            4        832.17        0.2        832.37
    3076        SUCCESS        2            4        832.17        0.2        832.37
    3088        SUCCESS        2            4        832.17        0.2        832.37
    3100        SUCCESS        2            4        832.17        0.2        832.37
    3112        SUCCESS        2            4        832.17        0.2        832.37
    3124        SUCCESS        2            4        832.17        0.2        832.37
    3136        SUCCESS        2            4        832.17        0.2        832.37
    3148        SUCCESS        2            4        832.17        0.2        832.37
    3160        SUCCESS        2            4        832.17        0.2        832.37
    3172        SUCCESS        2            4        832.17        0.2        832.37
    3184        SUCCESS        2            4        832.17        0.2        832.37
    3196        SUCCESS        2            4        832.17        0.2        832.37
    3208        SUCCESS        2            4        832.17        0.2        832.37
    3220        SUCCESS        2            4        832.17        0.2        832.37
    3232        SUCCESS        2            4        832.17        0.2        832.37
    3244        SUCCESS        2            4        832.17        0.2        832.37
    3256        SUCCESS        2            4        832.17        0.2        832.37
    3268        SUCCESS        2            4        832.17        0.2        832.37
    3280        SUCCESS        2            4        832.17        0.2        832.37
    3292        SUCCESS        2            4        832.17        0.2        832.37
    3304        SUCCESS        2            4        832.17        0.2        832.37
    3316        SUCCESS        2            4        832.17        0.2        832.37
    3328        SUCCESS        2            4        832.17        0.2        832.37
    3340        SUCCESS        2            4        832.17        0.2        832.37
    3352        SUCCESS        2            4        832.17        0.2        832.37
    3364        SUCCESS        2            4        832.17        0.2        832.37
    3376        SUCCESS        2            4        832.17        0.2        832.37
    3388        SUCCESS        2            4        832.17        0.2        832.37
    3400        SUCCESS        2            4        832.17        0.2        832.37
    3412        SUCCESS        2            4        832.17        0.2        832.37
    3424        SUCCESS        2            4        832.17        0.2        832.37
    3436        SUCCESS        2            4        832.17        0.2        832.37
    3448        SUCCESS        2            4        832.17        0.2        832.37
    3460        SUCCESS        2            4        832.17        0.2        832.37
    3472        SUCCESS        2            4        832.17        0.2        832.37
    3484        SUCCESS        2            4        832.17        0.2        832.37
    3496        SUCCESS        2            4        832.17        0.2        832.37
    3508        SUCCESS        2            4        832.17        0.2        832.37
    3520        SUCCESS        2            4        832.17        0.2        832.37
    3532        SUCCESS        2            4        832.17        0.2        832.37
    3544        SUCCESS        2            4        832.17        0.2        832.37
    3556        SUCCESS        2            4        832.17        0.2        832.37
    3568        SUCCESS        2            4        832.17        0.2        832.37
    3580        SUCCESS        2            4        832.17        0.2        832.37
    3592        SUCCESS        2            4        832.17        0.2        832.37
    3604        SUCCESS        2            4        832.17        0.2        832.37
    3616        SUCCESS        2            4        832.17        0.2        832.37
    3628        SUCCESS        2            4        832.17        0.2        832.37
    3640        SUCCESS        2            4        832.17        0.2        832.37
    3652        SUCCESS        2            4        832.17        0.2        832.37
    3664        SUCCESS        2            4        832.17        0.2        832.37
    3676        SUCCESS        2            4        832.17        0.2        832.37
    3688        SUCCESS        2            4        832.17        0.2        832.37
    3700        SUCCESS        2            4        832.17        0.2        832.37
    3712        SUCCESS        2            4        832.17        0.2        832.37
    3724        SUCCESS        2            4        832.17        0.2        832.37
    3736        SUCCESS        2            4        832.17        0.2        832.37
    3748        SUCCESS        2            4        832.17        0.2        832.37
    3760        SUCCESS        2            4        832.17        0.2        832.37
    3772        SUCCESS        2            4        832.17        0.2        832.37
    3784        SUCCESS        2            4        832.17        0.2        832.37
    3796        SUCCESS        2            4        832.17        0.2        832.37
    3808        SUCCESS        2            4        832.17        0.2        832.37
    3820        SUCCESS        2            4        832.17        0.2        832.37
    3832        SUCCESS        2            4        832.17        0.2        832.37
    3844        SUCCESS        2            4        832.17        0.2        832.37
    3856        SUCCESS        2            4        832.17        0.2        832.37
    3868        SUCCESS        2            4        832.17        0.2        832.37
    3880        SUCCESS        2            4        832.17        0.2        832.37
    3892        SUCCESS        2            4        832.17        0.2        832.37
    3904        SUCCESS        2            4        832.17        0.2        832.37
    3916        SUCCESS        2            4        832.17        0.2        832.37
    3928        SUCCESS        2            4        832.17        0.2        832.37
    3940        SUCCESS        2            4        832.17        0.2        832.37
    3952        SUCCESS        2            4        832.17        0.2        832.37
    3964        SUCCESS        2            4        832.17        0.2        832.37
    3976        SUCCESS        2            4        832.17        0.2        832.37
    3988        SUCCESS        2            4        832.17        0.2        832.37
    4000        SUCCESS        2            4        832.17        0.2        832.37
    4012        SUCCESS        2            4        832.17        0.2        832.37
    4024        SUCCESS        2            4        832.17        0.2        832.37
    4036        SUCCESS        2            4        832.17        0.2        832.37
    4048        SUCCESS        2            4        832.17        0.2        832.37
    4060        SUCCESS        2            4        832.17        0.2        832.37
    4072        SUCCESS        2            4        832.17        0.2        832.37
    4084        SUCCESS        2            4        832.17        0.2        832.37
    4096        SUCCESS        2            4        832.17        0.2        832.37
    4108        SUCCESS        2            4        832.17        0.2        832.37
    4120        SUCCESS        2            4        832.17        0.2        832.37
    4132        SUCCESS        2            4        832.17        0.2        832.37
    4144        SUCCESS        2            4        832.17        0.2        832.37
    4156        SUCCESS        2            4        832.17        0.2        832.37
    4168        SUCCESS        2            4        832.17        0.2        832.37
    4180        SUCCESS        2            4        832.17        0.2        832.37
    4192        SUCCESS        2            4        832.17        0.2        832.37
    4204        SUCCESS        2            4        832.17        0.2        832.37
    4216        SUCCESS        2            4        832.17        0.2        832.37
    4228        SUCCESS        2            4        832.17        0.2        832.37
    4240        SUCCESS        2            4        832.17        0.2        832.37
    4252        SUCCESS        2            4        832.17        0.2        832.37
    4264        SUCCESS        2            4        832.17        0.2        832.37
    4276        SUCCESS        2            4        832.17        0.2        832.37
    4288        SUCCESS        2            4        832.17        0.2        832.37
    4300        SUCCESS        2            4        832.17        0.2        832.37
    4312        SUCCESS        2            4        832.17        0.2        832.37
    4324        SUCCESS        2            4        832.17        0.2        832.37
    4336        SUCCESS        2            4        832.17        0.2        832.37
    4348        SUCCESS        2            4        832.17        0.2        832.37
    4360        SUCCESS        2            4        832.17        0.2        832.37
    4372        SUCCESS        2            4        832.17        0.2        832.37
    4384        SUCCESS        2            4        832.17        0.2        832.37
    4396        SUCCESS        2            4        832.17        0.2        832.37
    4408        SUCCESS        2            4        832.17        0.2        832.37
    4420        SUCCESS        2            4        832.17        0.2        832.37
    4432        SUCCESS        2            4        832.17        0.2        832.37
    4444        SUCCESS        2            4        832.17        0.2        832.37
    4456        SUCCESS        2            4        832.17        0.2        832.37
    4468        SUCCESS        2            4        832.17        0.2        832.37
    4480        SUCCESS        2            4        832.17        0.2        832.37
    4492        SUCCESS        2            4        832.17        0.2        832.37
    4504        SUCCESS        2            4        832.17        0.2        832.37
    4516        SUCCESS        2            4        832.17        0.2        832.37
    4528        SUCCESS        2            4        832.17        0.2        832.37
    4540        SUCCESS        2            4        832.17        0.2        832.37
    4552        SUCCESS        2            4        832.17        0.2        832.37
    4564        SUCCESS        2            4        832.17        0.2        832.37
    4576        SUCCESS        2            4        832.17        0.2        832.37
    4588        SUCCESS        2            4        832.17        0.2        832.37
    4600        SUCCESS        2            4        832.17        0.2        832.37
    4612        SUCCESS        2            4        832.17        0.2        832.37
    4624        SUCCESS        2            4        832.17        0.2        832.37
    4636        SUCCESS        2            4        832.17        0.2        832.37
    4648        SUCCESS        2            4        832.17        0.2        832.37
    4660        SUCCESS        2            4        832.17        0.2        832.37
    4672        SUCCESS        2            4        832.17        0.2        832.37
    4684        SUCCESS        2            4        832.17        0.2        832.37
    4696        SUCCESS        2            4        832.17        0.2        832.37
    4708        SUCCESS        2            4        832.17        0.2        832.37
    4720        SUCCESS        2            4        832.17        0.2        832.37
    4732        SUCCESS        2            4        832.17        0.2        832.37
    4744        SUCCESS        2            4        832.17        0.2        832.37
    4756        SUCCESS        2            4        832.17        0.2        832.37
    4768        SUCCESS        2            4        832.17        0.2        832.37
    4780        SUCCESS        2            4        832.17        0.2        832.37
    4792        SUCCESS        2            4        832.17        0.2        832.37
    4804        SUCCESS        2            4        832.17        0.2        832.37
    4816        SUCCESS        2            4        832.17        0.2        832.37
    4828        SUCCESS        2            4        832.17        0.2        832.37
    4840        SUCCESS        2            4        832.17        0.2        832.37
    4852        SUCCESS        2            4        832.17        0.2        832.37
    4864        SUCCESS        2            4        832.17        0.2        832.37
    4876        SUCCESS        2            4        832.17        0.2        832.37
    4888        SUCCESS        2            4        832.17        0.2        832.37
    4900        SUCCESS        2            4        832.17        0.2        832.37
    4912        SUCCESS        2            4        832.17        0.2        832.37
    4924        SUCCESS        2            4        832.17        0.2        832.37
    4936        SUCCESS        2            4        832.17        0.2        832.37
    4948        SUCCESS        2            4        832.17        0.2        832.37
    4960        SUCCESS        2            4        832.17        0.2        832.37
    4972        SUCCESS        2            4        832.17        0.2        832.37
    4984        SUCCESS        2            4        832.17        0.2        832.37
    4996        SUCCESS        2            4        832.17        0.2        832.37
    5008        SUCCESS        2            4        832.17        0.2        832.37
    5020        SUCCESS        2            4        832.17        0.2        832.37
    5032        SUCCESS        2            4        832.17        0.2        832.37
    5044        SUCCESS        2            4        832.17        0.2        832.37
    5056        SUCCESS        2            4        832.17        0.2        832.37
    5068        SUCCESS        2            4        832.17        0.2        832.37
    5080        SUCCESS        2            4        832.17        0.2        832.37
    5092        SUCCESS        2            4        832.17        0.2        832.37
    5104        SUCCESS        2            4        832.17        0.2        832.37
    5116        SUCCESS        2            4        832.17        0.2        832.37
    5128        SUCCESS        2            4        832.17        0.2        832.37
    5140        SUCCESS        2            4        832.17        0.2        832.37
    5152        SUCCESS        2            4        832.17        0.2        832.37
    5164        SUCCESS        2            4        832.17        0.2        832.37
    5176        SUCCESS        2            4        832.17        0.2        832.37
    5188        SUCCESS        2            4        832.17        0.2        832.37
    5200        SUCCESS        2            4        832.17        0.2        832.37
    5212        SUCCESS        2            4        832.17        0.2        832.37
    5224        SUCCESS        2            4        832.17        0.2        832.37
    5236        SUCCESS        2            4        832.17        0.2        832.37
    5248        SUCCESS        2            4        832.17        0.2        832.37
    5260        SUCCESS        2            4        832.17        0.2        832.37
    5272        SUCCESS        2            4        832.17        0.2        832.37
    5284        SUCCESS        2            4        832.17        0.2        832.37
    5296        SUCCESS        2            4        832.17        0.2        832.37
    5308        SUCCESS        2            4        832.17        0.2        832.37
    5320        SUCCESS        2            4        832.17        0.2        832.37
    5332        SUCCESS        2            4        832.17        0.2        832.37
    5344        SUCCESS        2            4        832.17        0.2        832.37
    5356        SUCCESS        2            4        832.17        0.2        832.37
    5368        SUCCESS        2            4        832.17        0.2        832.37
    5380        SUCCESS        2            4        832.17        0.2        832.37
    5392        SUCCESS        2            4        832.17        0.2        832.37
    5404        SUCCESS        2            4        832.17        0.2        832.37
    5416        SUCCESS        2            4        832.17        0.2        832.37
    5428        SUCCESS        2            4        832.17        0.2        832.37
    5440        SUCCESS        2            4        832.17        0.2        832.37
    5452        SUCCESS        2            4        832.17        0.2        832.37
    5464        SUCCESS        2            4        832.17        0.2        832.37
    5476        SUCCESS        2            4        832.17        0.2        832.37
    5488        SUCCESS        2            4        832.17        0.2        832.37
    5500        SUCCESS        2            4        832.17        0.2        832.37
    5512        SUCCESS        2            4        832.17        0.2        832.37
    5524        SUCCESS        2            4        832.17        0.2        832.37
    5536        SUCCESS        2            4        832.17        0.2        832.37
    5548        SUCCESS        2            4        832.17        0.2        832.37
    5560        SUCCESS        2            4        832.17        0.2        832.37
    5572        SUCCESS        2            4        832.17        0.2        832.37
    5584        SUCCESS        2            4        832.17        0.2        832.37
    5596        SUCCESS        2            4        832.17        0.2        832.37
    5608        SUCCESS        2            4        832.17        0.2        832.37
    5620        SUCCESS        2            4        832.17        0.2        832.37
    5632        SUCCESS        2            4        832.17        0.2        832.37
    5644        SUCCESS        2            4        832.17        0.2        832.37
    5656        SUCCESS        2            4        832.17        0.2        832.37
    5668        SUCCESS        2            4        832.17        0.2        832.37
    5680        SUCCESS        2            4        832.17        0.2        832.37
    5692        SUCCESS        2            4        832.17        0.2        832.37
    5704        SUCCESS        2            4        832.17        0.2        832.37
    5716        SUCCESS        2            4        832.17        0.2        832.37
    5728        SUCCESS        2            4        832.17        0.2        832.37
    5740        SUCCESS        2            4        832.17        0.2        832.37
    5752        SUCCESS        2            4        832.17        0.2        832.37
    5764        SUCCESS        2            4        832.17        0.2        832.37
    5776        SUCCESS        2            4        832.17        0.2        832.37
    5788        SUCCESS        2            4        832.17        0.2        832.37
    5800        SUCCESS        2            4        832.17        0.2        832.37
    5812        SUCCESS        2            4        832.17        0.2        832.37
    5824        SUCCESS        2            4        832.17        0.2        832.37
    5836        SUCCESS        2            4        832.17        0.2        832.37
    5848        SUCCESS        2            4        832.17        0.2        832.37
    5860        SUCCESS        2            4        832.17        0.2        832.37
    5872        SUCCESS        2            4        832.17        0.2        832.37
    5884        SUCCESS        2            4        832.17        0.2        832.37
    5896        SUCCESS        2            4        832.17        0.2        832.37
    5908        SUCCESS        2            4        832.17        0.2        832.37
    5920        SUCCESS        2            4        832.17        0.2        832.37
    5932        SUCCESS        2            4        832.17        0.2        832.37
    5944        SUCCESS        2            4        832.17        0.2        832.37
    5956        SUCCESS        2            4        832.17        0.2        832.37
    5968        SUCCESS        2            4        832.17        0.2        832.37
    5980        SUCCESS        2            4        832.17        0.2        832.37
    5992        SUCCESS        2            4        832.17        0.2        832.37
    6004        SUCCESS        2            4        832.17        0.2        832.37
    6016        SUCCESS        2            4        832.17        0.2        832.37
    6028        SUCCESS        2            4        832.17        0.2        832.37
    6040        SUCCESS        2            4        832.17        0.2        832.37
    6052        SUCCESS        2            4        832.17        0.2        832.37
    6064        SUCCESS        2            4        832.17        0.2        832.37
    6076        SUCCESS        2            4        832.17        0.2        832.37
    6088        SUCCESS        2            4        832.17        0.2        832.37
    6100        SUCCESS        2            4        832.17        0.2        832.37
    6112        SUCCESS        2            4        832.17        0.2        832.37
    6124        SUCCESS        2            4        832.17        0.2        832.37
    6136        SUCCESS        2            4        832.17        0.2        832.37
    6148        SUCCESS        2            4        832.17        0.2        832.37
    6160        SUCCESS        2            4        832.17        0.2        832.37
    6172        SUCCESS        2            4        832.17        0.2        832.37
    6184        SUCCESS        2            4        832.17        0.2        832.37
    6196        SUCCESS        2            4        832.17        0.2        832.37
    6208        SUCCESS        2            4        832.17        0.2        832.37
    6220        SUCCESS        2            4        832.17        0.2        832.37
    6232        SUCCESS        2            4        832.17        0.2        832.37
    6244        SUCCESS        2            4        832.17        0.2        832.37
    6256        SUCCESS        2            4        832.17        0.2        832.37
    6268        SUCCESS        2            4        832.17        0.2        832.37
    6280        SUCCESS        2            4        832.17        0.2        832.37
    6292        SUCCESS        2            4        832.17        0.2        832.37
    6304        SUCCESS        2            4        832.17        0.2        832.37
    6316        SUCCESS        2            4        832.17        0.2        832.37
    6328        SUCCESS        2            4        832.17        0.2        832.37
    6340        SUCCESS        2            4        832.17        0.2        832.37
    6352        SUCCESS        2            4        832.17        0.2        832.37
    6364        SUCCESS        2            4        832.17        0.2        832.37
    6376        SUCCESS        2            4        832.17        0.2        832.37
    6388        SUCCESS        2            4        832.17        0.2        832.37
    6400        SUCCESS        2            4        832.17        0.2        832.37
    6412        SUCCESS        2            4        832.17        0.2        832.37
    6424        SUCCESS        2            4        832.17        0.2        832.37
    6436        SUCCESS        2            4        832.17        0.2        832.37
    6448        SUCCESS        2            4        832.17        0.2        832.37
    6460        SUCCESS        2            4        832.17        0.2        832.37
    6472        SUCCESS        2            4        832.17        0.2        832.37
    6484        SUCCESS        2            4        832.17        0.2        832.37
    6496        SUCCESS        2            4        832.17        0.2        832.37
    6508        SUCCESS        2            4        832.17        0.2        832.37
    6520        SUCCESS        2            4        832.17        0.2        832.37
    6532        SUCCESS        2            4        832.17        0.2        832.37
    6544        SUCCESS        2            4        832.17        0.2        832.37
    6556        SUCCESS        2            4        832.17        0.2        832.37
    6568        SUCCESS        2            4        832.17        0.2        832.37
    6580        SUCCESS        2            4        832.17        0.2        832.37
    6592        SUCCESS        2            4        832.17        0.2        832.37
    6604        SUCCESS        2            4        832.17        0.2        832.37
    6616        SUCCESS        2            4        832.17        0.2        832.37
    6628        SUCCESS        2            4        832.17        0.2        832.37
    6640        SUCCESS        2            4        832.17        0.2        832.37
    6652        SUCCESS        2            4        832.17        0.2        832.37
    6664        SUCCESS        2            4        832.17        0.2        832.37
    6676        SUCCESS        2            4        832.17        0.2        832.37
    6688        SUCCESS        2            4        832.17        0.2        832.37
    6700        SUCCESS        2            4        832.17        0.2        832.37
    6712        SUCCESS        2            4        832.17        0.2        832.37
    6724        SUCCESS        2            4        832.17        0.2        832.37
    6736        SUCCESS        2            4        832.17        0.2        832.37
    6748        SUCCESS        2            4        832.17        0.2        832.37
    6760        SUCCESS        2            4        832.17        0.2        832.37
    6772        SUCCESS        2            4        832.17        0.2        832.37
    6784        SUCCESS        2            4        832.17        0.2        832.37
    6796        SUCCESS        2            4        832.17        0.2        832.37
    6808        SUCCESS        2            4        832.17        0.2        832.37
    6820        SUCCESS        2            4        832.17        0.2        832.37
    6832        SUCCESS        2            4        832.17        0.2        832.37
    6844        SUCCESS        2            4        832.17        0.2        832.37
    6856        SUCCESS        2            4        832.17        0.2        832.37
    6868        SUCCESS        2            4        832.17        0.2        832.37
    6880        SUCCESS        2            4        832.17        0.2        832.37
    6892        SUCCESS        2            4        832.17        0.2        832.37
    6904        SUCCESS        2            4        832.17        0.2        832.37
    6916        SUCCESS        2            4        832.17        0.2        832.37
    6928        SUCCESS        2            4        832.17        0.2        832.37
    6940        SUCCESS        2            4        832.17        0.2        832.37
    6952        SUCCESS        2            4        832.17        0.2        832.37
    6964        SUCCESS        2            4        832.17        0.2        832.37
    6976        SUCCESS        2            4        832.17        0.2        832.37
    6988        SUCCESS        2            4        832.17        0.2        832.37
    7000        SUCCESS        2            4        832.17        0.2        832.37
    7012        SUCCESS        2            4        832.17        0.2        832.37
    7024        SUCCESS        2            4        832.17        0.2        832.37
    7036        SUCCESS        2            4        832.17        0.2        832.37
    7048        SUCCESS        2            4        832.17        0.2        832.37
    7060        SUCCESS        2            4        832.17        0.2        832.37
    7072        SUCCESS        2            4        832.17        0.2        832.37
    7084        SUCCESS        2            4        832.17        0.2        832.37
    7096        SUCCESS        2            4        832.17        0.2        832.37
    7108        SUCCESS        2            4        832.17        0.2        832.37
    7120        SUCCESS        2            4        832.17        0.2        832.37
    7132        SUCCESS        2            4        832.17        0.2        832.37
    7144        SUCCESS        2            4        832.17        0.2        832.37
    7156        SUCCESS        2            4        832.17        0.2        832.37
    7168        SUCCESS        2            4        832.17        0.2        832.37
    7180        SUCCESS        2            4        832.17        0.2        832.37
    7192        SUCCESS        2            4        832.17        0.2        832.37
    7204        SUCCESS        2            4        832.17        0.2        832.37
    7216        SUCCESS        2            4        832.17        0.2        832.37
    7228        SUCCESS        2            4        832.17        0.2        832.37
    7240        SUCCESS        2            4        832.17        0.2        832.37
    7252        SUCCESS        2            4        832.17        0.2        832.37
    7264        SUCCESS        2            4        832.17        0.2        832.37
    7276        SUCCESS        2            4        832.17        0.2        832.37
    7288        SUCCESS        2            4        832.17        0.2        832.37
    7300        SUCCESS        2            4        832.17        0.2        832.37
    7312        SUCCESS        2            4        832.17        0.2        832.37
    7324        SUCCESS        2            4        832.17        0.2        832.37
    7336        SUCCESS        2            4        832.17        0.2        832.37
    7348        SUCCESS        2            4        832.17        0.2        832.37
    7360        SUCCESS        2            4        832.17        0.2        832.37
    7372        SUCCESS        2            4        832.17        0.2        832.37
    7384        SUCCESS        2            4        832.17        0.2        832.37
    7396        SUCCESS        2            4        832.17        0.2        832.37
    7408        SUCCESS        2            4        832.17        0.2        832.37
    7420        SUCCESS        2            4        832.17        0.2        832.37
    7432        SUCCESS        2            4        832.17        0.2        832.37
    7444        SUCCESS        2            4        832.17        0.2        832.37
    7456        SUCCESS        2            4        832.17        0.2        832.37
    7468        SUCCESS        2            4        832.17        0.2        832.37
    7480        SUCCESS        2            4        832.17        0.2        832.37
    7492        SUCCESS        2            4        832.17        0.2        832.37
    7504        SUCCESS        2            4        832.17        0.2        832.37
    7516        SUCCESS        2            4        832.17        0.2        832.37
    7528        SUCCESS        2            4        832.17        0.2        832.37
    7540        SUCCESS        2            4        832.17        0.2        832.37
    7552        SUCCESS        2            4        832.17        0.2        832.37
    7564        SUCCESS        2            4        832.17        0.2        832.37
    7576        SUCCESS        2            4        832.17        0.2        832.37
    7588        SUCCESS        2            4        832.17        0.2        832.37
    7600        SUCCESS        2            4        832.17        0.2        832.37
    7612        SUCCESS        2            4        832.17        0.2        832.37
    7624        SUCCESS        2            4        832.17        0.2        832.37
    7636        SUCCESS        2            4        832.17        0.2        832.37
    7648        SUCCESS        2            4        832.17        0.2        832.37
    7660        SUCCESS        2            4        832.17        0.2        832.37
    7672        SUCCESS        2            4        832.17        0.2        832.37
    7684        SUCCESS        2            4        832.17        0.2        832.37
    7696        SUCCESS        2            4        832.17        0.2        832.37
    7708        SUCCESS        2            4        832.17        0.2        832.37
    7720        SUCCESS        2            4        832.17        0.2        832.37
    7732        SUCCESS        2            4        832.17        0.2        832.37
    7744        SUCCESS        2            4        832.17        0.2        832.37
    7756        SUCCESS        2            4        832.17        0.2        832.37
    7768        SUCCESS        2            4        832.17        0.2        832.37
    7780        SUCCESS        2            4        832.17        0.2        832.37
    7792        SUCCESS        2            4        832.17        0.2        832.37
    7804        SUCCESS        2            4        832.17        0.2        832.37
    7816        SUCCESS        2            4        832.17        0.2        832.37
    7828        SUCCESS        2            4        832.17        0.2        832.37
    7840        SUCCESS        2            4        832.17        0.2        832.37
    7852        SUCCESS        2            4        832.17        0.2        832.37
    7864        SUCCESS        2            4        832.17        0.2        832.37
    7876        SUCCESS        2            4        832.17        0.2        832.37
    7888        SUCCESS        2            4        832.17        0.2        832.37
    7900        SUCCESS        2            4        832.17        0.2        832.37
    7912        SUCCESS        2            4        832.17        0.2        832.37
    7924        SUCCESS        2            4        832.17        0.2        832.37
    7936        SUCCESS        2            4        832.17        0.2        832.37
    7948        SUCCESS        2            4        832.17        0.2        832.37
    7960        SUCCESS        2            4        832.17        0.2        832.37
    7972        SUCCESS        2            4        832.17        0.2        832.37
    7984        SUCCESS        2            4        832.17        0.2        832.37
    7996        SUCCESS        2            4        832.17        0.2        832.37
    8008        SUCCESS        2            4        832.17        0.2        832.37
    8020        SUCCESS        2            4        832.17        0.2        832.37
    8032        SUCCESS        2            4        832.17        0.2        832.37
    8044        SUCCESS        2            4        832.17        0.2        832.37
    8056        SUCCESS        2            4        832.17        0.2        832.37
    8068        SUCCESS        2            4        832.17        0.2        832.37
    8080        SUCCESS        2            4        832.17        0.2        832.37
    8092        SUCCESS        2            4        832.17        0.2        832.37
    8104        SUCCESS        2            4        832.17        0.2        832.37
    8116        SUCCESS        2            4        832.17        0.2        832.37
    8128        SUCCESS        2            4        832.17        0.2        832.37
    8140        SUCCESS        2            4        832.17        0.2        832.37
    8152        SUCCESS        2            4        832.17        0.2        832.37
    8164        SUCCESS        2            4        832.17        0.2        832.37
    8176        SUCCESS        2            4        832.17        0.2        832.37
    8188        SUCCESS        2            4        832.17        0.2        832.37
    8200        SUCCESS        2            4        832.17        0.2        832.37
    8212        SUCCESS        2            4        832.17        0.2        832.37
    8224        SUCCESS        2            4        832.17        0.2        832.37
    8236        SUCCESS        2            4        832.17        0.2        832.37
    8248        SUCCESS        2            4        832.17        0.2        832.37
    8260        SUCCESS        2            4        832.17        0.2        832.37
    8272        SUCCESS        2            4        832.17        0.2        832.37
    8284        SUCCESS        2            4        832.17        0.2        832.37
    8296        SUCCESS        2            4        832.17        0.2        832.37
    8308        SUCCESS        2            4        832.17        0.2        832.37
    8320        SUCCESS        2            4        832.17        0.2        832.37
    8332        SUCCESS        2            4        832.17        0.2        832.37
    8344        SUCCESS        2            4        832.17        0.2        832.37
    8356        SUCCESS        2            4        832.17        0.2        832.37
    8368        SUCCESS        2            4        832.17        0.2        832.37
    8380        SUCCESS        2            4        832.17        0.2        832.37
    8392        SUCCESS        2            4        832.17        0.2        832.37
    8404        SUCCESS        2            4        832.17        0.2        832.37
    8416        SUCCESS        2            4        832.17        0.2        832.37
    8428        SUCCESS        2            4        832.17        0.2        832.37
    8440        SUCCESS        2            4        832.17        0.2        832.37
    8452        SUCCESS        2            4        832.17        0.2        832.37
    8464        SUCCESS        2            4        832.17        0.2        832.37
    8476        SUCCESS        2            4        832.17        0.2        832.37
    8488        SUCCESS        2            4        832.17        0.2        832.37
    8500        SUCCESS        2            4        832.17        0.2        832.37
    8512        SUCCESS        2            4        832.17        0.2        832.37
    8524        SUCCESS        2            4        832.17        0.2        832.37
    8536        SUCCESS        2            4        832.17        0.2        832.37
    8548        SUCCESS        2            4        832.17        0.2        832.37
    8560        SUCCESS        2            4        832.17        0.2        832.37
    8572        SUCCESS        2            4        832.17        0.2        832.37
    8584        SUCCESS        2            4        832.17        0.2        832.37
    8596        SUCCESS        2            4        832.17        0.2        832.37
    8608        SUCCESS        2            4        832.17        0.2        832.37
    8620        SUCCESS        2            4        832.17        0.2        832.37
    8632        SUCCESS        2            4        832.17        0.2        832.37
    8644        SUCCESS        2            4        832.17        0.2        832.37
    8656        SUCCESS        2            4        832.17        0.2        832.37
    8668        SUCCESS        2            4        832.17        0.2        832.37
    8680        SUCCESS        2            4        832.17        0.2        832.37
    8692        SUCCESS        2            4        832.17        0.2        832.37
    8704        SUCCESS        2            4        832.17        0.2        832.37
    8716        SUCCESS        2            4        832.17        0.2        832.37
    8728        SUCCESS        2            4        832.17        0.2        832.37
    8740        SUCCESS        2            4        832.17        0.2        832.37
    8752        SUCCESS        2            4        832.17        0.2        832.37
    8764        SUCCESS        2            4        832.17        0.2        832.37
    8776        SUCCESS        2            4        832.17        0.2        832.37
    8788        SUCCESS        2            4        832.17        0.2        832.37
    8800        SUCCESS        2            4        832.17        0.2        832.37
    8812        SUCCESS        2            4        832.17        0.2        832.37
    8824        SUCCESS        2            4        832.17        0.2        832.37
    8836        SUCCESS        2            4        832.17        0.2        832.37
    8848        SUCCESS        2            4        832.17        0.2        832.37
    8860        SUCCESS        2            4        832.17        0.2        832.37
    8872        SUCCESS        2            4        832.17        0.2        832.37
    8884        SUCCESS        2            4        832.17        0.2        832.37
    8896        SUCCESS        2            4        832.17        0.2        832.37
    8908        SUCCESS        2            4        832.17        0.2        832.37
    8920        SUCCESS        2            4        832.17        0.2        832.37
    8932        SUCCESS        2            4        832.17        0.2        832.37
    8944        SUCCESS        2            4        832.17        0.2        832.37
    8956        SUCCESS        2            4        832.17        0.2        832.37
    8968        SUCCESS        2            4        832.17        0.2        832.37
    8980        SUCCESS        2            4        832.17        0.2        832.37
    8992        SUCCESS        2            4        832.17        0.2        832.37
    9004        SUCCESS        2            4        832.17        0.2        832.37
    9016        SUCCESS        2            4        832.17        0.2        832.37
    9028        SUCCESS        2            4        832.17        0.2        832.37
    9040        SUCCESS        2            4        832.17        0.2        832.37
    9052        SUCCESS        2            4        832.17        0.2        832.37
    9064        SUCCESS        2            4        832.17        0.2        832.37
    9076        SUCCESS        2            4        832.17        0.2        832.37
    9088        SUCCESS        2            4        832.17        0.2        832.37
    9100        SUCCESS        2            4        832.17        0.2        832.37
    9112        SUCCESS        2            4        832.17        0.2        832.37
    9124        SUCCESS        2            4        832.17        0.2        832.37
    9136        SUCCESS        2            4        832.17        0.2        832.37
    9148        SUCCESS        2            4        832.17        0.2        832.37
    9160        SUCCESS        2            4        832.17        0.2        832.37
    9172        SUCCESS        2            4        832.17        0.2        832.37
    9184        SUCCESS        2            4        832.17        0.2        832.37
    9196        SUCCESS        2            4        832.17        0.2        832.37
    9208        SUCCESS        2            4        832.17        0.2        832.37
    9220        SUCCESS        2            4        832.17        0.2        832.37
    9232        SUCCESS        2            4        832.17        0.2        832.37
    9244        SUCCESS        2            4        832.17        0.2        832.37
    9256        SUCCESS        2            4        832.17        0.2        832.37
    9268        SUCCESS        2            4        832.17        0.2        832.37
    9280        SUCCESS        2            4        832.17        0.2        832.37
    9292        SUCCESS        2            4        832.17        0.2        832.37
    9304        SUCCESS        2            4        832.17        0.2        832.37
    9316        SUCCESS        2            4        832.17        0.2        832.37
    9328        SUCCESS        2            4        832.17        0.2        832.37
    9340        SUCCESS        2            4        832.17        0.2        832.37
    9352        SUCCESS        2            4        832.17        0.2        832.37
    9364        SUCCESS        2            4        832.17        0.2        832.37
    9376        SUCCESS        2            4        832.17        0.2        832.37
    9388        SUCCESS        2            4        832.17        0.2        832.37
    9400        SUCCESS        2            4        832.17        0.2        832.37
    9412        SUCCESS        2            4        832.17        0.2        832.37
    9424        SUCCESS        2            4        832.17        0.2        832.37
    9436        SUCCESS        2            4        832.17        0.2        832.37
    9448        SUCCESS        2            4        832.17        0.2        832.37
    9460        SUCCESS        2            4        832.17        0.2        832.37
    9472        SUCCESS        2            4        832.17        0.2        832.37
    9484        SUCCESS        2            4        832.17        0.2        832.37
    9496        SUCCESS        2            4        832.17        0.2        832.37
    9508        SUCCESS        2            4        832.17        0.2        832.37
    9520        SUCCESS        2            4        832.17        0.2        832.37
    9532        SUCCESS        2            4        832.17        0.2        832.37
    9544        SUCCESS        2            4        832.17        0.2        832.37
    9556        SUCCESS        2            4        832.17        0.2        832.37
    9568        SUCCESS        2            4        832.17        0.2        832.37
    9580        SUCCESS        2            4        832.17        0.2        832.37
    9592        SUCCESS        2            4        832.17        0.2        832.37
    9604        SUCCESS        2            4        832.17        0.2        832.37
    9616        SUCCESS        2            4        832.17        0.2        832.37
    9628        SUCCESS        2            4        832.17        0.2        832.37
    9640        SUCCESS        2            4        832.17        0.2        832.37
    9652        SUCCESS        2            4        832.17        0.2        832.37
    9664        SUCCESS        2            4        832.17        0.2        832.37
    9676        SUCCESS        2            4        832.17        0.2        832.37
    9688        SUCCESS        2            4        832.17        0.2        832.37
    9700        SUCCESS        2            4        832.17        0.2        832.37
    9712        SUCCESS        2            4        832.17        0.2        832.37
    9724        SUCCESS        2            4        832.17        0.2        832.37
    9736        SUCCESS        2            4        832.17        0.2        832.37
    9748        SUCCESS        2            4        832.17        0.2        832.37
    9760        SUCCESS        2            4        832.17        0.2        832.37
    9772        SUCCESS        2            4        832.17        0.2        832.37
    9784        SUCCESS        2            4        832.17        0.2        832.37
    9796        SUCCESS        2            4        832.17        0.2        832.37
    9808        SUCCESS        2            4        832.17        0.2        832.37
    9820        SUCCESS        2            4        832.17        0.2        832.37
    9832        SUCCESS        2            4        832.17        0.2        832.37
    9844        SUCCESS        2            4        832.17        0.2        832.37
    9856        SUCCESS        2            4        832.17        0.2        832.37
    9868        SUCCESS        2            4        832.17        0.2        832.37
    9880        SUCCESS        2            4        832.17        0.2        832.37
    9892        SUCCESS        2            4        832.17        0.2        832.37
    9904        SUCCESS        2            4        832.17        0.2        832.37
    9916        SUCCESS        2            4        832.17        0.2        832.37
    9928        SUCCESS        2            4        832.17        0.2        832.37
    9940        SUCCESS        2            4        832.17        0.2        832.37
    9952        SUCCESS        2            4        832.17        0.2        832.37
    9964        SUCCESS        2            4        832.17        0.2        832.37
    9976        SUCCESS        2            4        832.17        0.2        832.37
    9988        SUCCESS        2            4        832.17        0.2        832.37
