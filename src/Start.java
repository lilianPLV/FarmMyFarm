import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Start {
    @FXML
    private Button button_start;
    @FXML
    private Button button_saves;
    @FXML
    private Button button_quit;

    public void play(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Land.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Land1");
        stage.setScene(new Scene(root, 770, 500));
        stage.show();
    }

    public void saves(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Saves.fxml"));
        Scene scene_saves = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Land1");
        stage.setScene(scene_saves);
        stage.show();

    }

    public void quit(ActionEvent actionEvent) {
        button_quit.setOnAction(e -> {
            Stage stage = (Stage) button_quit.getScene().getWindow();
            stage.close();
        });
    }
}
