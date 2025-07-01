package org.unibl.calculator;

import org.unibl.exception.NotSupportedOperationException;
import org.unibl.exception.NumberNotInAreaException;

/**
 * The CalculatorAdvanced class extends the Calculator class and adds support for advanced calculations,
 * including exponentiation and factorial calculations.
 * 
 * @author Andjela Radakovic
 * @version 1.0
 */
public class CalculatorAdvanced extends Calculator {

    /**
     * Performs an advanced calculation based on the provided action.
     * 
     * - If action is a digit (0-9), it raises the integer part of `currentValue` to the power of the given digit.
     * - If action is '!', it calculates the factorial of the integer part of `currentValue`. 
     * 
     * @param action the action to perform ('0' to '9' for exponentiation, '!' for factorial).
     * @throws NullPointerException if the current value is null.
     * @throws NumberNotInAreaException if calculating factorial and `currentValue` is outside the range [0, 10].
     * @throws NotSupportedOperationException if the action is not supported.
     */
    public void calculateAdvanced(char action) {
        Double currentValue = getCurrentValue();

        if (currentValue == null) {
            throw new NullPointerException("Current value cannot be null.");
        }

        int integerValue = currentValue.intValue();

        if (isDigit(action)) {
            int power = action - '0';
            int result = 1;
            for (int i = 0; i < power; i++) {
                result *= integerValue;
            }
            setCurrentValue((double) result);
        } else if (action == '!') {
            if (integerValue < 0 || integerValue > 10) {
                throw new NumberNotInAreaException("Value must be in the range [0, 10] for factorial calculation.");
            }
            int result = 1;
            for (int i = 1; i <= integerValue; i++) {
                result *= i;
            }
            setCurrentValue((double) result);
        } else {
            throw new NotSupportedOperationException("Unsupported action: " + action);
        }
    }
    
    /**
     * Checks if the given character is a digit ('0' to '9').
     * 
     * This method works by comparing the character's ASCII value with the ASCII values of '0' and '9'.
     * If the character lies between these two values inclusively, it is considered a digit.
     * 
     * @param action the character to check.
     * @return {@code true} if the character represents a digit ('0' to '9'), 
     *         {@code false} otherwise.
     */
    public boolean isDigit(char action) {
        return action > 47 && action < 58; 
    }
    
    /**
     * Determines if the integer part of the current value has a specific characteristic.
     * 
     * @param value the characteristic to check:
     *              - 'A': Checks if the integer part of the currentValue is an Armstrong number.
     *              - 'P': Checks if the integer part of the currentValue is a perfect number.
     * @return {@code true} if the integer part of the currentValue satisfies the given characteristic,
     *         {@code false} otherwise.
     * @throws NumberNotInAreaException if the integer part of currentValue is less than 1.
     * @throws NotSupportedOperationException if the value parameter is not 'A' or 'P'.
     */
    public Boolean hasCharacteristic(char value) {
    	Double currentValue = getCurrentValue();

        if (currentValue == null) {
            throw new NullPointerException("Current value cannot be null.");
        }

        int integerValue = currentValue.intValue();
        
        if (integerValue <= 0) {
            throw new NumberNotInAreaException("Integer part of currentValue must be >= 1.");
        }

        if (value == 'A') { 
            return isArmstrong(integerValue);
        } else if (value == 'P') { 
            return isPerfect(integerValue);
        } else { 
            throw new NotSupportedOperationException("Unsupported characteristic: " + value);
        }
    }

    /**
     * Checks if a number is an Armstrong number.
     * 
     * An Armstrong number is a number that is equal to the sum of its own digits
     * each raised to the power of the number of digits.
     * 
     * @param number the number to check.
     * @return {@code true} if the number is an Armstrong number, {@code false} otherwise.
     */
    private boolean isArmstrong(int number) {
    	int originalNumber = number;
        int sum = 0;
        int digits = 0;

        while (number != 0) {
        	number /= 10;
        	digits++;
        }
        
        number = originalNumber;

        while (number != 0) {
            int digit = number % 10;
            int digitPower = 1;
            for (int i = 0; i < digits && i >= 0; i++) {
                digitPower *= digit;
            }
            sum += digitPower;
            number /= 10;
        }
        return sum == originalNumber;
    }

    /**
     * Checks if a number is a perfect number.
     * 
     * A perfect number is a positive integer that is equal to the sum of its proper
     * divisors, excluding itself.
     * 
     * @param number the number to check.
     * @return {@code true} if the number is a perfect number, {@code false} otherwise.
     */
    private boolean isPerfect(int number) {
        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}