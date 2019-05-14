/*
 * LoadBalancingVmAllocationPolicy.scala
 *
 * Load Balancing Vm Allocation.
 * Allocates the VMs to the host that has the least amount Processing Elements.
 */

import java.util.{Comparator, Optional, stream}
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyAbstract
import org.cloudbus.cloudsim.hosts.Host
import org.cloudbus.cloudsim.vms.Vm


class LoadBalancingVmAllocationPolicy extends VmAllocationPolicyAbstract {


  /*
   * Finds the hosts with the smallest amount of process elements.
   */
  override def defaultFindHostForVm(vm: Vm): Optional[Host] = {

    getStreamIfParallelHostEnabled
      .filter(host => host.isSuitableForVm(vm))
      .min(getActiveComparator)

  }


  /*
   * grabs the parallel stream if it is enabled
   */
  private def getStreamIfParallelHostEnabled: stream.Stream[Host] = {

    if(isParallelHostSearchEnabled) {

      getHostList[Host]().stream().parallel()

    }
    else {

      getHostList[Host]().stream()

    }

  }


  /*
   * Reversing the active hosts in order to get the hosts with minimum number of pes available
   */
  private def getActiveComparator: Comparator[Host] = {

      new HostComparator().thenComparingLong(_.getFreePesNumber)

  }

}
