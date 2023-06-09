package Service;

import Exeptions.DifferentNumberSystems;
import Exeptions.IncorrectMathematicalOperation;
import Exeptions.NoMathematicalOperations;

public class CalculatorService {
    public static int countChars(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                count++;
            }
        }
        return count;
    }
    public static String operatorSearch(String s) {
        if ((s.indexOf("+") + s.indexOf("-") + s.indexOf("/") + s.indexOf("*") < -3))
            throw new NoMathematicalOperations();
        if (countChars(s) != 1) throw new IncorrectMathematicalOperation();
        if (!(s.replaceAll("[+\\-*/]", "").matches("\\d+"))) throw new DifferentNumberSystems();

        String marker = "+";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                marker = "-";
            } else {
                if (s.charAt(i) == '/') {
                    marker = "/";
                } else {
                    if (s.charAt(i) == '*')
                        marker = "*";
                }
            }
        }
        return marker;
    }
    public static void Calculator(String s) {
        String marker = CalculatorService.operatorSearch(s);
        String[] arguments = s.split("\\" + marker);
        if (marker == "+") {
            Integer result = Integer.parseInt(arguments[0]) + Integer.parseInt(arguments[1]);
            System.out.println(s + " = " + result);
        } else {
            if (marker == "-") {
                Integer result = Integer.parseInt(arguments[0]) - Integer.parseInt(arguments[1]);
                System.out.println(s + " = " + result);
            } else {
                if (marker == "/") {
                    Integer result = Integer.parseInt(arguments[0]) / Integer.parseInt(arguments[1]);
                    System.out.println(s + " = " + result);
                } else {
                    Integer result = Integer.parseInt(arguments[0]) * Integer.parseInt(arguments[1]);
                    System.out.println(s + " = " + result);
                }
            }
        }
    }
}
