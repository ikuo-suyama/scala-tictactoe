package io.martinlover.ttt.controller

import scalaz.{-\/, \/, \/-}
import scalaz.effect.IO
import scalaz.effect.IO._
import _root_.io.martinlover.ttt.usecase._
import scalaz.Scalaz._

trait Game {
  def play(): IO[Unit]
}
class StdIOGameImpl(rule: Rule) extends Game {

  override def play(): IO[Unit] =
    IO.tailrecM(turn)(new Status())

  protected def turn(s: Status): IO[Status \/ Unit] =
    for {
      _      <- putStr(s"Player ${s.turn.displayName}: ")
      input  <- readLn
      result <- rule.drop(s, input).pure[IO]
      -      <- putStrLn(displayResults(result))
    } yield {
      result match {
        case Continue(s) => -\/(s)
        case Finish      => \/-()
      }
    }
  def displayResults(r: Results): String = "Finish"
}
