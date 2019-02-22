package io.martinlover.ttt

import io.martinlover.ttt.model.{Black, Board, Player}

package object usecase {

  sealed abstract class Result
  case class Continue(s: Status)         extends Result
  case class InvalidInput(val s: Status) extends Result
  case object Finish                     extends Result

  case class Status(board: Board = new Board, turn: Player = Black)

}
