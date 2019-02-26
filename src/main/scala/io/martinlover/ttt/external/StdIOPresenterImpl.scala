package io.martinlover.ttt.external

import io.martinlover.ttt.controller.Presenter
import io.martinlover.ttt.model.{Black, Board, Player, White}

class StdIOPresenterImpl extends Presenter {
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
