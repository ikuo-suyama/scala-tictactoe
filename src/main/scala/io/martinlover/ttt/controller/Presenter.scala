package io.martinlover.ttt.controller

import io.martinlover.ttt.model.{Board, Player}

trait Presenter {
  def transformBorad(board: Board): String
  def transformPlayer(maybePlayer: Option[Player]): String
}
