package system.management.book.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import system.management.book.services.DB;
import system.management.book.services.Book;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Top10Controller implements Initializable {

    @FXML
    private TableView top10Table;

    @FXML
    private TableColumn lpColumn, titleColumn, authorColumn, rateColumn;


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
                    Book clickedRow = row.getItem();
                    
                    System.out.println(clickedRow.getTitle());
                }
            });

            return row ;
        });
    }
}
