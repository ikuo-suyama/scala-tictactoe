package io.martinlover.ttt.controller

import io.martinlover.ttt.model.Board
import scalaz.{-\/, \/, \/-}
import scalaz.effect.IO
import scalaz.effect.IO.{putStrLn, readLn}

class Game {
  def play(board: Board): IO[Board \/ Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- readLn
      _ <- putStrLn("Hello, " + n + ", good to meet you!")
    } yield {
      if (board.isFinished()) \/-()
      else -\/(board)
    }
}
