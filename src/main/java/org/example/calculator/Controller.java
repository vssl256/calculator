package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    protected TextField textField;
    protected TextFormatter<String> formatter;

    @FXML
    protected Text answerText;

    @FXML
    protected Button resetButton;

    private Calculator calculator;

    @FXML
    protected void initialize() {
        formatter = new TextFormatter<>( change -> {
            if ( change.getControlNewText().matches( "[0-9+\\-*/.]*" ) ) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter( formatter );
        calculator = new Calculator();
    }

    @FXML
    protected void onKeyTyped( KeyEvent event ) {
        String ch = event.getCharacter();

        if ( ch.matches( "[0-9]" ) ) {
            double answer = calculator.inputDigit( Double.parseDouble( ch ) );
            answerText.setText( String.valueOf( answer ) );
            return;
        }

        Calculator.Operator op = switch ( ch ) {
            case "+" -> Calculator.Operator.ADD;
            case "-" -> Calculator.Operator.SUBTRACT;
            case "*" -> Calculator.Operator.MULTIPLY;
            case "/" -> Calculator.Operator.DIVIDE;
            default  -> null;
        };

        if ( op != null ) {
            calculator.inputOperator( op );
        }
    }

    @FXML
    protected void onAction() {
        double answer = calculator.evaluate();
        textField.setText( String.valueOf( answer ) );
        textField.end();
    }


    @FXML
    protected void reset() {
        textField.setText( "" );
        answerText.setText( "" );
        calculator.reset();
    }
}
