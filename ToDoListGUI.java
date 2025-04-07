import javax.swing.*;
import java.awt.*;
import java.awt.event.DefaultListSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ToDoListGUI extends JFrame implements ActionListener {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, removeButton;

    public ToDoListGUI() {
        setTitle("📝 To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Task input
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        taskField = new JTextField();
        addButton = new JButton("Add Task");
        addButton.addActionListener(this);
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.NORTH);

        // Task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Remove button
        removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(this);
        add(removeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == removeButton) {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                listModel.remove(selected);
            } else {
                JOptionPane.showMessageDialog(this, "No task selected!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
