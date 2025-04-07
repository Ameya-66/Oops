import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClockGUI extends JFrame {
    private JLabel timeLabel;
    private SimpleDateFormat timeFormat;

    public DigitalClockGUI() {
        setTitle("Digital Clock");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timeFormat = new SimpleDateFormat("HH:mm:ss");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setOpaque(true);

        add(timeLabel);
        setVisible(true);

        // Start the timer
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        updateClock(); // Initial time display
    }

    private void updateClock() {
        String currentTime = timeFormat.format(new Date());
        timeLabel.setText(currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClockGUI::new);
    }
}
