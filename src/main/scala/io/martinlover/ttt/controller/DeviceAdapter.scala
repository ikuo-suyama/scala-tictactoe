package io.martinlover.ttt.controller

import scalaz.effect.IO

trait DeviceAdapter {
  def readInput(): IO[String]
  def writeOutput(s: String, ln: Boolean = true): IO[Unit]
}
