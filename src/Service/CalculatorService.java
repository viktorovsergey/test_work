package Service;

import Exeptions.*;

import java.util.HashMap;
import java.util.Map;

public class CalculatorService {
    public static String arithmeticOperationCheck(String s) {
        String operation = "";
        if (((s.indexOf("+") + s.indexOf("-") + s.indexOf("/") + s.indexOf("*")) < -3)) throw new NoMathematicalOperations();
            /* Проверка на наличие арифметических операторов в операции и
            проверка на наличие агрументов (арабские или римские цифры*/
        String[] operationLenght = s.split("\\" + operatorSearch(s));
        if (operationLenght.length<2) throw new IncorrectMathematicalOperation();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                count++;
            }
        }
        if (count != 1) throw new IncorrectMathematicalOperation();
            /* проверка на количество операторов т.е. соответствию задания(два операнда один оператор)*/

            if ((s.replaceAll("[+\\-*/]", "").matches("\\d+"))) {
            operation = s;
                  } else {
                      if ((s.replaceAll("[+\\-*/]", "").matches("[IVX]+"))) {
                         String marker = operatorSearch(s);
                         String[] arguments = s.split("\\" + marker);
                         operation = "" + convertToArabic(arguments[0]) + marker + convertToArabic(arguments[1]);
            } else {
                throw new DifferentNumberSystems();
            }
              /* проверка на соответствие операндов одной сиситемы исчисления
              и перевод операндов из Римской системы исчисления в арабскую для проведения дальнейших операций*/
        }
        return operation;
    }
    /* метод, который переводит из Римской системы счисления в арабскую*/
    public static String convertToArabic(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1 && map.containsKey(s.substring(i, i + 2))) {
                result += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                result += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        String itog = "" + result;
        return itog;
    }

    /*метод, переводящий из арабской системы счисления в римскую*/
    public static String convertToRoman(int num) {
        String[] roman = {"X", "IX", "V", "IV", "I"};
        int[] arabic = {10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (num > 0) {
            int count = num / arabic[i];
            for (int j = 0; j < count; j++) {
                result.append(roman[i]);
                num -= arabic[i];
            }
            i++;
        }
        return result.toString();
    }
/* метод, который осуществляет поиск и распознавание в строковой переменной оператора*/
    public static String operatorSearch(String s) {
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
 /* метод выполняющий арифметические опреации*/
    public static String calculator(String s, String orgonalString) {
        String marker = operatorSearch(s);
        String[] arguments = s.split("\\" + marker);
        String result = "";
        if (marker == "+") {
            result = "" + (Integer.parseInt(arguments[0]) + Integer.parseInt(arguments[1]));
        } else {
            if (marker == "-") {
                if ((Integer.parseInt(arguments[0])<Integer.parseInt(arguments[1]))&&
                        (orgonalString.replaceAll("[+\\-*/]", "").matches("[IVX]+"))){
                    throw new RomanSystemNoNegativeNumber();
                }else {
                result = "" + (Integer.parseInt(arguments[0]) - Integer.parseInt(arguments[1]));}

            } else {
                if (marker == "/") {
                    if ((Integer.parseInt(arguments[0]) % Integer.parseInt(arguments[1]) != 0) &&
                            (orgonalString.replaceAll("[+\\-*/]", "").matches("[IVX]+")))
                        throw new RomansNotUseFractionalNumbers();
                    double arg0 = Integer.parseInt(arguments[0]);
                    double arg1 = Integer.parseInt(arguments[1]);
                    result = "" + Integer.valueOf((int) (arg0 / arg1));

                } else {
                    result = "" + (Integer.parseInt(arguments[0]) / Integer.parseInt(arguments[1]));

                }
            }

        }
        return result;
    }
}

