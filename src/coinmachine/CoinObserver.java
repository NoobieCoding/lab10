package coinmachine;
import java.util.*;

/**
 * Observer of CoinMachine that print the current balance when the coin is successfully added.
 * @author Nuttapong Rojanavanich
 *
 */
public class CoinObserver implements Observer {
	
	/**
	 * Print current balance when this observer is notified.
	 */
	public void update(Observable subject, Object info) {
		if (info != null) {
			List<Coin> coins = (List<Coin>) info;
			int balance = calculateBalance(coins);
			System.out.println(balance + " " + coins.get(0).getCurrency());
		}
	}
	
	/**
	 * Calculate the total balance of all coins in the coins list.
	 * @param coins is the list of coins
	 * @return balance is the total value of all coins balance.
	 */
	public int calculateBalance(List<Coin> coins) {
		int balance = 0;
		for (Coin coin : coins) {
			balance += coin.getValue();
		}
		return balance;
	}
}
