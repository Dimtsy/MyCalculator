import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String operation = null;
        String value1 = null;
        String value2 = null;
        System.out.println("Введите выражение:");

        Scanner scanner = new Scanner(System.in);

        try {
            while (scanner.hasNext()) {

                Pattern pattern = Pattern.compile("[0-9]*[.,]?[0-9]+");
                String inputs = scanner.nextLine();
                Matcher matcher = pattern.matcher(inputs);
                int flag = 0;
                String pat = null;
                while (matcher.find()) {
                    if (flag == 0) {
                        if (matcher.start() >= 0) {  //арабские
                            value1 = matcher.group();
                            flag++;
                        }
                    } else {
                        if (flag == 1) {
                            if (matcher.start() >= 0) {  //арабские
                                value2 = matcher.group();
                                flag++;
                            }
                        } else {
                            throw new MyExeption("формат математической операции не удовлетворяет" +
                                    " заданию - два операнда и один оператор (+, -, /, *)");
                        }
                    }
                    if (flag == 2) {
                        pat = "([0-9]*[.,]?[0-9]+)(\\s*[*/+-]\\s*)([0-9]*[.,]?[0-9]+)";
                    }
                }

                Pattern pattern2 = Pattern.compile("[IVXLDMC]+");
                Matcher matcher2 = pattern2.matcher(inputs);
                int flag2 = 0;
                while (matcher2.find()) {
                    if (flag == 1) throw new MyExeption("используются одновременно разные системы счисления");
                    if (flag2 == 0) {
                        if (matcher2.start() >= 0) {  //римские
                            value1 = matcher2.group();
                            flag2++;
                        }
                    } else {
                        if (flag2 == 1) {
                            if (matcher2.start() >= 0) {  //римские
                                value2 = matcher2.group();
                                flag2++;
                            }
                        } else {
                            throw new MyExeption("формат математической операции не удовлетворяет заданию" +
                                    " - два операнда и один оператор (+, -, /, *)");
                        }
                    }
                    if (flag2 == 2) {
                        pat = "([IVXLDMC]+)(\\s*[*/+-]\\s*)([IVXLDMC]+)";
                    }
                }
                if ((flag == 0 & flag2 == 0) | (flag == 0 & flag2 == 1) | (flag == 1 & flag2 == 0) |
                        (pat == null)) {
                    throw new MyExeption("строка не является математической операцией");
                }

                Pattern pattern3 = Pattern.compile(pat);
                Matcher matcher3 = pattern3.matcher(inputs);
                while (matcher3.find()) {
                    operation = matcher3.group(2);
                }

                if (flag == 2) {
                    int val1;
                    int val2;
                    try {
                        val1 = Integer.parseInt(value1);
                        val2 = Integer.parseInt(value2);
                    } catch (Exception e) {
                        throw new MyExeption("калькулятор умеет работать только с целыми числами");
                    }
                    int romAnswer = ActionArab(val1, val2, operation);
                    System.out.println(value1 + " " + operation + " " + value2 + " = " + romAnswer);
                }
                if (flag2 == 2) {
                    int rom1 = TransformationRomArab.romToArab(value1);
                    int rom2 = TransformationRomArab.romToArab(value2);
                    String arabAnswer = TransformationRomArab.arabToRom(ActionArab(rom1, rom2, operation));
                    System.out.println(value1 + " " + operation + " " + value2 + " = " + arabAnswer);
                }
            }

        } catch (Exception | MyExeption e) {
            System.out.println("Exception //" + e.getMessage());
        }
        scanner.close();

    }

    public static int ActionArab(int value1, int value2, String operation) throws IOException, MyExeption {
        int answer = 0;

        if (value1 < 0 | value1 > 10 | value2 < 0 | value2 > 10) {
            throw new MyExeption("калькулятор может принимать на вход числа от 1 до 10 включительно, не более.");
        }

        switch (operation) {
            case "+":
                answer = value1 + value2;
                break;
            case "-":
                answer = value1 - value2;
                break;
            case "*":
                answer = value1 * value2;
                break;
            case "/":
                answer = value1 / value2;
        }
        return answer;
    }
}
