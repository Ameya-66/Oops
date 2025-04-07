import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TypingSpeedTester extends JFrame implements KeyListener, ActionListener {
    private JTextArea typingArea;
    private JLabel promptLabel, resultLabel;
    private JButton startButton;
    private String[] sentences = {
        "The quick brown fox jumps over the lazy dog.",
        "Java is a popular programming language.",
        "Typing speed can be improved with practice.",
        "Always write clean and readable code.",
        "Debugging is twice as hard as writing the code."
    };
    private String currentSentence;
    private long startTime = 0;
    private boolean started = false;

    public TypingSpeedTester() {
        setTitle("⌨️ Typing Speed Tester");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        promptLabel = new JLabel("Press Start to begin!", JLabel.CENTER);
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(promptLabel, BorderLayout.NORTH);

        typingArea = new JTextArea();
        typingArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setEnabled(false);
        typingArea.addKeyListener(this);
        add(new JScrollPane(typingArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        startButton = new JButton("Start Test");
        startButton.addActionListener(this);
        resultLabel = new JLabel(" ", JLabel.CENTER);

        bottomPanel.add(startButton, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();
        currentSentence = sentences[rand.nextInt(sentences.length)];
        promptLabel.setText("Type this: " + currentSentence);
        typingArea.setText("");
        typingArea.setEnabled(true);
        typingArea.requestFocus();
        resultLabel.setText(" ");
        started = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!started) {
            startTime = System.currentTimeMillis();
            started = true;
        }

        String typed = typingArea.getText();
        if (typed.endsWith(".") && typed.length() >= currentSentence.length()) {
            long endTime = System.currentTimeMillis();
            double timeTaken = (endTime - startTime) / 1000.0;
            int wordCount = currentSentence.split("\\s+").length;
            int wpm = (int)((wordCount / timeTaken) * 60);

            int correctChars = 0;
            for (int i = 0; i < Math.min(typed.length(), currentSentence.length()); i++) {
                if (typed.charAt(i) == currentSentence.charAt(i)) {
                    correctChars++;
                }
            }
            int accuracy = (int)((correctChars * 100.0) / currentSentence.length());

            resultLabel.setText("⏱️ " + wpm + " WPM | 🎯 " + accuracy + "% Accuracy");
            typingArea.setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TypingSpeedTester::new);
    }
}
