package org.unibl.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.exception.DivisionByZeroException;
import org.unibl.exception.NotSupportedOperationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;

class CalculatorTest {
	private Calculator calculator = new Calculator();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculator() {
		assertThat(calculator, notNullValue());
	}

	@Test
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(0.0)));
	}

	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(11.3);
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(11.3)));
	}
	
	@Test
	void testSetCurrentValueWithNull() {
	    calculator.setCurrentValue(null);
	    assertThat(calculator.getCurrentValue(), is(nullValue()));
	}
	
    static Stream<Arguments> operatorTestData() {
        return Stream.of(
                Arguments.of(10.0, 5.0, '+', 15.0),
                Arguments.of(-10.0, 3.0, '-', -13.0),
                Arguments.of(4.0, -2.5, '*', -10.0),
                Arguments.of(-20.0, -4.0, '/', 5.0)
        );
    }

    @ParameterizedTest
    @MethodSource("operatorTestData")
    void testCalculateValidOperators(Double initialValue, Double value, char operator, Double expected) throws Exception {
        calculator.setCurrentValue(initialValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(expected));
    }

    static Stream<Arguments> divisionByZeroTestData() {
    	return Stream.of(
    			Arguments.of(10.0, 0.0),
    			Arguments.of(-1.4, 0.0),
    			Arguments.of(0.0, 0.0)
    			);
    }
    
    @ParameterizedTest
    @MethodSource("divisionByZeroTestData")
    void testCalculateDivisionByZero(Double initialValue, Double value) {
        calculator.setCurrentValue(initialValue);
        assertThrows(DivisionByZeroException.class, () -> calculator.calculate(value, '/'));
    }


    static Stream<Arguments> unsupportedOperatorTestData() {
        return Stream.of(
                Arguments.of(10.0, -5.76, '%'),
                Arguments.of(0.0, 3.0, '^')
        );
    }
    
    @ParameterizedTest
    @MethodSource("unsupportedOperatorTestData")
    void testCalculateUnsupportedOperator(Double initialValue, Double value, char operator) {
    	calculator.setCurrentValue(initialValue);
    	assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(value, operator));
    }
    
    static Stream<Arguments> nullOperandsTestData() {
    	return Stream.of(
    			Arguments.of(1.0, null, '+'),
    			Arguments.of(-20.4, null, '-'),
    			Arguments.of(0.0, null, '*'),
    			Arguments.of(null, -10.5, '+'),
    			Arguments.of(null, 20.4, '/'),
    			Arguments.of(null, 0.0, '*')
    			);
    }
    
    @ParameterizedTest
    @MethodSource("nullOperandsTestData")
    void testCalculateNullOperands(Double initialValue, Double value, char operator) {
        calculator.setCurrentValue(initialValue);
        assertThrows(NullPointerException.class, () -> calculator.calculate(value, operator));
    }
}
