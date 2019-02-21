import scalaz.effect.{IO, SafeApp}
import scalaz.effect.IO._

object MyApp extends SafeApp {

  override def runc: IO[Unit] = myAppLogic

  def myAppLogic: IO[Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- readLn
      _ <- putStrLn("Hello, " + n + ", good to meet you!")
    } yield ()
}
