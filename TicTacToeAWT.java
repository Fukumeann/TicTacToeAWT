import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class TicTacToeAWT implements ActionListener {

    // Declare instance variables
    Random random = new Random(); // To determine first player randomly
    Frame frame = new Frame(); // Main frame for the game
    Panel title_panel = new Panel(); // Panel for title
    Panel button_panel = new Panel(); // Panel for game buttons
    Panel score_panel = new Panel(); // Panel for scores and controls
    Label textfield = new Label(); // Label for game status
    Label scoreX = new Label("X: 0"); // Label for player X score
    Label scoreO = new Label("O: 0"); // Label for player O score
    Button resetButton = new Button("Reset"); // Button to reset scores
    Button restartButton = new Button("Restart"); // Button to restart the game
    Button[] buttons = new Button[9]; // Array for game buttons
    boolean player1_turn; // Boolean to track player turn
    int xScore = 0; // Player X score
    int oScore = 0; // Player O score

    // Constructor to set up the game
    public TicTacToeAWT() {
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(620, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0); // Exit program when window is closed
            }
        });
        frame.setResizable(false);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Arial", Font.BOLD, 50));
        textfield.setAlignment(Label.CENTER);
        textfield.setText("Tic-Tac-Toe"); // Display initial text

        scoreX.setForeground(new Color(255, 0, 0));
        scoreX.setBackground(new Color(255, 255, 255));
        scoreX.setFont(new Font("Arial", Font.BOLD, 70)); // Adjusted font size
        scoreO.setForeground(new Color(0, 0, 255));
        scoreO.setBackground(new Color(255, 255, 255));
        scoreO.setFont(new Font("Arial", Font.BOLD, 70)); // Adjusted font size
        restartButton.setFont(new Font("Arial", Font.BOLD, 40)); // Adjusted font size
        restartButton.setForeground(new Color(0, 0, 0));
        restartButton.addActionListener(this);
        resetButton.setFont(new Font("Arial", Font.BOLD, 40)); // Adjusted font size
        resetButton.setForeground(new Color(0, 0, 0));
        resetButton.addActionListener(this);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 130));
            buttons[i].addActionListener(this); // Add action listener to each button
        }

        score_panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        score_panel.add(scoreX, gbc); // Add scoreX label to score panel

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        score_panel.add(restartButton, gbc); // Add restart button to score panel
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        score_panel.add(resetButton, gbc); // Add reset button to score panel
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        score_panel.add(scoreO, gbc); // Add scoreO label to score panel

        title_panel.add(textfield, BorderLayout.CENTER); // Centered textfield
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.CENTER);
        frame.add(score_panel, BorderLayout.SOUTH);

        firstTurn(); // Determine who starts first
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            textfield.setText("Restarting game...");
            textfield.setForeground(new Color(255, 255, 255));
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restartGame();                    
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else if (e.getSource() == resetButton) {
            textfield.setText("Resetting scoreboard...");
            textfield.setForeground(new Color(255, 255, 255));
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetScoreboard();
                    restartGame();                 
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (!buttons[i].getLabel().equals("")) {
                        textfield.setText("Box occupied!");
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                textfield.setText(player1_turn ? "X Turn" : "O Turn");
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        if (player1_turn) {
                            buttons[i].setForeground(new Color(255, 0, 0));
                            buttons[i].setLabel("X");
                            player1_turn = false;
                            textfield.setText("O Turn");
                            textfield.setForeground(new Color(0, 0, 255));
                        } else {
                            buttons[i].setForeground(new Color(0, 0, 255));
                            buttons[i].setLabel("O");
                            player1_turn = true;
                            textfield.setText("X Turn");
                            textfield.setForeground(new Color(255, 0, 0));
                        }
                        check(); // Check for a winner or tie
                    }
                }
            }
        }
    }

    // Determine the first player to start
    public void firstTurn() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X Turn");
            textfield.setForeground(new Color(255, 0, 0));
        } else {
            player1_turn = false;
            textfield.setText("O Turn");
            textfield.setForeground(new Color(0, 0, 255));
        }
    }

    // Check for a win or tie
    public void check() {
        // Check X win conditions
        if (checkWin("X")) {
            return; // Exit check() if X wins
        }

        // Check O win conditions
        if (checkWin("O")) {
            return; // Exit check() if O wins
        }

        // Check tie condition
        if (Arrays.stream(buttons).allMatch(button -> !button.getLabel().equals(""))) {
            tieGame();
        }
    }

    // Helper method to check win conditions for a player
    public boolean checkWin(String player) {
        // Define winning combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        // Check all win combinations
        for (int[] combo : winCombinations) {
            if (buttons[combo[0]].getLabel().equals(player) &&
                buttons[combo[1]].getLabel().equals(player) &&
                buttons[combo[2]].getLabel().equals(player)) {
                if (player.equals("X")) {
                    xWins(combo[0], combo[1], combo[2]);
                } else {
                    oWins(combo[0], combo[1], combo[2]);
                }
                return true; // Return true if there's a winner
            }
        }
        return false; // Return false if no winner found
    }

    // Method to handle X player win
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(0, 255, 0));
        buttons[b].setBackground(new Color(0, 255, 0));
        buttons[c].setBackground(new Color(0, 255, 0));
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins!");
        textfield.setForeground(new Color(0, 255, 0));
        xScore++;
        scoreX.setText("X: " + xScore); // Update X player's score
    }

    // Method to handle O player win
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(0, 255, 0));
        buttons[b].setBackground(new Color(0, 255, 0));
        buttons[c].setBackground(new Color(0, 255, 0));
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins!");
        textfield.setForeground(new Color(0, 255, 0));
        oScore++;
        scoreO.setText("O: " + oScore); // Update O player's score
    }

    // Method to handle a tie game
    public void tieGame() {
        for (Button button : buttons) {
            button.setBackground(new Color(255, 255, 0));
            button.setEnabled(false);
        }
        textfield.setText("Tie Game!");
        textfield.setForeground(new Color(255, 255, 0));
                   
    }

    // Method to restart the game
    public void restartGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setLabel("");
            buttons[i].setBackground(new Color(240, 240, 240));
            buttons[i].setEnabled(true);
        }
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setText("Tic-Tac-Toe");
        firstTurn();
    }

    // Method to reset the scoreboard
    public void resetScoreboard() {
        xScore = 0;
        oScore = 0;
        scoreX.setText("X: 0");
        scoreO.setText("O: 0");
    }

    // Main method to launch the game
    public static void main(String[] args) {
        new TicTacToeAWT();
    }
}
