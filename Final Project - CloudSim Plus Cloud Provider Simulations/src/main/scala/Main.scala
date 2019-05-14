/*
 * Driver object that will be invoked by Docker
 */

object Main {

  /*
   * Main driver method
   */
  def main(args: Array[String]): Unit = {

    /*
     * Initiating simulation 1 which involves random load balancing.
     * The Virtual Machine Allocation Policy randomly assigns VMs to random hosts.
     *
     * In doing so, this creates a suboptimal solution in certain situations which require a more
     * elegant algorithm for virtual machine allocation.
     */
    val configFile: String = "configuration1.conf"
    val simulation1Random: Simulation1 = new Simulation1(configFile)

    simulation1Random.runSimulation()


    println()
    println("==========================================================================================================")
    println("==========================================================================================================")
    println("==========================================================================================================")
    println()


    /*
     * Initiating simulation 2 which utilizes two load balancers.
     *
     * The first load balancer consists of a Virtual Machine Allocation Policy that allocates Virtual
     * Machines to the available host that contains the least amount of active Process Elements.
     *
     * In doing so, this optimizes each Virtual Machine into the host with the least amount of Process
     * elements that satisfies the Virtual Machine.
     *
     * The second load balancer implements the Round Robin algorithm within the datacenter broker.
     * The Round Robin algorithm cycles through all hosts within each datacenter and delegates to the
     * best available host within the Virtual Machines.
     *
     */
    val simulation2RoundRobin = new Simulation2(configFile)

    simulation2RoundRobin.runSimulation()

  }

}
