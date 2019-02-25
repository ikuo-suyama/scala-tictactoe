package io.martinlover.ttt.model

import io.martinlover.ttt.model.Board.Point
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Tables

class BoardSpec extends Specification with Mockito with Tables {
  "drop" should {
    "create new Board contains new point" in {
      val b   = new Board
      val p1  = Point(0, 0)
      val p2  = Point(1, 1)
      val pl1 = White
      val pl2 = Black

      val result = Board.drop(b, pl1, p1)
      result.board must havePair(p1 -> White)

      val result2 = Board.drop(result, pl2, p2)
      result2.board must havePair(p1 -> White)
      result2.board must havePair(p2 -> Black)
    }
  }

  "isResonableMove" should {
    "false when out of bounds" in {
      "i" | "j" |
        3 ! 0 |
        0 ! 3 |
        -1 ! 0 |
        0 ! -1 |> { (i, j) =>
        val result = new Board().isReasonableMove(Point(i, j))
        result must beFalse
      }
    }
    "false when given point is already droped" in {
      val b   = new Board
      val p1  = Point(0, 0)
      val pl1 = White

      val b2 = Board.drop(b, pl1, p1)
      b2.isReasonableMove(p1) must beFalse
    }
    "true when point is valid" in {
      val b   = new Board
      val p1  = Point(0, 0)
      val p2  = Point(0, 1)
      val pl1 = White

      val b2 = Board.drop(b, pl1, p1)
      b2.isReasonableMove(p2) must beTrue
    }
  }

  "isFinished" should {
    "true when player black has 3-sequenced symbol" in {
      "patterns(i,j,p)" | "ret" |
        Seq((0, 0, White), (0, 1, White), (0, 2, White)) ! true |> { (score, ret) =>
        val board = score.foldLeft(new Board) { (brd, move) =>
          val (i, j, plyer) = move
          Board.drop(brd, plyer, Point(i, j))
        }
        board.isFinished() must beEqualTo(ret)
      }
    }
  }

}
