package io.martinlover.ttt.model

import io.martinlover.ttt.model.Board.Point

class Board {
  def isResonableMove(point: Point): Boolean = true

  def isFinished(): Boolean = false

  private val board: Map[Point, Int] = Map.empty

}

object Board {
  def drop(board: Board, point: Point): Board = board

  case class Point(i: Int, j: Int)
}
