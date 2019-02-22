package io.martinlover.ttt.controller

import scalaz.{-\/, \/, \/-}
import scalaz.effect.IO
import scalaz.effect.IO._
import _root_.io.martinlover.ttt.usecase._
import _root_.io.martinlover.ttt.model.Player
import scalaz.Scalaz._

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
        case Continue(s) => -\/(s)
        case Finish      => \/-()
      }
    }

  protected def playerInput(player: Player): IO[String] =
    for {
      _     <- putStr(s"Player ${player.displayName}: ")
      input <- readLn
    } yield input

  protected def move(s: Status, playerInput: String): IO[Result] = game.drop(s, playerInput).pure[IO]

  protected def displayResults(result: Result): IO[Unit] = putStrLn("Fin.")

}
