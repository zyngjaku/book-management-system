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

public class BooksController implements Initializable {

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

}
