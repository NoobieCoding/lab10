package coinmachine;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class CoinCountUI extends JFrame implements Observer {

	private JPanel upperPart, lowerPart;
	private JLabel coinsLabel, messageLabel;
	private JTextField coinsNumTextField;

	public CoinCountUI() {
		this.setTitle("Coint Counter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}

	public void initComponents() {
		upperPart = new JPanel();
		lowerPart = new JPanel();

		coinsLabel = new JLabel("#Coins: ");
		messageLabel = new JLabel("Accepting Coins");
		messageLabel.setForeground(Color.PINK);
		coinsNumTextField = new JTextField(4);
		coinsNumTextField.setText("0");
		coinsNumTextField.setEditable(false);

		setLayout(new BorderLayout());
		add(upperPart, BorderLayout.NORTH);
		add(lowerPart, BorderLayout.SOUTH);
		upperPart.setLayout(new FlowLayout());
		upperPart.add(coinsLabel);
		upperPart.add(coinsNumTextField);
		lowerPart.add(messageLabel);

	}

	public void update(Observable subject, Object info) {
		if (info != null) {
			CoinMachine coinMachine = (CoinMachine) subject;

			if (coinMachine.getCount() == coinMachine.getCapacity()) {
				messageLabel.setText("This machine is full");
				messageLabel.setForeground(Color.RED);
			}

			int numCoins = coinMachine.getCount();
			coinsNumTextField.setText(String.valueOf(numCoins));
		}
	}

	public void run() {
		setVisible(true);
		pack();
	}
}
