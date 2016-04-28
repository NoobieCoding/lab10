package coinmachine;

import java.util.*;

public class CoinObserver implements Observer {

	public void update(Observable subject, Object info) {
		if (info != null) {
			List<Coin> coins = (List<Coin>) info;
			int balance = calculateBalance(coins);
			System.out.println(balance + " " + coins.get(0).getCurrency());
		}
	}

	public int calculateBalance(List<Coin> coins) {
		int balance = 0;
		for (Coin coin : coins) {
			balance += coin.getValue();
		}
		return balance;
	}
}
