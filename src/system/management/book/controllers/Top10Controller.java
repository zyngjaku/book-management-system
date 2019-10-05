package system.management.book.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import system.management.book.services.DB;
import system.management.book.services.TableTop10;

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
        LinkedList<TableTop10> listTop10Books = db.getTop10Books();

        for(TableTop10 book:listTop10Books) {
            top10Table.getItems().add(book);
        }

    }
}
