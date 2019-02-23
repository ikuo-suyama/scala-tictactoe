package io.martinlover.ttt.external

import io.martinlover.ttt.controller.DeviceAdapter
import scalaz.effect.IO

class StdIODeviceImpl extends DeviceAdapter {
  override def readInput(): IO[String] = IO.readLn

  override def writeOutput(s: String, ln: Boolean = true): IO[Unit] =
    if (ln) IO.putStrLn(s) else IO.putStr(s)
}
