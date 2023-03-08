package me.tjkuson.wordle

object Wordle:
  def main(args: Array[String]): Unit =
    val word = getRandomWord
    // User keeps guessing until they get the word
    while true do
      val guess = scala.io.StdIn.readLine("Guess the word: ").toUpperCase()
      if guess == word then
        println("You guessed the word!")
        return
      else
        // Get list of letters that are in the wrong place but are in the word
        val correctLettersNotInPlace = word
          .zip(guess)
          .filter((letter, guess) => letter != guess && word.contains(guess))
          .map((*, guess) => guess)
        // Print the word where the user had the right letter in the right place, with the wrong letters as underscores
        println(
          word
            .zip(guess)
            .map((letter, guess) => if letter == guess then letter else '_')
            .mkString
        )
        // Print the letters that are in the word but in the wrong place
        if correctLettersNotInPlace.nonEmpty then
          println(
            s"Letters in the word but in the wrong place: ${correctLettersNotInPlace.mkString(", ")}"
          )

  private def getRandomWord: String =
    // Create a list of uppercase words
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
    // Select a random word from the list
    words(scala.util.Random.nextInt(words.length))
