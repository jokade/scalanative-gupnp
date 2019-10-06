package gssdp

import glib.{GError, guint, gulong}
import gobject.GObject

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * SSDP "bus" wrapper.
 *
 * @see [[https://developer.gnome.org/gssdp/unstable/GSSDPClient.html]]
 */
@CObj(prefix = "gssdp_client_")
class GSSDPClient extends GObject {
  def active: Boolean = getBooleanProp(c"active")
  def active_=(f: Boolean): Unit = setBooleanProp(c"active",f)

  def hostIP: String = getStringProp(c"host-ip")
  def hostIP_=(ip: String): Unit = (c"host-ip",ip)

  def interface: String = getStringProp(c"interface")
  def interface_=(iface: String): Unit = setStringProp(c"interface",iface)

  def network: String = getStringProp(c"network")
  def network_=(network: String): Unit = setStringProp(c"network",network)

  def serverId: String = getStringProp(c"server-id")
  def serverId_=(id: String): Unit = setStringProp(c"server-id",id)

  def socketTtl: guint = getUIntProp(c"socket-ttl")
  def socketTtl_=(ttl: guint): Unit = setUIntProp(c"socket-ttl",ttl)

//  def onMessageReceived(handler: ()=>Unit)(implicit refZone: RefZone): gulong = connect0(c"message-received",handler)
}
object GSSDPClient {
  @name("gssdp_client_new")
  def create(mainCtx: Ptr[Byte], iface: CString)(implicit error: ResultPtr[GError]): GSSDPClient = extern

  def apply(): GSSDPClient = create(null,null)(null)
}
