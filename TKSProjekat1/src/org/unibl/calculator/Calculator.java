package org.unibl.calculator;

import org.unibl.exception.DivisionByZeroException;
import org.unibl.exception.NotSupportedOperationException;

/**
 * The Calculator class is a simple application that performs basic arithmetic operations: 
 * addition, subtraction, multiplication, and division (+, -, *, /).
 * This class maintains a current value that can be updated through its methods.
 * 
 * @author Andjela Radakovic
 * @version 1.0
 */
public class Calculator {
	
	/**
	 * The current value of the calculator.
	 * Initially set to 0.0.
	 */
	private Double currentValue;

	/**
	 * Retrieves the current value of the calculator.
     * 
     * @return the current value of the calculator
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * Updates the current value of the calculator.
     * 
     * @param currentValue the new value to set
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
     * Performs a calculation based on the given operator and value.
     * The method applies the specified operation (addition, subtraction, multiplication, 
     * or division) using the current value as the first operand and the provided value 
     * as the second operand. The result is stored in {@code currentValue}.
     * 
     * @param value the second operand for the operation
     * @param operator the operator for the operation (allowed values: {@code '+', '-', '*', '/'})
     * @throws DivisionByZeroException if division by zero is attempted
     * @throws NotSupportedOperationException if the operator is not one of {@code '+', '-', '*', '/'}
     */
    public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
    	if (currentValue == null || value == null) {
            throw new NullPointerException("Operands cannot be null.");
        }
    	
        if (operator == '+') {
            currentValue += value;
        } else if (operator == '-') {
            currentValue -= value;
        } else if (operator == '*') {
            currentValue *= value;
        } else if (operator == '/') {
            if (value == 0) {
                throw new DivisionByZeroException("Division by zero is not allowed.");
            }
            currentValue /= value;
        } else {
            throw new NotSupportedOperationException("Operator '" + operator + "' is not supported.");
        }
    }
}
