package system.management.book.controllers;

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
        setGUI("book_view");
        setButtonColor(authors);
    }

    private void setGUI(String name) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("../layouts/" + name + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setBottom(root);
    }

    private void setButtonColor(Button newClickedButton) {
        if(clickedButton != signup) this.clickedButton.setStyle("-fx-background-color: #eeeeee; -fx-font-weight: bold; -fx-border-radius: 0;");
        else this.clickedButton.setStyle("-fx-background-color: #B1D347; -fx-font-weight: bold; -fx-border-radius: 0;");

        this.clickedButton = newClickedButton;
        if(clickedButton != signup) this.clickedButton.setStyle("-fx-background-color: #d6d6d6; -fx-font-weight: bold; -fx-border-radius: 0;");
    }
}
