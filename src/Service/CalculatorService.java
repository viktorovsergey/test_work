package Service;

import Exeptions.DifferentNumberSystems;
import Exeptions.IncorrectMathematicalOperation;
import Exeptions.NoMathematicalOperations;
import Exeptions.RomansNotUseFractionalNumbers;

import java.util.HashMap;
import java.util.Map;

public class CalculatorService {
    public static String arithmeticOperationCheck(String s) {
        String operation = "";
        if ((s.indexOf("+") + s.indexOf("-") + s.indexOf("/") + s.indexOf("*") < -3))
            throw new NoMathematicalOperations();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                count++;
            }
        }
        if (count != 1) throw new IncorrectMathematicalOperation();

        if ((s.replaceAll("[+\\-*/]", "").matches("\\d+"))) {
            operation = s;
        } else {
            if ((s.replaceAll("[+\\-*/]", "").matches("[IVX+\\-/*]+"))) {
               String marker= operatorSearch(s);
                String[] arguments = s.split("\\" + marker);
                operation =""+ convertToArabic(arguments[0])+marker+convertToArabic(arguments[1]);
            } else {
                throw new DifferentNumberSystems();
            }
        }
        return operation;
    }
public static String convertToArabic (String s){
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
    String itog=""+result;
    return itog;
}
    public static String convertToRoman(int num) {
        String[] roman = {"X","IX","V","IV","I"};
        int[] arabic = {10,9,5,4,1};
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
    public static String operatorSearch (String s){
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
    public static Integer calculator(String s){
        String marker = operatorSearch(s);
        String[] arguments = s.split("\\" + marker);
        Integer result=0;
        if (marker == "+") {
             result = Integer.parseInt(arguments[0]) + Integer.parseInt(arguments[1]);
           /* System.out.println(s + " = " + result);*/
        } else {
            if (marker == "-") {
                result =  Integer.parseInt(arguments[0]) - Integer.parseInt(arguments[1]);
                /*System.out.println(s + " = " + result);*/
            } else {
                if (marker == "/") {
                     result = Integer.parseInt(arguments[0]) / Integer.parseInt(arguments[1]);
                    /*System.out.println(s + " = " + result);*/
                } else {
                   result = Integer.parseInt(arguments[0]) * Integer.parseInt(arguments[1]);
                    /*System.out.println(s + " = " + result);*/
                }
            }

        }
        return result;
    }
}

