/*
 * ConfigKeys.scala
 *
 * Singleton object to allow the simulation to retrieve configuration values efficiently in order to
 * achieve separation of concerns
 *
 * This object obtains the necessary values from the configuration file located in the resources directory
 */
object ConfigKeys {


  /*
   * General configuration values for cloud sim initialization
   */
  object General {
    val num_user = "General.num_user"
    val num_cloudlet = "General.num_cloudlet"
    val flag_trace = "General.flag_trace"
    val name_datacenter1 = "General.name_datacenter1"
    val name_datacenter2 = "General.name_datacenter2"
  }


  /*
   * Attributes for the first data center
   */
  object DataCenter1 {
    val arch = "DataCenter1.arch"
    val os = "DataCenter1.os"
    val vmm = "DataCenter1.vmm"
    val hosts = "DataCenter1.hosts"
    val time_zone = "DataCenter1.time_zone"
    val cost = "DataCenter1.cost"
    val cost_per_memory = "DataCenter1.cost_per_memory"
    val cost_per_storage = "DataCenter1.cost_per_storage"
    val cost_per_bandwidth = "DataCenter1.cost_per_bandwidth"
  }


  /*
   * Attributes for each host within the data center
   */
  object Host {
    val pe = "Host.pe"
    val ram = "Host.ram"
    val storage = "Host.storage"
    val bandwidth = "Host.bandwidth"
    val vmCount = "Host.vmCount"
    val mips = "Host.mips"
  }


  /*
   * Attributes for each virtual machine
   */
  object VM {
    val size = "VM.size"
    val ram = "VM.ram"
    val mips = "VM.mips"
    val bw = "VM.bw"
    val pe = "VM.pe"
    val vmm = "VM.vmm"
  }


  /*
   * Attributes for each cloudlet that will be allocated
   */
  object Cloudlet {
    val length = "Cloudlet.length"
    val filesize = "Cloudlet.filesize"
    val outputsize = "Cloudlet.outputsize"
    val pes = "Cloudlet.pes"
    val initialUtilizationPercent = "Cloudlet.initialUtilizationPercent"
  }


}
