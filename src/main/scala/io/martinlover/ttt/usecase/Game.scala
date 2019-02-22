package io.martinlover.ttt.usecase

trait Game {
  def drop(s: Status, input: String): Result
}

class TicTokToe extends Game {
  override def drop(s: Status, input: String): Result = Finish
}
