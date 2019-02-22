package io.martinlover.ttt

import io.martinlover.ttt.model.{Black, Board, Player}

package object usecase {

  sealed abstract class Results
  case class Continue(s: Status) extends Results
  case object Finish             extends Results

  case class Status(board: Board = new Board, turn: Player = Black)

}
