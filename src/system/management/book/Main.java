package system.management.book;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.management.book.services.DB;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layouts/main.fxml"));

        //stage.setResizable(false);
        stage.setTitle("Book Management System");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
