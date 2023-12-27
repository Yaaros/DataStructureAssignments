package p8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 这是 实验2 的代码,也可以用来求普通的中缀表达式
 */
public class GetValueOfNiFix {

    public double get(Stack<Double> stack, String op){
        double b = stack.pop();
        double a = stack.pop();
        if(b==0&&op.equals("/")){
            throw new ArithmeticException("除数不能为0");
        }
        return switch (op) {
            case "+"-> a+b;
            case "-"-> a-b;
            case "*"-> a*b;
            case "/"-> a/b;
            case "%"-> a%b;
            case "^"-> Math.pow(a,b);
            default -> 0;
        };
    }
    public int getPriority(String op){
        if(op==null){
            return -1;
        }
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "%", "/" -> 2;
            case "^"-> 3;
            case "(" -> 4;
            default -> 0;
        };
    }

    public void isValid(String token) throws ArithmeticException{
        List<Character> validChars =
                List.of('(',')','0','1','2','3','4','5','6','7','8','9','.','#','+','-','*','/','%','^');
        Stack<Character> stack = new Stack<>();
        for (char c : token.toCharArray()) {
            if(!validChars.contains(c)){
                throw new ArithmeticException("非法字符");
            }
            if(c=='('){
                stack.push(c);
            }
            else if(c==')'){
                if(stack.isEmpty()){
                    throw new ArithmeticException("括号不成对");
                }
                else{
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()){
            throw new ArithmeticException("括号不成对");
        }
    }

    public double BasicCalculate(String token) throws ArithmeticException{
        System.out.println("对于表达式:"+token);
        isValid(token);
        String[] tokens = token.split("");
        Stack<Double> numStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        String last = "";//上一个是什么
        int i = 0;
        for (String s : tokens) {
            i++;
            System.out.println("第"+i+"轮:");
            System.out.println("上一个字符last="+last);
            System.out.println("上一次处理后的numStack="+numStack);
            System.out.println("上一次处理后的opStack="+operatorStack);
            if(s.equals("#")){
                break;
            }
            if(s.equals(".")){
                last = s;
                continue;
            }
            if(Objects.equals(s, ")")){
                while(!Objects.equals(operatorStack.peek(), "(")){
                    String pop = operatorStack.pop();
                    numStack.push(get(numStack,pop));
                }
                operatorStack.pop();
            }
            else if(s.matches("\\d")){
                double d = Double.parseDouble(s);
                if(last.matches("\\d")){
                    double peek = numStack.pop();
                    if(peek%1==0){//整数情况，把之前的乘10加上这个
                        d += peek*10;
                        numStack.push(d);
                    }else{//小数情况，把这个除以10加过去
                        peek += d/10;
                        numStack.push(peek);
                    }
                } else if (last.equals(".")) {
                    double temp = numStack.pop();
                    temp += d/10;
                    numStack.push(temp);
                }else{
                    numStack.push(d);
                }
            }else{
                if(operatorStack.isEmpty()){
                    operatorStack.push(s);
                    last = s;
                    continue;
                }
                String peek = operatorStack.peek();
                if(getPriority(s)>getPriority(peek)|| Objects.equals(peek, "(")){
                    operatorStack.push(s);
                }
                else{
                    String pop = operatorStack.pop();
                    numStack.push(get(numStack,pop));
                    operatorStack.push(s);
                }
            }
            last = s;
        }
        while(!operatorStack.isEmpty()){
            numStack.push(get(numStack,operatorStack.pop()));
        }
        return numStack.pop();
    }
    @Test
    public void test(){
        System.out.println(BasicCalculate("3.5*(20+4)-1#"));
        System.out.println(BasicCalculate("1+2.5*(3.5+4)"));
        //System.out.println(BasicCalculate("5tyeh3iuqwqi"));
    }
}
