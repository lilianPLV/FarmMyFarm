import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;

public class ShopController {
    private Land land;
    private Random random = new Random();
    public int increase = 0;
    public int carrot_cost = 10;
    public int mais_cost = 50;
    public int ble_cost = 150;
    public int sheep_cost = 500;
    public int chicken_cost = 750;
    public int cow_cost = 1000;
    @FXML public Button button_acheter_boostchance;
    @FXML private Label shopMoney;
    @FXML private Button button_home;
    @FXML private Label shop_nb_carrot;
    @FXML private Label shop_nb_mais;
    @FXML private Label shop_nb_ble;
    @FXML private Label shop_nb_sheep;
    @FXML private Label shop_nb_chicken;
    @FXML private Label shop_nb_cow;
    @FXML private Label label_cost_luck;
    @FXML private Label label_luck_actuel;
    @FXML private Label label_cost_carrot;
    @FXML private Label label_cost_mais;
    @FXML private Label label_cost_ble;
    @FXML private Label label_cost_sheep;
    @FXML private Label label_cost_chicken;
    @FXML private Label label_cost_cow;
    @FXML
    public void initialize() {
        updateShopMoney();
        updateShopGraine();
        updateShopAnimal();
        updateIncrease();
        updateCostIncrease();
        updateLuckActuel();
        updateCarrotCost();
        updateMaisCost();
        updateBleCost();
        updateSheepCost();
        updateChickenCost();
        updateCowCost();
    }

    public void setLand(Land land) {
        this.land = land;
    }

    private void updateShopMoney() {
        shopMoney.setText("Money: " + Land.argent);
    }

    private void updateCarrotCost() {
        if (Land.nb_carrots < 10) {
            label_cost_carrot.setText("Cost: " + carrot_cost);
        } else {
            label_cost_carrot.setText("Cost: " + 15);
        }
    }

    private void updateMaisCost() {
        if (Land.nb_mais < 10) {
            label_cost_mais.setText("Cost: " + mais_cost);
        } else {
            label_cost_mais.setText("Cost: " + 65);
        }
    }

    private void updateBleCost() {
        if (Land.nb_ble < 10) {
            label_cost_ble.setText("Cost: " + ble_cost);
        } else {
            label_cost_ble.setText("Cost: " + 170);
        }
    }

    private void updateSheepCost() {
        if (Land.nb_sheep < 10) {
            label_cost_sheep.setText("Cost: " + sheep_cost);
        } else {
            label_cost_sheep.setText("Cost: " + 600);
        }
    }

    private void updateChickenCost() {
        if (Land.nb_chicken < 10) {
            label_cost_chicken.setText("Cost: " + chicken_cost);
        } else {
            label_cost_chicken.setText("Cost: " + 875);
        }
    }

    private void updateCowCost() {
        if (Land.nb_cow < 10) {
            label_cost_cow.setText("Cost: " + cow_cost);
        } else {
            label_cost_cow.setText("Cost: " + 1250);
        }
    }

    private void updateShopGraine() {
        if (shop_nb_carrot != null) {
            shop_nb_carrot.setText("nb: " + Land.nb_carrots);
        }
        if (shop_nb_mais != null) {
            shop_nb_mais.setText("nb: " + Land.nb_mais);
        }
        if (shop_nb_ble != null) {
            shop_nb_ble.setText("nb: " + Land.nb_ble);
        }
    }

    private void updateShopAnimal(){
        if (shop_nb_sheep != null) {
            shop_nb_sheep.setText("nb: " + Land.nb_sheep);
        }
        if (shop_nb_chicken != null) {
            shop_nb_chicken.setText("nb: " + Land.nb_chicken);
        }
        if (shop_nb_cow != null) {
            shop_nb_cow.setText("nb: " + Land.nb_cow);
        }
    }

    private void updateCostIncrease() {
        label_cost_luck.setText("Cost: " + increase);
    }

    private void updateLuckActuel() {
        label_luck_actuel.setText("Luck: " + Land.luck_boost + "%");
    }

    private void updateIncrease() {
        increase += 500;
    }

    public void buy_boost_luck() {
        if (Land.luck_boost < 100) {
            if (Land.argent > increase + 10) {
                Land.luck_boost += 1;
                Land.argent -= increase;
                System.out.println(Land.luck_boost);
                updateIncrease();
                updateCostIncrease();
                updateShopMoney();
                updateLuckActuel();
            }
        } if (Land.luck_boost == 100) {
            label_cost_luck.setText("Max luck reach");
            button_acheter_boostchance.setDisable(true);
        }
    }

    public void buy_carrot() {
        if (Land.nb_carrots > 10) {
            if (Land.argent >= 15) {
                Land.nb_carrots += 1;
                Land.argent -= 15;
                land.updateMoney();
                land.updateSeed();
                updateShopMoney();
                updateShopGraine();
                updateCarrotCost();
            }
        } else if (Land.argent >= 10) {
            Land.nb_carrots += 1;
            Land.argent -= 10;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
            updateCarrotCost();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_mais() {
        if (Land.nb_mais > 10) {
            if (Land.argent >= 65) {
                Land.nb_mais += 1;
                Land.argent -= 65;
                land.updateMoney();
                land.updateSeed();
                updateShopMoney();
                updateShopGraine();
                updateMaisCost();
            }
        }
        if (Land.argent >= 50) {
            Land.nb_mais += 1;
            Land.argent -= 50;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
            updateMaisCost();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_ble() {
        if (Land.nb_ble > 170) {
            if (Land.argent >= 15) {
                Land.nb_ble += 1;
                Land.argent -= 170;
                land.updateMoney();
                land.updateSeed();
                updateShopMoney();
                updateShopGraine();
                updateBleCost();
            }
        }
        if (Land.argent >= 150) {
            Land.nb_ble += 1;
            Land.argent -= 150;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
            updateBleCost();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_sheep() {
        if (Land.nb_sheep > 600) {
            if (Land.argent >= 15) {
                Land.nb_sheep += 1;
                Land.argent -= 600;
                land.updateMoney();
                land.updateAnimal();
                updateShopMoney();
                updateSheepCost();
                updateShopAnimal();
            }
        }
        if (Land.argent >= 500) {
            Land.nb_sheep += 1;
            Land.argent -= 500;
            land.updateMoney();
            land.updateAnimal();
            updateShopMoney();
            updateSheepCost();
            updateShopAnimal();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_chicken() {
        if (Land.nb_chicken > 875) {
            if (Land.argent >= 15) {
                Land.nb_chicken += 1;
                Land.argent -= 875;
                land.updateMoney();
                land.updateAnimal();
                updateShopMoney();
                updateChickenCost();
                updateShopAnimal();
            }
        }
        if (Land.argent >= 750) {
            Land.nb_chicken += 1;
            Land.argent -= 750;
            land.updateMoney();
            land.updateAnimal();
            updateShopMoney();
            updateChickenCost();
            updateShopAnimal();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_cow() {
        if (Land.nb_cow > 1250) {
            if (Land.argent >= 15) {
                Land.nb_cow += 1;
                Land.argent -= 1250;
                land.updateMoney();
                land.updateAnimal();
                updateShopMoney();
                updateCowCost();
                updateShopAnimal();
            }
        }
        if (Land.argent >= 1000) {
            Land.nb_cow += 1;
            Land.argent -= 1000;
            land.updateMoney();
            land.updateAnimal();
            updateShopMoney();
            updateCowCost();
            updateShopAnimal();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void sell_carrot() {
        int bonus = random.nextInt(5);
        if (Land.nb_carrots_take > 0) {
            Land.nb_carrots_take -= 1;
            Land.argent += 12 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de carrote(s) à vendre");
        }
    }

    public void sell_mais() {
        int bonus = random.nextInt(5);
        if (Land.nb_mais_take > 0) {
            Land.nb_mais_take -= 1;
            Land.argent += 57 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de mai(s) à vendre");
        }
    }

    public void sell_ble() {
        int bonus = random.nextInt(10);
        if (Land.nb_ble_take > 0) {
            Land.nb_ble_take -= 1;
            Land.argent += 160 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de blé(s) à vendre");
        }
    }

    public void sell_laine() {
        int bonus = random.nextInt(25);
        if (Land.nb_laine > 0) {
            Land.nb_laine -= 1;
            Land.argent += 200 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de laine(s) à vendre");
        }
    }

    public void sell_egg() {
        int bonus = random.nextInt(20);
        if (Land.nb_egg > 0) {
            Land.nb_egg -= 1;
            Land.argent += 250 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas d'oeuf(s) à vendre");
        }
    }

    public void sell_lait() {
        int bonus = random.nextInt(15);
        if (Land.nb_lait > 0) {
            Land.nb_lait -= 1;
            Land.argent += 275 + bonus;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de lait(s) à vendre");
        }
    }

    public void sell_all_carrot() {
        int bonus = random.nextInt();
        if (Land.nb_carrots_take > 0) {
            Land.argent += Land.nb_carrots_take * 5;
            Land.nb_carrots_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de carrotes à vendre");
        }
    }

    public void sell_all_mais() {
        if (Land.nb_mais_take > 0) {
            Land.argent += Land.nb_mais_take * 12;
            Land.nb_mais_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_ble() {
        if (Land.nb_ble_take > 0) {
            Land.argent += Land.nb_ble_take * 61;
            Land.nb_ble_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_laine() {
        if (Land.nb_laine > 0) {
            Land.argent += Land.nb_laine * 5;
            Land.nb_laine = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_egg() {
        if (Land.nb_egg > 0) {
            Land.argent += Land.nb_egg * 5;
            Land.nb_egg = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_lait() {
        if (Land.nb_lait > 0) {
            Land.argent += Land.nb_lait * 5;
            Land.nb_lait = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void home() {
        button_home.setOnAction(e -> {
            Stage stage = (Stage) button_home.getScene().getWindow();
            land.updateAnimal();
            land.updateSeed();
            land.updateMoney();
            stage.close();
        });
    }
}