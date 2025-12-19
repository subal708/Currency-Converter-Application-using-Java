import java.awt.*;  // Used for colors, fonts, layout, and GUI components
import java.util.HashMap;  //Used to store currency exchange rates
import javax.swing.*;  //Used to create Swing GUI components like JFrame, JButton, JTextField

public class CurrencyConvert {  //Defines the main class Program execution starts from this class

    // Declare GUI Components
    private static JTextField fromField, toField;// Text fields for input amount and output result

    // Dropdowns for selecting source and target currencies
    private static JComboBox<String> fromCurrency, toCurrency;

    // Buttons for convert, reset and exit
    private static JButton convertButton, resetButton, exitButton;

    // HashMap to store exchange rates (base currency is USD)
    private static HashMap<String, Double> rates = new HashMap<>();

    public static void main(String[] args) {

        // --------- Exchange rates with respect to USD ---------
        rates.put("United States (USD)", 1.0);      // Base currency
        rates.put("Eurozone (EUR)", 0.85);
        rates.put("India (INR)", 90.40);
        rates.put("United Kingdom (GBP)", 0.75);
        rates.put("Australia (AUD)", 1.51);
        rates.put("Canada (CAD)", 1.38);
        rates.put("United Arab Emirates (AED)", 3.67);
        rates.put("Haiti (HTG)", 130.90);
        rates.put("Japan (JPY)", 155.60);
        rates.put("Nepal (NPR)", 144.61);
        rates.put("Bangladesh (BDT)", 122.12);

        // --------- Create main application window ---------
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(750, 400); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Closes program when window is closed
        frame.setLocationRelativeTo(null); // Opens window at center of screen

        // --------- Main panel ---------
        JPanel panel = new JPanel();
        panel.setLayout(null); // Manual positioning
        panel.setBackground(Color.LIGHT_GRAY);  //Sets background color
        frame.add(panel);  //Adds panel to frame

        // --------- Title label ---------
        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(220, 30, 300, 40);
        panel.add(title);

        // --------- From Currency section ---------
        JLabel fromLabel = new JLabel("From Currency Of");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16)); //Sets font style and size
        fromLabel.setBounds(80, 100, 200, 25);  //Position and size of label
        panel.add(fromLabel);  //Adds label to panel

        // Dropdown to select source currency
        fromCurrency = new JComboBox<>(new String[]{
            "United States (USD)", "India (INR)", "Eurozone (EUR)",
            "Haiti (HTG)", "Japan (JPY)", "United Kingdom (GBP)",
            "Australia (AUD)", "Canada (CAD)",
            "United Arab Emirates (AED)", "Nepal (NPR)", "Bangladesh (BDT)"
        });
        fromCurrency.setBounds(80, 130, 220, 30);  //Position and size of label
        panel.add(fromCurrency); //Adds label to panel

        // Text field to enter amount
        fromField = new JTextField();
        fromField.setBounds(80, 180, 220, 30);  //Position and size of label
        panel.add(fromField);//Adds label to panel

        // --------- To Currency section ---------
        JLabel toLabel = new JLabel("To Currency Of");
        toLabel.setFont(new Font("Arial", Font.BOLD, 16)); //Sets font style and size
        toLabel.setBounds(420, 100, 200, 25); //Position and size of label
        panel.add(toLabel); //Adds label to panel

        // Dropdown to select target currency
        toCurrency = new JComboBox<>(new String[]{
            "India (INR)", "United States (USD)", "Eurozone (EUR)",
            "Haiti (HTG)", "Japan (JPY)", "United Kingdom (GBP)",
            "Australia (AUD)", "Canada (CAD)",
            "United Arab Emirates (AED)", "Nepal (NPR)", "Bangladesh (BDT)"
        });
        toCurrency.setBounds(420, 130, 220, 30);  //Position and size of label
        panel.add(toCurrency);  //Adds label to panel

        // Output text field (read-only)
        toField = new JTextField();
        toField.setBounds(420, 180, 220, 30);//Position and size of label
        toField.setEditable(false); // User cannot edit
        panel.add(toField); //Adds label to panel

        // --------- Buttons ---------
        convertButton = new JButton("Convert");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 300, 750, 60);  //set Position and size
        buttonPanel.setBackground(Color.LIGHT_GRAY); //set colour

        buttonPanel.add(convertButton);  //Adds buttons to button panel
        buttonPanel.add(resetButton);  //Adds buttons to button panel
        buttonPanel.add(exitButton);  //Adds buttons to button panel
        panel.add(buttonPanel);  //Adds buttons to button panel

        // --------- Convert button logic ---------
        convertButton.addActionListener(e -> { //Runs when Convert button is clicked
            try {
                // Read amount entered by user & convert it to double
                double amount = Double.parseDouble(fromField.getText());

                // Get selected currencies
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();

                // Get exchange rates from HashMap
                double fromRate = rates.get(from);
                double toRate = rates.get(to);

                // Convert source currency to USD
                double amountInUSD = amount / fromRate;  //get amt-> USD value

                // Convert USD to target currency
                double finalAmount = amountInUSD * toRate;

                // Display result with 2 decimal places
                toField.setText(String.format("%.2f", finalAmount));

            } catch (NumberFormatException ex) {
                // Show error if input is invalid
                JOptionPane.showMessageDialog(frame,
                        "Enter a valid number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // --------- Reset button logic ---------
        resetButton.addActionListener(e -> {
            fromField.setText("");   // Clear input
            toField.setText("");     // Clear output
        });

        // --------- Exit button logic ---------
        exitButton.addActionListener(e -> System.exit(0));

        // Show the window
        frame.setVisible(true);
    }
}