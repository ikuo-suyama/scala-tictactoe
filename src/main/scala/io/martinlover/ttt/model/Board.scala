package io.martinlover.ttt.model

import io.martinlover.ttt.model.Board.Point

class Board {
  private final val BoardSize = 3

  def isReasonableMove(p: Point): Boolean =
    (Seq(p.i, p.j) forall { n =>
      0 <= n && n < BoardSize
    }) && !board.contains(p)

  def isFinished(): Boolean = (0 until BoardSize) map { Point(0, _) } forall { board.get(_).isDefined }

  private[model] val board: Map[Point, Player]        = Map.empty
  private[model] def copy(kv: (Point, Player)): Board = Board(board + kv)
}

object Board {
  private def apply(m: Map[Point, Player]): Board = {
    new Board {
      override private[model] val board = m
    }
  }
  def drop(board: Board, player: Player, point: Point): Board =
    board.copy(point -> player)

  case class Point(i: Int, j: Int)
}
