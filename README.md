# TicTacToeAWT

### Instructions for Running TicTacToeAWT Java Code

This guide will walk you through running the provided `TicTacToeAWT` Java code. The code implements a graphical Tic-Tac-Toe game using AWT (Abstract Window Toolkit).

#### Prerequisites

1. **Java Development Kit (JDK):**
   Ensure you have the JDK installed on your system. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use an open-source version like [OpenJDK](https://openjdk.java.net/).

2. **IDE (Integrated Development Environment):**
   It is recommended to use an IDE such as IntelliJ IDEA, Eclipse, or NetBeans. However, you can also use a simple text editor and compile/run the code from the command line.

#### Steps to Run the Code

1. **Create a New Project:**
   - Open your IDE and create a new Java project.
   - Name the project appropriately (e.g., "TicTacToeAWT").

2. **Create a New Java Class:**
   - Inside your project, create a new Java class file named `TicTacToeAWT`.

3. **Copy and Paste the Code:**
   - Copy the provided Java code and paste it into the `TicTacToeAWT.java` file.

4. **Compile the Code:**
   - If you are using an IDE, it will automatically compile the code when you save the file.
   - If you are using the command line, navigate to the directory containing `TicTacToeAWT.java` and run the following command to compile the code:
     ```sh
     javac TicTacToeAWT.java
     ```

5. **Run the Code:**
   - If you are using an IDE, right-click on the `TicTacToeAWT` class and select "Run" or use the run button in the IDE.
   - If you are using the command line, navigate to the directory containing the compiled `.class` files and run the following command:
     ```sh
     java TicTacToeAWT
     ```

#### Playing the Game

- The game window will open, displaying a 3x3 grid for the Tic-Tac-Toe game.
- The labels at the bottom of the window will show the scores for player X and player O.
- The "Restart" button will reset the current game, allowing you to start a new game while retaining the scores.
- The "Reset" button will reset both the game and the scores for both players.

#### Notes

- The game window is designed to be non-resizable and centers itself on the screen when launched.
- The first player is chosen randomly at the start of the game.
- The current player's turn is displayed at the top of the game window.
- If a player wins, the winning combination is highlighted in green, and the score is updated.
- If the game ends in a tie, the grid is highlighted in yellow.

By following these instructions, you should be able to compile, run, and play the Tic-Tac-Toe game implemented in Java using AWT.
