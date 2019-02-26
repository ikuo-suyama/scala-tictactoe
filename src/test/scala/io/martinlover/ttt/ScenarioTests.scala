package io.martinlover.ttt

import io.martinlover.ttt.controller._
import io.martinlover.ttt.external.StdIODeviceImpl
import io.martinlover.ttt.usecase.{Game, Status, TicTokToe}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import scalaz.effect.IO
import scalaz.Scalaz._

import scala.collection.mutable.Queue

class ScenarioTests extends Specification with Mockito {

  val device: DeviceAdapter = spy(new StdIODeviceImpl)
  val presenter: Presenter  = new PresenterImpl
  val game: Game            = new TicTokToe
  val app: Application      = new ApplicationImpl(device, presenter, game)

  "シナリオテスト" ^
    "1. 先手の勝ち" ! scenario1 ^
    "2. 後手の勝ち" ! scenario2
  end

  def scenario1 = {
    val scenario: Queue[String] = Queue("0 0", "0 1", "1 1", "2 2", "2 0", "1 0", "0 2")
    device.readInput answers { m: Any =>
      scenario.dequeue().pure[IO]
    }
    val result = app.run().unsafePerformIO()
    there was one(device).writeOutput(contain("Winner: x"), any())
  }
  def scenario2 = success

}
