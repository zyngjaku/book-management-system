package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import system.management.book.services.DB;
import org.apache.commons.validator.routines.EmailValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    Label errorLabel;

    @FXML
    TextField mailTextField, nameTextField, surnameTextField;

    @FXML
    PasswordField passwordPasswordField, repasswordPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onMouseClickedSignUp(MouseEvent event) {
        if(checkIfFieldsAreEmpty()) {
            setErrorText("red", "Fill the form below!");
        }
        else {
            if(!checkIfPasswordsAreEquals()) {
                setErrorText("red", "Passwords are not equal!");
            }
            else {
                if(!isEmailValid()) {
                    setErrorText("red", "Email is not valid!");
                }
                else {
                    String mail = mailTextField.getText();
                    String password = passwordPasswordField.getText();

                    DB db = new DB();

                    if(db.checkIfEmailNotExistAndCreateUser(mail, password)) {
                        setErrorText("green", "Account successfully created!");
                    }
                    else {
                        setErrorText("red", "This email already exists!");
                    }
                }
            }
        }
    }

    private boolean checkIfFieldsAreEmpty() {

        if(mailTextField.getText().isEmpty())
            return true;
        else if(nameTextField.getText().isEmpty())
            return true;
        else if(surnameTextField.getText().isEmpty())
            return true;
        else if(passwordPasswordField.getText().isEmpty())
            return true;
        else
            return repasswordPasswordField.getText().isEmpty();
    }

    private boolean checkIfPasswordsAreEquals() {

        return passwordPasswordField.getText().equals(repasswordPasswordField.getText());
    }

    private boolean isEmailValid() {
        String mail = mailTextField.getText();

        return EmailValidator.getInstance().isValid(mail);
    }

    private void setErrorText(String color, String text) {
        errorLabel.setStyle("-fx-text-fill: " + color);
        errorLabel.setText(text);
    }
}
