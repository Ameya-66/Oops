import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ExpenseTracker extends JFrame {
    private JTextField descField, amountField;
    private JLabel totalLabel;
    private DefaultTableModel tableModel;
    private double total = 0;

    public ExpenseTracker() {
        setTitle("Expense Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel - Form Inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Expense"));

        inputPanel.add(new JLabel("Description:"));
        descField = new JTextField();
        inputPanel.add(descField);

        inputPanel.add(new JLabel("Amount ($):"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JButton addBtn = new JButton("Add Expense");
        inputPanel.add(addBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel - Table
        tableModel = new DefaultTableModel(new Object[]{"Description", "Amount"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel - Total
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(totalLabel, BorderLayout.SOUTH);

        // Button Logic
        addBtn.addActionListener(e -> {
            String desc = descField.getText();
            String amountText = amountField.getText();

            if (desc.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);
                total += amount;
                tableModel.addRow(new Object[]{desc, String.format("$%.2f", amount)});
                totalLabel.setText("Total: $" + String.format("%.2f", total));
                descField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpenseTracker::new);
    }
}
