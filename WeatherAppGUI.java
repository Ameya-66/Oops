import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class WeatherAppGUI extends JFrame implements ActionListener {
    private JTextField cityField;
    private JLabel resultLabel;
    private JButton fetchButton;

    // Mock weather data
    private static final Map<String, String> mockWeather = new HashMap<>();
    static {
        mockWeather.put("new york", "🌤️ 15°C, Clear, Humidity: 40%");
        mockWeather.put("london", "🌧️ 10°C, Rainy, Humidity: 70%");
        mockWeather.put("tokyo", "⛅ 18°C, Partly Cloudy, Humidity: 55%");
        mockWeather.put("paris", "🌦️ 13°C, Drizzle, Humidity: 65%");
        mockWeather.put("mumbai", "☀️ 33°C, Sunny, Humidity: 60%");
    }

    public WeatherAppGUI() {
        setTitle("🌦️ Weather App");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Enter City Name", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        cityField = new JTextField();
        fetchButton = new JButton("Get Weather");
        fetchButton.addActionListener(this);

        resultLabel = new JLabel(" ", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        add(titleLabel);
        add(cityField);
        add(fetchButton);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String city = cityField.getText().trim().toLowerCase();
        if (city.isEmpty()) {
            resultLabel.setText("⚠️ Please enter a city.");
            return;
        }

        String weather = mockWeather.getOrDefault(city, "❌ Weather not found for \"" + city + "\".");
        resultLabel.setText(weather);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherAppGUI::new);
    }
}
