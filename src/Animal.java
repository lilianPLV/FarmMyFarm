import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Animal {
    private Land land;
    @FXML private GridPane gridpane_animal;

    public void setLand(Land land) {
        this.land = land;
    }

    
}
