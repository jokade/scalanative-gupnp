package gupnp

import gssdp.{DiscoveredResource, GSSDPClient, GSSDPResourceBrowser}

import scalanative._
import unsafe._
import glib.GMainLoop

object GUPnPBrowser {
  def main(args: Array[String]): Unit = {
    import scala.scalanative.interop.RefZone.Implicits.Global

    val client = GSSDPClient()
    val browser = GSSDPResourceBrowser(client,c"ssdp:all")
    browser.active = true
    browser.onResourceAvailable((service: DiscoveredResource) => {
      println(service)
    })

    val loop = GMainLoop()
    loop.run()
    client.unref()
    browser.unref()
    loop.unref()
    println("OK")
  }
}
