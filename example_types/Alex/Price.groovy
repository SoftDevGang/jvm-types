import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
public class Price {
	final BigDecimal value
	final Currency currency

	/**
	 * Allows creating a new price with named parameters in groovy: new Price(value: 20, currency: Currency.getInstance("RON"))
	 * @param parameters
	 */
	Price(parameters) {
		this.value = new BigDecimal(parameters.value).setScale(2, BigDecimal.ROUND_HALF_UP)
		this.currency = parameters.currency
	}

	Price convertTo(Currency currency, currencyRate) {
		if (currency == this.currency) {
			return this
		}

		return new Price(value: value * currencyRate, currency: currency)
	}

	def computeVAT(vatRatio) { // I'd like to rewrite the vatRatio to be a Percent type
		return new Price(value: vatRatio * value, currency: this.currency)
	}

	def addVAT(vatRatio) {
		return new Price(value: this.value + computeVAT(vatRatio).value, currency: this.currency)
	}
}
