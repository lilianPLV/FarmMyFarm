import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Objects;
import java.util.Random;

public class Land {
    public Random random = new Random();
    public static boolean boostGrowth = false;
    public static int nb_carrots = 0;
    public static int nb_carrots_take = 0;
    public static int nb_mais = 0;
    public static int nb_mais_take = 0;
    public static int nb_ble = 0;
    public static int nb_ble_take = 0;
    public static int argent = 25;
    @FXML private Button unlock;
    @FXML private GridPane champ1;
    @FXML private GridPane champ2;
    @FXML private Label money;
    @FXML private Button button_home;
    @FXML private StackPane slot_carrot;
    @FXML private StackPane slot_mais;
    @FXML private StackPane slot_ble;
    @FXML private Label label_slot_carrot;
    @FXML private Label label_slot_mais;
    @FXML private Label label_slot_ble;

    @FXML
    public void initialize() {
        updateMoney();
        updateGraine();
        startRandomEvents();
        setupHotbarDrag();
        setupFieldDrop();
        if (argent > 1000) {
            unlock.setDisable(false);
        }
    }

    // ── NOUVEAU : mise à jour hotbar ──────────────────────────────
    public void updateGraine() {
        label_slot_carrot.setText("🥕\n" + nb_carrots);
        label_slot_mais.setText("🌽\n" + nb_mais);
        label_slot_ble.setText("🌾\n" + nb_ble);
        slot_carrot.setOpacity(nb_carrots > 0 ? 1.0 : 0.4);
        slot_mais.setOpacity(nb_mais > 0 ? 1.0 : 0.4);
        slot_ble.setOpacity(nb_ble > 0 ? 1.0 : 0.4);
    }

    private void setupHotbarDrag() {
        setupSlotDrag(slot_carrot, "carrot");
        setupSlotDrag(slot_mais, "mais");
        setupSlotDrag(slot_ble, "ble");
    }

    private void setupSlotDrag(StackPane slot, String seedType) {
        slot.setOnDragDetected(event -> {
            int count = switch (seedType) {
                case "carrot" -> nb_carrots;
                case "mais"   -> nb_mais;
                case "ble"    -> nb_ble;
                default       -> 0;
            };
            if (count <= 0) return;
            Dragboard db = slot.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(seedType);
            db.setContent(content);
            event.consume();
        });
    }

    private void setupFieldDrop() {
        champ1.getChildren().forEach(node -> {
            if (node instanceof Pane cell) setupCellDrop(cell);
        });
        champ2.getChildren().forEach(node -> {
            if (node instanceof Pane cell) setupCellDrop(cell);
        });
    }

    private void setupCellDrop(Pane cell) {
        cell.setOnDragOver(event -> {
            if (event.getDragboard().hasString() && cell.getId().equals("empty")) {
                event.acceptTransferModes(TransferMode.COPY);
                cell.setStyle("-fx-background-color: #a0d080; -fx-border-color: white; -fx-border-width: 2;");
            }
            event.consume();
        });
        cell.setOnDragExited(event -> {
            if (cell.getId().equals("empty")) {
                cell.setStyle("-fx-background-color: #693501;");
            }
            event.consume();
        });
        cell.setOnDragDropped(event -> {
            String seed = event.getDragboard().getString();
            if (seed != null && cell.getId().equals("empty")) {
                switch (seed) {
                    case "carrot" -> carrot(cell);
                    case "mais"   -> mais(cell);
                    case "ble"    -> ble(cell);
                }
                event.setDropCompleted(true);
            }
            event.consume();
        });
    }

    public void startRandomEvents() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(15), event -> {
                    int chance = random.nextInt(100);
                    if (chance < 5) { //5% de chance
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

    public void Planting(MouseEvent mouseEvent) {
        Pane cell = (Pane) mouseEvent.getSource();
        Hover(cell);
        if (Objects.equals(cell.getId(), "ready_carrot")) {
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

    public void Dropped() {
    }

    public void Hover(Pane cell) {
        cell.setOnDragEntered(dragEvent -> {
            if (Objects.equals(cell.getId(), "empty")) {
                cell.setStyle("-fx-background-color: #a0522d; -fx-border-color: white; -fx-border-width: 2;");
            }else if (Objects.equals(cell.getId(), "ready_carrot") || Objects.equals(cell.getId(), "ready_mais") || Objects.equals(cell.getId(), "ready_ble")) {
                cell.setStyle("-fx-background-color: #FFD700; -fx-border-color: white; -fx-border-width: 2;");
            }
        });
        cell.setOnMouseExited(event -> {
            if (Objects.equals(cell.getId(), "empty")) {
                cell.setStyle("-fx-background-color: #693501;");
            }else if (Objects.equals(cell.getId(), "ready_carrot") || Objects.equals(cell.getId(), "ready_mais") || Objects.equals(cell.getId(), "ready_ble")) {
                cell.setStyle("-fx-background-color: Yellow;");
            }else if (cell.getId().startsWith("planted_")) {
                cell.setStyle("-fx-background-color: Green;");
            }
        });
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

    public void animal(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Animal.fxml"));
        Parent root = loader.load();
        Animal animal = loader.getController();
        animal.setLand(this);
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
        }else {
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