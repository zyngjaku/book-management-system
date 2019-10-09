package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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

        tableViewManager();
    }

    protected void initVariables(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    private void tableViewManager() {
        authorsTable.setRowFactory(observable -> {
            TableRow<Author> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Author clickedAuthor = row.getItem();

                    showAutorView(clickedAuthor);
                }
            });

            return row ;
        });
    }

    private void showAutorView(Author author) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layouts/author_view.fxml"));
            Parent root = (Parent) loader.load();

            AuthorViewController controller = loader.getController();
            controller.initVariable(borderPane, author);

            borderPane.setBottom(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
