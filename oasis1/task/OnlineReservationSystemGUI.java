import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OnlineReservationSystemGUI {

    private JFrame frame;
    private JTextField loginIdField;
    private JPasswordField passwordField;
    private JTextField basicDetailsField;
    private JTextField trainNumberField;
    private JTextField fromField;
    private JTextField toField;
    private JTextField pnrField;

    private Map<Integer, Reservation> reservations;

    private class Reservation {
        String basicDetails;
        int trainNumber;
        String from;
        String to;

        Reservation(String basicDetails, int trainNumber, String from, String to) {
            this.basicDetails = basicDetails;
            this.trainNumber = trainNumber;
            this.from = from;
            this.to = to;
        }
    }

    public OnlineReservationSystemGUI() {
        reservations = new HashMap<>();

        frame = new JFrame("Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1));

        JPanel loginPanel = new JPanel();
        loginIdField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        loginPanel.add(new JLabel("Login ID:"));
        loginPanel.add(loginIdField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        JPanel reservationPanel = new JPanel();
        basicDetailsField = new JTextField(15);
        trainNumberField = new JTextField(15);
        fromField = new JTextField(15);
        toField = new JTextField(15);
        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleInsert();
            }
        });
        reservationPanel.add(new JLabel("Name:"));
        reservationPanel.add(basicDetailsField);
        reservationPanel.add(new JLabel("Train Number:"));
        reservationPanel.add(trainNumberField);
        reservationPanel.add(new JLabel("From:"));
        reservationPanel.add(fromField);
        reservationPanel.add(new JLabel("To:"));
        reservationPanel.add(toField);
        reservationPanel.add(insertButton);

        JPanel cancellationPanel = new JPanel();
        pnrField = new JTextField(15);
        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCancellation();
            }
        });
        cancellationPanel.add(new JLabel("PNR Number:"));
        cancellationPanel.add(pnrField);
        cancellationPanel.add(cancelButton);

        frame.add(loginPanel);
        frame.add(reservationPanel);
        frame.add(cancellationPanel);

        frame.setVisible(true);
    }

    private void handleLogin() {
        String loginId = loginIdField.getText();
        char[] password = passwordField.getPassword();

        // Check login credentials (dummy check in this example)
        if (loginId.equals("admin") && new String(password).equals("password")) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed. Please check your credentials.");
        }

        // Clear fields
        loginIdField.setText("");
        passwordField.setText("");
    }

    private void handleInsert() {
        String basicDetails = basicDetailsField.getText();
        int trainNumber = Integer.parseInt(trainNumberField.getText());
        String from = fromField.getText();
        String to = toField.getText();

        // Generate a random PNR number for the reservation
        Random random = new Random();
        int pnrNumber = random.nextInt(9000) + 1000;

        // Store reservation
        reservations.put(pnrNumber, new Reservation(basicDetails, trainNumber, from, to));

        JOptionPane.showMessageDialog(frame, "Reservation successful! Your PNR number is: " + pnrNumber);

        // Clear fields
        basicDetailsField.setText("");
        trainNumberField.setText("");
        fromField.setText("");
        toField.setText("");
    }

    private void handleCancellation() {
        int pnrNumber = Integer.parseInt(pnrField.getText());

        if (reservations.containsKey(pnrNumber)) {
            Reservation reservation = reservations.get(pnrNumber);
            int response = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to cancel this reservation?\n" + "PNR: " + pnrNumber + "\n" +
                            "Basic Details: " + reservation.basicDetails + "\n" +
                            "Train Number: " + reservation.trainNumber + "\n" +
                            "From: " + reservation.from + "\n" +
                            "To: " + reservation.to,
                    "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                reservations.remove(pnrNumber);
                JOptionPane.showMessageDialog(frame, "Reservation canceled successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No reservation found for the provided PNR number.");
        }

        // Clear field
        pnrField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineReservationSystemGUI();
            }
        });
    }
}



    
