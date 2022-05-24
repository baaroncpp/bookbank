package com.bkbwongo.bookbank;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class BookBankApplicationTests {

	class Calculator{
		int add(int a, int b){
			return a + b;
		}
	}

	@Test
	void itShouldAddTwoNumbers(){

		Calculator underTest = new Calculator();

		//given
		int numberOne = 30;
		int numberTwo = 20;

		//when
		int result = underTest.add(numberOne, numberTwo);

		//then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}

}
