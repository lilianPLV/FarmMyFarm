import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShopController {
    private Land land;
    @FXML private Label shopMoney;
    @FXML private Button button_home;
    @FXML private Label shop_nb_carrot;
    @FXML private Label shop_nb_mais;
    @FXML private Label shop_nb_ble;
    @FXML
    public void initialize() {
        updateShopMoney();
        updateShopGraine();
    }

    private void updateShopMoney() {
        shopMoney.setText("Money: " + Land.argent);
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

    public void setLand(Land land) {
        this.land = land;
    }

    @FXML
    public void buy_carrot() {
        if (Land.argent >= 2) {
            Land.nb_carrots += 1;
            Land.argent -= 2;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void buy_mais() {
        if (Land.argent >= 10) {
            Land.nb_mais += 1;
            Land.argent -= 10;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void buy_ble() {
        if (Land.argent >= 50) {
            Land.nb_ble += 1;
            Land.argent -= 50;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_sheep() {
        if (Land.argent >= 100) {
            Land.nb_sheep += 1;
            Land.argent -= 100;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_chicken() {
        if (Land.argent >= 100) {
            Land.nb_cow += 1;
            Land.argent -= 100;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    public void buy_cow() {
        if (Land.argent >= 100) {
            Land.nb_chicken += 1;
            Land.argent -= 100;
            land.updateMoney();
            land.updateSeed();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void sell_carrot() {
        if (Land.nb_carrots_take > 0) {
            Land.nb_carrots_take -= 1;
            Land.argent += 5;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de carrote(s) à vendre");
        }
    }

    @FXML
    public void sell_mais() {
        if (Land.nb_mais_take > 0) {
            Land.nb_mais_take -= 1;
            Land.argent += 12;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de mai(s) à vendre");
        }
    }

    @FXML
    public void sell_ble() {
        if (Land.nb_ble_take > 0) {
            Land.nb_ble_take -= 1;
            Land.argent += 61;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de blé(s) à vendre");
        }
    }

    @FXML
    public void sell_laine() {
        if (Land.nb_laine > 0) {
            Land.nb_laine -= 1;
            Land.argent += 61;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de laine(s) à vendre");
        }
    }

    @FXML
    public void sell_egg() {
        if (Land.nb_egg > 0) {
            Land.nb_egg -= 1;
            Land.argent += 61;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas d'oeuf(s) à vendre");
        }
    }

    @FXML
    public void sell_lait() {
        if (Land.nb_lait > 0) {
            Land.nb_lait -= 1;
            Land.argent += 61;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de lait(s) à vendre");
        }
    }

    public void sell_all_carrot() {
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
        if (Land.nb_carrots_take > 0) {
            Land.argent += Land.nb_carrots_take * 5;
            Land.nb_carrots_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_egg() {
        if (Land.nb_carrots_take > 0) {
            Land.argent += Land.nb_carrots_take * 5;
            Land.nb_carrots_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_lait() {
        if (Land.nb_carrots_take > 0) {
            Land.argent += Land.nb_carrots_take * 5;
            Land.nb_carrots_take = 0;
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