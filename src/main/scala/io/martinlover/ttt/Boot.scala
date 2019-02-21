import scalaz.{-\/, \/, \/-}
import scalaz.effect.{IO, SafeApp}
import scalaz.effect.IO._

object MyApp extends SafeApp {

  override def runc: IO[Unit] = myAppLogic

  def myAppLogic: IO[Unit] =
    IO.tailrecM(io)(0)

  def io(i: Int): IO[Int \/ Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- readLn
      _ <- putStrLn("Hello, " + n + ", good to meet you!")
    } yield {
      if (i >= 3) \/-()
      else -\/(i + 1)
    }
}
