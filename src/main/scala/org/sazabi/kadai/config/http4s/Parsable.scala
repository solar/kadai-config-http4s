package org.sazabi.kadai.config.http4s

import org.http4s._
import scalaz._

trait Parsable[A, B] {
  def parse(s: A): ParseResult[B]

  def unsafe(s: A): B = parse(s).valueOr(f => throw ParseException(f))
}

trait Parsables {
  implicit val uriParsable: Parsable[String, Uri] = Parsable.build(Uri.fromString)

  implicit val methodParsable: Parsable[String, Method] = Parsable.build(Method.fromString)

  implicit val httpVersionParsable: Parsable[String, HttpVersion] =
    Parsable.build(HttpVersion.fromString)

  implicit val statusParsable: Parsable[Int, Status] = Parsable.build(Status.fromInt)

  implicit val charsetParsable: Parsable[String, Charset] = Parsable.build(Charset.fromString)
}

object Parsable extends Parsables {
  def apply[A, B](implicit ev: Parsable[A, B]): Parsable[A, B] = ev

  def build[A, B](f: A => ParseResult[B]): Parsable[A, B] = new Parsable[A, B] {
    def parse(s: A) = f(s)
  }
}
