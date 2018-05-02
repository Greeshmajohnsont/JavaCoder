package com;

public class LetterCount {

	public static void main(String[] args) {
		 int sum = 0;
	        for ( final String numbers : new String[]{ "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" } )
	            sum += numbers.length();
	        final int letters1To9 = sum;

	        sum = 0;
	        for ( final String numbers : new String[]{ "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" } )
	            sum += numbers.length();
	        final int letters10To19 = sum;

	        sum = 0;
	        for ( final String numbers : new String[]{ "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" } )
	            sum +=numbers.length();
	        final int lettersTens = sum;

	        final int letters1To99 = 9 * letters1To9 + letters10To19 + 10*lettersTens;

	        System.out.println(
	            10*letters1To99
	            + 100*letters1To9
	            + 900 * "Hundred".length()
	            + 891 * "And".length()
	            + "One".length()
	            + "Thousand".length()
	        );

	}

}
