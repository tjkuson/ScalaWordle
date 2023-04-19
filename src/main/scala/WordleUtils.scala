package me.tjkuson.wordle

import scala.io.StdIn.readLine
import scala.util.Random

object WordleUtils:

  def playerGuess(guesses: Int): String = readLine(
    s"Guess a word ($guesses remaining) or input q(uit) to exit: "
  ).toUpperCase

  def newGame: Boolean =
    readLine("Correct! Start a new game? (y/n): ").toUpperCase == "Y"

  def randomWord: String =
    val words = wordList.map(_.toUpperCase)
    words(Random.nextInt(words.length))

  def wordList: List[String] =
    val stream = scala.io.Source.getClass.getResourceAsStream("/words.txt")
    val lines = scala.io.Source.fromInputStream(stream).getLines.toList
    stream.close()
    lines

end WordleUtils
