package com.darshita.calculatorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.darshita.calculatorApp.Exception.MathOperatorException;
import com.darshita.calculatorApp.service.CalculatorService;

@ControllerAdvice
@RestController
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;
	
	@GetMapping("/calculator")
	public ModelAndView showCalculatorPage() {
		return new ModelAndView("CalculatorEntry");
	}
	
	@PostMapping("/calculate")
	public ModelAndView showResultPage(@RequestParam("operand1")String s1,@RequestParam("operand2")String s2,@RequestParam("operator")String s3) throws MathOperatorException {
		String result = calculatorService.performCalculation(s1,s2,s3);
		if(result.equals("ABCD")) {
			throw new MathOperatorException();
		}
		ModelAndView mv = new ModelAndView("CalculatorResult"); 
		mv.addObject("myresult",result);
		return mv;
	}
	
	@ExceptionHandler(value = ArithmeticException.class)
	   public ModelAndView handlingArithmeticException(ArithmeticException exception) {
		   String message="Divided by zero not possible";
		   ModelAndView mv=new ModelAndView("ErrorPage");
		   mv.addObject("errorMessage",message);
		   return mv;
	   }
	
	@ExceptionHandler(value = NumberFormatException.class)
	   public ModelAndView handlingNumberFormatException(NumberFormatException exception) {
		   String message="Invalid Number Format";
		   ModelAndView mv=new ModelAndView("ErrorPage");
		   mv.addObject("errorMessage",message);
		   return mv;
	   }
	
	@ExceptionHandler(value = MathOperatorException.class)
	   public ModelAndView handlingMathOperatorException(MathOperatorException exception) {
		   String message="Invalid Math Operator";
		   ModelAndView mv=new ModelAndView("ErrorPage");
		   mv.addObject("errorMessage",message);
		   return mv;
	   }
}
