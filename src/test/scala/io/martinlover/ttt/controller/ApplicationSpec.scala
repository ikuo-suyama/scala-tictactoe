package io.martinlover.ttt.controller

import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.usecase.{Continue, Finish, Game, Status}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import scalaz.effect.IO
import scalaz.{-\/, \/-}

class ApplicationSpec extends Specification with ApplicationSpecHelper {

  "turn" should {
    "Return Left when player Input is invalid" in {
      val (device, game, app) = createMocks()
      val c                   = Status()

      device.readInput() returns IO { "a" }

      game.drop(any(), any()) returns Finish

      val result = app.turn(c).unsafePerformIO()

      result must beEqualTo(-\/(c))
    }

    "Return Right when player Input is valid and Game said Finish" in {
      val (device, game, app) = createMocks()

      val c = Status()

      device.readInput() returns IO { "1 1" }

      game.drop(c, Point(1, 1)) returns Finish

      val result = app.turn(c).unsafePerformIO()

      result must beEqualTo(\/-())
    }

    "Return Left when game said Continue" in {
      val (device, game, app) = createMocks()

      val c = Status()
      val n = Status()

      device.readInput() returns IO { "1 2" }

      game.drop(c, Point(1, 2)) returns Continue(n)

      val result = app.turn(c).unsafePerformIO()

      result must beEqualTo(-\/(n))
    }
  }

}

trait ApplicationSpecHelper extends Mockito {
  def createMocks() = {
    val device: DeviceAdapter = mock[DeviceAdapter]
    val game: Game            = mock[Game]
    val presenter: Presenter  = mock[Presenter].smart
    val app                   = new ApplicationImpl(device, presenter, game)

    device.writeOutput(any(), any()) returns IO.ioUnit

    (device, game, app)
  }
}
