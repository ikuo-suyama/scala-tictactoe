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

  "isContinuable" should {
    "false when a line has 3-sequenced symbol" in {
      "patterns(i,j,p)" | "ret" |
        // horizontal
        Seq((0, 0, White), (0, 1, White), (0, 2, White)) ! false |
        Seq((1, 0, White), (1, 1, White), (1, 2, White)) ! false |
        Seq((2, 0, White), (2, 1, White), (2, 2, White)) ! false |
        // vertical
        Seq((0, 0, White), (1, 0, White), (2, 0, White)) ! false |
        Seq((0, 1, White), (1, 1, White), (2, 1, White)) ! false |
        Seq((0, 2, White), (1, 2, White), (2, 2, White)) ! false |
        // cross
        Seq((0, 0, White), (1, 1, White), (2, 2, White)) ! false |
        Seq((0, 2, White), (1, 1, White), (2, 0, White)) ! false |
        // black
        Seq((0, 0, Black), (0, 1, Black), (0, 2, Black)) ! false |> { (score, ret) =>
        val board = play(score)
        board.isContinuable must beEqualTo(ret)
      }
    }
    "true when no line has 3-sequenced symbol" in {
      "patterns(i,j,p)" | "ret" |
        Seq((0, 0, White)) ! true |
        Seq((0, 0, White), (0, 2, White)) ! true |
        Seq((0, 0, White), (0, 1, Black), (0, 2, White)) ! true |> { (score, ret) =>
        val board = play(score)
        board.isContinuable must beEqualTo(ret)
      }
    }
  }

  "toSeq" should {
    import scalaz.Scalaz._
    "board Map to nested Seq" in {
      val score = Seq((0, 0, Black), (1, 1, White), (0, 2, Black))
      val board = play(score)
      board.toSeq must beEqualTo(
        Seq(
          Seq(Black.some, None,       Black.some),
          Seq(None,       White.some, None),
          Seq(None,       None,       None)
        )
      )
    }
  }

  def play(score: Seq[(Int, Int, Player)]): Board =
    score.foldLeft(new Board) { (brd, move) =>
      val (i, j, plyer) = move
      Board.drop(brd, plyer, Point(i, j))
    }
}
