package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.management.book.services.DB;
import system.management.book.services.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Properties;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField mailTextField;

    @FXML
    private PasswordField passwordPasswordField;

    private BorderPane borderPane;
    private Button login, signup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initVariables(BorderPane borderPane, Button login, Button signup) {
        this.borderPane = borderPane;
        this.login = login;
        this.signup = signup;
    }

    @FXML
    private void onMouseClickedLogIn(MouseEvent event) {
        String mail = mailTextField.getText();
        String password = passwordPasswordField.getText();

        DB db = new DB();
        int idUser = db.checkIfLogInIsCorrect(mail, password);

        if(idUser != -1) {
            loginUser(idUser);
        }
        else {
            errorLabel.setText("Login or password is incorrect");
        }
    }

    private void loginUser(int idUser) {
        errorLabel.setText("");
        login.setVisible(false);
        signup.setText("LOG OUT");

        UserSession.logInUser(idUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layouts/top10.fxml"));
            Parent root = (Parent) loader.load();

            Top10Controller top10Controller = loader.getController();
            top10Controller.initVariables(borderPane);

            borderPane.setBottom(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
