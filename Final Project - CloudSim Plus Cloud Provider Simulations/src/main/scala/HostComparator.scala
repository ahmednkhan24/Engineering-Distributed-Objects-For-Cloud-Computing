
/*
 * HostComparator.scala
 *
 * Comparator that sorts the hosts by the least host activity
 */

import java.util.Comparator
import org.cloudbus.cloudsim.hosts.Host

class HostComparator extends Comparator[Host] {

  override def compare(o1: Host, o2: Host): Int = {

    val host1Active: Int = getActiveNum(o1)
    val host2Active: Int = getActiveNum(o2)
    host1Active.compareTo(host2Active)

  }

  /*
  * Function that represents a hosts activity by 1 or 0
  */
  private def getActiveNum(host: Host): Int = {

    if(host.isActive) 1 else 0

  }

}