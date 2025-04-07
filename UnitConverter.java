import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UnitConverter extends JFrame implements ActionListener {
    private JComboBox<String> categoryBox, fromBox, toBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton convertButton;

    public UnitConverter() {
        setTitle("🔁 Unit Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        String[] categories = {"Length", "Temperature", "Weight"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.addActionListener(e -> updateUnits());

        fromBox = new JComboBox<>();
        toBox = new JComboBox<>();
        updateUnits();

        inputField = new JTextField();
        resultLabel = new JLabel("Result: ", JLabel.CENTER);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        add(categoryBox);
        add(inputField);
        add(fromBox);
        add(toBox);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    private void updateUnits() {
        String category = (String) categoryBox.getSelectedItem();
        fromBox.removeAllItems();
        toBox.removeAllItems();

        switch (category) {
            case "Length" -> {
                fromBox.addItem("Meters");
                fromBox.addItem("Kilometers");
                fromBox.addItem("Miles");
                toBox.addItem("Meters");
                toBox.addItem("Kilometers");
                toBox.addItem("Miles");
            }
            case "Temperature" -> {
                fromBox.addItem("Celsius");
                fromBox.addItem("Fahrenheit");
                toBox.addItem("Celsius");
                toBox.addItem("Fahrenheit");
            }
            case "Weight" -> {
                fromBox.addItem("Kilograms");
                fromBox.addItem("Pounds");
                toBox.addItem("Kilograms");
                toBox.addItem("Pounds");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String fromUnit = (String) fromBox.getSelectedItem();
            String toUnit = (String) toBox.getSelectedItem();
            String category = (String) categoryBox.getSelectedItem();

            double result = convertUnits(inputValue, fromUnit, toUnit, category);
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("⚠️ Invalid input");
        }
    }

    private double convertUnits(double value, String from, String to, String category) {
        switch (category) {
            case "Length" -> {
                if (from.equals(to)) return value;
                // Convert to meters first
                if (from.equals("Kilometers")) value *= 1000;
                if (from.equals("Miles")) value *= 1609.34;
                // Convert from meters
                if (to.equals("Kilometers")) return value / 1000;
                if (to.equals("Miles")) return value / 1609.34;
                return value; // meters
            }
            case "Temperature" -> {
                if (from.equals(to)) return value;
                if (from.equals("Celsius") && to.equals("Fahrenheit"))
                    return (value * 9/5) + 32;
                if (from.equals("Fahrenheit") && to.equals("Celsius"))
                    return (value - 32) * 5/9;
            }
            case "Weight" -> {
                if (from.equals(to)) return value;
                if (from.equals("Kilograms") && to.equals("Pounds"))
                    return value * 2.20462;
                if (from.equals("Pounds") && to.equals("Kilograms"))
                    return value / 2.20462;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnitConverter::new);
    }
}
