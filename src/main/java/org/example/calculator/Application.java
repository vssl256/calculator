package org.example.calculator;

import atlantafx.base.theme.CupertinoDark;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private static final String MAIN_VIEW = "view.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start( Stage stage ) throws IOException {
        Application.setUserAgentStylesheet( new CupertinoDark().getUserAgentStylesheet() );

        FXMLLoader fxmlLoader = new FXMLLoader( Application.class.getResource( MAIN_VIEW ) );
        Scene scene = new Scene( fxmlLoader.load(), 320, 240 );
        stage.setTitle( "Calculator" );
        stage.setScene( scene );
        stage.show();
    }
}
