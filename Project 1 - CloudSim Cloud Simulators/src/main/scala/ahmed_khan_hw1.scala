/*
* Ahmed Khan
* */

import java.text.DecimalFormat
import java.util
import java.util.Calendar

import com.typesafe.config.{Config, ConfigFactory}

import org.cloudbus.cloudsim.Cloudlet
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared
import org.cloudbus.cloudsim.Datacenter
import org.cloudbus.cloudsim.DatacenterBroker
import org.cloudbus.cloudsim.DatacenterCharacteristics
import org.cloudbus.cloudsim.Host
import org.cloudbus.cloudsim.Log
import org.cloudbus.cloudsim.Pe
import org.cloudbus.cloudsim.Storage
import org.cloudbus.cloudsim.UtilizationModel
import org.cloudbus.cloudsim.UtilizationModelFull
import org.cloudbus.cloudsim.Vm
import org.cloudbus.cloudsim.VmAllocationPolicySimple
import org.cloudbus.cloudsim.VmSchedulerTimeShared
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple

import scala.collection.mutable.ListBuffer

object ahmed_khan_hw1 {

  /*
   * function to convert Scala list to Java list
   */
  private def toJavaList[T](l: List[T]): util.List[T] = {

    val a = new util.ArrayList[T]
    l.map(a.add(_))
    a

  }

  /*
   * function to convert Java list to Scala list
   */
  private def toScalaList[T](l: util.List[T]): List[T] = {

    var a = ListBuffer[T]()
    for (r <- 0 until l.size) a += l.get(r)
    a.toList

  }

  private def employBrokerSimulation(vmList: List[Vm], cloudletList: List[Cloudlet]): Unit = {
    if (vmList.size == cloudletList.size) {
      new DatacenterBroker("Broker IaaS")
    }
    else if (vmList.size < cloudletList.size) {
      new DatacenterBroker("Broker SaaS")
    }
    else {
      new DatacenterBroker("Broker PaaS")
    }
  }

  /*
   * Creates the broker using broker policies by submitting virtual machines and
   * cloudlets according to the specific rules of the simulation
   */
  private def createBroker: DatacenterBroker = {

    try {
      val broker = new DatacenterBroker("Broker")

      broker

    }
    catch {
      case e: Exception =>
        e.printStackTrace()
        return null
    }

  }

  /*
   * Creates the data center based on the values obtained from the configuration file
   * and depending on what model was specified
   * returns a created data center object
   */
  private def createDatacenter(name: String, path: String, config: Config) = {

    /*
     * A Machine contains one or more PEs or CPUs/Cores.
     * create a list based on the number of cores the configuration file has specified
     * 1 core means it's single core, 2 core means it's dual core, and 4 means it's quad core
     */
    // quad core
    val peList = List.tabulate(4/*config.getInt(path + ".core")*/
    )(x => {
      new Pe(x, new PeProvisionerSimple(config.getInt(path + ".mips")))
    })

    // dual core
    val peList2 = List.tabulate(2/*config.getInt(path + ".core")*/
    )(x => {
      new Pe(x, new PeProvisionerSimple(config.getInt(path + ".mips")))
    })

    /*
     * Create hosts and its processing elements to the machines based on the configuration file
     * following the simple ram and bw provision with shared vm time
     */
    val hostList = List(
      new Host(
        0,
        new RamProvisionerSimple(config.getInt(path + ".host.ram")),
        new BwProvisionerSimple(config.getInt(path + ".host.bandwidth")),
        config.getInt(path + ".host.storage"),
        toJavaList(peList),
        new VmSchedulerTimeShared(toJavaList(peList))),

      new Host(
        1,
        new RamProvisionerSimple(config.getInt(path + ".host.ram")),
        new BwProvisionerSimple(config.getInt(path + ".host.bandwidth")),
        config.getInt(path + ".host.storage"),
        toJavaList(peList2),
        new VmSchedulerTimeShared(toJavaList(peList2)))
    )

    /*
     * Create a Data centerCharacteristics object that stores the properties of a data center
     * that was found in the configuration file, including the hosts list
     */
    val characteristics = new DatacenterCharacteristics(
      config.getString("config.dc.characteristics.arch"),
      config.getString("config.dc.characteristics.os"),
      config.getString("config.dc.characteristics.vmm"),
      toJavaList(hostList),
      config.getDouble("config.dc.characteristics.time_zone"),
      config.getDouble("config.dc.characteristics.cost"),
      config.getDouble("config.dc.characteristics.cost_per_memory"),
      config.getDouble("config.dc.characteristics.cost_per_storage"),
      config.getDouble("config.dc.characteristics.cost_per_bandwidth"))

    /*
     * create the data center object using the objects already created and return it
     */
    try {
      val datacenter = new Datacenter(
        name,
        characteristics,
        new VmAllocationPolicySimple(toJavaList(hostList)),
        new util.LinkedList[Storage],
        0);

      datacenter
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }

  }

  /*
   * create a list of VM's based on the VM description found in the configuration file
   * and returns it
   */
  private def createVM(userId: Int, config: Config): List[Vm] = {

    /*
     * Create a virtual machine list that stores the properties of a vm
     * including the cloudlet schedule timer
     */
    val vmList = List.tabulate(config.getInt("config.vm.num_vms")
    )(x => {
      new Vm(
        x,
        userId,
        config.getInt("config.vm.mips"),
        config.getInt("config.vm.num_cpu"),
        config.getInt("config.vm.ram"),
        config.getInt("config.vm.bw"),
        config.getInt("config.vm.size"),
        config.getString("config.vm.vmm"),
        new CloudletSchedulerTimeShared)
    })

    vmList

  }

  /*
   * create a list of cloudlet's based on the cloudlet description found in
   * the configuration file and returns it
   */
  private def createCloudlet(userId: Int, config: Config): List[Cloudlet] = {

    /*
     * Create a cloudlet list that stores the properties of a cloudlet
     * including the utilization model
     */
    val cloudletList = List.tabulate(config.getInt("config.cloudlet.num_cloudlets")
    )(x => {
      new Cloudlet(
        x,
        config.getInt("config.cloudlet.length"),
        config.getInt("config.cloudlet.num_cpu"),
        config.getInt("config.cloudlet.filesize"),
        config.getInt("config.cloudlet.outputsize"),
        new UtilizationModelFull(),
        new UtilizationModelFull(),
        new UtilizationModelFull())

    })

    cloudletList.foreach(x => x.setUserId(userId))

    cloudletList
  }

  /*
   * Main Method
   */
  def main(args: Array[String]): Unit = {


    Log.printLine("Starting Simulation...")


    try {
      // obtain configuration file name from the command line argument and load it
      val config = ConfigFactory.load(args{0})

      // Initialize the CloudSim library
      CloudSim.init(config.getInt("config.num_user"), Calendar.getInstance, false)

      // Second step: Create Datacenters
      val datacenter = createDatacenter("Datacenter_0", "config.dc.dc_0", config)
      val datacenter2 = createDatacenter("Datacenter_1", "config.dc.dc_1", config)

      // Create Broker
      val broker = createBroker
      val brokerId = broker.getId

      // create VMs
      val vmList = createVM(brokerId, config)

      // submit vm list to the broker
      broker.submitVmList(toJavaList(vmList))

      // Create Cloudlets
      val cloudletList = createCloudlet(brokerId, config)

      // submit cloudlet list to the broker
      broker.submitCloudletList(toJavaList(cloudletList))

      // Start the simulation
      CloudSim.startSimulation()
      // Stop the simulation
      CloudSim.stopSimulation()

      // print results when simulation is over
      val newList = broker.getCloudletReceivedList

      printCloudletList(newList)

      Log.printLine("Simulation Ended!")

    } catch {
      case e: Exception =>
        e.printStackTrace()
        Log.printLine("Unwanted errors happen")
    }
  }

  /*
   * Prints the Cloudlet objects.
   */
  private def printCloudletList(list: java.util.List[_ <: Cloudlet]): Unit = {
    val size = list.size
    val indent = "    "
    Log.printLine()
    Log.printLine("========== OUTPUT ==========")
    Log.printLine("Cloudlet ID" + indent + "STATUS" + indent + "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time")
    val dft = new DecimalFormat("###.##")
    var i = 0
    while ( {
      i < size
    }) {
      var cloudlet = list.get(i)
      Log.print(indent + cloudlet.getCloudletId + indent + indent)
      if (cloudlet.getCloudletStatus == Cloudlet.SUCCESS) {
        Log.print("SUCCESS")
        Log.printLine(indent + indent + cloudlet.getResourceId + indent + indent + indent + cloudlet.getVmId + indent + indent + dft.format(cloudlet.getActualCPUTime) + indent + indent + dft.format(cloudlet.getExecStartTime) + indent + indent + dft.format(cloudlet.getFinishTime))
      }
      {
        i += 1;
        i - 1
      }
    }
  }
}