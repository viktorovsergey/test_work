import Service.CalculatorService;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию " +
                "в формате Аргумент1 действие(+, -, /, *) Аргумент2 (например 2+2) :");
        String arithmeticOperation = scanner.nextLine();
        CalculatorService.Calculator(arithmeticOperation);
    }

}


