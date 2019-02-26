import io.martinlover.ttt.controller.{Application, ApplicationImpl, DeviceAdapter, Presenter}
import io.martinlover.ttt.external.{StdIODeviceImpl, StdIOPresenterImpl}
import io.martinlover.ttt.usecase.{Game, TicTokToe}
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  val device: DeviceAdapter = new StdIODeviceImpl
  val presenter: Presenter  = new StdIOPresenterImpl
  val game: Game            = new TicTokToe
  val app: Application      = new ApplicationImpl(device, presenter, game)

  override def runc: IO[Unit] = app.run()

}
