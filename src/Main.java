import Service.CalculatorService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию " +
                "в формате Аргумент1 действие(+, -, /, *) Аргумент2 (например 2+2) или (IV-III) :");

        String arithmeticOperation = scanner.nextLine();
        String operation = CalculatorService.arithmeticOperationCheck(arithmeticOperation);
        if ((arithmeticOperation.replaceAll("[+\\-*/]", "").matches("\\d+"))) {
            System.out.println(arithmeticOperation + "= " + CalculatorService.calculator(operation,arithmeticOperation));
        } else {
            String result = CalculatorService.calculator(operation,arithmeticOperation);
            System.out.println(arithmeticOperation + "= " + CalculatorService.convertToRoman(Integer.parseInt(result)));
        }
    }
}