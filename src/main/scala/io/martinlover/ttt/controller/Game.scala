package io.martinlover.ttt.controller

import io.martinlover.ttt.model.{Board, Player}
import scalaz.{-\/, \/, \/-}
import scalaz.effect.IO
import scalaz.effect.IO._

class Game {
  def play(s: Status): IO[Status \/ Unit] =
    for {
      _               <- putStr(s"Player ${1}: ")
      input           <- readLn
      result: Results <- controller.drop(s, input)
      -               <- putStrLn(displayResults(result))
    } yield {
      result match {
        case Continue(s) => -\/(s)
        case Finish      => \/-()
      }
    }
  def displayResults(r: Results): String = "Finish"
}

sealed abstract class Results

case class Continue(s: Status) extends Results

case object Finish extends Results

case class Status(board: Board, turn: Player)
