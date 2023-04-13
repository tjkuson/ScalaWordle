package me.tjkuson.wordle

import scala.annotation.tailrec

@main def main(): Unit =
  val guessesRemaining = 5
  playGame(getRandomWord, guessesRemaining)

@tailrec
private def playGame(word: String, guessesRemaining: Int): Unit =
  guessesRemaining match
    case 0 =>
      println("You lose!")
    case _ =>
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

private def getRandomWord: String =
  // TODO: Get words from a file
  val words = List(
    "Hello",
    "World",
    "Apple",
    "Robot",
    "Index",
    "Magic",
    "Coder",
    "Redux",
    "Coats",
    "Puppy",
    "Kitty",
    "Riots"
  ).map(_.toUpperCase)
  words(scala.util.Random.nextInt(words.length))
