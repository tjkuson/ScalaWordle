package me.tjkuson.wordle

import scala.annotation.tailrec

@main def main(): Unit =
  val initialNumberOfGuesses = 5
  playGame(randomWord, initialNumberOfGuesses)

@tailrec
private def playGame(word: String, guessesRemaining: Int): Unit =
  if guessesRemaining == 0 then println(s"You lose! The word was $word")
  else
    println(s"Guesses remaining: $guessesRemaining")
    val guess = scala.io.StdIn.readLine("Guess a word: ").toUpperCase
    guess match
      case `word` =>
        println("You win!")
      case _ if guess.length != word.length =>
        println("Word length does not match")
        playGame(word, guessesRemaining)
      case _ =>
        // Print the word where the user had the right letter in the right place, with the wrong letters as underscores
        println(
          word
            .zip(guess)
            .map((letter, guess) => if letter == guess then letter else '_')
            .mkString
        )
        // Get list of letters that are in the wrong place but are in the word
        val correctLettersNotInPlace = word
          .zip(guess)
          .filter((letter, guess) => letter != guess && word.contains(guess))
          .map((_, guess) => guess)
        // Print the letters that are in the word but in the wrong place
        if correctLettersNotInPlace.nonEmpty then
          println(
            s"Letters in the word but in the wrong place: ${correctLettersNotInPlace.mkString(", ")}"
          )
        playGame(word, guessesRemaining - 1)
    end match
end playGame

private def randomWord: String =
  val words = loadWordList.map(_.toUpperCase)
  val randomIndex = scala.util.Random.nextInt(words.length)
  words(randomIndex)

private def loadWordList: List[String] =
  val source = scala.io.Source.getClass.getResourceAsStream("/words.txt")
  val lines = scala.io.Source.fromInputStream(source).getLines.toList
  source.close()
  lines
