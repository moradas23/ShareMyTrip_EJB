package alb.util.money;

import java.util.Currency;
import java.math.BigDecimal;

/*
 * Created on Jun 24, 2004
 *
 * A class for handling numeric calculation, rounding and currency.
 * This class is extracted from "Patterns of Enterprise Application Architecture" by Martin Fowler.
 * http://www.martinfowler.com/eaaCatalog/money.html
 */
public class Money {
	private long amount;
	private Currency currency;
	private static final int[] cents = new int[] { 1, 10, 100, 1000 };

	// default rounding preference for this Money object
	private int roundingMode = BigDecimal.ROUND_HALF_EVEN;

	public Money() {
	}

	public Money(double amount, Currency currency) {
		this.currency = currency;
		this.amount = Math.round(amount * centFactor());
	}

	public Money(BigDecimal amount, Currency currency, int roundingMode) {
		this.currency = currency;
		long myAmount = amount.longValue();
		this.roundingMode = roundingMode;
		this.amount = Math.round(myAmount * centFactor());
	}

	public Money(long amount, Currency currency) {
		this.currency = currency;
		this.amount = Math.round(amount * centFactor());
	}

	// helper constructor for frequently used currency
	public static Money dollars(double amount) {
		return new Money(amount, Currency.getInstance("USD"));
	}

	public static Money euros(double amount) {
		return new Money(amount, Currency.getInstance("EUR"));
	}

	private int centFactor() {
		return cents[currency.getDefaultFractionDigits()];
	}

	public BigDecimal amount() {
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}

	public Currency currency() {
		return currency;
	}

	public boolean equals(Object other) {
		return (other instanceof Money) && equals((Money) other);
	}

	public boolean equals(Money other) {
		return currency.equals(other.currency) && (amount == other.amount);
	}

	public int hashCode() {
		return (int) (amount ^ (amount >>> 32));
	}

	public Money add(Money other) {
		assertSameCurrencyAs(other);
		return newMoney(amount + other.amount);
	}

	public Money subtract(Money other) {
		assertSameCurrencyAs(other);
		return newMoney(amount - other.amount);
	}

	public int compareTo(Object other) {
		return compareTo((Money) other);
	}

	public int compareTo(Money other) {
		assertSameCurrencyAs(other);
		return (amount < other.amount) ? -1 : ((amount == other.amount) ? 0 : 1);
}

	public boolean greaterThan(Money other) {
		return (compareTo(other) > 0);
	}

	public Money multiply(double amount) {
		return multiply(new BigDecimal(amount));
	}

	public Money multiply(BigDecimal amount) {
		return multiply(amount, roundingMode);
	}

	public Money multiply(BigDecimal amount, int roundingMode) {
		return new Money(amount().multiply(amount), currency, roundingMode);
	}

	/** 
	 * These two methods allocate money among multiple targets without losing
	 * 
	 * @param targets
	 * @return
	 */
	public Money[] allocate(int targets) {
		Money lowResult = newMoney(amount / targets);
		Money highResult = newMoney(lowResult.amount + 1);
		Money[] results = new Money[targets];
		int remainder = (int) amount % targets;
		for (int i = 0; i < remainder; i++)
			results[i] = highResult;
		for (int i = remainder; i < targets; i++)
			results[i] = lowResult;
		return results;
	}

	/** 
	 * This allocation algorithm can handle any ratio
	 * 
	 * @param ratios
	 * @return
	 */
	public Money[] allocate(long[] ratios) {
		long total = 0;
		for (int i = 0; i < ratios.length; i++)
			total += ratios[i];
		long remainder = amount;
		Money[] results = new Money[ratios.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = newMoney(amount * ratios[i] / total);
			remainder -= results[i].amount;
		}
		for (int i = 0; i < remainder; i++) {
			results[i].amount++;
		}
		return results;
	}

	private void assertSameCurrencyAs(Money m) {
		if (currency.equals(m.currency)) return;
		throw new RuntimeException("Not same currency: " + currency + " <> " + m.currency);
	}

	private Money newMoney(long amount) {
		Money money = new Money();
		money.currency = this.currency;
		money.amount = amount;
		return money;
	}

}