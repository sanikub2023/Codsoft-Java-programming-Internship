import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private final BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }
}
public class ATMInterface {
    private final ATM atm;
    private JFrame frame;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMInterface(ATM atm) {
        this.atm = atm;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ATM Machine");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 30, 100, 30);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(100, 30, 150, 30);
        frame.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(30, 80, 100, 30);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                atm.deposit(amount);
                outputArea.append("Deposited: " + amount + "\n");
                updateBalance();
            }
        });
        frame.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(150, 80, 100, 30);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (atm.withdraw(amount)) {
                    outputArea.append("Withdrew: " + amount + "\n");
                } else {
                    outputArea.append("Insufficient funds for withdrawal of: " + amount + "\n");
                }
                updateBalance();
            }
        });
        frame.add(withdrawButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(270, 80, 150, 30);
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalance();
            }
        });
        frame.add(balanceButton);

        outputArea = new JTextArea();
        outputArea.setBounds(30, 120, 350, 120);
        outputArea.setEditable(false);
        frame.add(outputArea);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void updateBalance() {
        outputArea.append("Current Balance: " + atm.checkBalance() + "\n");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); 
        ATM atm = new ATM(account);
        new ATMInterface(atm);
    }
}