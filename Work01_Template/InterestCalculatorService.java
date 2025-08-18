package com.edu.myapp.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class InterestCalculatorService {
	

	InterestCalculator loanInterestCalculator;		
	InterestCalculator savingInterestCalculator;	
	
	

	public void setLoanInterestCalculator(InterestCalculator loanInterestCalculator) {
		this.loanInterestCalculator = loanInterestCalculator;
	}

	public void setSavingInterestCalculator(InterestCalculator savingInterestCalculator) {
		this.savingInterestCalculator = savingInterestCalculator;
	}

	public double calculateLoanInterest(int amount) {
		return loanInterestCalculator.calculate(amount);
	}
	
	public double calculateSavingInterest(int amount) {
		return savingInterestCalculator.calculate(amount);
	}
}
