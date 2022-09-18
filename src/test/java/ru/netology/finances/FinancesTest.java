package ru.netology.finances;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Тест класса Finances.")
@TestMethodOrder(MethodOrderer.DisplayName.class)
class FinancesTest {


    @Test
    @DisplayName("Тест: Платеж=0, если взнос больше суммы покупки")
    void calcPaymentReturnZero() {
        Assertions.assertEquals(0, Finances.calcMonthlyPayment(1000, 5000, 1));
    }

    @DisplayName("Тест: Расчет ежемесячного платежа")
    @ParameterizedTest
    @MethodSource("getArguments")
    void calcPayment(int amount, int start, int year, int expectedResult) {
        Assertions.assertEquals(expectedResult, Finances.calcMonthlyPayment(amount, start, year));
    }

    @DisplayName("Проброс ошибки, если данные некорректны")
    @ParameterizedTest
    @MethodSource("getArgumentsException")
    void calcMonthlyPaymentThrowException(int amount, int start, int year) {
        Assertions.assertThrows(RuntimeException.class, () -> Finances.calcMonthlyPayment(amount, start, year));
    }

    @Test
    @DisplayName("Exception in order: Сумма покупки <= 0")
    void exceptionTestingInOrderAmount() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                Finances.calcMonthlyPayment(0, 0, 0));
        Assertions.assertEquals("Сумма покупки должна быть больше 0. Вы ввели -> 0",
                exception.getMessage());
    }

    @Test
    @DisplayName("Exception in order: Сумма взноса < 0")
    void exceptionTestingInOrderStart() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                Finances.calcMonthlyPayment(1, -1, 0));
        Assertions.assertEquals("Сумма первоначального взноса должна быть больше или равно 0. Вы ввели -> -1",
                exception.getMessage());
    }

    @Test
    @DisplayName("Exception in order: Срок рассрочки <= 0")
    void exceptionTestingInOrderYear() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                Finances.calcMonthlyPayment(1, 0, 0));
        Assertions.assertEquals("Срок рассрочки должен быть больше 0. Вы ввели -> 0",
                exception.getMessage());
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(10000, 1000, 1, 750),
                Arguments.of(10000, 2000, 2, 333),
                Arguments.of(1800, 600, 1, 100)
        );
    }

    private static Stream<Arguments> getArgumentsException() {
        return Stream.of(
                Arguments.of(0, 1, 1),
                Arguments.of(1, -1, 0),
                Arguments.of(1, 1, 0),
                Arguments.of(-1, -1, -1)
        );
    }
}
