import Service.CalculatorService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию " +
                "в формате Аргумент1 действие(+, -, /, *) Аргумент2 (например 2+2) :");
        String arithmeticOperation = scanner.nextLine();
        String operation = CalculatorService.arithmeticOperationCheck(arithmeticOperation);
        if ((arithmeticOperation.replaceAll("[+\\-*/]", "").matches("\\d+"))) {
            System.out.println(arithmeticOperation + "= " + CalculatorService.calculator(operation));
        } else {
            Integer result = CalculatorService.calculator(operation);
            System.out.println(arithmeticOperation + "= " + CalculatorService.convertToRoman(result));
        }
    }
}