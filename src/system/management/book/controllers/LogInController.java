package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.management.book.services.DB;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    Label errorLabel;

    @FXML
    TextField mailTextField;

    @FXML
    PasswordField passwordPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onMouseClickedLogIn(MouseEvent event) {
        String mail = mailTextField.getText();
        String password = passwordPasswordField.getText();

        DB db = new DB();
        System.out.println(db.checkIfLogInIsCorrect(mail, password));

        if(db.checkIfLogInIsCorrect(mail, password)) {
            errorLabel.setText("");
        }
        else {
            errorLabel.setText("Login or password is incorrect");
        }
    }
}
