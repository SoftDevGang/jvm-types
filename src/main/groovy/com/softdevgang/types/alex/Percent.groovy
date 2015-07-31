package com.softdevgang.types.alex
// Mostly experimental, haven't yet used it but want to refactor some of my code in this direction
public class Percent{
	def value

	public static Percent value(value){
		return new Percent(value: value)
	}

	// Allows the following usage: 10432 * com.softdevgang.types.alex.Percent.value(5)
	def multiply(other){

		// Should it multiply first and then divide or the other way around? AFAIK, this implementation has better precision
		// but is limited to values <= (max_type_value / 100)
		return (value * other) / 100
	}
}