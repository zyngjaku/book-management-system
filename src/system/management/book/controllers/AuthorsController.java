package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import system.management.book.services.Author;
import system.management.book.services.Book;
import system.management.book.services.DB;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AuthorsController implements Initializable {

    private BorderPane borderPane;

    @FXML
    private TableView authorsTable;

    @FXML
    private TableColumn nameColumn, birthdayColumn, numberOfBooksColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        numberOfBooksColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfBooks"));

        DB db = new DB();
        LinkedList<Author> listofAllAuthors = db.getAllAutors();

        for(Author author : listofAllAuthors) {
            authorsTable.getItems().add(author);
        }
    }

    protected void initVariables(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
