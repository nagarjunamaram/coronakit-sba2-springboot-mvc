package com.eval.coronakit.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.exception.ProductException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(ProductException.class)
	public ModelAndView ErrorPage(ProductException exp)
	{
		return new ModelAndView("errPage","errMsg",exp.getMessage());		
	}

}
