package com.projetoodonto.model;

import java.util.Calendar;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		
		c.set(2017,Calendar.NOVEMBER, 25);
		
		Date data = c.getTime();
		
		System.out.println(data);
	}	
}
