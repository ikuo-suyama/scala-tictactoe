package io.martinlover.ttt.usecase

import io.martinlover.ttt.model.Board.Point

trait Game {
  def drop(s: Status, point: Point): Result
}

class TicTokToe extends Game {
  override def drop(s: Status, point: Point): Result = Finish
}
