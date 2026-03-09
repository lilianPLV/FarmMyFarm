import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Objects;
import java.util.Random;

public class Land1 {
    public Random random = new Random();
    public boolean boostGrowth = false;
    public int nb_carrots = 0;
    public int nb_carrots_take = 0;
    public int nb_mais = 0;
    public int nb_mais_take = 0;
    public int nb_ble = 0;
    public int nb_ble_take = 0;
    public int argent = 25;
    @FXML private CheckBox button_carrot;
    @FXML private CheckBox button_mais;
    @FXML private CheckBox button_ble;
    @FXML private Button unlock;
    @FXML private GridPane champ2;
    @FXML private Label money;
    @FXML private Button button_home;
    @FXML private Label label_nb_carrot;
    @FXML private Label label_nb_mais;
    @FXML private Label label_nb_ble;
    @FXML
    public void initialize() {
        updateMoney();
        updateGraine();
        startRandomEvents();
    }

    public void startRandomEvents() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(15), event -> {
                    int chance = random.nextInt(100);
                    if (chance < 5) { // 5% de chance d'avoir l'évent
                        growthBoostEvent();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void growthBoostEvent() {
        System.out.println("🌧 Pluie ! Les plantes poussent 2X plus vite pendant 30 seconde !");
        boostGrowth = true;
        PauseTransition pause = new PauseTransition(Duration.seconds(30));
        pause.setOnFinished(event -> {
            boostGrowth = false;
            System.out.println("Fin du boost");
        });
        pause.play();
    }

    public void updateMoney() {
        money.setText("Money: " + argent);
    }

    public void updateGraine() {
        label_nb_carrot.setText("nb" + nb_carrots);
        label_nb_mais.setText("nb" + nb_mais);
        label_nb_ble.setText("nb" + nb_ble);
    }

    public void Planting(MouseEvent mouseEvent) {
        if (argent > 1000) {
            unlock.setDisable(false);
        }
        Pane cell = (Pane) mouseEvent.getSource();
        if (button_carrot.isSelected()){
            carrot(cell);
        } else if (button_mais.isSelected()) {
            mais(cell);

        } else if (button_ble.isSelected()) {
            ble(cell);
        }if (Objects.equals(cell.getId(), "ready_carrot")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_carrots_take += 1;
        } if (Objects.equals(cell.getId(), "ready_mais")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_mais_take += 1;
        } if (Objects.equals(cell.getId(), "ready_ble")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_ble_take += 1;
        }
    }

    public void shop(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Shop.fxml"));
        Parent root = loader.load();
        ShopController shopController = loader.getController();
        shopController.setLand(this);
        Stage stage = new Stage();
        stage.setTitle("Shop");
        stage.setScene(new Scene(root, 770, 500));
        stage.show();
    }

    public void unlock() {
        if (argent > 1000) {
            champ2.setDisable(false);
            argent -= 1000;
            updateMoney();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void carrot(Pane cell) {
        if (Objects.equals(cell.getId(), "empty")) {
            if (nb_carrots > 0) {
                nb_carrots -= 1;
                updateGraine();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("planted_carrot");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_carrot")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_carrot");
                        }
                    });
                    pause.play();
                } if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(4));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_carrot")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_carrot");
                        }
                    });
                    pause.play();
                }
            } else {
                System.out.println("Pas assez de graines de carrots");
            }
        } if (Objects.equals(cell.getId(), "ready")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_carrots_take += 1;
        }
    }

    public void mais(Pane cell) {
        if (Objects.equals(cell.getId(), "empty")) {
            if (nb_mais > 0) {
                nb_mais -= 1;
                updateGraine();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("planted_mais");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_mais")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_mais");
                        }
                    });
                    pause.play();
                } if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_mais")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_mais");
                        }
                    });
                    pause.play();
                }
            } else {
                System.out.println("Pas assez de graines de mais");
            }
        }
        if (Objects.equals(cell.getId(), "ready")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_mais_take += 1;
        }
    }

    public void ble(Pane cell) {
        if (Objects.equals(cell.getId(), "empty")) {
            if (nb_ble > 0) {
                nb_ble -= 1;
                updateGraine();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("planted_ble");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_ble")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_ble");
                        }
                    });
                    pause.play();
                } if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(20));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "planted_ble")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_ble");
                        }
                    });
                    pause.play();
                }
            } else {
                System.out.println("Pas assez de graines de ble");
            }
        }
        if (Objects.equals(cell.getId(), "ready")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty");
            nb_ble_take += 1;
        }
    }
    public void home() {
        button_home.setOnAction(e -> {
            Stage stage = (Stage) button_home.getScene().getWindow();
            stage.close();
        });
    }
}