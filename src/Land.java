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
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Random;

public class Land {
    public Random random = new Random();
    public boolean boostGrowth = false;
    public static int nb_carrots = 10;
    public static int nb_carrots_take = 0;
    public static int nb_mais = 10;
    public static int nb_mais_take = 0;
    public static int nb_ble = 10;
    public static int nb_ble_take = 0;
    public static int argent = 1000000000;
    public static int nb_sheep = 10;
    public static int nb_chicken = 10;
    public static int nb_cow = 10;
    public int already_unlock = 0;
    public static int nb_laine = 0;
    public static int nb_egg = 0;
    public static int nb_lait = 0;
    public static Timestamp time = new Timestamp(System.currentTimeMillis());
    public static Timestamp timeAfter = new Timestamp(System.currentTimeMillis());
    @FXML private Button unlock;
    @FXML public GridPane champ1;
    @FXML private GridPane champ2;
    @FXML private Label money;
    @FXML private Button button_home;
    @FXML private StackPane slot_carrot;
    @FXML private StackPane slot_mais;
    @FXML private StackPane slot_ble;
    @FXML private Label label_slot_carrot;
    @FXML private Label label_slot_mais;
    @FXML private Label label_slot_ble;
    @FXML private StackPane slot_sheep;
    @FXML private StackPane slot_chicken;
    @FXML private StackPane slot_cow;
    @FXML private Label label_slot_sheep;
    @FXML private Label label_slot_chicken;
    @FXML private Label label_slot_cow;
    @FXML private GridPane etable;

    @FXML
    public void initialize() {
        Saves.load(this);
        updateMoney();
        updateSeed();
        updateAnimal();
        startRandomEvents();
        setupHotbar();
        setupFieldDrop();
        setupAnimalDrop();
        unlock.setVisible(false);
        champ2.setVisible(false);
        if (already_unlock == 1) {
            champ2.setVisible(true);
            champ2.setDisable(false);
        } else if (argent > 1000) {
            unlock.setVisible(true);
            unlock.setDisable(false);
        }
    }

    @FXML
    public void saveGame(ActionEvent event) {
        Saves.save(this);
        System.out.println("Partie sauvegardée !");
    }

    public Timestamp getTimeAfter() {
        return timeAfter;
    }

    public Timestamp getTime() {
        return time;
    }

    public GridPane getEtable() {
        return etable;
    }

    public GridPane getChamp2() {
        return champ2;
    }

    public GridPane getChamp1() {
        return champ1;
    }

    public int getArgent() {
        return argent;
    }

    public int getNb_carrots() {
        return nb_carrots;
    }

    public int getNb_carrots_take() {
        return nb_carrots_take;
    }

    public int getNb_mais() {
        return nb_mais;
    }

    public int getNb_mais_take() {
        return nb_mais_take;
    }

    public int getNb_ble() {
        return nb_ble;
    }

    public int getNb_ble_take() {
        return nb_ble_take;
    }

    public int getNb_sheep() {
        return nb_sheep;
    }

    public int getNb_chicken() {
        return nb_chicken;
    }

    public int getNb_cow() {
        return nb_cow;
    }

    public int getNb_laine() {
        return nb_laine;
    }

    public int getNb_egg() {
        return nb_egg;
    }

    public int getNb_lait() {
        return nb_lait;
    }

    public int getAlready_unlock() {
        return already_unlock;
    }

    public void updateSeed() {
        label_slot_carrot.setText("\uD83E\uDD55\n" + nb_carrots);
        label_slot_mais.setText("\uD83C\uDF3D\n" + nb_mais);
        label_slot_ble.setText("\uD83C\uDF3E\n" + nb_ble);
        slot_carrot.setOpacity(nb_carrots > 0 ? 1.0 : 0.4);
        slot_mais.setOpacity(nb_mais > 0 ? 1.0 : 0.4);
        slot_ble.setOpacity(nb_ble > 0 ? 1.0 : 0.4);
    }

    public void updateAnimal() {
        label_slot_sheep.setText("\uD83D\uDC11\n" + nb_sheep);
        label_slot_chicken.setText("\uD83D\uDC14\n" + nb_chicken);
        label_slot_cow.setText("\uD83D\uDC04\n" + nb_cow);
        slot_sheep.setOpacity(nb_sheep > 0 ? 1.0 : 0.4);
        slot_chicken.setOpacity(nb_chicken > 0 ? 1.0 : 0.4);
        slot_cow.setOpacity(nb_cow > 0 ? 1.0 : 0.4);
    }

    public void setupHotbar() {
        setupSlotDragSeed(slot_carrot, "carrot");
        setupSlotDragSeed(slot_mais, "mais");
        setupSlotDragSeed(slot_ble, "ble");
        setupSlotDragAnimal(slot_sheep, "sheep");
        setupSlotDragAnimal(slot_chicken, "chicken");
        setupSlotDragAnimal(slot_cow, "cow");
    }

    public void setupSlotDragSeed(StackPane slot, String seedType) {
        slot.setOnDragDetected(event -> {
            int count = switch (seedType) {
                case "carrot" -> nb_carrots;
                case "mais" -> nb_mais;
                case "ble" -> nb_ble;
                default -> 0;
            };
            if (count <= 0) return;
            Dragboard db = slot.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(seedType);
            db.setContent(content);
            event.consume();
        });
    }

    public void setupSlotDragAnimal(StackPane slot, String animalType) {
        slot.setOnDragDetected(event -> {
            int count = switch (animalType) {
                case "sheep" -> nb_sheep;
                case "chicken" -> nb_chicken;
                case "cow" -> nb_cow;
                default -> 0;
            };
            if (count <= 0) return;
            Dragboard db = slot.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(animalType);
            db.setContent(content);
            event.consume();
        });
    }

    public void Animaling(MouseEvent mouseEvent) {
        Pane cell = (Pane) mouseEvent.getSource();
        Hover(cell);
        if (Objects.equals(cell.getId(), "ready_sheep")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_laine += 1;
            System.out.println(nb_laine);
        } if (Objects.equals(cell.getId(), "ready_chicken")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_egg += 1;
            System.out.println(nb_egg);
        } if (Objects.equals(cell.getId(), "ready_cow")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_lait += 1;
            System.out.println(nb_lait);
        }
    }

    public void setupAnimalDrop() {
        etable.getChildren().forEach(node -> {
            if (Boolean.parseBoolean(String.valueOf(Objects.equals(node.getId(), "empty")))) {
                node.setId("empty_animal");
            }
            if (node instanceof Pane cell) setupCellDropAnimal(cell);
        });
    }

    private void setupCellDropAnimal(Pane cell) {
        cell.setOnDragOver(event -> {
            if (event.getDragboard().hasString() && cell.getId().equals("empty_animal")) {
                event.acceptTransferModes(TransferMode.COPY);
                cell.setStyle("-fx-background-color: #a0d080; -fx-border-color: white; -fx-border-width: 2;");
            }
            event.consume();
        });
        cell.setOnDragExited(event -> {
            if (cell.getId().equals("empty_animal")) {
                cell.setStyle("-fx-background-color: #693501;");
            }
            event.consume();
        });
        cell.setOnDragDropped(event -> {
            String animal = event.getDragboard().getString();
            if (animal != null && cell.getId().equals("empty_animal")) {
                switch (animal) {
                    case "sheep" -> Sheep(cell);
                    case "chicken" -> Chicken(cell);
                    case "cow" -> Cow(cell);
                }
                event.setDropCompleted(true);
            }
            event.consume();
        });
    }

    public void setupFieldDrop() {
        champ1.getChildren().forEach(node -> {
            if (node instanceof Pane cell) setupCellDrop(cell);
        });
        champ2.getChildren().forEach(node -> {
            if (node instanceof Pane cell) setupCellDrop(cell);
        });
    }

    public void setupCellDrop(Pane cell) {
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

    public void unlock() {
        if (argent > 1002) {
            if (already_unlock == 0) {
                champ2.setVisible(true);
                champ2.setDisable(false);
                unlock.setDisable(true);
                unlock.setVisible(false);
                argent -= 1000;
                updateMoney();
                already_unlock = 1;
            }else {
                System.out.println("Vous avez déjà acheté le terrain supplémentaire");
            }
        }else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void carrot(Pane cell) {
        if (Objects.equals(cell.getId(), "empty")) {
            if (nb_carrots > 0) {
                nb_carrots -= 1;
                updateSeed();
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
                updateSeed();
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
                updateSeed();
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

    public void Sheep(Pane cell) {
        if (Objects.equals(cell.getId(), "empty_animal")) {
            if (nb_sheep > 0) {
                nb_sheep -= 1;
                updateAnimal();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("animal_sheep");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_sheep")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_sheep");
                        }
                    });
                    pause.play();
                }
                if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(20));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_sheep")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_sheep");
                        }
                    });
                    pause.play();
                }
            }
        }
        if (Objects.equals(cell.getId(), "ready_sheep")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_laine += 1;
        }
    }

    public void Chicken(Pane cell) {
        if (Objects.equals(cell.getId(), "empty_animal")) {
            if (nb_chicken > 0) {
                nb_chicken -= 1;
                updateAnimal();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("animal_chicken");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_chicken")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_chicken");
                        }
                    });
                    pause.play();
                } if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(20));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_chicken")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_chicken");
                        }
                    });
                    pause.play();
                }
            }
        }
        if (Objects.equals(cell.getId(), "ready_chicken")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_egg += 1;
        }
    }

    public void Cow(Pane cell) {
        if (Objects.equals(cell.getId(), "empty_animal")) {
            if (nb_cow > 0) {
                nb_cow -= 1;
                updateAnimal();
                cell.setStyle("-fx-background-color: Green;");
                cell.setId("animal_cow");
                if (boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(10));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_cow")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_cow");
                        }
                    });
                    pause.play();
                } if (!boostGrowth) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(20));
                    pause.setOnFinished(event -> {
                        if (Objects.equals(cell.getId(), "animal_cow")) {
                            cell.setStyle("-fx-background-color: Yellow;");
                            cell.setId("ready_cow");
                        }
                    });
                    pause.play();
                }
            }
        }
        if (Objects.equals(cell.getId(), "ready_animal")) {
            cell.setStyle("-fx-background-color: #693501;");
            cell.setId("empty_animal");
            nb_lait += 1;
        }
    }

    public void home() {
        button_home.setOnAction(e -> {
            Stage stage = (Stage) button_home.getScene().getWindow();
            stage.close();
        });
    }
}