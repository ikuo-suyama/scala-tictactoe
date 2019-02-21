package io.martinlover.ttt.model

import io.martinlover.ttt.model.Board.Point

class Board {
  def isFinished(): Boolean = false

  private val board: Map[Point, Int] = Map.empty
}

object Board {

  case class Point(i: Int, j: Int)

}
