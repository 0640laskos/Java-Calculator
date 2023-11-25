import java.util.Stack;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(); // sadece integer değerler alabilen stack adında bir Stack oluşturdum
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("E")) { // çıkış
                break;
            } else if (isInteger(input)) {
                // girilen değer integer ise stack e atmak için
                stack.push(Integer.parseInt(input));
            } else if (isOperator(input)) {
                if (stack.size() < 2) { // en az iki sayı girilmediyse uyarı göndermek için
                    System.out.println("Not enough operands for the operator " + input);
                } else { // iki değer girildiyse stackten değerleri pop()lamak için
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    int result = performOperation(operand1, operand2, input); // input a göre işlemi sonuca eşitlemek için
                    stack.push(result);
                    System.out.println("Result is " + result);
                }
            } else {
                System.out.println("Invalid operator");
            }
        }

        scanner.close();
    }

    private static boolean isInteger(String s) { //girilen değerin integer olup olmadığına karar vermek için bool fonksiyon
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String s) { // girilen matematiksel operatörün geçerli olup olmadığını aşağıdaki işaretlere göre belirlemek için
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private static int performOperation(int operand1, int operand2, String operator) { // operatör işlemleri için
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {  // bir sayıyı sıfıra bölemeyeceğimiz için
                    System.out.println("You cannot divide any number by 0!");
                    System.exit(1);
                }
                return operand1 / operand2;
            default:
                System.out.println("Invalid operator");
                return 0;
        }
    }
}
