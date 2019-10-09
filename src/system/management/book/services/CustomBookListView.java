package system.management.book.services;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomBookListView extends ListCell<Comment> {
    private VBox content;
    private HBox top;
    private Text name;
    private Text comment;
    private Text date;
    private Text surname;
    private Text rate;

    public CustomBookListView() {
        super();

        date = new Text();
        name = new Text();
        surname = new Text();
        rate = new Text();
        comment = new Text();
        top = new HBox(name, new Text(" "), surname, new Text(" added comment on "), date, new Text(" with rate "), rate );
        content = new VBox(top, comment);
        content.setSpacing(3);
    }

    @Override
    protected void updateItem(Comment item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            name.setText(item.getName());
            comment.setText(item.getComment());
            rate.setText(String.valueOf(item.getRate()));
            surname.setText(item.getSurname());
            date.setText(String.valueOf(item.getDate()));
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
