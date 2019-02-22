import _root_.io.martinlover.ttt.controller.{Game, StdIOGameImpl}
import io.martinlover.ttt.usecase.{Rule, TicTokToe}
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  val rule: Rule = new TicTokToe
  val game: Game = new StdIOGameImpl(rule)

  override def runc: IO[Unit] = game.play()

}
