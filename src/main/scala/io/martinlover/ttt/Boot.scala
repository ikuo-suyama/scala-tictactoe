import _root_.io.martinlover.ttt.controller.Game
import scalaz.effect.{IO, SafeApp}

object Boot extends SafeApp {

  override def runc: IO[Unit] =
    IO.tailrecM(new Game().play)(0)

}
