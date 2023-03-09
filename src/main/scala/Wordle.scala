package me.tjkuson.wordle

object Wordle:
  def main(args: Array[String]): Unit =
    val word = getRandomWord
    var guessesRemaining = 5
    while true do
      if guessesRemaining == 0 then
        println(s"You ran out of guesses! The word was $word")
        return
      println(s"You have $guessesRemaining guesses remaining")
      val guess = scala.io.StdIn.readLine("Guess the word: ").toUpperCase()
      guess match
        case g if g == word =>
          println("You guessed the word!")
          return
        case g if g.length != word.length =>
          println("The word is not the right length")
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
            .map((*, guess) => guess)
          // Print the letters that are in the word but in the wrong place
          if correctLettersNotInPlace.nonEmpty then
            println(
              s"Letters in the word but in the wrong place: ${correctLettersNotInPlace.mkString(", ")}"
            )
          guessesRemaining -= 1

  private def getRandomWord =
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
