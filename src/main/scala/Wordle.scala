package me.tjkuson.wordle

import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit = playGame(randomWord, 5)

@tailrec private def playGame(word: String, guesses: Int): Unit =
  if guesses == 0 then println(s"You lose! The word was $word")
  else
    val guess = readLine(s"Guess a word ($guesses remaining): ").toUpperCase
    guess match
      case `word` => println("Correct! You win.")
      case _ if guess.length != word.length =>
        println("Word length does not match")
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

private def randomWord: String =
  val words = wordList.map(_.toUpperCase)
  words(scala.util.Random.nextInt(words.length))

private def wordList: List[String] =
  val stream = scala.io.Source.getClass.getResourceAsStream("/words.txt")
  val lines = scala.io.Source.fromInputStream(stream).getLines.toList
  stream.close()
  lines
