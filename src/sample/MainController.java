package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private Button clickedButton;

    @FXML
    private Button login, signup, top10, books, authors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickedButton = top10;

        setGUI("top10");
        setButtonColor(top10);
    }

    @FXML
    private void onMouseClickedLogIn(MouseEvent event) {
        setGUI("login");
        setButtonColor(login);
    }

    @FXML
    private void onMouseClickedSignUp(MouseEvent event) {
        setGUI("signup");
        setButtonColor(signup);
    }

    @FXML
    private void onMouseClickedTop10(MouseEvent event) {
        setGUI("top10");
        setButtonColor(top10);
    }

    @FXML
    private void onMouseClickedBooks(MouseEvent event) {
        setGUI("books");
        setButtonColor(books);
    }

    @FXML
    private void onMouseClickedAuthors(MouseEvent event) {
        setGUI("authors");
        setButtonColor(authors);
    }

    private void setGUI(String name) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(name + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(root);
    }

    private void setButtonColor(Button newClickedButton) {
        this.clickedButton.setStyle("-fx-background-color: #b1d347");

        this.clickedButton = newClickedButton;
        this.clickedButton.setStyle("-fx-background-color: #8da838");
    }
}
