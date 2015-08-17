package org.sazabi.kadai.config.http4s

import com.typesafe.config.Config
import kadai.config._, Configuration.Accessor
import org.http4s._
import scalaz._

trait LowPriorityAccessors1 {
  implicit def strParsableAccessor[A](implicit ev: Parsable[String, A]): Accessor[A] =
    Accessor[String].map(ev.unsafe)
}

trait LowPriorityAccessors extends LowPriorityAccessors1 {
  implicit def intParsableAccessor[A](implicit ev: Parsable[Int, A]): Accessor[A] =
    Accessor[Int].map(ev.unsafe)
}

trait Http4sAccessors extends LowPriorityAccessors {
  implicit val mediaRangeAccessor: Accessor[MediaRange] = new Accessor[MediaRange] {
    def apply(c: Config, s: String) = MediaRange.fromKey(c.getString(s))
  }

  implicit val mediaTypeAccessor: Accessor[MediaType] = new Accessor[MediaType] {
    def apply(c: Config, s: String) = {
      val value = c.getString(s)
      val parts = value.split('/')
      if (parts.length < 2 || parts(0) == "*" || parts(1) == "*") {
        throw new IllegalArgumentException(s"$value is not a valid media-type")
      } else new MediaType(parts(0), parts(1))
    }
  }
}
