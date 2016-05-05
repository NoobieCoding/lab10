package coinmachine;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Graphic user interface used to display the current balance in the coin machine,
 * display the percentage of space left for inserting more coin using progress bar.
 * The buttons can be put to add a coin to the coin machine.
 * Observer of CoinMachine.
 * @author Nuttapong Rojanavanich
 *
 */
public class CoinMachineUI extends JFrame implements Observer {

	private CoinMachine coinMachine;
	private JPanel upperPart, lowerPart;
	private JLabel balanceLabel, statusLabel;
	private JProgressBar progressBar;
	private TitledBorder border;
	private ImageIcon oneBahtPic, fiveBahtPic, tenBahtPic;
	private JButton oneBahtButton, fiveBahtButton, tenBahtButton;
	private URL oneBahtURL, fiveBahtURL, tenBahtURL;
	
	/**
	 *Constructor with CoinMachine object as an argument. 
	 * @param coinMachine is a coin machine that store coins.
	 */
	public CoinMachineUI(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
		this.setTitle("Coin Machine");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	/**
	 * Create components of the GUI.
	 * Use anonymous class to add action listener to buttons.
	 */
	public void initComponents() {
		upperPart = new JPanel();
		lowerPart = new JPanel();

		balanceLabel = new JLabel("Balance: 0");
		statusLabel = new JLabel("Status: ");

		progressBar = new JProgressBar(0, coinMachine.getCapacity());
		progressBar.setForeground(Color.blue);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		border = BorderFactory.createTitledBorder("Insert money");
		border.setTitleJustification(TitledBorder.LEFT);
		lowerPart.setBorder(border);

		ClassLoader loader = this.getClass().getClassLoader();
		oneBahtURL = loader.getResource("images/1baht.png");
		fiveBahtURL = loader.getResource("images/5baht.png");
		tenBahtURL = loader.getResource("images/10baht.png");

		oneBahtPic = new ImageIcon(oneBahtURL);
		fiveBahtPic = new ImageIcon(fiveBahtURL);
		tenBahtPic = new ImageIcon(tenBahtURL);

		oneBahtButton = new JButton(oneBahtPic);
		oneBahtButton.putClientProperty(1, 1);
		oneBahtButton.addActionListener(new InsertCoinListener());

		fiveBahtButton = new JButton(fiveBahtPic);
		fiveBahtButton.putClientProperty(1, 5);
		fiveBahtButton.addActionListener(new InsertCoinListener());

		tenBahtButton = new JButton(tenBahtPic);
		tenBahtButton.putClientProperty(1, 10);
		tenBahtButton.addActionListener(new InsertCoinListener());

		setLayout(new BorderLayout());
		add(upperPart, BorderLayout.NORTH);
		add(lowerPart, BorderLayout.SOUTH);
		upperPart.setLayout(new FlowLayout());
		upperPart.add(balanceLabel);
		upperPart.add(statusLabel);
		upperPart.add(progressBar);
		lowerPart.add(oneBahtButton);
		lowerPart.add(fiveBahtButton);
		lowerPart.add(tenBahtButton);
	}
	
	/**
	 * When the button is clicked, insert the coin into the coin machine 
	 * depending on the value of the button.
	 * 
	 * @author Nuttapong Rojanavanich
	 */
	class InsertCoinListener implements ActionListener{
		
		/**
		 * Insert the coin when the button is clicked
		 */
		@Override
		public void actionPerformed(ActionEvent evt) {
			JComponent source = (JComponent) evt.getSource();
			int amount = (int) source.getClientProperty(1);
			coinMachine.insert(new Coin(amount));
			
		}
		
	}
	
	/**
	 * Display current balance when a coin is successfully added.
	 * Notified from CoinMachine object.
	 * When the coin is added, Progress bar increases and its color is changed respectively to 
	 * the number of coins in the machine (if reach 50% change to orange, 100 % change to red).
	 */
	public void update(Observable subject, Object info) {
			CoinMachine coinMachine = (CoinMachine) subject;
			int balance = coinMachine.getBalance();
			balanceLabel.setText("Balance: " + balance);
			
			int coinCount = coinMachine.getCount();
			progressBar.setValue(coinCount);
			if (coinCount == coinMachine.getCapacity())
				progressBar.setForeground(Color.red);
			else if (coinCount >= coinMachine.getCapacity() / 2)
				progressBar.setForeground(Color.orange);
	}

	/**
	 * Run this GUI
	 */
	public void run() {
		setVisible(true);
		pack();
	}
}
