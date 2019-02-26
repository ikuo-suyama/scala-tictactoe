package io.martinlover.ttt

import io.martinlover.ttt.model.{Black, Board, Player}

package object usecase {

  sealed trait Result {
    val s: Status
  }
  sealed abstract class Continue(s: Status) extends Result
  sealed abstract class Finish(s: Status)   extends Result

  case class Next(s: Status)         extends Continue(s)
  case class InvalidInput(s: Status) extends Continue(s)
  case class IllegalMove(s: Status)  extends Continue(s)
  case class Draw(s: Status)         extends Finish(s)
  case class Ending(s: Status)       extends Finish(s)

  case class Status(board: Board = new Board, turn: Player = Black)

}
