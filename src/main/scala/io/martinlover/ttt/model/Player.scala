package io.martinlover.ttt.model

sealed abstract class Player(val id: Int, val displayName: String)

case object Black extends Player(1, "☓")
case object White extends Player(2, "○")

object Player {
  def switch(p: Player): Player = p match {
    case Black => White
    case White => Black
  }
}
