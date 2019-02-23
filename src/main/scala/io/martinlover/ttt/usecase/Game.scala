package io.martinlover.ttt.usecase

import com.sun.tools.classfile.Code_attribute.InvalidIndex
import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.model.{Board, Player}

trait Game {
  def drop(s: Status, point: Point): Result
}

class TicTokToe extends Game {

  override def drop(s: Status, point: Point): Result =
    (for {
      _        <- checkReasonableMove(s,  point)
      newBoard <- move(s.board,           point)
      _        <- checkGameOver(newBoard, s.turn)
    } yield Continue(Status(newBoard, Player.switch(s.turn)))) fold (identity, identity)

  protected def checkReasonableMove(status: Status, point: Point): Either[Result, Unit] =
    Either.cond(status.board.isResonableMove(point), (), InvalidInput(status))

  protected def move(board: Board, point: Point): Either[Result, Board] =
    Right(Board.drop(board, point))

  protected def checkGameOver(newBoard: Board, turn: Player): Either[Result, Unit] =
    Either.cond(newBoard.isFinished(), (), Finish)
}
