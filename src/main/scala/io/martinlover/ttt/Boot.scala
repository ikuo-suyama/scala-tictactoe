import io.martinlover.ttt.controller.{Application, ApplicationImpl, DeviceAdapter}
import io.martinlover.ttt.external.StdIODeviceImpl
import io.martinlover.ttt.usecase.{Game, TicTokToe}
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  val device: DeviceAdapter = new StdIODeviceImpl
  val game: Game            = new TicTokToe
  val app: Application      = new ApplicationImpl(device, game)

  override def runc: IO[Unit] = app.run()

}
