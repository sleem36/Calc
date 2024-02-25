import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("ведите свой пример");
        String d = s.nextLine();
        String x = calc(d);
        System.out.println("Получилось: " + x);
    }


    public static String calc(String input) {
        String var_left = null; // число слева от знака
        String var_right = null; // число справа от знака
        int znak = 0; // знак между числами
        int result = 0; // результат до перевода
        String[] values = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        int rim = 0; // rim = 1 если выводим ответ римскими цифрами
        String input_return; // результат римскими или арабскими
                
        CalcRimNum calcDopLeft = new CalcRimNum();
        CalcRimNum calcDopRight = new CalcRimNum();

        RestrictedRangeExample rangeNum = new RestrictedRangeExample();

        if (input.contains("*")) {
            String[] proizvedenie = input.split("\\*"); // делим строку на две части
            var_left = proizvedenie[0].trim(); // присваиваем переменную и убираем пробелы
            var_right = proizvedenie[1].trim();
            znak = 1;
        }

        if (input.contains("/")) {
            String[] proizvedenie = input.split("/"); // делим строку на две части
            var_left = proizvedenie[0].trim(); // присваиваем переменную и убираем пробелы
            var_right = proizvedenie[1].trim();
            znak = 2;
        }

        if (input.contains("+")) {
            String[] proizvedenie = input.split("\\+"); // делим строку на две части
            var_left = proizvedenie[0].trim(); // присваиваем переменную и убираем пробелы
            var_right = proizvedenie[1].trim();
            znak = 3;
        }

        if (input.contains("-")) {
            String[] proizvedenie = input.split("-"); // делим строку на две части
            var_left = proizvedenie[0].trim(); // присваиваем переменную и убираем пробелы
            var_right = proizvedenie[1].trim();
            znak = 4;
        }

        if(Arrays.asList(values).contains(var_left)
                || Arrays.asList(values).contains(var_right)){ // Левая или правая часть это римское число
            if(Arrays.asList(values).contains(var_left)
                    && Arrays.asList(values).contains(var_right)) { // оба числа должны быть - римские цифры
                rim = 1; // rim = 1 если выводим ответ римскими цифрами
            }
            else {
                throw new IllegalArgumentException("Либо оба числа римские цифры - либо оба арабские цифры!!!");
            }
        }


        String s1 = calcDopLeft.calc_left(var_left); // переводим римские цифры в арабские левой части
        String s2 = calcDopRight.calc_right(var_right); // переводим римские цифры в арабские правой части

        isFloat(s1);// проверка целого числа
        isFloat(s2);// проверка целого числа

        int value1 = Integer.parseInt(s1); // переводим строку в число
        int value2 = Integer.parseInt(s2);

        rangeNum.setAnumber(value1); // проверка что число не больше 10
        rangeNum.setAnumber(value2); // проверка что число не больше 10

        if(znak == 1){
            result = (value1 * value2);
        } else if (znak == 2) {
            result = (value1 / value2);
        } else if (znak == 3) {
            result = (value1 + value2);
        } else if (znak == 4) {
            result = (value1 - value2);
        }

        if (rim == 1){ // если ответ это римская цифра
            if(result <= 0){
                throw new IllegalArgumentException(" Результатом работы калькулятора с римскими числами могут быть только положительные числа!!!");
            } else {
                input_return = IntegerToRomanNumeral(result);
            }
        } else {
            input_return = Integer.toString(result);
        }

        return input_return;
    }


    public static boolean isFloat(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Можно ввести только целые числа!!!");
        }
    }

    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 100)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}




class RestrictedRangeExample {  // проверка что число не больше 10
    private int anumber;
    public void setAnumber(int newanumber) {
        if (newanumber >= 1 && newanumber <= 10) {
            anumber = newanumber;
        } else {
            throw new IllegalArgumentException("Можно ввести число не более 10!!!");
        }
    }
}

class CalcRimNum{ // римские цифры в арабские
    String calc_left(String var_left){
        switch(var_left){
            case "I" :
                var_left = "1";
                break;
            case "II" :
                var_left = "2";
                break;
            case "III" :
                var_left = "3";
                break;
            case "IV" :
                var_left = "4";
                break;
            case "V" :
                var_left = "5";
                break;
            case "VI" :
                var_left = "6";
                break;
            case "VII" :
                var_left = "7";
                break;
            case "VIII" :
                var_left = "8";
                break;
            case "IX" :
                var_left = "9";
                break;
            case "X" :
                var_left = "10";
                break;
        }
        return var_left;
    }

    String calc_right(String var_right){
        switch(var_right){
            case "I" :
                var_right = "1";
                break;
            case "II" :
                var_right = "2";
                break;
            case "III" :
                var_right = "3";
                break;
            case "IV" :
                var_right = "4";
                break;
            case "V" :
                var_right = "5";
                break;
            case "VI" :
                var_right = "6";
                break;
            case "VII" :
                var_right = "7";
                break;
            case "VIII" :
                var_right = "8";
                break;
            case "IX" :
                var_right = "9";
                break;
            case "X" :
                var_right = "10";
                break;
        }
        return var_right;
    }
}

