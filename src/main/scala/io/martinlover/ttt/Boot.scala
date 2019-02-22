import _root_.io.martinlover.ttt.controller.{Application, StdIOApplicationImpl}
import io.martinlover.ttt.usecase.{Game, TicTokToe}
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  val game: Game       = new TicTokToe
  val app: Application = new StdIOApplicationImpl(game)

  override def runc: IO[Unit] = app.run()

}
