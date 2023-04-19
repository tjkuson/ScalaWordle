package me.tjkuson.wordle

import scala.annotation.tailrec
import scala.io.StdIn.readLine

import WordleUtils.{randomWord, playerGuess, wordList, newGame}

@main def main(): Unit = playGame(randomWord, 6)

@tailrec private def playGame(word: String, guesses: Int): Unit =
  if guesses == 0 then println(s"You lose! The word was $word")
  else
    val guess = playerGuess(guesses)
    guess match
      case `word` => if newGame then playGame(randomWord, 6)
      case _ if guess.startsWith("Q") || guess == "QUIT" =>
        println("Quitting game")
      case _ if !wordList.contains(guess) =>
        if guess.length != word.length then
          println("Word length does not match")
        else println("Word not in dictionary")
        playGame(word, guesses)
      case _ =>
        val correct = word.zip(guess).map((l, g) => if l == g then l else '_')
        println(correct.mkString)
        val misplaced = word
          .zip(guess)
          .filter((l, g) => l != g && word.contains(g))
          .map((_, g) => g)
        if misplaced.nonEmpty then
          println(s"Letters present but misplaced: ${misplaced.mkString(", ")}")
        playGame(word, guesses - 1)
    end match
end playGame
