package io.martinlover.ttt.controller

import scalaz.{-\/, \/, \/-}
import scalaz.effect.IO
import scalaz.effect.IO.{putStrLn, readLn}

class Game {
  def play(i: Int): IO[Int \/ Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- readLn
      _ <- putStrLn("Hello, " + n + ", good to meet you!")
    } yield {
      if (i >= 3) \/-()
      else -\/(i + 1)
    }

}
