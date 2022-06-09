import java.util.Scanner;

public class Calc {

    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;
    static String pattern1 = "\\s*(C|XC|XL|L?X{0,3})(IX|IV|V?I{0,3})\\s*\\W\\s*(C|XC|XL|L?X{0,3})(IX|IV|V?I{0,3})\\s*";
    static String pattern2 = "\\s*(\\d{1,2})\\s*\\W\\s*(\\d{1,2})\\s*";


    public static void main(String[] args) throws RomeArithmeticException, IllegalArgumentException {

        System.out.println("введите арифметический пример арабскими или римскими цифрами от 1 до 10.");
        String userInputNumber = scanner.nextLine();

        char[] chars = userInputNumber.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = userInputNumber.charAt(i);
            if (chars[i] == '+') operation = '+';
            if (chars[i] == '-') operation = '-';
            if (chars[i] == '*') operation = '*';
            if (chars[i] == '/') operation = '/';
        }


        if (userInputNumber.matches(pattern1)) {
            String s = userInputNumber.replaceAll("\\s", "");
            String[] subStr = s.split("\\W");
            String s1 = subStr[0];
            String s2 = subStr[1];
            RomeNumber rn1 = RomeNumber.valueOf(s1);
            RomeNumber rn2 = RomeNumber.valueOf(s2);
            number1 = rn1.getNum();
            number2 = rn2.getNum();
            result = calculatedRoman(number1, number2, operation);
            System.out.println(RomeNumber.values()[result - 1] + " " + "(" + result + ")");
        } else if (userInputNumber.matches(pattern2)) {
            String s = userInputNumber.replaceAll("\\s", "");
            String[] subStr = s.split("\\W");
            number1 = Integer.parseInt(subStr[0]);
            number2 = Integer.parseInt(subStr[1]);
            if (number1 == 0 || number2 == 0 || number1 > 10 || number2 > 10) {
                throw new IllegalArgumentException("Введены некоректные данные");
            } else {
                result = calculatedArabian(number1, number2, operation);
                System.out.println(result);
            }

        } else {
            throw new IllegalArgumentException("Введены некоректные данные");

        }

    }

    public static int calculatedRoman(int number1, int number2, char operation) throws RomeArithmeticException {

        switch (operation) {
            case '+' -> result = number1 + number2;
            case '-' -> {
                if (number2 >= number1) {
                    throw new RomeArithmeticException("Результат вычисления римских чисел не может быть меньше 1");
                }
                result = number1 - number2;
            }
            case '*' -> result = number1 * number2;
            case '/' -> {
                if (number2 > number1) {
                    throw new RomeArithmeticException("Результат вычисления римских чисел не может быть меньше 1");
                }
                result = number1 / number2;
            }
        }
        return result;
    }

    public static int calculatedArabian(int number1, int number2, char operation) {

        switch (operation) {
            case '+' -> result = number1 + number2;
            case '-' -> result = number1 - number2;
            case '*' -> result = number1 * number2;
            case '/' -> result = number1 / number2;
        }
        return result;
    }

}