package io.martinlover.ttt.controller

import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.model.Player
import io.martinlover.ttt.usecase._
import scalaz.Scalaz._
import scalaz.effect.IO
import scalaz.effect.IO._
import scalaz.{-\/, \/, \/-}

trait Application {
  def run(): IO[Unit]
}
class StdIOApplicationImpl(game: Game) extends Application {

  override def run(): IO[Unit] =
    IO.tailrecM(turn)(Status())

  protected def turn(s: Status): IO[Status \/ Unit] =
    for {
      pi     <- playerInput(s.turn)
      result <- move(s, pi)
      _      <- displayResults(result)
    } yield {
      result match {
        case Continue(s)     => -\/(s)
        case InvalidInput(s) => -\/(s)
        case Finish          => \/-()
      }
    }

  protected def playerInput(player: Player): IO[Option[Point]] =
    for {
      _     <- putStr(s"Player ${player.displayName}: ")
      input <- readLn
    } yield validate(input)

  private final val point = """(\d) (\d)""".r
  protected def validate(in: String): Option[Point] = in match {
    case point(i, j) => Point(i.toInt, j.toInt).some
    case _           => None
  }

  protected def move(s: Status, maybePoint: Option[Point]): IO[Result] =
    maybePoint match {
      case Some(p) => game.drop(s, p).pure[IO]
      case _       => IO { InvalidInput(s) }
    }

  protected def displayResults(result: Result): IO[Unit] = result match {
    case Continue(_)     => ioUnit
    case InvalidInput(_) => putStrLn("Invalid Input. please input n␣n format:EX. 0 0")
    case Finish          => putStrLn("Winner: x")
  }

}
