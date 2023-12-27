package p8;
import java.util.Deque;
import java.util.LinkedList;

public class GetValueOfPostFix {
    public static void main(String[] args) {
        String[] a = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(new GetValueOfPostFix().getValue(a));
    }
    public double getValue(String tokens){
        String[] token = tokens.split("");
        return getValue(token);
    }
    public double getValue(String[] tokens) {
        Deque<Double> stack = new LinkedList<>();
        int n = tokens.length;
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble (token));
            } else {
                get(stack, token);
            }
        }
        return stack.pop();
    }

    private void get(Deque<Double> stack, String token) {
        double num2 = stack.pop();
        double num1 = stack.pop();
        switch (token) {
            case "+" -> stack.push(num1 + num2);
            case "-" -> stack.push(num1 - num2);
            case "*" -> stack.push(num1 * num2);
            case "/" -> stack.push(num1 / num2);
            default -> {
            }
        }
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
