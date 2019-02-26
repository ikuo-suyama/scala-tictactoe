package io.martinlover.ttt.usecase

import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.model.{Board, Player}

trait Game {
  def drop(s: Status, point: Point): Result
}

class TicTokToe extends Game {

  override def drop(s: Status, point: Point): Result =
    (for {
      _        <- checkReasonableMove(s,  point)
      newBoard <- move(s,                 point)
      _        <- checkGameOver(newBoard, s.turn)
    } yield Next(Status(newBoard, Player.switch(s.turn)))) fold (identity, identity)

  protected def checkReasonableMove(status: Status, point: Point): Either[Result, Unit] =
    Either.cond(status.board.isReasonableMove(point), (), IllegalMove(status))

  protected def move(status: Status, point: Point): Either[Result, Board] =
    Right(Board.drop(status.board, status.turn, point))

  protected def checkGameOver(newBoard: Board, turn: Player): Either[Result, Unit] =
    Either.cond(newBoard.isContinuable, (), Ending(Status(newBoard, turn)))
}
