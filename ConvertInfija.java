import java.util.Stack;

class InfixToPostfixConverter {
    public static int getPrecedence(char c) {
        if (c == '*' || c == '/') {
            return 3;
        }
        if (c == '+' || c == '-') {
            return 4;
        }
        if (c == '&') {
            return 8;
        }
        if (c == '^') {
            return 9;
        }
        if (c == '|') {
            return 10;
        }
        return Integer.MAX_VALUE;
    }

    public static boolean isOperand(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public static String convertInfixToPostfix(String infix) {
        if (infix == null || infix.length() == 0) {
            return infix;
        }

        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfixExpression = new StringBuilder();

        for (char currentToken : infix.toCharArray()) {
            if (currentToken == '(') {
                operatorStack.push(currentToken);
            } else if (currentToken == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else if (isOperand(currentToken)) {
                postfixExpression.append(currentToken);
            } else {
                while (!operatorStack.isEmpty() && getPrecedence(currentToken) >= getPrecedence(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.push(currentToken);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop());
        }

        return postfixExpression.toString();
    }

    public static void main(String[] args) {
        String infixExpression = "((A*17)(B*C+D*E)+F-9)";
        String postfixExpression = convertInfixToPostfix(infixExpression);
        System.out.println("Expresión Infix: " + infixExpression);
        System.out.println("Expresión Postfix: " + postfixExpression);
    }
}
