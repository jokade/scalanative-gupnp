package gssdp

import glib.{GList, GString, gulong}
import gobject.GObject

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * Class handling resource discovery.
 *
 * @see [[https://developer.gnome.org/gssdp/unstable/GSSDPResourceBrowser.html]]
 */
@CObj(prefix = "gssdp_resource_browser_")
class GSSDPResourceBrowser extends GObject {
  def active: Boolean = getBooleanProp(c"active")
  def active_=(f: Boolean): Unit = setBooleanProp(c"active",f)

  def rescan(): Boolean = extern

  //def onResourceAvailable(f: Function4[Ptr[Byte],Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit](implicit refZone: RefZone): gulong = connect4(c"resource-available",f)

  def onResourceAvailable(f: (DiscoveredResource)=>Unit)(implicit refZone: RefZone): gulong = {
    val handler = (browser: Ptr[Byte], usn: CString, locationsPtr: Ptr[Byte], userData: Ptr[Byte]) => {
      val locations = new GList[GString](locationsPtr).asScala.map(_.toString)
      f(DiscoveredResource(fromCString(usn),locations))
    }
    connect4(c"resource-available",handler)
  }
}
object GSSDPResourceBrowser {
  @name("gssdp_resource_browser_new")
  def apply(client: GSSDPClient, target: CString): GSSDPResourceBrowser = extern
}
