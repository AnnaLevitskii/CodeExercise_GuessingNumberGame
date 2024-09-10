# CodeExercise_GuessingNumberGame

## Test Report in GitHub Pages
After the Tests are automatically run in the GitHub Actions pipeline,
the report is published https://annalevitskii.github.io/CodeExercise_GuessingNumberGame/

## Run locally options

Run game locally:
### 1. `./gradlew shadowJar`
### 2. `java -jar build/libs/CodeExercise_GuessingNumberGame-1.0-SNAPSHOT-all.jar`

Run tests: 
### `gradlew clean test`

## Task
Please program the following game (using any programming language)
There should be an option to start a new Game
1. When starting a new Game, the user will be prompted to enter his name
2. Once the name was entered, the computer will select a number with four digits
3. The selected number should not include duplicate digits
4. The user will have the try a guess the number by entering four digits each time
5. The computer will then answer as follows
 5.1. If a digit exists in the computer number in the same location, the computer will report "+"
 5.2. If a digit exists but not in the same location, the computer will report "–"
 5.3. In example
   5.3.1. computer select the number 1234,   
   5.3.2. User enters the following number 1672
   5.3.3. The computer will display +-  
   5.3.4. The + is for the digit “1”
   5.3.5. The – is for the digit “2”
6. The computer should display the number of guesses it takes for the user to find the number  
7. The user score should be saved in a database (any database type that the candidate will select)
8. The computer will show the best score which is a mixture of (the best time and a minimum number 
of guesses). Any creative formula that combined the two factors will be accepted