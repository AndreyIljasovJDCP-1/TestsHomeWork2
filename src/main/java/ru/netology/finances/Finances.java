package ru.netology.finances;


public class Finances {

    public static int calcMonthlyPayment(int amount, int start, int year) {
        if (amount <= 0) {
            throw new RuntimeException("Сумма покупки должна быть больше 0. "
                    + "Вы ввели -> " + amount);
        } else if (start < 0) {
            throw new RuntimeException("Сумма первоначального взноса должна быть больше или равно 0. "
                    + "Вы ввели -> " + start);
        } else if (year <= 0) {
            throw new RuntimeException("Срок рассрочки должен быть больше 0. "
                    + "Вы ввели -> " + year);
        }
        return (amount - start) > 0 ? (amount - start) / (year * 12) : 0;
    }
}
