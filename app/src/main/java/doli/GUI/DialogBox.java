package doli.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {
    private Label text;
    private ImageView profilePicture;
    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.profilePicture = iv;
        text.setWrapText(true);
        profilePicture.setFitWidth(100);
        profilePicture.setFitHeight(100);

        Circle circle = new Circle(30);
        circle.setFill(new ImagePattern(profilePicture.getImage()));
        text.setFont(new Font("Arial", 12));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(
                text, circle
        );
    }
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
    public static DialogBox getDoliDialogBox(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }
    public static DialogBox getUserDialogBox(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
