package system.management.book.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

        top10Table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(top10Table.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = top10Table.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(newValue);


                TablePosition pos = (TablePosition) top10Table.getSelectionModel().getSelectedCells().get(0);
                TableColumn col = pos.getTableColumn();

                String data = (String) col.getCellObservableValue(1).getValue();

                //top10Table.getColumns().get(0).getCellObservableValue(newValue).getValue();

                System.out.println(">> " + data);
            }
        });
    }



}
