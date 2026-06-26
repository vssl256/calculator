package org.example.calculator;

public class Calculator {

    public enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private enum State {
        ENTERING_LEFT_OPERAND,
        ENTERING_RIGHT_OPERAND
    }

    private double leftOperand = 0;
    private double rightOperand = 0;
    private Operator operator;
    private double answer = 0;
    private State state = State.ENTERING_LEFT_OPERAND;

    public double inputDigit( double digit ) {
        if ( state == State.ENTERING_LEFT_OPERAND ) {
            leftOperand = leftOperand * 10 + digit;
            answer = leftOperand;
        } else {
            rightOperand = rightOperand * 10 + digit;
            calculate();
        }
        return answer;
    }

    public double inputOperator( Operator op ) {
        operator = op;
        leftOperand = answer;
        rightOperand = 0;
        state = State.ENTERING_RIGHT_OPERAND;
        return answer;
    }

    public double evaluate() {
        double result = answer;
        leftOperand = answer;
        rightOperand = 0;
        state = State.ENTERING_RIGHT_OPERAND;
        return result;
    }

    public void reset() {
        leftOperand = 0;
        rightOperand = 0;
        operator = null;
        answer = 0;
        state = State.ENTERING_LEFT_OPERAND;
    }

    private void calculate() {
        if ( operator == null ) return;
        answer = switch ( operator ) {
            case ADD -> leftOperand + rightOperand;
            case SUBTRACT -> leftOperand - rightOperand;
            case MULTIPLY -> leftOperand * rightOperand;
            case DIVIDE -> leftOperand / rightOperand;
        };
    }
}
