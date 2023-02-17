package gui;

import rates.Calculator;
import rates.Rate;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

public class GUI extends JFrame {

    private final String rateStr = "Current rate";

    private final List<Rate> rates;
    private final JPanel workPanel = new JPanel(new BorderLayout());
    private final JTextField youSendText = new JTextField();
    private JComboBox<String> youSendBox;
    private JTextField theyReceiveText = new JTextField();
    private final JLabel currentRateLabel = new JLabel(rateStr);
    private final JButton clearButton = new JButton("Clear");
    private final JButton calculateButton = new JButton("Calculate");
    private Rate currentRate;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.0000");

    public GUI(List<Rate> rates) {
        this.rates = rates;
        rates.add(0, null);
        createAndShow();
    }

    public void createAndShow() {
        setTitle("Remitly internship");
        setLayout(new BorderLayout());
        createYouSend();
        createTheyReceive();
        add(workPanel, BorderLayout.CENTER);
        createLabel();
        createButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createYouSend() {
        JPanel youSend = new JPanel();
        JLabel label = new JLabel("You send:");
        youSend.add(label);
        youSendText.setPreferredSize(new Dimension(100, 20));
        youSend.add(youSendText);

        youSendBox = new JComboBox<>(symbols());
        youSendBox.addActionListener(e -> {
            int selectedItem = youSendBox.getSelectedIndex();
            if (selectedItem == 0) {
                currentRateLabel.setText(rateStr);
            } else {
                currentRate = rates.get(selectedItem);
                currentRateLabel.setText(setCurrentRateLabel());
            }
        });
        youSend.add(youSendBox);
        workPanel.add(youSend, BorderLayout.NORTH);
    }

    private String setCurrentRateLabel() {
        return 1 + " " + currentRate.getCode() + " = " + currentRate.getMid() + " PLN";
    }

    private void createTheyReceive() {
        JPanel theyReceive = new JPanel();
        JLabel label = new JLabel("They receive:");
        theyReceive.add(label);

        theyReceiveText = new JTextField();
        theyReceiveText.setPreferredSize(new Dimension(100, 20));
        theyReceive.add(theyReceiveText);

        JLabel emptyLabel = new JLabel("PLN");
        emptyLabel.setPreferredSize(youSendBox.getPreferredSize());
        theyReceive.add(emptyLabel);
        workPanel.add(theyReceive, BorderLayout.SOUTH);
    }

    private void createLabel() {
        add(currentRateLabel, BorderLayout.SOUTH);
    }

    private void createButtons() {
        JPanel buttons = new JPanel(new BorderLayout());

        calculateButton.addActionListener(e -> calculate());

        clearButton.addActionListener(e -> clear());

        buttons.add(calculateButton, BorderLayout.NORTH);
        buttons.add(clearButton, BorderLayout.CENTER);
        JLabel help = new JLabel("Clear before each calculate!");
        buttons.add(help, BorderLayout.SOUTH);
        add(buttons, BorderLayout.EAST);
    }

    private void calculate() {
        try {
            String yourText = youSendText.getText();
            String theirText = theyReceiveText.getText();
            if (!yourText.equals("") && theirText.equals("")) {
                currentRateLabel.setText(setCurrentRateLabel());
                Double yourMoney = Double.parseDouble(yourText);
                Calculator calculator = new Calculator(currentRate);
                Double theirMoney = calculator.youSend(yourMoney);
                theyReceiveText.setText("" + decimalFormat.format(theirMoney));
            } else if (yourText.equals("") && !theirText.equals("")) {
                currentRateLabel.setText(setCurrentRateLabel());
                Double theirMoney = Double.parseDouble(theirText);
                Calculator calculator = new Calculator(currentRate);
                Double yourMoney = calculator.theyReceive(theirMoney);
                youSendText.setText("" + decimalFormat.format(yourMoney));
            } else {
                currentRateLabel.setText("One field must be filled!");
            }
        } catch (NullPointerException e) {
            currentRateLabel.setText("Why didn't you choose rate");
        } catch (NumberFormatException e) {
            currentRateLabel.setText("Why didn't you use numbers?");
        }
    }

    private void clear() {
        try {
            youSendText.setText("");
            theyReceiveText.setText("");
            currentRateLabel.setText(setCurrentRateLabel());
        } catch (NullPointerException e) {
            
        }
    }

    private Vector<String> symbols() {
        Vector<String> symbols = new Vector<>();
        symbols.add("");
        for (int i = 1; i < rates.size(); i++) {
            symbols.add(rates.get(i).getCode());
        }
        return symbols;
    }

}

