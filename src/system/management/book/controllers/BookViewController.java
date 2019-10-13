package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import system.management.book.services.*;

import javax.jws.soap.SOAPBinding;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @FXML
    Button addCommentButton;

    @FXML
    Label commentsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!UserSession.isUserLoggedIn()){
            commentsLabel.setText("If you want to rate a book, you need to log in to you account!");
            commentsLabel.setTextFill(Color.RED);
            commentTextArea.setDisable(true);
            rateChoiceBox.setDisable(true);
            addCommentButton.setDisable(true);
        }

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

        addCommentsToListView();
    }

    @FXML
    private void onMouseClickedAddComment(MouseEvent event) {
        if(!String.valueOf(rateChoiceBox.getValue()).equals("null")) {
            String comment = commentTextArea.getText();
            int rate = Integer.valueOf(String.valueOf(rateChoiceBox.getValue()));

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime currentDateTime = LocalDateTime.now();

            DB db = new DB();
            db.addNewComment(book.getIdBook(), UserSession.getIdUser(), comment, rate, dateTimeFormatter.format(currentDateTime));

            addCommentsToListView();
        }
    }

    private void addCommentsToListView() {
        DB db = new DB();
        LinkedList<Comment> lista = db.getCommentsForSpecificBook(book.getIdBook());

        commentsListView.getItems().clear();

        for(Comment c : lista)
            commentsListView.getItems().add(c);

        commentsListView.setCellFactory(commentsListView -> new CustomBookListView());
    }

}
