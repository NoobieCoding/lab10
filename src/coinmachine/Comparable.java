package coinmachine;

/**
 * Use can access value and currency of object that implements this interface.
 * @author Nuttapong Rojanavanich
 * @param <T> It is a generic type that user must specify with this interface.
 */
public interface Comparable<T> {
	int getValue();

	String getCurrency();
}
