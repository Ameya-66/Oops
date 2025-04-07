import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizAppGUI extends JFrame implements ActionListener {
    String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?",
        "Which data structure uses FIFO?",
        "What is the result of 9 + 6 / 3?"
    };

    String[][] options = {
        {"Berlin", "London", "Paris", "Madrid"},
        {"Earth", "Mars", "Jupiter", "Venus"},
        {"Shakespeare", "Hemingway", "Dickens", "Tolkien"},
        {"Stack", "Queue", "Tree", "Graph"},
        {"3", "5", "11", "15"}
    };

    char[] answers = {'C', 'B', 'A', 'B', 'C'};
    char guess;
    int index = 0;
    int correct = 0;

    JLabel questionLabel = new JLabel();
    JButton[] buttons = new JButton[4];
    JLabel resultLabel = new JLabel();

    public QuizAppGUI() {
        setTitle("Quiz App");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Top - Question
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        // Center - Options
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        String[] labels = {"A", "B", "C", "D"};
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setActionCommand(labels[i]);
            buttons[i].addActionListener(this);
            optionsPanel.add(buttons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Bottom - Result
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    public void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
            for (int i = 0; i < 4; i++) {
                buttons[i].setText(options[index][i]);
                buttons[i].setEnabled(true);
            }
        } else {
            for (JButton btn : buttons) btn.setVisible(false);
            questionLabel.setText("Quiz Completed!");
            resultLabel.setText("Score: " + correct + "/" + questions.length);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        guess = e.getActionCommand().charAt(0);
        if (guess == answers[index]) correct++;
        index++;
        loadQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
