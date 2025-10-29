// File: RockPaperScissorsDashboard.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissorsDashboard extends JFrame implements ActionListener {
    private JLabel playerChoiceLabel, aiChoiceLabel, resultLabel, scoreLabel;
    private JButton rockBtn, paperBtn, scissorsBtn, resetBtn;

    private int playerScore = 0;
    private int aiScore = 0;
    private Random random = new Random();

    public RockPaperScissorsDashboard() {
        setTitle("🎮 Rock Paper Scissors - AI Dashboard");
        setSize(600, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel heading = new JLabel("🤖 Rock Paper Scissors - AI Game", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 22));
        heading.setBounds(80, 20, 440, 40);
        add(heading);

        JLabel chooseLbl = new JLabel("Choose Your Move:");
        chooseLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        chooseLbl.setBounds(200, 90, 200, 25);
        add(chooseLbl);

        rockBtn = new JButton("🪨 Rock");
        rockBtn.setBounds(120, 130, 100, 40);
        rockBtn.setBackground(new Color(173, 216, 230));
        rockBtn.addActionListener(this);
        add(rockBtn);

        paperBtn = new JButton("📜 Paper");
        paperBtn.setBounds(240, 130, 100, 40);
        paperBtn.setBackground(new Color(144, 238, 144));
        paperBtn.addActionListener(this);
        add(paperBtn);

        scissorsBtn = new JButton("✂️ Scissors");
        scissorsBtn.setBounds(360, 130, 100, 40);
        scissorsBtn.setBackground(new Color(255, 182, 193));
        scissorsBtn.addActionListener(this);
        add(scissorsBtn);

        playerChoiceLabel = new JLabel("Your Choice: ");
        playerChoiceLabel.setBounds(100, 200, 400, 25);
        add(playerChoiceLabel);

        aiChoiceLabel = new JLabel("AI Choice: ");
        aiChoiceLabel.setBounds(100, 230, 400, 25);
        add(aiChoiceLabel);

        resultLabel = new JLabel("Result: Start Playing!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel.setBounds(120, 270, 360, 30);
        add(resultLabel);

        scoreLabel = new JLabel("Score ➤ You: 0  |  AI: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        scoreLabel.setBounds(150, 310, 300, 25);
        add(scoreLabel);

        resetBtn = new JButton("🔁 Reset Game");
        resetBtn.setBounds(230, 350, 140, 35);
        resetBtn.setBackground(new Color(255, 228, 181));
        resetBtn.addActionListener(e -> resetGame());
        add(resetBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerMove = "";
        if (e.getSource() == rockBtn) playerMove = "Rock";
        else if (e.getSource() == paperBtn) playerMove = "Paper";
        else if (e.getSource() == scissorsBtn) playerMove = "Scissors";

        String aiMove = generateAIMove();
        playerChoiceLabel.setText("Your Choice: " + playerMove);
        aiChoiceLabel.setText("AI Choice: " + aiMove);

        String winner = decideWinner(playerMove, aiMove);
        updateScore(winner);

        resultLabel.setText("Result: " + winner);
        scoreLabel.setText("Score ➤ You: " + playerScore + "  |  AI: " + aiScore);
    }

    private String generateAIMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[random.nextInt(3)];
    }

    private String decideWinner(String player, String ai) {
        if (player.equals(ai))
            return "Draw 😐";
        else if ((player.equals("Rock") && ai.equals("Scissors")) ||
                 (player.equals("Paper") && ai.equals("Rock")) ||
                 (player.equals("Scissors") && ai.equals("Paper"))) {
            return "You Win 🎉";
        } else {
            return "AI Wins 🤖";
        }
    }

    private void updateScore(String winner) {
        if (winner.contains("You Win")) playerScore++;
        else if (winner.contains("AI Wins")) aiScore++;
    }

    private void resetGame() {
        playerScore = 0;
        aiScore = 0;
        playerChoiceLabel.setText("Your Choice: ");
        aiChoiceLabel.setText("AI Choice: ");
        resultLabel.setText("Result: Start Playing!");
        scoreLabel.setText("Score ➤ You: 0  |  AI: 0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsDashboard frame = new RockPaperScissorsDashboard();
            frame.setVisible(true);
        });
    }
}
