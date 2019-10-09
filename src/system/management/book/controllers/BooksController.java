package system.management.book.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import system.management.book.services.Book;
import system.management.book.services.DB;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

    private BorderPane borderPane;

    @FXML
    TextField searchTextField;

    @FXML
    private TableView bookTable;

    @FXML
    private TableColumn lpColumn, titleColumn, authorColumn, releaseColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lpColumn.setCellValueFactory(new PropertyValueFactory<>("lp"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        releaseColumn.setCellValueFactory(new PropertyValueFactory<>("release"));

        DB db = new DB();
        LinkedList<Book> listBooks = db.getAllBooks();

        for(Book book:listBooks) {
            bookTable.getItems().add(book);
        }

        tableViewManager();
    }

    @FXML
    private void onMouseClickedSearch(MouseEvent event) {
        bookTable.getItems().clear();

        String phrase = searchTextField.getText();

        DB db = new DB();
        LinkedList<Book> listSearchedBooks = db.getSearchedBooks(phrase);

        for(Book book:listSearchedBooks) {
            bookTable.getItems().add(book);
        }
    }

    private void tableViewManager() {
        bookTable.setRowFactory(observable -> {
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

    protected void initVariables(BorderPane borderPane) {
        this.borderPane = borderPane;
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
