package com;
import java.math.BigInteger;

public class PowerSum {

	public static void main(String[] args) {
		int	sum=0;
		String	number	=	BigInteger.valueOf(2).pow(1000).toString();
		for(char	a:number.toCharArray())
		{
			sum	=sum+Character.getNumericValue(a);
		}
		System.out.println("The	sum	of	digits	of	power="+sum);
		
	}

}
