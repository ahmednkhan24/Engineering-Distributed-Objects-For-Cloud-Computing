/*
 * Simulation1.scala
 *
 * Random Load Balancing Simulation.
 * This simulation implements a custom Virtual Machine Space Allocation Policy
 * that randomly selects available hosts and places virtual machines within them.
 *
 */

import java.util.{Comparator, Optional}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.log4j.{BasicConfigurator, Logger}
import org.cloudbus.cloudsim.allocationpolicies.{VmAllocationPolicy, VmAllocationPolicySimple}
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.cloudlets.{Cloudlet, CloudletSimple}
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.{Datacenter, DatacenterSimple}
import org.cloudbus.cloudsim.distributions.{ContinuousDistribution, UniformDistr}
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic
import org.cloudbus.cloudsim.vms.{Vm, VmSimple}
import org.cloudsimplus.builders.tables.CloudletsTableBuilder


class Simulation1(private val configFileName: String) {

  // instantiate logger object
  private val logger: Logger = Logger.getLogger(this.getClass.getName)
  private val len: Int = 9000
  // number generator that returns values between [-1 .. 1]
  private val random: ContinuousDistribution = new UniformDistr(-1, 2)
  private val config: Config = ConfigFactory.load(configFileName)


  /*
   * function to create a data center needed for the simulation based on the configuration file
   */
  def createDatacenter(simulation: CloudSim): DatacenterSimple = {

    logger.info("Creating Datacenter")

    // grab the necessary values from the configuration file
    val hosts: Int = config.getInt(ConfigKeys.DataCenter1.hosts)
    val pe: Int = config.getInt(ConfigKeys.Host.pe)
    val mips: Int = config.getInt(ConfigKeys.Host.mips)
    // in Megabits
    val bandwidth: Long = config.getInt(ConfigKeys.Host.bandwidth)
    // in Megabytes
    val ram: Int = config.getInt(ConfigKeys.Host.ram)
    val storage: Long = config.getInt(ConfigKeys.Host.storage)

    // create the list of hosts for the data center
    val hostList: List[Host] = List.tabulate(hosts)(_ => createHost(pe, mips, ram, bandwidth, storage))

    // use a simple vm allocation policy for this simulation
    val vmAllocationPolicy: VmAllocationPolicySimple = new VmAllocationPolicySimple()

    // Replaces the default method that allocates Hosts to VMs by our own implementation
    vmAllocationPolicy.setFindHostForVmFunction(findRandomSuitableHostForVm)

    // Uses a VmAllocationPolicySimple by default to allocate VMs
    val dataCenter: DatacenterSimple = new DatacenterSimple(simulation, Utility.toJavaList(hostList), vmAllocationPolicy)

    // set the configuration characteristics of the data center specified by the configuration file
    dataCenter.getCharacteristics
      .setOs(config.getString(ConfigKeys.DataCenter1.os))
      .setTimeZone(config.getDouble(ConfigKeys.DataCenter1.time_zone))
      .setVmm(config.getString(ConfigKeys.DataCenter1.vmm))

    logger.info("Created Datacenter")

    dataCenter

  }


  /*
   * Define a specific policy to randomly select a suitable Host to place a given VM
   * by filtering through all available hosts.
   */
  def findRandomSuitableHostForVm(vmAllocationPolicy: VmAllocationPolicy, vm: Vm): Optional[Host] = {

    vmAllocationPolicy
      .getHostList[Host].stream()
      .filter((host: Host) => host.isSuitableForVm(vm))
      .sorted(Comparator.comparingDouble(_ => random.sample))
      .findAny

  }


  /*
   * function to create the necessary amount of hosts
   * needed for the simulation and returns them as a scala list
   */
  def createHost(hostPes: Int, mipsCapacity: Int, ram: Long, bw: Long, storage: Long): HostSimple = {

    logger.info("Creating Hosts")

    // creates a list of Host processing elements based on the amount of CPUs specified
    val peList: List[Pe] = List.tabulate(hostPes)(_ => new PeSimple(mipsCapacity))

    /*
     * Uses ResourceProvisionerSimple by default for RAM and BW provisioning
     * and VmSchedulerSpaceShared for VM scheduling.
     */
    val host = new HostSimple(ram, bw, storage, Utility.toJavaList(peList))

    logger.info("Created Hosts")

    host

  }


  /*
   * function to create the necessary amount of cloudlets needed for the simulation and returns
   * them as a scala list
   */
  def createCloudlets(): List[Cloudlet] = {

    logger.info("Creating Cloudlets")

    // grab the necessary values from the configuration file
    val cloudlets: Int = config.getInt(ConfigKeys.General.num_cloudlet)
    val percent: Double = config.getDouble(ConfigKeys.Cloudlet.initialUtilizationPercent)
    val length: Int = config.getInt(ConfigKeys.Cloudlet.length)
    val sizes: Int = config.getInt(ConfigKeys.Cloudlet.filesize)
    val pes: Int = config.getInt(ConfigKeys.Cloudlet.pes)


    /*
     * create a dynamic utilization model with the configured percent value
     * the utilization model allows the simulation to increase/decreases resources as needed
     */
    val utilizationModel: UtilizationModelDynamic = new UtilizationModelDynamic(percent)

    // instantiate and configure the list of cloudlets
    val cloudletList: List[Cloudlet] = List.tabulate(cloudlets)(_ => {

      val cl = new CloudletSimple(length+len, pes, utilizationModel)
      cl.setSizes(sizes)
      cl

    })

    logger.info("Created Cloudlets")

    cloudletList

  }


  /*
   * function to create the necessary amount of virtual machines
   * needed for the simulation and returns them as a scala list
   */
  def createVms(): List[Vm] = {

    logger.info("Creating Virtual Machines")

    // grab the necessary values from the configuration file
    val vms: Int = config.getInt(ConfigKeys.Host.vmCount)
    val mips: Int = config.getInt(ConfigKeys.VM.mips)
    val pes: Int = config.getInt(ConfigKeys.VM.pe)
    val ram: Int = config.getInt(ConfigKeys.VM.ram)
    val bw: Int = config.getInt(ConfigKeys.VM.bw)
    val size: Int = config.getInt(ConfigKeys.VM.size)

    // instantiate and configure the list of virtual machines
    val vmList: List[Vm] = List.tabulate(vms)(_ => {

      val vm = new VmSimple(mips, pes)
      vm.setRam(ram).setBw(bw).setSize(size)
      vm

    })

    logger.info("Created Virtual Machines")

    vmList

  }


//  def monthlyCost(characteristics: DatacenterCharacteristics, ram: Int, storage: Int, bw: Int ): Double = {
//    cost = (characteristics.getCostPerBw*bw + characteristics.getCostPerMem*ram + characteristics.getCostPerStorage*storage)*2000000
//    cost
//  }

  /*
   * driver method that handles the execution of the simulation
   */
  def runSimulation(): Unit = {

    // initialize logger
    BasicConfigurator.configure()
    logger.info("CS 441 Course Project: Simulation 1 Random Load Balancing")
    // instantiate the cloud sim object and the data center
    val simulation: CloudSim = new CloudSim()
    val datacenter: Datacenter = createDatacenter(simulation)

    // Create a broker that is a software acting on behalf a cloud customer to manage his/her VMs and Cloudlets
    val broker: DatacenterBrokerSimple = new DatacenterBrokerSimple(simulation)

    // obtain a a list of virtual machines and cloudlets specified by the configuration file
    val vmList: List[Vm] = createVms()
    val cloudletList: List[Cloudlet] = createCloudlets()

    // submit the lists to the broker
    broker.submitVmList(Utility.toJavaList(vmList))
    broker.submitCloudletList(Utility.toJavaList(cloudletList))

    logger.info("Starting simulation 1")

    simulation.start()

    logger.info("Ending simulation 1")

    // collect results of the simulation and output it
    val finishedCloudlets = broker.getCloudletFinishedList
    new CloudletsTableBuilder(finishedCloudlets).build()

    // calculate the cost of the simulation
    logger.info("Calculating simulation cost")

    val bwCost = config.getDouble("DataCenter1.cost_per_bandwidth")
    val memoryCost = config.getDouble("DataCenter1.cost_per_memory")
    val storageCost = config.getDouble("DataCenter1.cost_per_storage")

    val bw = config.getInt("Host.bandwidth")
    val memory = config.getInt("Host.ram")
    val storage = config.getInt("Host.storage")

    val cost = Utility.calculateCost(bwCost, bw, memoryCost, memory, storageCost, storage)

    logger.info("Total cost: $" + cost)

  }

}
