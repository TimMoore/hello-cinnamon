package com.example.hellostream.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.HelloStreamService
import com.example.hello.api.HelloService

import scala.concurrent.{ ExecutionContext, Future }

/**
  * Implementation of the HelloStreamService.
  */
class HelloStreamServiceImpl(helloService: HelloService)(implicit ec: ExecutionContext) extends HelloStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(helloService.hello(_).invoke()))
  }

  override def hello2(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    helloService.hello(id).invoke(NotUsed).map("Hello service said: " + _)
  }
}
