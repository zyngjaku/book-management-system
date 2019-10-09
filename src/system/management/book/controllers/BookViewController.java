package system.management.book.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import system.management.book.services.Book;
import system.management.book.services.DB;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BookViewController implements Initializable {

    private Book book;

    @FXML
    Label titleLabel, authorLabel, descriptionLabel, pageLabel, genresLabel, rateLabel;

    @FXML
    TextArea commentTextArea;

    @FXML
    ChoiceBox rateChoiceBox;

    @FXML
    ListView commentsListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=1;i<=5;i++) {
            rateChoiceBox.getItems().add(i);
        }
    }

    public void initVariable(Book book) {
        this.book = book;

        titleLabel.setText(this.book.getTitle());
        authorLabel.setText(this.book.getAuthor());
        descriptionLabel.setText(this.book.getDescription());

        pageLabel.setText(String.valueOf(this.book.getPages()));
        genresLabel.setText(String.valueOf(this.book.getGenres()));
        rateLabel.setText(String.valueOf(this.book.getRate()));
    }

    @FXML
    private void onMouseClickedAddComment(MouseEvent event) {
        String comment = commentTextArea.getText();
        //int rate = rateChoiceBox.getItems();

        DB db = new DB();
        //System.out.println(db.checkIfLogInIsCorrect(mail, password));
        System.out.println("gg");

        /*if(db.checkIfLogInIsCorrect(mail, password)) {
            errorLabel.setText("");
        }
        else {
            errorLabel.setText("Login or password is incorrect");
        }*/
    }

}
