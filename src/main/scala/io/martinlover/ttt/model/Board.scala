package io.martinlover.ttt.model

import io.martinlover.ttt.model.Board.Point

class Board {
  private final val BoardSize = 3

  def toSeq: Seq[Seq[Option[Player]]] = nestedSeq { (i, j) =>
    board.get(Point(i, j))
  }

  def isReasonableMove(p: Point): Boolean =
    (Seq(p.i, p.j) forall { n =>
      0 <= n && n < BoardSize
    }) && !board.contains(p)

  def isContinuable: Boolean = !(definedLines exists isUnifiedLine)

  private lazy val definedLines: Seq[Seq[(Int, Int)]] = {
    val horizontalLines = nestedSeq((i, j) => (i, j))
    val verticalLines   = nestedSeq((i, j) => (j, i))
    val crossedLines    = Seq(itr.map(i => (i, i))) ++ Seq(itr.map(i => (i, BoardSize - 1 - i)))
    verticalLines ++ horizontalLines ++ crossedLines
  }
  private def isUnifiedLine(l: Seq[(Int, Int)]): Boolean = {
    val line = l.flatMap { ij =>
      board.get(Point.tupled(ij))
    }
    (line.size == BoardSize) && (line.groupBy(identity).size == 1)
  }

  private val itr = 0 until BoardSize

  private def nestedSeq[A](f: (Int, Int) => A): Seq[Seq[A]] =
    itr.map(i => itr.map(j => f(i, j)))

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
