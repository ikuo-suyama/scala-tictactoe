package io.martinlover.ttt.external

import io.martinlover.ttt.model.Board.Point
import io.martinlover.ttt.model.{Black, Board, Player, White}
import org.specs2.mutable.Specification

class StdIOPresenterImplSpec extends Specification {

  "transformBoard" should {
    "create String Board" in {
      val presenter = new StdIOPresenterImpl
      val score     = Seq((0, 0, Black), (1, 1, White), (0, 2, Black))
      val board     = play(score)

      val result = presenter.transformBorad(board)

      result must beEqualTo(
        " x |   | x \n" +
          " - | - | - \n" +
          "   | o |   \n" +
          " - | - | - \n" +
          "   |   |   ")
    }
  }

  def play(score: Seq[(Int, Int, Player)]): Board =
    score.foldLeft(new Board) { (brd, move) =>
      val (i, j, plyer) = move
      Board.drop(brd, plyer, Point(i, j))
    }

}
