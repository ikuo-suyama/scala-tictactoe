package io.martinlover.ttt.model

sealed abstract class Player(val id: Int)
case object Black extends Player(1)
case object White extends Player(2)
