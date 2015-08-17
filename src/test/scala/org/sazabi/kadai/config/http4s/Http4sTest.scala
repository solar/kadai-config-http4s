package org.sazabi.kadai.config.http4s

import kadai.config._, ConfigResult.syntax, Configuration._
import org.http4s._
import scalaprops._, Property.forAll
import scalaz.std.string._

// @TODO need much more tests
object Http4sTest extends Scalaprops {
  import imports._

  val config = Configuration.from("""
    uri = "http://google.jp"
    invalid-uri = "booooo//;:boo/"

    status = 200
    invalid-status = 1200

    charset = "UTF-8"
  """)

  val `k.c.C.Accessor[o.h.Uri]` = {
    val uri = Uri.uri("http://google.jp")

    Property.prop {
      ConfigResult.get[Uri]("uri").extractId(config).map(_ == uri).getOrElse(false)
    }
  }

  val `k.c.C.Accessor[o.h.Status]` = {
    Property.prop {
      ConfigResult.get[Status]("status").extractId(config).isRight
    }
  }

  val `k.c.C.Accessor[o.h.Charset]` = {
    Property.prop {
      ConfigResult.get[Charset]("charset").extractId(config).isRight
    }
  }
}
