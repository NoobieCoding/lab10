package coinmachine;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Graphic user interface used to display the number of coins in the coin machine.
 * Show message that the coin machine accepts more coin if the machine is not full.
 * When the machine is full, shows message to inform user that the machine is full.
 * @author Nuttapong Rojanavanich
 *
 */
public class CoinCountUI extends JFrame implements Observer {

	private JPanel upperPart, lowerPart;
	private JLabel coinsLabel, messageLabel;
	private JTextField coinsNumTextField;
	
	/**
	 * Constructor with no argument.
	 */
	public CoinCountUI() {
		this.setTitle("Coint Counter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}

	/**
	 * create components of the GUI.
	 */
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
	
	/**
	 * Display the number of coins in the coin machine.
	 * Show message to inform user that the machine if the number of coins reach the machine capacity.
	 */
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
	
	/**
	 * Run this GUI.
	 */
	public void run() {
		setVisible(true);
		pack();
	}
}
