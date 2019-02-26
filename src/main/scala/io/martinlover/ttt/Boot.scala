import io.martinlover.ttt.controller._
import io.martinlover.ttt.external.StdIODeviceImpl
import io.martinlover.ttt.usecase.{Game, TicTokToe}
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  val device: DeviceAdapter = new StdIODeviceImpl
  val presenter: Presenter  = new PresenterImpl
  val game: Game            = new TicTokToe
  val app: Application      = new ApplicationImpl(device, presenter, game)

  override def runc: IO[Unit] = app.run()

}
