import java.util

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.log4j.BasicConfigurator
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.resources.Pe
import org.cloudbus.cloudsim.vms.Vm
import org.scalatest.FunSuite


class Tests extends FunSuite {


  /*
   * Utility tests
   */
  test("Utility.toJavaList should convert Scala List to Java") {

    val scalaNumberList: List[Int] = List.tabulate(10)(_ + 1)
    val javaNumberList = Utility.toJavaList(scalaNumberList)

    assert(javaNumberList.isInstanceOf[util.List[Int]])

  }


  test("Utility.toScalaList should convert Java List to Java") {

    val javaNumberList: util.List[Int] = util.Arrays.asList(1, 2, 3, 4, 5)
    val scalaNumberList = Utility.toScalaList(javaNumberList)

    assert(scalaNumberList.isInstanceOf[List[Int]])

  }


  /*
   * ConfigKeys test
   */
  test("ConfigKeys.Cloudlet.length should retrieve the length of the cloudlet as an integer") {

    val cloudletLengthShould: Int = 100000
    val configFileName: String = "configuration1.conf"
    val config: Config = ConfigFactory.load(configFileName)
    val cloudletLengthActual = config.getInt(ConfigKeys.Cloudlet.length)

    assert(cloudletLengthActual === cloudletLengthShould)

  }


  test("ConfigKeys.DataCenter1.cost should retrieve the correct cost of the datacenter as a double") {

    val dataCenterCostShould: Double = 3.0
    val configFileName: String = "configuration1.conf"
    val config: Config = ConfigFactory.load(configFileName)
    val dataCenterCostActual = config.getDouble(ConfigKeys.DataCenter1.cost)

    assert(dataCenterCostActual === dataCenterCostShould)

  }


  /*
   * Host Comparator test
   */
  test("HostComparator should have first two hosts being inactive") {

    val emptyPeList: util.List[Pe] = new util.ArrayList[Pe]()

    val hosts: util.List[Host] = util.Arrays.asList(
      new HostSimple(emptyPeList, false),
      new HostSimple(emptyPeList, true),
      new HostSimple(emptyPeList, false),
      new HostSimple(emptyPeList, true)
    )

    val hostComparator = new HostComparator()
    hosts.sort(hostComparator)

    assert(!hosts.get(0).isActive)
    assert(!hosts.get(1).isActive)

  }


  test("HostComparator should have last two hosts being active") {

    val emptyPeList: util.List[Pe] = new util.ArrayList[Pe]()

    val hosts: util.List[Host] = util.Arrays.asList(
      new HostSimple(emptyPeList, false),
      new HostSimple(emptyPeList, true),
      new HostSimple(emptyPeList, false),
      new HostSimple(emptyPeList, true)
    )

    val hostComparator = new HostComparator()
    hosts.sort(hostComparator)

    assert(hosts.get(2).isActive)
    assert(hosts.get(3).isActive)

  }



  /*
   * Simulation 1 tests
   */
  test("Simulation1 should build a datacenter") {

    BasicConfigurator.configure()
    val configFileName: String = "configuration1.conf"
    val simulation1: Simulation1 = new Simulation1(configFileName)
    val cloudSim: CloudSim = new CloudSim()

    val datacenter: DatacenterSimple = simulation1.createDatacenter(cloudSim)

    assert(datacenter !== null)

  }


  test("Simulation1 should build as many cloudlets as the config file says") {

    BasicConfigurator.configure()
    val configFileName: String = "configuration1.conf"
    val simulation1: Simulation1 = new Simulation1(configFileName)

    val config: Config = ConfigFactory.load(configFileName)
    val cloudletsExpected = config.getInt(ConfigKeys.General.num_cloudlet)

    val cloudlets: List[Cloudlet] = simulation1.createCloudlets()

    assert(cloudlets.size === cloudletsExpected)

  }


  test("Simulation1 should build as many VMs as the config file says") {

    BasicConfigurator.configure()
    val configFileName: String = "configuration1.conf"
    val simulation1: Simulation1 = new Simulation1(configFileName)

    val config: Config = ConfigFactory.load(configFileName)
    val vmCountExpected = config.getInt(ConfigKeys.Host.vmCount)

    val vmList: List[Vm] = simulation1.createVms()

    assert(vmList.size === vmCountExpected)

  }


}


