package io.martinlover.ttt.usecase

import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.model.{Board, Player}

trait Game {
  def drop(s: Status, point: Point): Result
}

class TicTokToe extends Game {

  override def drop(s: Status, point: Point): Result =
    (for {
      _        <- checkReasonableMove(s.board, point)
      newBoard <- move(s.board,                point)
      _        <- checkGameOver(newBoard,      s.turn)
    } yield Continue(Status(newBoard, Player.switch(s.turn)))) fold (identity, identity)

  protected def checkReasonableMove(board: Board, point: Point): Either[Result, Unit] = Right(())
  protected def move(board: Board, point: Point): Either[Result, Board]               = Right(board)
  protected def checkGameOver(newBoard: Board, turn: Player): Either[Result, Unit]    = Right(())
}
