import _root_.io.martinlover.ttt.controller.Game
import io.martinlover.ttt.model.Board
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  override def runc: IO[Unit] =
    IO.tailrecM(new Game().play)(new Board())

}
