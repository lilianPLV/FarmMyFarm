import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Sauvegardes {
    public static String PATH = "";
    @FXML private Button button_home;

    private void openLandWithSave(String savePath) {
        try {
            PATH = savePath;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Land.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Farm");
            stage.setScene(new Scene(root));
            stage.show();

            // Ferme la fenêtre Sauvegardes
            Stage current = (Stage) button_home.getScene().getWindow();
            current.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSave1() {
        openLandWithSave("src/saves/save1.txt");
    }

    public void loadSave2() {
        openLandWithSave("src/saves/save2.txt");
    }

    public void loadSave3() {
        openLandWithSave("src/saves/save3.txt");
    }

    public void deleteSave1() {
        new java.io.File("src/saves/save1.txt").delete();
        System.out.println("Save 1 supprimée");
    }

    public void deleteSave2() {
        new java.io.File("src/saves/save2.txt").delete();
        System.out.println("Save 2 supprimée");
    }

    public void deleteSave3() {
        new java.io.File("src/saves/save3.txt").delete();
        System.out.println("Save 3 supprimée");
    }

    public void home() {
        button_home.setOnAction(e -> {
            Stage stage = (Stage) button_home.getScene().getWindow();
            stage.close();
        });
    }
}