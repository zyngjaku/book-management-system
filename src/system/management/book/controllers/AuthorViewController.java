package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import system.management.book.services.Author;
import system.management.book.services.Book;
import system.management.book.services.DB;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AuthorViewController implements Initializable {

    private BorderPane borderPane;
    private Author author;

    @FXML
    Label authorLabel, dateOfBirthLabel, numberOfBooksLabel, averageRateLabel, numberOfCommentsLabel;

    @FXML
    TableView tableAuthorView;

    @FXML
    TableColumn titleColumn, rateColumn, releaseColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        releaseColumn.setCellValueFactory(new PropertyValueFactory<>("release"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));

        tableViewManager();
    }

    protected void initVariable(BorderPane borderPane, Author author) {
        this.borderPane = borderPane;
        this.author = author;

        for(Book book : author.getListOfBooks()) {
            tableAuthorView.getItems().add(book);
        }

        authorLabel.setText(author.getName());
        dateOfBirthLabel.setText(author.getBirthday().toString());
        numberOfBooksLabel.setText(author.getNumberOfBooks());
        averageRateLabel.setText(String.valueOf(author.getAvgRate()));
        //TODO: Set numberOfCommentsLabel
    }

    private void tableViewManager() {
        tableAuthorView.setRowFactory(observable -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Book clickedBook = row.getItem();

                    showBookView(clickedBook);
                }
            });

            return row ;
        });
    }

    private void showBookView(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layouts/book_view.fxml"));
            Parent root = (Parent) loader.load();

            BookViewController controller = loader.getController();
            controller.initVariable(book);

            borderPane.setBottom(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
