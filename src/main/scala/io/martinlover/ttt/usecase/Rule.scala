package io.martinlover.ttt.usecase

trait Rule {
  def drop(s: Status, input: String): Results
}

class TicTokToe extends Rule {
  override def drop(s: Status, input: String): Results = Finish
}
