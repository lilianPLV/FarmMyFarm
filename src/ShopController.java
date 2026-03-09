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

    public void setLand(Land land) {
        this.land = land;
    }

    @FXML
    public void buy_carrot() {
        if (land.argent >= 2) {
            land.nb_carrots += 1;
            land.argent -= 2;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void buy_mais() {
        if (land.argent >= 10) {
            land.nb_mais += 1;
            land.argent -= 10;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void buy_ble() {
        if (land.argent >= 50) {
            land.nb_ble += 1;
            land.argent -= 50;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas assez d'argent");
        }
    }

    @FXML
    public void sell_carrot() {
        if (land.nb_carrots_take > 0) {
            land.nb_carrots_take -= 1;
            land.argent += 5;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    @FXML
    public void sell_mais() {
        if (land.nb_mais_take > 0) {
            land.nb_mais_take -= 1;
            land.argent += 12;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    @FXML
    public void sell_ble() {
        if (land.nb_ble_take > 0) {
            land.nb_ble_take -= 1;
            land.argent += 61;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    private void updateShopMoney() {
        if (shopMoney != null) {
            shopMoney.setText("Money: " + land.argent);
        }
    }

    private void updateShopGraine() {
        if (shop_nb_carrot != null) {
            shop_nb_carrot.setText("nb: " + land.nb_carrots);
        }
        if (shop_nb_mais != null) {
            shop_nb_mais.setText("nb: " + land.nb_mais);
        }
        if (shop_nb_ble != null) {
            shop_nb_ble.setText("nb: " + land.nb_ble);
        }
    }

    public void sell_all_carrot() {
        if (land.nb_carrots_take > 0) {
            land.argent += land.nb_carrots_take * 5;
            land.nb_carrots_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_mais() {
        if (land.nb_mais_take > 0) {
            land.argent += land.nb_mais_take * 12;
            land.nb_mais_take = 0;
            land.updateMoney();
            updateShopMoney();
            updateShopGraine();
        }
        else {
            System.out.println("Pas de plante à vendre");
        }
    }

    public void sell_all_ble() {
        if (land.nb_ble_take > 0) {
            land.argent += land.nb_ble_take * 61;
            land.nb_ble_take = 0;
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
            stage.close();
        });
    }
}