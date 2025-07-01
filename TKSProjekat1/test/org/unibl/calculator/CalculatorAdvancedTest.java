package org.unibl.calculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.unibl.exception.NotSupportedOperationException;
import org.unibl.exception.NumberNotInAreaException;

class CalculatorAdvancedTest {
	private CalculatorAdvanced calculator = new CalculatorAdvanced();

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
    void testCalculatorAdvanced() {
        assertThat(calculator, notNullValue());
    }

    static Stream<Arguments> validExponentiationTestData() {
        return Stream.of(
                Arguments.of(3.58, '2', 9.0),  
                Arguments.of(5.0, '0', 1.0),  
                Arguments.of(-1.0, '4', 1.0),  
                Arguments.of(-3.7, '3', -27.0),
                Arguments.of(0.0, '0', 1.0)
        );
    }

    @ParameterizedTest
    @MethodSource("validExponentiationTestData")
    void testCalculateAdvancedExponentiation(Double initialValue, char action, Double expected) {
        calculator.setCurrentValue(initialValue);
        calculator.calculateAdvanced(action);
        assertThat(calculator.getCurrentValue(), is(expected));
    }

    static Stream<Arguments> validFactorialTestData() {
        return Stream.of(
                Arguments.of(0.0, '!', 1.0),  
                Arguments.of(1.6, '!', 1.0),  
                Arguments.of(5.8, '!', 120.0), 
                Arguments.of(10.0, '!', 3628800.0) 
        );
    }

    @ParameterizedTest
    @MethodSource("validFactorialTestData")
    void testCalculateAdvancedFactorial(Double initialValue, char action, Double expected) {
        calculator.setCurrentValue(initialValue);
        calculator.calculateAdvanced(action);
        assertThat(calculator.getCurrentValue(), is(expected));
    }

    static Stream<Arguments> factorialOutOfRangeTestData() {
        return Stream.of(
                Arguments.of(11.0),  
                Arguments.of(-1.0)   
        );
    }

    @ParameterizedTest
    @MethodSource("factorialOutOfRangeTestData")
    void testFactorialOutOfRangeException(Double initialValue) {
        calculator.setCurrentValue(initialValue);
        assertThrows(NumberNotInAreaException.class, () -> calculator.calculateAdvanced('!'));
    }

    static Stream<Arguments> unsupportedActionTestData() {
        return Stream.of(
                Arguments.of(5.0, '@'), 
                Arguments.of(-3.0, 'A'),
                Arguments.of(12.4, (char) 47),
                Arguments.of(4.0, (char) 58)
        );
    }

    @ParameterizedTest
    @MethodSource("unsupportedActionTestData")
    void testUnsupportedAction(Double initialValue, char action) {
        calculator.setCurrentValue(initialValue);
        assertThrows(NotSupportedOperationException.class, () -> calculator.calculateAdvanced(action));
    }

    @Test
    void testNullCurrentValueAdvanced() {
        calculator.setCurrentValue(null);
        assertThrows(NullPointerException.class, () -> calculator.calculateAdvanced('2'));
    }

    static Stream<Arguments> hasCharacteristicTestData() {
        return Stream.of(
                Arguments.of(153.78, 'A', true),
                Arguments.of(123.0, 'A', false),
                Arguments.of(6.8, 'P', true),
                Arguments.of(12.789, 'P', false)
        );
    }

    @ParameterizedTest
    @MethodSource("hasCharacteristicTestData")
    void testHasCharacteristic(Double currentValue, char characteristic, boolean expected) {
        calculator.setCurrentValue(currentValue);
        assertEquals(expected, calculator.hasCharacteristic(characteristic));
    }

    static Stream<Arguments> unsupportedCharacteristicTestData() {
        return Stream.of(
                Arguments.of(10.456, 'X'),   
                Arguments.of(15.0, '1')    
        );
    }

    @ParameterizedTest
    @MethodSource("unsupportedCharacteristicTestData")
    void testUnsupportedCharacteristic(Double currentValue, char characteristic) {
        calculator.setCurrentValue(currentValue);
        assertThrows(NotSupportedOperationException.class, () -> calculator.hasCharacteristic(characteristic));
    }


    static Stream<Arguments> valueLessThanOneTestData() {
        return Stream.of(
                Arguments.of(0.0, 'A'),  
                Arguments.of(0.0, 'P'),   
                Arguments.of(-5.7, 'A'),  
                Arguments.of(-5.5, 'P')
        );
    }
    
    @ParameterizedTest
    @MethodSource("valueLessThanOneTestData")
    void testValueLessThanOne(Double currentValue, char characteristic) {
    	calculator.setCurrentValue(currentValue);
    	assertThrows(NumberNotInAreaException.class, () -> calculator.hasCharacteristic(characteristic));
    }
    
    @Test
    void testNullCurrentValueCharacteristic() {
        calculator.setCurrentValue(null);
        assertThrows(NullPointerException.class, () -> calculator.hasCharacteristic('A'));
    }
}

