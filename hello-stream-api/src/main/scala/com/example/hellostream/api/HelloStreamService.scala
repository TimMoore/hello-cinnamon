package com.example.hellostream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The Hello stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HelloStream service.
  */
trait HelloStreamService extends Service {
  def hello2(id: String): ServiceCall[NotUsed, String]

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("hello-stream")
      .withCalls(
        pathCall("/api/hello2/:id", hello2 _),
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

