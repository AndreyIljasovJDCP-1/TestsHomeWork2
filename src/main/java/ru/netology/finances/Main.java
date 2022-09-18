package ru.netology.finances;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите общую сумму покупки: ");
        int amount = scan.nextInt();

        System.out.println("Введите сумму первоначального взноса: ");
        int start = scan.nextInt();

        System.out.println("Введите срок рассрочки(лет): ");
        int year = scan.nextInt();

        System.out.println("Ваш ежемесячный платеж составит: " + Finances.calcMonthlyPayment( amount,start, year) + " руб.");
        scan.close();


    }

}
