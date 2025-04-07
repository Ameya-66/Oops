import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0;
    private String operator = "";
    private boolean startNew = true;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            if (startNew) {
                display.setText(input);
                startNew = false;
            } else {
                display.setText(display.getText() + input);
            }
        } else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = input;
            startNew = true;
        } else if (input.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                double result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> {
                        if (num2 == 0) throw new ArithmeticException("Divide by zero");
                        yield num1 / num2;
                    }
                    default -> 0;
                };
                display.setText(String.valueOf(result));
                startNew = true;
            } catch (Exception ex) {
                display.setText("Error");
                startNew = true;
            }
        } else if (input.equals("C")) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
            startNew = true;
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
