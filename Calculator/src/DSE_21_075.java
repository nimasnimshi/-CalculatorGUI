import javax.swing.*;
import java.awt.*;

public class DSE_21_075 {

    private JFrame frame;           // Create a JFrame object to hold the GUI components
    private JTextField txtDisplay;  // Create a text field for displaying input and results

    double first;                  // Variables to store the first number in a calculation
    double second;                 // Variables to store the second number in a calculation
    double result;                 // Variable to store the result of a calculation
    String operation;              // String to store the current mathematical operation
    String answer;                 // String to store the final answer of a calculation

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DSE_21_075 window = new DSE_21_075();  // Create an instance of the DSE_21_075 class
                window.frame.setVisible(true);        // Make the GUI frame visible
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DSE_21_075() {
        initialize();  // Call the initialize method to set up the GUI
    }

    private void initialize() {
        frame = new JFrame();  // Create a new JFrame
        frame.setBounds(100, 100, 250, 390);  // Set the size and position of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set the close operation
        frame.getContentPane().setLayout(null);  // Use absolute layout for the frame's content pane

        txtDisplay = new JTextField();  // Create the text field for input and display
        txtDisplay.setHorizontalAlignment(SwingConstants.TRAILING);  // Set the text alignment to right
        txtDisplay.setColumns(10);  // Set the number of visible columns in the text field
        txtDisplay.setBackground(Color.WHITE);  // Set the background color of the text field
        txtDisplay.setBounds(10, 11, 215, 30);  // Set the position and size of the text field
        frame.getContentPane().add(txtDisplay);  // Add the text field to the frame

        JButton btnBackSpace = createButton("\uF0E7", Color.PINK);  // Create a backspace button
        btnBackSpace.addActionListener(e -> {
            String backspace = null;  // Initialize a string to hold the modified text
            if (txtDisplay.getText().length() > 0) {
                StringBuilder strB = new StringBuilder(txtDisplay.getText());
                strB.deleteCharAt(txtDisplay.getText().length() - 1);  // Remove the last character
                backspace = strB.toString();  // Convert the StringBuilder back to a string
                txtDisplay.setText(backspace);  // Update the text field with the modified text
            }
        });
        btnBackSpace.setBounds(10, 51, 50, 50);  // Set the position and size of the backspace button
        frame.getContentPane().add(btnBackSpace);  // Add the backspace button to the frame

        // Create numeric buttons 0-9 and set their positions
        createNumericButton("7", 10, 106);
        createNumericButton("8", 65, 106);
        createNumericButton("9", 120, 106);
        createNumericButton("4", 10, 161);
        createNumericButton("5", 65, 161);
        createNumericButton("6", 120, 161);
        createNumericButton("1", 10, 216);
        createNumericButton("2", 65, 216);
        createNumericButton("3", 120, 216);
        createNumericButton("0", 65, 270);

        // Create operator buttons (+, -, *, /, %) and set their positions
        createOperatorButton("+", 175, 216);
        createOperatorButton("-", 175, 162);
        createOperatorButton("*", 175, 106);
        createOperatorButton("/", 175, 52);
        createOperatorButton("%", 120, 51);

        JButton btnDot = createButton(".", Color.PINK);  // Create a decimal point button
        btnDot.addActionListener(e -> {
            String iNum = txtDisplay.getText() + btnDot.getText();  // Add a decimal point to the text
            txtDisplay.setText(iNum);  // Update the text field with the modified text
        });
        btnDot.setBounds(120, 270, 50, 50);  // Set the position and size of the decimal point button
        frame.getContentPane().add(btnDot);  // Add the decimal point button to the frame

        JButton btnClear = createButton("C", Color.PINK);  // Create a clear button
        btnClear.addActionListener(e -> txtDisplay.setText(null));  // Clear the text field
        btnClear.setBounds(65, 51, 50, 50);  // Set the position and size of the clear button
        frame.getContentPane().add(btnClear);  // Add the clear button to the frame

        JButton btnEqual = createButton("=", Color.PINK);  // Create an equal button
        btnEqual.addActionListener(e -> {
            second = Double.parseDouble(txtDisplay.getText());  // Get the second number
            answer = calculateResult(operation, first, second);  // Calculate the result
            txtDisplay.setText(answer);  // Update the text field with the result
        });
        btnEqual.setBounds(175, 270, 50, 50);  // Set the position and size of the equal button
        frame.getContentPane().add(btnEqual);  // Add the equal button to the frame

        JButton btnPlusMinus = createButton("\u00B1", Color.PINK);  // Create a plus-minus button
        btnPlusMinus.addActionListener(e -> {
            double ops = Double.parseDouble(txtDisplay.getText());  // Get the current number
            ops = ops * (-1);  // Change the sign of the number
            txtDisplay.setText(String.valueOf(ops));  // Update the text field with the modified number
        });
        btnPlusMinus.setBounds(10, 270, 50, 50);  // Set the position and size of the plus-minus button
        frame.getContentPane().add(btnPlusMinus);  // Add the plus-minus button to the frame
    }

    // Method to create numeric buttons with the given label and position
    private void createNumericButton(String label, int x, int y) {
        JButton button = createButton(label, Color.CYAN);  // Create a numeric button
        button.addActionListener(e -> {
            String iNum = txtDisplay.getText() + button.getText();  // Add the button label to the text
            txtDisplay.setText(iNum);  // Update the text field with the modified text
        });
        button.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 18));  // Set the font for numeric buttons
        button.setBounds(x, y, 50, 50);  // Set the position and size of the numeric button
        frame.getContentPane().add(button);  // Add the numeric button to the frame
    }

    // Method to create operator buttons with the given label and position
    private void createOperatorButton(String label, int x, int y) {
        JButton button = createButton(label, Color.PINK);  // Create an operator button
        button.addActionListener(e -> {
            first = Double.parseDouble(txtDisplay.getText());  // Get the first number
            txtDisplay.setText("");  // Clear the text field for entering the second number
            operation = label;  // Set the current operation
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));  // Set the font for operator buttons
        button.setBounds(x, y, 50, 50);  // Set the position and size of the operator button
        frame.getContentPane().add(button);  // Add the operator button to the frame
    }

    // Method to create buttons with the given label and background color
    private JButton createButton(String label, Color color) {
        JButton button = new JButton(label);  // Create a button with the given label
        button.setBackground(color);  // Set the background color of the button
        return button;  // Return the created button
    }

    // Method to calculate the result of a mathematical operation
    private String calculateResult(String operation, double first, double second) {
        switch (operation) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                if (second != 0) {
                    result = first / second;
                } else {
                    return "Error";  // Return an error message if dividing by zero
                }
                break;
            case "%":
                result = first % second;
                break;
        }
        return String.format("%.2f", result);  // Format the result with two decimal places
    }
}
