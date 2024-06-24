package com.darshita.calculatorApp.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
	public String performCalculation(String operand1, String operand2, String operator) {
		int n1 = Integer.parseInt(operand1);
		int n2 = Integer.parseInt(operand2);
		int result = 0;
		String r = "";
		switch(operator) {
		case "+" : result = n1+n2; break;
		case "-" : result = n1-n2; break;
		case "*" : result = n1*n2; break;
		case "/" : result = n1/n2; break;
		case "%" : result = n1%n2; break;
		default: r="ABCD";
		}
		if(r.equals("ABCD")) {
			return r;
		}else {
			return String.valueOf(result);
		}
	}
}
