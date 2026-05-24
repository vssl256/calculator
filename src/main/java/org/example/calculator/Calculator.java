package org.example.calculator;

public class Calculator {

    private static final String DIGITS = "0123456789.";
    private static final String OPERATORS = "+-*/";

    private static boolean isDigit( String input ) {
        return DIGITS.contains( input );
    }

    private static boolean isOperator( String input ) {
        return OPERATORS.contains( input );
    }

    private enum CalculatorStates {
        ENTERING_LEFT_OPERAND,
        ENTERING_RIGHT_OPERAND
    }

    private String leftOperandBuffer = "";
    private String rightOperandBuffer = "";
    private double leftOperand;
    private double rightOperand;
    private char operator;
    private double answer = 0;
    private CalculatorStates state = CalculatorStates.ENTERING_LEFT_OPERAND;

    public double input( String input ) {
        switch ( state ) {
            case ENTERING_LEFT_OPERAND -> handleLeftOperand( input );
            case ENTERING_RIGHT_OPERAND -> handleRightOperand( input );
        }
        System.out.println( leftOperandBuffer + " " + operator + " " + rightOperandBuffer );
        return answer;
    }

    private void handleLeftOperand( String input ) {
        System.out.println(state);
        if ( isDigit( input ) ) {
            leftOperandBuffer += input;
            answer = parseDouble( leftOperandBuffer );
            return;
        }
        if ( isOperator( input ) ) {
            leftOperand = answer;
            operator = input.charAt( 0 );
            state = CalculatorStates.ENTERING_RIGHT_OPERAND;
        }
    }

    private void handleRightOperand( String input ) {
        System.out.println(state);
        if ( isDigit( input ) ) {
            rightOperandBuffer += input;
            rightOperand = parseDouble( rightOperandBuffer );
            calculate();
        }
        if ( isOperator( input ) ) {
            rightOperandBuffer = "";
            operator = input.charAt( 0 );
            leftOperand = answer;
        }
    }

    private void calculate() {
        answer = switch ( operator ) {
            case '+' -> leftOperand + rightOperand;
            case '-' -> leftOperand - rightOperand;
            case '*' -> leftOperand * rightOperand;
            case '/' -> leftOperand / rightOperand;
            default -> 0;
        };
    }

    public double getAnswer() {
        rightOperandBuffer = "";
        return answer;
    }

    public void reset() {
        leftOperandBuffer = "";
        rightOperandBuffer = "";

        leftOperand = 0;
        rightOperand = 0;

        operator = ' ';
        answer = 0;

        state = CalculatorStates.ENTERING_LEFT_OPERAND;
    }

    private static double parseDouble( String string ) {
        try {
            return Double.parseDouble( string );
        } catch ( NumberFormatException e ) {
            System.err.println( e );
            return 0;
        }
    }
}
