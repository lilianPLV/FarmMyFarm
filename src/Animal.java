import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Animal {
    private Land land;
    @FXML private Button button_home;
    @FXML private GridPane gridpane_animal;

    public void setLand(Land land) {
        this.land = land;
    }

    public void Planting_animal(MouseEvent mouseEvent) {
        Pane cell = (Pane) mouseEvent.getSource();
        
    }

    public void home() {
        button_home.setOnAction(e -> {
            Stage stage = (Stage) button_home.getScene().getWindow();
            stage.close();
        });
    }
}
