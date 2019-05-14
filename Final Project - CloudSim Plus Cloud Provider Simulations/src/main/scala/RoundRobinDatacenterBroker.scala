/*
 * RoundRobinDatacenterBroker.scala
 *
 * This object is the implementation of the Round Robin algorithm
 * for handling virtual machine distribution to data center servers.
 * It finds the first available Datacenter that satisfies the requirements
 * and cycles around the hosts until it finds one and then submits a cloudlet to a virtual machine
 *
 */

import org.cloudbus.cloudsim.brokers.DatacenterBrokerAbstract
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.Datacenter
import org.cloudbus.cloudsim.vms.Vm


class RoundRobinDatacenterBroker(cloudSim: CloudSim) extends DatacenterBrokerAbstract(cloudSim) {


  // custom constructor
  initializeBroker()


  /*
   * initialize the virtual machine mapper, data center, and
   * fallback data center for the broker
   */
  private def initializeBroker(): Unit = {

    // set the round robin to the broker's VM mapper
    setVmMapper(defaultVmMapper)

    // select the first data center from the list if it exists, otherwise initialize to null
    setDatacenterSupplier(() => if (getDatacenterList.isEmpty) Datacenter.NULL else getDatacenterList.get(0))

    /*
     * if any datacenter fails to create our required virtual machines,
     * create a policy to retrieve a datacenter in order to host the vm.
     */
    setFallbackDatacenterSupplier(() => Utility.toScalaList(getDatacenterList)
      .find(dataCenter => !getDatacenterRequestedList.contains(dataCenter))
      .getOrElse(Datacenter.NULL))

  }


  /*
   * function returns the index of the next virtual machine if the list isn't empty
   * and -1 if it is empty
   */
  private def getNextVmIndexIfAvailable: Int = {

    if(!getVmExecList.isEmpty) {

      val i = getVmExecList.indexOf(getLastSelectedVm)
      cycleToNextIndex(i)

    }
    else{

      -1

    }

  }


  /*
   * function to cycle to the next index of the list of virtual machines
   */
  private def cycleToNextIndex(i: Int): Int = {

    val nextIndex = i + 1

    // If out of bounds return 0
    if(nextIndex >= getVmExecList.size()) {

      0

    }
    // otherwise return the next index
    else {

      nextIndex

    }

  }


  /*
   * function to get the next VM that is available within the list if the cloudlet needs a binding
   */
  override def defaultVmMapper(cloudlet: Cloudlet): Vm = {

    if(cloudlet.isBindToVm) {

      if(cloudlet.getVm.isCreated && cloudlet.getVm.getBroker.equals(this)) {

        cloudlet.getVm
      }
      else {
        Vm.NULL
      }

    }
    else {
      getVmFromCreatedList(getNextVmIndexIfAvailable)
    }

  }


}
