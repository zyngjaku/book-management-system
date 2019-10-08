package system.management.book.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.management.book.services.DB;
import system.management.book.services.Book;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Top10Controller implements Initializable {

    private BorderPane borderPane;

    @FXML
    private TableView top10Table;

    @FXML
    private TableColumn lpColumn, titleColumn, authorColumn, rateColumn;

    private LinkedList<Book> listTop10Books;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lpColumn.setCellValueFactory(new PropertyValueFactory<>("lp"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));

        DB db = new DB();
        LinkedList<Book> listTop10Books = db.getTop10Books();

        for(Book book:listTop10Books) {
            top10Table.getItems().add(book);
        }

        tableViewManager();
    }

    private void tableViewManager() {
        top10Table.setRowFactory(observable -> {
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
