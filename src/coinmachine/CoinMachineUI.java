package coinmachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CoinMachineUI extends JFrame implements Observer {

	private CoinMachine coinMachine;
	private JPanel upperPart, lowerPart;
	private JLabel balanceLabel, statusLabel;
	private JProgressBar progressBar;
	private TitledBorder border;
	private ImageIcon oneBahtPic, fiveBahtPic, tenBahtPic;
	private JButton oneBahtButton, fiveBahtButton, tenBahtButton;
	private URL oneBahtURL, fiveBahtURL, tenBahtURL;

	public CoinMachineUI(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
		this.setTitle("Coin Machine");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}

	public void initComponents() {
		upperPart = new JPanel();
		lowerPart = new JPanel();
		balanceLabel = new JLabel("Balance: 0");
		statusLabel = new JLabel("Status: ");
		progressBar = new JProgressBar(0, coinMachine.getCapacity());
		progressBar.setForeground(Color.red);
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
		oneBahtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				coinMachine.insert(new Coin(1));
			}
		});

		fiveBahtButton = new JButton(fiveBahtPic);
		fiveBahtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				coinMachine.insert(new Coin(5));
			}
		});

		tenBahtButton = new JButton(tenBahtPic);
		tenBahtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				coinMachine.insert(new Coin(10));
			}
		});

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

	public void update(Observable subject, Object info) {
		if (info != null) {
			CoinMachine coinMachine = (CoinMachine) subject;
			int balance = coinMachine.getBalance();
			balanceLabel.setText("Balance: " + balance);
			progressBar.setValue(coinMachine.getCount());
		}
	}

	public void run() {
		setVisible(true);
		pack();
	}
}
