package io.martinlover.ttt.controller

import io.martinlover.ttt.model.{Black, Board, Player, White}

trait Presenter {
  def transformBorad(board: Board): String
  def transformPlayer(maybePlayer: Option[Player]): String
}

class PresenterImpl extends Presenter {
  override def transformBorad(board: Board): String =
    board.toSeq
      .map {
        _.map(transformPlayer).mkString(" ", " | ", " ")
      }
      .mkString("\n - | - | - \n")

  override def transformPlayer(maybePlayer: Option[Player]): String = maybePlayer match {
    case Some(Black) => "x"
    case Some(White) => "o"
    case _           => " "
  }
}
