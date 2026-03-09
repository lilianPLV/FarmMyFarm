import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Animal {
    private Land land;
    @FXML private GridPane gridpane_animal;

    public void setLand(Land land) {
        this.land = land;
    }

    public void Planting_animal(MouseEvent mouseEvent) {
        Pane cell = (Pane) mouseEvent.getSource();
        
    }
}
